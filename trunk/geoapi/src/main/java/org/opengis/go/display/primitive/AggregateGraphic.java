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

import org.opengis.go.display.event.AggregationChangeEvent;
import org.opengis.go.display.event.AggregationListener;


/**
 * A common abstraction for implementations of aggregated {@link Graphic}s.
 * The abstraction makes no assumptions as to how the <code>Graphic</code>s are
 * stored within the aggregate. For example, the <code>Graphic</code>s may be
 * stored in an array such that the <code>Graphic</code> in the zeroeth element
 * of the array is considered the front-most object and the <code>Graphic</code>
 * in the largest element of the array is considered the bottom-most object. Or, the
 * <code>Graphic</code>s may be stored in a Skiplist sorted by a unique
 * <code>Graphic</code> identifier to make adding and removing
 * <code>Graphic</code>s very efficient.
 * <p>
 * Additionally, the abstraction makes no
 * assumptions as to thread safety. If the implementations of
 * <code>Graphic</code> are to be used in a multi-threaded environment,
 * the implementation will have to address thread safety by synchronizing
 * methods or by invoking all methods from a single thread.
 *
 * @author <A HREF="http://www.opengis.org">OpenGIS&reg; consortium</A>
 */
public interface AggregateGraphic extends Graphic {
    /**
     * Sets the children of this <code>AggregateGraphic</code> to the given
     * set of <code>Graphic</code>s.  If there are already children assigned,
     * then those children will be removed and their parent will be set to
     * {@code null}.
     *
     * @param children the new children of the <code>AggregateGraphic</code>.
     */
    void setChildren(Graphic[] children);

    /**
     * Returns the children of this <code>AggregateGraphic</code>. If no
     * children are assigned, the method returns an empty array.
     *
     * @return the child <code>Graphic</code>s of the <code>AggregateGraphic</code>.
     */
    Graphic[] getChildren();

    /**
     * Adds the given child to this <code>AggregateGraphic</code>.
     *
     * @param child <code>Graphic</code> child to add to the aggregate.
     * @return the added <code>Graphic</code>.
     */
    Graphic addChild(Graphic child);

    /**
     * Adds the given children to this <code>AggregateGraphic</code>.
     *
     * @param children <code>Graphic</code> children to add to the aggregate.
     * @return the added <code>Graphic</code>s.
     */
    Graphic[] addChildren(Graphic[] children);

    /**
     * Removes the given child from this <code>AggregateGraphic</code>.
     *
     * @param child the <code>Graphic</code> child to remove from the aggregate.
     * @return the removed <code>Graphic</code>, or {@code null} if it is not found.
     */
    Graphic removeChild(Graphic child);

    /**
     * Removes the given children from this <code>AggregateGraphic</code>.
     *
     * @param children the <code>Graphic</code> children to remove from the aggregate.
     * @return the removed <code>Graphic</code>s, or {@code null} if none are found.
     */
    Graphic[] removeChildren(Graphic[] children);

    /**
     * Removes all of the children from this <code>AggregateGraphic</code>.
     */
    void removeChildren();

    /**
     * Replaces the <code>oldChild</code> with the <code>newChild</code>.
     *
     * @param oldChild the child to be replaced.
     * @param newChild the child to be added in its place.
     * @return the <code>newChild</code> that replaced the <code>oldChild</code>.
     */
    Graphic replaceChild(Graphic oldChild, Graphic newChild);

    /**
     * Returns the number of children currently assigned to this
     * <code>AggregateGraphic</code>.
     *
     * @return the number of children currently assigned.
     */
    int getChildCount();

    /**
     * Adds the given <code>AggregationListener</code> to this
     * <code>AggregateGraphic</code>'s list of listeners. The listeners will
     * be notified if this <code>AggregateGraphic</code> adds or removes any elements.
     *
     * @param listener the <code>AggregationListener</code> to be added.
     */
    void addAggregationListener(AggregationListener listener);

    /**
     * Removes the given <code>AggregationListener</code> from this
     * <code>AggregateGraphic</code>'s list of listeners.
     *
     * @param listener the <code>AggregationListener</code> to be removed.
     */
    void removeAggregationListener(AggregationListener listener);

    /**
     * Calls the <code>aggregationChanged()</code> method of all <code>AggregationListener</code>s
     * in this <code>AggregateGraphic</code>'s list of listeners. This method is called when any
     * elements are added, removed, or reorderd in this <code>AggregateGraphic</code>.
     *
     * @param event the <code>AggregationChangedEvent</code> to give to the listeners.
     *
     * @todo Usually, this kind of method is a protected one in the implementation class,
     *       not a public method in the interface...
     */
    void aggregationChanged(AggregationChangeEvent event);
}
