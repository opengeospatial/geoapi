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
import org.opengis.go.typical.coord.LatLonAlt;

/**
 * The <code>LatLonBoundingPolygon</code> interface represents a
 * bounding polygon whose vertices are required to be <code>LatLonAlt</code>
 * objects.  This means that the values returned from the
 * <code>getVertices</code> and <code>getVerticesLatLon</code> methods will
 * always be instances of <code>LatLonAlt</code>.  Also, the values passed in
 * to the <code>setVertices</code> method must always be instances of
 * <code>LatLonAlt</code>.
 *
 * @author  Open GIS Consortium, Inc.
 * @version $Revision$, $Date$
 */
public interface LatLonBoundingPolygon extends BoundingPolygon {
	/**
	 * Returns a list of vertices of this object cast as LatLonAlt
	 * instead of DirectPosition.
	 */
	public LatLonAlt[] getVerticesLatLon();

	/**
	 * Sets the vertices of this object from an array of LatLon objects
	 * (rather than an array of DirectPosition objects).
	 */
	public void setVertices(LatLonAlt[] vertices);
}

