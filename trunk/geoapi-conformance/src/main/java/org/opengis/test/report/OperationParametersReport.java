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
import java.util.Map;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
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
 * The operations are described by instances of an {@link IdentifiedObject} subtype,
 * for example coordinates {@link OperationMethod}. Each operation can be associated
 * to a {@link ParameterDescriptorGroup} instance. Those elements can be
 * {@linkplain #add(IdentifiedObject, ParameterDescriptorGroup) added directly}
 * in the {@linkplain #groups} list. Alternatively, a convenience method can be used
 * for adding all operation methods available from a given {@link MathTransformFactory}.
 *
 * <p>This class recognizes the following property values:</p>
 *
 * <table border="1" cellspacing="0">
 *   <tr bgcolor="#CCCCFF"><th>Key</th>  <th>Remarks</th>   <th>Meaning</th></tr>
 *   <tr><td>{@code TITLE}</td>          <td>&nbsp;</td>    <td>Title of the web page to produce.</td></tr>
 *   <tr><td>{@code DESCRIPTION}</td>    <td>optional</td>  <td>Description to write after the introductory paragraph.</td></tr>
 *   <tr><td>{@code OBJECTS.KIND}</td>   <td>&nbsp;</td>    <td>Kind of objects listed in the page (e.g. "<cite>Operation Methods</cite>").</td></tr>
 *   <tr><td>{@code PRODUCT.NAME}</td>   <td>&nbsp;</td>    <td>Name of the product for which the report is generated.</td></tr>
 *   <tr><td>{@code PRODUCT.VERSION}</td><td>&nbsp;</td>    <td>Version of the product for which the report is generated.</td></tr>
 *   <tr><td>{@code PRODUCT.URL}</td>    <td>&nbsp;</td>    <td>URL where more information is available about the product.</td></tr>
 *   <tr><td>{@code JAVADOC.GEOAPI}</td> <td>&nbsp;</td>    <td>Base URL of GeoAPI javadoc.</td></tr>
 *   <tr><td>{@code FILENAME}</td>       <td>&nbsp;</td>    <td>Name of the file to create if the {@link #write(File)} argument is a directory.</td></tr>
 * </table>
 *
 * <p><b>How to use this class:</b></p>
 * <ul>
 *   <li>Create a {@link Properties} map with the values documented in the above table. Default
 *       values exist for many keys, but may depend on the environment. It is safer to specify
 *       values explicitly when they are known.</li>
 *   <li>Create a new {@code OperationParametersReport} with the above properties map
 *       given to the constructor.</li>
 *   <li>Invoke one of the {@link #add(IdentifiedObject, ParameterDescriptorGroup) add} method
 *       for each operation or factory to include in the HTML page.</li>
 *   <li>Invoke {@link #write(File)}.</li>
 * </ul>
 *
 * @author Martin Desruisseaux (Geomatys)
 * @version 3.1
 *
 * @since 3.1
 */
public class OperationParametersReport extends Report {
    /**
     * A single group in the table produced by {@link OperationParametersReport}.
     * Instances of this class are created by the
     * {@link OperationParametersReport#add(IdentifiedObject, ParameterDescriptorGroup)} method.
     * Subclasses of {@code OperationParametersReport} can override that method in order to modify
     * the content of a group.
     *
     * @author Martin Desruisseaux (Geomatys)
     * @version 3.1
     *
     * @since 3.1
     */
    protected static class Group implements Comparable<Group> {
        /**
         * An optional user category for the {@linkplain #operations}, or {@code null} if none.
         * If non-null, this category will be formatted as a single row in the HTML table before
         * all subsequent objects of the same category.
         *
         * <p>The default value is {@code null} in every cases. Subclasses can modify this value
         * in order to classify operations by category. For example subclasses may use this value
         * for classifying {@link OperationMethod} instances according the kind of map projection
         * (<cite>planar</cite>, <cite>cylindrical</cite>, <cite>conic</cite>).</p>
         */
        public String category;

        /**
         * The object for which to describe the {@linkplain #parameters}.
         */
        public final IdentifiedObject operation;

        /**
         * The parameters of the identified object. This list is initialized to a copy of
         * the {@linkplain ParameterDescriptorGroup#descriptors() parameter descriptors}
         * given at construction time. Consequently, subclasses can freely modify the
         * content of this list.
         */
        public final List<GeneralParameterDescriptor> parameters;

        /**
         * Creates a group to be shown on the HTML page.
         *
         * @param  operation  The operation to show on the HTML page.
         * @param  parameters The operation parameters.
         */
        public Group(final IdentifiedObject operation, final ParameterDescriptorGroup parameters) {
            this.operation  = operation;
            this.parameters = new ArrayList<GeneralParameterDescriptor>(parameters.descriptors());
        }

        /**
         * Compares this group with the given object for order. This method is used for sorting
         * the operations in the order to be show on the HTML output page.
         *
         * <p>The default implementation compare that {@linkplain #category} first - this is
         * needed in order to ensure that operations of the same category are grouped. Then,
         * this method compares {@linkplain IdentifiedObject#getName() object names} components
         * in the following order: {@linkplain ReferenceIdentifier#getCode() code},
         * {@linkplain ReferenceIdentifier#getCodeSpace() code space} and
         * {@linkplain ReferenceIdentifier#getVersion() version}.</p>
         *
         * <p>Subclasses can override this method if they want a different ordering
         * on the HTML page.</p>
         *
         * @param o The other group to compare with this group.
         * @return -1 if {@code this} should appears before {@code o}, -1 for the converse,
         *         or 0 if this method can not determine an ordering for the given object.
         */
        @Override
        public int compareTo(final Group o) {
            int c = IdentifiedObjects.compare(category, o.category);
            if (c == 0) {
                c = IdentifiedObjects.compare(operation.getName(), o.operation.getName());
            }
            return c;
        }
    }

    /**
     * The operations to publish in the HTML report.
     *
     * @see #add(IdentifiedObject, ParameterDescriptorGroup)
     * @see #add(MathTransformFactory)
     */
    protected final List<Group> groups;

    /**
     * The number of indentation spaces.
     */
    private int indentation;

    /**
     * Creates a new report generator using the given property values.
     * See the class javadoc for a list of expected values.
     *
     * @param properties The property values, or {@code null} for the default values.
     */
    public OperationParametersReport(final Properties properties) {
        super(properties);
        groups = new ArrayList<Group>();
        defaultProperties.setProperty("TITLE", "Supported ${OBJECTS.KIND}");
    }

    /**
     * Adds an operation to be shown on the HTML page.
     *
     * @param  operation  The operation to show on the HTML page.
     * @param  parameters The operation parameters.
     */
    public void add(final IdentifiedObject operation, final ParameterDescriptorGroup parameters) {
        groups.add(new Group(operation, parameters));
    }

    /**
     * Convenience method adding all {@linkplain MathTransformFactory#getAvailableMethods(Class)
     * available methods} from the given factory. Each {@linkplain OperationMethod coordinate
     * operation method} is added to the {@linkplain #groups} list as below:
     *
     * <blockquote><code>{@linkplain #add(IdentifiedObject, ParameterDescriptorGroup)
     * add}(method, method.{@linkplain OperationMethod#getParameters() getParameters()});</code></blockquote>
     *
     * @param  factory The factory for which to add available methods.
     */
    public void add(final MathTransformFactory factory) {
        defaultProperties.setProperty("OBJECTS.KIND", "Coordinate Operations");
        defaultProperties.setProperty("FILENAME", "CoordinateOperations.html");
        setVendor("PRODUCT", factory.getVendor());
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
     * <p>The default implementation gets the authorities from the {@link Group#operation}.
     * Subclasses can override this method if they want to use a different set of authorities.</p>
     *
     * @return The name of all code spaces or scopes. Some typical values are {@code "EPSG"},
     *         {@code "OGC"}, {@code "ESRI"}, {@code "GeoTIFF"} or {@code "NetCDF"}.
     */
    @SuppressWarnings("unchecked")
    public Map<String,String> getColumnHeaders() {
        final Map<String,Object> codeSpaces = new LinkedHashMap<String,Object>(8);
        for (final Group group : groups) {
            IdentifiedObjects.getCodeSpaces(group.operation, codeSpaces);
        }
        for (final Map.Entry<String,Object> entry : codeSpaces.entrySet()) {
            entry.setValue(entry.getKey());
        }
        // We replaced all Boolean values by String values, so we can cheat here.
        return (Map) codeSpaces;
    }

    /**
     * Returns a HTML anchor for the given category.
     */
    private String toAnchor(final String category) {
        return category.toLowerCase(getLocale()).replace(' ', '-');
    }

    /**
     * Formats the current content of the {@linkplain #groups} list as a HTML page in the given file.
     *
     * @param  destination The file to generate.
     * @return The given {@code destination} file.
     */
    @Override
    public File write(File destination) throws IOException {
        Collections.sort(groups);
        destination = toFile(destination);
        filter("OperationParameters.html", destination);
        return destination;
    }

    /**
     * Invoked by {@link Report} every time a {@code ${FOO}} occurrence is found.
     * If the given key is one of those that are managed by this {@code OperationParametersReport}
     * class, then this method will dispatch to the appropriate {@code writeFoo} method.
     */
    @Override
    final void writeContent(final BufferedWriter out, final String key) throws IOException {
        if ("CONTENT".equals(key)) {
            indentation = 6;
            writeCategories(out);
            writeTable(out, getColumnHeaders());
        } else {
            super.writeContent(out, key);
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
    private void writeCategories(final BufferedWriter out) throws IOException {
        String previous = null;
        for (final Group group : groups) {
            final String category = group.category;
            if (category != null && !category.equals(previous)) {
                if (previous == null) {
                    writeIndentation(out, indentation); out.write("<p>Content:</p>");
                    writeIndentation(out, indentation); out.write("<ul>");
                    out.newLine();
                    indentation += INDENT;
                }
                writeIndentation(out, indentation);
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
            writeIndentation(out, indentation);
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
    private void writeTable(final BufferedWriter out, final Map<String,String> columnHeaders) throws IOException {
        writeIndentation(out, indentation);
        out.write("<table cellspacing=\"0\" cellpadding=\"0\">");
        out.newLine();
        indentation += INDENT;
        String previous = null;
        boolean writeHeader = true;
        final String[] codeSpaces = columnHeaders.keySet().toArray(new String[columnHeaders.size()]);
        final String columnSpan = String.valueOf(codeSpaces.length);
        for (final Group group : groups) {
            final String category = group.category;
            /*
             * If begining a new section in the table, print the category
             * in bold characters. The column headers will be printed below.
             */
            if (category != null && !category.equals(previous)) {
                writeIndentation(out, indentation);
                out.write("<tr class=\"sectionHead\"><th colspan=\"");
                out.write(columnSpan);         out.write("\"><a name=\"");
                out.write(toAnchor(category)); out.write("\">");
                out.write(category);           out.write("</a></th></tr>");
                out.newLine();
                writeHeader = true;
                previous = category;
            }
            /*
             * If printing the first row, or if the above block printed a new category, print
             * the column headers. Otherwise we will just insert a horizontal separator.
             */
            if (writeHeader) {
                writeIndentation(out, indentation);
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
            final List<GeneralParameterDescriptor> parameters = group.parameters;
            final int size = parameters.size();
            writeRow(out, group.operation, codeSpaces, true, false, false);
            for (int i=0; i<size; i++) {
                writeRow(out, parameters.get(i), codeSpaces, false, (i == 0), (i == size-1));
            }
            writeHeader = false;
        }
        indentation -= INDENT;
        writeIndentation(out, indentation);
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
    private void writeRow(final BufferedWriter out, final IdentifiedObject object, final String[] codeSpaces,
            final boolean isGroup, final boolean isHead, final boolean isTail) throws IOException
    {
        @SuppressWarnings({"unchecked","rawtypes"})
        final Map<String,Boolean>[] nameSets = new Map[codeSpaces.length];
        for (int i=0; i<codeSpaces.length; i++) {
            nameSets[i] = IdentifiedObjects.getNameAndAliases(object, codeSpaces[i]);
        }
        writeIndentation(out, indentation);
        out.write("<tr");
        writeClassAttribute(out,
                isGroup  ? "groupName" : null,
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
