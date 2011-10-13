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

import java.awt.EventQueue;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.table.AbstractTableModel;


/**
 * The table model for the {@link ReportEntry} instances to be displayed.
 *
 * @author  Martin Desruisseaux (Geomatys)
 * @version 3.1
 * @since   3.1
 */
@SuppressWarnings("serial")
final class SwingTableModel extends AbstractTableModel implements ChangeListener, Runnable {
    /**
     * The titles of all columns.
     */
    private static final String[] COLUMN_TITLES = {
        "Class", "Method", "Result"
    };

    /**
     * The object which is receiving the result of each tests.
     */
    private final Runner data;

    /**
     * The result of each tests.
     */
    private ReportEntry[] entries;

    /**
     * Creates a table model for the given data.
     */
    SwingTableModel(final Runner data) {
        this.data = data;
        entries = data.getEntries();
        data.addChangeListener(this);
    }

    /**
     * Returns the number of columns in this table.
     */
    @Override
    public int getColumnCount() {
        return COLUMN_TITLES.length;
    }

    /**
     * Returns the name of the given column.
     */
    @Override
    public String getColumnName(final int column) {
        return COLUMN_TITLES[column];
    }

    /**
     * Returns the type of values in the given column.
     */
    @Override
    public Class<?> getColumnClass(final int column) {
        return String.class;
    }

    /**
     * Returns the number of rows in this table.
     */
    @Override
    public int getRowCount() {
        return entries.length;
    }

    /**
     * Returns the value in the given cell.
     */
    @Override
    public Object getValueAt(final int row, final int column) {
        final ReportEntry entry = entries[row];
        switch (column) {
            case 0:  return entry.className;
            case 1:  return entry.methodName;
            case 2:  return entry.status.name();
            default: throw new IndexOutOfBoundsException(String.valueOf(column));
        }
    }

    /**
     * Invoked every time a new entry has been added in {@link ReportData}.
     * This method may be invoked from any thread, so we just report the event
     * in the Swing thread.
     */
    @Override
    public void stateChanged(final ChangeEvent event) {
        EventQueue.invokeLater(this);
    }

    /**
     * Invoked in the Swing thread after a new entry has been added in {@link ReportData}.
     * This method fires a table event with the range of row index for the new entries.
     * We presume that the previous entries has not been modified.
     */
    @Override
    public void run() {
        final int lower = entries.length;
        entries = data.getEntries();
        final int upper = entries.length;
        if (lower != upper) {
            fireTableRowsInserted(lower, upper-1);
        }
    }
}
