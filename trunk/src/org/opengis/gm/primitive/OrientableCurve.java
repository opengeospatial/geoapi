/*
 * Copyright (C) 2003 Open GIS Consortium, Inc. All Rights Reserved. http://www.opengis.org/Legal/
 */
package org.opengis.gm.primitive;


/**
 * GM_OrientableCurve consists of a curve and an orientation inherited from GM_OrientablePrimitive.
 * If the orientation is "+", then the GM_OrientableCurve is a GM_Curve. If the orientation
 * is "-", then the GM_OrientableCurve is related to another GM_Curve with a parameterization
 * that reverses the sense of the curve traversal. GM_OrientableCurve: {Orientation
 * = "+" implies primitive = self}; {Orientation = "-" implies primitive.parameterization(length()-s)
 * = parameterization(s)}; 
 *  
 * @author GeoAPI
 * @version 1.0
 */
public interface OrientableCurve extends OrientablePrimitive {
//    public org.opengis.spatialschema.geometry.complex.GM_CompositeCurve composite[];
//    public void setComposite(org.opengis.spatialschema.geometry.complex.GM_CompositeCurve composite[]) {  }
//    public org.opengis.spatialschema.geometry.complex.GM_CompositeCurve[] getComposite() { return null; }
}
