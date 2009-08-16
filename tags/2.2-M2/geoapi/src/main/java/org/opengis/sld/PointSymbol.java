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

import org.opengis.annotation.XmlElement;


/**
 * Indicates how to draw point geometries on a map.
 *
 * @version <A HREF="http://portal.opengeospatial.org/files/?artifact_id=1188">Implementation specification 1.0</A>
 * @author Chris Dillard (SYS Technologies)
 * @since GeoAPI 2.0
 * 
 * @deprecated use interfaces from style package : org.opengis.style.PointSymbolizer
 */
@XmlElement("PointSymbolizer")
public interface PointSymbol extends Symbol {
    /**
     * Returns the graphic that will be drawn at each point of the geometry.
     */
    @XmlElement("Graphic")
    Graphic getGraphic();

    /**
     * Sets the graphic that will be drawn at each point of the geometry.
     * See {@link #getGraphic} for details.
     */
    @XmlElement("Graphic")
    void setGraphic(Graphic graphic);
}