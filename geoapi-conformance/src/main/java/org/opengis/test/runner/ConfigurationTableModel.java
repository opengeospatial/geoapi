/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    Copyright © 2011-2023 Open Geospatial Consortium, Inc.
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
