/* ====================================================================
 * The ImageMatters LLC Software License, Version 1.1
 *
 * Copyright (c) 1999-2004 ImageMatters LLC.  All rights reserved.
 *
 * ====================================================================
 * 
 * $Log$
 * Revision 1.1  2005/03/03 12:02:46  desruisseaux
 * Added Stephane's interfaces proposal for GridCoverage
 *
 * Revision 1.1  2005/02/28 17:11:05  stephanef
 * *** empty log message ***
 *
 * Revision 1.1  2005/01/05 21:59:40  stephanef
 * Initial revision
 * 
 */
package com.owsx.common.value;

/**
 *
 * @author Stephane Fellah
 * @version $Revision$
 * @since Jan 4, 2005
 */
public interface ValueArray extends Value {
    /**
     * Get the dimensions of the array
     * 
     * @return dimensions
     */
    public int[] getDimensions();
    
    /**
     * Get the rank of the array
     * 
     * @return  rank (number of dimensions)
     */
    public int getRank();
       
    public byte[] getByteArray();
    
    public byte getByte(int...pos);
    
    public short[] getShortArray();
    
    public short getShort(int...pos);
    
    public int[] getIntArray();
    
    public int getInt(int...pos);
    
    public float[] getFloatArray();
    
    public float getFloat(int...pos);
    
    public int[] getCurrentPosition();
}
