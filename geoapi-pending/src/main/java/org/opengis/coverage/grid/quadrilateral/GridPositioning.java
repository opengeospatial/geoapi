/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    http://www.geoapi.org
 *
 *    Copyright (C) 2006-2021 Open Geospatial Consortium, Inc.
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
package org.opengis.coverage.grid.quadrilateral;

import org.opengis.referencing.crs.CoordinateReferenceSystem;
import org.opengis.referencing.operation.CoordinateOperation;


/**
 * This is an abstract supertype used to form the Positioning association between {@link Grid} and
 * either {@link RectifiedGrid} or {@link ReferenceableGrid}.  Implementers should never make an
 * instantiable implementation of this interface.  The two child interfaces represent different
 * levels of complexity for the referencing of gridded data.  A {@link RectifiedGrid} object is
 * capable of transforming coordinates through a simple affine transformation.
 * A {@link ReferenceableGrid} object encapsulates an operation of arbitrary complexity.
 * This type does not exist in ISO 19123.
 *
 * @author  Alexander Petkov
 */
public interface GridPositioning {
    /**
     * Specifies the coordinate reference system into which this object transforms coordinates.
     * ISO 19123 only specifies this association on the {@link ReferenceableGrid} type,
     * but it is promoted to this superclass because it is required by both
     * {@linkplain ReferenceableGrid} and {@linkplain RectifiedGrid}.
     *
     * @return the coordinate reference system.
     */
    CoordinateReferenceSystem getCoordinateReferenceSystem();

    /**
     * Associates this {@code GridPositioning} object with a geometric description provided
     * by the {@link Grid} object.
     *
     * @return the gridded data.
     */
    Grid getGrid();

    /**
     * Associates this {@code GridPositioning} object with descriptive information about the
     * coordinate operation it implements.  A {@link RectifiableGrid} (or child thereof) will
     * be associated with a coordinate conversion operation, and a {@link ReferenceableGrid}
     * will be associated with a coordinate transformation operation.  All operations include
     * a reference to a {@link org.opengis.referencing.operation.MathTransform} object, which
     * actually performs the coordinate
     * conversion.  The {@code targetCRS} association of the operation attribute is considered
     * mandatory in this context.
     *
     * @return information about the implemented coordinate operation.
     */
    CoordinateOperation getOperation();

    /**
     * Associates this {@code GridPositioning} object with descriptive information about the
     * coordinate operation it implements.  A {@link RectifiableGrid} (or child thereof) will
     * be associated with a coordinate conversion operation, and a {@link ReferenceableGrid}
     * will be associated with a coordinate transformation operation.  All operations include
     * a reference to a {@link org.opengis.referencing.operation.MathTransform} object, which
     * actually performs the coordinate
     * conversion.  The {@code targetCRS} association of the {@code inverseOperation} attribute
     * is considered mandatory in this context.  This attribute shall represent the {@link CoordinateOperation}
     * which is the inverse of the operation attribute.
     *
     * @return the inverse of {@link #getOperation}.
     */
    CoordinateOperation getInverseOperation();
}
