/*
 * Copyright (C) 2003 Open GIS Consortium, Inc. All Rights Reserved. http://www.opengis.org/Legal/
 */
package org.opengis.gm.primitive;


/**
 * GM_OrientableSurface consists of a surface and an orientation inherited from GM_OrientablePrimitive.
 * If the orientation is "+", then the GM_OrientableSurface is a GM_Surface. If the
 * orientation is "-", then the GM_OrientableSurface is a reference to a GM_Surface
 * with an upNormal that reverses the direction for this GM_OrientableSurface, the sense
 * of "the top of the surface" (see 6.4.33.2). GM_OrientableSurface: {Orientation =
 * "+" implies primitive = self}; {(Orientation = "-" and TransfiniteSet::contains(p
 * : DirectPosition)) implies (primitive.upNormal(p) = - self.upNormal(p))}; 
 *  
 * @author GeoAPI
 * @version 1.0
 */
public interface OrientableSurface extends OrientablePrimitive {
//    public org.opengis.spatialschema.geometry.complex.GM_CompositeSurface composite[];
//    public void setComposite(org.opengis.spatialschema.geometry.complex.GM_CompositeSurface composite[]) {  }
//    public org.opengis.spatialschema.geometry.complex.GM_CompositeSurface[] getComposite() { return null; }
}
