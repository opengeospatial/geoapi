/*
 * Copyright (C) 2003 Open GIS Consortium, Inc. All Rights Reserved. http://www.opengis.org/Legal/
 */
package org.opengis.gm.primitive;


/**
 * GM_Point (Figure 9) is the basic data type for a geometric object consisting of one
 * and only one point. 
 *  
 * @author GeoAPI
 * @version 1.0
 */
public interface Point extends Primitive {
    /**
     * The attribute "position" shall be the DirectPosition of this GM_Point. GM_Point::position
     * [1] : DirectPosition NOTE In most cases, the state of a GM_Point is fully determined
     * by its position attribute. The only exception to this is if the GM_Point has been
     * subclassed to provide additional non-geometric information such as symbology. 
     */
//    public org.opengis.spatialschema.geometry.geometry.DirectPosition position;
//    public org.opengis.spatialschema.geometry.complex.GM_CompositePoint composite[];
//    public void setPosition(org.opengis.spatialschema.geometry.geometry.DirectPosition position) {  }
//    public org.opengis.spatialschema.geometry.geometry.DirectPosition getPosition() { return null; }
//    public void setComposite(org.opengis.spatialschema.geometry.complex.GM_CompositePoint composite[]) {  }
//    public org.opengis.spatialschema.geometry.complex.GM_CompositePoint[] getComposite() { return null; }
}
