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
 * @version 0.2
 * @author <A HREF="http://www.opengis.org">OpenGIS&reg; consortium</A>
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


    //
    // Generic value-based accessors and mutators.

    /**
     * Returns the attribute value specified by the attribute name.
     *
     * @param name the name of the attribute.
     * @return the value of the attribute.
     */
    public Object getValue(String name);

    /**
     * Returns whether the attribute value has been set.
     *
     * @param name the name of the attribute.
     * @return <code>true</code> if the value has been set,
     *         <code>false</code> otherwise.
     *
     * @revisit Why not just returns <code>null</code> from <code>getValue</code> if not set?
     */
    public boolean isValueSet(String name);

    /**
     * Sets the attribute value specified by the attribute name.
     *
     * @param name the name of the attribute.
     * @param value The value.
     */
    public void setValue(String name, Object value);

    /**
     * Sets the fact that the attribute value has been set.
     *
     * @param name the name of the attribute.
     * @param flag <code>true</code> if the value has been set,
     *        <code>false</code> otherwise.
     *
     * @revisit Why not a <code>clearValue</code> method instead? Or <code>setValue(null)</code>?
     *          This flag should be set to <code>true</code> automatically by <code>setValue</code>.
     */
    public void setValueSet(String name, boolean flag);


    //
    // GO-1 specific styling values.

    /**
     * Returns the <code>Editability</code> object.
     *
     * @return the <code>Editability</code> object.
     */
    public Editability getEditability();

    /**
     * Returns whether the <code>Editability</code> object has been set.
     *
     * @return <code>true</code> if the <code>Editability</code> object has been set,
     *         <code>false</code> otherwise.
     *
     * @revisit Why not just returns <code>null</code> from <code>getEditability</code> if not set?
     */
    public boolean isEditabilitySet();

    /**
     * Sets the <code>Editability</code> object.
     *
     * @param object the <code>Editability</code> object.
     */
    public void setEditability(Editability object);

    /**
     * Sets the fact that the <code>Editability</code> object has been set.
     *
     * @param flag <code>true</code> if the <code>Editability</code> object has been set,
     *        <code>false</code> otherwise.
     *
     * @revisit Why not a <code>clearEditability</code> method instead? Or <code>setEditability(null)</code>?
     *          This flag should be set to <code>true</code> automatically by <code>setEditability</code>.
     */
    public void setEditabilitySet(boolean flag);

    /**
     * Returns the <code>Highlight</code> object.
     *
     * @return the <code>Highlight</code> object.
     */
    public Highlight getHighlight();

    /**
     * Returns whether the <code>Highlight</code> object has been set.
     *
     * @return <code>true</code> if the <code>Highlight</code> object has been set,
     *         <code>false</code> otherwise.
     *
     * @revisit Why not just returns <code>null</code> from <code>getHighlight</code> if not set?
     */
    public boolean isHighlightSet();

    /**
     * Sets the <code>Highlight</code> object.
     *
     * @param object the <code>Highlight</code> object.
     */
    public void setHighlight(Highlight object);

    /**
     * Sets the fact that the <code>Highlight</code> object has been set.
     *
     * @param flag <code>true</code> if the <code>Highlight</code> object has been set,
     *             <code>false</code> otherwise.
     *
     * @revisit Why not a <code>clearHighlight</code> method instead? Or <code>setHighlight(null)</code>?
     *          This flag should be set to <code>true</code> automatically by <code>setHighlight</code>.
     */
    public void setHighlightSet(boolean flag);

    /**
     * Returns the <code>Inheritance</code> object.
     *
     * @return the <code>Inheritance</code> object.
     */
    public Inheritance getInheritance();

    /**
     * Returns whether the <code>Inheritance</code> object has been set.
     *
     * @return <code>true</code> if the <code>Inheritance</code> object has
     *         been set, <code>false</code> otherwise.
     *
     * @revisit Why not just returns <code>null</code> from <code>getInheritance</code> if not set?
     */
    public boolean isInheritanceSet();

    /**
     * Sets the <code>Inheritance</code> object.
     *
     * @param object the <code>Inheritance</code> object.
     */
    public void setInheritance(Inheritance object);

    /**
     * Sets the fact that the <code>Inheritance</code> object has been set.
     *
     * @param flag <code>true</code> if the <code>Inheritance</code> object
     *             has been set, <code>false</code> otherwise.
     *
     * @revisit Why not a <code>clearInheritance</code> method instead? Or <code>setInheritance(null)</code>?
     *          This flag should be set to <code>true</code> automatically by <code>setInheritance</code>.
     */
    public void setInheritanceSet(boolean flag);

    /**
     * Returns the <code>Viewability</code> object.
     *
     * @return the <code>Viewability</code> object.
     */
    public Viewability getViewability();

    /**
     * Returns whether the <code>Viewability</code> object has been set.
     *
     * @return <code>true</code> if the <code>Viewability</code> object has been set,
     *         <code>false</code> otherwise.
     *
     * @revisit Why not just returns <code>null</code> from <code>getViewability</code> if not set?
     */
    public boolean isViewabilitySet();

    /**
     * Sets the <code>Viewability</code> object.
     *
     * @param object the <code>Viewability</code> object.
     */
    public void setViewability(Viewability object);

    /**
     * Sets the fact that the <code>Viewability</code> object has been set.
     *
     * @param flag <code>true</code> if the <code>Viewability</code> object has been set,
     *             <code>false</code> otherwise.
     *
     * @revisit Why not a <code>clearViewability</code> method instead? Or <code>setViewability(null)</code>?
     *          This flag should be set to <code>true</code> automatically by <code>setViewability</code>.
     */
    public void setViewabilitySet(boolean flag);


    //
    // Standard SLD Symbolizers

    /**
     * Returns the <code>PointSymbolizer</code> object.
     *
     * @return the <code>PointSymbolizer</code> object.
     */
    public PointSymbolizer getPointSymbolizer();

    /**
     * Returns whether the <code>PointSymbolizer</code> object has been set.
     *
     * @return <code>true</code> if the <code>PointSymbolizer</code> object has been set,
     *         <code>false</code> otherwise.
     *
     * @revisit Why not just returns <code>null</code> from <code>getPointSymbolizer</code> if not set?
     */
    public boolean isPointSymbolizerSet();

    /**
     * Sets the <code>PointSymbolizer</code> object.
     *
     * @param object the <code>PointSymbolizer</code> object.
     */
    public void setPointSymbolizer(PointSymbolizer object);

    /**
     * Sets the fact that the <code>PointSymbolizer</code> object has been set.
     *
     * @param flag <code>true</code> if the <code>PointSymbolizer</code> object has been set,
     *             <code>false</code> otherwise.
     *
     * @revisit Why not a <code>clearPointSymbolizer</code> method instead? Or <code>setPointSymbolizer(null)</code>?
     *          This flag should be set to <code>true</code> automatically by <code>setPointSymbolizer</code>.
     */
    public void setPointSymbolizerSet(boolean flag);

    /**
     * Returns the <code>PolygonSymbolizer</code> object.
     *
     * @return the <code>PolygonSymbolizer</code> object.
     */
    public PolygonSymbolizer getPolygonSymbolizer();

    /**
     * Returns whether the <code>PolygonSymbolizer</code> object has been set.
     *
     * @return <code>true</code> if the <code>PolygonSymbolizer</code> object has been set,
     *         <code>false</code> otherwise.
     *
     * @revisit Why not just returns <code>null</code> from <code>getPolygonSymbolizer</code> if not set?
     */
    public boolean isPolygonSymbolizerSet();

    /**
     * Sets the <code>PolygonSymbolizer</code> object.
     *
     * @param object the <code>PolygonSymbolizer</code> object.
     */
    public void setPolygonSymbolizer(PolygonSymbolizer object);

    /**
     * Sets the fact that the <code>PolygonSymbolizer</code> object has been set.
     *
     * @param flag <code>true</code> if the <code>PolygonSymbolizer</code> object has
     *             been set, <code>false</code> otherwise.
     *
     * @revisit Why not a <code>clearPolygonSymbolizer</code> method instead? Or <code>setPolygonSymbolizer(null)</code>?
     *          This flag should be set to <code>true</code> automatically by <code>setPolygonSymbolizer</code>.
     */
    public void setPolygonSymbolizerSet(boolean flag);

    /**
     * Returns the <code>TextSymbolizer</code> object.
     *
     * @return the <code>TextSymbolizer</code> object.
     */
    public TextSymbolizer getTextSymbolizer();

    /**
     * Returns whether the <code>TextSymbolizer</code> object has been set.
     *
     * @return <code>true</code> if the <code>TextSymbolizer</code> object has
     *         been set, <code>false</code> otherwise.
     *
     * @revisit Why not just returns <code>null</code> from <code>getTextSymbolizer</code> if not set?
     */
    public boolean isTextSymbolizerSet();

    /**
     * Sets the <code>TextSymbolizer</code> object.
     *
     * @param object the <code>TextSymbolizer</code> object.
     */
    public void setTextSymbolizer(TextSymbolizer object);

    /**
     * Sets the fact that the <code>TextSymbolizer</code> object has been set.
     *
     * @param flag <code>true</code> if the <code>TextSymbolizer</code> object has
     *             been set, <code>false</code> otherwise.
     *
     * @revisit Why not a <code>clearTextSymbolizer</code> method instead? Or <code>setTextSymbolizer(null)</code>?
     *          This flag should be set to <code>true</code> automatically by <code>setTextSymbolizer</code>.
     */
    public void setTextSymbolizerSet(boolean flag);

    /**
     * Returns the <code>LineSymbolizer</code> object.
     *
     * @return the <code>LineSymbolizer</code> object.
     */
    public LineSymbolizer getLineSymbolizer();

    /**
     * Returns whether the <code>LineSymbolizer</code> object has been set.
     *
     * @return <code>true</code> if the <code>LineSymbolizer</code> object has been set,
     *         <code>false</code> otherwise.
     *
     * @revisit Why not just returns <code>null</code> from <code>getLineSymbolizer</code> if not set?
     */
    public boolean isLineSymbolizerSet();

    /**
     * Sets the <code>LineSymbolizer</code> object.
     *
     * @param object the <code>LineSymbolizer</code> object.
     */
    public void setLineSymbolizer(LineSymbolizer object);

    /**
     * Sets the fact that the <code>LineSymbolizer</code> object has been set.
     *
     * @param flag <code>true</code> if the <code>LineSymbolizer</code> object has been set,
                   <code>false</code> otherwise.
     *
     * @revisit Why not a <code>clearLineSymbolizerSet</code> method instead? Or <code>setLineSymbolizerSet(null)</code>?
     *          This flag should be set to <code>true</code> automatically by <code>setLineSymbolizerSet</code>.
     */
    public void setLineSymbolizerSet(boolean flag);
}
