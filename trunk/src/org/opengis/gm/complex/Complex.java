/*
 * Copyright (C) 2003 Open GIS Consortium, Inc. All Rights Reserved. http://www.opengis.org/Legal/
 */
package org.opengis.gm.complex;

// OpenGIS direct dependencies
import org.opengis.gm.Geometry;


/**
 * geometric complex set of disjoint geometric primitives such that the boundary of
 * each primitive can be represented as the union of other geometric primitives within
 * the complex NOTE: The geometric primitives in the set are mutually exclusive in the
 * sense that no point is interior to more than one primitive. The set is closed under
 * boundary operations, meaning that for each element in the complex, there is a collection
 * (also a complex) of geometric primitives that represents the boundary of that element.
 *  
 * @author GeoAPI
 * @version 1.0
 */
public interface Complex extends Geometry {
//    public org.opengis.spatialschema.geometry.primitive.GM_Primitive element[];
//    public org.opengis.spatialschema.topology.complex.TP_Complex topology[];
//    public GM_Complex superComplex[];
//    public GM_Complex subComplex[];
//    public void setElement(org.opengis.spatialschema.geometry.primitive.GM_Primitive element[]) {  }
//    public org.opengis.spatialschema.geometry.primitive.GM_Primitive[] getElement() { return null; }
//    public void setTopology(org.opengis.spatialschema.topology.complex.TP_Complex topology[]) {  }
//    public org.opengis.spatialschema.topology.complex.TP_Complex[] getTopology() { return null; }
//    public void setSuperComplex(GM_Complex superComplex[]) {  }
//    public GM_Complex[] getSuperComplex() { return null; }
//    public void setSubComplex(GM_Complex subComplex[]) {  }
//    public GM_Complex[] getSubComplex() { return null; }
}
