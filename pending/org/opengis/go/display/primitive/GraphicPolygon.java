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

// OpenGIS direct dependencies
import org.opengis.go.display.event.AggregationChangeEvent;
import org.opengis.go.display.event.AggregationListener;
import org.opengis.go.display.style.PolygonSymbolizer;
import org.opengis.go.spatial.PathType;
import org.opengis.spatialschema.geometry.DirectPosition;
import org.opengis.spatialschema.geometry.geometry.Polygon;
import org.opengis.spatialschema.geometry.primitive.Ring;

import java.util.List;

/**
 * Defines a common abstraction for graphic representation of polygons.  
 * A <code>GraphicPolygon</code> consists of a an exterior GraphicRing
 * and a set of non-mutually-overlapping interior GraphicRings.
 *
 * @author <A HREF="http://www.opengis.org">OpenGIS&reg; consortium</A>
 * @version $Revision$, $Date$
 * @since GO 1.1
 */
public interface GraphicPolygon extends Graphic {
	
    /**
     * Sets the geometry based on ISO 19107 geometric forms.
     *
     * @param polygon a geometry Polygon.
     */
    public void setPolygon(Polygon polygon);

    // Once you have the Polygon you can get the SurfaceBoundary, from which
    // you can get the exterior Ring and the interior Rings
    /**
     * Returns the geometry based on ISO 19107 geometric forms.
     *
     * @return the geometry Polygon.
     */
    public Polygon getPolygon();

    /**
     * Sets the exterior graphic boundary of the polygon.
     *
     * @param ring The exterior <code>GraphicRing</code>
     */
    public void setExteriorGraphicRing(GraphicRing ring);

    /**
     * Returns the exterior graphic boundary of the polygon.
     *
     * @return The exterior <code>GraphicRing</code>
     */
    public GraphicRing getExteriorGraphicRing();

    /**
     * Returns an editable List of the interior graphic boundaries of the polygon.
     *
     * @return List of the interior <code>GraphicRing</code>s
     */
    public List getInteriorGraphicRingList();
    
	/**
	 * For a <code>GraphicPolygon</code> to be valid,
	 * all constituent <code>GraphicRing</code>s must be valid,
	 * interior <code>GraphicRing</code>s must indeed be interior
	 * to the exterior <code>GraphicRing</code>, and no <code>GraphicRing</code>
	 * may overlap another <code>GraphicRing</code>.
	 * This method returns true if these conditions are met, false otherwise.
	 * @return true if composition of the <code>GraphicPolygon</code> is valid.
	 */
    public boolean isValid();

    /**
     * Adds the given <code>GraphicPolygon</code> to this 
     * <code>GraphicPolygon</code>'s list of listeners. The listeners will
     * be notified if this <code>GraphicPolygon</code> adds or removes any 
     * interior Rings.
     * 
     * @param listener the <code>AggregationListener</code> to be added.
     */
    public void addAggregationListener(AggregationListener listener);

    /**
     * Removes the given <code>AggregationListener</code> from this 
     * <code>GraphicPolygon</code>'s list of listeners.
     * 
     * @param listener the <code>AggregationListener</code> to be removed.
     */
    public void removeAggregationListener(AggregationListener listener);

    /**
     * Calls the <code>aggregationChanged()</code> method of all <code>AggregationListener</code>s
     * in this <code>GraphicPolygon</code>'s list of listeners. This method is called when any 
     * interior Rings are added, removed, or reordered in this <code>GraphicPolygon</code>.
     * 
     * @param event the <code>AggregationChangedEvent</code> to give to the listeners.
     *
     * @revisit Usually, this kind of method is a protected one in the implementation class,
     *          not a public method in the interface...
     */
    public void aggregationChanged(AggregationChangeEvent event);
    
    /**
     * Returns the <code>GraphicStyle</code> for this <code>GraphicPolygon</code>,
     * which is required to be a <code>PolygonSymbolizer</code>.
     * @return the GraphicPolygon's <code>GraphicStyle</code>.
     */
    public PolygonSymbolizer getPolygonSymbolizer();

}
