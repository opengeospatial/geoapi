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
import org.opengis.metadata.Identifier;
import org.opengis.metadata.citation.Citation;
import org.opengis.metadata.citation.Contact;
import org.opengis.metadata.citation.OnlineResource;
import org.opengis.metadata.citation.Party;
import org.opengis.metadata.citation.Responsibility;


/**
 * Base class for tools generating reports as HTML pages. The reports are based on HTML templates
 * with a few keywords to be replaced by user-provided values. The values associated to keywords
 * can be specified in two ways:
 *
 * <ul>
 *   <li>Specified at {@linkplain #Report(Properties) construction time}.</li>
 *   <li>Stored directly in the {@linkplain #properties} map by subclasses.</li>
 * </ul>
 *
 * The set of keywords, and whether a user-provided value for a given keyword is mandatory or optional,
 * is subclass-specific. However, most subclasses expect at least the following keywords:
 *
 * <table class="ogc">
 *   <caption>Report properties</caption>
 *   <tr><th>Key</th>                    <th>Remarks</th>   <th>Meaning</th></tr>
 *   <tr><td>{@code TITLE}</td>          <td></td>          <td>Title of the web page to produce.</td></tr>
 *   <tr><td>{@code DATE}</td>           <td>automatic</td> <td>Date of report creation.</td></tr>
 *   <tr><td>{@code DESCRIPTION}</td>    <td>optional</td>  <td>Description to write after the introductory paragraph.</td></tr>
 *   <tr><td>{@code OBJECTS.KIND}</td>   <td></td>          <td>Kind of objects listed in the page (e.g. <cite>"Operation Methods"</cite>).</td></tr>
 *   <tr><td>{@code PRODUCT.NAME}</td>   <td></td>          <td>Name of the product for which the report is generated.</td></tr>
 *   <tr><td>{@code PRODUCT.VERSION}</td><td></td>          <td>Version of the product for which the report is generated.</td></tr>
 *   <tr><td>{@code PRODUCT.URL}</td>    <td></td>          <td>URL where more information is available about the product.</td></tr>
 *   <tr><td>{@code JAVADOC.GEOAPI}</td> <td>predefined</td><td>Base URL of GeoAPI javadoc.</td></tr>
 *   <tr><td>{@code FILENAME}</td>       <td>predefined</td><td>Name of the file to create if the {@link #write(File)} argument is a directory.</td></tr>
 * </table>
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
     *
     * <p>The list of expected entries and their {@linkplain Properties#defaults default values}
     * (if any) are subclass-specific. See the subclass javadoc for a list of expected values.</p>
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
     * The listener object to inform about the progress, or {@code null} if none.
     */
    ProgressListener listener;

    /**
     * Creates a new report generator using the given property values. The list of expected
     * entries is subclass specific and shall be documented in their javadoc.
     *
     * @param properties  the property values, or {@code null} for the default values.
     */
    protected Report(final Properties properties) {
        defaultProperties = new Properties();
        defaultProperties.setProperty("TITLE", getClass().getSimpleName());
        defaultProperties.setProperty("DATE", NOW);
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
     * Infers default values for the "{@code PRODUCT.NAME}", "{@code PRODUCT.VERSION}" and "{@code PRODUCT.URL}"
     * {@linkplain #properties} from the given vendor. The vendor argument is typically the value obtained by a
     * call to the {@linkplain org.opengis.util.Factory#getVendor()} method.
     *
     * @param prefix  the property key prefix (usually {@code "PRODUCT"}, but may also be {@code "FACTORY"}).
     * @param vendor  the vendor, or {@code null}.
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
                if (identifier == null) continue;               // Paranoiac safety.
                if (title == null) {
                    title = identifier.getCode();
                }
                if (version == null) {
                    version = identifier.getVersion();
                }
                if (title != null && version != null) {
                    break;                                      // No need to continue.
                }
            }
            /*
            * Search for a URL, with opportunist assignment to the 'title' variable
            * if it was not set by the above lines.
            */
            String linkage = null;
search:     for (final Responsibility responsibility : vendor.getCitedResponsibleParties()) {
                if (responsibility == null) continue;                       // Paranoiac safety.
                for (final Party party : responsibility.getParties()) {
                    if (party == null) continue;                            // Paranoiac safety.
                    if (title == null) {
                        title = toString(party.getName());
                    }
                    for (final Contact contact : party.getContactInfo()) {
                        if (contact == null) continue;                      // Paranoiac safety.
                        for (final OnlineResource resource : contact.getOnlineResources()) {
                            if (resource == null) continue;                 // Paranoiac safety.
                            final URI uri = resource.getLinkage();
                            if (uri != null) {
                                linkage = uri.toString();
                                if (title != null) {                        // This is the usual case.
                                    break search;
                                }
                            }
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
     * @param  key  the property key for which to get the value.
     * @return the value for the given key.
     * @throws NoSuchElementException if no value has been found for the given key.
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
     * <p>The locale is fixed to {@linkplain Locale#ENGLISH English} for now, but may become
     * modifiable in a future version.</p>
     *
     * @return the locale to use for formatting messages.
     *
     * @see InternationalString#toString(Locale)
     * @see String#toLowerCase(Locale)
     * @see java.text.NumberFormat#getNumberInstance(Locale)
     */
    public Locale getLocale() {
        return Locale.ENGLISH;
    }

    /**
     * Returns a string value for the given text. If the given text is an instance of {@link InternationalString},
     * then this method fetches the string for the {@linkplain #locale current locale}.
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
     * If the given argument is a directory, then the {@code "FILENAME"} property value will be added.
     */
    final File toFile(File destination) {
        if (destination.isDirectory()) {
            destination = new File(destination, properties.getProperty("FILENAME", getClass().getSimpleName() + ".html"));
        }
        return destination;
    }

    /**
     * Generates the HTML report in the given file or directory.
     * If the given argument is a directory, then the path will be completed with the {@code "FILENAME"}
     * {@linkplain #properties} value if any, or an implementation specific default filename otherwise.
     *
     * <p>Note that the target directory must exist; this method does not create any new directory.</p>
     *
     * @param  destination  the destination file or directory.
     *         If this file already exists, then its content will be overwritten without warning.
     * @return the file to the HTML page generated by this report. This is usually the given
     *         {@code destination} argument, unless the destination was a directory.
     * @throws IOException if an error occurred while writing the report.
     */
    public abstract File write(final File destination) throws IOException;

    /**
     * Copies the given resource file to the given directory.
     * This method does nothing if the destination file already exits.
     *
     * @param  source     the name of the resource to copy.
     * @param  directory  the destination directory.
     * @throws IOException if an error occurred during the copy.
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
        try (OutputStream out = new FileOutputStream(file)) {
            int n;
            final byte[] buffer = new byte[1024];
            while ((n = in.read(buffer)) >= 0) {
                out.write(buffer, 0, n);
            }
        } finally {
            in.close();
        }
    }

    /**
     * Copies the given resource to the given file, replacing the {@code ${FOO}} occurrences in the process.
     * For each occurrence of a {@code ${FOO}} keyword, this method invokes the
     * {@link #writeContent(BufferedWriter, String)} method.
     *
     * @param  source       the resource name, without path.
     * @param  destination  the destination file. Will be overwritten if already presents.
     * @throws IOException if an error occurred while reading the resource or writing the file.
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
     * @param  out   the output writer.
     * @param  line  the line to write.
     * @return {@code true} on success, or {@code false} on malformed {@code ${FOO}} key.
     * @throws IOException if an error occurred while writing to the file.
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
     * @param  out  the output writer.
     * @param  key  the key to replace by a content.
     * @throws NoSuchElementException if no value has been found for the given key.
     * @throws IOException if an error occurred while writing the content.
     */
    private void writeProperty(final Appendable out, final String key) throws NoSuchElementException, IOException {
        final String value = properties.getProperty(key);
        if (value == null) {
            throw new NoSuchElementException("Undefined property: " + key);
        }
        if (!writeLine(out, value)) {
            throw new IOException(KEY_PREFIX + " without " + KEY_SUFFIX + " for property \"" + key + "\":\n" + value);
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
     * @param  out  the output writer.
     * @param  key  the key to replace by a content.
     * @throws NoSuchElementException if no value has been found for the given key.
     * @throws IOException if an error occurred while writing the content.
     */
    void writeContent(final BufferedWriter out, final String key) throws NoSuchElementException, IOException {
        writeProperty(out, key);
    }

    /**
     * Writes the indentation spaces on the left margin.
     */
    static void writeIndentation(final Appendable out, int indentation) throws IOException {
        while (--indentation >= 0) {
            out.append(' ');
        }
    }

    /**
     * Writes the {@code class="…"} attribute values inside a HTML element.
     *
     * @param classes  an arbitrary number of classes in the SLD. Length can be 0, 1, 2 or more.
     *        Any null element will be silently ignored.
     */
    static void writeClassAttribute(final Appendable out, final String... classes) throws IOException {
        boolean hasClasses = false;
        for (final String classe : classes) {
            if (classe != null) {
                out.append(' ');
                if (!hasClasses) {
                    out.append("class=\"");
                    hasClasses = true;
                }
                out.append(classe);
            }
        }
        if (hasClasses) {
            out.append('"');
        }
    }

    /**
     * Escape {@code <} and {@code >} characters for HTML. This method is null-safe.
     * Empty strings are replaced by {@code null} value.
     */
    static String escape(String text) {
        if (text != null) {
            text = text.replace("<", "&lt;").replace(">", "&gt;").trim();
            if (text.isEmpty()) {
                text = null;
            }
        }
        return text;
    }

    /**
     * Invoked when the report is making some progress. This is typically invoked from a
     * {@code add(…)} method, since they are usually slower than {@link #write(File)}.
     * Subclasses can override this method if they want to be notified about progress.
     *
     * @param position  a number ranging from 0 to {@code count}.
     *        This is typically the number or rows created so far for the HTML table to write.
     * @param count  the maximal expected value of {@code position}. Note that this value may change between
     *        different invocations if the report gets a better estimation about the number of rows to be created.
     */
    protected void progress(final int position, final int count) {
        final ProgressListener listener = this.listener;
        if (listener != null) {
            listener.progress(position, count);
        }
    }
}
