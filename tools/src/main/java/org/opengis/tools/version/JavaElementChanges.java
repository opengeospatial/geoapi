/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    Copyright © 2013-2023 Open Geospatial Consortium, Inc.
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
package org.opengis.tools.version;

import java.io.Writer;
import java.io.IOException;
import java.util.Objects;


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
     *
     * @see #isUmlRemoved()
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
     * If examination of UML identifiers suggests that the UML annotation moved to another member,
     * the member where the annotation moved. Otherwise, or {@code null}.
     *
     * <p>If non null, then {@link #oldName} and {@link #oldObligation} shall be non-null while
     * {@link #newName} and {@link #newObligation} shall be null.</p>
     */
    private String umlMovedTo;

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
     * @param oldElement The old element (cannot be null).
     * @param newElement The new element, or {@code null} if the element has been removed.
     */
    JavaElementChanges(final JavaElement oldElement, final JavaElement newElement) {
        kind      = oldElement.kind;
        isRemoved = (newElement == null);
        if (!isRemoved) {
            if (!Objects.equals(oldElement.ogcName, newElement.ogcName)) {
                oldName = oldElement.ogcName;
                newName = newElement.ogcName;
            }
            if (!Objects.equals(oldElement.type, newElement.type)) {
                oldType = oldElement.type;
                newType = newElement.type;
            }
            if (!Objects.equals(oldElement.obligation, newElement.obligation)) {
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
     * Returns {@code true} if the changes from the old to the new version include
     * the removal of the UML annotation.
     *
     * @see #isRemoved
     */
    final boolean isUmlRemoved() {
        return oldName != null && newName == null && oldObligation != null && newObligation == null;
    }

    /**
     * If the UML annotation has moved to the given element, then take note of this change.
     * This method shall be invoked only on instances for which {@link #isUmlRemoved()} returned {@code true}.
     */
    final void markIfUmlMovedTo(final JavaElement other) {
        if (Objects.equals(oldName, other.ogcName) && Objects.equals(oldObligation, other.obligation)) {
            umlMovedTo = other.getSimpleName();
        }
    }

    /**
     * Writes the changes to the given stream.
     */
    void write(final Writer out) throws IOException {
        String separator = "";
        separator = writeChange(out, separator, "<span class=\"remove\">Deprecated</span>", "Not deprecated anymore", isDeprecated);
        if (umlMovedTo != null) {
            out.write(separator);
            out.write("UML annotation moved to <code>");
            out.write(umlMovedTo);
            out.write("</code>");
            separator = SEPARATOR;
        } else {
            separator = writeChange(out, separator, "OGC/ISO identifier ", oldName, newName);
            separator = writeChange(out, separator, "Obligation ", oldObligation, newObligation);
        }
        separator = writeChange(out, separator, getKindOfType(), trimPackage(oldType), trimPackage(newType));
        writeChange(out, separator, "Made public", "Made protected", isPublic);
        out.write('.');
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
