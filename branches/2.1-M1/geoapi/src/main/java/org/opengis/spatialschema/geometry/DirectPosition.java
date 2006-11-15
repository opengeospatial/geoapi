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
package org.opengis.spatialschema.geometry;

// OpenGIS direct dependencies
import org.opengis.util.Cloneable;
import org.opengis.spatialschema.geometry.geometry.Position;
import org.opengis.referencing.crs.CoordinateReferenceSystem;

// Annotations
import org.opengis.annotation.UML;
import static org.opengis.annotation.Obligation.*;
import static org.opengis.annotation.Specification.*;


/**
 * Holds the coordinates for a position within some coordinate reference system. Since
 * {@code DirectPosition}s, as data types, will often be included in larger objects
 * (such as {@linkplain org.opengis.spatialschema.geometry.Geometry geometries}) that have
 * references to {@link CoordinateReferenceSystem}, the {@link #getCoordinateReferenceSystem}
 * method may returns {@code null} if this particular {@code DirectPosition} is
 * included in a larger object with such a reference to a {@linkplain CoordinateReferenceSystem
 * coordinate reference system}. In this case, the cordinate reference system is implicitly
 * assumed to take on the value of the containing object's {@link CoordinateReferenceSystem}.
 * 
 * @version <A HREF="http://www.opengis.org/docs/01-101.pdf">Abstract specification 5</A>
 * @author Martin Desruisseaux (IRD)
 * @since GeoAPI 1.0
 */
@UML(identifier="DirectPosition", specification=ISO_19107)
public interface DirectPosition extends Position, Cloneable {
    /**
     * The length of coordinate sequence (the number of entries). This is determined by
     * the {@linkplain #getCoordinateReferenceSystem() coordinate reference system}.
     *
     * @return The dimensionality of this position.
     */
    @UML(identifier="dimension", obligation=MANDATORY, specification=ISO_19107)
    int getDimension();

    /**
     * Returns the sequence of numbers that hold the coordinate of this
     * position in its reference system.
     *
     * @return A copy of the coordinates. Changes in the returned array will not be reflected
     *         back in this {@code DirectPosition} object.
     */
    @UML(identifier="coordinates", obligation=MANDATORY, specification=ISO_19107)
    double[] getCoordinates();

    /**
     * Returns the ordinate at the specified dimension.
     *
     * @param  dimension The dimension in the range 0 to {@linkplain #getDimension dimension}-1.
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
     * The coordinate reference system in which the coordinate is given.
     * May be {@code null} if this particular {@code DirectPosition} is included
     * in a larger object with such a reference to a {@linkplain CoordinateReferenceSystem
     * coordinate reference system}. In this case, the cordinate reference system is implicitly
     * assumed to take on the value of the containing object's {@linkplain CoordinateReferenceSystem
     * coordinate reference system}.
     *
     * @return The coordinate reference system, or {@code null}.
     */
    @UML(identifier="coordinateReferenceSystem", obligation=MANDATORY, specification=ISO_19107)
    CoordinateReferenceSystem getCoordinateReferenceSystem();

    /**
     * Makes an exact copy of this coordinate.
     */
    /*{DirectPosition}*/ Object clone();
}
