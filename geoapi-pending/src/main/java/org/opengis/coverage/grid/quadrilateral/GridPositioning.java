/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    http://www.geoapi.org
 *
 *    Copyright (C) 2006-2021 Open Geospatial Consortium, Inc.
 *    All Rights Reserved. http://www.opengeospatial.org/ogc/legal
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
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
