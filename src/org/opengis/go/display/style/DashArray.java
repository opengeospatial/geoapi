/**************************************************************************************************
 **
 ** $Id$
 **
 ** $Source$
 **
 ** Copyright (C) 2003 Open GIS Consortium, Inc. All Rights Reserved. http://www.opengis.org/Legal/
 **
 *************************************************************************************************/
package org.opengis.go.display.style;


/**
 * Indicates the various enumerations of drawing a pattern line.
 * An implementor can create a <code>DashArray</code> by passing
 * a float array in the constructor that would indicate the dash
 * pattern to be drawn.
 *
 * @version 0.2
 * @author <A HREF="http://www.opengis.org">OpenGIS&reg; consortium</A>
 */
public class DashArray extends LinePattern {

    //*************************************************************************
    //  Fields
    //*************************************************************************

    /**
     * The pattern.
     */
    private float[] dashPattern;

    //*************************************************************************
    //  Constructor
    //*************************************************************************

    /**
     * Construct an enumeration using the specified pattern.
     */
    public DashArray(String name, String description, float[] pattern){
        super(name, description);
        dashPattern = pattern;
    }

    //*************************************************************************
    //  accessor
    //*************************************************************************

    /**
     * Returns the dash pattern.
     *
     * @revisit Should clone the array in order to protect the enum from change.
     */
    public float[] getDashPattern(){
        return dashPattern;
    }
}
