/**************************************************************************************************
 **
 ** $Id$
 **
 ** $URL$
 **
 ** Copyright (C) 2003-2005 Open GIS Consortium, Inc.
 ** All Rights Reserved. http://www.opengis.org/legal/
 **
 *************************************************************************************************/
package org.opengis.go.display.style;

import java.util.ArrayList;
import java.util.List;
import org.opengis.util.CodeList;


/**
 * Indicates the various enumerations of drawing a pattern line.
 * An implementor can create a <code>DashArray</code> by passing
 * a float array in the constructor that would indicate the dash
 * pattern to be drawn.
 *
 * @author <A HREF="http://www.opengis.org">OpenGIS&reg; consortium</A>
 * @version $Revision: 658 $, $Date: 2006-02-23 12:09:34 +1100 (jeu., 23 févr. 2006) $
 */
public class DashArray extends LinePattern {
    /**
     * Serial number for compatibility with different versions.
     */
    private static final long serialVersionUID = 3542251485648657363L;

    //*************************************************************************
    //  Static Fields
    //*************************************************************************
    /**
     * The list of enumeration available in this virtual machine.
     * <strong>Must be declared first!</strong>.
     */
    private static final List<LinePattern> VALUES = new ArrayList<LinePattern>(1);

    /**
     * The line should be drawn with no dash array.
     */
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
        super(VALUES, name, description);
        dashPattern = pattern;
    }

    //*************************************************************************
    //  accessor
    //*************************************************************************

    /**
     * Returns the dash pattern.
     *
     * @todo Should clone the array in order to protect the enum from change.
     */
    public float[] getDashPattern() {
        return dashPattern;
    }

    /**
     * Returns the list of {@code DashArray}s.
     */
    public static /*{DashArray}*/ LinePattern[] values() {
        synchronized (VALUES) {
            return (DashArray[]) VALUES.toArray(new DashArray[VALUES.size()]);
        }
    }

    /**
     * Returns the list of enumerations of the same kind than this enum.
     */
    @Override
    public /*{DashArray}*/ CodeList[] family() {
        return values();
    }
}

