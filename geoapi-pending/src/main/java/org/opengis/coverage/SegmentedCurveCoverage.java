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
package org.opengis.coverage;

import java.util.Collection;
import java.util.Set;
import org.opengis.util.Record;
import org.opengis.geometry.DirectPosition;
import org.opengis.geometry.primitive.Curve;
import org.opengis.annotation.UML;

import static org.opengis.annotation.Obligation.*;
import static org.opengis.annotation.Specification.*;


/**
 * Model phenomena that vary continuously or discontinuously along curves, which may be elements
 * of a network. The domain of a segmented curve coverage is described by a set of curves and
 * includes all the direct positions in all of the curves in the set.
 * <p>
 * Arc-length parameterization of a curve simplifies interpolation between direct positions on the
 * curve. Representation of phenomena that vary discontinuously along a curve requires segmentation
 * of the curve into regions of continuous variation. Such segmentation is also simplified by
 * arc-length parameterization. {@link Curve} supports arc-length parameterization. In particular,
 * the operation:
 * <p>
 * <blockquote>{@link Curve#getParamForPoint}</blockquote>
 * <p>
 * returns the arc-length distance from the start point of the curve to the input direct position.
 * <p>
 * A segmented curve coverage operates on a domain composed of {@linkplain Curve curves}. It is
 * composed of a set of {@link ValueCurve}s, each of which maps feature attribute values to
 * position on a {@linkplain Curve curve}.
 *
 * @version ISO 19123:2004
 * @author  Alessio Fabiani
 * @author  Martin Desruisseaux (IRD)
 * @since   GeoAPI 2.1
 */
@UML(identifier="CV_SegmentedCurveCoverage", specification=ISO_19123)
public interface SegmentedCurveCoverage extends ContinuousCoverage {
    /**
     * Returns the set of value objects used to evaluate the coverage. This
     * association is optional - an analytical coverage needs no value objects.
     */
    @UML(identifier="element", obligation=OPTIONAL, specification=ISO_19123)
    Set<ValueCurve> getElements();

    /**
     * Returns the interpolation method to be used in evaluating the coverage. The default value
     * shall be "{@linkplain InterpolationMethod#LINEAR linear}". An application schema may define
     * other interpolation methods.
     */
    @UML(identifier="interpolationType", obligation=OPTIONAL, specification=ISO_19123)
    InterpolationMethod getInterpolationMethod();

    /**
     * Return the value curve nearest to the specified direct position. The operation shall throw
     * an exception if the direct position is not close (i.e., within the distance specified by the
     * tolerance parameter) to one of the {@linkplain ValueCurve value curves} in this segmented
     * curve coverage. The default value for tolerance is zero.
     *
     * @param position The position where to search for a value curve.
     * @param tolerance The maximal distance between the curve and the specified position.
     * @return the curve nearest to the specified position.
     */
    @UML(identifier="curve", obligation=MANDATORY, specification=ISO_19123)
    ValueCurve curve(DirectPosition position, double tolerance);

    /**
     * Return the value curve nearest to the specified direct position. This method is equivalent
     * to <code>{@linkplain #curve(DirectPosition,double) curve}(position, 0)</code>.
     *
     * @param position The position where to search for a value curve.
     * @return the curve nearest to the specified position.
     */
    @UML(identifier="curve", obligation=MANDATORY, specification=ISO_19123)
    ValueCurve curve(DirectPosition position);

    /**
     * Returns a set of records of feature attribute values for the specified direct position.
     * Evaluation of a segmented curve coverage involves several steps:
     * <p>
     * <ul>
     *   <li>Invoke {@link #curve} in order to find the {@linkplain ValueCurve value curve}
     *       that contains the input direct position.</li>
     *
     *   <li>Invoke {@link ValueCurve#segment} in order to find the {@linkplain ValueSegment
     *       value segment} that contains the input direct position.</li>
     *
     *   <li>If {@code segment} returns a single value segment, use the specified interpolation
     *       type to compute feature attribute values from the associated control values.</li>
     *
     *   <li>If {@code segment} returns a pair of conterminous {@code ValueSegments}, compute the
     *       feature attribute values according to the rule specified by the {@linkplain
     *       #getCommonPointRule common point rule}.</li>
     * </ul>
     *
     * @throws PointOutsideCoverageException if the point is outside the coverage domain.
     * @throws CannotEvaluateException if the point can not be evaluated for some other reason.
     */
    @UML(identifier="evaluate", obligation=MANDATORY, specification=ISO_19123)
    Set<Record> evaluate(DirectPosition p, Collection<String> list) throws CannotEvaluateException;
}
