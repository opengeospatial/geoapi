/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    http://www.geoapi.org
 *
 *    Copyright (C) 2004-2021 Open Geospatial Consortium, Inc.
 *    All Rights Reserved. http://www.opengeospatial.org/ogc/legal
 *
 *    Permission to use, copy, and modify this software and its documentation, with
 *    or without modification, for any purpose and without fee or royalty is hereby
 *    granted, provided that you include the following on ALL copies of the software
 *    and documentation or portions thereof, including modifications, that you make:
 *
 *    1. The full text of this NOTICE in a location viewable to users of the
 *       redistributed or derivative work.
 *    2. Notice of any changes or modifications to the OGC files, including the
 *       date changes were made.
 *
 *    THIS SOFTWARE AND DOCUMENTATION IS PROVIDED "AS IS," AND COPYRIGHT HOLDERS MAKE
 *    NO REPRESENTATIONS OR WARRANTIES, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED
 *    TO, WARRANTIES OF MERCHANTABILITY OR FITNESS FOR ANY PARTICULAR PURPOSE OR THAT
 *    THE USE OF THE SOFTWARE OR DOCUMENTATION WILL NOT INFRINGE ANY THIRD PARTY
 *    PATENTS, COPYRIGHTS, TRADEMARKS OR OTHER RIGHTS.
 *
 *    COPYRIGHT HOLDERS WILL NOT BE LIABLE FOR ANY DIRECT, INDIRECT, SPECIAL OR
 *    CONSEQUENTIAL DAMAGES ARISING OUT OF ANY USE OF THE SOFTWARE OR DOCUMENTATION.
 *
 *    The name and trademarks of copyright holders may NOT be used in advertising or
 *    publicity pertaining to the software without specific, written prior permission.
 *    Title to copyright in this software and any associated documentation will at all
 *    times remain with copyright holders.
 */

/**
 * {@linkplain org.opengis.referencing.datum.Datum Geodetic datum} (the relationship of a
 * {@linkplain org.opengis.referencing.cs.CoordinateSystem coordinate system} to the earth).
 * The following is adapted from
 * {@linkplain org.opengis.annotation.Specification#ISO_19111 OpenGIS® Spatial Referencing by
 * Coordinates (Topic 2)} specification.
 *
 * <p>A datum can be used as the basis for one-, two- or three-dimensional systems.
 * For {@linkplain org.opengis.referencing.crs.GeodeticCRS geodetic}
 * and {@linkplain org.opengis.referencing.crs.VerticalCRS vertical} coordinate reference systems,
 * the datum shall relate the coordinate system to the Earth.
 * With other types of coordinate reference systems (CRS), the datum may relate the coordinate system
 * to another physical or virtual object. In some applications of an
 * {@linkplain org.opengis.referencing.crs.EngineeringCRS Engineering CRS}, the object may be a platform
 * moving relative to the Earth. In these applications, the datum itself is not time-dependent,
 * but any transformations of the associated coordinates to an Earth-fixed or other coordinate reference system
 * shall contain time-dependent parameters.</p>
 *
 * <p>Five subtypes of datum are specified:
 * {@linkplain org.opengis.referencing.datum.GeodeticDatum geodetic},
 * {@linkplain org.opengis.referencing.datum.VerticalDatum vertical},
 * {@linkplain org.opengis.referencing.datum.EngineeringDatum engineering},
 * {@linkplain org.opengis.referencing.datum.ImageDatum image} and
 * {@linkplain org.opengis.referencing.datum.TemporalDatum temporal}.
 * Each datum subtype can be associated only with specific types of coordinate reference systems:</p>
 *
 * <ul>
 *   <li>A geodetic datum is used with three-dimensional or horizontal (two-dimensional) coordinate reference systems,
 *       and requires an ellipsoid definition and a prime meridian definition.
 *       It is used to describe large portions of the earth's surface up to the entire earth's surface.</li>
 *   <li>A vertical datum can only be associated with a vertical coordinate reference system.</li>
 *   <li>Image datum and engineering datum are both used in a local context only:
 *       to describe the origin of an image and the origin of an engineering (or local) coordinate reference system.</li>
 * </ul>
 *
 * <h2>Vertical datum</h2>
 * <p>Further sub-typing is required to describe vertical datums adequately.
 * The following types of vertical datum are distinguished:</p>
 * <ul>
 *   <li><p><b>Geoidal</b><br>
 *   The zero value of the associated (vertical) coordinate system axis is defined
 *   to approximate a constant potential surface, usually the geoid. Such a reference
 *   surface is usually determined by a national or scientific authority and is then
 *   a well known, named datum. This is the default vertical datum type, because it is
 *   the most common one encountered.</p></li>
 *
 *   <li><p><b>Depth</b><br>
 *   The zero point of the vertical axis is defined by a surface that has meaning for
 *   the purpose the associated vertical measurements are used for. For hydrographic
 *   charts, this is often a predicted nominal sea surface (i.e., without waves or
 *   other wind and current effects) that occurs at low tide. Examples are Lowest
 *   Astronomical Tide and Lowest Low Water Spring. A different example is a sloping
 *   and undulating River Datum defined as the nominal river water surface occurring
 *   at a quantified river discharge.</p></li>
 *
 *   <li><p><b>Barometric</b><br>
 *   A vertical datum is of type "barometric" if atmospheric pressure is the basis
 *   for the definition of the origin. Atmospheric pressure may be used as the
 *   intermediary to determine height (barometric height determination) or it may
 *   be used directly as the vertical coordinate, against which other parameters are
 *   measured. The latter case is applied routinely in meteorology.</p>
 *
 *   <p>Barometric height determination is routinely used in aircraft.
 *   The altimeter (barometer) on board is set to the altitude of the airfield at the
 *   time of take-off, which corrects simultaneously for instantaneous air pressure and
 *   altitude of the airfield. The measured height value is commonly named "altitude".</p>
 *
 *   <p>In some land surveying applications height differences between
 *   points are measured with barometers. To obtain absolute heights the measured height
 *   differences are added to the known heights of control points. In that case the vertical
 *   datum type is not barometric, but is the same as that of the vertical control network
 *   used to obtain the heights of the new points and its vertical datum type.</p>
 *
 *   <p>The accuracy of this technique is limited, as it is affected
 *   strongly by the spatial and temporal variability of atmospheric pressure. This
 *   accuracy limitation impacts the precision of the associated vertical datum definition.
 *   The datum is usually the surface of constant atmospheric pressure approximately
 *   equating to mean sea level (MSL). The origin or anchor point is usually a point
 *   of known MSL height. The instruments are calibrated at this point by correcting
 *   for the instantaneous atmospheric pressure at sea level and the height of the
 *   point above MSL.</p>
 *
 *   <p>In meteorology, atmospheric pressure routinely takes the role
 *   as vertical coordinate in a CRS that is used as a spatial reference frame for
 *   meteorological parameters in the upper atmosphere. The origin of the datum
 *   is in that case the (hypothetical) zero atmospheric pressure and the positive
 *   vertical axis points down (to increasing pressure).</p></li>
 *
 *   <li><p><b>Other surface</b><br>
 *   In some cases, e.g. oil exploration and production, geological features,
 *   i.e., the top or bottom of a geologically identifiable and meaningful subsurface
 *   layer, are sometimes used as a vertical datum. Other variations to the above three
 *   vertical datum types may exist and are all bracketed in this category.</p></li>
 * </ul>
 *
 * <h2>Image datum</h2>
 * <p>The image pixel grid is defined as the set of lines of constant
 * integer coordinate values. The term "image grid" is often used in other standards to
 * describe the concept of Image CRS. However, care must be taken to correctly interpret
 * this term in the context in which it is used. The term "grid cell" is often used as a
 * substitute for the term "pixel".</p>
 *
 * <p>The grid lines of the image may be associated in two ways with
 * the data attributes of the pixel or grid cell (ISO CD 19123). The data attributes
 * of the image usually represent an average or integrated value that is associated
 * with the entire pixel.</p>
 *
 * <p>An image grid can be associated with this data in such a way
 * that the grid lines run through the centres of the pixels. The cell centres will
 * thus have integer coordinate values. In that case the attribute "pixel in cell"
 * will have the value "cell centre".</p>
 *
 * <p>Alternatively, the image grid may be defined such that the
 * grid lines associate with the cell or pixel corners rather than the cell centres.
 * The cell centres will thus have noninteger coordinate values, the fractional parts
 * always being 0.5. ISO CD 19123 calls the grid points in this latter case "posts"
 * and associated image data: "matrix data". The attribute "pixel in cell" will now
 * have the value "cell corner".</p>
 *
 * <p>This difference in perspective has no effect on the image
 * interpretation, but is important for coordinate transformations involving this
 * defined image.</p>
 *
 * <h2>Prime meridian</h2>
 * <p>A prime meridian defines the origin from which longitude values
 * are specified. Most geodetic datums use Greenwich as their prime meridian. A prime
 * meridian description is not needed for any datum type other than geodetic, or if the
 * datum type is geodetic and the prime meridian is Greenwich. The prime meridian
 * description is mandatory if the datum type is geodetic and its prime meridian
 * is not Greenwich.</p>
 *
 * <h2>Ellipsoid</h2>
 * <p>An ellipsoid is defined that approximates the surface of the
 * geoid. Because of the area for which the approximation is valid - traditionally
 * regionally, but with the advent of satellite positioning often globally - the
 * ellipsoid is typically associated with Geographic and Projected CRSs. An ellipsoid
 * specification shall not be provided if the datum type not geodetic.</p>
 *
 * <p>One ellipsoid must be specified with every geodetic datum,
 * even if the ellipsoid is not used computationally. The latter may be the case
 * when a Geocentric CRS is used, e.g., in the calculation of satellite orbit and
 * ground positions from satellite observations. Although use of a Geocentric CRS
 * apparently obviates the need of an ellipsoid, the ellipsoid usually played a role
 * in the determination of the associated geodetic datum. Furthermore, one or more
 * Geographic CRSs may be based on the same geodetic datum, which requires the correct
 * ellipsoid the associated with any given geodetic datum.</p>
 *
 * <p>An ellipsoid is defined either by its semi-major axis and
 * inverse flattening, or by its semi-major axis and semi-minor axis. For some
 * applications, for example small-scale mapping in atlases, a spherical approximation
 * of the geoid's surface is used, requiring only the radius of the sphere to be
 * specified.</p>
 *
 * @author  Martin Desruisseaux (IRD)
 * @version 3.1
 * @since   1.0
 */
package org.opengis.referencing.datum;
