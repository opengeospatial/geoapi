/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    http://www.geoapi.org
 *
 *    Copyright (C) 2005-2019 Open Geospatial Consortium, Inc.
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

import java.util.Set;
import java.util.Collection;
import org.opengis.util.Record;
import org.opengis.coverage.ContinuousCoverage;
import org.opengis.coverage.InterpolationMethod;
import org.opengis.coverage.CannotEvaluateException;
import org.opengis.coverage.PointOutsideCoverageException;
import org.opengis.geometry.DirectPosition;
import org.opengis.annotation.UML;

import static org.opengis.annotation.Obligation.*;
import static org.opengis.annotation.Specification.*;


/**
 * A continuous coverage that operates on a {@linkplain GridValuesMatrix grid values matrix}.
 * The domain of a continuous quadrilateral grid coverage is the convex hull of the collection
 * of {@linkplain GridPoint grid points} defined by the grid values matrix. Evaluation of a
 * continuous quadrilateral grid coverage generates feature attribute values at direct positions
 * within the convex hull of the grid points provided by the grid values matrix. The general idea
 * is to extend the coverage to direct positions within the interior of each grid cell by
 * interpolation from the grid points at the corners of the cell.
 *
 * @version ISO 19123:2004
 * @author  Wim Koolhoven
 * @author  Martin Desruisseaux (IRD)
 * @since   GeoAPI 2.1
 */
@UML(identifier="CV_ContinousQuadrilateralGridCoverage", specification=ISO_19123)
public interface ContinuousQuadrilateralGridCoverage extends ContinuousCoverage {
    /**
     * Returns the set of {@linkplain GridValueCell grid value cells} that provide the structure
     * to support the {@linkplain #evaluate evaluate} operation.
     */
    @UML(identifier="element", obligation=MANDATORY, specification=ISO_19123)
    Set<GridValueCell> getElements();

    /**
     * Returns a code that identifies the interpolation method that shall be used to derive a
     * feature attribute value at any direct position within the {@linkplain GridValueCell grid
     * value cell}. This value is often {@linkplain InterpolationMethod#BILINEAR bilinear}.
     *
     * @return the interpolation method.
     */
    @UML(identifier="interpolationType", obligation=MANDATORY, specification=ISO_19123)
    InterpolationMethod getInterpolationMethod();

    /**
     * Returns the grid value cell that contains the specified direct position.
     * This method always returns a set of 1 member.
     */
    @UML(identifier="locate", obligation=MANDATORY, specification=ISO_19123)
    Set<GridValueCell> locate(DirectPosition p);

    /**
     * Returns a set of records of feature attribute values for the specified direct position.
     * Evaluation of a continuous quadrilateral grid coverage involves two steps. The first is
     * to use the information from the {@linkplain GridValuesMatrix values matrix} at {@linkplain
     * #getSource quadrilateral grid coverage source} to generate the
     * {@linkplain GridValueCell grid value cell} that contains the input {@linkplain DirectPosition
     * direct position}; the second is to interpolate the feature attribute values at the direct
     * position from the {@linkplain GridPointValuePair grid point value pairs} at the corners of
     * the {@linkplain GridValueCell grid value cell}. Some interpolation methods (e.g.
     * {@linkplain InterpolationMethod#BICUBIC bicubic interpolation}) may require the use of
     * {@linkplain GridPointValuePair grid point value pairs} outside of the {@linkplain GridValueCell
     * grid value cell} that contains the {@linkplain DirectPosition direct position}.
     *
     * <div class="note"><b>Note:</b>
     * {@linkplain InterpolationMethod#NEAREST_NEIGHBOUR Nearest neighbour interpolation} will return
     * for any direct position within a {@linkplain GridValueCell grid value cell} the record associated
     * with the {@linkplain GridPointValuePair grid point value pair} at the nearest corner of the
     * {@linkplain GridValueCell grid value cell}. In other words, a continuous grid coverage
     * that uses nearest neighbour interpolation acts as a discrete surface coverage.
     * </div>
     *
     * @throws PointOutsideCoverageException if the point is outside the coverage domain.
     * @throws CannotEvaluateException if the point can not be evaluated for some other reason.
     */
    @UML(identifier="evaluate", obligation=MANDATORY, specification=ISO_19123)
    Set<Record> evaluate(DirectPosition p, Collection<String> list)
            throws PointOutsideCoverageException, CannotEvaluateException;

    /**
     * Provides the data for the {@linkplain #evaluate evaluate} operation.
     *
     * @return the underlying data.
     */
    @UML(identifier="source", obligation=MANDATORY, specification=ISO_19123)
    GridValuesMatrix getSource();
}
