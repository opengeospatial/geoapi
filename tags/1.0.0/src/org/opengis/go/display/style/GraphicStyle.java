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

// OpenGIS direct dependencies
import org.opengis.go.display.primitive.Graphic;

/**
 * Encapsulates the drawing attributes that can be applied to any {@link Graphic}
 * implementation. It provides attributes for specifying SLD-based line symbolizer,
 * polygon symbolizer, point symbolizer, text symbolizer, as well as style inheritance,
 * viewability, highlight, and editability.  Not every attribute will necessarily
 * be used by every type of <code>Graphic</code>.
 * 
 * @version $Revision$, $Date$
 * @author <A HREF="http://www.opengis.org">OpenGIS&reg; consortium</A>
 */
public interface GraphicStyle extends Editability, Highlight, Symbology, Viewability, 
        LineSymbolizer, PointSymbolizer, PolygonSymbolizer, TextSymbolizer {
    
    //*************************************************************************
    //  Static Fields
    //*************************************************************************
    
    //**  String names for the symbolizers  **
    
    /**  <code>Editability</code> attribute name. */
    public static final String EDITABILITY = "EDITABILITY";
    
    /**  <code>Highlight</code> attribute name.  */
    public static final String HIGHLIGHT = "HIGHLIGHT";
    
    /** <code>Symbology</code> attribute name.  */
    public static final String SYMBOLOGY = "SYMBOLOGY";
    
    /** Viewability attribute name. */
    public static final String VIEWABILITY = "VIEWABILITY";
    
    /**  <code>LineSymbolizer</code> attribute name.  */
    public static final String LINE_SYMBOLIZER = "LINE_SYMBOLIZER";
    
    /**  <code>PolygonSymbolizer</code> attribute name.  */
    public static final String POLYGON_SYMBOLIZER = "POLYGON_SYMBOLIZER";
    
    /**  <code>PointSymbolizer</code> attribute name.  */
    public static final String POINT_SYMBOLIZER = "POINT_SYMBOLIZER";
    
    /**  <code>TextSymbolizer</code> attribute name.  */
    public static final String TEXT_SYMBOLIZER = "TEXT_SYMBOLIZER";
    
    //**  Inheritance property names **
    
    /**  Inherit style from parent attribute name.  */
    public static final String INHERIT_STYLE_FROM_PARENT = "INHERIT_STYLE_FROM_PARENT";

    /**  Override aggregated graphics attribute name.  */
    public static final String OVERRIDE_AGGREGATED_GRAPHICS = "OVERRIDE_AGGREGATED_GRAPHICS";
    
    //**  Default Inheritance property values  **
    
    /**  Default inherit style from parent value.  */
    public static final boolean DEFAULT_INHERIT_STYLE_FROM_PARENT = false;

    /**  Default override aggregated graphics value.  */
    public static final boolean DEFAULT_OVERRIDE_AGGREGATED_GRAPHICS = false;
                       
    //*************************************************************************
    //  Methods
    //*************************************************************************

    //**  External configuration code.  **
    
    /**
     * Returns the <code>Graphic</code> object associated with this
     * <code>GraphicStyle</code> object.
     *
     * @return the <code>Graphic</code> associtated with this <code>GraphicStyle</code>.
     */
    public Graphic getGraphic();
    
    /**
     * Returns the given implementation-specific hint for the given name.
     *
     * @param hintName The hint key.
     * @return the hint object associated with the hint name.
     *
     * @revisit Why not <code>getRenderingHint</code> for consistency
     *          with current J2SE usage? Then, the key type should
     *          be a {@link java.awt.RenderingHint.Key} type.
     */
    public Object getImplHint(String hintName);
    
    /**
     * Sets the given implementation-specific hint for the given name.
     *
     * @param hintname The hint key.
     * @param hint The hint.
     *
     * @revisit Why not <code>setRenderingHint</code> for consistency
     *          with current J2SE usage? Then, the key type should
     *          be a {@link java.awt.RenderingHint.Key} type.
     */
    public void setImplHint(String hintname, Object hint);
    
    /**
     * Sets the properties of this <code>GraphicStyle</code> from the
     * properties of the specified <code>GraphicStyle</code>.
     * 
     * @param style the <code>GraphicStyle</code> used to set this
     *        <code>GraphicStyle</code> properties.
     */
    public void setPropertiesFrom(GraphicStyle style);
    
    //**  Accessors/Mutators for Symbolizers  **
    
    /**
     * Returns an <code>Editability</code> object with the same values as this 
     * GraphicStyle's <code>Editability</code> properties.  Setting values on the returned 
     * <code>Editability</code> should not affect this <code>GraphicStyle</code>'s values
     * @return the <code>Editability</code> object.
     */
    public Editability getEditability();
    
    /**
     * Sets the <code>Editability</code> properties on this <code>GraphicStyle</code> from the given
     * <code>Editability</code> object.
     * @param object the <code>Editability</code> object.
     */
    public void setEditability(Editability object);
    
    /**
     * Returns a <code>Highlight</code> object with the same values as this <code>GraphicStyle</code>'s
     * <code>Highlight</code> properties.  Setting values on the returned <code>Highlight</code> should
     * not affect this <code>GraphicStyle</code>'s values.
     * @return the <code>Highlight</code> object.
     */
    public Highlight getHighlight();
    
    /**
     * Sets the <code>Highlight</code> properties on this <code>GraphicStyle</code> from the given
     * <code>Highlight</code> object.
     * @param object the <code>Highlight</code> object.
     */
    public void setHighlight(Highlight object);
    
    /**
     * Returns a <code>Symbology</code> object with the same values as this <code>GraphicStyle</code>'s
     * <code>Symbology</code> properties.  Setting values on the returned <code>Symbology</code> should
     * not affect this <code>GraphicStyle</code>'s values.
     * @return the <code>Symbology</code> object.
     */
    public Symbology getSymbology();
    
    /**
     * Sets the <code>Symbology</code> properties on this <code>GraphicStyle</code> from the given
     * <code>Symbology</code> object.
     * @param object the <code>Symbology</code> object.
     */
    public void setSymbology(Symbology object);
    
    /**
     * Returns a <code>Viewability</code> object with the same values as this <code>GraphicStyle</code>'s
     * <code>Viewability</code> properties.  Setting values on the returned Viewability
     * should not affect this <code>GraphicStyle</code>'s values.
     * @return the <code>Viewability</code> object.
     */
    public Viewability getViewability();
    
    /**
     * Sets the <code>Viewability</code> properties on this <code>GraphicStyle</code> from the given
     * <code>Viewability</code> object.
     * @param object the <code>Viewability</code> object.
     */
    public void setViewability(Viewability object);    
    
    /**
     * Returns a <code>LineSymbolizer</code> object with the same values as this
     * GraphicStyle's <code>LineSymbolizer</code> properties.  Setting values on the
     * returned <code>LineSymbolizer</code> should not affect this <code>GraphicStyle</code>'s values.
     * @return the <code>LineSymbolizer</code> object.
     */
    public LineSymbolizer getLineSymbolizer();

    /**
     * Sets the <code>LineSymbolizer</code> properties on this <code>GraphicStyle</code> from the given
     * <code>LineSymbolizer</code> object.
     * @param object the <code>LineSymbolizer</code> object.
     */
    public void setLineSymbolizer(LineSymbolizer object);
    
    /**
     * Returns a <code>PointSymbolizer</code> object with the same values as this 
     * GraphicStyle's <code>PointSymbolizer</code> properties.  Setting values on the 
     * returned <code>PointSymbolizer</code> should not affect this <code>GraphicStyle</code>'s values.
     * @return the <code>PointSymbolizer</code> object.
     */
    public PointSymbolizer getPointSymbolizer();

    /**
     * Sets the <code>PointSymbolizer</code> properties on this <code>GraphicStyle</code> from the given
     * <code>PointSymbolizer</code> object.
     * @param object the <code>PointSymbolizer</code> object.
     */
    public void setPointSymbolizer(PointSymbolizer object);

    /**
     * Returns a <code>PolygonSymbolizer</code> object with the same values as this
     * GraphicStyle's <code>PolygonSymbolizer</code> properties.  Setting values on the
     * returned <code>PolygonSymbolizer</code> should not affect this <code>GraphicStyle</code>'s values.
     * @return the <code>PolygonSymbolizer</code> object.
     */
    public PolygonSymbolizer getPolygonSymbolizer();

    /**
     * Sets the <code>PolygonSymbolizer</code> properties on this <code>GraphicStyle</code> from the 
     * given <code>PolygonSymbolizer</code> object.
     * @param object the <code>PolygonSymbolizer</code> object.
     */
    public void setPolygonSymbolizer(PolygonSymbolizer object);

    /**
     * Returns a <code>TextSymbolizer</code> object with the same values as this
     * GraphicStyle's <code>TextSymbolizer</code> properties.  Setting values on the
     * returned <code>TextSymbolizer</code> should not affect this <code>GraphicStyle</code>'s values.
     * @return the <code>TextSymbolizer</code> object.
     */
    public TextSymbolizer getTextSymbolizer();

    /**
     * Sets the <code>TextSymbolizer</code> properties on this <code>GraphicStyle</code> from the given
     * <code>TextSymbolizer</code> object.
     * @param object the <code>TextSymbolizer</code> object.
     */
    public void setTextSymbolizer(TextSymbolizer object);

    //**  Inheritance  **
    
    /**
     * Returns the inherit style from parent value.
     * @return the inherit style from parent value.
     */
    public boolean isInheritingStyleFromParent();
    
    /**
     * Sets the inherit style from parent value.
     * @param inheritStyleFromParent the inherit style from parent value.
     */    
    public void setInheritingStyleFromParent(boolean inheritStyleFromParent);
    
    /**
     * Returns the override aggregated graphics value.
     * @return the override aggregated graphics value.
     */
    public boolean isOverridingAggregatedGraphics();
    
    /**
     * Sets the override aggregated graphics value.
     * @param overrideAggregatedGraphics the override aggregated graphics value.
     */    
    public void setOverridingAggregatedGraphics(boolean overrideAggregatedGraphics);
           
}

