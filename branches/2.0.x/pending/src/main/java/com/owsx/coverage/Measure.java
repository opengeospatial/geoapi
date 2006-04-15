/* ====================================================================
 * The ImageMatters LLC Software License, Version 1.1
 *
 * Copyright (c) 1999-2004 ImageMatters LLC.  All rights reserved.
 *
 * ====================================================================
 * 
 * $Log$
 * Revision 1.2  2005/08/30 03:17:23  desruisseaux
 * Javadoc fixes.
 *
 * Revision 1.1  2005/03/03 11:54:42  desruisseaux
 * Added Stephane's interfaces proposal for GridCoverage
 *
 * Revision 1.3  2005/01/06 13:54:28  stephanef
 * Replace ValueArray by Dataset
 *
 * Revision 1.2  2005/01/05 19:03:06  stephanef
 * *** empty log message ***
 *
 * Revision 1.1  2005/01/04 15:08:49  stephanef
 * Change osf to owsx project name
 * 
 */
package com.owsx.coverage;


import com.owsx.dataset.Dataset;
import com.owsx.value.Value;

/**
 * Interface used to represent a Measure. Every Measure
 * instance is associated with a Measure type. A Measure
 * may zero, one or more axis (parameters on which the measure
 * depends on). A measure store a ValueArray that has a 
 * predefined order according the associated geometry it 
 * is associated with.
 * 
 * @author Stephane Fellah
 * @version $Revision$
 * @since Jan 3, 2005
 */
public interface Measure {
    /**
     * Get the Measure type of this measure.
     * 
     * @return MeasureType instance
     */
    public MeasureType getMeasureType();
    
    /**
     * Get the axis names that are set for this measure
     * 
     * @return array of axis names
     */
    public String[] getAxisNames();
    
    /**
     * Get the Value for the axis with the given name
     * 
     * @param axisName
     * @return Value instance
     */
    public Value getAxis(String axisName);
    
    /**
     * Set the axis value for the given axis
     * 
     * @param axis Axis instance
     * @param value value
     * @throws IllegalArgumentException - if you passed a invalid axis, 
     * or if the value is invalid
     */
    public void setAxis(Axis axis,Value value);
    
    /**
     * Set the axis value for the given axis name
     * 
     * @param axisName Axis name
     * @param value value
     *@throws IllegalArgumentException - if you passed a invalid axis name, 
     * or if the value is invalid
     */
    public void setAxis(String axisName,Value value);
    
    /**
     * Get the Value for this measure. The Value
     * is following the implicit order of the Geometry.
     * 
     * @return Value instance
     */
    public Value getValue();
    
    /**
     * Get the unit of measure (if the measurement scale  is ratio or interval).
     * 
     * @return String
     * @todo review if there is a better way to model Unit (JSR 108 is dead).
     */
    public String getUnit();
}
