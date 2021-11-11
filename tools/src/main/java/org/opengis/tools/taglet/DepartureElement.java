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

import java.io.Writer;
import java.io.IOException;
import com.sun.source.doctree.DocTree;
import javax.lang.model.element.Name;
import javax.lang.model.element.Element;
import javax.lang.model.element.QualifiedNameable;


/**
 * An element in the {@link Departure#departures} list.
 *
 * <p>Note: {@link #compareTo} is inconsistent with {@link #equals}, but the
 * {@link Departure} taglet doesn't use the {@code equals} method anyway.</p>
 *
 * @author  Martin Desruisseaux (Geomatys)
 * @version 3.0.2
 * @since   2.3
 */
final class DepartureElement implements Comparable<DepartureElement> {
    /**
     * Qualified name of the type, package or module that contains the departure.
     * Used for inferring the source file path relative to the javadoc root directory.
     */
    final Name typeName;

    /**
     * The simple name (without path or extension) of the type that contains the departure.
     */
    private final String shortTypeName;

    /**
     * Path to HTML javadoc.
     */
    private final String pathToHTML;

    /**
     * "Package", "Interface", "Class", "Method", "Field" or "Enum".
     */
    private final String type;

    /**
     * The class or method name that contains the departure.
     * May be the same than {@link #shortTypeName}.
     */
    private final String name;

    /**
     * The departure text.
     */
    final String text;

    /**
     * {@code true} if this element is for a field or method.
     * {@code false} if this element is for an interface, class or package.
     */
    final boolean isMember;

    /**
     * {@code true} if this element is for a package.
     */
    private final boolean isPackage;

    /**
     * Stores a description for the given departure tag.
     */
    DepartureElement(final Element parent, final DocTree tag, final String text) {
        Element typeElement = parent;
        while (!(typeElement instanceof QualifiedNameable)) {
            if (typeElement == null) {
                throw new IllegalArgumentException(parent.getKind() + " without parent TypeElement.");
            }
            typeElement = typeElement.getEnclosingElement();
        }
        this.typeName      = ((QualifiedNameable) typeElement).getQualifiedName();
        this.shortTypeName = typeElement.getSimpleName().toString();
        this.name          = parent.getSimpleName().toString();
        this.text          = text;
        boolean isPackage  = false;
        switch (parent.getKind()) {
            case METHOD:        isMember=true;  type = "Method";    break;
            case FIELD:         isMember=true;  type = "Field";     break;
            case ENUM_CONSTANT: isMember=true;  type = "Enum";      break;
            case ENUM:          isMember=false; type = "Enum";      break;
            case INTERFACE:     isMember=false; type = "Interface"; break;
            case CLASS:         isMember=false; type = "Class";     break;
            case PACKAGE:       isMember=false; type = "Package";   isPackage = true; break;
            default:            isMember=false; type = "Unknown";   break;
        }
        this.isPackage = isPackage;
        /*
         * Get the path relative to the javadoc root.
         */
        final StringBuilder filenameHTML = new StringBuilder(typeName.toString().replace('.', '/'));
        if (isPackage) {
            filenameHTML.append("/package-summary");
        }
        pathToHTML = filenameHTML.append(".html").toString();
    }

    /**
     * For sorting in the order to be published on the HTML page.
     */
    @Override
    public int compareTo(final DepartureElement other) {
        if (isPackage != other.isPackage) {
            return isPackage ? -1 : +1;         // Sort packages first.
        }
        if (isMember != other.isMember) {
            return isMember ? +1 : -1;          // Sort members last.
        }
        return shortTypeName.compareTo(other.shortTypeName);
    }

    /**
     * Writes the a class name to the given writer.
     */
    final void writeClassName(final Writer out) throws IOException {
        // If we don't know the real type, assume interface.
        out.write(isMember && !type.equals("Enum") ? "Interface" : type);
        out.write(' ');
        out.write("<a href=\"");
        out.write(pathToHTML);
        out.write("\">");
        out.write(shortTypeName);
        out.write("</a>");
    }

    /**
     * Writes the a field name to the given writer.
     */
    final void writeFieldName(final Writer out) throws IOException {
        out.write(type);
        out.write(' ');
        out.write(name);
    }
}
