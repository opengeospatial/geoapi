/**************************************************************************************************
 **
 ** $Id$
 **
 ** $Source$
 **
 ** Copyright (C) 2003 Open GIS Consortium, Inc. All Rights Reserved. http://www.opengis.org/Legal/
 **
 *************************************************************************************************/
package org.opengis.crs.operation;

// OpenGIS direct dependencies
import org.opengis.spatialschema.geometry.Geometry;
import org.opengis.spatialschema.geometry.DirectPosition;
import org.opengis.spatialschema.geometry.geometry.PointArray;
import org.opengis.crs.crs.IncompatibleCRSException;
import org.opengis.crs.crs.HeterogeneousCRSObjectException;


/**
 * <code>CoordinateTransformation</code> defines a common abstraction for classes
 * that convert <code>DirectPosition</code>s from one <code>CoordinateReferenceSystem</code> to another
 * using a particular <code>OperationMethod</code>.
 *
 * @see CoordinateReferenceSystem
 * @see DirectPosition
 *
 * @author  Open GIS Consortium, Inc.
 * @version $Revision$, $Date$
 *
 * @revisit Extending {@link Transformation} is not quite right; it could be a
 *          {@link Conversion} as well. It may be better to defines the
 *          <code>MathTransform</code> insteface as in OGC 2001-09.
 */
public interface CoordinateTransformation extends Transformation {
    /**
     * Transforms the referenced <code>DirectPosition</code> object to a
     * <code>DirectPosition</code> object in the target <code>CRS</code>
     * as applicable to this <code>Transformation</code> class. The
     * object reference that is passed in is returned.
     * @param fromCoordinate the <code>DirectPosition</code> to be transformed.
     * @return the <code>DirectPosition</code> reference after the transformation.
     */
    public DirectPosition transform(DirectPosition fromCoordinate) throws IncompatibleCRSException;

    /**
     * Transforms the referenced <code>PointArray</code> object to a
     * <code>PointArray</code> object in the target <code>CRS</code>
     * as applicable to this <code>Transformation</code> class. The
     * object reference that is passed in is returned.
     * @param fromCoordinates the <code>PointArray</code> to convert.
     * @return the <code>PointArray</code> that results from the conversion.
     */
    public PointArray transform(PointArray fromCoordinates) throws IncompatibleCRSException;

    /**
     * Transforms the referenced <code>Geometry</code> object to a
     * <code>Geometry</code> object in the target <code>CRS</code>
     * as applicable to this <code>Transformation</code> class. The
     * object reference that is passed in is returned.
     * @param fromGeometry the <code>Geometry</code> to convert.
     * @return the <code>Geometry</code> that results from the conversion.
     */
    public Geometry transform(Geometry fromGeometry) throws IncompatibleCRSException, HeterogeneousCRSObjectException;
}
