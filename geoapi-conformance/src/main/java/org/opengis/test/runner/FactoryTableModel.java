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

import java.util.List;
import java.util.Collections;
import javax.swing.table.AbstractTableModel;


/**
 * The table model for the list of factories.
 *
 * @author  Martin Desruisseaux (Geomatys)
 * @version 3.1
 * @since   3.1
 */
@SuppressWarnings("serial")
final class FactoryTableModel extends AbstractTableModel {
    /**
     * Index of columns handled by this model.
     * Must matches the columns documented in {@link ReportEntry#factories}.
     */
    static final int CATEGORY_COLUMN       = 0,
                     IMPLEMENTATION_COLUMN = 1,
                     VENDOR_COLUMN         = 2,
                     AUTHORITY_COLUMN      = 3;

    /**
     * The titles of all columns.
     */
    private static final String[] COLUMN_TITLES;
    static {
        COLUMN_TITLES = new String[4];
        COLUMN_TITLES[CATEGORY_COLUMN]        = "Category";
        COLUMN_TITLES[IMPLEMENTATION_COLUMN]  = "Implementation";
        COLUMN_TITLES[VENDOR_COLUMN]          = "Vendor";
        COLUMN_TITLES[AUTHORITY_COLUMN]       = "Authority";
    };

    /**
     * The factories.
     */
    List<String[]> entries;

    /**
     * Creates an initially empty table model.
     */
    FactoryTableModel() {
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
        return String.class;
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
    public String getValueAt(final int row, final int column) {
        return entries.get(row)[column];
    }
}
