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

import org.opengis.go.display.primitive.Graphic;

/**
 * The <code>GraphicStyle</code> interface encapsulates the drawing attributes
 * that can be applied to any Graphic implementation. It provides attributes
 * for specifying SLD-based line symbolizer, polygon symbolizer, point symbolizer, 
 * text symbolizer, as well as style inheritance, viewability, highlight, and 
 * editability.  Not every attribute will necessarily be used by every type of 
 * <code>Graphic</code>.
 * 
 * @version $Revision$, $Date$
 * @author Open GIS Consortium, Inc.
 */
public interface GraphicStyle {
	
	/**
	 * Editability attribute name.
	 */
	public static final String EDITABILITY = "EDITABILITY";
	
	/**
	 * Inheritance attribute name.
	 */
	public static final String INHERITANCE = "INHERITANCE";
	
	/**
	 * Highlight attribute name.
	 */
	public static final String HIGHLIGHT = "HIGHLIGHT";
	
	/**
	 * Viewability attribute name.
	 */
	public static final String VIEWABILITY = "VIEWABILITY";
	
	/**
	 * LineSymbolizer attribute name.
	 */
	public static final String LINESYMBOLIZER = "LINESYMBOLIZER";
	
	/**
	 * PolygonSymbolizer attribute name.
	 */
	public static final String POLYGONSYMBOLIZER = "POLYGONSYMBOLIZER";
	
	/**
	 * PointSymbolizer attribute name.
	 */
	public static final String POINTSYMBOLIZER = "POINTSYMBOLIZER";
	
	/**
	 * TextSymbolizer attribute name.
	 */
	public static final String TEXTSYMBOLIZER = "TEXTSYMBOLIZER";
	
	//
	// External configuration code.
	
	/**
	 * Returns the <code>Graphic</code> object associated with this <code>GraphicStyle</code> object.
	 * @return the Graphic associtated with this GraphicStyle.
	 */
	public Graphic getGraphic();

	/**
	 * Returns the given implementation-specific hint for the given name.
	 * @param hintName
	 * @return the hint object associated with the hint name.
	 */
	public Object getImplHint(String hintName);

	/**
	 * Sets the given implementation-specific hint for the given name.
	 * @param hintname
	 * @param hint Nanme the name of the hint.
	 */
	public void setImplHint(String hintname, Object hint);

	/**
	 * Sets the properties of this <code>GraphicStyle</code> from the
	 * properties of the specified <code>GraphicStyle</code>.
	 * @param style the GraphicStyle used to set this GraphicStyle properties.
	 * 
	 */
	public void setPropertiesFrom(GraphicStyle style);


    //
    // Generic value-based accessors and mutators.
    
	/**
	 * Returns the attribute value specified by the attribute name.
	 * @param name the name of the attribute.
	 * @return the value of the attribute.
	 */
	public Object getValue(String name);

	/**
	 * Returns whether the attribute value has been set.
	 * @param name the name of the attribute.
	 * @return true if the value has been set, false otherwise.
	 */
	public boolean isValueSet(String name);

	/**
	 * Sets the attribute value specified by the attribute name.
	 * @param name the name of the attribute.
	 * @param value
	 */
	public void setValue(String name, Object value);

	/**
	 * Sets the fact that the attribute value has been set.
	 * @param name the name of the attribute.
	 * @param flag true if the value has been set, false otherwise.
	 * 
	 */
	public void setValueSet(String name, boolean flag);


    //
    // GO-1 specific styling values.
    
	/**
	 * Returns the Editability object.
	 * @return the Editability object.
	 */
	public Editability getEditability();

	/**
	 * Returns whether the Editability object has been set.
	 * @return true if the Editability object has been set, false otherwise.
	 */
	public boolean isEditabilitySet();

	/**
	 * Sets the Editability object.
	 * @param object the Editability object.
	 * 
	 */
	public void setEditability(Editability object);

	/**
	 * Sets the fact that the Editability object has been set.
	 * @param flag true if the Editability object has been set, false otherwise.
	 * 
	 */
	public void setEditabilitySet(boolean flag);

	/**
	 * Returns the Highlight object.
	 * @return the Highlight object.
	 */
	public Highlight getHighlight();

	/**
	 * Returns whether the Highlight object has been set.
	 * @return true if the Highlight object has been set, false otherwise.
	 */
	public boolean isHighlightSet();

	/**
	 * Sets the Highlight object.
	 * @param object the Highlight object.
	 * 
	 */
	public void setHighlight(Highlight object);

	/**
	 * Sets the fact that the Highlight object has been set.
	 * @param flag true if the Highlight object has been set, false otherwise.
	 * 
	 */
	public void setHighlightSet(boolean flag);

	/**
	 * Returns the Inheritance object.
	 * @return the Inheritance object.
	 */
	public Inheritance getInheritance();

	/**
	 * Returns whether the Inheritance object has been set.
	 * @return true if the Inheritance object has been set, false otherwise.
	 */
	public boolean isInheritanceSet();

	/**
	 * Sets the Inheritance object.
	 * @param object the Inheritance object.
	 * 
	 */
	public void setInheritance(Inheritance object);

	/**
	 * Sets the fact that the Inheritance object has been set.
	 * @param flag true if the Inheritance object has been set, false otherwise.
	 * 
	 */
	public void setInheritanceSet(boolean flag);

	/**
	 * Returns the Viewability object.
	 * @return the Viewability object.
	 */
	public Viewability getViewability();

	/**
	 * Returns whether the Viewability object has been set.
	 * @return true if the Viewability object has been set, false otherwise.
	 */
	public boolean isViewabilitySet();

	/**
	 * Sets the Viewability object.
	 * @param object the Viewability object.
	 * 
	 */
	public void setViewability(Viewability object);
    
    /**
     * Sets the fact that the Viewability object has been set.
     * @param flag true if the Viewability object has been set, false otherwise.
     * 
     */
	public void setViewabilitySet(boolean flag);
	
	
	//
	// Standard SLD Symbolizers
	
	/**
	 * Returns the PointSymbolizer object.
	 * @return the PointSymbolizer object.
	 */
	public PointSymbolizer getPointSymbolizer();

	/**
	 * Returns whether the PointSymbolizer object has been set.
	 * @return true if the PointSymbolizer object has been set, false otherwise.
	 */
	public boolean isPointSymbolizerSet();

	/**
	 * Sets the PointSymbolizer object.
	 * @param object the PointSymbolizer object.
	 * 
	 */
	public void setPointSymbolizer(PointSymbolizer object);

	/**
	 * Sets the fact that the PointSymbolizer object has been set.
	 * @param flag true if the PointSymbolizer object has been set, false otherwise.
	 * 
	 */
	public void setPointSymbolizerSet(boolean flag);
	
	/**
	 * Returns the PolygonSymbolizer object.
	 * @return the PolygonSymbolizer object.
	 */
	public PolygonSymbolizer getPolygonSymbolizer();

	/**
	 * Returns whether the PolygonSymbolizer object has been set.
	 * @return true if the PolygonSymbolizer object has been set, false otherwise.
	 */
	public boolean isPolygonSymbolizerSet();

	/**
	 * Sets the PolygonSymbolizer object.
	 * @param object the PolygonSymbolizer object.
	 * 
	 */
	public void setPolygonSymbolizer(PolygonSymbolizer object);

	/**
	 * Sets the fact that the PolygonSymbolizer object has been set.
	 * @param flag true if the PolygonSymbolizer object has been set, false otherwise.
	 * 
	 */
	public void setPolygonSymbolizerSet(boolean flag);
	
	/**
	 * Returns the TextSymbolizer object.
	 * @return the TextSymbolizer object.
	 */
	public TextSymbolizer getTextSymbolizer();

	/**
	 * Returns whether the TextSymbolizer object has been set.
	 * @return true if the TextSymbolizer object has been set, false otherwise.
	 */
	public boolean isTextSymbolizerSet();

	/**
	 * Sets the TextSymbolizer object.
	 * @param object the TextSymbolizer object.
	 * 
	 */
	public void setTextSymbolizer(TextSymbolizer object);

	/**
	 * Sets the fact that the TextSymbolizer object has been set.
	 * @param flag true if the TextSymbolizer object has been set, false otherwise.
	 * 
	 */
	public void setTextSymbolizerSet(boolean flag);
	
	/**
	 * Returns the LineSymbolizer object.
	 * @return the LineSymbolizer object.
	 */
	public LineSymbolizer getLineSymbolizer();

	/**
	 * Returns whether the LineSymbolizer object has been set.
	 * @return true if the LineSymbolizer object has been set, false otherwise.
	 */
	public boolean isLineSymbolizerSet();

	/**
	 * Sets the LineSymbolizer object.
	 * @param object the LineSymbolizer object.
	 * 
	 */
	public void setLineSymbolizer(LineSymbolizer object);

	/**
	 * Sets the fact that the LineSymbolizer object has been set.
	 * @param flag true if the LineSymbolizer object has been set, false otherwise.
	 * 
	 */
	public void setLineSymbolizerSet(boolean flag);
	
}
