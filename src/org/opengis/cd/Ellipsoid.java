/*
 * Copyright (C) 2003 Open GIS Consortium, Inc. All Rights Reserved. http://www.opengis.org/Legal/
 */
package org.opengis.cd;

// OpenGIS direct dependencies
import org.opengis.rs.Identifier;


/**
 * Geometric figure that can be used to describe the approximate shape of the earth.
 * In mathematical terms, it is a surface formed by the rotation of an ellipse about
 * its minor axis.
 *  
 * @author <A HREF="http://www.opengis.org">OpenGIS&reg; consortium</A>
 * @version 2.0
 */
public interface Ellipsoid {
    /**
     * The name by which this ellipsoid is identified.
     *
     * @return The ellipsoid name.
     * @mandatory
     *
     * @rename  Omitted the "<code>ellipsoid</code>" prefix.
     */
    public String getName();

    /**
     * Set of alternative identifications of this ellipsoid. The first <code>ID</code>, if
     * any, is normally the primary identification code, and any others are aliases.
     *
     * @return  The ellipsoid identifiers, or an empty array if there is none.
     * @optional
     *
     * @rename  Omitted the "<code>ellipsoid</code>" prefix.
     * @revisit Should we rename this method as <code>getIdentifiers()</code>?
     *          Note the proposed plural form.
     */
    public Identifier[] getID();

    /**
     * Length of the semi-major axis of the ellipsoid.
     *
     * @return Length of semi-major axis.
     * @mandatory
     *
     * @revisit In UML, the return type for this method is <code>Length</code>.
     */
    public double getSemiMajorAxis();

    /**
     * Definition of the second parameter which describes the shape of this ellipsoid.
     *
     * @return Second defining parameter.
     * @mandatory
     *
     * @revisit In UML, the return type for this method is <code>SecondDefiningParameter</code>,
     *          which is an union of <code>Scale</code> and <code>Length</code> values. Why not
     *          adopt the "Coordinate Transformation Services implementation specification" API
     *          instead?
     */
    public double getSecondDefiningParameter();

    /**
     * Comments on or information about this ellipsoid, including source information.
     *
     * @return Ellipsoid remarks, or <code>null</code> if not available.
     * @optional
     */
    public String getRemarks();
}
