/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    http://www.geoapi.org
 *
 *    Copyright (C) 2004-2012 Open Geospatial Consortium, Inc.
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


/**
 * The kind of a Java element (interface, field, method).
 *
 * @author  Martin Desruisseaux (Geomatys)
 * @version 3.1
 * @since   3.1
 */
enum JavaElementKind {
    /**
     * The element is a package.
     */
    PACKAGE("Packages"),

    /**
     * The type is an enumeration.
     */
    ENUM("Enumerations"),

    /**
     * The type is a code list.
     */
    CODE_LIST("Code lists"),

    /**
     * The element is type other than an enumeration or a code list.
     */
    CLASS("Classes"),

    /**
     * The type is a field. This normally occur only in enumeration or code lists.
     */
    FIELD("Fields"),

    /**
     * The type is a constructor.
     */
    CONSTRUCTOR("Constructors"),

    /**
     * The type is a method.
     */
    METHOD("Methods");

    /**
     * The title to display in the HTML page.
     */
    final String label;

    /**
     * Creates a new enum with the given title to display in the HTML page.
     * @param label
     */
    private JavaElementKind(final String label) {
        this.label = label;
    }
}
