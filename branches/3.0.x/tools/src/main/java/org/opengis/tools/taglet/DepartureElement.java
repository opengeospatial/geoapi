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

import java.io.File;
import java.io.Writer;
import java.io.IOException;
import com.sun.javadoc.Doc;
import com.sun.javadoc.Tag;


/**
 * An element in the {@link Departure#departures} list.
 * <p>
 * Note: {@link #compareTo} is inconsistent with {@link #equals}, but the
 * {@link Departure} taglet doesn't use the {@code equals} method anyway.
 *
 * @author  Martin Desruisseaux (Geomatys)
 * @version 3.0
 */
final class DepartureElement implements Comparable<DepartureElement> {
    /**
     * The source file.
     */
    final File file;

    /**
     * The name part of the source file, without path and extension.
     */
    private final String filename;

    /**
     * Path to HTML javadoc.
     */
    private final String pathToHTML;

    /**
     * "Package", "Interface", "Class", "Method", "Field" or "Enum".
     */
    private final String type;

    /**
     * The class or method name.
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
    final boolean member;

    /**
     * Creates an element for the given tag.
     */
    DepartureElement(final Tag tag, final String text) {
        final Doc holder = tag.holder();
        this.file = tag.position().file();
        this.name = holder.name();
        this.text = text;
        if      (holder.isMethod())       {member=true;  type = "Method";}
        else if (holder.isField())        {member=true;  type = "Field";}
        else if (holder.isEnumConstant()) {member=true;  type = "Enum";}
        else if (holder.isEnum())         {member=false; type = "Enum";}
        else if (holder.isInterface())    {member=false; type = "Interface";}
        else if (holder.isClass())        {member=false; type = "Class";}
        else                              {member=false; type = "Package";}
        /*
         * Get the name of the file, without path or extension. An extension is applied for
         * package-info, where the package name (e.g. "org.opengis.util") is used instead.
         */
        String filename = file.getName();
        int s = filename.lastIndexOf('.');
        if (s > 0) {
            filename = filename.substring(0, s);
        }
        String filenameHTML = filename;
        if (filename.equals("package") || filename.equals("package-info")) {
            filename = name;
            filenameHTML = "package-summary";
        }
        this.filename = filename;
        /*
         * Get the path relative to the javadoc root. We assume the standard
         * Maven directory layout, with source code under the "java" directory.
         */
        String path = file.getParent().replace(File.separatorChar, '/');
        s = path.lastIndexOf("/java/");
        if (s >= 0) {
            path = path.substring(s + 6); // 6 is the length of "/java/".
        }
        path = path + '/' + filenameHTML + ".html";
        pathToHTML = path;
    }

    /**
     * For sorting in the order to be published on the HTML page.
     */
    public int compareTo(final DepartureElement other) {
        final String  n1 =  this.file.getName();
        final String  n2 = other.file.getName();
        final boolean p1 = n1.startsWith("package");
        final boolean p2 = n2.startsWith("package");
        if (p1 != p2) {
            return p1 ? -1 : +1; // Sort packages first.
        }
        if (member != other.member) {
            return member ? -1 : +1; // Sort members last.
        }
        return n1.compareTo(n2);
    }

    /**
     * Writes the a class name to the given writer.
     */
    final void writeClassName(final Writer out) throws IOException {
        // If we don't know the real type, assume interface.
        out.write(member && !type.equals("Enum") ? "Interface" : type);
        out.write(' ');
        out.write("<A HREF=\"");
        out.write(pathToHTML);
        out.write("\">");
        out.write(filename);
        out.write("</A>");
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
