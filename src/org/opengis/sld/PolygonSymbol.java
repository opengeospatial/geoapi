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
 * Holds the information that indicates how to draw the lines and the interior of polygons.
 *
 * @author <A HREF="http://www.opengis.org">OpenGIS&reg; consortium</A>
 * @version <A HREF="http://www.opengis.org/docs/02-070.pdf">Implementation specification 1.0</A>
 * @since GeoAPI 1.1
 */
public interface PolygonSymbol extends Symbol {
    /**
     * Returns the object containing all the information necessary to draw
     * styled lines.  This is used for the edges of polygons.
     */
    public Stroke getStroke();

    /**
     * Sets the object containing all the information necessary to draw styled
     * lines.  This is used for the edges of polygons.
     */
    public void setStroke(Stroke s);

    /**
     * Returns the object that holds the information about how the interior of
     * polygons should be filled.  This may be null if the polygons are not to
     * be filled at all.
     */
    public Fill getFill();

    /**
     * Sets the object the holds the information about how the interior of
     * polygons should be filled.  This may be set to null if the polygons are
     * not to be filled at all.
     */
    public void setFill(Fill f);
}
