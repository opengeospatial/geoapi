/**************************************************************************************************
 **
 ** $Id$
 **
 ** $Source$
 **
 ** Copyright (C) 2003 Open GIS Consortium, Inc. All Rights Reserved. http://www.opengis.org/Legal/
 **
 *************************************************************************************************/

package org.opengis.go.display.event;

import org.opengis.go.display.primitive.Graphic;

public class GraphicListenerSupport {
    
    //*************************************************************************
    //  Fields
    //*************************************************************************
    
    private GraphicListener[] listeners;
    
    /**
     * an optional field.  if this Support object knows the graphic it works
     * for, then it can also handle the passingEventsToParent stuff.
     */
    private Graphic graphic;
    
    //*************************************************************************
    //  Constructors
    //*************************************************************************
    
    public GraphicListenerSupport() {
        
    }
    
    /**
     * Constructs a new <code>GraphicListenerSupport</code> that works for the
     * given <code>Graphic</code>.  The <code>Graphic</code> is given to this
     * support object so the support object can handle passingEventsToParent.
     * @param graphic the <code>Graphic</code> this support object works for.
     */
    public GraphicListenerSupport(Graphic graphic) {
        this.graphic = graphic;   
    }
    
    //*************************************************************************
    //
    //*************************************************************************
    
    /**
     * Adds the given listener to this <code>GraphicListenerSupport</code>'s 
     * list of listeners.  Listeners are notified of key, mouse, and change 
     * events that affect the <code>Graphic</code> this 
     * <code>GraphicListenerSupport</code> provides support for.
     * @param listener the <code>GraphicListener</code> to add.
     */
    public synchronized void addGraphicListener(GraphicListener listener) {
        if (listeners == null) {
            listeners = new GraphicListener[] { listener };
            return;
        }
        GraphicListener[] newListeners = new GraphicListener[listeners.length + 1];
        System.arraycopy(listeners, 0, newListeners, 0, listeners.length);
        newListeners[listeners.length] = listener;
        listeners = newListeners;
    }

    /**
     * Removes the given listener from this 
     * <code>GraphicListenerSupport</code>'s list of listeners.  
     * @param listener the <code>GraphicListener</code> to remove.
     */
    public synchronized void removeGraphicListener(GraphicListener listener) {
        int length = (listeners != null) ? listeners.length : 0;
        for (int i = 0; i < length; i++) {
            if (listeners[i] == listener) {
                GraphicListener[] newListeners = new GraphicListener[length - 1];   
                
                if (length > 1) {
                    if (i == length) {
                        // removing the last listener from the array
                        System.arraycopy(listeners, 0, newListeners, 0, length - 1);
                    } else if (i == 0) {
                        // removing the first listener from the array
                        System.arraycopy(listeners, 1, newListeners, 0, length - 1);
                    } else {
                        // removing a listener from the middle of the array
                        System.arraycopy(listeners, 0, newListeners, 0, i);
                        System.arraycopy(listeners, i + 1, newListeners, i, length - i - 1);
                    }
                    break;
                }
                listeners = newListeners;
            }
        }
    }
    
    private boolean isEmpty() {
        return listeners == null || listeners.length == 0;
    }
    
    /**
     * Calls the <code>mouseClicked()</code> method of all listeners in this 
     * <code>GraphicListenerSupport<code>'s array of listeners.
     * @param gme the <code>GraphicMouseEvent</code> to give to the listeners.
     */
    public void fireMouseClicked(GraphicMouseEvent gme) {
        if (isEmpty()) {
            return;
        }
        GraphicListener[] holder = listeners;
        for (int i = 0; i < holder.length; i++) {
            holder[i].mouseClicked(gme);   
        }
        if (graphic != null && graphic.isPassingEventsToParent()) {
            Graphic parent = graphic.getParent();
            if (parent != null) {
                parent.fireGraphicEvent(gme);   
            }
        }
    }
    
    /**
     * Calls the <code>mousePressed()</code> method of all listeners in this 
     * <code>GraphicListenerSupport<code>'s array of listeners.
     * @param gme the <code>GraphicMouseEvent</code> to give to the listeners.
     */
    public void fireMousePressed(GraphicMouseEvent gme) {
        if (isEmpty()) {
            return;
        }
        GraphicListener[] holder = listeners;
        for (int i = 0; i < holder.length; i++) {
            holder[i].mousePressed(gme);   
        }
        if (graphic != null && graphic.isPassingEventsToParent()) {
            Graphic parent = graphic.getParent();
            if (parent != null) {
                parent.fireGraphicEvent(gme);
            }
        }
    }
    
    /**
     * Calls the <code>mouseReleased()</code> method of all listeners in this 
     * <code>GraphicListenerSupport<code>'s array of listeners.
     * @param gme the <code>GraphicMouseEvent</code> to give to the listeners.
     */
    public void fireMouseReleased(GraphicMouseEvent gme) {
        if (isEmpty()) {
            return;
        }
        GraphicListener[] holder = listeners;
        for (int i = 0; i < holder.length; i++) {
            holder[i].mouseReleased(gme);   
        }
        if (graphic != null && graphic.isPassingEventsToParent()) {
            Graphic parent = graphic.getParent();
            if (parent != null) {
                parent.fireGraphicEvent(gme);   
            }
        }
    }
    
    /**
     * Calls the <code>mouseDwelled()</code> method of all listeners in this 
     * <code>GraphicListenerSupport<code>'s array of listeners.
     * @param gme the <code>GraphicMouseEvent</code> to give to the listeners.
     */
    public void fireMouseDwelled(GraphicMouseEvent gme) {
        if (isEmpty()) {
            return;
        }
        GraphicListener[] holder = listeners;
        for (int i = 0; i < holder.length; i++) {
            holder[i].mouseDwelled(gme);   
        }
        if (graphic != null && graphic.isPassingEventsToParent()) {
            Graphic parent = graphic.getParent();
            if (parent != null) {
                parent.fireGraphicEvent(gme);   
            }
        }
    }
    
    /**
     * Calls the <code>graphicSelected()</code> method of all listeners in this 
     * <code>GraphicListenerSupport<code>'s array of listeners.
     * @param gme the <code>GraphicChangeEvent</code> to give to the listeners.
     */
    public void fireGraphicSelected(GraphicChangeEvent gce) {
        if (isEmpty()) {
            return;
        }
        GraphicListener[] holder = listeners;
        for (int i = 0; i < holder.length; i++) {
            holder[i].graphicSelected(gce);   
        }
    }
    
    /**
     * Calls the <code>graphicdeSelected()</code> method of all listeners in 
     * this <code>GraphicListenerSupport<code>'s array of listeners.
     * @param gme the <code>GraphicChangeEvent</code> to give to the listeners.
     */
    public void fireGraphicDeselected(GraphicChangeEvent gce) {
        if (isEmpty()) {
            return;
        }
        GraphicListener[] holder = listeners;
        for (int i = 0; i < holder.length; i++) {
            holder[i].graphicDeselected(gce);   
        }
    }
    
    /**
     * Calls the <code>graphicDisposed()</code> method of all listeners in this 
     * <code>GraphicListenerSupport<code>'s array of listeners.
     * @param gme the <code>GraphicChangeEvent</code> to give to the listeners.
     */
    public void fireGraphicDisposed(GraphicChangeEvent gce) {
        if (isEmpty()) {
            return;
        }
        GraphicListener[] holder = listeners;
        for (int i = 0; i < holder.length; i++) {
            holder[i].graphicDisposed(gce);   
        }
    }
    
    /**
     * Calls the <code>graphicEditableStart()</code> method of all listeners in
     * this <code>GraphicListenerSupport<code>'s array of listeners.
     * @param gme the <code>GraphicChangeEvent</code> to give to the listeners.
     */
    public void fireGraphicEditableStart(GraphicChangeEvent gce) {
        if (isEmpty()) {
            return;
        }
        GraphicListener[] holder = listeners;
        for (int i = 0; i < holder.length; i++) {
            holder[i].graphicEditableStart(gce);   
        }
    }
    
    /**
     * Calls the <code>graphicEditableChanged()</code> method of all listeners 
     * in this <code>GraphicListenerSupport<code>'s array of listeners.
     * @param gme the <code>GraphicChangeEvent</code> to give to the listeners.
     */
    public void fireGraphicEditableChanged(GraphicChangeEvent gce) {
        if (isEmpty()) {
            return;
        }
        GraphicListener[] holder = listeners;
        for (int i = 0; i < holder.length; i++) {
            holder[i].graphicEditableChanged(gce);   
        }
    }
    
    /**
     * Calls the <code>graphicEditableEnd()</code> method of all listeners in
     * this <code>GraphicListenerSupport<code>'s array of listeners.
     * @param gme the <code>GraphicChangeEvent</code> to give to the listeners.
     */
    public void fireGraphicEditableEnd(GraphicChangeEvent gce) {
        if (isEmpty()) {
            return;
        }
        GraphicListener[] holder = listeners;
        for (int i = 0; i < holder.length; i++) {
            holder[i].graphicEditableEnd(gce);   
        }
    }
    
    /**
     * Calls the <code>graphicChanged()</code> method of all listeners in this
     * <code>GraphicListenerSupport<code>'s array of listeners.
     * @param gme the <code>GraphicChangeEvent</code> to give to the listeners.
     */
    public void fireGraphicChanged(GraphicChangeEvent gce) {
    if (isEmpty()) {
            return;
        }
        GraphicListener[] holder = listeners;
        for (int i = 0; i < holder.length; i++) {
            holder[i].graphicChanged(gce);   
        }
    }
    
}