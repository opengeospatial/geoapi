/*
 * Copyright (C) 2003 Open GIS Consortium, Inc. All Rights Reserved. http://www.opengis.org/Legal/
 */
package org.opengis.gm;

// OpenGIS direct dependencies
import org.opengis.crs.crs.CRS;


/**
 * Holds the coordinates for a position within some coordinate reference system. Since
 * <code>DirectPosition</code>s, as data types, will often be included in larger objects
 * (such as {@linkplain org.opengis.gm.Geometry geometries}) that have references to
 * {@linkplain CRS}, the {@link #getCoordinateReferenceSystem} method may returns
 * <code>null</code> if this particular <code>DirectPosition</code> is included in a larger
 * object with such a reference to a {@linkplain CRS}. In this case, the cordinate reference
 * system is implicitly assumed to take on the value of the containing object's {@linkplain CRS}.
 *  
 * @UML datatype DirectPosition
 * @author ISO/DIS 19107
 * @author <A HREF="http://www.opengis.org">OpenGIS&reg; consortium</A>
 * @version 2.0
 */
public interface DirectPosition {
    /**
     * The length of coordinate sequence (the number of entries).
     * This is determined by the reference system.
     *
     * @return The dimension.
     * @UML mandatory dimension
     */
    public int getDimension();

    /**
     * Returns a sequence of numbers that hold the coordinate of this position in its
     * reference system.
     *
     * @return The coordinates
     * @UML mandatory coordinates
     */
    public double[] getCoordinates();

    /**
     * Returns the coordinate at the specified dimension.
     *
     * @param  dim The dimension in the range 0 to {@link #getDimension dimension}-1.
     * @return The coordinate at the specified dimension.
     * @throws IndexOutOfBoundsException if the specified index is out of bounds.
     */
    public double getCoordinate(int dim) throws IndexOutOfBoundsException;

    /**
     * The coordinate reference system in which the coordinate is given.
     * May be <code>null</code> if this particular <code>DirectPosition</code> is included
     * in a larger object with such a reference to a {@linkplain CRS}. In this case, the cordinate
     * reference system is implicitly assumed to take on the value of the containing object's
     * {@linkplain CRS}.
     *
     * @return The coordinate reference system, or <code>null</code>.
     * @UML association coordinateReferenceSystem
     */
    public CRS getCoordinateReferenceSystem();
}
