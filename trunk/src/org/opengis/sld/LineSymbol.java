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

// Annotations
import org.opengis.annotation.XmlElement;


/**
 * Gives directions for how to draw lines on a map.
 *
 * @author <A HREF="http://www.opengis.org">OpenGIS&reg; consortium</A>
 * @version <A HREF="http://www.opengis.org/docs/02-070.pdf">Implementation specification 1.0</A>
 * @since GeoAPI 1.1
 */
@XmlElement("LineSymbolizer")
public interface LineSymbol extends Symbol {
    /**
     * Returns the object containing all the information necessary to draw
     * styled lines.
     */
    @XmlElement("Stroke")
    Stroke getStroke();

    /**
     * Sets the object containing all the information necessary to draw styled
     * lines.
     */
    @XmlElement("Stroke")
    void setStroke(Stroke s);
}
