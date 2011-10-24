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
package org.opengis.test.referencing.gigs;

import java.util.List;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.FileNotFoundException;


/**
 * The expected data, which are read from a CSV file in this package.
 *
 * @author  Martin Desruisseaux (Geomatys)
 * @version 3.1
 * @since   3.1
 */
final class ExpectedData {
    /**
     * The character used as column separator.
     */
    private static final char COLUMN_SEPARATOR = ',';

    /**
     * The separator for elements in a list.
     */
    private static final char LIST_ELEMENT_SEPARATOR = ';';

    /**
     * The character used for quoting strings. The column separator
     * can be used as an ordinary character inside the quoted string.
     */
    private static final char QUOTE = '"';

    /**
     * Escape character for escaping quotes.
     */
    private static final char ESCAPE = '\\';

    /**
     * The expected data.
     */
    private final List<Object[]> data;

    /**
     * The current row. This is the value of {@link #data} at index {@link #cursor}.
     */
    private Object[] currentRow;

    /**
     * The current cursor position.
     */
    private int cursor = -1;

    /**
     * Loads the data from the given file.
     *
     * @param file  The file name, without path.
     * @param types The type of each column. The only legal values at this time are
     *              {@link String}, {@link Integer}, {@link Double} and {@link Boolean}.
     */
    ExpectedData(final String file, final Class<?>... columnTypes) {
        try {
            final InputStream stream = ExpectedData.class.getResourceAsStream(file);
            if (stream == null) {
                throw new FileNotFoundException(file);
            }
            final BufferedReader reader = new BufferedReader(new InputStreamReader(stream, "UTF-8"));
            data = new ArrayList<Object[]>();
            String line;
            while ((line = reader.readLine()) != null) {
                line = line.trim();
                if (!line.isEmpty() && line.charAt(0) != '#') {
                    data.add(parseRow(line, columnTypes));
                }
            }
            reader.close();
        } catch (IOException e) {
            throw new DataException("Can not read " + file, e);
        }
    }

    /**
     * Parses a single row. The given line must be non-empty.
     */
    static Object[] parseRow(String line, final Class<?>[] columnTypes) {
        final Object[] row = new Object[columnTypes.length];
        for (int i=0; i<columnTypes.length; i++) {
            // Find the start index and end index of substring to parse.
            // If the part begin with a opening quote, we will search for
            // the closing quote before to search for the column separator.
            int skip=0, end=0;
            if (line.charAt(0) == QUOTE) {
                do if ((end = line.indexOf(QUOTE, end+1)) < 0) {
                    throw new DataException("Unbalanced quote.");
                } while (line.charAt(end-1) == ESCAPE);
                skip = 1; // Skip the quotes.
            }
            end = line.indexOf(COLUMN_SEPARATOR, end);
            if (end < 0) {
                end = line.length();
            }
            String part = line.substring(0, end).trim();
            if (skip != 0) {
                part = line.substring(skip, part.length()-skip).trim();
            }
            if (!part.isEmpty()) {
                final Class<?> type = columnTypes[i];
                final Object value;
                if      (type == String .class) value = part;
                else if (type == Integer.class) value = Integer.valueOf(part);
                else if (type == Double .class) value = Double .valueOf(part);
                else if (type == Boolean.class) value = Boolean.valueOf(part);
                else throw new DataException("Unsupported column type: " + type);
                row[i] = value;
            }
            if (++end >= line.length()) {
                break;
            }
            line = line.substring(end).trim();
        }
        return row;
    }

    /**
     * Moves the cursor to the next row and returns {@code true} on success, or {@code false}
     * if there is no more row to iterate.
     */
    public boolean next() {
        if (++cursor < data.size()) {
            currentRow = data.get(cursor);
            return true;
        } else {
            currentRow = null;
            return false;
        }
    }

    /**
     * Returns the value in the current row at the given column.
     *
     * @param  column The column from which to get the value.
     * @return The value in the given column, or {@code null} if none.
     * @throws NoSuchElementException If there is currently no active row.
     */
    private Object getValue(final int column) throws NoSuchElementException {
        if (currentRow != null) {
            return currentRow[column];
        }
        throw new NoSuchElementException("No active row.");
    }

    /**
     * Returns the value in the given column as a string.
     *
     * @param  column The column from which to get the value.
     * @return The value in the given column, or {@code null} if none.
     * @throws NoSuchElementException If there is currently no active row.
     * @throws ClassCastException If the value in the given column is not a string.
     */
    public String getString(final int column) {
        return (String) getValue(column);
    }

    /**
     * Returns the value in the given column as an integer.
     *
     * @param  column The column from which to get the value.
     * @return The value in the given column.
     * @throws NoSuchElementException If there is currently no active row.
     * @throws ClassCastException If the value in the given column is not an integer.
     * @throws NullPointerException If there is no value in the given column.
     */
    public int getInt(final int column) {
        return (Integer) getValue(column);
    }

    /**
     * Returns the value in the given column as an integer, or {@code null} if none.
     *
     * @param  column The column from which to get the value.
     * @return The value in the given column.
     * @throws NoSuchElementException If there is currently no active row.
     * @throws ClassCastException If the value in the given column is not an integer.
     */
    public Integer getIntOptional(final int column) {
        return (Integer) getValue(column);
    }

    /**
     * Returns the value in the given column as a double.
     *
     * @param  column The column from which to get the value.
     * @return The value in the given column, or {@code Double#NaN} if none.
     * @throws NoSuchElementException If there is currently no active row.
     * @throws ClassCastException If the value in the given column is not a double.
     */
    public double getDouble(final int column) {
        final Double value = (Double) getValue(column);
        return (value != null) ? value : Double.NaN;
    }

    /**
     * Returns the value in the given column as a boolean.
     *
     * @param  column The column from which to get the value.
     * @return The value in the given column.
     * @throws NoSuchElementException If there is currently no active row.
     * @throws ClassCastException If the value in the given column is not a boolean.
     * @throws NullPointerException If there is no value in the given column.
     */
    public boolean getBoolean(final int column) {
        return (Boolean) getValue(column);
    }

    /**
     * Returns the value in the given column as a list of strings.
     * The original data is assumed to be a semi-colon separated list.
     *
     * @param  column The column from which to get the value.
     * @return The values in the given column, or an empty array if none.
     * @throws NoSuchElementException If there is currently no active row.
     * @throws ClassCastException If the value in the given column is not a string.
     */
    public String[] getStrings(final int column) {
        final String data = getString(column);
        final List<String> elements = new ArrayList<String>(4);
        if (data != null) {
            int lower = 0;
            int upper = data.indexOf(LIST_ELEMENT_SEPARATOR);
            boolean stop = false;
            do {
                if (upper < 0) {
                    upper = data.length();
                    stop = true;
                }
                elements.add(data.substring(lower, upper).trim());
                lower = upper+1;
                upper = data.indexOf(LIST_ELEMENT_SEPARATOR, lower);
            } while (!stop);
        }
        return elements.toArray(new String[elements.size()]);
    }

    /**
     * Returns a string representation of the currently active row.
     */
    @Override
    public String toString() {
        final StringBuilder buffer = new StringBuilder("ExpectedData[");
        if (currentRow == null) {
            buffer.append("no active row");
        } else {
            buffer.append(cursor);
            String separator = ": ";
            for (final Object value : currentRow) {
                buffer.append(separator).append(value);
                separator = ", ";
            }
        }
        return buffer.append(']').toString();
    }
}
