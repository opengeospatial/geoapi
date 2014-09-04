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

import java.util.Map;
import com.sun.javadoc.Tag;
import com.sun.tools.doclets.Taglet;


/**
 * The <code>@note</code> tag for inserting a note in a javadoc comment.
 *
 * @author  Martin Desruisseaux (Geomatys)
 * @version 3.0
 * @since   2.0
 */
public final class Note implements Taglet {
    /**
     * Register this taglet.
     *
     * @param tagletMap the map to register this tag to.
     */
    public static void register(final Map<String,Taglet> tagletMap) {
       final Note tag = new Note();
       tagletMap.put(tag.getName(), tag);
    }

    /**
     * Constructs a default <code>@note</code> taglet.
     */
    private Note() {
        super();
    }

    /**
     * Returns the name of this custom tag.
     *
     * @return The tag name.
     */
    public String getName() {
        return "note";
    }

    /**
     * Returns {@code true} since <code>@note</code> can be used in overview.
     *
     * @return Always {@code false}.
     */
    public boolean inOverview() {
        return true;
    }

    /**
     * Returns {@code true} since <code>@note</code> can be used in package documentation.
     *
     * @return Always {@code true}.
     */
    public boolean inPackage() {
        return true;
    }

    /**
     * Returns {@code true} since <code>@note</code> can be used in type documentation
     * (classes or interfaces).
     *
     * @return Always {@code true}.
     */
    public boolean inType() {
        return true;
    }

    /**
     * Returns {@code true} since <code>@note</code> can be used in constructor
     *
     * @return Always {@code true}.
     */
    public boolean inConstructor() {
        return true;
    }

    /**
     * Returns {@code true} since <code>@note</code> can be used in method documentation.
     *
     * @return Always {@code true}.
     */
    public boolean inMethod() {
        return true;
    }

    /**
     * Returns {@code true} since <code>@note</code> can be used in field documentation.
     *
     * @return Always {@code true}.
     */
    public boolean inField() {
        return true;
    }

    /**
     * Returns {@code true} since <code>@note</code> is an inline tag.
     *
     * @return Always {@code true}.
     */
    public boolean isInlineTag() {
        return true;
    }

    /**
     * Given the <code>Tag</code> representation of this custom tag, return its string representation.
     *
     * @param tag The tag to format.
     * @return A string representation of the given tag.
     */
    public String toString(final Tag tag) {
        final StringBuilder buffer = new StringBuilder("<blockquote><font size=-1><b>Note:</b>\n");
        buffer.append(tag.text());
        return buffer.append("</font></blockquote>").toString();
    }

    /**
     * Given an array of {@code Tag}s representing this custom tag, return its string
     * representation. This method should not be called since arrays of inline tags do
     * not exist. However we define it as a matter of principle.
     *
     * @param tags The tags to format.
     * @return A string representation of the given tags.
     */
    public String toString(final Tag[] tags) {
        final StringBuilder buffer = new StringBuilder();
        for (int i=0; i<tags.length; i++) {
            buffer.append(toString(tags[i]));
        }
        return buffer.toString();
    }
}
