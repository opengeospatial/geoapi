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

import org.opengis.referencing.operation.CoordinateOperation;
import org.opengis.referencing.operation.Conversion;
import org.opengis.referencing.operation.MathTransform;
import org.opengis.referencing.operation.TransformException;
import org.opengis.coverage.grid.GridCoordinates;
import org.opengis.geometry.DirectPosition;


/**
 * Represents a general coordinate conversion algorithm to be applied to the grid.
 * In the special case where the coordinate conversion is affine, see {@link RectifiedGrid}.
 * This class defines the required {@code convertCoordinates} and {@code inverseConvertCoordinates}
 * methods required by the {@code RectifiableGrid} interface and provides access to the
 * {@link MathTransform} object associated with the algorithm.  Children of this class need
 * only supply the {@link Conversion} object (stored in the inherited {@code operation} attribute)
 * to produce a functional coordinate conversion object.
 *
 * @author  Alexander Petkov
 */
public interface RectifiableGrid  extends GridPositioning {
    /**
     * This attribute shall contain only the {@link Conversion} subtype of the {@link CoordinateOperation}
     * interface, signifying that {@code RectifiableGrid} and children represent only coordinate
     * conversions as defined by ISO 19111.  This attribute shall be identical to the conversion
     * attribute.
     */
    Conversion getOperation();

    /**
     * This inherited attribute shall contain only the {@link Conversion} subtype of the
     * {@link CoordinateOperation} interface, signifying that {@code RectifiableGrid} and children
     * represent only coordinate conversions as defined by ISO 19111.  This attribute shall
     * be identical to the conversion attribute.
     */
    Conversion getInverseOperation();

    /**
     * Converts grid coordinates through an affine transform to a direct position.
     * This is an adapter method for the {@link MathTransform#transform} method.
     * The {@link MathTransform} object used in the conversion is associated with
     * the "conversion" and "operation" attributes.
     */
    DirectPosition convertCoordinates(GridCoordinates g) throws TransformException;

    /**
     * Converts through an affine transform a direct position to the grid coordinates of the nearest
     * grid point.  This is an adapter method for the {@link MathTransform#transform} method.
     * The {@link MathTransform} object used in the conversion is associated with the "inverseConversion"
     * and "inverseOperation" attributes.
     */
    GridCoordinates inverseConvertCoordinates(DirectPosition p) throws TransformException;

    /**
     * This optional attribute is specified on the {@code GridGeometry} from the legacy OGC 01-004
     * specification.  It is retained here because it allows the user access to a conversion object
     * which yields non-integer results.  This property is derived from the {@link MathTransform}
     * object associated with the operation and conversion attributes, and is merely a convenience
     * method.
     */
    MathTransform getGridToCRS();
}
