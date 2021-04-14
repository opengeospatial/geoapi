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
package org.opengis.test.runner;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;


/**
 * The cell renderer for the {@link ResultTableModel}. This cell renderer can display
 * cells in different color depending the test status.
 *
 * @author  Martin Desruisseaux (Geomatys)
 * @version 3.1
 * @since   3.1
 */
@SuppressWarnings("serial")
final class ResultCellRenderer extends DefaultTableCellRenderer {
    /**
     * The default text and background color.
     */
    private Color foreground, background;

    /**
     * The color to use for successful tests.
     */
    private final Color ignoreColor, failureColor;

    /**
     * The cell renderer for test coverage.
     */
    private final Coverage coverage;

    /**
     * Creates a default cell renderer for {@link ResultTableModel}.
     */
    ResultCellRenderer() {
        super();
        foreground   = super.getForeground();
        background   = super.getBackground();
        ignoreColor  = Color.GRAY;
        failureColor = Color.RED;
        coverage     = new Coverage();
    }

    /**
     * Specifies the text color for valid coordinates.
     */
    @Override
    public void setForeground(final Color c) {
        this.foreground = c;
        super.setForeground(c);
    }

    /**
     * Specifies the background color for valid coordinates.
     */
    @Override
    public void setBackground(final Color c) {
        this.background = c;
        super.setBackground(c);
    }

    /**
     * Returns the component for cell rendering.
     */
    @Override
    public Component getTableCellRendererComponent(final JTable table, final Object value,
            final boolean isSelected, final boolean hasFocus, final int row, final int column)
    {
        final ResultTableModel model = (ResultTableModel) table.getModel();
        final ResultEntry entry = model.getValueAt(row);
        Color foreground = this.foreground;
        Color background = this.background;
        boolean isIgnore = false;
        if (!isSelected) {
            switch (entry.status) {
                case IGNORED:
                case ASSUMPTION_NOT_MET: {
                    foreground = ignoreColor;
                    isIgnore = true;
                    break;
                }
                case FAILURE: {
                    foreground = failureColor;
                    break;
                }
            }
        }
        if (!isIgnore && column == ResultTableModel.RESULT_COLUMN) {
            coverage.report = entry;
            return coverage;
        }
        super.setBackground(background);
        super.setForeground(foreground);
        return super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
    }

    /**
     * The cell renderer for test coverage.
     */
    private static final class Coverage extends JPanel {
        /**
         * The space to keep between two cells.
         */
        private static final int MARGIN = 1;

        /**
         * The test report to paint. This value shall be set by
         * {@link ResultCellRenderer#getTableCellRendererComponent}
         * before this component is rendered.
         */
        ResultEntry report;

        /**
         * Creates a new instance.
         */
        Coverage() {
            setOpaque(false);
        }

        /**
         * Paints the test coverage.
         */
        @Override
        protected void paintComponent(final Graphics graphics) {
            super.paintComponent(graphics);
            if (report != null) {
                final Rectangle bounds = getBounds();
                bounds.x       = MARGIN;
                bounds.y       = MARGIN;
                bounds.width  -= MARGIN*2;
                bounds.height -= MARGIN*2;
                report.drawCoverage((Graphics2D) graphics, bounds);
            }
        }
    }
}
