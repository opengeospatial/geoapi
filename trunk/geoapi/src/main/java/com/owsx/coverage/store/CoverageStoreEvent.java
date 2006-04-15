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
 * Revision 1.5  2005/01/06 13:54:45  stephanef
 * Added serial number
 *
 * Revision 1.4  2005/01/06 13:40:50  stephanef
 * Minor editing
 *
 * Revision 1.1  2005/01/05 19:02:54  stephanef
 * Initial revision
 * 
 */
package com.owsx.coverage.store;

import java.util.EventObject;

import com.owsx.coverage.Coverage;
import com.owsx.coverage.CoverageType;

/**
 * Event fired by CoverageStore and listened by CoverageStoreListener
 *
 * @author Stephane Fellah
 * @version $Revision$
 * @since Jan 5, 2005
 */
public class CoverageStoreEvent extends EventObject {
    
    private static final long serialVersionUID = 1L;
    /**
     * Enumeration defining the type of event.
     *
     * @author Stephane Fellah
     * @version $Revision$
     * @since Jan 5, 2005
     */
    public static enum EventType { TYPE_ADDED, 
                            TYPE_CHANGED, 
                            TYPE_REMOVED, 
                            COVERAGE_ADDED,
                            COVERAGE_CHANGED,
                            COVERAGE_REMOVED};
    /**
     * Constructor used for a Coverage event 
     * 
     * @param store CoverageStore instance source of the event
     * @param coverage Coverage instance
     * @param eventType eventType
     * 
     */
    public CoverageStoreEvent(CoverageStore store,Coverage coverage,EventType eventType) {
        super(store);
        this.eventType = eventType;
        this.coverage = coverage;
        
    }
   
    /**
     * Constructor used for a CoverageType event 
     * 
     * @param store CoverageStore instance source of the event
     * @param coverageType coverageType instance
     * @param eventType eventType
     * 
     */
    public CoverageStoreEvent(CoverageStore store,CoverageType coverageType,EventType eventType) {
        super(store);
        this.eventType = eventType;
        this.coverageType = coverageType;
    }

    /**
     * Get the COverageStore source of the event
     * 
     * @return CoverageStore instance
     */
    public CoverageStore getSource()
    {
        return (CoverageStore) source;
    }
   
    /**
     * Get the EventType 
     * 
     * @return EventType
     */ 
    public EventType getEventType()
    {
        return eventType;
    }
    
    /**
     * Get the Coverage on which the event applies
     * 
     * @return Coverage instance (null if CoverageType event).
     */
    public Coverage getCoverage()
    {
        return coverage;
    }
    
    /**
     * Get the CoverageType on which the event applies
     * 
     * @return CoverageType instance (null if Coverage event).
     */
    public CoverageType getCoverageType()
    {
        return coverageType;
    }
    
    
    
    private EventType eventType = null;
    private Coverage coverage = null;
    private CoverageType coverageType = null;
}
