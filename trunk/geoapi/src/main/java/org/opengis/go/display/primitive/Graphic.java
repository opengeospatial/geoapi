/**************************************************************************************************
 **
 ** $Id$
 **
 ** $URL$
 **
 ** Copyright (C) 2003-2005 Open GIS Consortium, Inc.
 ** All Rights Reserved. http://www.opengis.org/legal/
 **
 *************************************************************************************************/
package org.opengis.go.display.primitive;

import org.opengis.go.display.event.GraphicEvent;
import org.opengis.go.display.event.GraphicListener;
import org.opengis.go.display.style.GraphicStyle;
import org.opengis.go.display.style.Symbology;


/**
 * <code>Graphic</code> defines the root abstraction of a graphic object
 * taxonomy, specifying the methods common to a lightweight set of graphic objects.
 *
 * @author <A HREF="http://www.opengis.org">OpenGIS&reg; consortium</A>
 */
public interface Graphic {

    //*************************************************************************
    //**
    //**  Static Fields
    //**
    //*************************************************************************

    //**  Default Editability property values  **

    /**  Default auto edit value.  */
    boolean DEFAULT_AUTO_EDIT = true;

    /**  Default drag selectable value.  */
    boolean DEFAULT_DRAG_SELECTABLE = true;

    /** Default pickable value.  */
    boolean DEFAULT_PICKABLE = true;

    /**  Default selected value.  */
    boolean DEFAULT_SELECTED = false;

    //**  Default Highlight property values  **

    /**  Default blinking value.  */
    boolean DEFAULT_BLINKING = false;

    /**  Default blink pattern value.  */
    float[] DEFAULT_BLINK_PATTERN = {0.5f, 0.5f};

    //**  Default Viewability property values  **

    /**  Default max scale value.  */
    int DEFAULT_MAX_SCALE = Integer.MAX_VALUE;

    /**  Default min scale value.  */
    int DEFAULT_MIN_SCALE = 1;

    /**  Default visible value.  */
    boolean DEFAULT_VISIBLE = true;

    /**  Default z order value.  */
    double DEFAULT_Z_ORDER = 0.0;

    //**  deconstructor  **

    //*************************************************************************
    //**
    //**  Methods
    //**
    //*************************************************************************

    /**
     * Method that can be called when an object is no longer needed.
     * Implementations may use this method to release resources, if needed.
     * Implementations may also implement this method to return an object
     * to an object pool.  It is an error to reference a <code>Graphic</code> in any
     * way after its dispose method has been called.
     *
     * @deprecated Moved to {@link org.opengis.display.primitive.Graphic#dispose}.
     */
    @Deprecated
    void dispose();

    /**
     * Flags this <code>Graphic</code> object as needing to be redrawn,
     * due to changes to the internal data of the object which affect the
     * rendering of the object.
     * <p>
     * The actual flag set/unset mechanism is implementation-specific.
     * The implementation also choses the manner and timing in which both
     * the flag is checked and the <code>Graphic</code> object is redrawn.
     * <p>
     * An application would call this method when any geometric
     * information for this <code>Graphic</code> object has changed;
     * for example, when the underlying <code>Geometry</code> instance
     * is changed or data in that instance has changed.
     */
    void refresh();

    //**  accessors/mutators  **

    /**
     * Sets the name of this <code>Graphic</code> to the given value.
     * @param name the new name to assign to this <code>Graphic</code>.
     */
    void setName(String name);

    /**
     * Returns the name assigned to this <code>Graphic</code>.
     * @return  the name assigned to this <code>Graphic</code>.
     */
    String getName();

    /**
     * Sets the parent of this <code>Graphic</code>.
     * @param parent the parent of this <code>Graphic</code>.
     */
    void setParent(Graphic parent);

    /**
     * Returns the parent of this <code>Graphic</code>.  Currently, only
     * <code>AggregateGraphic</code>s have <code>Graphic</code> children.
     * @return  the parent of this <code>Graphic</code>.
     */
    Graphic getParent();

    /**
     * Sets the <code>GraphicStyle</code> for this <code>Graphic</code>.
     * <code>Graphic</code>s may share style property objects with other
     * <code>Graphic</code>s.
     */
    void setGraphicStyle(GraphicStyle style);

    /**
     * Returns the <code>GraphicStyle</code> for this <code>Graphic</code>.
     * Implementations should return a reference to their internal
     * <code>GraphicStyle</code> object, so that users may modify this
     * <code>Graphic</code>'s style properties directly through the
     * style object.
     * @return the <code>GraphicStyle</code>.
     */
    GraphicStyle getGraphicStyle();

    /**
     * Returns the value of the property with the specified key. Only
     * properties added with <code>putClientProperty</code> will return a
     * non-null value.
     * @return the value of this property or null
     * @see #putClientProperty
     */
    Object getClientProperty(Object key);

    /**
     * Adds an arbitrary key/value "client property" to this <code>Graphic</code>.
     * The {@code get}/{@code putClientProperty} methods provide access to a small
     * per-instance hashtable. Callers can use {@code get}/{@code putClientProperty}
     * to annotate {@code Graphic}s that were created by another module.
     * <p>
     * If value is null this method will remove the property. Changes to client
     * properties are reported with property change events. The name of the
     * property (for the sake of property change events) is {@code key.toString()}.
     * The {@code clientProperty} dictionary is not intended to support large scale
     * extensions to <code>Graphic</code> nor should be it considered an alternative to
     * subclassing when designing a new component.
     *
     * @param key the Object containing the key string.
     * @param value the Object that is the client data.
     * @see #getClientProperty
     */
    void putClientProperty(Object key, Object value);

    /**
     * Sets a boolean indicating whether mouse events on this <code>Graphic</code> should
     * be passed to the parent <code>Graphic</code> in addition to being passed to any
     * listeners on this object.  The default is false, indicating that events
     * will not be passed to the parent.  If the boolean is true, then the
     * event will be passed to the parent after having been passed to the
     * listeners on this object.
     *
     * @param passToParent <code>true</code> if events should be passed to the
     *        parent graphic, <code>false</code> if they should not.
     */
    void setPassingEventsToParent(boolean passToParent);

    /**
     * Returns a boolean indicating whether mouse events on this <code>Graphic</code> will
     * be passed to the parent <code>Graphic</code> in addition to being passed to any
     * listeners on this object.  The default is <code>false</code>, indicating that events
     * will not be passed to the parent.  If the boolean is <code>true</code>, then the
     * event will be passed to the parent after having been passed to the
     * listeners on this object.
     *
     * @return <code>true</code> if this graphic pass the events to the parent graphic.
     */
    boolean isPassingEventsToParent();

    /**
     * Sets a boolean flag specifying whether this object is to show
     * its edit handles. Edit handles are the small boxes that appear on the
     * end of a line segment or on the four corners of a box that a users
     * selects to edit this object.
     *
     * @param showingHandles <code>true</code> if this object show its edit handles.
     */
    void setShowingEditHandles(boolean showingHandles);

    /**
     * Returns the boolean flag that specifies whether this object is showing
     * its edit handles.
     *
     * @return <code>true</code> means it is showing its handles.
     */
    boolean isShowingEditHandles();

    /**
     * Sets a boolean flag indicating whether this object is
     * to show anchor handles. Anchor handles allow the object to be moved
     * in the display.
     */
    void setShowingAnchorHandles(boolean showingHandles);

    /**
     * Returns the boolean flag that indicates whether this object
     * is showing anchor handles. Anchor handles allow the object to be moved
     * in the display.
     */
    boolean isShowingAnchorHandles();

    //**  methods to work with/create other Graphics and Styles

    /**
     * Creates a new <code>Graphic</code> of the same type as this object.  The resulting
     * object should be identical in all respects to the original.
     *
     * @todo Consider overriding <code>Object.clone()</code> instead.
     */
    Graphic cloneGraphic();

    //**  listener methods  **

    /**
     * Adds the given <code>GraphicListener</code> to this <code>Graphic</code>'s list of
     * listeners.  <code>GraphicListener</code>s are notified of key, mouse, and change events that
     * affect this <code>Graphic</code>.
     *
     * @param listener the <code>GraphicListener</code> to add.
     */
    void addGraphicListener(GraphicListener listener);

    /**
     * Removes the given <code>GraphicListener</code> from this <code>Graphic</code>'s list of
     * listeners.
     *
     * @param listener the <code>GraphicListener</code> to remove.
     */
    void removeGraphicListener(GraphicListener listener);

    /**
     * Calls the graphic event method of all <code>GraphicListener</code>s in this
     * <code>Graphic</code>'s list of listeners. The listeners need to
     * determine which subclassed event is called and what event-specific
     * action was taken.
     *
     * @param ge the <code>GraphicEvent</code> to give to the listeners.
     *
     * @see org.opengis.go.display.event.GraphicListener
     * @see org.opengis.go.display.event.GraphicEvent
     * @see org.opengis.go.display.event.GraphicChangeEvent
     * @see org.opengis.go.display.event.GraphicMouseEvent
     */
    void fireGraphicEvent(GraphicEvent ge);

    //*************************************************************************
    //**
    //**  Style Property Accessors
    //**
    //*************************************************************************

    /**
     * Returns the auto edit value.
     * @return the auto edit value.
     */
    boolean getAutoEdit();

    /**
     * Sets the auto edit value.
     * @param autoEdit the auto edit value.
     */
    void setAutoEdit(boolean autoEdit);

    /**
     * Returns the drag selectable value.
     * @return the drag selectable value.
     */
    boolean getDragSelectable();

    /**
     * Sets the drag selectable value.
     * @param dragSelectable the drag selectable value.
     */
    void setDragSelectable(boolean dragSelectable);

    /**
     * Returns the pickable value.
     * @return the pickable value.
     */
    boolean getPickable();

    /**
     * Sets the pickable value.
     * @param pickable the pickable value.
     */
    void setPickable(boolean pickable);

    /**
     * Returns the selected value.
     * @return the selected value.
     */
    boolean getSelected();

    /**
     * Sets the selected value.
     * @param selected the selected value.
     */
    void setSelected(boolean selected);

    /**
     * Returns the blinking value.
     * @return the blinking value.
     */
    boolean getBlinking();

    /**
     * Sets the blinking value.
     * @param blinking the blinking value.
     */
    void setBlinking(boolean blinking);

    /**
     * Returns the blink pattern value.
     * @return the blink pattern value.
     */
    float[] getBlinkPattern();

    /**
     * Sets the blink pattern value.
     * @param blinkPattern the blink pattern value.
     */
    void setBlinkPattern(float[] blinkPattern);

    /**
     * Returns a symbology object that will be used to override some, if not
     * all, of the graphical properties of Graphics using this style.  This will
     * be null by default.
     */
    Symbology getSymbology();

    /**
     * Sets the symbology object that will be used to override some, if not
     * all, of the graphical properties of Graphics using this style.
     */
    void setSymbology(Symbology symbology);

    /**
     * Returns the max scale value.
     * @return the max scale value.
     */
    double getMaxScale();

    /**
     * Sets the max scale value.
     * @param maxScale the max scale value.
     */
    void setMaxScale(double maxScale);

    /**
     * Returns the min scale value.
     * @return the min scale value.
     */
    double getMinScale();

    /**
     * Sets the min scale value.
     * @param minScale the min scale value.
     */
    void setMinScale(double minScale);

    /**
     * Returns the z order hint value.
     * @return the z order hint value.
     */
    double getZOrderHint();

    /**
     * Sets the z order hint value.
     * @param zOrderHint the z order hint value.
     */
    void setZOrderHint(double zOrderHint);

    /**
     * Returns the visible value.
     * @return the visible value.
     *
     * @deprecated Moved to {@link org.opengis.display.primitive.Graphic#isVisible}.
     */
    @Deprecated
    boolean getVisible();

    /**
     * Sets the visible value.
     * @param visible the visible value.
     *
     * @deprecated Moved to {@link org.opengis.display.primitive.Graphic#getVisible}.
     */
    @Deprecated
    void setVisible(boolean visible);
}
