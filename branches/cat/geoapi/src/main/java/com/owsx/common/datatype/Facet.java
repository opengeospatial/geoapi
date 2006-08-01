/* ====================================================================
 * The ImageMatters LLC Software License, Version 1.1
 *
 * Copyright (c) 1999-2004 ImageMatters LLC.  All rights reserved.
 * Donation to GeoAPI.
 * ====================================================================
 * 
 * $Log$
 * Revision 1.1  2005/03/03 11:54:33  desruisseaux
 * Added Stephane's interfaces proposal for GridCoverage
 *
 * Revision 1.1  2005/01/04 15:08:49  stephanef
 * Change osf to owsx project name
 *
 * Revision 1.1  2004/12/30 22:41:21  stephanef
 * Refactoring of datatype library
 * 
 */
package com.owsx.common.datatype;

/**
 * A facet is a single defining aspect of a ·value space·. Generally speaking, 
 * each facet characterizes a ·value space· along independent axes or dimensions. 
 *
 * @author Stephane Fellah
 * @version $Revision: 658 $
 * @since Dec 30, 2004
 */
public interface Facet {
    
    /**
     * Returns the name of this Facet
     * 
     * @return the name of this Facet
     */
    public String getName();
    
    /**
     * Get the lexical facet value
     * 
     * @return Lexical Facet Value
     */
    public String getLexicalFacetValue();
}
