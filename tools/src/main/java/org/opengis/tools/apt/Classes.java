/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    http://www.geoapi.org
 *
 *    Copyright (C) 2006-2019 Open Geospatial Consortium, Inc.
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
package org.opengis.tools.apt;


/**
 * A few classes of special interest.
 *
 * @author  Martin Desruisseaux (Geomatys)
 * @version 3.1
 * @since   3.1
 */
enum Classes {
    /**
     * {@code "org.opengis.annotation.UML"}.
     */
    UML(UmlProcessor.UML_CLASSNAME, false),

    /**
     * {@code "org.opengis.util.CodeList"}.
     */
    CODE_LIST("org.opengis.util.CodeList", false),

    /**
     * {@code "org.opengis.parameter.ParameterValueGroup"}.
     */
    PARAMETER_VALUE_GROUP("org.opengis.parameter.ParameterValueGroup", false),

    /**
     * {@code "org.opengis.parameter.ParameterDescriptorGroup"}.
     */
    PARAMETER_DESCRIPTOR_GROUP("org.opengis.parameter.ParameterDescriptorGroup", false),

    /**
     * {@code "org.opengis.geometry.coordinate.PointGrid"}.
     */
    POINT_GRID("org.opengis.geometry.coordinate.PointGrid", true),

    /**
     * {@code "org.opengis.geometry.coordinate.PointArray"}.
     */
    POINT_ARRAY("org.opengis.geometry.coordinate.PointArray", true),

    /**
     * {@code "java.util.Collection"}.
     */
    COLLECTION("java.util.Collection", false),

    /**
     * {@code "java.util.Map"}.
     */
    MAP("java.util.Map", false),

    /**
     * {@code "java.lang.Boolean"}.
     */
    BOOLEAN("java.lang.Boolean", false),

    /**
     * {@code "java.lang.Exception"}.
     */
    EXCEPTION("java.lang.Exception", false);

    /**
     * Returns types for which the name of the getter method should be in plural form.
     */
    static Classes[] MULTI_OCCURRENCE = {
        COLLECTION, MAP, POINT_GRID, POINT_ARRAY, PARAMETER_VALUE_GROUP, PARAMETER_DESCRIPTOR_GROUP
    };

    /**
     * The classname.
     */
    final String classname;

    /**
     * {@code true} for interfaces from the {@code geoapi-pending} module.
     * Those interfaces are optional rather than mandatory.
     */
    final boolean isPending;

    /**
     * Creates a new enumeration for the given class name.
     */
    private Classes(final String classname, final boolean isPending) {
        this.classname = classname;
        this.isPending = isPending;
    }
}
