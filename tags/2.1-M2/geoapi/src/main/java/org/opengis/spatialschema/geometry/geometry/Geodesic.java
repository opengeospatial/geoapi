/*$************************************************************************************************
 **
 ** $Id$
 **
 ** $URL$
 **
 ** Copyright (C) 2003-2005 Open GIS Consortium, Inc.
 ** All Rights Reserved. http://www.opengis.org/legal/
 **
 *************************************************************************************************/
package org.opengis.spatialschema.geometry.geometry;

// Annotations
import org.opengis.annotation.UML;
import static org.opengis.annotation.Obligation.*;
import static org.opengis.annotation.Specification.*;


/**
 * Two distinct positions joined by a geodesic curve. The control points of a {@code Geodesic}
 * shall all lie on the geodesic between its start point and end point. Between these two points,
 * a geodesic curve defined from the {@linkplain org.opengis.referencing.datum.Ellipsoid ellipsoid} or geoid model
 * used by the {@linkplain org.opengis.referencing.crs.CoordinateReferenceSystem coordinate reference system} may
 * be used to interpolate other positions. Any other point in the {@link #getControlPoints controlPoint}
 * array must fall on this geodesic.
 *  
 * @version <A HREF="http://www.opengis.org/docs/01-101.pdf">Abstract specification 5</A>
 * @author Martin Desruisseaux (IRD)
 * @since GeoAPI 1.0
 *
 * @see GeometryFactory#createGeodesic
 */
@UML(identifier="GM_Geodesic", specification=ISO_19107)
public interface Geodesic extends GeodesicString {
}
