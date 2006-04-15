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
 * Revision 1.1  2005/01/05 19:02:54  stephanef
 * Initial revision
 * 
 */
package com.owsx.coverage;

/**
 * Enumeration definition the Measurement Scale
 * 
 * @author Stephane Fellah
 * @version $Revision$
 * @since Jan 5, 2005
 */
public enum MeasurementScale {
    NOMINAL,
    ORDINAL,
    INTERVAL,
    RATIO,
    STATE,
    COUNT
}
