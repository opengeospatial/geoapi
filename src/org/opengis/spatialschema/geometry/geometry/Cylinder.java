/*$************************************************************************************************
 **
 ** $Id$
 **
 ** $Source$
 **
 ** Copyright (C) 2003 Open GIS Consortium, Inc. All Rights Reserved. http://www.opengis.org/Legal/
 **
 *************************************************************************************************/
package org.opengis.spatialschema.geometry.geometry;

// Annotations
///import org.opengis.annotation.UML;
///import static org.opengis.annotation.Obligation.*;


/**
 * A {@linkplain GriddedSurface gridded surface} given as a family of circles whose positions
 * vary along a set of parallel lines, keeping the cross sectional horizontal curves of a constant
 * shape. Given the same working assumptions as in {@linkplain GriddedSurface gridded surface}, a
 * cylinder can be given by two circles, giving us control points of the form
 * 
 * &lt;&lt;P1, P2, P3&gt;, &lt;P4, P5, P6&gt;&gt;.
 *
 * @author ISO/DIS 19107
 * @author <A HREF="http://www.opengis.org">OpenGIS&reg; consortium</A>
 * @version 2.0
 */
///@UML (identifier="GM_Cylinder")
public interface Cylinder extends GriddedSurface {
}
