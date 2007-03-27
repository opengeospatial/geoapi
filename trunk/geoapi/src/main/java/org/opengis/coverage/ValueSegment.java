/*$************************************************************************************************
 **
 ** $Id$
 **
 ** $URL$
 **
 ** Copyright (C) 2005 Open GIS Consortium, Inc.
 ** All Rights Reserved. http://www.opengis.org/legal/
 **
 *************************************************************************************************/
package org.opengis.coverage;

// J2SE direct dependencies
import static org.opengis.annotation.Obligation.MANDATORY;
import static org.opengis.annotation.Specification.ISO_19123;

import java.util.Set;

import org.opengis.annotation.UML;
import org.opengis.geometry.primitive.Curve;


/**
 * Limits of a value segment specified by two values of the arc-length parameter of the
 * {@linkplain Curve curve} underlying its parent {@linkplain ValueCurve value curve}.
 *
 * @author Alessio Fabiani
 * @author Martin Desruisseaux
 */
@UML(identifier="CV_ValueSegment", specification=ISO_19123)
public interface ValueSegment {
    /**
     * Returns the value of the arc-length parameter of the parent
     * curve at the start of this value segment.
     */
    @UML(identifier="startParameter", obligation=MANDATORY, specification=ISO_19123)
    double getStartParameter();

    /**
     * Returns the value of the arc-length parameter of the parent
     * curve at the end of this value segment.
     */
    @UML(identifier="endParameter", obligation=MANDATORY, specification=ISO_19123)
    double getEndParameter();

    /**
     * Returns the set of <var>point</var>-<var>value</var> pairs that provide control
     * values for the interpolation. Linear interpolation requires a minimum of two control
     * values, usually those at the beginning and end of the value segment. Additional
     * control values are required to support interpolation by higher order functions.
     */
    @UML(identifier="controlPoint", obligation=MANDATORY, specification=ISO_19123)
    Set<PointValuePair> getControlPoints();
}
