/**************************************************************************************************
 **
 ** $Id$
 **
 ** $Source$
 **
 ** Copyright (C) 2003 Open GIS Consortium, Inc. All Rights Reserved. http://www.opengis.org/Legal/
 **
 *************************************************************************************************/

package org.opengis.go.display.primitive;

import org.opengis.go.display.event.GraphicEvent;
import org.opengis.go.display.event.GraphicListener;
import org.opengis.go.display.style.GraphicStyle;


/**
 * <code>Graphic</code> defines the root abstraction of a graphic object
 * taxonomy, specifying the methods common to a lightweight set of graphic objects.
 *
 * @author Open GIS Consortium, Inc.
 * @version $Revision$, $Date$
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
	public void refresh();

    //**  accessors/mutators  **
    
    /**
     * Sets the name of this <code>Graphic</code> to the given value.
     * @param name the new name to assign to this <code>Graphic</code>.
     */
    public void setName(String name);
    
    /**
     * Returns the name assigned to this <code>Graphic</code>.
     * @return	the name assigned to this <code>Graphic</code>.
     */
    public String getName();

    /**
     * Sets the parent of this <code>Graphic</code>.
     * @param parent the parent of this <code>Graphic</code>.
     */
    public void setParent(AggregateGraphic parent);

    /**
     * Returns the parent of this <code>Graphic</code>. This is most likely of type
     * <code>AggregateGraphic</code> or <code>GraphicCompositeCurve</code>.
     * @return	the parent of this <code>Graphic</code>.
     */
    public Graphic getParent();

    /**
	 * Returns the <code>GraphicStyle</code> for this <code>Graphic</code>.
	 * Implementations should return a reference to their internal
	 * <code>GraphicStyle</code> object, so that users may modify this
	 * <code>Graphic</code>'s style properties directly through the
	 * style object.
	 * @return @return	the <code>GraphicStyle</code>.
	 */
    public GraphicStyle getGraphicStyle();

    /**
     * Returns the value of the property with the specified key. Only
     * properties added with <code>putClientProperty</code> will return a 
     * non-null value.
     * @return the value of this property or null
     * @see #putClientProperty
     */
    public Object getClientProperty(Object key);

    /**
     * Adds an arbitrary key/value "client property" to this <code>Graphic</code>.
     * The get/putClientProperty methods provide access to a small
     * per-instance hashtable. Callers can use get/putClientProperty to
     * annotate Graphics that were created by another module.
     *
     * If value is null this method will remove the property. Changes to client
     * properties are reported with PropertyChange events. The name of the
     * property (for the sake of PropertyChange events) is key.toString(). 
     * The clientProperty dictionary is not intended to support large scale
     * extensions to <code>Graphic</code> nor should be it considered an alternative to
     * subclassing when designing a new component.
     * @param key the Object containing the key string.
     * @param value the Object that is the client data.
     * @see #getClientProperty
     */
    public void putClientProperty(Object key, Object value);

    /**
     * Sets a boolean indicating whether mouse events on this <code>Graphic</code> should
     * be passed to the parent <code>Graphic</code> in addition to being passed to any
     * listeners on this object.  The default is false, indicating that events
     * will not be passed to the parent.  If the boolean is true, then the
     * event will be passed to the parent after having been passed to the
     * listeners on this object.
     * @param passEventsToParent true, if events should be passed to the
     *     parent graphic.  false if they should not.
     */
    public void setPassingEventsToParent(boolean passToParent);

    /**
     * Returns a boolean indicating whether mouse events on this <code>Graphic</code> will
     * be passed to the parent <code>Graphic</code> in addition to being passed to any
     * listeners on this object.  The default is false, indicating that events
     * will not be passed to the parent.  If the boolean is true, then the
     * event will be passed to the parent after having been passed to the
     * listeners on this object.
     */
    public boolean isPassingEventsToParent();
    
    /**
     * Sets a boolean flag specifying whether this object is to show
     * its edit handles. Edit handles are the small boxes that appear on the
     * end of a line segment or on the four corners of a box that a users
     * selects to edit this object.
     * @param showingHandles boolean flag: true tells this object to show
     * its edit handles.
     */
    public void setShowingEditHandles(boolean showingHandles);

    /**
     * Returns the boolean flag that specifies whether this object is showing
     * its edit handles.
     * @return boolean flag: true means it is showing its handles.
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
     */
    public Graphic cloneGraphic();

    //**  listener methods  **

    /**
     * Adds the given listener to this <code>Graphic</code>'s list of
     * listeners.  Listeners are notified of key, mouse, and change events that
     * affect this <code>Graphic</code>.
     * @param listener the <code>GraphicListener</code> to add.
     */
    public void addGraphicListener(GraphicListener listener);

    /**
     * Removes the given listener from this <code>Graphic</code>'s list of
     * listeners.  
     * @param listener the <code>GraphicListener</code> to remove.
     */
    public void removeGraphicListener(GraphicListener listener);
    
    
    /**
     * Calls the graphic event method of all listeners in this
     * <code>Graphic</code>'s list of listeners. The listeners need to 
     * determine which subclassed event is called and what event-specific 
     * action was taken.
     * @param ge the <code>GraphicEvent</code> to give to the listeners.
     * @see org.opengis.go.display.event.GraphicListener
     * @see org.opengis.go.display.event.GraphicEvent
     * @see org.opengis.go.display.event.GraphicChangeEvent
     * @see org.opengis.go.display.event.GraphicMouseEvent
     */
    public void fireGraphicEvent(GraphicEvent ge);
    
}
