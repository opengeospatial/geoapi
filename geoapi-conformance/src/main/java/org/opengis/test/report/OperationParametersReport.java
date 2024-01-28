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
package org.opengis.test.report;

import java.io.File;
import java.io.IOException;
import java.io.BufferedWriter;
import java.util.Set;
import java.util.Map;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.LinkedHashMap;
import java.util.Properties;

import org.opengis.util.GenericName;
import org.opengis.metadata.Identifier;
import org.opengis.parameter.GeneralParameterDescriptor;
import org.opengis.parameter.ParameterDescriptorGroup;
import org.opengis.referencing.IdentifiedObject;
import org.opengis.referencing.operation.SingleOperation;
import org.opengis.referencing.operation.OperationMethod;
import org.opengis.referencing.operation.MathTransformFactory;


/**
 * Generates a list of operations (typically map projections) and their parameters.
 * The operations are described by instances of an {@link IdentifiedObject} subtype,
 * for example coordinates {@link OperationMethod}. Each operation can be associated
 * to a {@link ParameterDescriptorGroup} instance. Those elements can be
 * {@linkplain #add(IdentifiedObject, ParameterDescriptorGroup) added individually}
 * in the {@linkplain #rows} list. Alternatively, a convenience method can be used
 * for adding all operation methods available from a given {@link MathTransformFactory}.
 *
 * <p>This class recognizes the following property values:</p>
 *
 * <table class="ogc">
 *   <caption>Report properties</caption>
 *   <tr><th>Key</th>                    <th>Remarks</th>   <th>Meaning</th></tr>
 *   <tr><td>{@code TITLE}</td>          <td></td>          <td>Title of the web page to produce.</td></tr>
 *   <tr><td>{@code DESCRIPTION}</td>    <td>optional</td>  <td>Description to write after the introductory paragraph.</td></tr>
 *   <tr><td>{@code OBJECTS.KIND}</td>   <td></td>          <td>Kind of objects listed in the page (e.g. <q>Operation Methods</q>).</td></tr>
 *   <tr><td>{@code PRODUCT.NAME}</td>   <td></td>          <td>Name of the product for which the report is generated.</td></tr>
 *   <tr><td>{@code PRODUCT.VERSION}</td><td></td>          <td>Version of the product for which the report is generated.</td></tr>
 *   <tr><td>{@code PRODUCT.URL}</td>    <td></td>          <td>URL where more information is available about the product.</td></tr>
 *   <tr><td>{@code JAVADOC.GEOAPI}</td> <td>predefined</td><td>Base URL of GeoAPI javadoc.</td></tr>
 *   <tr><td>{@code FILENAME}</td>       <td>predefined</td><td>Name of the file to create if the {@link #write(File)} argument is a directory.</td></tr>
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
     * A single row in the table produced by {@link OperationParametersReport}.
     * Instances of this class are created by the {@link OperationParametersReport#createRow
     * OperationParametersReport.createRow(…)} method. Subclasses of {@code OperationParametersReport}
     * can override that methods in order to modify the content of a row.
     *
     * <p>Every {@link String} fields in this class can contain HTML elements, especially the
     * {@linkplain #names} values. If some text is expected to print {@code <} or {@code >}
     * characters, then those characters need to be escaped to their HTML entities.</p>
     *
     * @author Martin Desruisseaux (Geomatys)
     * @version 3.1
     *
     * @see OperationParametersReport#createRow(IdentifiedObject, ParameterDescriptorGroup, Set)
     *
     * @since 3.1
     */
    protected static class Row implements Comparable<Row> {
        /**
         * An optional user category, or {@code null} if none. If non-null, this category will be
         * formatted as a single row in the HTML table before all subsequent {@code Row} instances
         * of the same category.
         *
         * <p>The default value is {@code null} in every cases. Subclasses of {@link OperationParametersReport}
         * can modify this value in order to classify operations by category. For example, subclasses
         * may use this value for classifying {@link OperationMethod} instances according the kind
         * of map projection (<cite>planar</cite>, <cite>cylindrical</cite>, <cite>conic</cite>).</p>
         */
        public String category;

        /**
         * The {@link IdentifiedObject} name, used only for {@link #compareTo(Row)} implementation.
         * This field is not used for defining the row content.
         */
        private final Identifier name;

        /**
         * The names or aliases to write on the table row. Each entry will be formatted in a
         * single table cell. The column of the cell is determined by the key, and the content
         * is determined by the value. More specifically:
         *
         * <ul>
         *   <li>{@linkplain Map#keySet() Map keys} are the {@linkplain Identifier#getCodeSpace()
         *   code spaces} or {@linkplain GenericName#scope() scopes} of the name or aliases.</li>
         *
         *   <li>{@linkplain Map#values() Map values} are the {@linkplain Identifier#getCode()
         *   codes} or {@linkplain GenericName#toInternationalString() string representations} of the name
         *   or aliases.</li>
         * </ul>
         *
         * <p>The values may contain HTML elements. In particular:</p>
         * <ul>
         *   <li>{@code <em>…</em>} for {@linkplain IdentifiedObject#getName() primary names}.</li>
         *   <li>{@code <del>…</del>} for deprecated objects (need to be added by the user).</li>
         * </ul>
         */
        public final Map<String,String[]> names;

        /**
         * The operation parameters or the parameter sub-groups, or {@code null} if not applicable.
         * If this row describes an operation, then the content of this list is derived from the
         * values returned by {@link ParameterDescriptorGroup#descriptors()}. If this row describes
         * a parameter, then this list will contain the sub-groups (if any).
         *
         * <p><b>Note:</b> subgroups are not yet supported.</p>
         */
        public List<Row> parameters;

        /**
         * Creates a row to be show on the HTML page.
         *
         * @param  object      the operation or parameter to show on the HTML page.
         * @param  codeSpaces  the code spaces for which to get the name and aliases.
         */
        public Row(final IdentifiedObject object, final Set<String> codeSpaces) {
            name  = object.getName();
            names = new LinkedHashMap<>();
            for (final String cs : codeSpaces) {
                final Map<String,Boolean> toCopy = IdentifiedObjects.getNameAndAliases(object, cs);
                final int size = toCopy.size();
                if (size != 0) {
                    int i=0;
                    final String[] array = new String[size];
                    for (final Map.Entry<String,Boolean> entry : toCopy.entrySet()) {
                        String name = escape(entry.getKey());
                        if (entry.getValue()) {
                            name = "<em>" + name + "</em>";
                        }
                        array[i++] = name;
                    }
                    if (names.put(cs, array) != null) {
                        throw new AssertionError(cs);                       // Should never happen.
                    }
                }
            }
        }

        /**
         * Creates a new row initialized to a shallow copy of the given row.
         * The {@link Map} and {@link List} collections are copied, but the
         * content of those collections are not cloned.
         *
         * @param toCopy  the row to copy.
         */
        public Row(final Row toCopy) {
            category = toCopy.category;
            name     = toCopy.name;
            names    = new LinkedHashMap<>(toCopy.names);
            if (toCopy.parameters != null) {
                parameters = new ArrayList<>(toCopy.parameters);
            }
        }

        /**
         * Compares this row with the given object for order. This method is used for sorting
         * the operations in the order to be show on the HTML output page.
         *
         * <p>The default implementation compare that {@linkplain #category} first - this is
         * needed in order to ensure that operations of the same category are grouped. Then,
         * this method compares {@linkplain IdentifiedObject#getName() object names} components
         * in the following order: {@linkplain Identifier#getCode() code},
         * {@linkplain Identifier#getCodeSpace() code space} and
         * {@linkplain Identifier#getVersion() version}.</p>
         *
         * <p>Subclasses can override this method if they want a different ordering
         * on the HTML page.</p>
         *
         * @param  o  the other row to compare with this row.
         * @return -1 if {@code this} should appears before {@code o}, -1 for the converse,
         *         or 0 if this method cannot determine an ordering for the given object.
         */
        @Override
        public int compareTo(final Row o) {
            int c = IdentifiedObjects.compare(category, o.category);
            if (c == 0) {
                c = IdentifiedObjects.compare(name, o.name);
            }
            return c;
        }

        /**
         * Returns a string representation of this row, for debugging purpose only.
         *
         * @return an arbitrary string representation of this row.
         */
        @Override
        public String toString() {
            final StringBuilder buffer = new StringBuilder(64);
            try {
                write(buffer, names.keySet().toArray(String[]::new), false, false, false);
            } catch (IOException e) {
                throw new AssertionError(e);                                // Should never happen.
            }
            return buffer.toString();
        }

        /**
        * Writes a single row with the names of the given objects.
        *
        * @param  out         where to write the content.
        * @param  codeSpaces  the code spaces to use in columns, typically {@link #getCodeSpaces()}.
        * @param  isGroup     {@code true} if formatting a group, or {@code false} for a parameter.
        * @param  isHead      {@code true} if formatting the first group of parameter values in a section.
        * @param  isTail      {@code true} if formatting the last parameter value in a group.
        * @throws IOException if an error occurred while writing the content.
        */
        final void write(final Appendable out, final String[] codeSpaces,
                final boolean isGroup, final boolean isHead, final boolean isTail) throws IOException
        {
            out.append("<tr");
            writeClassAttribute(out,
                    isGroup  ? "groupName" : null,
                    isHead   ? "groupHead" : null,
                    isTail   ? "groupTail" : null);
            out.append('>');
            for (int i=0; i<codeSpaces.length;) {
                final String cs = codeSpaces[i];
                final String[] codes = names.get(cs);
                /*
                 * If the next columns are empty, allow the current column to use their space.
                 * This allow a more compact table since EPSG names may be quite long, and in
                 * many cases have no corresponding names in other code spaces.
                 */
                int colspan = 1;
                while (++i < codeSpaces.length) {
                    if (names.get(codeSpaces[i]) != null) {
                        break;
                    }
                    colspan++;
                }
                out.append("<td");
                if (colspan != 1) {
                    out.append(" colspan=\"");
                    out.append(Integer.toString(colspan));
                    out.append('"');
                }
                out.append('>');
                /*
                 * Write the parameter name. Typically there is only one name, since we are
                 * formatting the names for only one code space. However in some few cases,
                 * we still have many names declared by the same authority. The other names
                 * are typically legacy names. In such case, we will put each additional
                 * name on its own line in the same cell.
                 */
                boolean hasMore = false;
                if (codes != null) {
                    // Intentionally no enclosing <ul>.
                    if (!isGroup) out.append("<li>");
                    for (final String name : codes) {
                        if (hasMore) out.append("<br>");
                        out.append(name);
                        hasMore = true;
                    }
                    if (!isGroup) out.append("</li>");
                }
                out.append("</td>");
            }
            out.append("</tr>");
        }
    }

    /**
     * The operations to publish in the HTML report.
     *
     * @see #add(IdentifiedObject, ParameterDescriptorGroup)
     * @see #add(MathTransformFactory)
     */
    protected final List<Row> rows;

    /**
     * The number of indentation spaces.
     */
    private int indentation;

    /**
     * Creates a new report generator using the given property values.
     * See the class javadoc for a list of expected values.
     *
     * @param properties  the property values, or {@code null} for the default values.
     */
    public OperationParametersReport(final Properties properties) {
        super(properties);
        rows = new ArrayList<>();
        defaultProperties.setProperty("TITLE", "Supported ${OBJECTS.KIND}");
    }

    /**
     * Adds an operation to be show on the HTML page. The default implementation performs the
     * following steps:
     *
     * <ul>
     *   <li>Get the set of all code spaces or scopes found in the given {@code operation}.</li>
     *   <li>Delegates to {@link #createRow createRow(…)} with the above set. This means that
     *       any parameter names defined in another scope will be ignored.</li>
     *   <li>Add the new row to the {@linkplain #rows} list if non-null.</li>
     * </ul>
     *
     * @param  operation   the operation to show on the HTML page.
     * @param  parameters  the operation parameters, or {@code null} if none.
     */
    public void add(final IdentifiedObject operation, final ParameterDescriptorGroup parameters) {
        final Map<String, Boolean> codeSpaces = new LinkedHashMap<>(8);
        IdentifiedObjects.getCodeSpaces(operation, codeSpaces);
        final Row group = createRow(operation, parameters, codeSpaces.keySet());
        if (group != null) {
            rows.add(group);
        }
    }

    /**
     * Convenience method adding all {@linkplain MathTransformFactory#getAvailableMethods(Class)
     * available methods} from the given factory. Each {@linkplain OperationMethod coordinate
     * operation method} is added to the {@linkplain #rows} list as below:
     *
     * <blockquote><code>{@linkplain #add(IdentifiedObject, ParameterDescriptorGroup)
     * add}(method, method.{@linkplain OperationMethod#getParameters() getParameters()});</code></blockquote>
     *
     * @param  factory  the factory for which to add available methods.
     */
    public void add(final MathTransformFactory factory) {
        defaultProperties.setProperty("OBJECTS.KIND", "Coordinate Operations");
        defaultProperties.setProperty("FILENAME", "CoordinateOperations.html");
        setVendor("PRODUCT", factory.getVendor());
        final Set<OperationMethod> operations = factory.getAvailableMethods(SingleOperation.class);
        for (final OperationMethod operation : operations) {
            add(operation, operation.getParameters());
        }
    }

    /**
     * Creates a new row for the given operation and parameters. This method is invoked by the
     * {@link #add(IdentifiedObject, ParameterDescriptorGroup) add(…)} method when a new row
     * needs to be created, either for an operation or for one of its parameters.
     *
     * <p>The default implementation instantiate a new {@link Row} with the given operation and
     * code spaces. Then, if the given {@code parameters} argument is non-null, this method
     * iterates over all parameter descriptor and invokes this method recursively for creating
     * their rows.</p>
     *
     * @param  operation   the operation.
     * @param  parameters  the operation parameters, or {@code null} if none.
     * @param  codeSpaces  the code spaces for which to get the name and aliases.
     * @return the new row, or {@code null} if none.
     */
    protected Row createRow(final IdentifiedObject operation, final ParameterDescriptorGroup parameters, final Set<String> codeSpaces) {
        final Row row = new Row(operation, codeSpaces);
        if (parameters != null) {
            final List<GeneralParameterDescriptor> descriptors = parameters.descriptors();
            for (final GeneralParameterDescriptor desc : descriptors) {
                final Row child = createRow(desc, (desc instanceof ParameterDescriptorGroup) ?
                        (ParameterDescriptorGroup) desc : null, codeSpaces);
                if (child != null) {
                    if (row.parameters == null) {
                        row.parameters = new ArrayList<>(descriptors.size());
                    }
                    row.parameters.add(child);
                }
            }
        }
        return row;
    }

    /**
     * Returns the HTML text to use as a column header for each
     * {@linkplain Identifier#getCodeSpace() code spaces} or
     * {@linkplain GenericName#scope() scopes}. The columns will be shown in iteration order.
     *
     * @return the name of all code spaces or scopes. Some typical values are {@code "EPSG"},
     *         {@code "OGC"}, {@code "ESRI"}, {@code "GeoTIFF"} or {@code "NetCDF"}.
     */
    private String[] getColumnHeaders() {
        final Set<String> codeSpaces = new LinkedHashSet<>(8);
        for (final Row row : rows) {
            codeSpaces.addAll(row.names.keySet());
        }
        return codeSpaces.toArray(String[]::new);
    }

    /**
     * {@return a HTML anchor for the given category}.
     *
     * @param  category  the category for which to get an HTML anchor.
     */
    private String toAnchor(final String category) {
        return category.toLowerCase(getLocale()).replace(' ', '-');
    }

    /**
     * Formats the current content of the {@linkplain #rows} list as a HTML page in the given file.
     *
     * @param  destination  the file to generate.
     * @return the given {@code destination} file.
     * @throws IOException if an error occurred while writing the HTML page.
     */
    @Override
    public File write(File destination) throws IOException {
        Collections.sort(rows);
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
            writeTable(out);
        } else {
            super.writeContent(out, key);
        }
    }

    /**
     * Writes the list of content before the table of operations. This list is created
     * only if {@link #getCategory(IdentifiedObject)} returned a non-null value for at
     * least one operation.
     *
     * @param  out  where to write the content.
     * @throws IOException if an error occurred while writing the content.
     */
    private void writeCategories(final BufferedWriter out) throws IOException {
        String previous = null;
        for (final Row row : rows) {
            final String category = row.category;
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
     * @param  out  where to write the content.
     * @throws IOException if an error occurred while writing the content.
     */
    private void writeTable(final BufferedWriter out) throws IOException {
        writeIndentation(out, indentation);
        out.write("<table cellspacing=\"0\" cellpadding=\"0\">");
        out.newLine();
        indentation += INDENT;
        String previous = null;
        boolean writeHeader = true;
        final String[] codeSpaces = getColumnHeaders();
        final String columnSpan = String.valueOf(codeSpaces.length);
        for (final Row row : rows) {
            final String category = row.category;
            /*
             * If beginning a new section in the table, print the category
             * in bold characters. The column headers will be printed below.
             */
            if (category != null && !category.equals(previous)) {
                writeIndentation(out, indentation);
                out.write("<tr class=\"sectionHead\"><th colspan=\"");
                out.write(columnSpan);         out.write("\" id name=\"");
                out.write(toAnchor(category)); out.write("\">");
                out.write(category);           out.write("</th></tr>");
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
                for (final String cs : codeSpaces) {
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
            writeIndentation(out, indentation);
            row.write(out, codeSpaces, true, false, false);
            out.newLine();
            final List<Row> parameters = row.parameters;
            if (parameters != null) {
                indentation += INDENT;
                final int size = parameters.size();
                for (int i=0; i<size; i++) {
                    writeIndentation(out, indentation);
                    parameters.get(i).write(out, codeSpaces, false, (i == 0), (i == size-1));
                    out.newLine();
                }
                indentation -= INDENT;
            }
            writeHeader = false;
        }
        indentation -= INDENT;
        writeIndentation(out, indentation);
        out.write("</table>");
    }
}
