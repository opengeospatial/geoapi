/*
 * Copyright (C) 2003 Open GIS Consortium, Inc. All Rights Reserved. http://www.opengis.org/Legal/
 */
package org.opengis.cd;

// OpenGIS direct dependencies
import org.opengis.rs.Info;


/**
 * Geometric figure that can be used to describe the approximate shape of the earth.
 * In mathematical terms, it is a surface formed by the rotation of an ellipse about
 * its minor axis.
 *
 * @UML abstract CD_Ellipsoid
 * @author ISO 19111
 * @author <A HREF="http://www.opengis.org">OpenGIS&reg; consortium</A>
 * @version 2.0
 */
public interface Ellipsoid extends Info {
    /**
     * Length of the semi-major axis of the ellipsoid.
     *
     * @return Length of semi-major axis.
     * @UML mandatory semiMajorAxis
     *
     * @revisit In UML, the return type for this method is <code>Length</code>.
     */
    public double getSemiMajorAxis();

    /**
     * Definition of the second parameter which describes the shape of this ellipsoid.
     *
     * @return Second defining parameter.
     * @UML mandatory secondDefiningParameter
     *
     * @revisit In UML, the return type for this method is <code>SecondDefiningParameter</code>,
     *          which is an union of <code>Scale</code> and <code>Length</code> values. Why not
     *          adopt the "Coordinate Transformation Services implementation specification" API
     *          instead?
     */
    public double getSecondDefiningParameter();
}
