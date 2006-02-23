/**************************************************************************************************
 **
 ** $Id$
 **
 ** $Source$
 **
 ** Copyright (C) 2003-2005 Open GIS Consortium, Inc.
 ** All Rights Reserved. http://www.opengis.org/legal/
 **
 *************************************************************************************************/
package org.opengis.go.spatial;

/**
 * This class serves to contain one static constant, VECTOR, indicating that
 * the in-between points of a path are points on the Euclidean shortest
 * distance line between the vertices.  This line should be computed in
 * whatever "real-world" space the points live.  So for example, if the
 * points are LatLonAlts, then the shortest distance line should be computed
 * in a coordinate system such as Earth Centered Earth Fixed (ECEF).
 * <P>
 * As an example of the use of VECTOR, consider two buildings some distance
 * apart, each with a person standing on top.  If one of the people were to
 * fire a laser at the other, the beam would follow a straight path that does
 * not bend with the surface of the earth.  This straight path is what is
 * meant by VECTOR.
 * 
 * @author Open GIS Consortium, Inc.
 * @version $Revision$, $Date$
 */
public class VectorPathType extends PathType {
    
    /**
     * Creates a new VectorPathType with the given value and name.
     * @param name the short name for the enum.
     * @param description the description for the enum.
     */
    protected VectorPathType(String name, String description) {
        super(name, description);
    }
    
    public static final VectorPathType VECTOR =
        new VectorPathType("VECTOR", "The path that is the Euclidean shortest distance path.");
    
}
