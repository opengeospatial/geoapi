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

import java.util.List;
import java.util.Set;

/**
 * RangeType describes the MeasureTypes contained in
 * the range of a Coverage.
 * 
 * @author Stephane Fellah
 * @since Jan 08, 2005
 * @version $Revision$
 */
public interface RangeType {
    
    /**
     * Get the MeasureType names
     * 
     * @return List of MeasureType names
     */
    public List<String> getMeasureTypeNames();
    
    /**
     * Get the Number of MeasureTypes in the range
     * 
     * @return number of measure types
     */
    public int getNumMeasuresTypes();
    
    /**
     * Get all the measure types of this Coverage type
     * 
     * @return Set of MeasureType instances
     */
    public Set<MeasureType> listMeasureTypes();
    
    /**
     * Get the measureType by name
     * 
     * @param name name of the MeasureType
     * @return MeasureType instance or null if not found
     */
    public MeasureType getMeasureType(String name);
}
