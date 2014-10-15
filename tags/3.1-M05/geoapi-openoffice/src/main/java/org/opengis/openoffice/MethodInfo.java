/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    http://www.geoapi.org
 *
 *    This file is hereby placed into the Public Domain.
 *    This means anyone is free to do whatever they wish with this file.
 */
package org.opengis.openoffice;


/**
 * Information about a method to be exported as <A HREF="http://www.openoffice.org">OpenOffice.org</A>
 * add-in.
 *
 * @author  Martin Desruisseaux (IRD)
 * @version 3.1
 * @since   3.1
 */
final class MethodInfo {
    /** The category name. */
    final String category;

    /** The display name. */
    final String display;

    /** A description of the exported method. */
    final String description;

    /** Arguments names (even index) and descriptions (odd index). */
    final String[] arguments;

    /**
     * Constructs method informations.
     *
     * @param category    The category name.
     * @param display     The display name.
     * @param description A description of the exported method.
     * @param arguments   Arguments names (even index) and descriptions (odd index).
     */
    MethodInfo(final String   category,
               final String   display,
               final String   description,
               final String[] arguments)
    {
        this.category    = category;
        this.display     = display;
        this.description = description;
        this.arguments   = arguments;
    }
}
