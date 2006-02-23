/*$************************************************************************************************
 **
 ** $Id$
 **
 ** $Source$
 **
 ** Copyright (C) 2003 Open GIS Consortium, Inc. All Rights Reserved. http://www.opengis.org/Legal/
 **
 *************************************************************************************************/
package org.opengis.referencing.datum;

// J2SE direct dependencies
import java.util.Date;
import java.util.Locale;

// OpenGIS direct dependencies
import org.opengis.referencing.Info;
import org.opengis.metadata.extent.Extent;


/**
 * Specifies the relationship of a coordinate system to the earth, thus creating a {@linkplain
 * org.opengis.referencing.crs.CoordinateReferenceSystem coordinate reference system}. A datum uses a
 * parameter or set of parameters that determine the location of the origin of the coordinate
 * reference system. Each datum subtype can be associated with only specific types of
 * {@linkplain org.opengis.referencing.cs.CoordinateSystem coordinate systems}.
 *
 * @UML abstract CD_Datum
 * @author ISO 19111
 * @author <A HREF="http://www.opengis.org">OpenGIS&reg; consortium</A>
 * @version <A HREF="http://www.opengis.org/docs/03-073r1.zip">Abstract specification 2.0</A>
 *
 * @see org.opengis.referencing.cs.CoordinateSystem
 * @see org.opengis.referencing.crs.CoordinateReferenceSystem
 */
public interface Datum extends Info {
    /**
     * Description, possibly including coordinates, of the point or points used to anchor the datum
     * to the Earth. Also known as the "origin", especially for Engineering and Image Datums.
     *
     * <ul>
     *   <li>For a geodetic datum, this point is also known as the fundamental point, which is
     *       traditionally the point where the relationship between geoid and ellipsoid is defined.
     *       In some cases, the "fundamental point" may consist of a number of points. In those
     *       cases, the parameters defining the geoid/ellipsoid relationship have then been averaged
     *       for these points, and the averages adopted as the datum definition.</li>
     *
     *   <li>For an engineering datum, the anchor point may be a physical point, or it may be a
     *       point with defined coordinates in another CRS.</li>
     *
     *   <li>For an image datum, the anchor point is usually either the centre of the image or the
     *       corner of the image.</li>
     *
     *   <li>For a temporal datum, this attribute is not defined. Instead of the anchor point,
     *       a temporal datum carries a separate time origin of type {@link Date}.</li>
     * </ul>
     *
     * @param  locale The desired locale for the datum anchor point to be returned,
     *         or <code>null</code> for anchor point in some default locale (may or
     *         may not be the {@linkplain Locale#getDefault() system default}).
     * @return The datum anchor point in the given locale, or <code>null</code> if none. If no
     *         anchor point is available in the given locale, then some default locale is used.
     * @UML optional anchorPoint
     */
    String getAnchorPoint(Locale locale);

    /**
     * The time after which this datum definition is valid. This time may be precise (e.g. 1997
     * for IRTF97) or merely a year (e.g. 1983 for NAD83). In the latter case, the epoch usually
     * refers to the year in which a major recalculation of the geodetic control network, underlying
     * the datum, was executed or initiated. An old datum can remain valid after a new datum is
     * defined. Alternatively, a datum may be superseded by a later datum, in which case the
     * realization epoch for the new datum defines the upper limit for the validity of the
     * superseded datum.
     *
     * @return The datum realization epoch, or <code>null</code> if not available.
     * @UML optional realizationEpoch
     */
    Date getRealizationEpoch();

    /**
     * Area or region in which this datum object is valid.
     *
     * @return The datum valid area, or <code>null</code> if not available.
     * @UML optional validArea
     */
    Extent getValidArea();

    /**
     * Description of domain of usage, or limitations of usage, for which this
     * datum object is valid.
     *
     * @param  locale The desired locale for the datum scope to be returned, or <code>null</code>
     *         for scope in some default locale (may or may not be the
     *         {@linkplain Locale#getDefault() system default}).
     * @return The datum scope in the given locale, or <code>null</code> if none. If no
     *         scope is available in the given locale, then some default locale is used.
     * @UML optional scope
     */
    String getScope(Locale locale);
}
