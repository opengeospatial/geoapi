/*
 * Copyright (C) 2003 Open GIS Consortium, Inc. All Rights Reserved. http://www.opengis.org/Legal/
 */
package org.opengis.gm.primitive;

// OpenGIS direct dependencies
import org.opengis.gm.Geometry;


/**
 * GM_Primitive (Figure 8) is the abstract root class of the geometric primitives. Its
 * main purpose is to define the basic "boundary" operation that ties the primitives
 * in each dimension together. A geometric primitive (GM_Primitive) is a geometric object
 * that is not decomposed further into other primitives in the system. This includes
 * curves and surfaces, even though they are composed of curve segments and surface
 * patches, respectively. This composition is a strong aggregation: curve segments and
 * surface patches cannot exist outside the context of a primitive. NOTE Most geometric
 * primitives are decomposable infinitely many times. Adding a centre point to a line
 * may split that line into two separate lines. A new curve drawn across a surface may
 * divide that surface into two parts, each of which is a surface. This is the reason
 * that the normal definition of primitive as "non-decomposable" is not plausible in
 * a geometry model - the only non-decomposable object in geometry is a point. Any geometric
 * object that is used to describe a feature is a collection of geometric primitives.
 * A collection of geometric primitives may or may not be a geometric complex. Geometric
 * complexes have additional properties such as closure by boundary operations and mutually
 * exclusive component parts. GM_Primitive and GM_Complex share most semantics, in the
 * meaning of operations, attributes and associations. There is an exception in that
 * a GM_Primitive shall not contain its boundary (except in the trivial case of GM_Point
 * where the boundary is empty), while a GM_Complex shall contain its boundary in all
 * cases. This means that if an instantiated object implements GM_Object operations
 * both as GM_Primitive and as a GM_Complex, the semantics of each set theoretic operation
 * is determined by the its name resolution. Specifically, for a particular object such
 * as GM_CompositeCurve, GM_Primitive::contains (returns FALSE for end points) is different
 * from GM_Complex::contains (returns TRUE for end points). Further, if that object
 * is cast as a GM_Primitive value and as a GM_Complex value, then the two values need
 * not be equal as GM_Objects. 
 *  
 * @author GeoAPI
 * @version 1.0
 */
public interface Primitive extends Geometry {
//    public org.opengis.spatialschema.topology.primitive.TP_Primitive topology[];
//    public org.opengis.spatialschema.geometry.complex.GM_Complex complex[];
//    public org.opengis.spatialschema.geometry.complex.GM_Composite composite[];
//    public GM_Primitive containedPrimitive[];
//    public GM_Primitive containingPrimitive[];
//    public GM_OrientablePrimitive proxy[];
//    public void setTopology(org.opengis.spatialschema.topology.primitive.TP_Primitive topology[]) {  }
//    public org.opengis.spatialschema.topology.primitive.TP_Primitive[] getTopology() { return null; }
//    public void setComplex(org.opengis.spatialschema.geometry.complex.GM_Complex complex[]) {  }
//    public org.opengis.spatialschema.geometry.complex.GM_Complex[] getComplex() { return null; }
//    public void setComposite(org.opengis.spatialschema.geometry.complex.GM_Composite composite[]) {  }
//    public org.opengis.spatialschema.geometry.complex.GM_Composite[] getComposite() { return null; }
//    public void setContainedPrimitive(GM_Primitive containedPrimitive[]) {  }
//    public GM_Primitive[] getContainedPrimitive() { return null; }
//    public void setContainingPrimitive(GM_Primitive containingPrimitive[]) {  }
//    public GM_Primitive[] getContainingPrimitive() { return null; }
//    public void setProxy(GM_OrientablePrimitive proxy[]) {  }
//    public GM_OrientablePrimitive[] getProxy() { return null; }
}
