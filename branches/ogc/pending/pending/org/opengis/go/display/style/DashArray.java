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
 * Instances of <code>DashArray</code> indicate the various
 * enumerations of drawing a pattern line.  An implementor can
 * create a DashArray by passing a float array in the constructor
 * that would indicate the dash pattern to be drawn.
 *
 * @author Open GIS Consortium, Inc.
 * @version $Revision$, $Date$
 */

public class DashArray extends LinePattern {

	//*************************************************************************
    //  Fields
    //*************************************************************************

	private float[] dashPattern;

	//*************************************************************************
	//  Constructor
	//*************************************************************************

	public DashArray(String name, String description, float[] pattern){
		super(name, description);
		dashPattern = pattern;
	}

    //*************************************************************************
	//  accessor
	//*************************************************************************

    public float[] getDashPattern(){
     	return dashPattern;
    }
}
