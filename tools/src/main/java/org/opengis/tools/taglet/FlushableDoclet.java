/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    Copyright © 2009-2021 Open Geospatial Consortium, Inc.
 *    http://www.geoapi.org
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
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
 * Taglets cannot invoke {@code FlushableDoclet} methods directly because the doclet initialized by Javadoc
 * tools does not use the same class loader as taglet. {@link BlockTaglet} instances can communicate with
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
     * because custom methods cannot be invoked at taglet initialization time, because of different
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
