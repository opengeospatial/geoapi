/*
 * Copyright (C) 2003 Open GIS Consortium, Inc. All Rights Reserved. http://www.opengis.org/Legal/
 */
package org.opengis.gm.complex;

// OpenGIS direct dependencies
import org.opengis.gm.primitive.OrientableCurve;


/**
 * geometric composite curve, path list of geometric curves such that the each geometric
 * curve in the set terminates at the start point of the subsequent curve in the list
 *  
 * @author GeoAPI
 * @version 1.0
 *
 * @revisit This interface extends (indirectly) both {@link org.opengis.gm.primitive.Primitive} and
 *          {@link org.opengis.gm.complex.Complex}. Concequently, there is a clash in the semantics
 *          of some set theoretic operation. Specifically, <code>Primitive.contains(...)</code>
 *          (returns FALSE for end points) is different from <code>Complex.contains(...)</code>
 *          (returns TRUE for end points).
 */
public interface CompositeCurve extends Composite, OrientableCurve {
//    public org.opengis.spatialschema.geometry.primitive.GM_OrientableCurve generator[];
//    public void setGenerator(org.opengis.spatialschema.geometry.primitive.GM_OrientableCurve generator[]) {  }
//    public org.opengis.spatialschema.geometry.primitive.GM_OrientableCurve[] getGenerator() { return null; }
}
