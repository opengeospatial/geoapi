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
 * Revision 1.2  2005/01/05 19:03:06  stephanef
 * *** empty log message ***
 *
 * Revision 1.1  2005/01/04 15:08:49  stephanef
 * Change osf to owsx project name
 * 
 */
package com.owsx.coverage;

import java.util.Iterator;

import com.owsx.datatype.Datatype;

/**
 * Definition of a Measure type. A Measure has a name,
 * a description, a datatype and one or more independant 
 * parameters called axis.
 * 
 * @todo We need to add reference system such as category list or 
 *       the dimension of the quantity by giving a unit of measure. 
 *
 * @author Stephane Fellah
 * @version $Revision$
 * @since Jan 3, 2005
 */
public interface MeasureType {
    
    /**
     * Get the measure name
     * 
     * @return name of the measure
     */
    public String getName();
    
    /**
     * Get the description of the measure
     * 
     * @return description of the meaasure
     */
    public String getDescription();
    
    /**
     * Get the datatype of the measure value
     * 
     * @return Datatype of the measure
     */
    public Datatype getDatatype();
    
    
    /**
     * List all the axes of the measure
     * 
     * @return Iterator of Axis instance
     */
    public Iterator<Axis> listAxes();
    
    /**
     * Get the Axis by name
     * 
     * @param name name of the Axis
     * @return Axis instance or null if not found
     */
    public Axis getAxis(String name);
    
    /**
     * Add an axis to this measure type
     * 
     * @param axis Axis instance
     */
    public void add(Axis axis);
    
    /**
     * Get the reference system of this measure type.
     * Can be a unit of measure indicating the dimension of a quantity
     * ("m" for length) or a qualitative reference system
     * 
     * @return String
     * @todo Review if there is a better way to convey Dimension and Reference System.
     */
    public String getReferenceSystem();
    
    /**
     * Indicates the measurement scale
     * 
     * @return MeasurementScale
     */
    public MeasurementScale getMeasurementScale();
}
