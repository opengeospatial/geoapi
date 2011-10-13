/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    http://www.geoapi.org
 *
 *    Copyright (C) 2011 Open Geospatial Consortium, Inc.
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

import java.io.File;
import java.io.IOException;
import java.util.prefs.Preferences;
import java.util.concurrent.ExecutionException;

import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingWorker;
import javax.swing.JOptionPane;
import javax.swing.JFileChooser;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.filechooser.FileNameExtensionFilter;


/**
 * The main frame of the test runner.
 *
 * @author  Martin Desruisseaux (Geomatys)
 * @version 3.1
 * @since   3.1
 */
@SuppressWarnings("serial")
final class SwingFrame extends JFrame implements Runnable {
    /**
     * The preference key for the directory in which to select JAR files.
     */
    private static final String JAR_DIRECTORY_KEY = "jar.directory";

    /**
     * Labels used for rendering information from {@link ImplementationManifest}.
     *
     * @see #setManifest(ImplementationManifest)
     */
    private final JLabel title, vendor, version, vendorID, url;

    /**
     * The table showing the results.
     */
    private final JTable results;

    /**
     * The object to use for running the tests.
     */
    private final Runner runner;

    /**
     * Creates a new frame.
     */
    SwingFrame() {
        super("GeoAPI conformance tests");
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setSize(800, 600);
        setLocationByPlatform(true);
        add(new SwingPanelBuilder().createManifestPane(
                title    = new JLabel(),
                version  = new JLabel(),
                vendor   = new JLabel(),
                vendorID = new JLabel(),
                url      = new JLabel()), BorderLayout.NORTH);

        runner = new Runner();
        results = new JTable(new SwingTableModel(runner));
        results.setAutoCreateRowSorter(true);
        add(new JScrollPane(results), BorderLayout.CENTER);
    }

    /**
     * Opens the file chooser dialog box for selecting JAR files. This method remember the
     * directory selected by the user last time this method was executed. This method is
     * invoked from the {@link Main} class.
     */
    @Override
    public void run() {
        final Preferences prefs = Preferences.userNodeForPackage(org.opengis.test.TestCase.class);
        final String directory = prefs.get(JAR_DIRECTORY_KEY, null);
        final JFileChooser chooser = new JFileChooser(directory != null ? new File(directory) : null);
        chooser.setDialogTitle("Select a GeoAPI implementation");
        chooser.setFileFilter(new FileNameExtensionFilter("Java Archive Files", "jar"));
        chooser.setMultiSelectionEnabled(true);
        if (chooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            prefs.put(JAR_DIRECTORY_KEY, chooser.getCurrentDirectory().getPath());
            new Loader(chooser.getSelectedFiles()).execute();
        }
    }

    /**
     * Sets the implementation identification.
     */
    private void setManifest(final ImplementationManifest manifest) {
        title   .setText(manifest != null ? manifest.title    : null);
        version .setText(manifest != null ? manifest.version  : null);
        vendor  .setText(manifest != null ? manifest.vendor   : null);
        vendorID.setText(manifest != null ? manifest.vendorID : null);
        url     .setText(manifest != null ? manifest.url      : null);
    }

    /**
     * The worker for loading JAR files in background.
     */
    private final class Loader extends SwingWorker<Object,Object> {
        /**
         * The JAR files.
         */
        private final File[] files;

        /**
         * Creates a new worker which will loads the given JAR files.
         */
        Loader(final File[] files) {
            this.files = files;
        }

        /**
         * Loads the given JAR files and creates a class loader for running the tests.
         */
        @Override
        protected Object doInBackground() throws IOException {
            setManifest(ImplementationManifest.parse(files));
            Runner.setClassLoader(files);
            runner.run();
            return null;
        }

        /**
         * Invoked from the Swing thread when the task is over of failed.
         */
        @Override
        protected void done() {
            try {
                get();
            } catch (InterruptedException e) {
                // Should not happen at this point.
            } catch (ExecutionException e) {
                JOptionPane.showMessageDialog(SwingFrame.this,
                        "An error occurred while processing the JAR files: " + e.getCause(),
                        "Can not use the JAR files", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}
