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
	
	// This exception is primarily used by GraphicArc, which throws it
	// if an invalid CurveSegment type or a Conic with non-elliptic parameters
	// is passed into a Geometry setter.  However, CurveSegments, be they Arc,
	// Conic, or otherwise, are not Geometries!
    /**
     * The unsupported geometry.
     */
    //private final Geometry geometry;

    /**
     * Constructs an exception with the given invalid geometry.
     *
     * @param geometry The invalid geometry.
     * @revisit Localize the error message.
     * /
    public GeometryNotSupportedException(Geometry geometry) {
        super("This Geometry is not supported: " + geometry);
        this.geometry = geometry;
    }

    /**
     * Constructs an exception with an empty message.
     */
    public GeometryNotSupportedException() {
    	this(null);
    }
    
    /**
     * Constructs an exception with the specified message.
     *
     * @param message
     * @revisit Localize the error message.
     */
    public GeometryNotSupportedException(String message) {
    	super(message);
    }
    
    /**
     * Returns the invalid geometry.
     *
     * @return the invalid geometry.
     * /
    public Geometry getGeometry() {
        return geometry;
    }
    */
}

