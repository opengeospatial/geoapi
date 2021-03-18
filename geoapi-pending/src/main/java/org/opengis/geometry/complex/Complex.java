/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    http://www.geoapi.org
 *
 *    Copyright (C) 2004-2021 Open Geospatial Consortium, Inc.
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
package org.opengis.geometry.complex;

import java.util.Collection;
import org.opengis.geometry.Geometry;
import org.opengis.geometry.primitive.Point;            // For javadoc
import org.opengis.geometry.primitive.Primitive;        // For javadoc
import org.opengis.annotation.UML;

import static org.opengis.annotation.Obligation.*;
import static org.opengis.annotation.Specification.*;


/**
 * A collection of geometrically disjoint, simple {@linkplain Primitive primitives}. If a
 * {@linkplain Primitive primitive} (other than a {@linkplain Point point} is in a particular
 * {@code Complex}, then there exists a set of primitives of lower dimension in the same complex
 * that form the boundary of this primitive.
 * <p>
 * A geometric complex can be thought of as a set in two distinct ways. First, it is a finite set
 * of objects (via delegation to its elements member) and, second, it is an infinite set of point
 * values as a subtype of geometric object. The dual use of delegation and subtyping is to
 * disambiguate the two types of set interface. To determine if a {@linkplain Primitive primitive}
 * <var>P</var> is an element of a {@code Complex} <var>C</var>,
 * call: {@code C.element().contains(P)}.
 * <p>
 * The "{@linkplain #getElements elements}" attribute allows {@code Complex} to inherit the
 * behavior of {@code Set<Primitive>} without confusing the same sort of behavior
 * inherited from {@link org.opengis.geometry.TransfiniteSet TransfiniteSet&lt;DirectPosition&gt;}
 * inherited through {@link Geometry}. Complexes shall be used in application schemas where
 * the sharing of geometry is important, such as in the use of computational topology. In a
 * complex, primitives may be aggregated many-to-many into composites for use as attributes
 * of features.
 *
 * @version <A HREF="http://www.opengeospatial.org/standards/as">ISO 19107</A>
 * @author Martin Desruisseaux (IRD)
 * @since GeoAPI 1.0
 *
 * @todo Some associations are commented out for now.
 */
@UML(identifier="GM_Complex", specification=ISO_19107)
public interface Complex extends Geometry {
    /**
     * Returns {@code true} if and only if this {@code Complex} is maximal.
     * A complex is maximal if it is a subcomplex of no larger complex.
     *
     * @return {@code true} if this complex is maximal.
     */
    @UML(identifier="isMaximal", obligation=MANDATORY, specification=ISO_19107)
    boolean isMaximal();

    /**
     * Returns a superset of primitives that is also a complex.
     *
     * @return the supercomplexes, or an empty array if none.
     *
     * @todo Consider using a Collection return type instead.
     */
    @UML(identifier="superComplex", obligation=MANDATORY, specification=ISO_19107)
    Complex[] getSuperComplexes();

    /**
     * Returns a subset of the primitives of that complex
     * that is, in its own right, a geometric complex.
     *
     * @return the subcomplexes, or an empty array if none.
     *
     * @todo Consider using a Collection return type instead.
     */
    @UML(identifier="subComplex", obligation=MANDATORY, specification=ISO_19107)
    Complex[] getSubComplexes();

    /**
     * Returns the collection of primitives contained in this complex.
     *
     * @return the collection of primitives for this complex.
     */
    @UML(identifier="element", obligation=MANDATORY, specification=ISO_19107)
    Collection<? extends Primitive> getElements();

//    public org.opengis.topology.complex.TP_Complex topology[];
}
