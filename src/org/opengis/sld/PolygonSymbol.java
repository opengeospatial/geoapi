package org.opengis.sld;

/**
 * Implementations of this interface hold the information that indicates how to
 * draw the lines and the interior of polygons.
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
