/*$************************************************************************************************
 **
 ** $Id$
 **
 ** $Source$
 **
 ** Copyright (C) 2005 Open GIS Consortium, Inc.
 ** All Rights Reserved. http://www.opengis.org/legal/
 **
 *************************************************************************************************/
package org.opengis.sld;

// Annotations
import org.opengis.annotation.XmlElement;


/**
 * Holds the information that indicates how to draw the lines and the interior of polygons.
 *
 * @version <A HREF="http://portal.opengeospatial.org/files/?artifact_id=1188">Implementation specification 1.0</A>
 * @author Chris Dillard (SYS Technologies)
 * @since GeoAPI 2.0
 */
@XmlElement("PolygonSymbolizer")
public interface PolygonSymbol extends Symbol {
    /**
     * Returns the object containing all the information necessary to draw
     * styled lines.  This is used for the edges of polygons.
     */
    @XmlElement("Stroke")
    Stroke getStroke();

    /**
     * Sets the object containing all the information necessary to draw styled lines.
     * This is used for the edges of polygons.
     * See {@link #getStroke} for details.
     */
    @XmlElement("Stroke")
    void setStroke(Stroke s);

    /**
     * Returns the object that holds the information about how the interior of
     * polygons should be filled.  This may be null if the polygons are not to
     * be filled at all.
     */
    @XmlElement("Fill")
    Fill getFill();

    /**
     * Sets the object the holds the information about how the interior of
     * polygons should be filled.  This may be null if the polygons are not
     * to be filled at all.
     * See {@link #getFill} for details.
     */
    @XmlElement("Fill")
    void setFill(Fill f);
}
