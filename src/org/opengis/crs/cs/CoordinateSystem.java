/*
 * Copyright (C) 2003 Open GIS Consortium, Inc. All Rights Reserved. http://www.opengis.org/Legal/
 */
package org.opengis.crs.cs;

// J2SE direct dependencies and extensions
import javax.units.Unit;  // For Javadoc

// OpenGIS direct dependencies
import org.opengis.crs.Info;


/**
 * The set of coordinate system axes that spans a given coordinate space. A coordinate system (CS)
 * is derived from a set of (mathematical) rules for specifying how coordinates in a given space
 * are to be assigned to points. The coordinate values in a coordinate tuple shall be recorded in
 * the order in which the coordinate system axes associations are recorded, whenever those
 * coordinates use a coordinate reference system that uses this coordinate system.
 *
 * @UML abstract CS_CoordinateSystem
 * @author ISO 19111
 * @author <A HREF="http://www.opengis.org">OpenGIS&reg; consortium</A>
 * @version 2.0
 *
 * @see org.opengis.crs.cs.CoordinateSystemAxis
 * @see javax.units.Unit
 * @see org.opengis.crs.datum.Datum
 * @see org.opengis.crs.crs.CoordinateReferenceSystem
 */
public interface CoordinateSystem extends Info {
    /**
     * Returns the dimension of the coordinate system.
     *
     * @return The dimension of the coordinate system.
     */
    public int getDimension();

    /**
     * Returns the axis for this coordinate system at the specified dimension.
     * Each coordinate system must have at least one axis.
     *
     * @param  dimension The zero based index of axis.
     * @return The axis at the specified dimension.
     * @throws IndexOutOfBoundsException if <code>dimension</code> is out of bounds.
     * @UML association usesAxis
     */
    public CoordinateSystemAxis getAxis(int dimension) throws IndexOutOfBoundsException;
}
