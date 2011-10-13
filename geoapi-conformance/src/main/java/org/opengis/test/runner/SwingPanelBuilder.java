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

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;


/**
 * An utility class for building various panels used by {@link SwingFrame} or other components.
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
            final JLabel vendorID, final JLabel url)
    {
        final Insets insets = this.insets;
        insets.left = 12;
        insets.top = 3;
        final JPanel panel = new JPanel(new GridBagLayout());
        gridx=0; weightx=0; anchor=WEST;
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
}
