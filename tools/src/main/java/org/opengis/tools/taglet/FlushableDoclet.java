/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    http://www.geoapi.org
 *
 *    Copyright (C) 2009-2021 Open Geospatial Consortium, Inc.
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
package org.opengis.tools.taglet;

import java.util.function.Consumer;
import java.io.Flushable;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import javax.tools.Diagnostic;
import jdk.javadoc.doclet.Reporter;
import jdk.javadoc.doclet.DocletEnvironment;
import jdk.javadoc.doclet.StandardDoclet;


/**
 * A doclet which delegates the work to the standard doclet and completes by generating
 * the list of departures after all Javadoc pages have been generated.
 *
 * <p>The {@code Consumer<Flushable>} interface is used as a callback mechanism for taglet initialization.
 * Taglets can not invoke {@code FlushableDoclet} methods directly because the doclet initialized by Javadoc
 * tools does not use the same class loader than taglet. {@link BlockTaglet} instances can communicate with
 * the {@code FlushableDoclet} instance only using objects from the standard Java library.</p>
 *
 * @author  Martin Desruisseaux (Geomatys)
 * @since   3.0.2
 * @version 3.0.2
 */
public final class FlushableDoclet extends StandardDoclet implements Consumer<Flushable> {
    /**
     * Process to execute after the Javadoc generation has been completed.
     * This is used for writing summary tables.
     */
    private Flushable postProcess;

    /**
     * Invoked by the Javadoc tools for instantiating the custom doclet.
     */
    public FlushableDoclet() {
    }

    /**
     * Returns a name identifying this doclet.
     *
     * @return "GeoAPI".
     */
    @Override
    public String getName() {
        return "GeoAPI";
    }

    /**
     * Registers an action to execute after doclet finished to generate Javadoc.
     * This initialization is done through an interface of the standard Java API ({@link Flushable})
     * because custom methods can not be invoked at taglet initialization time, because of different
     * class loaders.
     *
     * @param  finisher  action to execute at the end of javadoc generation.
     */
    @Override
    public void accept(final Flushable finisher) {
        postProcess = finisher;
    }

    /**
     * Invoked by Javadoc for starting the doclet.
     *
     * @param  environment  the Javadoc environment.
     * @return {@code true} on success, or {@code false} on failure.
     */
    @Override
    @SuppressWarnings("CallToPrintStackTrace")
    public boolean run(final DocletEnvironment environment) {
        boolean status = super.run(environment);
        if (status) try {
            if (postProcess != null) {
                postProcess.flush();
            }
        } catch (IOException e) {
            final Reporter reporter = getReporter();
            if (reporter != null) {
                final StringWriter buffer = new StringWriter();
                final PrintWriter p = new PrintWriter(buffer);
                e.printStackTrace(p);
                reporter.print(Diagnostic.Kind.ERROR, buffer.toString());
            } else {
                e.printStackTrace();
            }
            status = false;
        }
        return status;
    }
}
