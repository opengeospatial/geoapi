/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    http://www.geoapi.org
 *
 *    Copyright (C) 2004-2021 Open Geospatial Consortium, Inc.
 *    All Rights Reserved. http://www.opengeospatial.org/ogc/legal
 *
 *    Permission to use, copy, and modify this software and its documentation, with
 *    or without modification, for any purpose and without fee or royalty is hereby
 *    granted, provided that you include the following on ALL copies of the software
 *    and documentation or portions thereof, including modifications, that you make:
 *
 *    1. The full text of this NOTICE in a location viewable to users of the
 *       redistributed or derivative work.
 *    2. Notice of any changes or modifications to the OGC files, including the
 *       date changes were made.
 *
 *    THIS SOFTWARE AND DOCUMENTATION IS PROVIDED "AS IS," AND COPYRIGHT HOLDERS MAKE
 *    NO REPRESENTATIONS OR WARRANTIES, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED
 *    TO, WARRANTIES OF MERCHANTABILITY OR FITNESS FOR ANY PARTICULAR PURPOSE OR THAT
 *    THE USE OF THE SOFTWARE OR DOCUMENTATION WILL NOT INFRINGE ANY THIRD PARTY
 *    PATENTS, COPYRIGHTS, TRADEMARKS OR OTHER RIGHTS.
 *
 *    COPYRIGHT HOLDERS WILL NOT BE LIABLE FOR ANY DIRECT, INDIRECT, SPECIAL OR
 *    CONSEQUENTIAL DAMAGES ARISING OUT OF ANY USE OF THE SOFTWARE OR DOCUMENTATION.
 *
 *    The name and trademarks of copyright holders may NOT be used in advertising or
 *    publicity pertaining to the software without specific, written prior permission.
 *    Title to copyright in this software and any associated documentation will at all
 *    times remain with copyright holders.
 */
package org.opengis.geometry;

import org.opengis.geometry.coordinate.Position;
import org.opengis.referencing.crs.CoordinateReferenceSystem;
import org.opengis.annotation.UML;
import org.opengis.annotation.Classifier;
import org.opengis.annotation.Stereotype;

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
 * the coordinate reference system is implicitly assumed to take on the value of the containing
 * object's {@linkplain CoordinateReferenceSystem coordinate reference system}.
 *
 * @departure easeOfUse
 *   The ISO specification defines this interface in the {@code coordinate} sub-package.
 *   GeoAPI moved this interface into the {@code org.opengis.geometry} root package for
 *   convenience, because it is extensively used.
 *
 * @author  Martin Desruisseaux (IRD)
 * @version 3.1
 * @since   1.0
 */
@Classifier(Stereotype.DATATYPE)
@UML(identifier="DirectPosition", specification=ISO_19107)
public interface DirectPosition extends Position {
    /**
     * The coordinate reference system in which the coordinate tuple is given. May be {@code null} if this
     * particular {@code DirectPosition} is included in a larger object with such a reference to a
     * {@linkplain CoordinateReferenceSystem coordinate reference system}. In this case, the
     * coordinate reference system is implicitly assumed to take on the value of the containing
     * object's {@linkplain CoordinateReferenceSystem coordinate reference system}.
     *
     * @return the coordinate reference system, or {@code null}.
     */
    @UML(identifier="coordinateReferenceSystem", obligation=MANDATORY, specification=ISO_19107)
    CoordinateReferenceSystem getCoordinateReferenceSystem();

    /**
     * The length of coordinate sequence (the number of entries). This is determined by the
     * {@linkplain #getCoordinateReferenceSystem() coordinate reference system}.
     *
     * @return the dimensionality of this position.
     */
    @UML(identifier="dimension", obligation=MANDATORY, specification=ISO_19107)
    int getDimension();

    /**
     * A <b>copy</b> of the coordinates presented as an array of double values.
     * Please note that this is only a copy (the real values may be stored in
     * another format) so changes to the returned array will not affect the
     * source DirectPosition.
     *
     * {@snippet lang="java" :
     * final int dim = position.getDimension();
     * for (int i=0; i&lt;dim; i++) {
     *     position.getOrdinate(i);       // no copy overhead
     * }}
     *
     * To manipulate coordinates, the following idiom can be used:
     *
     * {@snippet lang="java" :
     * position.setOrdinate(i, value);    // edit in place
     * }
     *
     * There are a couple reasons for requesting a copy:
     *
     * <ul>
     *   <li>We want an array of coordinates with the intent to modify it for computation purpose
     *       (without modifying the original {@code DirectPosition}), or we want to protect the
     *       array from future {@code DirectPosition} changes.</li>
     *   <li>If {@code DirectPosition.getOrdinates()} is guaranteed to not return the backing array,
     *       then we can work directly on this array. If we don't have this guarantee, then we must
     *       conservatively clone the array in every cases.</li>
     *   <li>Cloning the returned array is useless if the implementation cloned the array or was
     *       forced to returns a new array anyway (for example because the coordinates were
     *       computed on the fly)</li>
     * </ul>
     *
     * Precedence is given to data integrity over {@code getOrdinates()} performance.
     * Performance concern can be avoided with usage of {@link #getOrdinate(int)}.
     *
     * @return a copy of the coordinates. Changes in the returned array will not be reflected back
     *         in this {@code DirectPosition} object.
     *
     * @todo needs to be renamed {@code getCoordinates()}.
     */
    @UML(identifier="coordinate", obligation=MANDATORY, specification=ISO_19107)
    double[] getCoordinate();

    /**
     * Returns the coordinate at the specified dimension.
     *
     * @param  dimension  the dimension in the range 0 to {@linkplain #getDimension dimension}-1.
     * @return the coordinate at the specified dimension.
     * @throws IndexOutOfBoundsException if the given index is negative or is equal or greater
     *         than the {@linkplain #getDimension() position dimension}.
     *
     * @todo needs to be renamed {@code getCoordinate(int)}.
     */
    double getOrdinate(int dimension) throws IndexOutOfBoundsException;

    /**
     * Sets the coordinate value along the specified dimension.
     *
     * @param  dimension  the dimension for the coordinate of interest.
     * @param  value      the coordinate value of interest.
     * @throws IndexOutOfBoundsException if the given index is negative or is equal or greater
     *         than the {@linkplain #getDimension() position dimension}.
     * @throws UnsupportedOperationException if this direct position is immutable.
     *
     * @todo needs to be renamed {@code setCoordinate(int, double)}.
     */
    void setOrdinate(int dimension, double value)
            throws IndexOutOfBoundsException, UnsupportedOperationException;

    /**
     * Compares this direct position with the specified object for equality.
     * Two direct positions are considered equal if the following conditions
     * are meet:
     *
     * <ul>
     *   <li>{@code object} is non-null and is an instance of {@code DirectPosition}.</li>
     *   <li>Both direct positions have the same {@linkplain #getDimension() number of dimensions}.</li>
     *   <li>Both direct positions have the same or equal {@linkplain #getCoordinateReferenceSystem
     *       coordinate reference system}.</li>
     *   <li>For all dimension <var>i</var>, the {@linkplain #getOrdinate coordinate value} of both
     *       direct positions at that dimension are equals in the sense of {@link Double#equals(Object)}.
     *       In other words, <code>{@linkplain java.util.Arrays#equals(double[],double[])
     *       Arrays.equals}({@linkplain #getCoordinate()}, object.getCoordinate())</code>
     *       returns {@code true}.</li>
     * </ul>
     *
     * @param  object  the object to compare with this direct position for equality.
     * @return {@code true} if the given object is equal to this direct position.
     */
    @Override
    boolean equals(Object object);

    /**
     * Returns a hash code value for this direct position. This method should returns
     * the same value as:
     *
     * <code>{@linkplain java.util.Arrays#hashCode(double[]) Arrays.hashCode}({@linkplain
     * #getCoordinate()}) + {@linkplain #getCoordinateReferenceSystem()}.hashCode()</code>
     *
     * where the right hand side of the addition is omitted if the coordinate reference
     * system is null.
     *
     * @return a hash code value for this direct position.
     */
    @Override
    int hashCode();
}
