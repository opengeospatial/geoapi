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
package com.owsx.coverage.store;

import java.util.List;

import com.owsx.coverage.Coverage;
import com.owsx.coverage.CoverageType;

/**
 * Gives a normalized interface to a coverage store that can serve 
 * up collections of Coverage instances.
 * 
 * @author Stephane Fellah
 * @version $Revision$
 * @since Jan 5, 2005
 */
public interface CoverageStore {
    /**
     * Get the list of CoverageTypes served by this store
     * 
     * @return List of CoverageType instances
     */
    public List<CoverageType> getCoverageTypes();
    
    /**
     * Get the CoverageType with the given name
     * 
     * @param name name of the CoverageType
     * @return CoverageType instance or null if not found
     */
    public CoverageType getCoverageType(String name);
    
    /**
     * Get the names of all CoverageType in this store
     * 
     * @return array of coverageType names
     */
    public String[] getCoverageTypeNames();
     
    /**
     * Get the list of Coverage instances in this store
     * 
     * @return List of Coverage instances 
     */
    public List<Coverage> getCoverages();
    
    /**
     * Get the list of Coverage instances with the given CoverageType
     * 
     * @param coverageType CoverageType instance
     * @return List of Coverage instances 
     */
    public List<Coverage> getCoverages(CoverageType coverageType);
    
    /**
     * Get the list of Coverage instances with CoverageType name
     * 
     * @param typeName CoverageType name
     * @return List of Coverage instances 
     */
    public List<Coverage> getCoverages(String typeName);
    
    /**
     * Get the Coverage with the given identifier
     * 
     * @param coverageId coverage identifier
     * @return Coverage instance
     */
    public Coverage getCoverage(String coverageId);    
    
    /**
     * Add a CoverageStoreListener to this store
     * 
     * @param l CoverageStoreListener instance
     */
    public void addCoverageStoreListener(CoverageStoreListener l);
   
    /**
     * Remove a CoverageStoreListener from this store
     * 
     * @param l CoverageStoreListener instance
     */
    public void removeCoverageStoreListener(CoverageStoreListener l);
}
