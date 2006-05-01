/**************************************************************************************************
 **
 ** $Id$
 **
 ** $URL$
 **
 ** Copyright (C) 2003 Open GIS Consortium, Inc. All Rights Reserved. http://www.opengis.org/Legal/
 **
 *************************************************************************************************/
package org.opengis.go.spatial;

/**
 * The instances of this class represent methods of calculating a path
 * between two locations. The in-between points of the path satisfy two
 * conditions:
 * <OL>
 * <LI>The in-between points are the same regardless of the way the current
 *     path is displayed (i.e. the path is independent of map projection,
 *     current viewport, etc.)
 * <LI>The in-between points are claculated along a surface that the points
 *     are projected onto (such as the surface of the earth).
 * </OL>
 * The second condition implies that altitude is not taken into account
 * when calculating paths of type GlobalPathType.  Hence paths of this type
 * are well suited for navigation of surface ships or vehicles.\
 * 
 * @author Open GIS Consortium, Inc.
 * @version $Revision: 659 $, $Date: 2006-02-23 14:07:07 +1100 (jeu., 23 févr. 2006) $
 */
public class GlobalPathType extends PathType {

    /**
     * The list of enumeration available in this virtual machine.
     * <strong>Must be declared first!</strong>.
     */
  //  private static final List VALUES = new ArrayList(4);   
        
    /**
     * Creates a new GlobalPathType with the given value and name.
     * @param name the short name for the enum.
     * @param description the description for the enum.
     */
    protected GlobalPathType (String name, String description) {
        super(name, description);
    }
    
    public static final GlobalPathType GREAT_CIRCLE_ELLIPSOIDAL =
        new GlobalPathType("GREAT_CIRCLE_ELLIPSOIDAL",
            "The path that is the shortest distance path over the WGS84 ellipsoid.");
            
    public static final GlobalPathType GREAT_CIRCLE_SPHERICAL =
        new GlobalPathType("GREAT_CIRCLE_SPHERICAL",
            "The path that is the shortest distance path over the sphere whose radius is the equatorial radius of the WGS84 ellipsoid.");
    
    public static final GlobalPathType RHUMBLINE_ELLIPSOIDAL =
        new GlobalPathType("RHUMBLINE_ELLIPSOIDAL",
            "The path that is the path of constant bearing over the WGS84 ellipsoid.");
    
    public static final GlobalPathType RHUMBLINE_SPHERICAL =
        new GlobalPathType("RHUMBLINE_SPHERICAL",
            "The path that is the path of constant bearing over the sphere whose radius is the equatorial radius of the WGS84 ellipsoid.");
    
    /**
     * Enumeration value of the <code>GREAT_CIRCLE_ELLIPSOIDAL</code>
     * constant.
     */
    //public static final int GREAT_CIRCLE_ELLIPSOIDAL_VALUE = GREAT_CIRCLE_ELLIPSOIDAL.getValue();
    
    /**
     * Enumeration value of the <code>GREAT_CIRCLE_SPHERICAL</code>
     * constant.
     */
    //public static final int GREAT_CIRCLE_SPHERICAL_VALUE = GREAT_CIRCLE_SPHERICAL.getValue();
    
    /**
     * Enumeration value of the <code>RHUMBLINE_ELLIPSOIDAL</code>
     * constant.
     */
    //public static final int RHUMBLINE_ELLIPSOIDAL_VALUE = RHUMBLINE_ELLIPSOIDAL.getValue();
    
    /**
     * Enumeration value of the <code>RHUMBLINE_SPHERICAL</code>
     * constant.
     */
    //public static final int RHUMBLINE_SPHERICAL_VALUE = RHUMBLINE_SPHERICAL.getValue();
    
    /**
     * A list containing all the enumerators so that the list can be
     * "walked" and also to do reverse lookups (id to object).
     */
    private static final GlobalPathType[] enumList =
        {
            GREAT_CIRCLE_ELLIPSOIDAL,
            GREAT_CIRCLE_SPHERICAL,
            RHUMBLINE_ELLIPSOIDAL,
            RHUMBLINE_SPHERICAL };
  
}
