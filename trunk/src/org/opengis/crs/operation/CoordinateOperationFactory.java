/*$************************************************************************************************
 **
 ** $Id$
 **
 ** $Source$
 **
 ** Copyright (C) 2003 Open GIS Consortium, Inc. All Rights Reserved. http://www.opengis.org/Legal/
 **
 *************************************************************************************************/
package org.opengis.crs.operation;

// OpenGIS dependencies
import org.opengis.crs.crs.CRS;
import org.opengis.crs.FactoryException;


/**
 * Creates coordinate {@linkplain Transformation transformations} or
 * {@linkplain Conversion conversions} between two coordinate reference systems.
 *
 * @author <A HREF="http://www.opengis.org">OpenGIS&reg; consortium</A>
 * @version 2.0
 */
public interface CoordinateOperationFactory {
    /**
     * Creates an operation between two coordinate reference systems.
     * This method will examine the CRS in order to construct a transformation between them.
     * This method may fail if no path between the coordinate reference systems is found.
     *
     * @param  sourceCRS Input coordinate reference system.
     * @param  targetCRS Output coordinate reference system.
     * @throws FactoryException if no transformation path was found from <code>sourceCRS</code>
     *         to <code>targetCRS</code>.
     *
     * @revisit Should we create a more accurate subclass of <code>FactoryException</code>?
     */
    CoordinateOperation createFromCRS(CRS sourceCRS, CRS targetCRS) throws FactoryException;
}
