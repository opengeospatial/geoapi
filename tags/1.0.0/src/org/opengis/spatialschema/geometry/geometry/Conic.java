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

// OpenGIS direct dependencies
import org.opengis.spatialschema.geometry.primitive.CurveSegment;


/**
 * Any general conic curve. Any of the conic section curves can be canonically represented
 * in polar co-ordinates (&rho;, &phi;) as:
 *
 * <br><center><img src="doc-files/ConicCurve.png"></center><br>
 *
 * where "<var>P</var>" is semi-latus rectum and "<var>e</var>" is the eccentricity. This gives
 * a conic with focus at the pole (origin), and the vertex on the conic nearest this focus in the
 * direction of the polar axis, <var>&phi;</var>=0.
 *
 * For <var>e</var>=0, this is a circle.
 * For 0&nbsp;&lt;&nbsp;<var>e</var>&nbsp;&lt;&nbsp;1, this is an ellipse.
 * For <var>e</var>=1, this is a parabola.
 * For <var>e</var>&gt;1, this is one branch of a hyperbola.
 *
 * <br><br>
 *
 * These generic conics can be viewed in a two-dimensional Cartesian parameter space
 * (<var>u</var>,&nbsp;<var>v</var>) given by the usual coordinate conversions
 * <var>u</var>=<var>&rho;</var>cos(<var>&phi;</var>) and
 * <var>v</var>=<var>&rho;</var>sin(<var>&phi;</var>).
 * We can then convert this to a 3D coordinate reference system by using an affine transformation,
 * (<var>u</var>,&nbsp;<var>v</var>) &rarr; (<var>x</var>,&nbsp;<var>y</var>,&nbsp;<var>z</var>)
 * which is defined by:
 *
 * (TODO: paste the matrix there).
 *  
 * @UML abstract GM_Conic
 * @author ISO/DIS 19107
 * @author <A HREF="http://www.opengis.org">OpenGIS&reg; consortium</A>
 * @version 2.0
 */
public interface Conic extends CurveSegment {
//    public GM_AffinePlacement position;
//    public boolean shifted;
//    public double eccentricity;
//    public double semiLatusRectum;
//    public double startConstrParam;
//    public double endConstrParam;
//    public void setPosition(GM_AffinePlacement position) {  }
//    public GM_AffinePlacement getPosition() { return null; }
//    public void setShifted(boolean shifted) {  }
//    public boolean getShifted() { return false; }
//    public void setEccentricity(double eccentricity) {  }
//    public double getEccentricity() { return 0; }
//    public void setSemiLatusRectum(double semiLatusRectum) {  }
//    public double getSemiLatusRectum() { return 0; }
//    public void setStartConstrParam(double startConstrParam) {  }
//    public double getStartConstrParam() { return 0; }
//    public void setEndConstrParam(double endConstrParam) {  }
//    public double getEndConstrParam() { return 0; }
}
