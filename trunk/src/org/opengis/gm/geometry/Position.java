/*
 * Copyright (C) 2003 Open GIS Consortium, Inc. All Rights Reserved. http://www.opengis.org/Legal/
 */
package org.opengis.gm.geometry;

// OpenGIS direct dependencies
import org.opengis.gm.primitive.Point;


/**
 * The data type GM_Position is a union type consisting of either a DirectPosition or
 * of a reference to a GM_Point from which a DirectPosition shall be obtained. The use
 * of this data type allows the identification of a position either directly as a coordinate
 * (variant direct) or indirectly as a reference to a GM_Point (variant indirect). GM_Position::direct
 * [0,1] : DirectPosition; GM_Position::indirect [0,1] : GM_PointRef; GM_Position: {direct.isNull
 * = indirect.isNotNull} 
 *  
 * @author GeoAPI
 * @version 1.0
 */
public interface Position {
//    public void setDirect(DirectPosition direct);
//    public DirectPosition getDirect();

    /**
     * Creates a point at this position.
     *
     * @return The point at this position.
     * @UML constructor Point(Position)
     */
    public Point toPoint();
}
