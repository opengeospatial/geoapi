/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    Copyright © 2006-2023 Open Geospatial Consortium, Inc.
 *    http://www.geoapi.org
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
