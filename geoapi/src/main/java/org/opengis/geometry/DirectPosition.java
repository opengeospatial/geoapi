/*$************************************************************************************************
 **
 ** $Id$
 **
 ** $URL$
 **
 ** Copyright (C) 2003-2005 Open GIS Consortium, Inc.
 ** All Rights Reserved. http://www.opengis.org/legal/
 **
 *************************************************************************************************/
package org.opengis.geometry;

import org.opengis.util.Cloneable;
import org.opengis.geometry.coordinate.Position;
import org.opengis.referencing.crs.CoordinateReferenceSystem;
import org.opengis.annotation.UML;

import static org.opengis.annotation.Obligation.*;
import static org.opengis.annotation.Specification.*;


/**
 * Holds the coordinates for a position within some coordinate reference system. Since
 * {@code DirectPosition}s, as data types, will often be included in larger objects (such as
 * {@linkplain org.opengis.geometry.Geometry geometries}) that have references to
 * {@linkplain CoordinateReferenceSystem coordinate reference system}, the
 * {@link #getCoordinateReferenceSystem} method may returns {@code null} if this particular
 * {@code DirectPosition} is included in a larger object with such a reference to a
 * {@linkplain CoordinateReferenceSystem coordinate reference system}. In this case,
 * the cordinate reference system is implicitly assumed to take on the value of the containing
 * object's {@linkplain CoordinateReferenceSystem coordinate reference system}.
 * 
 * @version <A HREF="http://www.opengeospatial.org/standards/as">ISO 19107</A>
 * @author Martin Desruisseaux (IRD)
 * @since GeoAPI 1.0
 */
@UML(identifier="DirectPosition", specification=ISO_19107)
public interface DirectPosition extends Position, Cloneable {
    /**
     * The length of coordinate sequence (the number of entries). This is determined by the
     * {@linkplain #getCoordinateReferenceSystem() coordinate reference system}.
     * 
     * @return The dimensionality of this position.
     */
    @UML(identifier="dimension", obligation=MANDATORY, specification=ISO_19107)
    int getDimension();

    /**
     * A <b>copy</b> of the ordinates presented as a double array.
     * Please note that this is only a copy (the real values may be stored in another format).
     * <p>
     * To quickly access individual ordinates please use the following:
     * 
     * <blockquote><pre>
     * final int dim = position.{@linkplain #getDimension getDimension}();
     * for (int i=0; i&lt;dim; i++) {
     *     position.{@linkplain #getOrdinate getOrdinate}(i); // no copy overhead
     * }
     * </pre></blockquote>
     * 
     * If you want to manipulate ordinates please use:
     * 
     * <blockquote><pre>
     * position.{@linkplain #setOrdinate setOrdinate}(i, value); // edit in place
     * </pre></blockquote>
     * 
     * There are a couple reasons you would like a copy:
     * <ul>
     *   <li>We want an array of coordinates with the intend to modify it for computation purpose
     *       (without modifying the original {@code DirectPosition}), or we want to protect the
     *       array from future {@code DirectPosition} changes.</li>
     *   <li>If {@code DirectPosition.getOrdinates()} is garanteed to not return the backing array,
     *       then we can work directly on this array. If we don't have this garantee, then we must
     *       conservatively clone the array in every cases.</li>
     *   <li>Cloning the returned array is useless if the implementation cloned the array or was
     *       forced to returns a new array anyway (for example because the coordinates were
     *       computed on the fly)</li>
     * </ul>
     *
     * Precedence is given to data integrity over {@code getOrdinates()} performance.
     * Performance concern can be avoided with usage of {@link #getOrdinate(int)}.
     * 
     * @return A copy of the coordinates. Changes in the returned array will not be reflected back
     *         in this {@code DirectPosition} object.
     */
    @UML(identifier = "coordinates", obligation = MANDATORY, specification = ISO_19107)
    double[] getCoordinates();

    /**
     * Returns the ordinate at the specified dimension.
     * 
     * @param dimension The dimension in the range 0 to {@linkplain #getDimension dimension}-1.
     * @return The coordinate at the specified dimension.
     * @throws IndexOutOfBoundsException if the specified dimension is out of bounds.
     */
    double getOrdinate(int dimension) throws IndexOutOfBoundsException;

    /**
     * Sets the ordinate value along the specified dimension.
     * 
     * @param dimension the dimension for the ordinate of interest.
     * @param value the ordinate value of interest.
     * @throws IndexOutOfBoundsException if the specified dimension is out of bounds.
     */
    void setOrdinate(int dimension, double value) throws IndexOutOfBoundsException;

    /**
     * The coordinate reference system in which the coordinate is given. May be {@code null} if this
     * particular {@code DirectPosition} is included in a larger object with such a reference to a
     * {@linkplain CoordinateReferenceSystem coordinate reference system}. In this case, the
     * cordinate reference system is implicitly assumed to take on the value of the containing
     * object's {@linkplain CoordinateReferenceSystem coordinate reference system}.
     * 
     * @return The coordinate reference system, or {@code null}.
     */
    @UML(identifier="coordinateReferenceSystem", obligation=MANDATORY, specification=ISO_19107)
    CoordinateReferenceSystem getCoordinateReferenceSystem();

    /**
     * Compares this direct position with the specified object for equality.
     * Two direct positions are considered equal if the following conditions
     * are meet:
     * <p>
     * <ul>
     *   <li>{@code object} is non-null and is an instance of {@code DirectPosition}.</li>
     *   <li>Both direct positions have the same {@linkplain #getDimension number of dimension}.</li>
     *   <li>Both direct positions have the same or equal {@linkplain #getCoordinateReferenceSystem
     *       coordinate reference system}.</li>
     *   <li>For all dimension <var>i</var>, the {@linkplain #getOrdinate ordinate value} of both
     *       direct positions at that dimension are equals in the sense of {@link Double#equals}.
     *       In other words, <code>{@linkplain java.util.Arrays#equals(double[],double[])
     *       Arrays.equals}({@linkplain #getCoordinates()}, object.getCoordinates())</code>
     *       returns {@code true}.</li>
     * </ul>
     *
     * @since GeoAPI 2.1
     */
    boolean equals(Object object);
    
    /**
     * Returns a hash code value for this direct position. This method should returns
     * the same value as:
     *
     * <code>{@linkplain java.util.Arrays.hashCode(double[]) Arrays.hashCode}({@linkplain
     * #getCoordinates()}) + {@linkplain #getCoordinateReferenceSystem()}.hashCode()</code>
     *
     * where the right hand side of the addition is omitted if the coordinate reference
     * system is null.
     *
     * @since GeoAPI 2.1
     */
    int hashCode();

    /**
     * Makes an exact copy of this coordinate.
     *
     * @deprecated The {@linkplain Cloneable} status of {@code DirectPosition} should be left
     *             to implementors.
     */
    /* {DirectPosition} */Object clone();
}
