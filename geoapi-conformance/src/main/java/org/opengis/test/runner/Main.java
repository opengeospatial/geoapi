/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    http://www.geoapi.org
 *
 *    Copyright (C) 2011-2019 Open Geospatial Consortium, Inc.
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
