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
 * Revision 1.1  2005/01/21 19:42:48  stephanef
 * RangePosition related change
 *
 */
package com.owsx.coverage;

/**
 * Interpolation Method 
 * 
 * @author Stephane Fellah
 * @since Jan 18, 2005
 * @version $Revision$
 */
public enum InterpolationMethod {
    /**
     * Nearest neighbor  interpolation
     */
    NEAREST_NEIGHBOR("nearest neighbor"),
    /**
     * Bilinear interpolation
     */
    BILINEAR("bilinear"),
    /**
     * bicubic interpolation
     */
    BICUBIC("bicubic"),
    /**
     * lost area interpolation
     */
    LOST("lost area"),
    /**
     * barycentric interpolation
     */
    BARYCENTRIC("barycentric"),
    /**
     * none interpolation
     */
    NONE("none");
    
    
    String name = null;
  
    /**
     * Default constructor
     * 
     * @param name name of the Interpolation (OGC Name)
     */
    InterpolationMethod(String name)
    {
        this.name = name;
    }
    
    /**
     * Get the name of the Interpolation (OGC Name)
     * 
     * @return name of the interpolation
     */
    public String getName()
    {
        return name;
    }
}
