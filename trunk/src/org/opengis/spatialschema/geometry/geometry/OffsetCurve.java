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

// J2SE dependencies
import java.util.List;

// OpenGIS direct dependencies
import org.opengis.spatialschema.geometry.primitive.CurveSegment;

// Annotations
///import org.opengis.annotation.UML;
///import static org.opengis.annotation.Obligation.*;


/**
 * A curve at a constant distance from the basis curve. They can be useful as a cheap and
 * simple alternative to constructing curves that are offsets by definition.
 *
 * @author ISO/DIS 19107
 * @author <A HREF="http://www.opengis.org">OpenGIS&reg; consortium</A>
 * @version 2.0
 */
///@UML (identifier="GM_OffsetCurve")
public interface OffsetCurve extends CurveSegment {
    /**
     * Returns the base curves.
     */
/// @UML (identifier="baseCurve", obligation=MANDATORY)
    public List/*<CurveSegment>*/ getBaseCurves();

    /**
     * Returns the distance at which the offset curve is generated from the basis curve.
     * In a 2D system, positive distances are to be left of the basis curve, and negative
     * distances are right of the basis curve.
     *
     * @unitof Length
     */
/// @UML (identifier="distance", obligation=MANDATORY)
    public double getDistance();

    /**
     * Define the vector direction of the offset curve from the basis curve. It can be
     * omitted in the 2D case, where the distance can be positive or negative. In that
     * case, distance defines left side (positive distance) or right side (negative distance)
     * with respect to the tangent to the basis curve. In 3D the basis curve shall have a
     * well-defined tangent direction for every point. The offset curve at any point
     * (parameter) on the basis curve <var>c</var> is in the direction
     *
     * <P>(TODO: paste the formulae here)</P>
     *
     * <P>For the offset direction to be well-defined, <var>v</var> shall not on any point
     * of the curve be in the same, or opposite, direction as <var>t</var>.
     *
     * The default value of the reference direction shall be the local coordinate axis
     * vector for elevation, which indicates up for the curve in a geographic sense.
     * If the reference direction is the positive tangent to the local elevation axis
     * ("points upward"), then the offset vector points to the left of the curve when
     * viewed from above.
     */
/// @UML (identifier="refDirection", obligation=OPTIONAL)
    public double[] getReferenceDirection();
}
