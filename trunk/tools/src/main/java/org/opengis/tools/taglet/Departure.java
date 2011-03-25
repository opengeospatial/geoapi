/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    http://www.geoapi.org
 *
 *    Copyright (C) 2009-2011 Open Geospatial Consortium, Inc.
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
package org.opengis.tools.taglet;

import java.io.*;
import java.util.*;
import com.sun.javadoc.Tag;
import com.sun.tools.doclets.Taglet;
import com.sun.tools.doclets.formats.html.ConfigurationImpl;


/**
 * The <code>@departure</code> tag for documenting the raisons for a departure from OGC/ISO
 * standard. The first word after the tag must be the departure category, one of:
 * <p>
 * <ul>
 *   <li>{@code constraint} - Some departure are required because of Java language constraint.
 *        The most common case is the mapping of the {@code union} construct, which exists in
 *        C/C++ but not in Java.</li>
 *   <li>{@code integration} - the departure exists for better integration with standard Java
 *        library.</li>
 *   <li>{@code generalization} - the departure exists in order to generalize an aspect of
 *       the specification.</li>
 *   <li>{@code historic} - the departure exists for historical version (e.g. for compatibility
 *       with previous GeoAPI version).</li>
 *   <li>{@code rename} - the departure is a method renaming for better compliance with Java usage,
 *        without any change in the method purpose, arguments and return type.</li>
 * </ul>
 * <p>
 * The remaining is the explanation as HTML text (no javadoc tags).
 *
 * @author  Martin Desruisseaux (Geomatys)
 * @version 3.0
 * @since   2.3
 */
public final class Departure implements Taglet, Runnable {
    /**
     * The allowed departure categories. Keys are the departure keyword, and values are descriptions
     * of that departure category. The order of elements is the order of sections to be produced by
     * {@link #summary}.
     */
    private static final Map<String,String> CATEGORIES;
    static {
        final Map<String,String> c = new LinkedHashMap<String,String>();
        c.put("constraint",     "Departures due to constraints of the Java language");
        c.put("historic",       "Departures due to historical reasons");
        c.put("harmonization",  "Departures for harmonization between the different specifications");
        c.put("integration",    "Departures for closer integration with the Java environment");
        c.put("rename",         "Changes of name without change in functionality");
        c.put("generalization", "Generalizations due to relaxation of ISO/OGC restrictions");
        c.put("extension",      "Addition of elements not in the ISO/OGC specifications");
        c.put("easeOfUse",      "Extensions for convenience, without introduction of new functionality");
        CATEGORIES = c;
    }

    /**
     * All departures declared in javadoc tags. The keys are the category, and the value
     * is a list of departure for that category.
     */
    private final Map<String,List<DepartureElement>> departures = new HashMap<String,List<DepartureElement>>();

    /**
     * Register this taglet.
     *
     * @param tagletMap the map to register this tag to.
     */
    public static void register(final Map<String,Taglet> tagletMap) {
       final Departure tag = new Departure();
       tagletMap.put(tag.getName(), tag);
    }

    /**
     * Constructs a default <code>@departure</code> taglet.
     */
    private Departure() {
        super();
        Runtime.getRuntime().addShutdownHook(new Thread(this));
    }

    /**
     * Run the {@link #summary} method after the javadoc creation has been completed.
     * We run this task as a JVM shutdown hook (which is far from ideal) because I'm
     * not aware of any standard way to register an action to be executed at the end
     * of javadoc generation.
     */
    public void run() {
        try {
            summary();
        } catch (IOException e) {
            // The stack trace is not of interrest since we are doing only trivial call
            // to out.write("..."). The most likely cause is no write permission.
            System.err.println("ERROR while writing the departures from OGC/ISO pages:");
            System.err.println(e);
        }
    }

    /**
     * Returns the name of this custom tag.
     *
     * @return The tag name.
     */
    public String getName() {
        return "departure";
    }

    /**
     * Returns {@code false} since <code>@departure</code> can not be used in overview.
     *
     * @return Always {@code false}.
     */
    public boolean inOverview() {
        return false;
    }

    /**
     * Returns {@code true} since <code>@departure</code> can be used in package documentation.
     *
     * @return Always {@code true}.
     */
    public boolean inPackage() {
        return true;
    }

    /**
     * Returns {@code true} since <code>@departure</code> can be used in type documentation
     * (classes or interfaces).
     *
     * @return Always {@code true}.
     */
    public boolean inType() {
        return true;
    }

    /**
     * Returns {@code true} since <code>@departure</code> can be used in constructor
     *
     * @return Always {@code true}.
     */
    public boolean inConstructor() {
        return true;
    }

    /**
     * Returns {@code true} since <code>@departure</code> can be used in method documentation.
     *
     * @return Always {@code true}.
     */
    public boolean inMethod() {
        return true;
    }

    /**
     * Returns {@code true} since <code>@departure</code> can be used in field documentation.
     *
     * @return Always {@code true}.
     */
    public boolean inField() {
        return true;
    }

    /**
     * Returns {@code false} since <code>@departure</code> is not an inline tag.
     *
     * @return Always {@code false}.
     */
    public boolean isInlineTag() {
        return false;
    }

    /**
     * Given an array of {@code Tag}s representing this custom tag, return its string
     * representation.
     *
     * @param tags The tags to format.
     * @return A string representation of the given tags.
     */
    public String toString(final Tag[] tags) {
        final StringBuilder buffer = new StringBuilder();
        for (int i=0; i<tags.length; i++) {
            toString(tags[i], buffer);
        }
        return buffer.toString();
    }

    /**
     * Given the <code>Tag</code> representation of this custom tag, return its string representation.
     *
     * @param tag The tag to format.
     * @return A string representation of the given tag.
     */
    public String toString(final Tag tag) {
        final StringBuilder buffer = new StringBuilder();
        toString(tag, buffer);
        return buffer.toString();
    }

    /**
     * Formats the given tag in the given buffer. This is the implementation of
     * the public <code>toString(Tag...)</code> methods.
     */
    private void toString(final Tag tag, final StringBuilder buffer) {
        String text = tag.text().trim().replace("\r\n", "\n").replace('\r', '\n');
        String category = "<unspecified>";
        /*
         * Extracts the first word, which is expected to be the category name.
         */
        for (int i=0; i<text.length(); i++) {
            if (Character.isWhitespace(text.charAt(i))) {
                category = text.substring(0, i);
                text = text.substring(i).trim();
                break;
            }
        }
        if (!CATEGORIES.containsKey(category)) {
            ConfigurationImpl.getInstance().root.printWarning(tag.position(), "Unknown category: " + category);
        }
        /*
         * Adds the current departure to the collection of departures.
         * They will be processed later by the summary() method.
         */
        synchronized (departures) {
            List<DepartureElement> forCategory = departures.get(category);
            if (forCategory == null) {
                forCategory = new ArrayList<DepartureElement>();
                departures.put(category, forCategory);
            }
            forCategory.add(new DepartureElement(tag, text));
        }
        /*
         * Nows copies the text.
         */
        buffer.append("<blockquote><font color=\"firebrick\" size=-1><b>Departure from OGC/ISO specification:</b><br>").
                append(text).append("</font></blockquote>");
    }

    /**
     * Generates a summary of all departures.
     *
     * @throws IOException If an error occured while writing the summary page.
     */
    private void summary() throws IOException {
        final BufferedWriter out = new BufferedWriter(new FileWriter("departures.html"));
        out.write("<!DOCTYPE HTML PUBLIC -//W3C//DTD HTML 4.01 Transitional//EN>"); out.newLine();
        out.newLine();
        out.write("<HTML>"); out.newLine();
        out.write("  <HEAD>"); out.newLine();
        out.write("    <TITLE>Departures from the ISO/OGC specifications</TITLE>"); out.newLine();
        out.write("  </HEAD>"); out.newLine();
        out.write("  <BODY>"); out.newLine();
        out.write("  <H1>Departures from the ISO/OGC specifications</H1>"); out.newLine();
        out.write("  <P>The following sections list all the departures from the ISO " +
                  "standards taken by the GeoAPI interface library. The rationale for " +
                  "these departures fall into the following categories:</P>"); out.newLine();
        final Set<String> categories = new LinkedHashSet<String>(CATEGORIES.keySet());
        synchronized (departures) {
            categories.addAll(departures.keySet());
            /*
             * Writes the table of content. The result should looks like:
             *
             *    • Constraints of the Java language
             *    • Integration with the Java environment
             *    • Generalization
             *    • Historical raisons
             *    • Class or method renaming
             */
            out.write("  <UL>");
            out.newLine();
            for (final String category : categories) {
                if (!departures.containsKey(category)) {
                    continue;
                }
                String description = CATEGORIES.get(category);
                if (description == null) {
                    description = category;
                }
                out.write("    <LI><A HREF=\"#");
                out.write(category);
                out.write("\">");
                out.write(description);
                out.write("</A></LI>");
                out.newLine();
            }
            out.write("  </UL>");
            out.newLine();
            /*
             * Write all sections. The result should looks like:
             *
             * Constraints of the Java language
             *   Position
             *     ISO 19107 defines Position as a C/C++ union (etc...)
             */
            for (final String category : categories) {
                final List<DepartureElement> elements = departures.get(category);
                if (elements == null) {
                    continue;
                }
                Collections.sort(elements);
                String description = CATEGORIES.get(category);
                if (description == null) {
                    description = category;
                }
                out.newLine(); out.newLine();
                out.write("  <P>&nbsp;</P><HR><P>&nbsp;</P>"); out.newLine();
                out.write("  <H2><A NAME=\""); out.write(category); out.write("\">");
                out.write(description); out.write("</A></H2>"); out.newLine();
                out.write("  <BLOCKQUOTE>"); out.newLine();
                File lastFile = null;
                boolean isBlockquote = false;
                /*
                 * Write the departure of all classes, interfaces, methods and fields for the
                 * current section. The following code really needs to evaluate elements.size()
                 * in the loop, because some elements may be removed ahead of time.
                 */
                for (int i=0; i<elements.size(); i++) {
                    final DepartureElement element = elements.get(i);
                    // Gets the filename without its path or extension.
                    final File file = element.file;
                    if (file.equals(lastFile)) {
                        /*
                         * New method or field for the same interface than the previous method
                         * or field. Just insert a new line, do not repeat the interface name.
                         */
                        out.newLine();
                    } else {
                        /*
                         * New class or interface. Formats its name in a header. If the previous
                         * class or interface had a list of methods or fields, we need to close
                         * the previous blockquote.
                         */
                        if (isBlockquote) {
                            out.write("  </BLOCKQUOTE>");
                            out.newLine();
                            isBlockquote = false;
                        }
                        lastFile = file;
                        out.newLine();
                        out.write("  <H3>");
                        element.writeClassName(out);
                        /*
                         * If this class or interface do not have any member to document for
                         * departure, and if an other class or interface has exactly the same
                         * departure, merge their description in order to avoid repeating the
                         * same text twice.
                         */
                        if (!element.member) {
                            boolean alone = true;
                            for (int j=i+1; j<elements.size(); j++) {
                                if (file.equals(elements.get(j).file)) {
                                    alone = false;
                                    break;
                                }
                            }
                            if (alone) {
                                for (int j=i+1; j<elements.size();) {
                                    final DepartureElement candidate = elements.get(j);
                                    if (!candidate.member && element.text.equals(candidate.text)) {
                                        out.write(",<BR>");
                                        candidate.writeClassName(out);
                                        elements.remove(j);
                                    } else j++;
                                }
                            }
                        }
                        out.write("</H3>");
                        out.newLine();
                    }
                    /*
                     * Formats the method or field name. This will add one indentation
                     * level if the text was not already indented.
                     */
                    if (element.member) {
                        if (!isBlockquote) {
                            out.write("  <BLOCKQUOTE>");
                            out.newLine();
                            isBlockquote = true;
                        }
                        out.write("    <H4>");
                        element.writeFieldName(out);
                        /*
                         * If there is more elements in the same file with exactly the same
                         * text, merge them together. This is similar to the merge done above
                         * for classes or interfaces having the same departure, except that
                         * this merge is performed at the field or methods level.
                         */
                        for (int j=i+1; j<elements.size();) {
                            final DepartureElement candidate = elements.get(j);
                            if (file.equals(candidate.file) && element.text.equals(candidate.text)) {
                                out.write(",<BR>");
                                candidate.writeFieldName(out);
                                elements.remove(j);
                            } else j++;
                        }
                        out.write("</H4>");
                        out.newLine();
                    }
                    /*
                     * Formats the text for either interface, class, method or field.
                     */
                    out.write("    <BLOCKQUOTE>");
                    out.write(element.text);
                    out.write("</BLOCKQUOTE>");
                    out.newLine();
                }
                out.write("  </BLOCKQUOTE>");
                if (isBlockquote) {
                    out.write("</BLOCKQUOTE>");
                }
                out.newLine();
            }
        }
        out.write("  </BODY>"); out.newLine();
        out.write("</HTML>"); out.newLine();
        out.close();
    }
}
