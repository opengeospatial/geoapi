/**************************************************************************************************
 **
 ** $Id$
 **
 ** $Source$
 **
 ** Copyright (C) 2003 Open GIS Consortium, Inc. All Rights Reserved. http://www.opengis.org/Legal/
 **
 *************************************************************************************************/

package org.opengis.go.display.canvas;

import org.opengis.crs.coordops.CoordinateTransformation;
import org.opengis.crs.coordrefsys.CoordinateReferenceSystem;
import org.opengis.go.geometry.BoundingRectangle;
import org.opengis.spatialschema.coordinate.DirectPosition;
import org.opengis.go.display.DisplayFactory;
import org.opengis.go.display.event.EventManager;
import org.opengis.go.display.primitive.Graphic;

/**
 * <code>Canvas</code> defines a common abstraction for implementations that
 * manage the display and user manipulation of <code>Graphic</code> instances.
 * A <code>Canvas</code> with an XY (Cartesian) display field
 * should support the following properties:
 * <ul>
 *   <li>pixelWidth</li>
 *   <li>pixelHeight</li>
 *   <li>center</li>
 *   <li>width</li>
 *   <li>scale</li>
 *   <li>boundingRectangle</li>
 * </ul>
 *
 * @author Open GIS Consortium, Inc.
 * @version $Revision$, $Date$
 */
public interface Canvas {
    
    //**  deconstructor  **
    
    /**
     * Method that may be called when a <code>Canvas</code> is no longer
     * needed.  Implementations may use this method to release resources or to
     * return the object to an object pool.  It is an error to reference a
     * <code>Canvas</code> after its dispose method has been called.
     */
    public void dispose();
    
    /**
     * Method that may be called when the <code>EventManager</code>s of a 
     * <code>Canvas</code> are no longer
     * needed.  Implementations may use this method to release resources or to
     * return the object to an object pool.  It is an error to reference any
     * <code>EventManager</code>s of a <code>Canvas</code> after this method 
     * has been called.
     */
    public void disposeEventManagers();
    
    //**  accessors/mutators  **
    
    /**
     * Returns the unique identifier of this <code>Canvas</code>, which is
     * assigned by the implementation.  The UID is immutable and may be used
     * to retrieve a particular <code>Canvas</code> from the 
     * <code>GraphicFactory</code>.
     * @return the UID of this <code>Canvas</code>.
     */
    public String getUID();
    
    /**
     * Sets the title of this <code>Canvas</code>.  The title of a
     * <code>Canvas</code> may or may not be displayed on the titlebar of
     * an application's window.
     * @param name the new title for this <code>Canvas</code>.
     */
    public void setTitle(String title);
    
    /**
     * Returns the title assigned to this <code>Canvas</code>.
     * @return the title of this <code>Canvas</code>.
     */
    public String getTitle();

    /**
     * Returns the <code>DisplayFactory</code> associated with this
     * <code>Canvas</code>.
     * @return the <code>DisplayFactory</code>.
     */
    public DisplayFactory getFactory();

    /**
     * Returns a copy of the current state of this <code>Canvas</code>.
     * The object returned will implement <code>CanvasState</code> or one of
     * its subinterfaces, depending on the type of Canvas.
     */
    public CanvasState getState();

    /**
     * Returns true if the given coordinate is visible on this 
     * <code>Canvas</code>.
     */
    public boolean isVisible(DirectPosition coordinate);
    
    //**  Graphic methods  **
    
    /**
     * Adds the given <code>Graphic</code> to this <code>Canvas</code>.
     * Implementations should respect the zOrder retrieved by calling
     * <code>Graphic.getGraphicStyle().getZOrderHint()</code>.  When two added
     * <code>Graphic</code>s have the same zOrder, the most recently added
     * one should be on top.
     * @param graphic the <code>Graphic</code> to add.
     */
    public void add(Graphic graphic);
    
    /**
     * Adds the given <code>Graphic</code> to this <code>Canvas</code>,
     * immediately placing the <code>Graphic</code> in an editing/drawing
     * mode, as defined by the <code>Canvas</code> implementation.  A
     * <code>Graphic</code> added as editable may or may not be visible when
     * it is added, as it may wait for user input to define the 
     * <code>Graphic</code>'s values through mouse gestures or key input.
     * @param graphic the <code>Graphic</code> to add as editable.
     */
    public void addAsEditable(Graphic graphic);
    
    /**
     * Removes the given <code>Graphic</code> from this 
     * <code>Canvas</code>.
     * @param graphic the <code>Graphic</code> to remove.
     */
    public void remove(Graphic graphic);
    
    /**
     * Returns the EventManager subinterface, based on the class type.
     * <p>
     * Note: While the class implementing <code>Canvas</code> may additionally implement
     * <code>EventManager</code> subinterface(s). 
     * While this design is simple, it limits the <code>Canvas</code> object to the
     * input mechanisms supported by to those particular <code>EventManager</code>
     * subinterface(s), and thus is discouraged.
     * <p>
     * If the class implementing Canvas does <i>not</i> implement the particular 
     * <code>EventManager</code> subinterface, then this method can look up the 
     * <code>EventManager</code> via an implementation-specific mechanism.
     * Otherwise, if the class implementing Canvas <i>does</i> implement the 
     * particular <code>EventManager</code> subinterface, this method can return 
     * the <code>this</code> reference.
     * 
     * @param eventManagerClass the class type of the EventManager subinterface.
     * @return a class that implements the requested EventManager subinterface, or 
     * null if there is no implementing class.
     */
    public EventManager findEventManager(Class eventManagerClass);
    
    /**
     * Adds the <code>EventManager</code> subinterface if it not currently in the 
     * <code>Canvas</code>'s collection of <code>EventManager</code>s.
     * @param eventManager the <code>EventManager</code> type to be added to the 
     *          <code>Canvas</code>'s collection.
     * @return true if the <code>EventManager</code> is not in the collection, 
     *          false otherwise.
     */
    public void addEventManager(EventManager eventManager);
    
    /**
     * Returns the top-most <code>Graphic</code> that occupies given 
     * <code>DirectPosition</code>.  The top-most <code>Graphic</code>
     * will have the highest zOrder.
     * @param directPosition the <code>DirectPosition</code> at which to look 
     *   for <code>Graphic</code>s.
     * @return the top-most <code>Graphic</code> at the given 
     *   <code>DirectPosition</code>.
     */
    public Graphic getTopGraphicAt(DirectPosition directPosition);
    
    /**
     * Returns the <code>Graphic</code>s that occupy the given 
     * <code>DirectPosition</code>. The order is implementation-specific.
     * @param directPosition the <code>DirectPosition</code> at which to look 
     *   for <code>Graphic</code>s.
     * @return the array of <code>Graphic</code>s at the given 
     *   <code>DirectPosition</code>.
     */
    public Graphic[] getGraphicsAt(DirectPosition directPosition);
    
    /**
     * Returns the <code>Graphic</code>s that occupy the given 
     * <code>BoundingRectangle</code>. The order is implementation-specific.
     * @param bounds the <code>BoundingRectangle</code> in which to look for
     *   <code>Graphic</code>s.
     * @return the array of <code>Graphic</code>s in the given 
     *   <code>BoundingRectangle</code>.
     */
    public Graphic[] getGraphicsIn(BoundingRectangle bounds);
    
    //**  canvas listener methods  **
    
    /**
     * Adds the given listener that will be notified when the state of this
     * <code>Canvas</code> has changed.
     */
    public void addCanvasListener(CanvasListener listener);

    /**
     * Removes the given listener.
     */
    public void removeCanvasListener(CanvasListener listener);
    
    //**  CanvasManager methods  **
    
    /**
     * Enables the given <code>CanvasHandler</code>, removing the current 
     * handler (if any).  The new handler's 
     * <code>handlerEnabled(CanvasController)</code> method is called, passing
     * in a new, active <code>CanvasController</code> that will allow the 
     * programmer to modify the <code>Canvas</code>'s properties.
     * <p/>
     * Implementation suggestion:
     * <pre><code>
     *  public void enableCanvasHandler(CanvasHandler handler) {
     *      if (handler != activeHandler) {
     *          if (activeHandler != null) {
     *              removeCanvasHandler(activeHandler);
     *          }
     *          activeHandler = handler;
     *          activeController = new CanvasController(this);
     *          activeHandler.handlerEnabled(activeController);
     *      }
     *  }
     * </code></pre>
     */
    public void enableCanvasHandler(CanvasHandler handler);
    
    /**
     * Removes the given <code>CanvasHandler</code> from this 
     * <code>Canvas</code>.
     */
    public void removeCanvasHandler(CanvasHandler handler);
    
    /**
     * Returns the currently active <code>CanvasHandler</code> or null if no
     * handler is active.
     */
    public CanvasHandler getActiveCanvasHandler();
    
    //**  imlementation hints  **
    
    /**
     * Sets a rendering hint for implementation or platform specific 
     * rendering information.
     * @param hintName the name of the hint.
     * @param hint the rendering hint.
     */
    public void setImplHint(String hintName, Object hint);
    
    /**
     * Returns the rendering hint associated with the hint name.
     * @param hintName the name of the hint.
     * @return         the rendering hint.
     */
    public Object getImplHint(String hintName);
    
    //** CoordinateReferenceSystem methods **
    
    /**
     * Returns the Coordinate Reference System associated with the display of this
     * <code>Canvas</code>. The display Coordinate Reference System corresponds to 
     * the geometry of the display device (e.g. video monitor = Cartesian, 
     * planetarium = Spherical).
     * @return the display Coordinate Reference System
     */
    public CoordinateReferenceSystem getDisplayCoordinateReferenceSystem();
   
    /**
     * Returns the Coordinate Reference System for the geospatial model for this
     * <code>Canvas</code>. This is the default geospatial Coordinate Reference System
     * for this <code>Canvas</code>.
     * @return the geospatial Coordinate Reference System
     */ 
    public CoordinateReferenceSystem getGeospatialCoordinateReferenceSystem();
    
    /**
     * Sets the Coordinate Reference System for the geospatial model for this
     * <code>Canvas</code>. This is the default geospatial Coordinate Reference System
     * for this <code>Canvas</code>.
     * @param the geospatial Coordinate Reference System
     */
    public void setGeospatialCoordinateReferenceSystem(CoordinateReferenceSystem crs);
    
    /**
     * Returns the coordinate transformation object for this
     * <code>Canvas</code>. This allows the <code>Canvas</code> to resolve 
     * conversions of coordinates between different Coordinate Reference Systems.
     * @return the coordinate transformation object
     */    
    public CoordinateTransformation getCoordinateTransformation();
    
}

