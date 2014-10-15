/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    http://www.geoapi.org
 *
 *    Copyright (C) 2004-2014 Open Geospatial Consortium, Inc.
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
package org.opengis.tools.version;

import java.io.Writer;
import java.io.IOException;


/**
 * Changes between two {@link JavaElement} instances.
 *
 * @author  Martin Desruisseaux (Geomatys)
 * @version 3.1
 * @since   3.1
 */
final class JavaElementChanges {
    /**
     * The separator between changes, used only if more than one aspect changed for an element.
     */
    private static final String SEPARATOR = ", ";

    /**
     * {@code true} if we have determined that the API element has been removed.
     * If {@code true}, then all other values are {@code null}.
     */
    final boolean isRemoved;

    /**
     * The kind of element for which we are reporting changes.
     */
    private final JavaElementKind kind;

    /**
     * The old and new OGC/ISO names.
     */
    private String oldName, newName;

    /**
     * The old and new types.
     */
    private String oldType, newType;

    /**
     * The old and new obligations.
     */
    private String oldObligation, newObligation;

    /**
     * {@code TRUE} if the element has been made public, {@code FALSE} if it has been made
     * protected, or {@code null} if the visibility has not been changed.
     */
    private Boolean isPublic;

    /**
     * {@code TRUE} if the element has been deprecated, {@code FALSE} if it is not longer
     * deprecated, or {@code null} if the deprecation has not been changed.
     */
    private Boolean isDeprecated;

    /**
     * Creates a new set of changes between the two given elements.
     *
     * @param oldElement The old element (can not be null).
     * @param newElement The new element, or {@code null} if the element has been removed.
     */
    JavaElementChanges(final JavaElement oldElement, final JavaElement newElement) {
        kind      = oldElement.kind;
        isRemoved = (newElement == null);
        if (!isRemoved) {
            if (!JavaElement.equals(oldElement.ogcName, newElement.ogcName)) {
                oldName = oldElement.ogcName;
                newName = newElement.ogcName;
            }
            if (!JavaElement.equals(oldElement.type, newElement.type)) {
                oldType = oldElement.type;
                newType = newElement.type;
            }
            if (!JavaElement.equals(oldElement.obligation, newElement.obligation)) {
                oldObligation = oldElement.obligation;
                newObligation = newElement.obligation;
            }
            if (oldElement.isPublic != newElement.isPublic) {
                isPublic = newElement.isPublic;
            }
            if (oldElement.isDeprecated != newElement.isDeprecated) {
                isDeprecated = newElement.isDeprecated;
            }
        }
    }

    /**
     * Writes the changes to the given stream.
     */
    void write(final Writer out) throws IOException {
        String separator = "";
        separator = writeChange(out, separator, "OGC/ISO identifier ", oldName, newName);
        separator = writeChange(out, separator, getKindOfType(), trimPackage(oldType), trimPackage(newType));
        separator = writeChange(out, separator, "Obligation ", oldObligation, newObligation);
        separator = writeChange(out, separator, "Made public", "Made protected", isPublic);
        separator = writeChange(out, separator, "<span class=\"remove\">Deprecated</span>", "Not deprecated anymore", isDeprecated);
        if (!SEPARATOR.equals(separator)) {
            out.write("</span>");
        }
    }

    /**
     * Returns the kind elements represented by {@link #oldType} and {@link #newType}.
     */
    private String getKindOfType() {
        return (kind == JavaElementKind.METHOD) ? "Return type " : kind.isMember ? "Type " : "Parent ";
    }

    /**
     * Trims the package prefix in the given name.
     */
    private static String trimPackage(String name) {
        if (name != null) {
            name = name.substring(name.lastIndexOf('.') + 1);
        }
        return name;
    }

    /**
     * Formats the changes from {@code oldValue} to {@code newValue}, if non-null.
     */
    private static String writeChange(final Writer out, String separator, final String label,
            final String oldValue, final String newValue) throws IOException
    {
        if (oldValue != null || newValue != null) {
            out.write(separator);
            out.write(label);
            if (oldValue != null && newValue != null) {
                out.write("changed from “");
                out.write(oldValue);
                out.write("” to “");
                out.write(newValue);
                out.write('”');
            } else if (newValue != null) {
                out.write("set to “");
                out.write(newValue);
                out.write('”');
            } else if (oldValue != null) {
                out.write("“");
                out.write(oldValue);
                out.write("” removed");
            }
            separator = SEPARATOR;
        }
        return separator;
    }

    /**
     * Writes the change in a boolean value, if non-null.
     */
    private static String writeChange(final Writer out, String separator, final String onTrue,
            final String onFalse, final Boolean newValue) throws IOException
    {
        if (newValue != null) {
            out.write(separator);
            out.write(newValue ? onTrue : onFalse);
            separator = SEPARATOR;
        }
        return separator;
    }
}
