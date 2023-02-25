/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    Copyright © 2012-2023 Open Geospatial Consortium, Inc.
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

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;
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
import javax.swing.JProgressBar;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.BorderFactory;
import javax.swing.border.Border;
import javax.swing.SwingWorker;

import org.opengis.util.FactoryException;
import org.opengis.test.report.Report;
import org.opengis.test.report.Reports;
import org.opengis.test.report.AuthorityCodesReport;
import org.opengis.test.report.OperationParametersReport;
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
        COORDINATE_OPERATIONS("Supported Coordinate Operations", OperationParametersReport.class),

        /**
         * The list of supported CRS authority codes.
         * This list usually includes the EPSG codes.
         */
        CRS_AUTHORITY_CODES("Supported CRS authority codes", AuthorityCodesReport.class);

        /**
         * The label for this generator.
         */
        final String label;

        /**
         * The kind of report to generate.
         */
        final Class<? extends Report> reportType;

        /**
         * Creates a new instance.
         */
        private Controller(final String label, final Class<? extends Report> reportType) {
            this.label = label;
            this.reportType = reportType;
        }
    }

    /**
     * The choices of reports. The length of this list shall be equal to the amount
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
    private final JButton startButton;

    /**
     * The progress bar.
     */
    final JProgressBar progressBar;

    /**
     * Where to save the last user choices, for the next run.
     */
    private final Preferences preferences;

    /**
     * The desktop for browse operations, or {@code null} if unsupported.
     */
    private final Desktop desktop;

    /**
     * Vendor and product information extracted from the {@code META-INF/MANIFEST.MF} file.
     * The reports cannot be generated before this field is set.
     */
    private ImplementationManifest manifest;

    /**
     * Creates a new panel.
     */
    @SuppressWarnings("OverridableMethodCallDuringObjectConstruction")
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
        startButton = new JButton("Generate reports", new ImageIcon(ReportsPanel.class.getResource("start.png")));
        startButton.addActionListener(this);
        startButton.setEnabled(false);
        c.fill   = GridBagConstraints.NONE;
        c.anchor = GridBagConstraints.CENTER;
        c.gridx=0; c.gridwidth=0; c.insets.top = 35;
        c.gridy++; add(startButton, c);
        /*
         * The progress bar.
         */
        progressBar = new JProgressBar();
        progressBar.setBorder(BorderFactory.createLoweredBevelBorder());
        c.fill = GridBagConstraints.HORIZONTAL;
        c.insets.left = c.insets.right = 40;
        c.gridy++; add(progressBar, c);
        /*
         * Final decoration.
         */
        final Border space = BorderFactory.createEmptyBorder(18, 18, 18, 18);
        setBorder(BorderFactory.createCompoundBorder(space,
                  BorderFactory.createCompoundBorder(
                  BorderFactory.createEtchedBorder(), space)));
        setOpaque(false);
    }

    /**
     * Enables or disables the start button and all check boxes. The progress bar is set
     * to the reverse of {@code enabled}, since this method is invoked when the reports
     * generation begins or ends.
     */
    final void setChoicesEnabled(final boolean enabled) {
        startButton.setEnabled(enabled);
        for (final JCheckBox choice : reportChoices) {
            choice.setEnabled(enabled);
        }
        progressBar.setEnabled(!enabled);
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
            startButton.setEnabled(true);
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
        final Controller[] controllers = Controller.values();
        int count = 0;
        for (int i=0; i<controllers.length; i++) {
            if (reportChoices[i].isSelected()) {
                controllers[count++] = controllers[i];
            }
        }
        Arrays.fill(controllers, count, controllers.length, null);
        /*
         * Generates all selected reports, then display the last report (which is assumed
         * to be the index) in the web browser. This code is invoked in the Swing thread,
         * and starts a worker in the background thread.
         */
        final Worker worker = new Worker(directory);
        worker.reports = new Reports(properties) {
            /** Creates reports only of the kind selected by the user. */
            @Override
            protected <T extends Report> T createReport(final Class<T> type) {
                for (final Controller controller : controllers) {
                    if (controller != null && controller.reportType == type) {
                        return super.createReport(type);
                    }
                }
                return null;
            }

            /** Be informed about the progress. */
            @Override
            protected void progress(final int position, final int count) {
                worker.progress(position, count);
            }
        };
        progressBar.setValue(0);
        setChoicesEnabled(false);
        worker.execute();
    }

    /**
     * The class for generating the reports in a background thread. The final value after
     * computation is the {@link File} to the index page of generated HTML reports. The
     * intermediate values are progress, as {@link Integer} ranging from 0 to 100 inclusive.
     */
    private final class Worker extends SwingWorker<File,Integer> {
        /**
         * The reports generator. Must be set to a non-null value
         * before to execute this worker.
         */
        Reports reports;

        /**
         * The destination directory specified at construction time.
         */
        private final File directory;

        /**
         * The progress set by the last call to {@link #progress(int, int)}, as a value ranging
         * from 0 to 100. This is used in order to avoid too many progress notifications to the
         * Swing widgets.
         */
        private int progress;

        /**
         * Creates a new worker which will write reports in the given directory.
         */
        Worker(final File directory) {
            this.directory = directory;
        }

        /**
         * Invoked by {@link SwingWorker} for generating the reports in a background thread.
         */
        @Override
        protected File doInBackground() throws FactoryException, IOException {
            reports.addAll();
            final File file = reports.write(directory);
            if (file != null && desktop != null) {
                desktop.browse(file.toURI());
            }
            return file;
        }

        /**
         * Invoked in the background thread when the report generation made some progress.
         */
        final void progress(final int position, final int count) {
            final int p = position * 100 / count;
            if (p != progress) {
                progress = p;
                publish(p);
            }
        }

        /**
         * Receives the progress notifications in the Swing thread.
         */
        @Override
        protected void process(final List<Integer> chunks) {
            final int size = chunks.size();
            if (size != 0) {
                progressBar.setValue(chunks.get(size-1));
            }
        }

        /**
         * Invoked in the Swing thread when the report generation is completed.
         * This method show an error panel if an exception occurred during the
         * reports creation.
         */
        @Override
        protected void done() {
            setChoicesEnabled(true);
            try {
                get();
                progressBar.setValue(100);              // Only if no exception.
            } catch (Exception exception) {
                Runner.LOGGER.log(Level.WARNING, exception.toString(), exception);
                JOptionPane.showMessageDialog(ReportsPanel.this, exception.toString(),
                        "Cannot write or show the reports", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}
