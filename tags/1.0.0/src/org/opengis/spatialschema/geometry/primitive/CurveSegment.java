/*$************************************************************************************************
 **
 ** $Id$
 **
 ** $URL$
 **
 ** Copyright (C) 2003 Open GIS Consortium, Inc. All Rights Reserved. http://www.opengis.org/Legal/
 **
 *************************************************************************************************/
package org.opengis.spatialschema.geometry.primitive;

// OpenGIS direct dependencies
import org.opengis.spatialschema.geometry.geometry.GenericCurve;
import org.opengis.spatialschema.geometry.geometry.PointArray;


/**
 * Defines a homogeneous segment of a {@linkplain Curve curve}.
 * Each <code>CurveSegment</code> shall be in, at most, one {@linkplain Curve curve}.
 *
 * @UML type GM_CurveSegment
 * @author ISO/DIS 19107
 * @author <A HREF="http://www.opengis.org">OpenGIS&reg; consortium</A>
 * @version 2.0
 */
public interface CurveSegment extends GenericCurve {
    /**
     * Returns the curve which own this curve segment.
     *
     * <blockquote><font size=2>
     * <strong>NOTE:</strong> In this specification, curve segments do not appear except in the
     * context of a curve, and therefore this method should never returns <code>null</code>
     * which would preclude the use of curve segments except in this manner. While this would
     * not affect this specification, allowing <code>null</code> owner allows other standards
     * based on this one to use curve segments in a more open-ended manner.
     * </font></blockquote>
     *
     * @return The owner of this curve segment, or <code>null</code> if none.
     * @UML association curve
     *
     * @see Curve#getSegments
     */
    public Curve getCurve();

    /**
     * Specifies the curve interpolation mechanism used for this segment. This mechanism
     * uses the control points and control parameters to determine the position of this
     * <code>CurveSegment</code>.
     *
     * @return The interpolation mechanism used for this segment.
     * @UML operation interpolation
     */
    public CurveInterpolation getInterpolation();

    /**
     * Specifies the type of continuity between this curve segment and its immediate neighbors.
     * If this is the first curve segment in the curve, this value is ignored.
     *
     * <blockquote><font size=2>
     * <strong>NOTE:</strong> Use of these values is only appropriate when the basic curve
     * definition is an underdetermined system. For example, line strings and segments cannot
     * support continuity above C<sup>0</sup>, since there is no spare control parameter to
     * adjust the incoming angle at the end points of the segment. Spline functions on the
     * other hand often have extra degrees of freedom on end segments that allow them to adjust
     * the values of the derivatives to support C<sup>1</sup> or higher continuity.
     * </font></blockquote>
     *
     * @return The type of continuity between this curve semgent and its immediate neighbors.
     * @UML operation numDerivativesAtStart
     *
     * @see #getNumDerivativeInterior
     * @see #getNumDerivativesAtEnd
     */
    public int getNumDerivativesAtStart();

    /**
     * Specifies the type of continuity that is guaranteed interior to the curve. The default
     * value of "0" means simple continuity, which is a mandatory minimum level of continuity.
     * This level is referred to as "C<sup>0</sup>" in mathematical texts. A value of 1 means
     * that the function and its first derivative are continuous at the appropriate end point:
     * "C<sup>1</sup>" continuity. A value of "n" for any integer means the function and its
     * first <var>n</var> derivatives are continuous: "C<sup>n</sup>" continuity.
     *
     * @return The type of continuity that is guaranteed interior to the curve.
     * @UML operation numDerivativeInterior
     *
     * @see #getNumDerivativesAtStart
     * @see #getNumDerivativesAtEnd
     */
    public int getNumDerivativeInterior();

    /**
     * Specifies the type of continuity between this curve segment and its immediate neighbors.
     * If this is the last curve segment in the curve, this value is ignored.
     *
     * <blockquote><font size=2>
     * <strong>NOTE:</strong> Use of these values is only appropriate when the basic curve
     * definition is an underdetermined system. For example, line strings and segments cannot
     * support continuity above C<sup>0</sup>, since there is no spare control parameter to
     * adjust the incoming angle at the end points of the segment. Spline functions on the
     * other hand often have extra degrees of freedom on end segments that allow them to adjust
     * the values of the derivatives to support C<sup>1</sup> or higher continuity.
     * </font></blockquote>
     *
     * @return The type of continuity between this curve semgent and its immediate neighbors.
     * @UML operation numDerivativesAtEnd
     *
     * @see #getNumDerivativesAtStart
     * @see #getNumDerivativeInterior
     */
    public int getNumDerivativesAtEnd();

    /**
     * Returns an ordered array of point values that lie on the {@linkplain CurveSegment curve segment}.
     * In most cases, these will be related to control points used in the construction of the segment.
     * The control points of a curve segment are use to control its shape, and are not always on the
     * curve segment itself. For example in a spline curve, the curve segment is given as a weighted
     * vector sum of the control points. Each weight function will have a maximum within the
     * constructive parameter interval, which will roughly correspond to the point on the curve
     * where it passes closest that the corresponding control point. These points, the values of
     * the curve at the maxima of the weight functions, will be the sample points for the curve
     * segment.
     *
     * @return The control points.
     * @UML operation samplePoint
     */
    public PointArray getSamplePoints();

    /**
     * Returns an ordered pair of points, which are the start point and end point of the curve.
     * This method operates with the same semantics as that on {@linkplain Curve#getBoundary curve}
     * except that the end points of a <code>CurveSegment</code> are not necessarily existing
     * {@linkplain Point points} and thus the boundary may contain transient {@linkplain Point points}.
     *
     * <blockquote><font size=2>
     * <strong>NOTE:</strong> The above {@linkplain CurveBoundary curve boundary} will almost always
     * be two distinct positions, but, like {@linkplain Curve curves}, <code>CurveSegment</code>s can
     * be cycles in themselves. The most likely scenario is that all of the points used will be transients
     * (constructed to support the return value), except for the start point and end point of the aggregated
     * {@linkplain Curve curve}. These two positions, in the case where the {@linkplain Curve curve} is
     * involved in a {@linkplain org.opengis.spatialschema.geometry.complex.Complex complex}, will be represented as
     * {@linkplain Point points} in the same {@linkplain org.opengis.spatialschema.geometry.complex.Complex complex}.
     * </font></blockquote>
     *
     * @return The sets of positions on the boundary.
     * @UML operation boundary
     */
    public CurveBoundary getBoundary();

    /**
     * Reverses the orientation of the parameterizations of the segment.
     *
     * @return The reverse of this curve segment.
     * @UML operation reverse
     */
    public CurveSegment reverse();
}
