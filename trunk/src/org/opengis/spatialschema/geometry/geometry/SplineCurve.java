/*$************************************************************************************************
 **
 ** $Id$
 **
 ** $Source$
 **
 ** Copyright (C) 2003 Open GIS Consortium, Inc. All Rights Reserved. http://www.opengis.org/Legal/
 **
 *************************************************************************************************/
package org.opengis.spatialschema.geometry.geometry;

// J2SE direct dependencies
import java.util.List;

// OpenGIS direct dependencies
import org.opengis.spatialschema.geometry.primitive.CurveSegment;

// Annotations
///import org.opengis.annotation.UML;
///import static org.opengis.annotation.Obligation.*;


/**
 * Root for subtypes of {@linkplain CurveSegment curve segment} using some version of spline,
 * either polynomial or rational functions.
 *
 * @author ISO/DIS 19107
 * @author <A HREF="http://www.opengis.org">OpenGIS&reg; consortium</A>
 * @version 2.0
 */
/// @UML (identifier="GM_SplineCurve")
public interface SplineCurve extends CurveSegment {
    /**
     * The sequence of distinct knots used to define the spline basis functions.
     * Recall that the knot data type holds information on knot multiplicity.
     */
/// @UML (identifier="knot", obligation=MANDATORY)
    List/*<Knot>*/ getKnots();

    /**
     * The degree of the polynomial used for interpolation in a
     * {@linkplain PolynomialSpline polynomial spline}.
     */
/// @UML (identifier="degree", obligation=MANDATORY)
    int getDegree();

    /**
     * An array of points that are used in the interpolation in this spline curve.
     */
/// @UML (identifier="controlPoints", obligation=MANDATORY)
    PointArray getControlPoints();
}
