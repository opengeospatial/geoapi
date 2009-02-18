/*$************************************************************************************************
 **
 ** $Id$
 **
 ** $URL$
 **
 ** Copyright (C) 2005 Open GIS Consortium, Inc.
 ** All Rights Reserved. http://www.opengis.org/legal/
 **
 *************************************************************************************************/
package org.opengis.sld;

import org.opengis.annotation.Extension;


/**
 * Abstract base class for the two different classes that specify the placement of text
 * in a style, namely {@link PointPlacement} and {@link LinePlacement}.
 * For a {@code PointPlacement}, the anchor point of the label and a linear displacement
 * from the point can be specified, to allow a graphic symbol to be plotted directly at
 * the point. This might be useful to label a city, for example. For a {@code LinePlacement},
 * a perpendicular offset can be specified, to allow the line itself to be plotted also. This
 * might be useful for labelling a road or a river, for example.
 *
 * @version <A HREF="http://portal.opengeospatial.org/files/?artifact_id=1188">Implementation specification 1.0</A>
 * @author Chris Dillard (SYS Technologies)
 * @since GeoAPI 2.0
 * 
 * @deprecated no replacement
 */
public interface TextPlacement {
    /**
     * Accepts a visitor.  Implementations of all subinterfaces must have with a
     * method whose content is the following:
     * <pre>return visitor.{@linkplain StyleVisitor#visit visit}(this, extraData);</pre>
     */
    @Extension
    Object accept(StyleVisitor visitor, Object extraData);
}
