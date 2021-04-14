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
package org.opengis.test;

import java.util.EventListener;


/**
 * A listener which is notified when a test begin, complete or fail.
 * Listeners can be registered by invoking {@link TestSuite#addTestListener(TestListener)}.
 *
 * @author  Martin Desruisseaux (Geomatys)
 * @version 3.1
 * @since   3.1
 *
 * @see TestSuite#addTestListener(TestListener)
 * @see TestEvent
 *
 * @deprecated To be replaced by JUnit 5 listener mechanism.
 */
@Deprecated
public interface TestListener extends EventListener {
    /**
     * Invoked when a test is about to start.
     *
     * @param event  a description of the test which is about to be run.
     */
    void starting(TestEvent event);

    /**
     * Invoked when a test succeeds.
     *
     * @param event  a description of the test which has been run.
     */
    void succeeded(TestEvent event);

    /**
     * Invoked when a test fails.
     *
     * @param event      a description of the test which has been run.
     * @param exception  the exception that occurred during the execution.
     */
    void failed(TestEvent event, Throwable exception);

    /**
     * Invoked when a test method finishes (whether passing or failing).
     *
     * @param event  a description of the test which has been run.
     */
    void finished(TestEvent event);
}
