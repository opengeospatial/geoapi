/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    http://www.geoapi.org
 *
 *    Copyright (C) 2004-2019 Open Geospatial Consortium, Inc.
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
package org.opengis.geometry.coordinate;

import java.util.List;
import java.util.ArrayList;

import org.opengis.util.CodeList;
import org.opengis.annotation.UML;

import static org.opengis.annotation.Obligation.*;
import static org.opengis.annotation.Specification.*;


/**
 * The type of a B-spline.
 * A B-spline is uniform if and only if all knots are of {@linkplain Knot#getMultiplicity
 * multiplicity 1} and they differ by a positive constant from the preceding knot. A B-spline
 * is quasi-uniform if and only if the knots are of multiplicity (degree+1) at the ends, of
 * multiplicity 1 elsewhere, and they differ by a positive constant from the preceding knot.
 * This code list is used to describe the distribution of knots in the parameter space of
 * various splines.
 *
 * @version <A HREF="http://www.opengeospatial.org/standards/as">ISO 19107</A>
 * @author Martin Desruisseaux (IRD)
 * @since GeoAPI 2.0
 */
@UML(identifier="GM_KnotType", specification=ISO_19107)
public class KnotType extends CodeList<KnotType> {
    /**
     * Serial number for compatibility with different versions.
     */
    private static final long serialVersionUID = -431722533158166557L;

    /**
     * List of all enumerations of this type.
     * Must be declared before any enum declaration.
     */
    private static final List<KnotType> VALUES = new ArrayList<KnotType>(3);

    /**
     * The form of knots is appropriate for a uniform B-spline.
     */
    @UML(identifier="uniform", obligation=CONDITIONAL, specification=ISO_19107)
    public static final KnotType UNIFORM = new KnotType("UNIFORM");

    /**
     * The form of knots is appropriate for a quasi-uniform B-spline.
     */
    @UML(identifier="quasiUniform", obligation=CONDITIONAL, specification=ISO_19107)
    public static final KnotType QUASI_UNIFORM = new KnotType("QUASI_UNIFORM");

    /**
     * The form of knots is appropriate for a piecewise Bezier curve.
     */
    @UML(identifier="piecewiseBezier", obligation=CONDITIONAL, specification=ISO_19107)
    public static final KnotType PIECEWISE_BEZIER = new KnotType("PIECEWISE_BEZIER");

    /**
     * Constructs an element of the given name. The new element is
     * automatically added to the list returned by {@link #values()}.
     *
     * @param  name  the name of the new element.
     *        This name must not be in use by an other element of this type.
     */
    private KnotType(final String name) {
        super(name, VALUES);
    }

    /**
     * Returns the list of {@code KnotType}s.
     *
     * @return the list of codes declared in the current JVM.
     */
    public static KnotType[] values() {
        synchronized (VALUES) {
            return VALUES.toArray(new KnotType[VALUES.size()]);
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
    public KnotType[] family() {
        return values();
    }

    /**
     * Returns the KnotType that matches the given string, or returns a
     * new one if none match it. More specifically, this methods returns the first instance for
     * which <code>{@linkplain #name() name()}.{@linkplain String#equals equals}(code)</code>
     * returns {@code true}. If no existing instance is found, then a new one is created for
     * the given name.
     *
     * @param  code  the name of the code to fetch or to create.
     * @return a code matching the given name.
     */
    public static KnotType valueOf(String code) {
        return valueOf(KnotType.class, code);
    }
}
