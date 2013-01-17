/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    http://www.geoapi.org
 *
 *    Copyright (C) 2004-2012 Open Geospatial Consortium, Inc.
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
package org.opengis.tools.version;

import java.io.File;
import java.io.Writer;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.IOException;


/**
 * Reports public and protected API changes between two JAR files.
 * The arguments expected by the main methods are:
 * <p>
 * <table>
 * <tr><th>Name</th>               <th>Meaning</th>                                                    <th>Example</th></tr>
 * <tr><td>{@code oldVersion}</td> <td>Old GeoAPI version number, as declared in Maven artefact.</td>  <td>{@code "3.0.0"}</td></tr>
 * <tr><td>{@code newVersion}</td> <td>Old GeoAPI version number, as declared in Maven artefact.</td>  <td>{@code "3.1-M04"}</td></tr>
 * <tr><td>{@code outputFile}</td> <td>Name of the file to create. This file shall not exist.</td>     <td>{@code "Changes.html"}</td></tr>
 * </table>
 *
 * @author  Martin Desruisseaux (Geomatys)
 * @version 3.1
 * @since   3.1
 */
public final class ChangeReport {
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
        if (false) {
            // For testing purpose only.
            args = new String[] {"3.0.0", "3.1-M04", "change-summary.html"};
        }
        if (args.length != 3) {
            System.err.println("Expected: oldVersion newVersion outputFile");
            return;
        }
        final File outputFile = new File(args[2]);
        if (outputFile.exists()) {
            System.err.println("Output file " + outputFile + " already exists.");
            return;
        }
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
        final Writer out = new BufferedWriter(new FileWriter(outputFile));
        out.write("<!DOCTYPE html>\n"
                + "<html xmlns=\"http://www.w3.org/1999/xhtml\">\n"
                + "  <head>\n"
                + "    <title>Changes between GeoAPI ");
        out.write(oldVersion.toString());
        out.write(" and ");
        out.write(newVersion.toString());
        out.write(    "</title>\n"
                + "    <style type=\"text/css\" media=\"all\">\n"
                + "      @import url(\"../../css/change-summary.css\");\n"
                + "    </style>\n"
                + "  </head>\n"
                + "  <body><div>\n"
                + "    <h1>Changes between GeoAPI ");
        out.write(oldVersion.toString());
        out.write(" and ");
        out.write(newVersion.toString());
        out.write("</h1>\n");
        writeLegend(out);
        write(collectAPIChanges("geoapi"), out, true);
        out.write("    <hr/>\n"
                + "    <h1>Changes in GeoAPI-conformance</h1>\n");
        writeLegend(out);
        write(collectAPIChanges("geoapi-conformance"), out, false);
        out.write("  </div></body>\n"
                + "</html>\n");
        out.close();
    }

    /**
     * Writes the legend to the given stream.
     */
    private void writeLegend(final Writer out) throws IOException {
        out.write("    <p>Legend:</p>\n"
                + "    <ul>\n"
                + "      <li><code><i>Element</i></code> — new Java type, field or method.</li>\n"
                + "      <li><code><del>Element</del></code> — removed Java type, field or method.</li>\n"
                + "      <li><span class=\"change\">deprecated</span> — element to be removed in a future release.\n"
                + "    </ul>\n");
    }

    /**
     * Writes the given set of API elements to the given writer.
     * The given set shall contains only API differences (new or removed elements).
     *
     * @param  elements The elements to write.
     * @param  out Where to write the differences.
     * @param  showIdentifiers {@code true} for writing the "OGC/ISO name" column.
     * @throws IOException If an I/O error occurred.
     */
    private static void write(final JavaElement[] elements, final Writer out,
            final boolean showIdentifiers) throws IOException
    {
        JavaElementKind kind = null;
        out.write("    <table border=\"1\" cellspacing=\"0\">\n"
                + "      <tr>\n");
        if (showIdentifiers) {
            out.write("        <th>OGC/ISO identifier</th>\n");
        }
        out.write("        <th>Encosing type or package</th>\n"
                + "        <th>Change (type or member)</th>\n"
                + "      </tr>\n");
        for (final JavaElement element : elements) {
            if (element.kind != kind) {
                kind = element.kind;
                out.write("      <tr>\n"
                        + "        <th class=\"section\" colspan=\"");
                out.write(Integer.toString(showIdentifiers ? 3 : 2));
                out.write("\">");
                out.write(kind.label);
                out.write("</th>\n");
                out.write("      </tr>\n");
            }
            out.write("      <tr>\n"
                    + "        <td>");
            if (showIdentifiers) {
                if (element.ogcName != null) {
                    out.write("<code>");
                    out.write(element.ogcName);
                    out.write("</code>");
                }
                out.write(        "</td>\n"
                        + "        <td>");
            }
            if (element.parent != null) {
                out.write("<span class=\"note\">");
                writeFullyQualifiedName(element.parent, out);
                out.write("</span>");
            }
            out.write(        "</td>\n"
                    + "        <td>");
            out.write("<code>");
            final JavaElementChanges changes = element.changes();
            if (changes == null) {
                out.write("<i>");
            } else if (changes.isRemoved) {
                out.write("<del>");
            }
            out.write(element.javaName);
            if (changes == null) {
                out.write("</i>");
            } else if (changes.isRemoved) {
                out.write("</del>");
                if (element.isDeprecated) {
                    out.write("  — <span class=\"note\">was deprecated</span>");
                }
            }
            out.write("</code>");
            if (changes != null) {
                changes.write(out);
            }
            out.write(        "</td>\n"
                    + "      </tr>\n");
        }
        out.write("    </table>\n");
    }

    /**
     * Writes the fully-qualified name of the given element to the given writer.
     */
    private static void writeFullyQualifiedName(final JavaElement element, final Writer out) throws IOException {
        if (element.parent != null) {
            writeFullyQualifiedName(element.parent, out);
            out.write('.');
        }
        out.write(element.javaName);
    }
}
