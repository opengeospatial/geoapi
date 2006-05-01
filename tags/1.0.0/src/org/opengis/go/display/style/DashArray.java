/**************************************************************************************************
 **
 ** $Id$
 **
 ** $URL$
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
 * @author <A HREF="http://www.opengis.org">OpenGIS&reg; consortium</A>
 * @version $Revision: 659 $, $Date: 2006-02-23 14:07:07 +1100 (jeu., 23 févr. 2006) $
 */
public class DashArray extends LinePattern {

    //*************************************************************************
    //  Static Fields
    //*************************************************************************
   
	public static final DashArray NONE = new DashArray("NONE", "No Dash Array", new float[0]);

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
    public DashArray(String name, String description, float[] pattern) {
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
    public float[] getDashPattern() {
        return dashPattern;
    }
}

