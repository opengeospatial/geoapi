/*
 * Copyright (C) 2003 Open GIS Consortium, Inc. All Rights Reserved. http://www.opengis.org/Legal/
 */
package org.opengis.gm.geometry;


/**
 * DirectPosition object data types (Figure 14) hold the coordinates for a position
 * within some coordinate reference system. The coordinate reference system is described
 * in ISO 19111. Since DirectPositions, as data types, will often be included in larger
 * objects (such as GM_Objects) that have references to ISO19111::SC_CRS, the DirectPosition::cordinateReferenceSystem
 * may be left NULL if this particular DirectPosition is included in a larger object
 * with such a reference to a SC_CRS. In this case, the DirectPosition::cordinateReferenceSystem
 * is implicitly assumed to take on the value of the containing object's SC_CRS. 
 *  
 * @author GeoAPI
 * @version 1.0
 */
public interface DirectPosition {
//    public java.util.Vector /*Number*/ coordinate;
//    public int dimension;
//    public void setCoordinate(java.util.Vector /*Number*/ coordinate);
//    public java.util.Vector /*Number*/ getCoordinate();
//    public void setDimension(int dimension);
//    public int getDimension();
}
