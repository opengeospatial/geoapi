package org.opengis.sld;

/**
 * Instances of this interface give directions for how to draw lines on a map.
 */
public interface LineSymbol extends Symbol {
    /**
     * Returns the object containing all the information necessary to draw
     * styled lines.
     */
    public Stroke getStroke();

    /**
     * Sets the object containing all the information necessary to draw styled
     * lines.
     */
    public void setStroke(Stroke s);
}
