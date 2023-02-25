/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    Copyright © 2005-2023 Open Geospatial Consortium, Inc.
 *    http://www.geoapi.org
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
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
     * @throws CannotEvaluateException if the point cannot be evaluated for some other reason.
     */
    @UML(identifier="evaluate", obligation=MANDATORY, specification=ISO_19123)
    Set<Record> evaluate(DirectPosition p, Collection<String> list) throws CannotEvaluateException;
}
