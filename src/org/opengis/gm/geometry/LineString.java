/*
 * Copyright (C) 2003 Open GIS Consortium, Inc. All Rights Reserved. http://www.opengis.org/Legal/
 */
package org.opengis.gm.geometry;

// OpenGIS direct dependencies
import org.opengis.gm.primitive.CurveSegment;


/**
 * A GM_LineString (Figure 16) consists of sequence of line segments, each having a
 * parameterization like the one for GM_LineSegment (See 6.4.11). The class essentially
 * combines a Sequence<GM_LineSegments> into a single object, with the obvious savings
 * of storage space. 
 *  
 * @author GeoAPI
 * @version 1.0
 */
public interface LineString extends CurveSegment {
//    public GM_PointArray controlPoint;
//    public void setControlPoint(GM_PointArray controlPoint) {  }
//    public GM_PointArray getControlPoint() { return null; }
}
