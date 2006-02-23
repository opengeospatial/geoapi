/**************************************************************************************************
 **
 ** $Id$
 **
 ** $Source$
 **
 ** Copyright (C) 2003 Open GIS Consortium, Inc. All Rights Reserved. http://www.opengis.org/Legal/
 **
 *************************************************************************************************/
package org.opengis.go.display.primitive;

import org.opengis.spatialschema.geometry.Geometry;

/**
 * Exception thrown by {@link Graphic} objects for Geometries that are not supported
 * by a display implementation.
 *
 * @author <A HREF="http://www.opengis.org">OpenGIS&reg; consortium</A>
 * @version $Revision$, $Date$
 */
public class GeometryNotSupportedException extends Exception {
    /**
     * The unsupported geometry.
     */
    private final Geometry geometry;

    /**
     * Constructs an exception with the given invalid geometry.
     *
     * @param geometry The invalid geometry.
     * @revisit Localize the error message.
     */
    public GeometryNotSupportedException(Geometry geometry) {
        super("This Geometry is not supported: " + geometry);
        this.geometry = geometry;
    }

    /**
     * Returns the invalid geometry.
     *
     * @return the invalid geometry.
     */
    public Geometry getGeometry() {
        return geometry;
    }
}

