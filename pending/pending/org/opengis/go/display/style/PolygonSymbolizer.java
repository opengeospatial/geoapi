/**************************************************************************************************
 **
 ** $Id$
 **
 ** $Source$
 **
 ** Copyright (C) 2003 Open GIS Consortium, Inc. All Rights Reserved. http://www.opengis.org/Legal/
 **
 *************************************************************************************************/

package org.opengis.go.display.style;

import java.awt.Color;

/**
 * The <code>PolygonSymbolizer</code> interface encapsulates the style data applicable to
 * <code>Graphic</code>s that are of type Polygon in the sense of SLD (OGC 02-070).
 *
 * @author Open GIS Consortium, Inc.
 * @version $Revision$, $Date$
 */
public interface PolygonSymbolizer {
	
	/**
	 * Default dash array value.
	 */
	public static final DashArray DEFAULT_STROKE_DASH_ARRAY = (DashArray) LinePattern.NONE;
    
	/**
	 * Default dash offset value.
	 */
	public static final float DEFAULT_STROKE_DASH_OFFSET = 0.f;

	/**
	 * Default line cap value.
	 */
	public static final LineCap DEFAULT_STROKE_LINE_CAP = LineCap.BUTT;
    
	/**
	 * Default line gap value.
	 */
	public static final float DEFAULT_STROKE_LINE_GAP = 10.f;

	/**
	 * Default line join value.
	 */
	public static final LineJoin DEFAULT_STROKE_LINE_JOIN = LineJoin.BEVEL;
    
	/**
	 * Default line style value.
	 */
	public static final LineStyle DEFAULT_STROKE_LINE_STYLE = LineStyle.SINGLE;
    
	/**
	 * Default stroke color value.
	 */
	public static final Color DEFAULT_STROKE_COLOR = Color.BLACK;
   
	/**
	 * Default stroke opacity value.
	 */
	public static final float DEFAULT_STROKE_OPACITY = 1.f;
   
	/**
	 * Default stroke width value.
	 */
	public static final float DEFAULT_STROKE_WIDTH = 1.f;
    
	/**
	 * Default fill color value.
	 */
	public static final Color DEFAULT_FILL_COLOR = Color.GRAY;

	/**
	 * Default fill background color value.
	 */
	public static final Color DEFAULT_FILL_BACKGROUND_COLOR = Color.BLACK;

	/**
	 * Default fill gradient points value.
	 */
	public static final float[] DEFAULT_FILL_GRADIENT_POINTS = new float[2];

	/**
	 * Default fill opacity value.
	 */
	public static final float DEFAULT_FILL_OPACITY = 1.f;

	/**
	 * Default fill pattern value.
	 */
	public static final FillPattern DEFAULT_FILL_PATTERN = FillPattern.NONE;

	/**
	 * Default fill style value.
	 */
	public static final FillStyle DEFAULT_FILL_STYLE = FillStyle.SOLID;
    
	/**
	 * PolygonSymbolizer fill attribute name.
	 */
	public static final String FILL = "POLYGONSYMBOLIZER_FILL";
     
	/**
	 * PolygonSymbolizer stroke attribute name.
	 */
	public static final String STROKE = "POLYGONSYMBOLIZER_STROKE";
    
	/**
	 * Returns the Stroke object.
	 * @return the Stroke object.
	 */
	public Stroke getStroke();

	/**
	 * Returns whether the Stroke object has been set.
	 * @return true if the Stroke object has been set, false otherwise.
	 */
	public boolean isStrokeSet();

	/**
	 * Sets the Stroke object.
	 * @param object the Stroke object.
	 * 
	 */
	public void setStroke(Stroke object);

	/**
	 * Sets the fact that the Stroke object has been set.
	 * @param flag true if the Stroke object has been set, false otherwise.
	 * 
	 */
	public void setStrokeSet(boolean flag);
	
	/**
	 * Returns the Fill object.
	 * @return the Fill object.
	 */
	public Fill getFill();

	/**
	 * Returns whether the Fill object has been set.
	 * @return true if the Fill object has been set, false otherwise.
	 */
	public boolean isFillSet();

	/**
	 * Sets the Fill object.
	 * @param object the Fill object.
	 * 
	 */
	public void setFill(Fill object);

	/**
	 * Sets the fact that the Fill object has been set.
	 * @param flag true if the Fill object has been set, false otherwise.
	 * 
	 */
	public void setFillSet(boolean flag);

}
