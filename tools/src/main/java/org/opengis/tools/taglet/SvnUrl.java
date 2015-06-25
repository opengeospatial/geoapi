/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    http://www.geoapi.org
 *
 *    Copyright (C) 2009-2015 Open Geospatial Consortium, Inc.
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
 * The <code>@svnurl</code> tag for inserting a SVN URL in a javadoc comment. This tag shall
 * contains a keyword followed by the path, for example <code>{@svnurl gigs/GIGS_2006_libProjectedCRS.csv}</code>.
 * Valid keywords are:
 *
 * <table class="ogc">
 *   <tr><th>Keyword</th> <th>path</th></tr>
 *   <tr><td>gigs</td>   <td>geoapi-conformance/src/main/resources/org/opengis/test/referencing/gigs</td></tr>
 *   <tr><td>netcdf</td> <td>geoapi-netcdf/src/test/resources/org/opengis/wrapper/netcdf</td></tr>
 * </table>
 *
 * @author  Martin Desruisseaux (Geomatys)
 * @version 3.1
 * @since   3.1
 */
public final class SvnUrl extends AbstractTaglet {
    /**
     * Keywords (at even index) and the corresponding path (at odd index).
     */
    private static final String[] KEYWORDS = {
        "gigs/",   "geoapi-conformance/src/main/resources/org/opengis/test/referencing/gigs/",
        "netcdf/", "geoapi-netcdf/src/test/resources/org/opengis/wrapper/netcdf/"
    };

    /**
     * Register this taglet.
     *
     * @param tagletMap the map to register this tag to.
     */
    public static void register(final Map<String,Taglet> tagletMap) {
       final SvnUrl tag = new SvnUrl();
       tagletMap.put(tag.getName(), tag);
    }

    /**
     * Constructs a default <code>@svnurl</code> taglet.
     */
    private SvnUrl() {
        super();
    }

    /**
     * Returns the name of this custom tag.
     *
     * @return The tag name.
     */
    @Override
    public String getName() {
        return "svnurl";
    }

    /**
     * Returns {@code true} since <code>@svnurl</code> is an inline tag.
     *
     * @return Always {@code true}.
     */
    @Override
    public boolean isInlineTag() {
        return true;
    }

    /**
     * Given the <code>Tag</code> representation of this custom tag, return its string representation.
     *
     * @param tag The tag to format.
     * @return A string representation of the given tag.
     */
    @Override
    public String toString(final Tag tag) {
        final String url = tag.text();
        for (int i=0; i<KEYWORDS.length; i += 2) {
            final String keyword = KEYWORDS[i];
            if (url.startsWith(keyword)) {
                return new StringBuilder(160)
                        .append("<a href=\"http://svn.code.sf.net/p/geoapi/code/trunk/")
                        .append(KEYWORDS[i+1]).append(url, keyword.length(), url.length())
                        .append("\"><code>").append(url, url.lastIndexOf('/') + 1, url.length())
                        .append("</code></a>").toString();
            }
        }
        printWarning(tag.position(), "Unknown URL: " + url);
        return "<code>" + url + "</code>";
    }

    /**
     * Given an array of {@code Tag}s representing this custom tag, return its string
     * representation. This method should not be called since arrays of inline tags do
     * not exist. However we define it as a matter of principle.
     *
     * @param tags The tags to format.
     * @return A string representation of the given tags.
     */
    @Override
    public String toString(final Tag[] tags) {
        final StringBuilder buffer = new StringBuilder();
        for (int i=0; i<tags.length; i++) {
            buffer.append(toString(tags[i]));
        }
        return buffer.toString();
    }
}
