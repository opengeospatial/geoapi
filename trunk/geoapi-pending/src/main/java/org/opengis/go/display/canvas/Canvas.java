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
package org.opengis.go.display.canvas;

import org.opengis.go.display.DisplayFactory;
import org.opengis.go.display.event.EventManager;
import org.opengis.go.display.primitive.Graphic;
import org.opengis.referencing.crs.CoordinateReferenceSystem;
import org.opengis.referencing.operation.IncompatibleOperationException;
import org.opengis.referencing.operation.MathTransform;
import org.opengis.geometry.DirectPosition;
import org.opengis.geometry.Envelope;


/**
 * <code>Canvas</code> defines a common abstraction for implementations that
 * manage the display and user manipulation of <code>Graphic</code> instances.
 * A <code>Canvas</code> with an XY (Cartesian) display field should support
 * the following properties:
 * <ul>
 *   <li>pixelWidth</li>
 *   <li>pixelHeight</li>
 *   <li>center</li>
 *   <li>width</li>
 *   <li>scale</li>
 *   <li>envelope</li>
 * </ul>
 *
 * @author Open GIS Consortium, Inc.
 *
 * @deprecated Splitted to {@link org.opengis.display.canvas} and to {@link org.opengis.display.renderer}.
 */
@Deprecated
public interface Canvas {

    //**  deconstructor  **

    /**
     * Method that may be called when a <code>Canvas</code> is no longer
     * needed.  Implementations may use this method to release resources or to
     * return the object to an object pool.  It is an error to reference a
     * <code>Canvas</code> after its dispose method has been called.
     *
     * @deprecated Replaced by {@link org.opengis.display.renderer.Renderer#dispose}.
     */
    @Deprecated
    void dispose();

    /**
     * Method that may be called when the <code>EventManager</code>s of a
     * <code>Canvas</code> are no longer needed. Implementations may use this
     * method to release resources or to return the object to an object pool. It
     * is an error to reference any <code>EventManager</code> s of a
     * <code>Canvas</code> after this method has been called.
     */
    void disposeEventManagers();

    //**  accessors/mutators  **

    /**
     * Returns the unique identifier of this <code>Canvas</code>, which is
     * assigned by the implementation. The UID is immutable and may be used to
     * retrieve a particular <code>Canvas</code> from the
     * <code>GraphicFactory</code>.
     *
     * @return the UID of this <code>Canvas</code>.
     *
     * @deprecated No replacement.
     */
    @Deprecated
    String getUID();

    /**
     * Sets the title of this <code>Canvas</code>.  The title of a
     * <code>Canvas</code> may or may not be displayed on the titlebar of an
     * application's window.
     *
     * @param title the new title for this <code>Canvas</code>.
     *
     * @deprecated Moved to {@link org.opengis.display.canvas.CanvasController#setTitle}.
     */
    @Deprecated
    void setTitle(String title);

    /**
     * Returns the title assigned to this <code>Canvas</code>.
     *
     * @return the title of this <code>Canvas</code>.
     *
     * @deprecated Moved to {@link org.opengis.display.canvas.CanvasState#getTitle}.
     */
    @Deprecated
    String getTitle();

    /**
     * Returns the <code>DisplayFactory</code> associated with this
     * <code>Canvas</code>.
     *
     * @return the <code>DisplayFactory</code>.
     */
    DisplayFactory getFactory();

    /**
     * Returns a copy of the current state of this <code>Canvas</code>. The
     * object returned will implement <code>CanvasState</code> or one of its
     * subinterfaces, depending on the type of Canvas.
     *
     * @deprecated Moved to {@link org.opengis.display.canvas.Canvas#getState}.
     */
    @Deprecated
    CanvasState getState();

    /**
     * Returns true if the given coordinate is visible on this
     * <code>Canvas</code>.
     *
     * @deprecated Moved to {@link org.opengis.display.canvas.Canvas#isVisible}.
     */
    @Deprecated
    boolean isVisible(DirectPosition coordinate);

    //**  Graphic methods  **

    /**
     * Adds the given <code>Graphic</code> to this <code>Canvas</code>.
     * Implementations should respect the zOrder retrieved by calling
     * <code>Graphic.getGraphicStyle().getZOrderHint()</code>.  When two added
     * <code>Graphic</code>s have the same zOrder, the most recently added
     * one should be on top.
     *
     * @param graphic the <code>Graphic</code> to add.
     *
     * @deprecated Replaced by {@link org.opengis.display.renderer.Renderer#graphics}.
     */
    @Deprecated
    Graphic add(Graphic graphic);

    /**
     * Adds the given <code>Graphic</code> to this <code>Canvas</code>,
     * immediately placing the <code>Graphic</code> in an editing/drawing
     * mode, as defined by the <code>Canvas</code> implementation.  A
     * <code>Graphic</code> added as editable may or may not be visible when
     * it is added, as it may wait for user input to define the
     * <code>Graphic</code>'s values through mouse gestures or key input.
     *
     * @param graphic the <code>Graphic</code> to add as editable.
     *
     * @deprecated No direct repacement.
     */
    @Deprecated
    Graphic addAsEditable(Graphic graphic);

    /**
     * Removes the given <code>Graphic</code> from this <code>Canvas</code>.
     *
     * @param graphic the <code>Graphic</code> to remove.
     *
     * @deprecated Replaced by {@link org.opengis.display.renderer.Renderer#graphics}.
     */
    @Deprecated
    void remove(Graphic graphic);

    /**
     * Returns the EventManager subinterface, based on the class type.
     * <p>
     * Note: While the class implementing <code>Canvas</code> may additionally
     * implement <code>EventManager</code> subinterface(s). While this design
     * is simple, it limits the <code>Canvas</code> object to the input
     * mechanisms supported by to those particular <code>EventManager</code>
     * subinterface(s), and thus is discouraged.
     * <p>
     * If the class implementing Canvas does <i>not </i> implement the
     * particular <code>EventManager</code> subinterface, then this method can
     * look up the <code>EventManager</code> via an implementation-specific
     * mechanism. Otherwise, if the class implementing Canvas <i>does </i>
     * implement the particular <code>EventManager</code> subinterface, this
     * method can return the <code>this</code> reference.
     *
     * @param eventManagerClass the class type of the EventManager subinterface.
     * @return a class that implements the requested EventManager subinterface,
     *         or null if there is no implementing class.
     */
    EventManager findEventManager(Class eventManagerClass);

    /**
     * Adds the <code>EventManager</code> subinterface if it not currently in
     * the <code>Canvas</code>'s collection of <code>EventManager</code>s.
     *
     * @param eventManager the <code>EventManager</code> type to be added to
     *        the <code>Canvas</code>'s collection.
     */
    void addEventManager(EventManager eventManager);

    /**
     * Returns the top-most <code>Graphic</code> that occupies given
     * <code>DirectPosition</code>. The top-most <code>Graphic</code> will
     * have the highest zOrder.
     *
     * @param directPosition the <code>DirectPosition</code> at which to look
     *   for <code>Graphic</code>s.
     * @return the top-most <code>Graphic</code> at the given
     *   <code>DirectPosition</code>.
     */
    Graphic getTopGraphicAt(DirectPosition directPosition);

    /**
     * Returns the <code>Graphic</code>s that occupy the given
     * <code>DirectPosition</code>. The order is implementation-specific.
     *
     * @param directPosition the <code>DirectPosition</code> at which to look
     *   for <code>Graphic</code>s.
     * @return the array of <code>Graphic</code>s at the given
     *   <code>DirectPosition</code>.
     */
    Graphic[] getGraphicsAt(DirectPosition directPosition);

    /**
     * Returns the <code>Graphic</code>s that occupy the given
     * <code>Envelope</code>. The order is implementation-specific.
     *
     * @param bounds the <code>Envelope</code> in which to look for
     *   <code>Graphic</code>s.
     * @return the array of <code>Graphic</code>s in the given
     *   <code>Envelope</code>.
     */
    Graphic[] getGraphicsIn(Envelope bounds);

    //**  canvas listener methods  **

    /**
     * Adds the given listener that will be notified when the state of this
     * <code>Canvas</code> has changed.
     *
     * @deprecated Moved to {@link org.opengis.display.canvas.canvas#addCanvasListener}.
     */
    @Deprecated
    void addCanvasListener(CanvasListener listener);

    /**
     * Removes the given listener.
     *
     * @deprecated Moved to {@link org.opengis.display.canvas.canvas#removeCanvasListener}.
     */
    @Deprecated
    void removeCanvasListener(CanvasListener listener);

    //**  CanvasManager methods  **

    /**
     * Enables the given <code>CanvasHandler</code>, removing the current
     * handler (if any).  The new handler's
     * <code>handlerEnabled(CanvasController)</code> method is called, passing
     * in a new, active <code>CanvasController</code> that will allow the
     * programmer to modify the <code>Canvas</code>'s properties. <p/>
     * Implementation suggestion:
     *
     * <pre><code>
     *
     * public void enableCanvasHandler(CanvasHandler handler) {
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
     *
     * @deprecated Replaced by {@link org.opengis.display.canvas.canvas#getController}.
     */
    @Deprecated
    void enableCanvasHandler(CanvasHandler handler);

    /**
     * Removes the given <code>CanvasHandler</code> from this
     * <code>Canvas</code>.
     *
     * @deprecated Replaced by {@link org.opengis.display.canvas.canvas#getController}.
     */
    @Deprecated
    void removeCanvasHandler(CanvasHandler handler);

    /**
     * Returns the currently active <code>CanvasHandler</code> or null if no
     * handler is active.
     *
     * @deprecated Replaced by {@code getController}. A side effect is to suppress
     *             one level of indirection.
     */
    @Deprecated
    CanvasHandler getActiveCanvasHandler();

    //**  imlementation hints  **

    /**
     * Sets a rendering hint for implementation or platform specific rendering
     * information.
     *
     * @param hintName the name of the hint.
     * @param hint the rendering hint.
     *
     * @deprecated Moved to {@link org.opengis.display.canvas.Canvas#setRenderingHint}.
     */
    @Deprecated
    void setImplHint(String hintName, Object hint);

    /**
     * Returns the rendering hint associated with the hint name.
     *
     * @param hintName the name of the hint.
     * @return         the rendering hint.
     *
     * @deprecated Moved to {@link org.opengis.display.canvas.Canvas#getRenderingHint}.
     */
    @Deprecated
    Object getImplHint(String hintName);

    //** CoordinateReferenceSystem methods **

    /**
     * Returns the Coordinate Reference System associated with the display of
     * this <code>Canvas</code>. The display Coordinate Reference System
     * corresponds to the geometry of the display device (e.g. video monitor =
     * Cartesian, planetarium = Spherical).
     *
     * @return the display Coordinate Reference System
     *
     * @deprecated Moved to {@link org.opengis.display.canvas.CanvasState#getDisplayCRS}.
     */
    @Deprecated
    CoordinateReferenceSystem getDisplayCoordinateReferenceSystem();

    /**
     * Returns the objective Coordinate Reference System (e.g. the projection of
     * a georeferenced Coordinate Reference System) for this <code>Canvas</code>.
     * This is the default objective Coordinate Reference System for this
     * <code>Canvas</code>.
     *
     * @return the objective Coordinate Reference System
     *
     * @deprecated Moved to {@link org.opengis.display.canvas.CanvasState#getObjectiveCRS}.
     */
    @Deprecated
    CoordinateReferenceSystem getObjectiveCoordinateReferenceSystem();

    /**
     * Sets the objective Coordinate Reference System (e.g. the projection of a
     * georeferenced Coordinate Reference System) for this <code>Canvas</code>.
     * This is the default objective Coordinate Reference System for this
     * <code>Canvas</code>.
     *
     * @param crs the objective Coordinate Reference System
     * @throws IncompatibleOperationException when the specified transformation does not apply to either the objective or the display Coordinate Reference Systems.
     *
     * @deprecated Moved to {@link org.opengis.display.canvas.CanvasSController#setObjectiveCRS}.
     */
    @Deprecated
    void setObjectiveCoordinateReferenceSystem(CoordinateReferenceSystem crs);

    /**
     * Sets the objective Coordinate Reference System (e.g. the projection of a
     * georeferenced Coordinate Reference System) for this <code>Canvas</code>.
     * This is the default objective Coordinate Reference System for this
     * <code>Canvas</code>.
     *
     * @param crs the objective Coordinate Reference System
     * @param objectiveToDisplay the trasformation that converts between this objective Coordinate Reference System and the Canvas display Coordinate Reference System.
     * @param displayToObjective the trasformation that converts between the Canvas display Coordinate Reference System and this objective Coordinate Reference System.
     * @throws IncompatibleOperationException when the specified transformation does not apply to either the objective or the display Coordinate Reference Systems.
     *
     * @deprecated No replacement.
     */
    @Deprecated
    void setObjectiveCoordinateReferenceSystem(CoordinateReferenceSystem crs, MathTransform objectiveToDisplay,
                    MathTransform displayToObjective) throws IncompatibleOperationException;

    /**
     * Returns the coordinate transformation object for this
     * <code>Canvas</code>. This allows the <code>Canvas</code> to resolve
     * conversions of coordinates between the objective and display Coordinate Reference Systems.
     * @return the coordinate transformation object
     *
     * @deprecated Moved to {@link org.opengis.display.canvas.CanvasState#getObjectiveToDisplayTransform}.
     */
    @Deprecated
    MathTransform getObjectiveToDisplayTransform();

    /**
     * Returns the coordinate transformation object for this
     * <code>Canvas</code>. This allows the <code>Canvas</code> to resolve
     * conversions of coordinates between the display and objective Coordinate Reference Systems.
     *
     * @return the coordinate transformation object
     *
     * @deprecated Moved to {@link org.opengis.display.canvas.CanvasState#getDisplayToObjectiveTransform}.
     */
    @Deprecated
    MathTransform getDisplayToObjectiveTransform();
}
