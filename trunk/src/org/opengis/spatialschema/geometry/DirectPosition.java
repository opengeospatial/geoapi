/**************************************************************************************************
 **
 ** $Id$
 **
 ** $Source$
 **
 ** Copyright (C) 2003 Open GIS Consortium, Inc. All Rights Reserved. http://www.opengis.org/Legal/
 **
 *************************************************************************************************/
package org.opengis.spatialschema.geometry;

// OpenGIS direct dependencies
import org.opengis.crs.crs.CRS;
import org.opengis.util.Cloneable;


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
 * @version $Revision$, $Date$
 *
 * @revisit: Version number: I suggest to use <strong>specification</strong> version number
 *           (here 2.0).
 */
public interface DirectPosition extends Cloneable {
    /**
     * The length of coordinate sequence (the number of entries).
     * This may be less than or equal to the dimensionality of the 
     * {@linkplain #getCoordinateReferenceSystem() coordinate reference system}.
     *
     * @return The dimensionality of this position.
     * @UML mandatory dimension
     */
    public int getDimension();

    /**
     * Returns a sequence of numbers that hold the coordinate of this position in its
     * reference system.
     *
     * @return The coordinates
     * @UML mandatory coordinates
     *
     * @revisit Should we said that this array is a clone (i.e. change to this array will
     *          not affect the underlying coordinates). Or should we provides an optionnaly
     *          pre-allocated array argument?
     */
    public double[] getCoordinates();

    /**
     * Returns the ordinate at the specified dimension.
     *
     * @param  dimension The dimension in the range 0 to {@linkplain #getDimension dimension}-1.
     * @return The coordinate at the specified dimension.
     * @throws IndexOutOfBoundsException if the specified dimension is out of bounds.
     */
    public double getOrdinate(int dimension) throws IndexOutOfBoundsException;

    /**
     * Sets the ordinate value along the specified dimension.
     *
     * @param dimension the dimension for the ordinate of interest.
     * @param value the ordinate value of interest.
     * @throws IndexOutOfBoundsException if the specified dimension is out of bounds.
     */
    public void setOrdinate(int dimension, double value) throws IndexOutOfBoundsException;

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

    /**
     * Makes an exact copy of this coordinate.
     *
     * @revisit Waiting for GO-1 aggrement (because of covariant return type).
     */
//    public DirectPosition clone();

    /**
     * Releases any resources that may have been allocated for this object.
     * It is an error to reference a DirectPosition after its <code>dispose</code>
     * method has been called.
     * <p>
     * For example, implementations might use this method to return this
     * <code>DirectPosition</code> back to a pool of <code>DirectPosition</code>s, if
     * the <code>SpatialschemaFactory</code> is implemented to
     * manage a pool of <code>DirectPosition</code>s.
     *
     * @revisit Shoud we really provides this method? Using a pool of objects
     *          is strongly discouraged by HotSpot performance team. See
     *          "<A HREF="http://java.sun.com/docs/hotspot/PerformanceFAQ.html#15">Should
     *          I pool objects to help GC?</A>" The answer is <U>no</U>!
     */
    public void dispose();
}
