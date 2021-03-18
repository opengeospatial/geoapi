/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    http://www.geoapi.org
 *
 *    Copyright (C) 2005-2021 Open Geospatial Consortium, Inc.
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
package org.opengis.coverage.grid;

import java.util.List;
import org.opengis.geometry.DirectPosition;
import org.opengis.referencing.crs.CoordinateReferenceSystem;
import org.opengis.referencing.datum.Datum;
import org.opengis.annotation.UML;

import static org.opengis.annotation.Obligation.*;
import static org.opengis.annotation.Specification.*;


/**
 * Grid for which there is an affine transformation between the grid coordinates and the coordinates of
 * an external {@linkplain CoordinateReferenceSystem coordinate reference system}. A rectified grid is
 * defined by an origin in an external {@linkplain CoordinateReferenceSystem coordinate reference system},
 * and a set of offset vectors that specify the direction and distance between grid lines within
 * that external CRS.
 *
 * <div class="note"><b>Note:</b>
 * if the coordinate reference system is related to the earth by a
 * {@linkplain Datum datum}, the grid is a georectified grid.
 * </div>
 *
 * <p><b>Constraints:</b></p>
 * <ul>
 *   <li>The {@linkplain Grid#getDimension dimension of the grid} shall be less than or equal to the
 *       dimension of the {@linkplain DirectPosition#getCoordinateReferenceSystem coordinate
 *       reference system of the point} that is the {@linkplain #getOrigin origin}.</li>
 *   <li>The number of {@linkplain #getOffsetVectors offset vectors} shall equal the
 *       {@linkplain Grid#getDimension dimension of the grid}.</li>
 *   <li>The dimension of all offset vectors shall equal the dimension of the coordinate reference
 *       system, even if an offset vector is aligned with an axis of the external coordinate system.</li>
 * </ul>
 *
 * @departure constraint
 *   ISO 19123 defines <code>RectifiedGrid</code> as a direct sub-type of <code>Grid</code>.
 *   In GeoAPI, <code>RectifiedGrid</code> extends <code>Grid</code> indirectly, through
 *   <code>ReferenceableGrid</code>. In the GeoAPI hierarchy, <code>RectifiedGrid</code>
 *   is considered as a special case of <code>ReferenceableGrid</code> where the <cite>grid
 *   to CRS</cite> coordinate operation is affine. This hierarchy make easier to leverage
 *   the same code for both the affine and non-affine cases when the code does not require
 *   a strictly affine operation.
 *
 * @version ISO 19123:2004
 * @author  Wim Koolhoven
 * @author  Martin Schouwenburg
 * @author  Martin Desruisseaux (IRD)
 * @since   GeoAPI 2.1
 */
@UML(identifier="CV_RectifiedGrid", specification=ISO_19123)
public interface RectifiedGrid extends ReferenceableGrid {
    /**
     * Returns the origin of the rectified grid in an external coordinate reference system.
     *
     * @return the origin of the rectified grid.
     */
    @UML(identifier="origin", obligation=MANDATORY, specification=ISO_19123)
    DirectPosition getOrigin();

    /**
     * Returns the offset vectors that determine the grid spacing in each direction.
     * The vectors are defined in terms of the external coordinate reference system.
     *
     * @return the offset vectors that determine the grid spacing in each direction.
     */
    @UML(identifier="offsetVectors", obligation=MANDATORY, specification=ISO_19123)
    List<double[]> getOffsetVectors();

    /**
     * Converts through an affine transform grid coordinates to a direct position.
     *
     * @param  g The grid coordinates to convert.
     * @return the "real world" coordinates.
     *
     * @departure rename
     *   A <code>"convertCoordinates"</code> method name would match better the ISO identifier.
     *   However since <code>RectifiedGrid</code> extends <code>ReferenceableGrid</code> in GeoAPI,
     *   we have to use the same method names than the later. Here, <cite>transform</cite> is to be
     *   understood as a term encompassing both <cite>transformation</cite> and <cite>conversion</cite>.
     *   This is similar to the <code>MathTransform</code> name policy.
     */
    @UML(identifier="coordConv", obligation=MANDATORY, specification=ISO_19123)
    DirectPosition transformCoordinates(GridCoordinates g);

    /**
     * @deprecated Renamed as {@link #transformCoordinates}.
     *
     * @param  g The grid coordinates to convert.
     * @return the "real world" coordinates.
     */
    @Deprecated
    DirectPosition convertCoordinates(GridCoordinates g);

    /**
     * Converts through an affine transform a direct position to the grid coordinates of
     * the nearest grid point.
     *
     * @param p The "real world" coordinates to convert.
     * @return the grid coordinates.
     *
     * @departure rename
     *   A <code>"inverseConvertCoordinates"</code> method name would match better the ISO identifier.
     *   However since <code>RectifiedGrid</code> extends <code>ReferenceableGrid</code> in GeoAPI,
     *   we have to use the same method names than the later. Here, <cite>transform</cite> is to be
     *   understood as a term encompassing both <cite>transformation</cite> and <cite>conversion</cite>.
     *   This is similar to the <code>MathTransform</code> name policy.
     */
    @UML(identifier="invCoordConv", obligation=MANDATORY, specification=ISO_19123)
    GridCoordinates inverseTransformCoordinates(DirectPosition p);

    /**
     * @deprecated Renamed as {@link #inverseTransformCoordinates}.
     *
     * @param p The "real world" coordinates to convert.
     * @return the grid coordinates.
     */
    @Deprecated
    GridCoordinates inverseConvertCoordinates(DirectPosition p);
}
