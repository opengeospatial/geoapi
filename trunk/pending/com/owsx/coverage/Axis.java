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
 * Revision 1.1  2005/01/04 15:08:49  stephanef
 * Change osf to owsx project name
 * 
 */
package com.owsx.coverage;

import com.owsx.datatype.Datatype;

/**
 * An Axis represents a parameter on which a measure depends on.
 * 
 * @TODO: We need to add reference system such as category list or 
 * the dimension of the quantity by giving a unit of measure. 
 *
 * @author Stephane Fellah
 * @version $Revision$
 * @since Jan 3, 2005
 */
public interface Axis {
    /**
     * Get the axis name
     * 
     * @return name of the axis
     */
    public String getName();
    
    /**
     * Get the description of the axis
     * 
     * @return description of the axis
     */
    public String getDescription();
    
    /**
     * Get the datatype of the axis value
     * 
     * @return Datatype of the axis
     */
    public Datatype getDatatype();
    
    /**
     * Get the reference system of this axis parameter.
     * Can be a unit of measure indicating the dimension of a quantity
     * ("m" for length) or a qualitative reference system
     * 
     * @return String
     * @TODO: Review if there is a better way to convey Dimension 
     * and Reference System.
     */
    public String getReferenceSystem();

}
