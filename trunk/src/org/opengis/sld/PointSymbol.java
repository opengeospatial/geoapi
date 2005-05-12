/*$************************************************************************************************
 **
 ** $Id$
 **
 ** $Source$
 **
 ** Copyright (C) 2003 Open GIS Consortium, Inc. All Rights Reserved. http://www.opengis.org/Legal/
 **
 *************************************************************************************************/
package org.opengis.sld;


/**
 * Indicates how to draw point geometries on a map.
 *
 * @author <A HREF="http://www.opengis.org">OpenGIS&reg; consortium</A>
 * @version <A HREF="http://www.opengis.org/docs/02-070.pdf">Implementation specification 1.0</A>
 * @since GeoAPI 1.1
 */
public interface PointSymbol extends Symbol {
    /**
     * Returns the graphic that will be drawn at each point of the geometry.
     */
    public Graphic getGraphic();

    /**
     * Sets the graphic that will be drawn at each point of the geometry.
     */
    public void setGraphic(Graphic graphic);
}
