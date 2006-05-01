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

// OpenGIS dependencies
import org.opengis.go.display.event.AggregationChangeEvent;
import org.opengis.go.display.event.AggregationListener;
import org.opengis.spatialschema.geometry.complex.CompositeCurve;


/**
 * Defines a common abstraction for general implementations of aggregated <code>Graphic</code>s.
 * <p>
 * The abstraction assumes that each of the <code>Graphic</code>s are each topologically open
 * and the ordered aggregation is piecewise continuous in the zeroith degree, i.e. piecewise,
 * the endpoint of one <code>Graphic</code> is the same location as the startpoint of the next
 * <code>Graphic</code> in the order.
 * <p>
 * If the endpoint of the last <code>Graphic</code> in the aggregation 
 * is in the same location as the startpoint of the first <code>Graphic</code>,
 * then the <code>GraphicCompositeCurve</code> is <i>closed</i>, and the 
 * <code>isClosed()</code> method returns true. Otherwise this method returns
 * false.
 * <p>
 * The abstraction makes no assumptions as to thread safety. If the implementations of
 * <code>Graphic</code> are to be used in a multi-threaded environment, the implementation
 * will have to address thread safety by synchronizing methods or by invoking all methods
 * from a single thread.
 * 
 * @author <A HREF="http://www.opengis.org">OpenGIS&reg; consortium</A>
 * @version $Revision: 659 $, $Date: 2006-02-23 14:07:07 +1100 (jeu., 23 févr. 2006) $
 */
public interface GraphicCompositeCurve extends Graphic {
    
    /**
     * Sets the geometry based on ISO 19107 <code>CompositeCurve</code> geometry for this <code>Graphic</code>. 
     * @param compositeCurve the CompositeCurve for this <code>Graphic</code>.
     */
    public void setCompositeCurve(CompositeCurve compositeCurve) throws GeometryNotSupportedException;
    
    /**
     * Returns the ISO 19107 <code>CompositeCurve</code> geometry for this <code>Graphic</code>
     * @return the CompositeCurve representing this GraphicCompositeCurve
     */
    public CompositeCurve getCompositeCurve();

    /**
     * Returns whether this composite curve is topologically closed.
     * If the endpoint of the last <code>Graphic</code> in the aggregation 
     * is in the same location as the startpoint of the first <code>Graphic</code>,
     * then the <code>GraphicCompositeCurve</code> is <i>closed</i>, and the 
     * <code>isClosed()</code> method returns true. Otherwise this method returns
     * false.
     * 
     * @return true if the composite curve is closed.
     */
    public boolean isClosed();
    
	/**
	 * Sets the segments of this <code>GraphicCompositeCurve</code> to the given
	 * set of <code>Graphic</code>s.  If there are already segments assigned,
	 * then those segments will be removed and their parent will be set to
	 * null.
	 * @param segments the new segments of the <code>GraphicCompositeCurve</code>.
	 * 
	 */
    public void setSegments(Graphic[] segments);

	/**
	 * Replaces the oldSegment with the newSegment.
	 * @param oldSegment the segment to be replaced.
	 * @param newSegment the segment to be added in its place.
	 * @return the newSegment that replaced the oldSegment.
	 */
    public Graphic replaceSegment(Graphic oldSegment, Graphic newSegment);

	/**
	 * Removes all of the segments from this <code>GraphicCompositeCurve</code>.
	 */
    public void removeSegments();

	/**
	 * Removes the given segment from this <code>GraphicCompositeCurve</code>.
	 * @param segment the <code>Graphic</code> segment to remove from the
	 * composite curve.
	 * @return the removed <code>Graphic</code>, or null if it is not found.
	 */
    public Graphic removeSegment(Graphic segment);

	/**
	 * Removes the segment at the given index.
	 * 
	 * @param index
     * @return Returns the segment just removed.
	 */
    public Graphic removeSegment(int index);

	/**
	 * Returns whether the <code>GraphicCompositeCurve</code> is continuous.
	 * If all <code>Graphic</code>s in the aggregation are each topologically
	 * open and the ordered aggregation is piecewise continuous in degree zero,
	 * then the <code>GraphicCompositeCurve</code> is valid, and this method returns
	 * true. Otherwise this method returns false.
	 * @return true if the <code>GraphicCompositeCurve</code> is continuous.
	 */
    public boolean isValid();

	/**
     * Adds a segment into this composite curve, inserting it before the segment
	 * at the given index.  The existing segment at the given index and
	 * all those with a larger index have their index increased by one.
	 * 
     * @param index Index where the new segment will be added.
     * @param segment New segment Graphic to add.
     * @return Returns the segment just added.
	 */
    public Graphic insertSegment(int index, Graphic segment);

	/**
	 * Returns the segments of this <code>GraphicCompositeCurve</code>. If no
	 * segments are assigned, the method returns an empty array.
	 * @return the segment <code>Graphic</code>s of the <code>GraphicCompositeCurve</code>.
	 */
    public Graphic[] getSegments();

	/**
	 * Returns the number of segments currently assigned to this
	 * <code>GraphicCompositeCurve</code>.
	 * @return the number of segments currently assigned.
	 */
    public int getSegmentCount();

	/**
     * Retrieves the segment Graphic at the given index.
	 * @param index the index of the segment of interest.
	 * @return the segment at the index.
	 */
    public Graphic getSegment(int index);

	/**
	 * Adds the given segment to this <code>GraphicCompositeCurve</code>.
	 * @param segment <code>Graphic</code> segment to add to the composite curve.
	 * @return the added <code>Graphic</code>.
	 */
    public Graphic addSegment(Graphic segment);
	
	/**
	 * Adds the given <code>AggregationListener</code> to this 
	 * <code>GraphicCompositeCurve</code>'s list of listeners. The listeners will
	 * be notified if this <code>GraphicCompositeCurve</code> adds or removes any elements.
	 * 
	 * @param listener the <code>AggregationListener</code> to be added.
	 */
	public void addAggregationListener(AggregationListener listener);
    
	/**
	 * Removes the given <code>AggregationListener</code> from this 
	 * <code>GraphicCompositeCurve</code>'s list of listeners.
	 * 
	 * @param listener the <code>AggregationListener</code> to be removed.
	 */
	public void removeAggregationListener(AggregationListener listener);
	
	/**
	 * Calls the <code>aggregationChanged()</code> method of all <code>AggregationListener</code>s
	 * in this <code>GraphicCompositeCurve</code>'s list of listeners. This method is called when any 
	 * elements are added, removed, or reorderd in this <code>GraphicCompositeCurve</code>.
	 * 
	 * @param event the <code>AggregationChangedEvent</code> to give to the listeners.
	 */
	public void aggregationChanged(AggregationChangeEvent event);
}

