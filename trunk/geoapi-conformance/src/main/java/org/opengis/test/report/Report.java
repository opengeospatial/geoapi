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
import java.util.NoSuchElementException;
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
 * with a few keywords to be replaced by user-provided values. The values can be specified in two
 * ways:
 * <p>
 * <ul>
 *   <li>Specified at {@linkplain #Report(Properties) construction time}.</li>
 *   <li>Stored directly in the {@linkplain #properties} map by subclasses.</li>
 * </ul>
 *
 * <p>Some keywords common to most subclasses are:</p>
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
 * <p>The set of expected entries, and whatever a user-provided value for a given keyword is
 * mandatory or optional, is subclass-specific.</p>
 *
 * <p><b>How to use this class:</b></p>
 * <ul>
 *   <li>Create a {@link Properties} map with the values documented in the subclass to be
 *       instantiated. Default values exist for many keys, but those defaults may depend
 *       on the environment (information found in {@code META-INF/MANIFEST.MF}, <i>etc</i>).
 *       It is safer to specify values explicitly when they are known.</li>
 *   <li>Create a new instance of the {@code Report} subclass with the above properties map
 *       given to the constructor.</li>
 *   <li>All {@code Report} subclasses define at least one {@code add(…)} method for declaring
 *       the objects to include in the HTML page. At least one object or factory needs to be
 *       declared.</li>
 *   <li>Invoke {@link #write(File)}.</li>
 * </ul>
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
     * The prefix before key names in HTML templates.
     */
    private static final String KEY_PREFIX = "${";

    /**
     * The suffix after key names in HTML templates.
     */
    private static final char KEY_SUFFIX = '}';

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
     * The values to substitute to keywords in the HTML templates. This map is initialized to a
     * copy of the map given by the user at {@linkplain #Report(Properties) construction time},
     * or to an empty map if the user gave a {@code null} map. Subclasses can freely add, edit
     * or remove entries in this map.
     * <p>
     * The list of expected entries and their {@linkplain Properties#defaults default values}
     * (if any) are subclass-specific. See the subclass javadoc for a list of expected values.
     */
    protected final Properties properties;

    /**
     * The properties to use as a fallback if a key was not found in the {@link #properties} map.
     * Subclasses defined in the {@code org.opengis.test.report} package shall put their default
     * values here. Note that the default values are highly implementation-specific and may change
     * in any future version. We don't document them (for now) for this reason.
     */
    final Properties defaultProperties;

    /**
     * Creates a new report generator using the given property values. The list of expected
     * entries is subclass specific and shall be documented in their javadoc.
     *
     * @param properties The property values, or {@code null} for the default values.
     */
    protected Report(final Properties properties) {
        defaultProperties = new Properties();
        defaultProperties.setProperty("TITLE", getClass().getSimpleName());
        defaultProperties.setProperty("DESCRIPTION", "");
        defaultProperties.setProperty("PRODUCT.VERSION", NOW);
        defaultProperties.setProperty("FACTORY.VERSION", NOW);
        defaultProperties.setProperty("JAVADOC.GEOAPI", "http://www.geoapi.org/snapshot/javadoc");
        this.properties = new Properties(defaultProperties);
        if (properties != null) {
            this.properties.putAll(properties);
        }
    }

    /**
     * Infers default values for the "{@code PRODUCT.NAME}", "{@code PRODUCT.VERSION}" and
     * "{@code PRODUCT.URL}" {@linkplain #properties} from the given vendor. The vendor
     * argument is typically the value obtained by a call to the
     * {@linkplain org.opengis.util.Factory#getVendor()} method.
     *
     * @param prefix The property key prefix (usually {@code "PRODUCT"},
     *               but may also be {@code "FACTORY"}).
     * @param vendor The vendor, or {@code null}.
     *
     * @see org.opengis.util.Factory#getVendor()
     */
    final void setVendor(final String prefix, final Citation vendor) {
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
            if (title   != null) defaultProperties.setProperty(prefix + ".NAME",    title);
            if (version != null) defaultProperties.setProperty(prefix + ".VERSION", version);
            if (linkage != null) defaultProperties.setProperty(prefix + ".URL",     linkage);
        }
    }

    /**
     * Returns the value associated to the given key in the {@linkplain #properties} map.
     * If the value for the given key contains other keys, then this method will resolve
     * those values recursively.
     *
     * @param  key The property key for which to get the value.
     * @return The value for the given key.
     * @throws NoSuchElementException If no value has been found for the given key.
     */
    final String getProperty(final String key) throws NoSuchElementException {
        final StringBuilder buffer = new StringBuilder();
        try {
            writeProperty(buffer, key);
        } catch (IOException e) {
            // Should never happen, since we are appending to a StringBuilder.
            throw new AssertionError(e);
        }
        return buffer.toString();
    }

    /**
     * Returns the locale to use for producing messages in the reports. The locale may be
     * used for fetching the character sequences from {@link InternationalString} objects,
     * for converting to lower-cases or for formatting numbers.
     *
     * <p>The locale is fixed to {@linkplain Locale#ENGLISH English} for now, but may become
     * modifiable in a future version.</p>
     *
     * @return The locale to use for formatting messages.
     *
     * @see InternationalString#toString(Locale)
     * @see String#toLowerCase(Locale)
     * @see java.text.NumberFormat#getNumberInstance(Locale)
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
     * Ensures that the given {@link File} object denotes a file (not a directory).
     * If the given argument is a director, then the {@code "FILENAME"} property
     * value will be added.
     */
    final File toFile(File destination) {
        if (destination.isDirectory()) {
            destination = new File(destination, properties.getProperty("FILENAME", getClass().getSimpleName() + ".html"));
        }
        return destination;
    }

    /**
     * Generates the HTML report in the given file or directory. If the given argument
     * is a directory, then the path will be completed with the {@code "FILENAME"}
     * {@linkplain #properties} value if any, or an implementation specific default
     * filename otherwise.
     *
     * <p>Note that the target directory must exist; this method does not create any new
     * directory.</p>
     *
     * @param  destination The destination file or directory. If this file already
     *         exists, then its content will be overwritten without warning.
     * @return The file to the HTML page generated by this report. This is usually the given
     *         {@code destination} argument, unless the destination was a directory.
     * @throws IOException If an error occurred while writing the report.
     */
    public abstract File write(final File destination) throws IOException;

    /**
     * Copies the the given resource file to the given directory.
     * This method does nothing if the destination file already exits.
     *
     * @param  source The name of the resource to copy.
     * @param  directory The destination directory.
     * @throws IOException If an error occurred during the copy.
     */
    private static void copy(final String source, final File directory) throws IOException {
        final File file = new File(directory, source);
        if (file.isFile() && file.length() != 0) {
            return;
        }
        final InputStream in = Report.class.getResourceAsStream(source);
        if (in == null) {
            throw new FileNotFoundException("Resource not found: " + source);
        }
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

    /**
     * Copies the given resource to the given file, replacing the {@code ${FOO}} occurrences
     * in the process. For each occurrence of a {@code ${FOO}} keyword, this method invokes
     * the {@link #writeContent(BufferedWriter, String)} method.
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
        try {
            String line;
            while ((line = reader.readLine()) != null) {
                if (!writeLine(writer, line)) {
                    throw new IOException(KEY_PREFIX + " without " + KEY_SUFFIX +
                            " at line " + reader.getLineNumber() + ":\n" + line);
                }
                writer.newLine();
            }
        } finally {
            writer.close();
            reader.close();
        }
    }

    /**
     * Writes the given line, replacing replacing the {@code ${FOO}} occurrences in the process.
     * For each occurrence of a {@code ${FOO}} keyword, this method invokes the
     * {@link #writeContent(BufferedWriter, String)} method.
     *
     * <p>This method does not invokes {@link BufferedWriter#newLine()} after the line.
     * This is caller responsibility to invoke {@code newLine()} is desired.</p>
     *
     * @param  out    The output writer.
     * @param  line   The line to write.
     * @return {@code true} on success, or {@code false} on malformed {@code ${FOO}} key.
     * @throws IOException If an error occurred while writing to the file.
     */
    private boolean writeLine(final Appendable out, final String line) throws IOException {
        int endOfPreviousPass = 0;
        for (int i=line.indexOf(KEY_PREFIX); i >= 0; i=line.indexOf(KEY_PREFIX, i)) {
            out.append(line, endOfPreviousPass, i);
            final int stop = line.indexOf(KEY_SUFFIX, i += 2);
            if (stop < 0) {
                return false;
            }
            final String key = line.substring(i, stop).trim();
            if (out instanceof BufferedWriter) {
                writeContent((BufferedWriter) out, key);
            } else {
                writeProperty(out, key);
            }
            endOfPreviousPass = stop + 1;
            i = endOfPreviousPass;
        }
        out.append(line, endOfPreviousPass, line.length());
        return true;
    }

    /**
     * Writes the property value for the given key.
     *
     * @param  out The output writer.
     * @param  key The key to replace by a content.
     * @throws NoSuchElementException If no value has been found for the given key.
     * @throws IOException If an error occurred while writing the content.
     */
    private void writeProperty(final Appendable out, final String key) throws NoSuchElementException, IOException {
        final String value = properties.getProperty(key);
        if (value == null) {
            throw new NoSuchElementException("Undefined property: " + key);
        }
        if (!writeLine(out, value)) {
            throw new IOException(KEY_PREFIX + " without " + KEY_SUFFIX +
                    " for property \"" + key + "\":\n" + value);
        }
    }

    /**
     * Invoked every time a {@code ${FOO}} occurrence is found. The default implementation gets
     * the value from the {@linkplain #properties} map. Subclasses can override this method in
     * order to compute the actual content here.
     *
     * <p>If the value for the given key contains other keys, then this method
     * invokes itself recursively for resolving those values.</p>
     *
     * @param  out The output writer.
     * @param  key The key to replace by a content.
     * @throws NoSuchElementException If no value has been found for the given key.
     * @throws IOException If an error occurred while writing the content.
     */
    void writeContent(final BufferedWriter out, final String key) throws NoSuchElementException, IOException {
        writeProperty(out, key);
    }

    /**
     * Writes the indentation spaces on the left margin.
     */
    static void writeIndentation(final BufferedWriter out, int indentation) throws IOException {
        while (--indentation >= 0) {
            out.write(' ');
        }
    }

    /**
     * Writes the {@code class="…"} attribute values inside a HTML element.
     *
     * @param classes An arbitrary number of classes in the SLD. Length can be 0, 1, 2 or more.
     *        Any null element will be silently ignored.
     */
    static void writeClassAttribute(final BufferedWriter out, final String... classes) throws IOException {
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
}
