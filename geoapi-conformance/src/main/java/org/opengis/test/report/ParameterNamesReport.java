/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    http://www.geoapi.org
 *
 *    Copyright (C) 2011-2012 Open Geospatial Consortium, Inc.
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
package org.opengis.test.report;

import java.io.File;
import java.io.IOException;
import java.io.BufferedWriter;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.LinkedHashMap;
import java.util.Comparator;
import java.util.Properties;

import org.opengis.util.GenericName;
import org.opengis.parameter.GeneralParameterDescriptor;
import org.opengis.parameter.ParameterDescriptorGroup;
import org.opengis.referencing.IdentifiedObject;
import org.opengis.referencing.ReferenceIdentifier;
import org.opengis.referencing.operation.SingleOperation;
import org.opengis.referencing.operation.OperationMethod;
import org.opengis.referencing.operation.MathTransformFactory;


/**
 * Generates a list of operations (typically map projections) and their parameters.
 * The operations and their parameters are declared in the {@link #operations} map
 * as ({@link IdentifiedObject}, {@link ParameterDescriptorGroup}) pairs. In the particular
 * case of {@linkplain OperationMethod coordinate operation methods}, the pairs are simply
 * ({@link OperationMethod method}, <code>method.{@linkplain OperationMethod#getParameters()
 * getParameters()}</code>).
 *
 * <p>To use this class:</p>
 * <ul>
 *   <li>Create a new instance with a properties map containing the value documented in
 *       the {@link #properties} field.</li>
 *   <li>Invoke the {@link #add(IdentifiedObject, ParameterDescriptorGroup) add} method
 *       for each operation or factory to include in the HTML page.</li>
 *   <li>Invoke {@link #write(File)}.</li>
 * </ul>
 *
 * @author Martin Desruisseaux (Geomatys)
 * @version 3.1
 *
 * @since 3.1
 */
public class ParameterNamesReport extends Report implements Comparator<IdentifiedObject> {
    /**
     * The operations to publish in the HTML report (the {@linkplain java.util.Map.Entry#getKey() keys})
     * together with their parameter descriptors (the {@linkplain java.util.Map.Entry#getValue() values}).
     * This map is sorted according the criterion implemented by the
     * {@link #compare(IdentifiedObject, IdentifiedObject)} method.
     *
     * @see #add(IdentifiedObject, ParameterDescriptorGroup)
     * @see #add(MathTransformFactory)
     */
    protected final SortedMap<IdentifiedObject, ParameterDescriptorGroup> operations;

    /**
     * Creates a new report generator using the given property values.
     * See the {@link Report} javadoc for a list of expected values.
     *
     * @param properties The property values, or {@code null} for the default values.
     */
    public ParameterNamesReport(final Properties properties) {
        super(properties);
        operations = new TreeMap<IdentifiedObject, ParameterDescriptorGroup>(this);
    }

    /**
     * Adds an operation to be shown on the HTML page. The given arguments will
     * be added to the {@linkplain #operations} map in the order defined by the
     * {@link #compare(IdentifiedObject, IdentifiedObject)} method.
     *
     * @param  operation  The operation to show on the HTML page.
     * @param  parameters The operation parameters.
     * @throws IllegalArgumentException If the {@linkplain #operations operations map}
     *         already contains an entry which is equals to the given operation according
     *         to the {@link #compare(IdentifiedObject, IdentifiedObject) compare} method.
     *         The state of this {@code ParameterNamesReport} object is undetermined after
     *         this exception.
     */
    public void add(final IdentifiedObject operation, final ParameterDescriptorGroup parameters)
            throws IllegalArgumentException
    {
        if (operations.put(operation, parameters) != null) {
            throw new IllegalArgumentException("Operation \"" +
                    IdentifiedObjects.toString(operation.getName()) + "\" is declared twice.");
        }
    }

    /**
     * Convenience method adding all {@linkplain MathTransformFactory#getAvailableMethods(Class)
     * available methods} from the given factory. The operation methods will be added to the
     * {@linkplain #operations} map in the order defined by the {@link #compare(IdentifiedObject,
     * IdentifiedObject)} method.
     *
     * @param  factory The factory for which to add available methods.
     * @throws IllegalArgumentException If two coordinate operation methods are equal according
     *         to the {@link #compare(IdentifiedObject, IdentifiedObject) compare} method.
     *         The state of this {@code ParameterNamesReport} object is undetermined after
     *         this exception.
     */
    public void add(final MathTransformFactory factory) throws IllegalArgumentException {
        defaultProperties.setProperty("TITLE", "Supported Coordinate Operations");
        defaultProperties.setProperty("FILENAME", "CoordinateOperations.html");
        setVendor(factory.getVendor());
        for (final OperationMethod operation : factory.getAvailableMethods(SingleOperation.class)) {
            add(operation, operation.getParameters());
        }
    }

    /**
     * Returns the HTML text to use as a column header for each
     * {@linkplain ReferenceIdentifier#getCodeSpace() code spaces} or
     * {@linkplain GenericName#scope() scopes}. For each entry in the returned map, the
     * {@linkplain java.util.Map.Entry#getKey() key} is the code spaces or scope and the
     * {@linkplain java.util.Map.Entry#getValue() value} is the column header. The columns
     * will be shown in iteration order.
     *
     * <p>The default implementation gets the authorities from the {@linkplain #operations} keys.
     * Subclasses can override this method if they want to use a different set of authorities.</p>
     *
     * @return The name of all code spaces or scopes. Some typical values are {@code "EPSG"},
     *         {@code "OGC"}, {@code "ESRI"}, {@code "GeoTIFF"} or {@code "NetCDF"}.
     */
    @SuppressWarnings("unchecked")
    public Map<String,String> getColumnHeaders() {
        final Map<String,Object> codeSpaces = new LinkedHashMap<String,Object>(8);
        for (final IdentifiedObject op : operations.keySet()) {
            IdentifiedObjects.getCodeSpaces(op, codeSpaces);
        }
        for (final Map.Entry<String,Object> entry : codeSpaces.entrySet()) {
            entry.setValue(entry.getKey());
        }
        // We replaced all Boolean values by String values, so we can cheat here.
        return (Map) codeSpaces;
    }

    /**
     * Returns a user category for the given object, or {@code null} if none. If non-null,
     * this category will be formatted as a single row in the HTML table before all subsequent
     * objects of the same category.
     *
     * <p>The default implementation returns {@code null} in every cases. Subclasses can override
     * this method in order to classify objects by categories. For example a subclass may use this
     * method for classifying {@link OperationMethod} instances according their kind of map projection
     * (cylindrical, conical, planar). Note that subclasses that override this method shall also
     * override the {@link #compare(IdentifiedObject, IdentifiedObject)} method in such a way that
     * objects of the same category are grouped together.</p>
     *
     * @param  object The object for which to get the category.
     * @return The category of the given object, or {@code null} if none.
     */
    public String getCategory(final IdentifiedObject object) {
        return null;
    }

    /**
     * Returns a HTML anchor for the given category.
     */
    private String toAnchor(final String category) {
        return category.toLowerCase(getLocale()).replace(' ', '-');
    }

    /**
     * Compares the given operation methods for display order. This method is used for
     * sorting the operation methods in the order to be show on the HTML output page.
     * <p>
     * The default implementation performs a comparison of
     * {@linkplain IdentifiedObject#getName() object names} components in
     * the following order: {@linkplain ReferenceIdentifier#getCode() code},
     * {@linkplain ReferenceIdentifier#getCodeSpace() code space} and
     * {@linkplain ReferenceIdentifier#getVersion() version}.
     * Subclasses can override this method if they want a different ordering
     * on the HTML page.
     *
     * @param o1 The first operation method to compare.
     * @param o2 The second operation method to compare.
     * @return -1 if {@code o1} should appears before {@code o2}, -1 for the converse,
     *         or 0 if this method can not determine an ordering for the given object.
     */
    @Override
    public int compare(final IdentifiedObject o1, final IdentifiedObject o2) {
        return IdentifiedObjects.compare(o1.getName(), o2.getName());
    }

    /**
     * Formats the current content of the {@linkplain #operations} map as a HTML page in the given file.
     *
     * @param  destination The file to generate.
     * @return The given {@code destination} file.
     */
    @Override
    public File write(final File destination) throws IOException {
        filter("OperationParameters.html", destination);
        return destination;
    }

    /**
     * Invoked by {@link Report} every time a {@code ${FOO}} occurrence is found.
     * If the given key is one of those that are managed by this {@code ParameterNamesReport}
     * class, then this method will dispatch to the appropriate {@code writeFoo} method.
     */
    @Override
    final void writeValue(final String key) throws IOException {
        if ("CONTENT".equals(key)) {
            indentation = 6;
            writeCategories();
            writeTable(getColumnHeaders());
        } else {
            super.writeValue(key);
        }
    }

    /**
     * Writes the list of content before the table of operations. This list is created
     * only if {@link #getCategory(IdentifiedObject)} returned a non-null value for at
     * least one operation.
     *
     * @param  out Where to write the content.
     * @throws IOException If an error occurred while writing the content.
     */
    private void writeCategories() throws IOException {
        final BufferedWriter out = getOutput();
        String previous = null;
        for (final IdentifiedObject op : operations.keySet()) {
            final String category = getCategory(op);
            if (category != null && !category.equals(previous)) {
                if (previous == null) {
                    writeIndentation(); out.write("<p>Content:</p>");
                    writeIndentation(); out.write("<ul>");
                    out.newLine();
                    indentation += INDENT;
                }
                writeIndentation();
                out.write("<li><a href=\"#");
                out.write(toAnchor(category));
                out.write("\">");
                out.write(category);
                out.write("</a></li>\n");
                previous = category;
            }
        }
        if (previous != null) {
            indentation -= INDENT;
            writeIndentation();
            out.write("</ul>");
            out.newLine();
        }
    }

    /**
     * Writes the table of operations and their parameters.
     *
     * @param  out Where to write the content.
     * @param  columnHeaders The code spaces to use in columns, typically {@link #getColumnHeaders()}.
     * @throws IOException If an error occurred while writing the content.
     */
    private void writeTable(final Map<String,String> columnHeaders) throws IOException {
        final BufferedWriter out = getOutput();
        writeIndentation();
        out.write("<table cellspacing=\"0\" cellpadding=\"0\">");
        out.newLine();
        indentation += INDENT;
        String previous = null;
        boolean writeHeader = true;
        final String[] codeSpaces = columnHeaders.keySet().toArray(new String[columnHeaders.size()]);
        final String columnSpan = String.valueOf(codeSpaces.length);
        for (final Map.Entry<IdentifiedObject,ParameterDescriptorGroup> entry : operations.entrySet()) {
            final IdentifiedObject op = entry.getKey();
            final String category = getCategory(op);
            /*
             * If begining a new section in the table, print the category
             * in bold characters. The column headers will be printed below.
             */
            if (category != null && !category.equals(previous)) {
                writeIndentation();
                out.write("<tr class=\"sectionHead\"><th colspan=\"");
                out.write(columnSpan);         out.write("\"><a name=\"");
                out.write(toAnchor(category)); out.write("\">");
                out.write(category);           out.write("</a></th></tr>");
                out.newLine();
                writeHeader = true;
            }
            /*
             * If printing the first row, or if the above block printed a new category, print
             * the column headers. Otherwise we will just insert a horizontal separator.
             */
            writeIndentation();
            if (writeHeader) {
                out.write("<tr class=\"sectionTail\">");
                for (final String cs : columnHeaders.values()) {
                    out.write("<th>");
                    out.write(cs);
                    out.write("</th>");
                }
                out.write("</tr>");
                out.newLine();
            }
            /*
             * Print the operation name, then the name of all parameters.
             */
            final List<GeneralParameterDescriptor> parameters = entry.getValue().descriptors();
            final int size = parameters.size();
            writeRow(entry.getKey(), codeSpaces, true, false, false);
            for (int i=0; i<size; i++) {
                writeRow(parameters.get(i), codeSpaces, false, (i == 0), (i == size-1));
            }
            writeHeader = false;
        }
        indentation -= INDENT;
        writeIndentation();
        out.write("</table>");
    }

    /**
     * Writes a single row with the names of the given objects.
     *
     * @param  out Where to write the content.
     * @param  object The object from which to get the names.
     * @param  codeSpaces The code spaces to use in columns, typically {@link #getCodeSpaces()}.
     * @param  isGroup {@code true} if formatting a group, or {@code false} for a parameter.
     * @param  isHead {@code true} if formatting the first group of parameter values in a section.
     * @param  isTail {@code true} if formatting the last parameter value in a group.
     * @throws IOException If an error occurred while writing the content.
     */
    private void writeRow(final IdentifiedObject object, final String[] codeSpaces,
            final boolean isGroup, final boolean isHead, final boolean isTail) throws IOException
    {
        final BufferedWriter out = getOutput();
        @SuppressWarnings({"unchecked","rawtypes"})
        final Map<String,Boolean>[] nameSets = new Map[codeSpaces.length];
        for (int i=0; i<codeSpaces.length; i++) {
            nameSets[i] = IdentifiedObjects.getNameAndAliases(object, codeSpaces[i]);
        }
        writeIndentation();
        out.write("<tr");
        writeClasses(isGroup  ? "groupName" : null,
                     isHead   ? "groupHead" : null,
                     isTail   ? "groupTail" : null);
        out.write('>');
        for (int i=0; i<nameSets.length;) {
            final Map<String,Boolean> names = nameSets[i];
            /*
             * If the next columns are empty, allow the current column to use their space.
             * This allow a more compact table since EPSG names may be quite long, and in
             * many cases have no corresponding names in other code spaces.
             */
            int colspan = 1;
            while (++i < nameSets.length) {
                if (!nameSets[i].isEmpty()) {
                    break;
                }
                colspan++;
            }
            out.write("<td");
            if (colspan != 1) {
                out.write(" colspan=\"");
                out.write(Integer.toString(colspan));
                out.write('"');
            }
            out.write('>');
            /*
             * Write the parameter name. Typically there is only one name, since we are
             * formatting the names for only one code space. However in some few cases,
             * we still have many names declared by the same authority. The other names
             * are typically legacy names. In such case, we will put each additional
             * name on its own line in the same cell.
             */
            boolean hasMore = false;
            for (final Map.Entry<String,Boolean> entry : names.entrySet()) {
                if (hasMore) {
                    out.write("<br>");
                }
                if (!isGroup) {
                    out.write("\u00A0•\u00A0");
                }
                final boolean isPrimaryName = entry.getValue();
                if (isPrimaryName) {
                    out.write("<em>");
                }
                out.write(entry.getKey());
                if (isPrimaryName) {
                    out.write("</em>");
                }
                hasMore = true;
            }
            out.write("</td>");
        }
        out.write("</tr>");
        out.newLine();
    }
}
