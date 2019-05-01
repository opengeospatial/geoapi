/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    http://www.geoapi.org
 *
 *    Copyright (C) 2009-2019 Open Geospatial Consortium, Inc.
 *    All Rights Reserved. http://www.opengeospatial.org/ogc/legal
 *
 *    Permission to use, copy, and modify this software and its documentation, with
 *    or without modification, for any purpose and without fee or royalty is hereby
 *    granted, provided that you include the following on ALL copies of the sohg comftware
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
package org.opengis.geometry.coordinate;

import org.opengis.annotation.UML;
import org.opengis.annotation.Draft;
import org.opengis.geometry.DirectPosition;
import static org.opengis.annotation.Obligation.*;
import static org.opengis.annotation.Specification.*;


/**
 * A direct position adding another element to the coordinate array which carries a non-zero
 * “weight”, and multiplies all other columns in the coordinate array by that weight.
 * “Homogeneous coordinates” equates, for every <var>w</var>&ne;0, the coordinate (<var>wx</var>,
 * <var>wy</var>, <var>wz</var>, <var>w</var>) in four-dimensional Euclidean space with the point
 * (<var>x</var>, <var>y</var>, <var>z</var>) in three-dimensional Euclidean space. This mapping
 * is used heavily in projective geometry which aids implementers in creating realistic perspective
 * views of higher dimensional geometry, and will also be used here in the definition of rational
 * splines.
 *
 * @departure historic
 *   The ISO 19107 draft document defines a single interface, <code>DirectPosition</code>, with
 *   a <code>isHomgeneous</code> attribute. In GeoAPI, we keep the historical direct position
 *   interface mostly unchanged, defines the new properties in a separated type, and replace
 *   the <code>isHomgeneous</code> attribute by a <code>instanceof</code> check.
 *
 * @author  Axel Francois (LSIS/Geomatys)
 * @version 3.1
 * @since   3.1
 */
@Draft
@UML(identifier="DirectPosition", specification=ISO_19107)
public interface HomogeneousDirectPosition extends DirectPosition {
    /**
     * The {@linkplain org.opengis.referencing.cs.CoordinateSystem#getDimension() dimension}
     * of the associated coordinate system. If the {@code DirectPosition} is not homogeneous,
     * dimension is equal to the length of the {@linkplain #getCoordinate() coordinate array}.
     * If the {@code DirectPosition} is in
     * {@linkplain org.opengis.geometry.coordinate.HomogeneousDirectPosition homogeneous format},
     * this is one less than the length of the array.
     *
     * @return the dimensionality of this position.
     *
     * @todo Move this definition in the <cite>geoapi normative</cite> module if approved.
     */
    @Override
    @UML(identifier="dimension", obligation=MANDATORY, specification=ISO_19107)
    int getDimension();

    /**
     * Returns the weight value, which is the last value in the {@linkplain #getCoordinate()
     * coordinate array}.
     *
     * @return the weight as a positive real number.
     */
    @Draft
    @UML(identifier="weight", obligation=MANDATORY, specification=ISO_19107)
    double getWeight();

    /**
     * Sets the weight value, which is the last value in the {@linkplain #getCoordinate()
     * coordinate array}. The given weight shall be grater than zero.
     *
     * @param weight The new weight value.
     */
    void setWeight(double weight);

    /**
     * The {@linkplain CoordinateSystem coordinate system} in which the coordinate is given.
     * May be {@code null} if this particular {@code DirectPosition} is included in a larger
     * object with such a reference to a coordinate system. In this case, the coordinate system
     * is implicitly assumed to take on the value of the containing object's coordinate system.
     * And the first in the sequence of coordinate systems must be the spatial CRS from 19111.
     *
     * @return the coordinate system, or {@code null}.
     *
     * @see #getCoordinateReferenceSystem()
     */
    @Draft
    @UML(identifier="coordinateSystem", obligation=MANDATORY, specification=ISO_19107)
    CoordinateSystem getCoordinateSystem();
}
