package org.opengis.sld;

/**
 * Instances of this interface indicate how to draw point geometries on a map.
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
