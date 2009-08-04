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
 * The remainding is the explanation as HTML text (no javadoc tags).
 *
 * @author Martin Desruisseaux (Geomatys)
 */
public final class Departure implements Taglet {
    /**
     * The allowed departure categories.
     */
    private static final Set<String> CATEGORIES = new HashSet<String>(Arrays.asList(
            "constraint", "integration", "generalization", "historic", "rename"));

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
        if (!CATEGORIES.contains(category)) {
            ConfigurationImpl.getInstance().root.printWarning(tag.position(), "Unknown category: " + category);
        }
        /*
         * Nows copies the text.
         */
        buffer.append("<blockquote><font color=\"firebrick\" size=-1><b>Departure from OGC/ISO specification:</b><br>").
                append(text).append("</font></blockquote>");
    }
}
