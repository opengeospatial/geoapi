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

import java.net.URI;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.Date;
import java.util.Locale;
import java.util.Properties;
import java.text.SimpleDateFormat;

import org.opengis.util.InternationalString;
import org.opengis.referencing.ReferenceIdentifier;
import org.opengis.metadata.Identifier;
import org.opengis.metadata.citation.Citation;
import org.opengis.metadata.citation.Contact;
import org.opengis.metadata.citation.OnlineResource;
import org.opengis.metadata.citation.ResponsibleParty;


/**
 * Base class for tools generating reports as HTML pages. The reports are based on HTML templates
 * with a few keywords to be replaced by user-provided values. The keyword values are stored in a
 * {@linkplain #properties} map which can be freely edited by subclasses. The map shall contain
 * the following property values:
 * <p>
 * <table border="1" cellspacing="0">
 *   <tr bgcolor="#CCCCFF"><th>Key</th>  <th>Meaning</th>                                                       <th>Default value</th></tr>
 *   <tr><td>{@code TITLE}</td>          <td>Title of the web page to produce.</td>                             <td>The subclass simple name</td></tr>
 *   <tr><td>{@code DESCRIPTION}</td>    <td>An optional description to add in the HTML report.</td>            <td>Empty string</td></tr>
 *   <tr><td>{@code PRODUCT.NAME}</td>   <td>The name of the product for which a report is generated.</td>      <td>(none)</td></tr>
 *   <tr><td>{@code PRODUCT.VERSION}</td><td>The version of the product for which a report is generated.</td>   <td>Today date in <var>year</var>-<var>month</var>-<var>day</var> format</td></tr>
 *   <tr><td>{@code PRODUCT.URL}</td>    <td>The URL where more information is available about the product.</td><td>(none)</td></tr>
 *   <tr><td>{@code FILENAME}</td>       <td>The output filename (for {@link Reports} only)</td>                <td>(implementation specific)</td>
 *   <tr><td>{@code JAVADOC.GEOAPI}</td> <td>Base URL of GeoAPI javadoc.</td>                                   <td>http://www.geoapi.org/snapshot/javadoc</td>
 * </table>
 * <p>
 * Subclasses can freely add, edit or delete entries in the {@linkplain #properties}
 * map before to invoke the {@link #write(File)} method.
 *
 * @author Martin Desruisseaux (Geomatys)
 * @version 3.1
 *
 * @since 3.1
 */
public abstract class Report {
    /**
     * The encoding of every reports.
     */
    private static final String ENCODING = "UTF-8";

    /**
     * Number of spaces to add when we increase the indentation.
     */
    static final int INDENT = 2;

    /**
     * The timestamp at the time this class has been initialized.
     * Will be used as the default value for {@code PRODUCT.VERSION}.
     */
    private static final String NOW;
    static {
        final SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.CANADA);
        NOW = format.format(new Date());
    }

    /**
     * The values to substitute to keywords in the HTML templates. The class javadoc lists
     * the values expected by all reports. Subclasses can freely add or edit entries in this
     * map before to invoke the {@link #write(File)} method.
     *
     * <p>This properties map may have some {@linkplain Properties#defaults default values}.
     * Those defaults, if any, are sub-class specific.</p>
     */
    protected final Properties properties;

    /**
     * The properties to use as a fallback if a key was not found in the {@link #properties} map.
     * Subclasses shall put the default values here.
     */
    final Properties defaultProperties;

    /**
     * The number of indentation spaces.
     */
    int indentation;

    /**
     * The stream writer where to write the HTML page.
     */
    private BufferedWriter output;

    /**
     * Creates a new report generator using the given property values.
     * See the {@linkplain Report class javadoc} for a list of expected values.
     *
     * @param properties The property values, or {@code null} for the default values.
     */
    protected Report(final Properties properties) {
        defaultProperties = new Properties();
        defaultProperties.setProperty("TITLE", getClass().getSimpleName());
        defaultProperties.setProperty("DESCRIPTION", "");
        defaultProperties.setProperty("PRODUCT.VERSION", NOW);
        defaultProperties.setProperty("JAVADOC.GEOAPI", "http://www.geoapi.org/snapshot/javadoc");
        this.properties = new Properties(defaultProperties);
        if (properties != null) {
            this.properties.putAll(properties);
        }
    }

    /**
     * Returns the locale to use for producing messages in the reports. This is fixed to
     * {@linkplain Locale#ENGLISH English} for now, but may become modifiable in a future
     * version.
     *
     * @return The locale to use for formatting messages.
     *
     * @see InternationalString#toString(Locale)
     */
    public Locale getLocale() {
        return Locale.ENGLISH;
    }

    /**
     * Returns a string value for the given text. If the given text is an instance
     * of {@link InternationalString}, then this method fetches the string for the
     * {@linkplain #locale current locale}.
     */
    final String toString(final CharSequence text) {
        if (text == null) {
            return null;
        }
        if (text instanceof InternationalString) {
            return ((InternationalString) text).toString(getLocale());
        }
        return text.toString();
    }

    /**
     * Infers default values for the "{@code PRODUCT.NAME}", "{@code PRODUCT.VERSION}" and
     * "{@code PRODUCT.URL}" {@linkplain #properties} from the given vendor. The vendor
     * argument is typically the value obtained by a call to the
     * {@linkplain org.opengis.util.Factory#getVendor()} method.
     *
     * @param vendor The vendor, or {@code null}.
     *
     * @see org.opengis.util.Factory#getVendor()
     */
    final void setVendor(final Citation vendor) {
        if (vendor != null) {
            String title = toString(vendor.getTitle());
            /*
            * Search for the version number, with opportunist assignment
            * to the 'title' variable if it was not set by the above line.
            */
            String version = null;
            for (final Identifier identifier : IdentifiedObjects.nullSafe(vendor.getIdentifiers())) {
                if (identifier == null) continue; // Paranoiac safety.
                if (title == null) {
                    title = identifier.getCode();
                }
                if (version == null && (identifier instanceof ReferenceIdentifier)) {
                    version = ((ReferenceIdentifier) identifier).getVersion();
                }
                if (title != null && version != null) {
                    break; // No need to continue.
                }
            }
            /*
            * Search for a URL, with opportunist assignment to the 'title' variable
            * if it was not set by the above lines.
            */
            String linkage = null;
            for (final ResponsibleParty party : vendor.getCitedResponsibleParties()) {
                if (party == null) continue; // Paranoiac safety.
                if (title == null) {
                    title = toString(party.getOrganisationName());
                    if (title == null) {
                        title = party.getIndividualName();
                    }
                }
                final Contact contact = party.getContactInfo();
                if (contact != null) {
                    final OnlineResource resource = contact.getOnlineResource();
                    if (resource != null) {
                        final URI uri = resource.getLinkage();
                        if (uri != null) {
                            linkage = uri.toString();
                            if (title != null) break;
                        }
                    }
                }
            }
            /*
            * If we found at least one property, set all of them together
            * (including null values) for consistency.
            */
            if (title   != null) defaultProperties.setProperty("PRODUCT.NAME",    title);
            if (version != null) defaultProperties.setProperty("PRODUCT.VERSION", version);
            if (linkage != null) defaultProperties.setProperty("PRODUCT.URL",     linkage);
        }
    }

    /**
     * Generates the HTML report in the given file or directory.
     *
     * @param  destination The destination file or directory. If this file already
     *         exists, then its content will be overwritten without warning.
     * @return The file to the HTML page generated by this report. This is usually the given
     *         {@code destination} argument, unless the destination was a directory in which
     *         case this method returns the {@code index.html} file.
     * @throws IOException If an error occurred while writing the report.
     */
    public abstract File write(final File destination) throws IOException;

    /**
     * Copies the given resource to the given file, replacing the {@code ${FOO}} occurrences
     * in the process. For each occurrence of a {@code ${FOO}} keyword, this method invokes
     * the {@link #writeValue(String, BufferedWriter)} method.
     *
     * @param  source      The resource name, without path.
     * @param  destination The destination file. Will be overwritten if already presents.
     * @throws IOException If an error occurred while reading the resource or writing the file.
     */
    final void filter(final String source, final File destination) throws IOException {
        copy("geoapi-reports.css", destination.getParentFile());
        final InputStream in = Report.class.getResourceAsStream(source);
        if (in == null) {
            throw new FileNotFoundException("Resource not found: " + source);
        }
        final BufferedWriter   writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(destination), ENCODING));
        final LineNumberReader reader = new LineNumberReader(new InputStreamReader(in, ENCODING));
        reader.setLineNumber(1);
        output = writer;
        try {
            String line;
            while ((line = reader.readLine()) != null) {
                int endOfPreviousPass = 0;
                for (int i=line.indexOf("${"); i >= 0; i=line.indexOf("${", i)) {
                    writer.write(line, endOfPreviousPass, i - endOfPreviousPass);
                    final int stop = line.indexOf('}', i += 2);
                    if (stop < 0) {
                        throw new IOException("${ without } at line " + reader.getLineNumber() + ":\n" + line);
                    }
                    writeValue(line.substring(i, stop).trim());
                    endOfPreviousPass = stop + 1;
                    i = endOfPreviousPass;
                }
                writer.write(line, endOfPreviousPass, line.length() - endOfPreviousPass);
                writer.newLine();
            }
        } finally {
            output = null;
            writer.close();
            reader.close();
        }
    }

    /**
     * Returns the writer where the HTML page will be written.
     */
    final BufferedWriter getOutput() {
        return output;
    }

    /**
     * Invoked every time a {@code ${FOO}} occurrence is found. The default occurrence get
     * the value from the {@linkplain #properties} map. Subclasses override this method
     * in order to compute the actual content here.
     *
     * @param  key The key to replace by a content.
     * @throws IOException If an error occurred while writing the content.
     */
    void writeValue(final String key) throws IOException {
        final BufferedWriter out = getOutput();
        final String value = properties.getProperty(key);
        if (value == null) {
            throw new IOException("Undefined property: " + key);
        }
        out.write(value);
    }

    /**
     * Writes the indentation spaces on the left margin.
     */
    final void writeIndentation() throws IOException {
        final BufferedWriter out = getOutput();
        for (int i=indentation; --i>=0;) {
            out.write(' ');
        }
    }

    /**
     * Writes the {@code class="..."} attribute values inside a HTML element.
     *
     * @param classes An arbitrary number of classes in the SLD. Length can be 0, 1, 2 or more.
     *        Any null element will be silently ignored.
     */
    final void writeClasses(final String... classes) throws IOException {
        final BufferedWriter out = getOutput();
        boolean hasClasses = false;
        for (final String classe : classes) {
            if (classe != null) {
                out.write(' ');
                if (!hasClasses) {
                    out.write("class=\"");
                    hasClasses = true;
                }
                out.write(classe);
            }
        }
        if (hasClasses) {
            out.write('"');
        }
    }

    /**
     * Copies the the given resource file to the given directory.
     * This method does nothing if the destination file already exits.
     *
     * @param  resource The name of the resource to copy.
     * @param  directory The destination directory.
     * @throws IOException If an error occurred during the copy.
     */
    private static void copy(final String resource, final File directory) throws IOException {
        final File file = new File(directory, resource);
        if (file.isFile() && file.length() != 0) {
            return;
        }
        final InputStream in = Report.class.getResourceAsStream(resource);
        final OutputStream out = new FileOutputStream(file);
        try { // JDK7: Use "try with resource".
            int n;
            final byte[] buffer = new byte[1024];
            while ((n = in.read(buffer)) >= 0) {
                out.write(buffer, 0, n);
            }
        } finally {
            out.close();
            in.close();
        }
    }
}
