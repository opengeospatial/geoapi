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

import org.opengis.util.Cloneable;

/**
 * Serves as the base interface for the collection of drawing attributes that are
 * applied to a {@link org.opengis.go.display.primitive.Graphic}.  Subclasses provide
 * attributes for specifying SLD-based line symbolizer, polygon symbolizer, point
 * symbolizer, text symbolizer.  Attributes common to all types of geometry are
 * contained in this base class.
 * <p>
 * Note that code that sets the properties of a <code>GraphicStyle</code>
 * cannot assume that there is only one Graphic that is referencing the style.
 * So changing the values may affect many Graphics.  If the code wishes to
 * guarantee that the change affects only one Graphic, it should clone the
 * style and call setGraphicStyle on the Graphic with the clone.
 *
 * @version $Revision$, $Date$
 * @author <A HREF="http://www.opengis.org">OpenGIS&reg; consortium</A>
 */
public interface GraphicStyle extends Cloneable {

    //*************************************************************************
    //**
    //**  Static Fields
    //**
    //*************************************************************************

    //**  Default Editability property values  **

    /**  Default auto edit value.  */
    public static final boolean DEFAULT_AUTO_EDIT = true;

    /**  Default drag selectable value.  */
    public static final boolean DEFAULT_DRAG_SELECTABLE = true;

    /** Default pickable value.  */
    public static final boolean DEFAULT_PICKABLE = true;

    /**  Default selected value.  */
    public static final boolean DEFAULT_SELECTED = false;

    //**  Default Highlight property values  **

    /**  Default blinking value.  */
    public static final boolean DEFAULT_BLINKING = false;

    /**  Default blink pattern value.  */
    public static final float[] DEFAULT_BLINK_PATTERN = {0.5f, 0.5f};

    //**  Default Viewability property values  **

    /**  Default max scale value.  */
    public static final int DEFAULT_MAX_SCALE = Integer.MAX_VALUE;

    /**  Default min scale value.  */
    public static final int DEFAULT_MIN_SCALE = 1;

    /**  Default visible value.  */
    public static final boolean DEFAULT_VISIBLE = true;

    /**  Default z order value.  */
    public static final double DEFAULT_Z_ORDER = 0.0;

    //*************************************************************************
    //**
    //**  Methods
    //**
    //*************************************************************************

    /**
     * Registers the given object as a listener to receive events when the
     * properties of this style have changed.
     */
    public void addGraphicStyleListener(GraphicStyleListener listener);

    /**
     * For a listener that was previously added using the
     * <code>addGraphicStyleListener</code> method, this method de-registers
     * it so that it will no longer receive events when the properties of this
     * style have changed.
     */
    public void removeGraphicStyleListener(GraphicStyleListener listener);

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
     * properties of the specified <code>GraphicStyle</code>.  May throw an execption
     * if the given object is not the same type as this one.
     *
     * @param style the <code>GraphicStyle</code> used to set this
     *        <code>GraphicStyle</code> properties.
     */
    public void setPropertiesFrom(GraphicStyle style);

    /**
     * Returns the auto edit value.
     * @return the auto edit value.
     */
    public boolean getAutoEdit();

    /**
     * Sets the auto edit value.
     * @param autoEdit the auto edit value.
     */
    public void setAutoEdit(boolean autoEdit);

    /**
     * Returns the drag selectable value.
     * @return the drag selectable value.
     */
    public boolean getDragSelectable();

    /**
     * Sets the drag selectable value.
     * @param dragSelectable the drag selectable value.
     */
    public void setDragSelectable(boolean dragSelectable);

    /**
     * Returns the pickable value.
     * @return the pickable value.
     */
    public boolean getPickable();

    /**
     * Sets the pickable value.
     * @param pickable the pickable value.
     */
    public void setPickable(boolean pickable);

    /**
     * Returns the selected value.
     * @return the selected value.
     */
    public boolean getSelected();

    /**
     * Sets the selected value.
     * @param selected the selected value.
     */
    public void setSelected(boolean selected);

    /**
     * Returns the blinking value.
     * @return the blinking value.
     */
    public boolean getBlinking();

    /**
     * Sets the blinking value.
     * @param blinking the blinking value.
     */
    public void setBlinking(boolean blinking);

    /**
     * Returns the blink pattern value.
     * @return the blink pattern value.
     */
    public float[] getBlinkPattern();

    /**
     * Sets the blink pattern value.
     * @param blinkPattern the blink pattern value.
     */
    public void setBlinkPattern(float[] blinkPattern);

    /**
     * Returns a symbology object that will be used to override some, if not
     * all, of the graphical properties of Graphics using this style.  This will
     * be null by default.
     */
    public Symbology getSymbology();

    /**
     * Sets the symbology object that will be used to override some, if not
     * all, of the graphical properties of Graphics using this style.
     */
    public void setSymbology(Symbology symbology);

    /**
     * Returns the max scale value.
     * @return the max scale value.
     */
    public double getMaxScale();

    /**
     * Sets the max scale value.
     * @param maxScale the max scale value.
     */
    public void setMaxScale(double maxScale);

    /**
     * Returns the min scale value.
     * @return the min scale value.
     */
    public double getMinScale();

    /**
     * Sets the min scale value.
     * @param minScale the min scale value.
     */
    public void setMinScale(double minScale);

    /**
     * Returns the z order hint value.
     * @return the z order hint value.
     */
    public double getZOrderHint();

    /**
     * Sets the z order hint value.
     * @param zOrderHint the z order hint value.
     */
    public void setZOrderHint(double zOrderHint);

    /**
     * Returns the visible value.
     * @return the visible value.
     */
    public boolean getVisible();

    /**
     * Sets the visible value.
     * @param visible the visible value.
     */
    public void setVisible(boolean visible);

    /**
     * Method inherited from the <code>Cloneable</code> interface, included here
     * for completeness.
     *
     * @return Returns a shallow copy of this object.  This means that all of
     *   the subordinate objects referenced by this object will also be
     *   referenced by the result.  These objects include the values for
     *   <code>implHint</code>s, Symbology, blink pattern arrays, etc.
     */
    public Object clone();
}
