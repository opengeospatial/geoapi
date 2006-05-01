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

//OpenGIS dependencies:
import org.opengis.referencing.crs.CoordinateReferenceSystem;
import org.opengis.referencing.operation.Operation;

// Annotations
import org.opengis.annotation.Extension;
import org.opengis.spatialschema.geometry.geometry.Position;
import org.opengis.util.Cloneable;

import static org.opengis.annotation.Obligation.*;
import static org.opengis.annotation.Specification.*;


/**
 * This is an abstract supertype used to form the Positioning association between {@link Grid} and
 * either {@link RectifiedGrid} or {@link ReferencableGrid}.  Implementors should never make an
 * instantiable implementation of this interface.  The two child interfaces represent different
 * levels of complexity for the referencing of gridded data.  A {@link RectifiedGrid} object is
 * capable of transforming coordinates through a simple affine transformation.
 * A {@link ReferencableGrid} object encapsulates an operation of arbitrary complexity.
 * This type does not exist in ISO 19123. 
 *  
 * @author Alexander Petkov
 */
@Extension
public interface GridPositioning {
    /**
     * Specifies the coordinate system into which this object transforms coordinates.
     * ISO 19123 only specifies this association on the {@link ReferenceableGrid} type, 
     * but it is promoted to this superclass because it is required by both 
     * {@linkplain ReferenceableGrid} and {@linkplain RectifiedGrid}.
     */
    @Extension
	CoordinateReferenceSystem getCoordinateReferenceSystem();

    /**
     * Associates this {@code GridPositioning} object with a geometric description provided
     * by the {@link Grid} object.
     */
    @Extension
	Grid getGrid();

    /**
     * Associates this {@code GridPositioning} object with descriptive information about the
     * coordinate operation it implements.  A {@link RectifiableGrid} (or child thereof) will
     * be associated with a coordinate conversion operation, and a {@link ReferenceableGrid}
     * will be associated with a coordinate transformation operation.  All operations include
     * a reference to a {@link MathTransform} object, which actually performs the corodinate
     * conversion.  The {@code targetCRS} association of the operation attribute is considered
     * mandatory in this context.	 	 	
     */
    @Extension
	Operation getOperation();
    
    /**
     * Associates this {@code GridPositioning} object with descriptive information about the
     * coordinate operation it implements.  A {@link RectifiableGrid} (or child thereof) will
     * be associated with a coordinate conversion operation, and a {@link ReferencableGrid}
     * will be associated with a coordinate transformation operation.  All operations include
     * a reference to a {@link MathTransform} object, which actually performs the corodinate
     * conversion.  The {@code targetCRS} association of the {@code inverseOperation} attribute
     * is considered mandatory in this context.  This attribute shall represent the {@link Operation}
     * which is the inverse of the operation attribute.
     */
    @Extension
	Operation getInverseOperation();
}
