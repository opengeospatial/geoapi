/**************************************************************************************************
 **
 ** $Id$
 **
 ** $Source$
 **
 ** Copyright (C) 2003 Open GIS Consortium, Inc. All Rights Reserved. http://www.opengis.org/Legal/
 **
 *************************************************************************************************/
package org.opengis.go.display.util;

import org.opengis.spatialschema.Geometry;

/**
 * Exception thrown by Graphic objects for ISO Geometries that are not supported by a display implementation.
 *
 * @author Open GIS Consortium, Inc.
 * @version $Revision$, $Date$
 */
public class GeometryNotSupportedException extends Exception {
    private Geometry value;

    /**
     * Constructor taking an error (invalid) value.
     */
    public GeometryNotSupportedException(Geometry value) {
        super("This Geometry is not supported: " + value);
        this.value = value;
    }

    /**
     * Accessor for the invalid value.
     * @return the invalid value.
     */
    public Geometry getValue() {
        return value;
    }
}
