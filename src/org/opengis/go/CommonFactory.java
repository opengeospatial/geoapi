/**************************************************************************************************
 **
 ** $Id$
 **
 ** $Source$
 **
 ** Copyright (C) 2003 Open GIS Consortium, Inc. All Rights Reserved. http://www.opengis.org/Legal/
 **
 *************************************************************************************************/

package org.opengis.go;

import org.opengis.crs.crs.CRSAuthorityFactory;
import org.opengis.crs.crs.CRSFactory;
import org.opengis.crs.datum.DatumAuthorityFactory;
import org.opengis.crs.datum.DatumFactory;
import org.opengis.go.geometry.BoundsFactory;
import org.opengis.spatialschema.SpatialschemaFactory;

/**
 * <code>CommonFactory</code> defines a common abstraction for 
 * getting the different factories for classes that all GO-1 objects
 * have a common dependency on.
 *
 * @author Open GIS Consortium, Inc.
 * @version $Revision$, $Date$
 */
public interface CommonFactory {

    /**
     * Returns an object that represents the capabilities of this
     * common factory and its associated canvas.
     */
    public CommonCapabilities getCapabilities();
       
    /**
     * Returns the BoundsFactory singleton.
     */
    public BoundsFactory getBoundsFactory();

    /**
     * Returns the CoordinateReferenceSystemFactory singleton.
     */
    public CRSFactory getCRSFactory();
    
    /**
     * Returns the CRSAuthorityFactory singleton.
     */
    public CRSAuthorityFactory getCRSAuthorityFactory();
       
    /**
     * Returns the DatumAuthorityFactory singleton.
     */
    public DatumAuthorityFactory getDatumAuthorityFactory();
    
    /**
     * Returns the DatumFactory singleton.
     */
    public DatumFactory getDatumFactory();
    
    /**
     * Returns the SpatialschemaFactory singleton.
     */
    public SpatialschemaFactory getSpatialschemaFactory();


}
