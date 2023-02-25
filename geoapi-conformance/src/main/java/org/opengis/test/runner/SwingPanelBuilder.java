/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    http://www.geoapi.org
 *
 *    Copyright (C) 2011-2021 Open Geospatial Consortium, Inc.
 *    All Rights Reserved. http://www.opengeospatial.org/ogc/legal
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

import java.awt.Font;
import java.awt.Insets;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JButton;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.border.Border;
import javax.swing.BorderFactory;


/**
 * An utility class for building various panels used by {@link MainFrame} or other components.
 * This class extends {@link GridBagConstraints} only for opportunist reason - do not rely on that.
 *
 * @author  Martin Desruisseaux (Geomatys)
 * @version 3.1
 * @since   3.1
 */
@SuppressWarnings("serial")
final class SwingPanelBuilder extends GridBagConstraints {
    /**
     * Creates a new builder.
     */
    SwingPanelBuilder() {
    }

    /**
     * Creates the panel where to display {@link ImplementationManifest} information.
     */
    JPanel createManifestPane(final JLabel title, final JLabel version, final JLabel vendor,
            final JLabel vendorID, final JLabel url, final JLabel specification,
            final JLabel specVersion, final JLabel specVendor)
    {
        final JPanel panel = new JPanel(new GridBagLayout());
        final JLabel implementsLabel;
        gridx=0; weightx=0; anchor=WEST; insets.left = 12;
        gridy=0; panel.add(createLabel("Title:",     title),    this);
        gridy++; panel.add(createLabel("Version:",   version),  this);
        gridy++; panel.add(createLabel("Vendor:",    vendor),   this);
        gridy++; panel.add(createLabel("Vendor ID:", vendorID), this);
        gridy++; panel.add(createLabel("URL:",       url),      this);
        gridx=1; weightx=1;
        gridy=0; panel.add(title,    this);
        gridy++; panel.add(version,  this);
        gridy++; panel.add(vendor,   this);
        gridy++; panel.add(vendorID, this);
        gridy++; panel.add(url,      this);
        gridx=2; weightx=0;
        gridy=0; panel.add(implementsLabel = createLabel("implements:", specification), this);
        gridx=3; weightx=1;
        gridy=0; panel.add(specification, this);
        gridy++; panel.add(specVersion,   this);
        gridy++; panel.add(specVendor,    this);

        implementsLabel.setFont(implementsLabel.getFont().deriveFont(Font.ITALIC));

        final Border space = BorderFactory.createEmptyBorder(6, 6, 6, 6);
        panel.setBorder(
                BorderFactory.createCompoundBorder(space,
                BorderFactory.createCompoundBorder(
                BorderFactory.createEtchedBorder(), space)));
        panel.setOpaque(false);
        return panel;
    }

    /**
     * Creates the panel where to display details about a particular test.
     */
    JPanel createDetailsPane(final JLabel testName, final JButton viewJavadoc,
            final JTable factories, final JTable configuration, final JTextArea exception)
    {
        final Font monospaced = Font.decode("Monospaced");
        testName.setFont(monospaced);

        final JPanel panel = new JPanel(new GridBagLayout());
        gridy=0; anchor=WEST; fill=BOTH; insets.left = 12;
        gridx=0; weightx=0; panel.add(createLabel("Test method:", testName), this);
        gridx++; weightx=1; panel.add(testName, this);
        gridx++; weightx=0; panel.add(viewJavadoc, this);
        gridx=0; gridy++; gridwidth=3; weightx=1; weighty=1; insets.top = 12;
        final JTabbedPane tabs = new JTabbedPane();
        panel.add(tabs, this);

        // If new tabs are added below, make sure that the index of the "Exception"
        // tab match the index given to 'tabs.setEnabledAt(…)' in the listener.
        tabs.addTab("Factories",     createScrollPane(factories));
        tabs.addTab("Configuration", createScrollPane(configuration));
        tabs.addTab("Exception",     createScrollPane(exception));
        exception.addPropertyChangeListener("enabled", new PropertyChangeListener() {
            @Override public void propertyChange(final PropertyChangeEvent event) {
                tabs.setEnabledAt(2, (Boolean) event.getNewValue());
                // Number 2 above is the index of "Exception" tab.
            }
        });
        exception.setEnabled (false);               // Shall be invoked only after we have set the above listener.
        exception.setEditable(false);
        exception.setFont(monospaced);
        panel.setOpaque(false);
        return panel;
    }

    /**
     * Creates a new label with the given text. The created label will be a header
     * for the given component.
     */
    private static JLabel createLabel(final String title, final JComponent labelFor) {
        final JLabel label = new JLabel(title);
        label.setLabelFor(labelFor);
        return label;
    }

    /**
     * Creates a transparent scroll pane for the given component with the given title.
     */
    private static JScrollPane createScrollPane(final JComponent component) {
        final JScrollPane scroll = new JScrollPane(component);
        component.setOpaque(false);
        scroll.getViewport().setOpaque(false);
        scroll.setOpaque(false);
        return scroll;
    }

    /**
     * Invoked by the layout manager when a component is added.
     */
    @Override
    @SuppressWarnings("CloneDoesntCallSuperClone")          // Okay because this class is final.
    public Object clone() {
        return new GridBagConstraints(gridx, gridy, gridwidth, gridheight,
                weightx, weighty, anchor, fill, (Insets) insets.clone(), ipadx, ipady);
    }
}
