/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    Copyright © 2009-2024 Open Geospatial Consortium, Inc.
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
package org.opengis.tools.doclet;

import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.Set;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Collections;
import java.util.function.Consumer;
import java.io.Flushable;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.IOException;
import jdk.javadoc.doclet.Doclet;
import jdk.javadoc.doclet.DocletEnvironment;
import com.sun.source.doctree.DocTree;
import javax.lang.model.element.Name;
import javax.lang.model.element.Element;
import javax.tools.Diagnostic;


/**
 * The <code>@departure</code> tag for documenting the reasons for a departure from OGC/ISO standard.
 * The first word after the tag must be the departure category, one of:
 *
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
 *
 * The remaining is the explanation as HTML text (no javadoc tags).
 *
 * @author  Martin Desruisseaux (Geomatys)
 * @version 3.1
 * @since   2.3
 */
public final class Departure extends BlockTaglet implements Flushable {
    /**
     * The allowed departure categories. Keys are the departure keyword, and values are descriptions
     * of that departure category. The order of elements is the order of sections to be produced by
     * {@link #summary}.
     */
    private static final Map<String,String> CATEGORIES = Map.of(
            "constraint",     "Departure due to constraint of the Java language",
            "historic",       "Departure for historical reason",
            "harmonization",  "Departure for harmonization between different specifications",
            "integration",    "Departure for closer integration with the Java environment",
            "rename",         "Change of name without change in functionality",
            "generalization", "Generalization by relaxation of ISO/OGC restriction",
            "extension",      "Addition of element not in the ISO/OGC specification",
            "easeOfUse",      "Extension for convenience without introduction of new functionality");

    /**
     * All departures declared in javadoc tags. The keys are the category, and the value
     * is a list of departure for that category.
     */
    private final Map<String, List<DepartureElement>> departures = new HashMap<>();

    /**
     * Constructs a <code>@departure</code> taglet.
     */
    public Departure() {
    }

    /**
     * Invoked when the doclet initializes this taglet. This method registers this taglet for execution
     * of the {@link #flush()} method after the doclet finished to generate all the javadoc.
     */
    @Override
    @SuppressWarnings("unchecked")
    public void init(final DocletEnvironment env, final Doclet doclet) {
        super.init(env, doclet);
        try {
            // Cannot access FlushableDoclet.postProcess directly because of different ClassLoaders.
            ((Consumer<Flushable>) doclet).accept(this);
        } catch (ClassCastException e) {
            print(Diagnostic.Kind.ERROR, null, e.toString());
        }
    }

    /**
     * Returns the name of this custom tag.
     *
     * @return the tag name.
     */
    @Override
    public String getName() {
        return "departure";
    }

    /**
     * Given a list of {@code DocTree}s representing this custom tag, returns its string representation.
     *
     * @param  tags     the tags to format.
     * @param  element  the element to which the enclosing comment belongs.
     * @return a string representation of the given tags.
     */
    @Override
    public String toString(final List<? extends DocTree> tags, final Element element) {
        if (tags == null || tags.isEmpty()) {
            return "";
        }
        final StringBuilder buffer = new StringBuilder();
        final String lineSeparator = System.getProperty("line.separator", "\n");
        buffer.append("<dt class=\"departure\">Departure from OGC/ISO abstract specification:</dt>").append(lineSeparator);
        for (final DocTree tag : tags) {
            String text = text(element, tag);
            String category = "";
            /*
             * Extracts the first word, which is expected to be the category name.
             */
            for (int i=0; i<text.length();) {
                final int c = text.codePointAt(i);
                if (Character.isWhitespace(c)) {
                    category = text.substring(0, i);
                    text = text.substring(i).trim();
                    break;
                }
                i += Character.charCount(c);
            }
            String summary = CATEGORIES.get(category);
            if (summary == null) {
                final String message;
                if (category.isEmpty()) {
                    message = "No category has been specified for @departure tag.";
                } else {
                    message = "Unknown @departure category: ".concat(category);
                }
                print(Diagnostic.Kind.WARNING, element, message);
                summary = "Details";
            }
            /*
             * Adds the current departure to the collection of departures.
             * They will be processed later by the summary() method.
             */
            synchronized (departures) {
                departures.computeIfAbsent(category, f -> new ArrayList<>()).add(new DepartureElement(element, tag, text));
            }
            /*
             * Now copies the text.
             */
            buffer.append("<dd class=\"departure\"><details>").append(lineSeparator)
                  .append("<summary>").append(summary).append("</summary>").append(lineSeparator)
                  .append(text).append(lineSeparator).append("</details></dd>");
        }
        return buffer.toString();
    }

    /**
     * Writes to the disk all information collected during the javadoc generation.
     * In the case of this taglet, this method generates a summary of all departures.
     * This method does nothing if there is no reported departures.
     *
     * @throws IOException if an error occurred while writing the summary page.
     */
    @Override
    public void flush() throws IOException {
        synchronized (departures) {
            if (departures.isEmpty()) {
                return;
            }
        }
        try (BufferedWriter out = new BufferedWriter(new FileWriter("departures.html"))) {
            out.write("<!DOCTYPE html>"); out.newLine();
            out.newLine();
            out.write("<html>"); out.newLine();
            out.write("  <head>"); out.newLine();
            out.write("    <title>Departures from the ISO/OGC specifications</title>"); out.newLine();
            out.write("    <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">"); out.newLine();
            out.write("    <link rel=\"stylesheet\" type=\"text/css\" href=\"departure.css\"/>"); out.newLine();
            out.write("  </head>"); out.newLine();
            out.write("  <body>"); out.newLine();
            out.write("  <h1>Departures from the ISO/OGC specifications</h1>"); out.newLine();
            out.write("  <p>The following sections list all the departures from the ISO " +
                      "standards taken by the GeoAPI interface library. The rationale for " +
                      "these departures fall into the following categories:</p>"); out.newLine();
            final Set<String> categories = new LinkedHashSet<>(CATEGORIES.keySet());
            synchronized (departures) {
                categories.addAll(departures.keySet());
                /*
                 * Writes the table of content. The result should looks like:
                 *
                 *    • Constraints of the Java language
                 *    • Integration with the Java environment
                 *    • Generalization
                 *    • Historical reasons
                 *    • Class or method renaming
                 */
                out.write("  <ul>");
                out.newLine();
                for (final String category : categories) {
                    if (!departures.containsKey(category)) {
                        continue;
                    }
                    String description = CATEGORIES.get(category);
                    if (description == null) {
                        description = category;
                    }
                    out.write("    <li><a href=\"#");
                    out.write(category);
                    out.write("\">");
                    out.write(description);
                    out.write("</a></li>");
                    out.newLine();
                }
                out.write("  </ul>");
                out.newLine();
                /*
                 * Write all sections. The result should looks like:
                 *
                 * Constraints of the Java language
                 *   Position
                 *     ISO 19107 defines Position as a C/C++ union (etc...)
                 */
                out.write("  <section class=\"categories\">"); out.newLine();
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
                    out.write("    <h2 id=\""); out.write(category); out.write("\">");
                    out.write(description); out.write("</h2>"); out.newLine();
                    out.write("    <section class=\"types\">"); out.newLine();
                    Name lastName = null;
                    boolean isMemberSectionOpen = false;
                    /*
                     * Write the departure of all classes, interfaces, methods and fields for the
                     * current section. The following code really needs to evaluate elements.size()
                     * in the loop, because some elements may be removed ahead of time.
                     */
                    for (int i=0; i<elements.size(); i++) {
                        final DepartureElement element = elements.get(i);
                        // Gets the fully qualified enclosing type name.
                        final Name name = element.typeName;
                        if (name.equals(lastName)) {
                            /*
                             * New method or field for the same interface as the previous method
                             * or field. Just insert a new line, do not repeat the interface name.
                             */
                            out.newLine();
                        } else {
                            /*
                             * New class or interface. Formats its name in a header. If the previous
                             * class or interface had a list of methods or fields, we need to close
                             * the previous section.
                             */
                            if (isMemberSectionOpen) {
                                out.write("      </section>");
                                out.newLine();
                                isMemberSectionOpen = false;
                            }
                            lastName = name;
                            out.newLine();
                            out.write("      <h3>");
                            element.writeClassName(out);
                            /*
                             * If this class or interface do not have any member to document for
                             * departure, and if another class or interface has exactly the same
                             * departure, merge their description in order to avoid repeating the
                             * same text twice.
                             */
                            if (!element.isMember) {
                                boolean alone = true;
                                for (int j=i+1; j<elements.size(); j++) {
                                    if (name.equals(elements.get(j).typeName)) {
                                        alone = false;
                                        break;
                                    }
                                }
                                if (alone) {
                                    for (int j=i+1; j<elements.size();) {
                                        final DepartureElement candidate = elements.get(j);
                                        if (!candidate.isMember && element.text.equals(candidate.text)) {
                                            out.write(",<br>");
                                            candidate.writeClassName(out);
                                            elements.remove(j);
                                        } else j++;
                                    }
                                }
                            }
                            out.write("</h3>");
                            out.newLine();
                        }
                        /*
                         * Formats the method or field name. This will add one indentation
                         * level if the text was not already indented.
                         */
                        if (element.isMember) {
                            if (!isMemberSectionOpen) {
                                out.write("      <section class=\"members\">");
                                out.newLine();
                                isMemberSectionOpen = true;
                            }
                            out.write("        <h4>");
                            element.writeFieldName(out);
                            /*
                             * If there is more elements in the same file with exactly the same
                             * text, merge them together. This is similar to the merge done above
                             * for classes or interfaces having the same departure, except that
                             * this merge is performed at the field or methods level.
                             */
                            for (int j=i+1; j<elements.size();) {
                                final DepartureElement candidate = elements.get(j);
                                if (name.equals(candidate.typeName) && element.text.equals(candidate.text)) {
                                    out.write(",<br>");
                                    candidate.writeFieldName(out);
                                    elements.remove(j);
                                } else j++;
                            }
                            out.write("</h4>");
                            out.newLine();
                        }
                        /*
                         * Formats the text for either interface, class, method or field.
                         */
                        out.write("        <div class=\"desc\">");
                        out.write(element.text);
                        out.write("</div>");
                        out.newLine();
                    }
                    if (isMemberSectionOpen) {
                        out.write("      </section>");
                        out.newLine();
                    }
                    out.write("    </section>");
                    out.newLine();
                }
                out.write("  </section>");
                out.newLine();
            }
            out.write("  </body>"); out.newLine();
            out.write("</html>"); out.newLine();
        }
    }
}
