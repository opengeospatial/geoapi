/*$************************************************************************************************
 **
 ** $Id: LineSymbol.java 831 2006-05-01 00:27:23Z Desruisseaux $
 **
 ** $URL: https://svn.sourceforge.net/svnroot/geoapi/branches/sld-1.1/geoapi/src/main/java/org/opengis/sld/LineSymbol.java $
 **
 ** Copyright (C) 2005 Open GIS Consortium, Inc.
 ** All Rights Reserved. http://www.opengis.org/legal/
 **
 *************************************************************************************************/
package org.opengis.sld;

// Annotations
import org.opengis.annotation.XmlElement;


/**
 * Gives directions for how to draw lines on a map.
 *
 * @version <A HREF="http://portal.opengeospatial.org/files/?artifact_id=1188">Implementation specification 1.0</A>
 * @author Chris Dillard (SYS Technologies)
 * @since GeoAPI 2.0
 */
@XmlElement("LineSymbol")
public interface MappedColorSymbol extends Symbol {
    /**
     * Returns the object containing all the information necessary to draw
     * styled lines.
     */
    @XmlElement("Stroke")
    Stroke getStroke();

    /**
     * Sets the object containing all the information necessary to draw styled lines.
     * See {@link #getStroke} for details.
     */
    @XmlElement("Stroke")
    void setStroke(Stroke s);
}
