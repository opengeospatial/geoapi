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
 * Revision 1.3  2005/01/21 19:42:48  stephanef
 * RangePosition related change
 *
 * Revision 1.2  2005/01/05 19:03:06  stephanef
 * *** empty log message ***
 *
 * Revision 1.1  2005/01/04 15:08:49  stephanef
 * Change osf to owsx project name
 * 
 */
package com.owsx.coverage;

import org.opengis.util.InternationalString;

/**
 * The CoverageType interface is intended to provide details of the type
 * of a Coverage. Every Coverage must be associated to a CoverageType. 
 *
 * @author Stephane Fellah
 * @version $Revision$
 * @since Jan 3, 2005
 */
public interface CoverageType {
    
    /**
     * Get the coverage name
     * 
     * @return name of the coverage
     */
    public String getName();
    
    /**
     * Get the description of the coverage type
     * 
     * @return description of the coverage type
     */
    public InternationalString getDescription();
     
    /**
     * Indicates if the coverage is spatial i.e. has a domain
     * containing Geometry objects. 
     * NOTE: a Coverage must be either be spatial or temporal or both.
     * 
     * @return true if spatial
     */
    public boolean isSpatial();
    
    /**
     * Indicates if the coverage is temporal
     * NOTE: a Coverage must be either be spatial or temporal or both.
     * 
     * @return  true if temporal
     */
    public boolean isTemporal();
    
    /**
     * Get the Range description
     * 
     * @return RangeType instance
     */
    public RangeType getRangeType();
    
}
