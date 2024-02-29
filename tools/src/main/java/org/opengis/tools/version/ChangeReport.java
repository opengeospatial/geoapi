/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    Copyright © 2012-2023 Open Geospatial Consortium, Inc.
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
package org.opengis.tools.version;

import java.io.File;
import java.io.Writer;
import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.IOException;


/**
 * Reports public and protected API changes between two JAR files.
 * Instructions about this command can be found in the module javadoc.
 * The arguments expected by the main methods are:
 *
 * <table class="ogc">
 * <caption>Command-line arguments</caption>
 * <tr><th>Name</th> <th>Meaning</th> <th>Example</th></tr>
 * <tr><td>{@code oldVersion}</td> <td>Old GeoAPI version number, as declared in Maven artefact.</td> <td>{@code "3.0.2"}</td></tr>
 * <tr><td>{@code newVersion}</td> <td>New GeoAPI version number, as declared in Maven artefact.</td> <td>{@code "3.1-M07"}</td></tr>
 * <tr><td>{@code outputFile}</td> <td>Name of the file to create.</td> <td>{@code "Changes.html"}</td></tr>
 * </table>
 *
 * @author  Martin Desruisseaux (Geomatys)
 * @version 3.1
 * @since   3.1
 */
public final class ChangeReport {
    /**
     * The encoding to use for the HTML page to write.
     */
    private static final String ENCODING = "UTF-8";

    /**
     * No-breaking space.
     */
    private static final char NOBREAK = '\u00A0';

    /**
     * The old GeoAPI version.
     */
    private final Version oldVersion;

    /**
     * The new GeoAPI version.
     */
    private final Version newVersion;

    /**
     * Generates the API difference between two GeoAPI versions.
     * See class Javadoc for the list of expected arguments.
     *
     * @param  args An array of length 4 containing the two GeoAPI versions to compare.
     * @throws Exception If an I/O or reflection error occurred
     *         (too many checked exceptions for enumerating them all).
     */
    public static void main(String[] args) throws Exception {
        if (args.length != 3) {
            System.err.println("Expected: oldVersion newVersion outputFile");
            return;
        }
        final File outputFile = new File(args[2]);
        new ChangeReport(new Version(args[0]), new Version(args[1])).write(outputFile);
    }

    /**
     * Creates a new object for generating a report of the changes between two GeoAPI versions.
     *
     * @param oldVersion The old GeoAPI version.
     * @param newVersion The new GeoAPI version.
     */
    public ChangeReport(final Version oldVersion, final Version newVersion) {
        this.oldVersion = oldVersion;
        this.newVersion = newVersion;
    }

    /**
     * Collects the API changes for the given artefact.
     * The artefact can be either {@code "geoapi"} or {@code "geoapi-conformance"}.
     *
     * @param artefact The GeoAPI artefact for which to generate a change report
     *                 ({@code "geoapi"} or {@code "geoapi-conformance"}).
     */
    private JavaElement[] collectAPIChanges(final String artefact) throws Exception {
        return JavaElementCollector.collectAPIChanges(artefact, oldVersion, newVersion);
    }

    /**
     * Implementation of the {@link #main(String[])} method after arguments parsing.
     *
     * @param  outputFile The file where to write the report.
     * @throws Exception If an I/O or reflection error occurred
     *         (too many checked exceptions for enumerating them all).
     */
    public void write(final File outputFile) throws Exception {
        final Writer out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(outputFile), ENCODING));
        out.write("<?xml version=\"1.0\" encoding=\"" + ENCODING + "\" ?>\n"
                + "<!DOCTYPE html>\n"
                + "<html xmlns=\"http://www.w3.org/1999/xhtml\" xml:lang=\"en\">\n"
                + "  <head>\n"
                + "    <title>Changes between GeoAPI ");
        out.write(oldVersion.toString());
        out.write(" and ");
        out.write(newVersion.toString());
        out.write(    "</title>\n"
                + "    <meta charset=\"UTF-8\"/>\n"
                + "    <link rel=\"stylesheet\" type=\"text/css\" href=\"../summary.css\"/>\n"
                + "  </head>\n"
                + "  <body><div>\n"
                + "    <h1>GeoAPI changes</h1>\n"
                + "    <p>This page contains two tables:</p>\n"
                + "    <ol>\n"
                + "      <li>Changes in the normative part of GeoAPI</li>\n"
                + "      <li>Changes in the conformance tests</li>\n"
                + "    </ol>\n"
                + "    <h2>Changes between GeoAPI ");
        out.write(oldVersion.toString());
        out.write(" and ");
        out.write(newVersion.toString());
        out.write("</h2>\n"
                + "    <p>The following table summarizes all API changes in the "
                + "<a href=\"http://www.geoapi.org/geoapi/index.html\">normative part</a> of GeoAPI.\n"
                + "    It does not include the pending part, conformance tests and code examples.</p>\n");
        write(out, collectAPIChanges("geoapi"), "geoapi");
        if (Math.min(oldVersion.major, newVersion.major) >= 2) {
            out.write("    <p/>\n"
                    + "    <hr/>\n"
                    + "    <h2>Changes in GeoAPI-conformance</h2>\n"
                    + "    <p>The following table summarizes changes in the "
                    + "<a href=\"http://www.geoapi.org/conformance/index.html\">conformance tests</a> provided by GeoAPI.\n"
                    + "    The <code>geoapi-conformance</code> test suite is not part of standard API that vendors are expected to "
                    + "implement, and will change over time to offer clarification for GeoAPI implementations.<p>\n");
            write(out, collectAPIChanges("geoapi-conformance"), "geoapi-conformance");
        }
        out.write("  </div></body>\n"
                + "</html>\n");
        out.close();
    }

    /**
     * Writes the given set of API elements to the given writer.
     * The given set shall contain only API differences (new or removed elements).
     *
     * @param  elements The elements to write.
     * @param  out      Where to write the differences.
     * @param  artefact The GeoAPI artefact ({@code "geoapi"} or {@code "geoapi-conformance"}).
     * @throws IOException If an I/O error occurred.
     */
    private void write(final Writer out, final JavaElement[] elements, final String artefact) throws IOException {
        final boolean showIdentifiers = !artefact.endsWith("conformance");
        JavaElement container = null;
        out.write("    <table cellspacing=\"0\">\n"
                + "      <tr>\n");
        if (showIdentifiers) {
            out.write("        <th>OGC/ISO identifier</th>\n");
        }
        out.write("        <th>Modified type or member</th>\n"
                + "        <th>Change description</th>\n"
                + "        <th>Javadoc</th>\n"
                + "      </tr>\n");
        for (final JavaElement element : elements) {
            if (!JavaElement.isSameElement(element.container, container)) {
                container = element.container;
                out.write("      <tr>\n"
                        + "        <th class=\"section\"");
                if (showIdentifiers) {
                    out.write("></th><th class=\"section\"");
                }
                out.write(" colspan=\"3\">");
                out.write(container.kind.label);
                out.write(" <code>");
                writeFullyQualifiedName(out, container, '.');
                out.write("</code></th>\n");
                out.write("      </tr>\n");
            }
            out.write("      <tr>\n");
            if (showIdentifiers) {
                out.write("        <td>");
                if (element.ogcName != null) {
                    out.write("<code>");
                    out.write(element.ogcName);
                    out.write("</code>");
                }
                out.write(        "</td>\n");
            }
            out.write("        <td>");
            out.write(element.kind.label);
            out.write(" <code>");
            final JavaElementChanges changes = element.changes();
            final boolean isDeleted = (changes != null) && (changes.isRemoved || element.isDeprecated);
            if (isDeleted) {
                out.write("<del>");
            }
            out.write(element.getSimpleName());
            if (isDeleted) {
                out.write("</del>");
                if (changes.isRemoved && element.isDeprecated) {
                    out.write("  — <span class=\"note\">was deprecated</span>");
                }
            }
            out.write("</code></td>\n"
                    + "        <td>");
            if (changes == null) {
                out.write("<span class=\"add\">New</span>.");
            } else if (changes.isRemoved) {
                out.write("<span class=\"remove\">Removed</span>.");
            } else {
                changes.write(out);
            }
            out.write(        "</td>\n"
                    + "        <td>\n");
            writeLinkToJavadoc(out, artefact, element, false);
            out.write(NOBREAK);
            writeLinkToJavadoc(out, artefact, element, true);
            out.write(        "</td>\n"
                    + "      </tr>\n");
        }
        out.write("    </table>\n");
    }

    /**
     * Writes the fully-qualified name of the given element to the given writer.
     *
     * @param out       Where to write the name.
     * @param element   The element for which to write the name.
     * @param separator The separator character to insert between the names:
     *                  {@code '.'} for package name or {@code '/'} for URL.
     */
    private static void writeFullyQualifiedName(final Writer out, final JavaElement element, final char separator)
            throws IOException
    {
        if (element.container != null) {
            writeFullyQualifiedName(out, element.container, separator);
            out.write(separator);
        }
        String javaName = element.javaName;
        if (JavaElementKind.PACKAGE.equals(element.kind)) {
            javaName = javaName.replace('.', separator);
        }
        out.write(javaName);
    }

    /**
     * Formats the path to the Javadoc URL for the given element.
     *
     * @param out     Where to write the javadoc.
     * @param element The element for which to write the link to the javadoc.
     */
    private static void writePathToJavadoc(final Writer out, final JavaElement element)
            throws IOException
    {
        if (element.kind.isMember) {
            writePathToJavadoc(out, element.container);
            out.write('#');
            out.write(element.javaName);
        } else {
            writeFullyQualifiedName(out, element, '/');
            out.write(".html");
        }
    }

    /**
     * Writes the {@code "<a href>"} HTML element to the Javadoc of the given Java element.
     *
     * @param out      Where to write the Javadoc.
     * @param artefact The GeoAPI artefact ({@code "geoapi"} or {@code "geoapi-conformance"}).
     * @param element  The element for which to write the link to the Javadoc.
     * @param toNew    {@code true} for link to new Javadoc, or {@code false} for link to old Javadoc.
     */
    private void writeLinkToJavadoc(final Writer out, final String artefact,
            final JavaElement element, final boolean toNew) throws IOException
    {
        final Version version = toNew ? newVersion : oldVersion;
        if (version != null) {
            final JavaElementChanges changes = element.changes();
            String javadocURL = null;
            if (toNew ? (changes == null || !changes.isRemoved) : (changes != null)) {
                javadocURL = version.getJavadocURL(artefact);
            }
            if (javadocURL != null) {
                out.write("<a href=\"");
                out.write(javadocURL);
                writePathToJavadoc(out, element);
                out.write("\">");
            } else {
                out.write("<span class=\"disabled\">");
            }
            out.write(toNew ? "new" : "prev");
            out.write((javadocURL != null) ? "</a>" : "</span>");
        }
    }
}
