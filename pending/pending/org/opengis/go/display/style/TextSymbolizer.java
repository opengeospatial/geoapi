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
import java.awt.Font;

/**
 * The <code>TextSymbolizer</code> interface encapsulates the style data applicable to
 * <code>Graphic</code>s that are of type Text in the sense of SLD (OGC 02-070).
 *
 * @author Open GIS Consortium, Inc.
 * @version $Revision$, $Date$
 */
public interface TextSymbolizer {
	/**
	 * Default fill color value.
	 */
	public static final Color DEFAULT_FILL_COLOR = Color.BLACK;

	/**
	 * Default fill background color value.
	 */
	public static final Color DEFAULT_FILL_BACKGROUND_COLOR = Color.WHITE;

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
	 * Default show label.
	 */
	public static final boolean DEFAULT_SHOW_LABEL = false;
    
	/**
	 * Default halo radius value.
	 */
	public static final float DEFAULT_HALO_RADIUS = 1.f;
	
	/**
	 * TextSymbolizer mark attribute name.
	 */
	public static final String HALO = "TEXTSYMBOLIZER_HALO";
    
	/**
	 * TextSymbolizer fill attribute name.
	 */
	public static final String FILL = "TEXTSYMBOLIZER_FILL";
     
	/**
	 * TextSymbolizer stroke attribute name.
	 */
	public static final String STROKE = "TEXTSYMBOLIZER_STROKE";
    
	/**
	 * TextSymbolizer stroke attribute name.
	 */
	public static final String LABELPLACEMENT = "TEXTSYMBOLIZER_LABELPLACEMENT";
	
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
	
	/**
	 * Returns the Font object.
	 * @return the Font object.
	 */
	public Font getFont();

	/**
	 * Returns whether the Font object has been set.
	 * @return true if the Font object has been set, false otherwise.
	 */
	public boolean isFontSet();

	/**
	 * Sets the Font object.
	 * @param object the Font object.
	 * 
	 */
	public void setFont(Font object);

	/**
	 * Sets the fact that the Font object has been set.
	 * @param flag true if the Font object has been set, false otherwise.
	 * 
	 */
	public void setFontSet(boolean flag);
	
	/**
	 * Returns the LabelPlacement object.
	 * @return the LabelPlacement object.
	 */
	public LabelPlacement getLabelPlacement();

	/**
	 * Returns whether the LabelPlacement object has been set.
	 * @return true if the LabelPlacement object has been set, false otherwise.
	 */
	public boolean isLabelPlacementSet();

	/**
	 * Sets the LabelPlacement object.
	 * @param object the LabelPlacement object.
	 * 
	 */
	public void setLabelPlacement(LabelPlacement object);

	/**
	 * Sets the fact that the LabelPlacement object has been set.
	 * @param flag true if the LabelPlacement object has been set, false otherwise.
	 * 
	 */
	public void setLabelPlacementSet(boolean flag);

	/**
	 * Returns the Halo object.
	 * @return the Halo object.
	 */
	public Halo getHalo();

	/**
	 * Returns whether the Halo object has been set.
	 * @return true if the Halo object has been set, false otherwise.
	 */
	public boolean isHaloSet();

	/**
	 * Sets the Halo object.
	 * @param object the Halo object.
	 * 
	 */
	public void setHalo(Halo object);

	/**
	 * Sets the fact that the Halo object has been set.
	 * @param flag true if the Halo object has been set, false otherwise.
	 * 
	 */
	public void setHaloSet(boolean flag);
	
}
