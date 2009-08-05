/*$************************************************************************************************
 **
 ** $Id$
 **
 ** $URL$
 **
 ** Copyright (C) 2009 Open GIS Consortium, Inc. All Rights Reserved. http://www.opengis.org/Legal/
 **
 *************************************************************************************************/
package org.opengis.tools.taglet;

import java.io.*;
import java.util.*;
import com.sun.javadoc.Doc;
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
 * The remainding is the explanation as HTML text (no javadoc tags).
 *
 * @author Martin Desruisseaux (Geomatys)
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
        c.put("integration",    "Departures for closer integration with Java environment");
        c.put("harmonization",  "Departures for harmonization of different specifications");
        c.put("historic",       "Departures due to historical raisons");
        c.put("rename",         "Renaming with no change in functionality");
        c.put("generalization", "Generalizations (OGC/ISO restrictions relaxed)");
        c.put("extension",      "Extensions (elements not in OGC/ISO specifications)");
        CATEGORIES = c;
    }

    /**
     * All departures declared in javadoc tags. The keys are the category, and the value
     * is a list of departure for that category.
     */
    private final Map<String,List<Element>> departures = new HashMap<String,List<Element>>();

    /**
     * An element in the {@link #departures} list.
     */
    private static final class Element implements Comparable<Element> {
        /** The source file.            */ final File    file;
        /** "Method", "Field", etc.     */ final String  type;
        /** The class or method name.   */ final String  name;
        /** The departure text.         */ final String  text;
        /** true if field or method.    */ final boolean member;

        /** Creates an element for the given tag. */
        Element(final Tag tag, final String text) {
            final Doc holder = tag.holder();
            this.file = tag.position().file();
            this.name = holder.name();
            this.text = text;
            if      (holder.isMethod())    {member=true;  type = "Method";}
            else if (holder.isField())     {member=true;  type = "Field";}
            else if (holder.isEnum())      {member=true;  type = "Enum";}
            else if (holder.isInterface()) {member=false; type = "Interface";}
            else if (holder.isClass())     {member=false; type = "Class";}
            else                           {member=false; type = "Package";}
        }

        /**
         * For sorting in the order to be published on the HTML page.
         */
        public int compareTo(final Element other) {
            final String  n1 =  this.file.getName();
            final String  n2 = other.file.getName();
            final boolean p1 = n1.startsWith("package");
            final boolean p2 = n2.startsWith("package");
            if (p1 != p2) {
                return p1 ? -1 : +1; // Put packages first.
            }
            return n1.compareTo(n2);
        }
    }

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
            List<Element> forCategory = departures.get(category);
            if (forCategory == null) {
                forCategory = new ArrayList<Element>();
                departures.put(category, forCategory);
            }
            forCategory.add(new Element(tag, text));
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
        out.write("    <TITLE>Departures from OGC/ISO specifications</TITLE>"); out.newLine();
        out.write("  </HEAD>"); out.newLine();
        out.write("  <BODY>"); out.newLine();
        out.write("  <H1>Departures from OGC/ISO specifications</H1>"); out.newLine();
        out.write("  <P>The following sections list all the departures from the ISO " +
                  "standards taken by the GeoAPI interface library. The rational for " +
                  "departures fall in the following categories:</P>"); out.newLine();
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
                final List<Element> elements = departures.get(category);
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
                for (final Element element : elements) {
                    // Gets the filename without its path or extension.
                    final File file = element.file;
                    String typename = file.getName();
                    int s = typename.lastIndexOf('.');
                    if (s > 0) {
                        typename = typename.substring(0, s);
                    }
                    String filename = typename;
                    if (typename.equals("package-info")) {
                        typename = element.name;
                        filename = "package-summary";
                    }
                    // Gets the path relative to the javadoc root. We assume the standard
                    // Maven directory layout, with source code under the "java" directory.
                    String path = file.getParent().replace(File.separatorChar, '/');
                    s = path.lastIndexOf("/java/");
                    if (s >= 0) {
                        path = path.substring(s + 6); // 6 is the length of "/java/".
                    }
                    path = path + '/' + filename + ".html";
                    if (file.equals(lastFile)) {
                        // New method or field for the same interface than the
                        // previous method or field. Just insert a new line,
                        // do not repeat the interface name.
                        out.newLine();
                    } else {
                        // New class or interface. Formats its name in a header.
                        if (isBlockquote) {
                            out.write("  </BLOCKQUOTE>");
                            out.newLine();
                            isBlockquote = false;
                        }
                        lastFile = file;
                        out.newLine();
                        out.write("  <H3>");
                        // If we don't know the real type, assume interface.
                        out.write(element.member ? "Interface" : element.type);
                        out.write(' ');
                        out.write("<A HREF=\"");
                        out.write(path);
                        out.write("\">");
                        out.write(typename);
                        out.write("</A></H3>");
                        out.newLine();
                    }
                    if (element.member) {
                        // Formats the method or field name. This will add one indentation
                        // level if the text were not already indented.
                        if (!isBlockquote) {
                            out.write("  <BLOCKQUOTE>");
                            out.newLine();
                            isBlockquote = true;
                        }
                        out.write("    <H4>");
                        out.write(element.type);
                        out.write(' ');
                        out.write(element.name);
                        out.write("</H4>");
                        out.newLine();
                    }
                    // Formats the text, for either interface, class, method or field.
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
