/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    Copyright © 2011-2023 Open Geospatial Consortium, Inc.
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
package org.opengis.test.runner;

import java.io.Console;
import java.io.PrintWriter;
import java.awt.EventQueue;
import javax.swing.UIManager;


/**
 * Provides the entry point for running the {@code geoapi-conformance} tests.
 *
 * @author  Martin Desruisseaux (Geomatys)
 * @version 3.1
 * @since   3.1
 */
public final class Main {
    /**
     * Do not allow instantiation of this class.
     */
    private Main() {
    }

    /**
     * The application entry point. Current implementation {@linkplain #startSwingApplication()
     * starts the Swing application}. Future versions may provides different alternatives based
     * on the command-line arguments.
     *
     * @param arguments  must be an empty string in current version.
     *        Future versions may accept some command-line arguments.
     */
    @SuppressWarnings("UseOfSystemOutOrSystemErr")
    public static void main(final String[] arguments) {
        if (arguments.length == 0) {
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (Exception e) {
                // Ignore - keep the default L&F.
            }
            startSwingApplication();
        } else {
            final Console console = System.console();
            final PrintWriter out = (console != null) ? console.writer() : new PrintWriter(System.out, true);
            out.println("Usage: java -jar geoapi-conformance.jar");
            out.flush();
        }
    }

    /**
     * Starts the swing application.
     */
    public static void startSwingApplication() {
        final MainFrame frame = new MainFrame();
        frame.setVisible(true);
        EventQueue.invokeLater(frame);
    }
}
