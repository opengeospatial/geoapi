package org.opengis.go.display.primitive;

import org.opengis.go.display.style.Symbology;
import org.opengis.spatialschema.geometry.DirectPosition;
import org.opengis.spatialschema.geometry.geometry.PointArray;

/**
 * The <code>GraphicSymbol</code> interface is used to draw a symbolic graphic
 * on the canvas.  A GraphicSymbol can be defined as a point, line, area, or
 * other type of graphic.  The symbology attributes of the graphic define how
 * the graphic is rendered.  The symbol can be a complex rendering or a simple
 * rendering.  Each graphic symbol defines 1 or more anchor points.  These
 * anchor points define special positional locations in the symbol.  The 
 * required number, order and position of anchor points are dependent on what 
 * type of symbology you are using.
 */
public interface GraphicSymbol extends Graphic {
    
    /**
     * Appends an anchor point to the end of the list of anchors.  An anchor 
     * point is a point of interest in a graphic symbol that is used in creating
     * the symbol.
     * @param anchor the new position to add as an anchor point
     */
    public void addAnchorPoint(DirectPosition anchor);
    
    /**
     * Removes the anchor point located at the given index.  
     * @param index the index of the anchor point to remove.
     * @throws IndexOutOfBoundsException if the index does not fall in the range
     *         of anchor points.
     */
    public void deleteAnchorPoint(int index) throws IndexOutOfBoundsException;
    
    /**
     * Gets an anchor point located at the given index.
     * @param index the index of the anchor point to get
     * @return the anchor point position
     * @throws IndexOutOfBoundsException if the index does not fall in the range
     *         of anchor points.
     */
    public DirectPosition getAnchorPoint(int index) 
        throws IndexOutOfBoundsException;
    
    /**
     * Inserts an anchor point at the given index.  All other anchor points at
     * index and greater will increase in index location by one.  The number of
     * anchor points will increase by 1 as a result of this operation.
     * @param index the index of where to insert the anchor point
     * @param anchor the anchor point to insert
     * @throws IndexOutOfBoundsException if the index does not fall in the range
     *         of anchor points.
     */
    public void insertAnchorPoint(int index, DirectPosition anchor) 
        throws IndexOutOfBoundsException;
    
    /**
     * Sets an anchor point at the given index.  The current anchor point that
     * is located at the index will be replaced with the anchor point passed in.
     * The number of anchor points will stay the same since no new points will
     * be added
     * @param index the index of where to set the anchor point
     * @param anchor the anchor point to set
     * @throws IndexOutOfBoundsException if the index does not fall in the range
     *         of anchor points.
     */
    public void setAnchorPoint(int index, DirectPosition anchor) 
        throws IndexOutOfBoundsException;
    
    /**
     * Sets the complete list of anchor points for this graphic symbol.  All 
     * existing anchor points will be removed as a result of this operation.
     * @param anchors the new list of anchors
     */
    public void setAnchorPoints(DirectPosition[] anchors);
    
    /**
     * Gets the current list of anchor points for this graphic symbol.
     * @return the ordered list of anchor points
     */
    public DirectPosition[] getAnchorPoints();
    
    /**
     * Sets the complete list of anchor points for this graphic symbol.  All 
     * existing anchor points will be removed as a result of this operation.
     * @param pointArray the new list of anchors
     */
    public void setAnchorPointArray(PointArray pointArray);
    
    /**
     * Gets the current list of anchor points for this graphic symbol.
     * @return the ordered list of anchor points
     */
    public PointArray getAnchorPointArray();
    
    /**
     * Gets the symbology settings object for the given symbology name.  If the
     * symbology is not supported, it will return null.
     * @param symbologyName the name of the symbology properties to get
     * @return the Symbology instance to set properties on the requested type 
     *         of symbology
     */
    public Symbology getSymbology(String symbologyName);
    
    /**
     * Gets the active symbology name that is being used to render this
     * graphic symbol.  An active symbology will use the settings that were
     * set on the Symbology object that will define how the graphic is displayed
     * @return the active symbology name
     */
    public String getActiveSymbology();
    
    /**
     * Sets the active symbology name that is being used to render this
     * graphic symbol.  
     * @param symbologyName the new active symbology
     * @throws IllegalArgumentException if the symbology isn't supported
     */
    public void setActiveSymbology(String symbologyName) throws IllegalArgumentException;
    
    /**
     * Gets the active symbology name that is being used to render this
     * graphic symbol.  An active symbology will use the settings that were
     * set on the Symbology object that will define how the graphic is displayed
     * @return the active symbology version string
     */
    public String getSymbologyVersion();

    /**
     * Sets the active symbology version that is being supported by this
     * implementation.
     * @param symbologyVersion the symbology verion supported
     * @throws IllegalArgumentException if the symbology doesn't support versions
     */
    public void setSymbologyVersion(String symbologyVersion) throws IllegalArgumentException;    
}
