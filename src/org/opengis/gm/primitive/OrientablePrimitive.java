/*
 * Copyright (C) 2003 Open GIS Consortium, Inc. All Rights Reserved. http://www.opengis.org/Legal/
 */
package org.opengis.gm.primitive;


/**
 * An orientable primitives (Figure 10) are those that can be mirrored into new geometric
 * objects in terms of their internal local coordinate systems (manifold charts). For
 * curves, the orientation reflects the direction in which the curve is traversed, that
 * is, the sense of its parameterization. When used as boundary curves, the surface
 * being bounded is to the "left" of the oriented curve. For surfaces, the orientation
 * reflects from which direction the local coordinate system can be viewed as right
 * handed, the "top" or the surface being the direction of a completing z-axis that
 * would form a right-handed system. When used as a boundary surface, the bounded solid
 * is "below" the surface. The orientation of points and solids has no immediate geometric
 * interpretation in 3-dimensional space. GM_OrientablePrimitive objects are essentially
 * references to geometric primitives that carry an "orientation" reversal flag (either
 * "+" or "-") that determines whether this primitive agrees or disagrees with the orientation
 * of the referenced object. NOTE There are several reasons for subclassing the "positive"
 * primitives under the orientable primitives. First is a matter of the semantics of
 * subclassing. Subclassing is assumed to be a "is type of" hierarchy. In the view used,
 * the "positive" primitive is simply the orientable one with the positive orientation.
 * If the opposite view were taken, and orientable primitives were subclassed under
 * the "positive" primitive, then by subclassing logic, the "negative" primitive would
 * have to hold the same sort of geometric description that the "positive" primitive
 * does. The only viable solution would be to separate "negative" primitives under the
 * geometric root as being some sort of reference to their opposite. This adds a great
 * deal of complexity to the subclassing tree. To minimize the number of objects and
 * to bypass this logical complexity, positively oriented primitives are self-referential
 * (are instances of the corresponding primitive subtype) while negatively oriented
 * primitives are not. Orientable primitives are often denoted by a sign (for the orientation)
 * and a base geometry (curve or surface). The sign datatype is defined in ISO 19103.
 * If "c" is a curve, then "<+, c>" is its positive orientable curve and "<-, c>" is
 * its negative orientable curve. In most cases, leaving out the syntax for record "<
 * , >" does not lead to confusion, so "<+, c>" may be written as "+c" or simply "c",
 * and "<-, c>" as "-c". Curve space arithmetic can be performed if the curves align
 * properly, so that: For c, d : GM_OrientableCurves such that c.endPoint = d.startPoint
 * then ( c + d ) ==: GM_CompositeCurve = < c, d > 
 *  
 * @author GeoAPI
 * @version 1.0
 */
public interface OrientablePrimitive extends Primitive {
//    public  orientation;
//    public GM_Primitive primitive[];
//    public void setOrientation( orientation) {  }
//    public  getOrientation() { return null; }
//    public void setPrimitive(GM_Primitive primitive[]) {  }
//    public GM_Primitive[] getPrimitive() { return null; }
}
