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
