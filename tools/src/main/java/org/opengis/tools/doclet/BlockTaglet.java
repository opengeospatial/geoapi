/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    http://www.geoapi.org
 *
 *    Copyright (C) 2009-2018 Open Geospatial Consortium, Inc.
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
package org.opengis.tools.doclet;

import java.util.Set;
import java.util.EnumSet;
import javax.tools.Diagnostic;
import jdk.javadoc.doclet.Taglet;
import jdk.javadoc.doclet.Reporter;
import com.sun.source.doctree.DocTree;
import com.sun.source.doctree.TextTree;
import com.sun.source.doctree.UnknownBlockTagTree;


/**
 * Base class for block (not inline) taglets implemented in this package.
 *
 * @author  Martin Desruisseaux (Geomatys)
 * @version 3.1
 * @since   3.1
 */
abstract class BlockTaglet implements Taglet {
    /**
     * For subclasses constructors.
     */
    BlockTaglet() {
    }

    /**
     * Returns the set of locations in which this taglet may be used.
     * By default the taglet can be used everywhere except overviews,
     * and modules.
     *
     * @return the set of locations in which this taglet may be used.
     */
    @Override
    public Set<Taglet.Location> getAllowedLocations() {
        final EnumSet<Location> locations = EnumSet.allOf(Taglet.Location.class);
        locations.remove(Taglet.Location.OVERVIEW);
        locations.remove(Taglet.Location.MODULE);
        return locations;
    }

    /**
     * Returns {@code false} since this base class is about block (not inline) tags.
     *
     * @return always {@code false}.
     */
    @Override
    public final boolean isInlineTag() {
        return false;
    }

    /**
     * Returns the text contained in the given block tag.
     */
    static String text(final DocTree tag) {
        for (final DocTree node : ((UnknownBlockTagTree) tag).getContent()) {
            if (node.getKind() == DocTree.Kind.TEXT) {
                return ((TextTree) node).getBody().trim();
            }
        }
        return "";
    }

    /**
     * Prints a warning message.
     */
    @SuppressWarnings("UseOfSystemOutOrSystemErr")
    static void printWarning(final DocTree tag, final String message) {
        final Reporter reporter = Doclet.reporter;
        if (reporter != null) {
            reporter.print(Diagnostic.Kind.WARNING, message);
        } else {
            System.err.println(message);
        }
    }
}
