/* ====================================================================
 * The ImageMatters LLC Software License, Version 1.1
 *
 * Copyright (c) 1999-2004 ImageMatters LLC.  All rights reserved.
 *
 * ====================================================================
 * 
 * $Log$
 * Revision 1.1  2005/03/03 11:54:42  desruisseaux
 * Added Stephane's interfaces proposal for GridCoverage
 *
 * Revision 1.1  2005/01/05 19:02:54  stephanef
 * Initial revision
 * 
 */
package com.owsx.coverage;

import org.opengis.spatialschema.geometry.Geometry;
import com.owsx.temporal.TemporalGeometricPrimitive;

/**
 * Domain Object representing a spatial temporal Object
 *
 * @author Stephane Fellah
 * @version $Revision$
 * @since Jan 4, 2005
 */
public interface DomainObject {
    /**
     * Get the Spatial element of the domain object
     * 
     * @return Geometry instance
     */
    public Geometry getSpatialElement();
    
    /**
     * Get the TemporalGeometricPrimitive element of the domain object
     * 
     * @return TemporalGeometricPrimitive instance
     */
    public TemporalGeometricPrimitive getTemporalElement();
}
