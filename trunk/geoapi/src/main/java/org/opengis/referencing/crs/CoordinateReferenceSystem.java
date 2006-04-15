/*$************************************************************************************************
 **
 ** $Id$
 **
 ** $Source$
 **
 ** Copyright (C) 2003-2005 Open GIS Consortium, Inc.
 ** All Rights Reserved. http://www.opengis.org/legal/
 **
 *************************************************************************************************/
package org.opengis.referencing.crs;

// OpenGIS direct dependencies
import org.opengis.referencing.ReferenceSystem;
import org.opengis.referencing.cs.CoordinateSystem;

// Annotations
import org.opengis.annotation.UML;
import static org.opengis.annotation.Obligation.*;
import static org.opengis.annotation.Specification.*;


/**
 * Abstract coordinate reference system, usually defined by a 
 * {@linkplain org.opengis.referencing.cs.CoordinateSystem coordinate system} and a
 * {@linkplain org.opengis.referencing.datum.Datum datum}. The concept of a coordinate
 * reference system (CRS) captures the choice of values for the parameters that constitute
 * the degrees of freedom of the coordinate space. The fact that such a choice has to be made,
 * either arbitrarily or by adopting values from survey measurements, leads to the large number
 * of coordinate reference systems in use around the world. It is also the cause of the little
 * understood fact that the latitude and longitude of a point are not unique. Without the full
 * specification of the coordinate reference system, coordinates are ambiguous at best and
 * meaningless at worst. However for some interchange purposes it is sufficient to confirm the
 * {@linkplain #getName identity of the system} without necessarily having the full system
 * definition.
 * <p>
 * The concept of coordinates may be expanded from a strictly spatial context to include time.
 * Time is then added as another coordinate to the coordinate tuple. It is even possible to add
 * two time-coordinates, provided the two coordinates describe different independent quantities.
 * An example of the latter is the time/space position of a subsurface point of which the vertical
 * coordinate is expressed as the two-way travel time of a sound signal in milliseconds, as is
 * common in seismic imaging. A second time-coordinate indicates the time of observation, usually
 * expressed in whole years.
 *
 * @version <A HREF="http://portal.opengeospatial.org/files/?artifact_id=6716">Abstract specification 2.0</A>
 * @author ISO/DIS 19111
 * @author Martin Desruisseaux (IRD)
 * @since GeoAPI 1.0
 */
@UML(identifier="SC_CRS", specification=ISO_19111)
public interface CoordinateReferenceSystem extends ReferenceSystem {
    /**
     * Returns the coordinate system. One of {@linkplain CoordinateSystem coordinate system}
     * sub-interfaces is associated with {@linkplain SingleCRS single CRS}. Other CRS
     * like {@linkplain CompoundCRS compound CRS} may also provides a synthetic coordinate
     * system in order to allow users to access to two commonly requested information:
     * {@linkplain CoordinateSystem#getDimension dimension} and
     * {@linkplain CoordinateSystem#getAxis axis}.
     */
    CoordinateSystem getCoordinateSystem();
}
