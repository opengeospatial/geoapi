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
 * The <code>LineSymbolizer</code> interface encapsulates the style data applicable to
 * <code>Graphic</code>s that are of type Line in the sense of SLD (OGC 02-070).
 *
 * @author Open GIS Consortium, Inc.
 * @version $Revision$, $Date$
 */
public interface LineSymbolizer {
	/**
	 * Default begin arrow style value.
	 */
	public static final ArrowStyle DEFAULT_STROKE_BEGIN_ARROW_STYLE = ArrowStyle.NONE;
    
	/**
	 * Default end arrow style value.
	 */
	public static final ArrowStyle DEFAULT_STROKE_END_ARROW_STYLE = ArrowStyle.NONE;

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
	 * LineSymbolizer stroke attribute name.
	 */
	public static final String STROKE = "LINESYMBOLIZER_STROKE";
    
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

}
