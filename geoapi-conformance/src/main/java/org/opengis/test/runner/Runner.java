/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    http://www.geoapi.org
 *
 *    Copyright (C) 2011-2021 Open Geospatial Consortium, Inc.
 *    All Rights Reserved. http://www.opengeospatial.org/ogc/legal
 *
 *    Permission to use, copy, and modify this software and its documentation, with
 *    or without modification, for any purpose and without fee or royalty is hereby
 *    granted, provided that you include the following on ALL copies of the software
 *    and documentation or portions thereof, including modifications, that you make:
 *
 *    1. The full text of this NOTICE in a location viewable to users of the
 *       redistributed or derivative work.
 *    2. Notice of any changes or modifications to the OGC files, including the
 *       date changes were made.
 *
 *    THIS SOFTWARE AND DOCUMENTATION IS PROVIDED "AS IS," AND COPYRIGHT HOLDERS MAKE
 *    NO REPRESENTATIONS OR WARRANTIES, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED
 *    TO, WARRANTIES OF MERCHANTABILITY OR FITNESS FOR ANY PARTICULAR PURPOSE OR THAT
 *    THE USE OF THE SOFTWARE OR DOCUMENTATION WILL NOT INFRINGE ANY THIRD PARTY
 *    PATENTS, COPYRIGHTS, TRADEMARKS OR OTHER RIGHTS.
 *
 *    COPYRIGHT HOLDERS WILL NOT BE LIABLE FOR ANY DIRECT, INDIRECT, SPECIAL OR
 *    CONSEQUENTIAL DAMAGES ARISING OUT OF ANY USE OF THE SOFTWARE OR DOCUMENTATION.
 *
 *    The name and trademarks of copyright holders may NOT be used in advertising or
 *    publicity pertaining to the software without specific, written prior permission.
 *    Title to copyright in this software and any associated documentation will at all
 *    times remain with copyright holders.
 */
package org.opengis.test.runner;

import java.util.Set;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.logging.Level;
import java.util.logging.Logger;

import java.io.File;
import java.net.URL;
import java.net.URLClassLoader;
import java.net.MalformedURLException;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import org.junit.runner.Result;
import org.junit.runner.JUnitCore;
import org.junit.runner.Description;
import org.junit.runner.notification.Failure;
import org.junit.runner.notification.RunListener;

import org.opengis.test.TestSuite;
import org.opengis.test.TestEvent;
import org.opengis.test.TestListener;

import static org.opengis.test.runner.ResultEntry.Status.*;


/**
 * Provides methods for running the tests. This class does not depend on Swing widgets
 * or on console program.
 *
 * @author  Martin Desruisseaux (Geomatys)
 * @version 3.1
 * @since   3.1
 */
final class Runner extends RunListener implements TestListener {
    /**
     * The logger for this package.
     */
    static final Logger LOGGER = Logger.getLogger("org.opengis.test.runner");

    /**
     * The platform-specific line separator.
     */
    static final String LINE_SEPARATOR = System.getProperty("line.separator", "\n");

    /**
     * The result of each tests. All a access to this list must be synchronized.
     */
    private final Set<ResultEntry> entries;

    /**
     * The listeners to inform of any new entry. Note that those listeners will
     * <strong>not</strong> be notified from the Swing thread. It is listener
     * responsibility to be safe regarding the Swing events queue.
     */
    private ChangeListener[] listeners;

    /**
     * The single change event to reuse every time an event is fired.
     */
    private final ChangeEvent event;

    /**
     * Creates a new, initially empty, runner.
     */
    Runner() {
        entries   = new LinkedHashSet<>();
        listeners = new ChangeListener[0];
        event     = new ChangeEvent(this);
    }

    /**
     * Sets the class loader to use for running the tests.
     *
     * @param files  the JAR files that contain the implementation to test.
     */
    static void setClassLoader(final File... files) throws MalformedURLException {
        final URL[] urls = new URL[files.length];
        for (int i=0; i<urls.length; i++) {
            urls[i] = files[i].toURI().toURL();
        }
        TestSuite.setClassLoader(new URLClassLoader(urls, TestSuite.class.getClassLoader()));
    }

    /**
     * Runs the JUnit tests.
     */
    void run() {
        final JUnitCore junit = new JUnitCore();
        junit.addListener(this);
        final Result result;
        try {
            TestSuite.addTestListener(this);
            result = junit.run(TestSuite.class);
        } finally {
            TestSuite.removeTestListener(this);
        }
        if (result.getRunCount() == 1 && result.getFailureCount() == 1) {
            final Throwable exception = result.getFailures().get(0).getException();
            LOGGER.log(Level.WARNING, exception.toString(), exception);
            // Should never happen, unless a problem occurred very soon in
            // the initialization process (typically a NoClassDefFoundError).
            // Without this hack, JUnit just silently do nothing...
        }
    }

    /**
     * Returns all entries. This method returns a copy of the internal array.
     * Changes to this {@code ReportData} object will not be reflected in that array.
     */
    ResultEntry[] getEntries() {
        synchronized (entries) {
            return entries.toArray(new ResultEntry[entries.size()]);
        }
    }

    /**
     * Adds a new test result. If we already have an entry for the same test method,
     * silently discards the new entry. We do that because test failure cause two
     * entries to be emitted: first an entry for the test failure, then another
     * entry because the test finished.
     */
    private void addEntry(final ResultEntry entry) {
        final ChangeListener[] list;
        synchronized (entries) {
            entries.add(entry);
            list = listeners;
        }
        for (final ChangeListener listener : list) {
            listener.stateChanged(event);
        }
    }

    /**
     * Called when a test is about to start.
     * Current implementation does nothing.
     */
    @Override
    public void starting(final TestEvent event) {
    }

    /**
     * Called when an atomic test has finished, whether the test succeeds or fails.
     * Current implementation does nothing - we will rely instead on the more specific
     * methods below.
     */
    @Override
    public void finished(final TestEvent event) {
    }

    /**
     * Called when a test succeed. This method adds a new entry in the {@linkplain #entries} list
     * with the {@code SUCCESS} status.
     */
    @Override
    public void succeeded(final TestEvent event) {
        addEntry(new ResultEntry(event, SUCCESS, null));
    }

    /**
     * Called when a test failed. This method adds a new entry in the {@linkplain #entries} list
     * with the {@code FAILURE} status and the stack trace.
     */
    @Override
    public void failed(final TestEvent event, final Throwable exception) {
        addEntry(new ResultEntry(event, FAILURE, exception));
    }

    /**
     * Called when an atomic test flags that it assumes a condition that is false.
     */
    @Override
    public void testAssumptionFailure(final Failure failure) {
        addEntry(new ResultEntry(failure.getDescription(), ASSUMPTION_NOT_MET, failure.getException()));
        super.testAssumptionFailure(failure);
    }

    /**
     * Called when a test will not be run, generally because a test method is annotated with
     * {@link org.junit.Ignore}.
     */
    @Override
    public void testIgnored(final Description description) throws Exception {
        addEntry(new ResultEntry(description, IGNORED, null));
        super.testIgnored(description);
    }

    /**
     * Adds a change listener to be invoked when new entries are added.
     * This is of interest mostly to swing widgets - we don't use this
     * listener for collecting new information.
     *
     * <p>Note that the listeners given to this method will <strong>not</strong> be notified from the
     * Swing thread. It is listener responsibility to be safe regarding the Swing events queue.</p>
     */
    void addChangeListener(final ChangeListener listener) {
        synchronized (entries) {
            ChangeListener[] list = listeners;
            final int length = list.length;
            list = Arrays.copyOf(list, length + 1);
            list[length] = listener;
            listeners = list;
        }
    }
}
