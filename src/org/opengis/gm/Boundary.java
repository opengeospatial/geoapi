/*
 * Copyright (C) 2003 Open GIS Consortium, Inc. All Rights Reserved. http://www.opengis.org/Legal/
 */
package org.opengis.gm;

// OpenGIS direct dependencies
import org.opengis.gm.complex.Complex;


/**
 * The abstract root data type for all the data types used to represent the boundary
 * of geometric objects is GM_Boundary (Figure 7). Any subclass of GM_Object will use
 * a subclass of GM_Boundary to represent its boundary through the operation GM_Object::boundary.
 * By the nature of geometry, boundary objects are cycles. GM_Boundary: {isCycle() =
 * TRUE} 
 *  
 * @author GeoAPI
 * @version 1.0
 */
public interface Boundary extends Complex {
}
