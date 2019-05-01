/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    http://www.geoapi.org
 *
 *    Copyright (C) 2009-2019 Open Geospatial Consortium, Inc.
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
package org.opengis.metadata.acquisition;

import java.util.ArrayList;
import java.util.List;

import org.opengis.annotation.UML;
import org.opengis.util.CodeList;

import static org.opengis.annotation.Obligation.*;
import static org.opengis.annotation.Specification.*;


/**
 * Geometric description of the collection.
 *
 * @author  Cédric Briançon (Geomatys)
 * @version 3.0
 * @since   2.3
 */
@UML(identifier="MI_GeometryTypeCode", specification=ISO_19115_2)
public final class GeometryType extends CodeList<GeometryType> {
    /**
     * Serial number for compatibility with different versions.
     */
    private static final long serialVersionUID = -8326145457020825352L;

    /**
     * List of all enumerations of this type.
     * Must be declared before any enum declaration.
     */
    private static final List<GeometryType> VALUES = new ArrayList<>(4);

    /**
     * Single geographic point of interest.
     */
    @UML(identifier="point", obligation=CONDITIONAL, specification=ISO_19115_2)
    public static final GeometryType POINT = new GeometryType("POINT");

    /**
     * Extended collection in a single vector.
     */
    @UML(identifier="linear", obligation=CONDITIONAL, specification=ISO_19115_2)
    public static final GeometryType LINEAR = new GeometryType("LINEAR");

    /**
     * Collection of a geographic area defined by a polygon (coverage).
     */
    @UML(identifier="areal", obligation=CONDITIONAL, specification=ISO_19115_2)
    public static final GeometryType AREAL = new GeometryType("AREAL");

    /**
     * Series of linear collections grouped by way points.
     */
    @UML(identifier="strip", obligation=CONDITIONAL, specification=ISO_19115_2)
    public static final GeometryType STRIP = new GeometryType("STRIP");

    /**
     * Constructs an element of the given name. The new element is
     * automatically added to the list returned by {@link #values()}.
     *
     * @param name  the name of the new element. This name shall not be in use by another element of this type.
     */
    private GeometryType(final String name) {
        super(name, VALUES);
    }

    /**
     * Returns the list of {@code GeometryType}s.
     *
     * @return the list of codes declared in the current JVM.
     */
    public static GeometryType[] values() {
        synchronized (VALUES) {
            return VALUES.toArray(new GeometryType[VALUES.size()]);
        }
    }

    /**
     * Returns the list of codes of the same kind than this code list element.
     * Invoking this method is equivalent to invoking {@link #values()}, except that
     * this method can be invoked on an instance of the parent {@code CodeList} class.
     *
     * @return all code {@linkplain #values() values} for this code list.
     */
    @Override
    public GeometryType[] family() {
        return values();
    }

    /**
     * Returns the geometry type that matches the given string, or returns a
     * new one if none match it. More specifically, this methods returns the first instance for
     * which <code>{@linkplain #name() name()}.{@linkplain String#equals equals}(code)</code>
     * returns {@code true}. If no existing instance is found, then a new one is created for
     * the given name.
     *
     * @param  code  the name of the code to fetch or to create.
     * @return a code matching the given name.
     */
    public static GeometryType valueOf(String code) {
        return valueOf(GeometryType.class, code);
    }
}
