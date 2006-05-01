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
 */
package com.owsx.coverage;

/**
 * GeometryValuePair interface
 * 
 * @author Stephane Fellah
 * @since Feb 28, 2005
 * @version $Revision: 658 $
 */
public interface GeometryValuePair {
    /**
     * Get the Domain Object associated with the Value of the Coverage
     * 
     * @return DomainObject instance
     */
    public DomainObject getDomainObject();  
    
    /**
     * Get the Value of the Coverage as Measure.
     * 
     * @return Measure instance
     */
    public Measure getValue();
}
