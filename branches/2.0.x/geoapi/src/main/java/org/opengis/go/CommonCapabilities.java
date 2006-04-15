/**************************************************************************************************
 **
 ** $Id$
 **
 ** $Source$
 **
 ** Copyright (C) 2003-2005 Open GIS Consortium, Inc.
 ** All Rights Reserved. http://www.opengis.org/legal/
 **
 *************************************************************************************************/

package org.opengis.go;

import org.opengis.referencing.crs.CoordinateReferenceSystem;
import org.opengis.go.spatial.PathType;

/**
 * The <code>CommonCapabilities</code> interface provides runtime information
 * about the capabilities of a given GO-1 implementation. Objects implementing
 * this interface are obtained through the
 * <code>CommonFactory.getCapabilities()</code> method.
 *
 * @author Open GIS Consortium, Inc.
 * @version $Revision$, $Date$
 */
public interface CommonCapabilities {

    /**
     * Returns an array of <code>Class</code> objects for the directposition
     * interfaces that are supported by a given implementation.
     */
    Class[] getSupportedDirectPositions();

    /**
     * Returns an array of <code>Class</code> objects for the directposition
     * interfaces that are supported by a given implementation for the specified
     * Coordinate Reference System.
     *
     * @param crs the Coordinate Reference System.
     */
    Class[] getSupportedDirectPositions(CoordinateReferenceSystem crs);

    /**
     * Returns an array of <code>Class</code> objects from the
     * spatialschema-package that are supported by a given implementation for
     * the specified Coordinate Reference System.
     *
     * @param crs the Coordinate Reference System.
     */
    Class[] getSupportedSpatialSchemaObjects(CoordinateReferenceSystem crs);

    /**
     * Returns an array of <code>Class</code> objects for the orientation
     * interfaces that are supported by a given implementation.
     */
    Class[] getSupportedOrientations();

    /**
     * Returns an array of <code>Class</code> objects for the orientation
     * interfaces that are supported by a given implementation for the specified
     * Coordinate Reference System.
     *
     * @param crs the Coordinate Reference System.
     */
    Class[] getSupportedOrientations(CoordinateReferenceSystem crs);

    /**
     * Returns an array of <code>Class</code> objects for the geometry
     * interfaces that are supported by a given implementation. These Class
     * objects can be used in a call to
     * <code>GeometryFactory.getGeometry(Class)</code>.
     */
    Class[] getSupportedGeometries();

    /**
     * Returns an array of Strings that are keys for the Coordinate Reference
     * Systems that this implementation supports. These strings can be used in a
     * call to
     * <code>CoordinateReferenceSystemFactory.getCoordinateReferenceSystem(String)</code>.
     */
    String[] getSupportedCoordinateReferenceSystems();

    /**
     * Returns an array of Strings that are keys for the datums that this
     * implementation supports. These strings can be used in a call to
     * <code>DatumFactory.getDatum(String)</code>.
     */
    String[] getSupportedDatums();

    /**
     * Returns an array of <code>PathType</code> objects that indicates the
     * path types that are supported.
     */
    PathType[] getSupportedPathTypes();

    /**
     * Returns an array of Strings representing
     * the projections that are supported.
     */
    String[] getSupportedProjections();
}