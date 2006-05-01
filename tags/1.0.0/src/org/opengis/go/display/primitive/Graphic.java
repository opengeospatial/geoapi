/**************************************************************************************************
 **
 ** $Id$
 **
 ** $URL$
 **
 ** Copyright (C) 2003 Open GIS Consortium, Inc. All Rights Reserved. http://www.opengis.org/Legal/
 **
 *************************************************************************************************/
package org.opengis.go.display.primitive;

// OpenGIS direct dependencies
import org.opengis.go.display.event.GraphicEvent;
import org.opengis.go.display.event.GraphicListener;
import org.opengis.go.display.style.GraphicStyle;

/**
 * The root abstraction of a graphic object taxonomy, specifying the methods
 * common to a lightweight set of graphic objects.
 *
 * @author <A HREF="http://www.opengis.org">OpenGIS&reg; consortium</A>
 * @version $Revision: 659 $, $Date: 2006-02-23 14:07:07 +1100 (jeu., 23 févr. 2006) $
 */
public interface Graphic {

    //**  deconstructor  **

    /**
     * Method that can be called when an object is no longer needed.
     * Implementations may use this method to release resources, if needed.
     * Implementations may also implement this method to return an object
     * to an object pool.  It is an error to reference a <code>Graphic</code> in any
     * way after its dispose method has been called.
     */
    public void dispose();

    /**
     * Flags this <code>Graphic</code> object as needing to be redrawn, 
     * due to changes to the internal data of the object which affect the
     * rendering of the object.
     * <br><br>
     * The actual flag set/unset mechanism is implementation-specific. 
     * The implementation also choses the manner and timing in which both 
     * the flag is checked and the <code>Graphic</code> object is redrawn.
     * <br><br>
     * An application would call this method when any geometric 
     * information for this <code>Graphic</code> object has changed; 
     * for example, when the underlying <code>Geometry</code> instance
     * is changed or data in that instance has changed.
     */
    public void refresh();

    //**  accessors/mutators  **

    /**
     * Sets the name of this <code>Graphic</code> to the given value.
     *
     * @param name the new name to assign to this <code>Graphic</code>.
     */
    public void setName(String name);

    /**
     * Returns the name assigned to this <code>Graphic</code>.
     *
     * @return	the name assigned to this <code>Graphic</code>.
     */
    public String getName();

    /**
     * Sets the parent of this <code>Graphic</code>.
     *
     * @param parent the parent of this <code>Graphic</code>.
     *
     * @revisit The argument type is inconsistent with the {@link #getParent} return type.
     */
    public void setParent(Graphic parent);

    /**
     * Returns the parent of this <code>Graphic</code>. This is most likely of type
     * <code>AggregateGraphic</code> or <code>GraphicCompositeCurve</code>.
     *
     * @return	the parent of this <code>Graphic</code>.
     */
    public Graphic getParent();

    /**
     * Returns the <code>GraphicStyle</code> for this <code>Graphic</code>.
     * Implementations should return a reference to their internal
     * <code>GraphicStyle</code> object, so that users may modify this
     * <code>Graphic</code>'s style properties directly through the
     * style object.
     *
     * @return @return	the <code>GraphicStyle</code>.
     */
    public GraphicStyle getGraphicStyle();

    /**
     * Returns the value of the property with the specified key. Only
     * properties added with <code>putClientProperty</code> will return a 
     * non-null value.
     *
     * @return the value of this property or <code>null</code>.
     * @see #putClientProperty
     */
    public Object getClientProperty(Object key);

    /**
     * Adds an arbitrary key/value "client property" to this <code>Graphic</code>.
     * The <code>get/putClientProperty</code> methods provide access to a small
     * per-instance hashtable. Callers can use <code>get/putClientProperty</code>
     * to annotate Graphics that were created by another module.
     *
     * If value is <code>null</code> this method will remove the property. Changes
     * to client properties are reported with <code>PropertyChange</code> events.
     * The name of the property (for the sake of <code>PropertyChange</code> events)
     * is key.toString(). 
     * The <code>clientProperty</code> dictionary is not intended to support large scale
     * extensions to <code>Graphic</code> nor should be it considered an alternative to
     * subclassing when designing a new component.
     *
     * @param key the Object containing the key string.
     * @param value the Object that is the client data.
     * @see #getClientProperty
     */
    public void putClientProperty(Object key, Object value);

    /**
     * Sets a boolean indicating whether mouse events on this <code>Graphic</code> should
     * be passed to the parent <code>Graphic</code> in addition to being passed to any
     * listeners on this object.  The default is <code>false</code>, indicating that events
     * will not be passed to the parent.  If the boolean is <code>true</code>, then the
     * event will be passed to the parent after having been passed to the
     * listeners on this object.
     *
     * @param passToParent <code>true</code> if events should be passed to the
     *        parent graphic, <code>false</code> if they should not.
     */
    public void setPassingEventsToParent(boolean passToParent);

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
    public boolean isPassingEventsToParent();

    /**
     * Sets a boolean <code>flag</code> specifying whether this object is to show
     * its edit handles. Edit handles are the small boxes that appear on the
     * end of a line segment or on the four corners of a box that a users
     * selects to edit this object.
     *
     * @param showingHandles <code>true</code> if this object show its edit handles.
     */
    public void setShowingEditHandles(boolean showingHandles);

    /**
     * Returns the boolean flag that specifies whether this object is showing
     * its edit handles.
     *
     * @return <code>true</code> means it is showing its handles.
     */
    public boolean isShowingEditHandles();

    /**
     * Sets a boolean flag indicating whether this object is
     * to show anchor handles. Anchor handles allow the object to be moved
     * in the display.
     */
    public void setShowingAnchorHandles(boolean showingHandles);

    /**
     * Returns the boolean flag that indicates whether this object
     * is showing anchor handles. Anchor handles allow the object to be moved
     * in the display.
     */
    public boolean isShowingAnchorHandles();

    //**  methods to work with/create other Graphics and Styles

    /**
     * Creates a new <code>Graphic</code> of the same type as this object.  The resulting
     * object should be identical in all respects to the original.
     *
     * @revisit Consider overriding <code>Object.clone()</code> instead.
     */
    public Graphic cloneGraphic();

    //**  listener methods  **

    /**
     * Adds the given listener to this <code>Graphic</code>'s list of
     * listeners.  Listeners are notified of key, mouse, and change events that
     * affect this <code>Graphic</code>.
     *
     * @param listener the <code>GraphicListener</code> to add.
     */
    public void addGraphicListener(GraphicListener listener);

    /**
     * Removes the given listener from this <code>Graphic</code>'s list of
     * listeners.  
     *
     * @param listener the <code>GraphicListener</code> to remove.
     */
    public void removeGraphicListener(GraphicListener listener);

    /**
     * Calls the graphic event method of all listeners in this
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
     *
     * @revisit Usually, this kind of method is a protected one in the implementation class,
     *          not a public method in the interface...
     */
    public void fireGraphicEvent(GraphicEvent ge);
}

