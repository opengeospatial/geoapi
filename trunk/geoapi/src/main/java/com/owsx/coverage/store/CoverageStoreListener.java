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
 * Revision 1.2  2005/01/06 13:40:50  stephanef
 * Minor editing
 *
 * Revision 1.1  2005/01/05 19:02:54  stephanef
 * Initial revision
 * 
 */
package com.owsx.coverage.store;

import java.util.EventListener;

/**
 * Interface whose methods are invoked when types are modified in a 
 * CoverageStore. To use this interface, implement its methods and 
 * invoke the addCoverageStoreListener() method on CoverageStore. 
 * 
 * @author Stephane Fellah
 * @version $Revision: 658 $
 * @since Jan 5, 2005
 */
public interface CoverageStoreListener extends EventListener {
    /**
     * Invoked when a new CoverageType has been added
     * 
     * @param e CoverageStoreEvent
     */
    public void onCoverageTypeAdded(CoverageStoreEvent e);
    
    /**
     * Invoked when a CoverageType has been modified
     * 
     * @param e CoverageStoreEvent
     */
    public void onCoverageTypeChanged(CoverageStoreEvent e);
    
    /**
     * Invoked when a CoverageType has been removed
     * 
     * @param e CoverageStoreEvent
     */
    public void onCoverageTypeRemoved(CoverageStoreEvent e);
    
    /**
     * Invoked when a new Coverage has been added
     * 
     * @param e CoverageStoreEvent
     */
    public void onCoverageAdded(CoverageStoreEvent e);
    
    /**
     * Invoked when a Coverage has been modified
     * 
     * @param e CoverageStoreEvent
     */
    public void onCoverageChanged(CoverageStoreEvent e);
    
    /**
     * Invoked when a Coverage has been removed
     * 
     * @param e CoverageStoreEvent
     */
    public void onCoverageRemoved(CoverageStoreEvent e);
}
