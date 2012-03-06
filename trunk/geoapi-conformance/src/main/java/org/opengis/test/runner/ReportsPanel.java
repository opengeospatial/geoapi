/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    http://www.geoapi.org
 *
 *    Copyright (C) 2012 Open Geospatial Consortium, Inc.
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
import java.io.InputStream;
import java.io.OutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.Properties;
import java.util.prefs.Preferences;
import java.awt.Desktop;
import java.awt.GridLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JTextField;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.BorderFactory;
import javax.swing.border.Border;

import org.opengis.util.Factory;
import org.opengis.test.report.ReportGenerator;
import org.opengis.test.report.OperationParameters;
import org.opengis.referencing.operation.MathTransformFactory;
import static org.opengis.test.runner.MainFrame.REPORTS_DIRECTORY_KEY;


/**
 * A panel allowing users to choose the HTML reports to produce and their destination directory.
 * The HTML reports contain information like the list of supported Coordinate Operations or the
 * list of supported EPSG codes.
 *
 * @author Martin Desruisseaux
 */
@SuppressWarnings("serial")
final class ReportsPanel extends JPanel implements ActionListener {
    /**
     * The enumeration of HTML reports that {@link ReportsPanel} can produce. Those HTML reports
     * are actually produced by classes in the {@link org.opengis.test.report} package.
     *
     * @author Martin Desruisseaux
     */
    private static enum Controller {
        /**
         * The list of supported Coordinate Operations and their parameters.
         * This list usually includes the map projections.
         */
        COORDINATE_OPERATIONS("Supported Coordinate Operations") {
            @Override File run(final Properties properties, final File directory) throws IOException {
                final File file = new File(directory, "CoordinateOperations.html");
                final OperationParameters generator = new OperationParameters(properties);
                for (final Factory factory : FactoryProvider.forType(MathTransformFactory.class)) {
                    generator.add((MathTransformFactory) factory);
                }
                generator.write(file);
                return file;
            }
        },

        /**
         * The list of supported CRS authority codes.
         * This list usually includes the EPSG codes.
         */
        CRS_AUTHORITY_CODES("Supported CRS authority codes") {
            @Override File run(final Properties properties, final File directory) throws IOException {
                return null;
                // TODO
            }
        };

        /**
         * The label for this generator.
         */
        private final String label;

        /**
         * Creates a new instance.
         */
        private Controller(final String label) {
            this.label = label;
        }

        /**
         * Invoked by {@link ReportsPanel#generateReports} for creating the reports.
         * This method will delegates to the {@link ReportGenerator} of the appropriate class.
         *
         * @param  properties Product and vendor information (name, version, URL).
         * @param  directory  The destination directory.
         * @return The generated file, or {@code null} if none.
         */
        abstract File run(final Properties properties, final File directory) throws IOException;
    }

    /**
     * The choices of reports. The length of this list shall be equals to the amount
     * of {@link Controller} enumerations.
     */
    private final JCheckBox[] reportChoices;

    /**
     * The directory where HTML report will be generated.
     */
    private final JTextField directory;

    /**
     * The button for starting the HTML report generation.
     */
    private final JButton start;

    /**
     * Where to save the last user choices, for the next run.
     */
    private final Preferences preferences;

    /**
     * The desktop for browse operations, or {@code null} if unsupported.
     */
    private final Desktop desktop;

    /**
     * Vendor and product information extracted from the {@code META-INF/MANIFEST.MF} file.
     * The reports can not be generated before this field is set.
     */
    private ImplementationManifest manifest;

    /**
     * Creates a new panel.
     */
    ReportsPanel(final Desktop desktop, final Preferences preferences) {
        super(new GridBagLayout());
        this.desktop     = desktop;
        this.preferences = preferences;
        final GridBagConstraints c = new GridBagConstraints();
        /*
         * General description.
         */
        final JLabel description = new JLabel("<html>Select the reports to produce and the "
                + "destination directory. The reports will be generated as HTML pages.</html>");
        c.gridy=0; c.insets.top = c.insets.bottom = 6;
        c.gridx=0; c.weightx=1; c.gridwidth=3;
        c.anchor = GridBagConstraints.LINE_START;
        add(description, c);
        /*
         * Check boxes for selecting the reports to generate.
         */
        final Controller[] controllers = Controller.values();
        reportChoices = new JCheckBox[controllers.length];
        final JPanel choices = new JPanel(new GridLayout(controllers.length, 1));
        for (int i=0; i<controllers.length; i++) {
            final Controller controller = controllers[i];
            final JCheckBox check = new JCheckBox(controller.label, true);
            check.setName(controller.name());
            reportChoices[i] = check;
            choices.add(check);
        }
        choices.setOpaque(false);
        c.weightx=0; c.gridy++;
        c.insets.left = 15;
        add(choices, c);
        /*
         * The destination directory.
         */
        directory = new JTextField(preferences.get(REPORTS_DIRECTORY_KEY, null));
        final JLabel label = new JLabel("Directory:");
        label.setLabelFor(directory);
        final JButton browse = new JButton("Browse", new ImageIcon(ReportsPanel.class.getResource("mydocuments.png")));
        browse.addActionListener(new ActionListener() {
            @Override public void actionPerformed(final ActionEvent event) {
                showDialogChooser();
            }
        });
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridy++; c.gridwidth=1; c.insets.left=0; add(label,     c);
        c.gridx++; c.weightx=1;   c.insets.left=6; add(directory, c);
        c.gridx++; c.weightx=0;                    add(browse,    c);
        /*
         * The button starting the HTML reports generation.
         */
        start = new JButton("Generate reports", new ImageIcon(ReportsPanel.class.getResource("start.png")));
        start.addActionListener(this);
        start.setEnabled(false);
        c.fill   = GridBagConstraints.NONE;
        c.anchor = GridBagConstraints.CENTER;
        c.gridx=0; c.gridwidth=0; c.insets.top = 35;
        c.gridy++; add(start, c);
        /*
         * Final decoration.
         */
        final Border space = BorderFactory.createEmptyBorder(18, 18, 18, 18);
        setBorder(BorderFactory.createCompoundBorder(space,
                  BorderFactory.createCompoundBorder(
                  BorderFactory.createEtchedBorder(), space)));
        setOpaque(false);

        reportChoices[1].setEnabled(false); // TODO: not yet implemented.
    }

    /**
     * Enables or disables all check boxes.
     */
    private void setChoicesEnabled(final boolean enabled) {
        start.setEnabled(enabled);
        for (final JCheckBox choice : reportChoices) {
            choice.setEnabled(enabled);
        }
        reportChoices[1].setEnabled(false); // TODO: not yet implemented.
    }

    /**
     * Invoked when the user click on the "Browse" button. This method shows the
     * "save in directory" dialog. If the user confirms his choice, the
     * {@link #directory} text field will be updated accordingly.
     */
    final void showDialogChooser() {
        final String directory = this.directory.getText();
        final JFileChooser chooser = new JFileChooser(directory != null ? new File(directory) : null);
        chooser.setDialogTitle("Select a destination directory");
        chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        if (chooser.showSaveDialog(this) == JFileChooser.APPROVE_OPTION) {
            final String selected = chooser.getSelectedFile().getAbsolutePath();
            preferences.put(REPORTS_DIRECTORY_KEY, selected);
            this.directory.setText(selected);
        }
    }

    /**
     * Sets the implementation identification. This method must be invoked in the
     * Swing thread before the reports generation can be started.
     */
    final void setManifest(final ImplementationManifest manifest) {
        if (manifest != null) {
            this.manifest = manifest;
            start.setEnabled(true);
        }
    }

    /**
     * Invoked when the user click on the "Generate reports" button. Note that {@link #manifest}
     * should never be null, since the start button should be disabled until
     * {@link #setManifest(ImplementationManifest)} has been invoked.
     */
    @Override
    public void actionPerformed(final ActionEvent event) {
        final Properties properties = new Properties();
        properties.setProperty("PRODUCT.NAME",    manifest.vendor);
        properties.setProperty("PRODUCT.VERSION", manifest.version);
        properties.setProperty("PRODUCT.URL",     manifest.url);
        final File directory = new File(this.directory.getText());
        Controller[] controllers = Controller.values();
        int count = 0;
        for (int i=0; i<controllers.length; i++) {
            if (reportChoices[i].isSelected()) {
                controllers[count++] = controllers[i];
            }
        }
        if (count != controllers.length) {
            controllers = Arrays.copyOf(controllers, count);
        }
        setChoicesEnabled(false);
        try {
            generateReports(controllers, properties, directory);
        } finally {
            setChoicesEnabled(true);
        }
    }

    /**
     * Generates all selected reports, then display the last report (which is assumed
     * to be the index) in the web browser.
     *
     * @todo Needs to be run in a background thread.
     */
    private void generateReports(final Controller[] controllers, final Properties properties, final File directory) {
        try {
            copy("geoapi-reports.css", directory);
            File last = null;
            for (final Controller controller : controllers) {
                final File result = controller.run(properties, directory);
                if (result != null) {
                    last = result;
                }
            }
            if (last != null && desktop != null) {
                desktop.browse(last.toURI());
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.toString(),
                    "Can not write or show the reports", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Copies the the given resource file to the given file.
     */
    private static void copy(final String resource, final File directory) throws IOException {
        final InputStream in = ReportGenerator.class.getResourceAsStream(resource);
        final OutputStream out = new FileOutputStream(new File(directory, resource));
        try {
            int n;
            final byte[] buffer = new byte[1024];
            while ((n = in.read(buffer)) >= 0) {
                out.write(buffer, 0, n);
            }
        } finally {
            out.close();
            in.close();
        }
    }
}
