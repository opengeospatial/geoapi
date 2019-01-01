/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    http://www.geoapi.org
 *
 *    Copyright (C) 2005-2019 Open Geospatial Consortium, Inc.
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
 * Indicates which sort of curve may be approximated by a particular B-spline.
 *
 * @version <A HREF="http://www.opengeospatial.org/standards/as">ISO 19107</A>
 * @author Martin Desruisseaux (IRD)
 * @since GeoAPI 2.0
 */
@UML(identifier="GM_SplineCurveForm", specification=ISO_19107)
public final class SplineCurveForm extends CodeList<SplineCurveForm> {
    /**
     * Serial number for compatibility with different versions.
     */
    private static final long serialVersionUID = 7692137703533158212L;

    /**
     * List of all enumerations of this type.
     * Must be declared before any enum declaration.
     */
    private static final List<SplineCurveForm> VALUES = new ArrayList<SplineCurveForm>(5);

    /**
     * A connected sequence of line segments represented by a 1 degree B-spline (a line string).
     */
    @UML(identifier="polylineForm", obligation=CONDITIONAL, specification=ISO_19107)
    public static final SplineCurveForm POLYLINE_FORM = new SplineCurveForm(
                                       "POLYLINE_FORM");

    /**
     * An arc of a circle or a complete circle.
     */
    @UML(identifier="circularArc", obligation=CONDITIONAL, specification=ISO_19107)
    public static final SplineCurveForm CIRCULAR_ARC = new SplineCurveForm(
                                       "CIRCULAR_ARC");

    /**
     * An arc of an ellipse or a complete ellipse.
     */
    @UML(identifier="ellipticalArc", obligation=CONDITIONAL, specification=ISO_19107)
    public static final SplineCurveForm ELLIPTICAL_ARC = new SplineCurveForm(
                                       "ELLIPTICAL_ARC");

    /**
     * An arc of a finite length of a parabola.
     */
    @UML(identifier="parabolicArc", obligation=CONDITIONAL, specification=ISO_19107)
    public static final SplineCurveForm PARABOLIC_ARC = new SplineCurveForm(
                                       "PARABOLIC_ARC");

    /**
     * An arc of a finite length of one branch of a hyperbola.
     */
    @UML(identifier="hyperbolicArc", obligation=CONDITIONAL, specification=ISO_19107)
    public static final SplineCurveForm HYPERBOLIC_ARC = new SplineCurveForm(
                                       "HYPERBOLIC_ARC");

    /**
     * Constructs an element of the given name. The new element is
     * automatically added to the list returned by {@link #values()}.
     *
     * @param  name  the name of the new element.
     *        This name must not be in use by an other element of this type.
     */
    private SplineCurveForm(final String name) {
        super(name, VALUES);
    }

    /**
     * Returns the list of {@code SplineCurveForm}s.
     *
     * @return the list of codes declared in the current JVM.
     */
    public static SplineCurveForm[] values() {
        synchronized (VALUES) {
            return VALUES.toArray(new SplineCurveForm[VALUES.size()]);
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
    public SplineCurveForm[] family() {
        return values();
    }

    /**
     * Returns the spline curve form that matches the given string, or returns a
     * new one if none match it. More specifically, this methods returns the first instance for
     * which <code>{@linkplain #name() name()}.{@linkplain String#equals equals}(code)</code>
     * returns {@code true}. If no existing instance is found, then a new one is created for
     * the given name.
     *
     * @param  code  the name of the code to fetch or to create.
     * @return a code matching the given name.
     */
    public static SplineCurveForm valueOf(String code) {
        return valueOf(SplineCurveForm.class, code);
    }
}
