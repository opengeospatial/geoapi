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

// OpenGIS direct dependencies
import org.opengis.util.Cloneable;
import org.opengis.geometry.coordinate.Position;
import org.opengis.referencing.crs.CoordinateReferenceSystem;

// Annotations
import org.opengis.annotation.UML;
import static org.opengis.annotation.Obligation.*;
import static org.opengis.annotation.Specification.*;

/**
 * Holds the coordinates for a position within some coordinate reference system. Since
 * {@code DirectPosition}s, as data types, will often be included in larger objects (such as
 * {@linkplain org.opengis.geometry.Geometry geometries}) that have references to
 * {@link CoordinateReferenceSystem}, the {@link #getCoordinateReferenceSystem} method may returns
 * {@code null} if this particular {@code DirectPosition} is included in a larger object with such a
 * reference to a {@linkplain CoordinateReferenceSystem coordinate reference system}. In this case,
 * the cordinate reference system is implicitly assumed to take on the value of the containing
 * object's {@link CoordinateReferenceSystem}.
 * 
 * @version <A HREF="http://www.opengis.org/docs/01-101.pdf">Abstract specification 5</A>
 * @author Martin Desruisseaux (IRD)
 * @since GeoAPI 1.0
 */
@UML(identifier = "DirectPosition", specification = ISO_19107)
public interface DirectPosition extends Position, Cloneable {
    /**
     * The length of coordinate sequence (the number of entries). This is determined by the
     * {@linkplain #getCoordinateReferenceSystem() coordinate reference system}.
     * 
     * @return The dimensionality of this position.
     */
    @UML(identifier = "dimension", obligation = MANDATORY, specification = ISO_19107)
    int getDimension();

    /**
     * A <b>copy</b> of the ordinates presented as a double array.
     * <p>
     * Please note that this is only a copy (the real values may be stored in another format).
     * <p>
     * <p>
     * To quickly access individual ordinates please use the following:
     * 
     * <pre><code>
     * for( int i = 0, D = position.getDimension(); i &lt; D; i++ ) {
     *     position.getOrdinate(i); // no copy overhead!
     * }
     * </code></pre>
     * 
     * If you want to manipulate ordinates please use:
     * 
     * <pre><code>
     * position.setOrdinate(i, value); // edit in place
     * </code></pre>
     * 
     * There are a couple reasons you would like a copy:
     * <ul>
     * <li>We want an array of coordinates with the intend to modify it for computation purpose
     * (without modifying the original DirectPosition), or we want to protect the array from future
     * DirectPosition changes. </li>
     * <li>If DirectPosition.getOrdinates() is garanteed to not return the backing array, then we
     * can work directly on this array. If we don't have this garantee, then we must conservatively
     * clone the array in very cases.</li>
     * <li>Cloning the returned array is useless if the implementation cloned the array or was
     * forced to returns a new array anyway (for example because the coordinates were computed on
     * the fly)</li>
     * </ul>
     * Precedence is given to data integrity over 'getOrdinates()' performance, any performance
     * concern can be avoided with usage of 'getOrdinate(int)'.
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
    double getOrdinate( int dimension ) throws IndexOutOfBoundsException;

    /**
     * Sets the ordinate value along the specified dimension.
     * 
     * @param dimension the dimension for the ordinate of interest.
     * @param value the ordinate value of interest.
     * @throws IndexOutOfBoundsException if the specified dimension is out of bounds.
     */
    void setOrdinate( int dimension, double value ) throws IndexOutOfBoundsException;

    /**
     * The coordinate reference system in which the coordinate is given. May be {@code null} if this
     * particular {@code DirectPosition} is included in a larger object with such a reference to a
     * {@linkplain CoordinateReferenceSystem coordinate reference system}. In this case, the
     * cordinate reference system is implicitly assumed to take on the value of the containing
     * object's {@linkplain CoordinateReferenceSystem coordinate reference system}.
     * 
     * @return The coordinate reference system, or {@code null}.
     */
    @UML(identifier = "coordinateReferenceSystem", obligation = MANDATORY, specification = ISO_19107)
    CoordinateReferenceSystem getCoordinateReferenceSystem();

    /**
     * Makes an exact copy of this coordinate.
     */
    /* {DirectPosition} */Object clone();
}
