/**************************************************************************************************
 **
 ** $Id$
 **
 ** $Source$
 **
 ** Copyright (C) 2003 Open GIS Consortium, Inc. All Rights Reserved. http://www.opengis.org/Legal/
 **
 *************************************************************************************************/

package org.opengis.spatialschema;

import org.opengis.crs.coordrefsys.CoordinateReferenceSystem;
import org.opengis.crs.coordrefsys.UnsupportedCRSException;


/**
 * <code>SpatialschemaFactory</code> defines a common abstraction for classes that
 * create <code>DirectPosition</code>s.  New coordinates are created by calling
 * <code>createDirectPosition</code>.
 *
 * @author Open GIS Consortium, Inc.
 * @version $Revision$, $Date$
 */
public interface SpatialschemaFactory {
    /**
     * Creates a spatialschema object instance of the type 
     * corresponding to the Class and CoordinateReferenceSystem. 
     * An appropriate combination of Class and CoordinateReferenceSystem can be verified with
     *  <code>CommonFactory.getCommonCapabilities().getSupportedSpatialschemaObjects(CoordinateReferenceSystem)</code>.
     * @param crs the Coordinate Reference System to be used.
     * @param coordInterface The <code>Class</code> of a spatialschema object
     *   interface.
     * @return Returns a newly created <code>Object</code> which should be cast to the <code>Class</code>.
     */
    public Object createObjectWithCRS(Class coordInterface, CoordinateReferenceSystem crs) throws UnsupportedCRSException;


    /**
     * Creates a spatialschema object instance of the type 
     * corresponding to the Class and CoordinateReferenceSystem. 
     * An appropriate combination of Class and CoordinateReferenceSystem can be verified with
     *  <code>CommonFactory.getCommonCapabilities().getSupportedSpatialschemaObjects(CoordinateReferenceSystem)</code>.
     * @param crs the Coordinate Reference System to be used.
     * @param coordInterface The <code>Class</code> of a spatialschema object
     *   interface.
     * @return Returns a newly created <code>PointArray</code>.
     */
    public Object createObject(Class coordInterface);
    
}
