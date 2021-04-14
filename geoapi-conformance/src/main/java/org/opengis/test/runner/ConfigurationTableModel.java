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

import java.util.Map;
import java.util.List;
import java.util.Collections;
import javax.swing.table.AbstractTableModel;

import org.opengis.test.Configuration;


/**
 * The table model for the list of configuration entries.
 *
 * @author  Martin Desruisseaux (Geomatys)
 * @version 3.1
 * @since   3.1
 */
@SuppressWarnings("serial")
final class ConfigurationTableModel extends AbstractTableModel {
    /**
     * Index of columns handled by this model.
     */
    private static final int KEY_COLUMN   = 0,
                             VALUE_COLUMN = 1,
                             PASS_COLUMN  = 2;

    /**
     * The titles of all columns.
     */
    private static final String[] COLUMN_TITLES;
    static {
        COLUMN_TITLES = new String[3];
        COLUMN_TITLES[KEY_COLUMN]   = "Feature";
        COLUMN_TITLES[VALUE_COLUMN] = "Enabled";
        COLUMN_TITLES[PASS_COLUMN]  = "Remarks";
    };

    /**
     * The configuration entries.
     */
    List<Map.Entry<Configuration.Key<?>, ResultEntry.StatusOptional>> entries;

    /**
     * Creates an initially empty table model.
     */
    ConfigurationTableModel() {
        entries = Collections.emptyList();
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
        switch (column) {
            case KEY_COLUMN:   return String.class;
            case VALUE_COLUMN: return Boolean.class;
            case PASS_COLUMN:  return String.class;
            default: throw new IndexOutOfBoundsException(String.valueOf(column));
        }
    }

    /**
     * Returns the number of rows in this table.
     */
    @Override
    public int getRowCount() {
        return entries.size();
    }

    /**
     * Returns the value in the given cell.
     */
    @Override
    public Object getValueAt(final int row, final int column) {
        final Map.Entry<Configuration.Key<?>, ResultEntry.StatusOptional> entry = entries.get(row);
        switch (column) {
            case KEY_COLUMN:   return ResultEntry.separateWords(entry.getKey().name(), true);
            case VALUE_COLUMN: return entry.getValue() != ResultEntry.StatusOptional.DISABLED;
            case PASS_COLUMN:  return entry.getValue() == ResultEntry.StatusOptional.FAILED ? "Failed" : null;
            default: throw new IndexOutOfBoundsException(String.valueOf(column));
        }
    }
}
