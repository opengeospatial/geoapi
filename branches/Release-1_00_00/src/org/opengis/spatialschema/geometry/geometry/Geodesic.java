/*$************************************************************************************************
 **
 ** $Id$
 **
 ** $Source$
 **
 ** Copyright (C) 2003 Open GIS Consortium, Inc. All Rights Reserved. http://www.opengis.org/Legal/
 **
 *************************************************************************************************/
package org.opengis.spatialschema.geometry.geometry;


/**
 * Two distinct positions joined by a geodesic curve. The control points of a <code>Geodesic</code>
 * shall all lie on the geodesic between its start point and end point. Between these two points,
 * a geodesic curve defined from the {@linkplain org.opengis.referencing.datum.Ellipsoid ellipsoid} or geoid model
 * used by the {@linkplain org.opengis.referencing.crs.CoordinateReferenceSystem coordinate reference system} may
 * be used to interpolate other positions. Any other point in the {@link #getControlPoints controlPoint}
 * array must fall on this geodesic.
 *  
 * @UML abstract GM_Geodesic
 * @author ISO/DIS 19107
 * @author <A HREF="http://www.opengis.org">OpenGIS&reg; consortium</A>
 * @version 2.0
 *
 * @see GeometryFactory#createGeodesic
 */
public interface Geodesic extends GeodesicString {
}
