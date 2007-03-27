/*$************************************************************************************************
 **
 ** $Id$
 **
 ** $URL$
 **
 ** Copyright (C) 2005 Open GIS Consortium, Inc.
 ** All Rights Reserved. http://www.opengis.org/legal/
 **
 *************************************************************************************************/
package org.opengis.coverage.grid.quadrilateral;

import org.opengis.referencing.operation.Operation;  // For javadoc
import org.opengis.referencing.operation.Conversion;
import org.opengis.referencing.operation.MathTransform;
import org.opengis.referencing.operation.TransformException;
import org.opengis.geometry.DirectPosition;
import org.opengis.annotation.UML;
import org.opengis.annotation.Extension;

import static org.opengis.annotation.Obligation.*;
import static org.opengis.annotation.Specification.*;


/**
 * Represents a general coordinate conversion algorithm to be applied to the grid.  
 * In the special case where the coordinate conversion is affine, see {@link RectifiedGrid}.
 * This class defines the required {@code convertCoordinates} and {@code inverseConvertCoordinates}
 * methods required by the {@code RectifiableGrid} interface and provides access to the
 * {@link MathTransform} object associated with the algorithm.  Children of this class need
 * only supply the {@link Conversion} object (stored in the inherited {@code operation} attribute)
 * to produce a functional coordinate conversion object.
 *  
 * @author Alexander Petkov
 */
@Extension
public interface RectifiableGrid  extends GridPositioning {
    /**
     * This attribute shall contain only the {@link Conversion} subtype of the {@link Operation}
     * interface, signifying that {@code RectifiableGrid} and children represent only coordinate
     * conversions as defined by ISO 19111.  This attribute shall be identical to the conversion
     * attribute.
     */
/// @Extension
/// Conversion getOperation();

    /**
     * This inherited attribute shall contain only the {@link Conversion} subtype of the
     * {@link Operation} interface, signifying that {@code RectifiableGrid} and children
     * represent only coordinate conversions as defined by ISO 19111.  This attribute shall
     * be identical to the conversion attribute.
     */
/// @Extension
/// Conversion getInverseOperation();

    /**
     * Converts grid coordinates through an affine transform to a direct position.  
     * This is an adapter method for the {@link MathTransform#transform()} method.  
     * The {@link MathTransform} object used in the conversion is associated with
     * the "conversion" and "operation" attributes.
     */
    @Extension
    DirectPosition convertCoordinates(GridCoordinates g) throws TransformException;

    /**
     * Converts through an affine transform a direct position to the grid coordinates of the nearest
     * grid point.  This is an adapter method for the {@link MathTransform#transform()} method.  
     * The {@link MathTransform} object used in the conversion is associated with the "inverseConversion"
     * and "inverseOperation" attributes.
     */
    @Extension
    GridCoordinates inverseConvertCoordinates(DirectPosition p) throws TransformException;

    /**
     * This optional attribute is specified on the {@link GridGeometry} from the legacy OGC 01-004
     * specification.  It is retained here because it allows the user access to a conversion object
     * which yields non-integer results.  This property is derived from the {@link MathTransform}
     * object associated with the operation and conversion attributes, and is merely a convenience
     * method.
     */
    @Extension
    MathTransform getGridToCRS();
}
