/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    Copyright © 2009-2021 Open Geospatial Consortium, Inc.
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
