/**************************************************************************************************
 **
 ** $Id$
 **
 ** $Source$
 **
 ** Copyright (C) 2003 Open GIS Consortium, Inc. All Rights Reserved. http://www.opengis.org/Legal/
 **
 *************************************************************************************************/

package org.opengis.go.typical.geometry;

import org.opengis.go.geometry.BoundingPolygon;
import org.opengis.go.typical.coord.XY;

/**
 * The <code>XYBoundingPolygon</code> interface represents a
 * bounding polygon whose vertices are required to be XY objects.
 * This means that the values returned from the getVertices and
 * getVerticesXY method will always be instances of XY.  Also,
 * the values passed in to the setVertices methods must be
 * instances of XY.
 *
 * @author  Open GIS Consortium, Inc.
 * @version $Revision$, $Date$
 */
public interface XYBoundingPolygon extends BoundingPolygon {
	/**
	 * Returns a list of vertices of this object cast as XY
	 * instead of DirectPosition.
	 */
	public XY [] getVerticesXY();

	/**
	 * Sets the vertices of this object from an array of XY objects
	 * (rather than an array of DirectPosition objects).
	 */
	public void setVertices(XY [] vertices);
}

