/*$************************************************************************************************
 **
 ** $Id$
 **
 ** $Source$
 **
 ** Copyright (C) 2003 Open GIS Consortium, Inc. All Rights Reserved. http://www.opengis.org/Legal/
 **
 *************************************************************************************************/
package org.opengis.referencing.crs;

// OpenGIS direct dependencies
import org.opengis.referencing.ReferenceSystem;
import org.opengis.referencing.cs.CoordinateSystem; // For javadoc
import org.opengis.referencing.cs.CoordinateSystemAxis;


/**
 * Abstract coordinate reference system, usually defined by a coordinate system and a datum.
 *
 * @UML abstract SC_CRS
 * @author ISO 19111
 * @author <A HREF="http://www.opengis.org">OpenGIS&reg; consortium</A>
 * @version 2.0
 */
public interface CoordinateReferenceSystem extends ReferenceSystem {
    /**
     * Returns the dimension of the underlying {@linkplain CoordinateSystem coordinate system}.
     * For {@linkplain SingleCRS single CRS}, this is equivalent to
     * <code>{@linkplain SingleCRS#getCoordinateSystem() getCoordinateSystem}().{@linkplain
     * CoordinateSystem#getDimension getDimension}()</code>.
     * For {@linkplain CompoundCRS compound CRS}, this is equals to the sum of the dimension of
     * each inner CRS.
     *
     * @return The dimension of this coordinate reference system.
     */
    int getDimension();

    /**
     * Returns the axis for the underlying {@linkplain CoordinateSystem coordinate system} at
     * the specified dimension. For {@linkplain SingleCRS single CRS}, this is equivalent to
     * <code>{@linkplain SingleCRS#getCoordinateSystem() getCoordinateSystem}().{@linkplain
     * CoordinateSystem#getAxis getAxis}(dimension)</code>.
     *
     * @param  dimension The zero based index of axis.
     * @return The axis at the specified dimension.
     * @throws IndexOutOfBoundsException if <code>dimension</code> is out of bounds.
     */
    CoordinateSystemAxis getAxis(int dimension) throws IndexOutOfBoundsException;
}
