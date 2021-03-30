/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    http://www.geoapi.org
 *
 *    Copyright (C) 2021 Open Geospatial Consortium, Inc.
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
package org.opengis.filter;

import java.util.List;
import java.util.ArrayList;
import org.opengis.util.CodeList;
import org.opengis.annotation.UML;

import static org.opengis.annotation.Obligation.CONDITIONAL;
import static org.opengis.annotation.Specification.ISO_19143;


/**
 * Nature of the spatial operation between two geometries.
 *
 * @author  Chris Dillard (SYS Technologies)
 * @author  Martin Desruisseaux (Geomatys)
 * @version 3.1
 * @since   3.1
 */
@UML(identifier="SpatialOperatorName", specification=ISO_19143)
public final class SpatialOperatorName extends CodeList<SpatialOperatorName> {
    /**
     * Serial number for compatibility with different versions.
     */
    private static final long serialVersionUID = -5467041303033565459L;

    /**
     * List of all enumerations of this type.
     * Must be declared before any enum declaration.
     */
    private static final List<SpatialOperatorName> VALUES = new ArrayList<>(9);

    /**
     * Operator evaluates to {@code true} when the bounding box of the feature's geometry
     * interacts with the bounding box provided in the filter properties.
     * The geometry is given by the first expression and the bounding box
     * is the envelope given by the second expression.
     *
     * <p>The {@code BBOX} operation is defined as a convenient and more compact way of
     * expressing the very common bounding box constraint based on the envelope geometry.
     * It is equivalent to the spatial operation <code>NOT {@linkplain #DISJOINT}</code>
     * meaning that the {@code BBOX} operator identifies all geometries that spatially
     * interact with the box.</p>
     *
     * <p>An implementation may choose to throw an exception if one attempts to test
     * features that are in a different SRS than the SRS contained in the filter.</p>
     */
    @UML(identifier="BBOX", obligation=CONDITIONAL, specification=ISO_19143)
    public static final SpatialOperatorName BBOX = new SpatialOperatorName("BBOX");

    /**
     * Operator evaluates to {@code true} if the geometry of the two operands are equal.
     */
    @UML(identifier="Equals", obligation=CONDITIONAL, specification=ISO_19143)
    public static final SpatialOperatorName EQUALS = new SpatialOperatorName("EQUALS");

    /**
     * Operator evaluates to {@code true} if the first operand is disjoint from the second
     * (in the sense defined in the OGC Simple Features specification).
     */
    @UML(identifier="Disjoint", obligation=CONDITIONAL, specification=ISO_19143)
    public static final SpatialOperatorName DISJOINT = new SpatialOperatorName("DISJOINT");

    /**
     * Operator evaluates to {@code true} if the two geometric operands intersect.
     */
    @UML(identifier="Intersects", obligation=CONDITIONAL, specification=ISO_19143)
    public static final SpatialOperatorName INTERSECTS = new SpatialOperatorName("INTERSECTS");

    /**
     * Operator evaluates to {@code true} if the feature's geometry touches,
     * but does not overlap with the geometry held by this object.
     */
    @UML(identifier="Touches", obligation=CONDITIONAL, specification=ISO_19143)
    public static final SpatialOperatorName TOUCHES = new SpatialOperatorName("TOUCHES");

    /**
     * Operator evaluates to {@code true} if the first geometric operand crosses
     * the second (in the sense defined by the OGC Simple Features specification).
     */
    @UML(identifier="Crosses", obligation=CONDITIONAL, specification=ISO_19143)
    public static final SpatialOperatorName CROSSES = new SpatialOperatorName("CROSSES");

    /**
     * Operator evaluates to {@code true} if the feature's geometry is completely
     * contained by the constant geometry held by this object.
     */
    @UML(identifier="Within", obligation=CONDITIONAL, specification=ISO_19143)
    public static final SpatialOperatorName WITHIN = new SpatialOperatorName("WITHIN");

    /**
     * Operator evaluates to {@code true} if the first geometric operand contains the second.
     */
    @UML(identifier="Contains", obligation=CONDITIONAL, specification=ISO_19143)
    public static final SpatialOperatorName CONTAINS = new SpatialOperatorName("CONTAINS");

    /**
     * Operator evaluates to {@code true} if the interior of the first geometry
     * somewhere overlaps the interior of the second geometry.
     */
    @UML(identifier="Overlaps", obligation=CONDITIONAL, specification=ISO_19143)
    public static final SpatialOperatorName OVERLAPS = new SpatialOperatorName("OVERLAPS");

    /**
     * Constructs an element of the given name. The new element is
     * automatically added to the list returned by {@link #values()}.
     *
     * @param name  the name of the new element. This name shall not be in use by another element of this type.
     */
    private SpatialOperatorName(final String name) {
        super(name, VALUES);
    }

    /**
     * Returns the list of {@code SpatialOperatorName}s.
     *
     * @return the list of codes declared in the current JVM.
     */
    public static SpatialOperatorName[] values() {
        synchronized (VALUES) {
            return VALUES.toArray(new SpatialOperatorName[VALUES.size()]);
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
    public SpatialOperatorName[] family() {
        return values();
    }

    /**
     * Returns the spatial operator that matches the given string, or returns a new one if none match it.
     * More specifically, this methods returns the first instance for which
     * <code>{@linkplain #name() name()}.{@linkplain String#equals equals}(code)</code> returns {@code true}.
     * If no existing instance is found, then a new one is created for the given name.
     *
     * @param  code  the name of the code to fetch or to create.
     * @return a code matching the given name.
     */
    public static SpatialOperatorName valueOf(String code) {
        return valueOf(SpatialOperatorName.class, code);
    }
}
