package org.opengis.sld;

/**
 * Element that indicates that polygons should be filled by repeatedly drawing
 * a Graphic in the interior.
 */
public interface GraphicFill {
    public Graphic getGraphic();
    public void setGraphic(Graphic graphic);
}
