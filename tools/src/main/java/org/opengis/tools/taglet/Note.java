/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    http://www.geoapi.org
 *
 *    Copyright (C) 2009-2021 Open Geospatial Consortium, Inc.
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

import java.util.Set;
import java.util.EnumSet;
import java.util.List;
import javax.lang.model.element.Element;
import jdk.javadoc.doclet.Taglet;
import com.sun.source.doctree.DocTree;
import com.sun.source.doctree.TextTree;
import com.sun.source.doctree.UnknownInlineTagTree;


/**
 * The <code>@note</code> tag for inserting a note in a javadoc comment.
 *
 * @author  Martin Desruisseaux (Geomatys)
 * @version 3.0.2
 * @since   2.0
 */
public final class Note implements Taglet {
    /**
     * Constructs a default <code>@note</code> taglet.
     */
    public Note() {
        super();
    }

    /**
     * Returns the name of this custom tag.
     *
     * @return the tag name.
     */
    @Override
    public String getName() {
        return "note";
    }

    /**
     * Returns the set of locations in which this taglet may be used.
     *
     * @return the set of locations in which this taglet may be used.
     */
    @Override
    public Set<Taglet.Location> getAllowedLocations() {
        return EnumSet.of(Taglet.Location.OVERVIEW,
                          Taglet.Location.PACKAGE,
                          Taglet.Location.TYPE,
                          Taglet.Location.CONSTRUCTOR,
                          Taglet.Location.METHOD,
                          Taglet.Location.FIELD);
    }

    /**
     * Returns {@code true} since <code>@note</code> is an inline tag.
     *
     * @return always {@code true}.
     */
    @Override
    public boolean isInlineTag() {
        return true;
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
        final StringBuilder buffer = new StringBuilder("<blockquote><font size=-1><b>Note:</b>\n");
        for (final DocTree tag : tags) {
            for (final DocTree node : ((UnknownInlineTagTree) tag).getContent()) {
                buffer.append(((TextTree) node).getBody());
            }
        }
        return buffer.append("</font></blockquote>").toString();
    }
}
