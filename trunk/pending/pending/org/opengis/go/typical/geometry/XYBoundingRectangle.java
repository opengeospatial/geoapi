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

import org.opengis.go.geometry.BoundingRectangle;
import org.opengis.go.typical.coord.XY;

/**
 * The <code>XYBoundingRectangle</code> class represents a bounding
 * rectangle whose corners are required to be XY points.  Implementations
 * of this interface must return instances of XY from the
 * <code>getTopLeft</code> and <code>getBottomRight</code> methods.
 * The parameters passed to <code>setTopLeft</code> and
 * <code>setBottomRight</code> must be XY instances as well.  As
 * a convenience, two additional methods are provided that remove the need
 * to cast the corner points to the appropriate class.
 *
 * @author  Open GIS Consortium, Inc.
 * @version $Revision$, $Date$
 */
public interface XYBoundingRectangle extends BoundingRectangle {
	/**
	 * Returns the top left corner of this bounding rectangle as
	 * an XY.
	 */
	public XY getTopLeftXY();

	/**
	 * Returns the bottom right corner of this bounding rectangle
	 * as an XY.
	 */
	public XY getBottomRightXY();
}
