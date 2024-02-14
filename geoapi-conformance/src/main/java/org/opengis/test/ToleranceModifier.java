/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    Copyright © 2011-2023 Open Geospatial Consortium, Inc.
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
package org.opengis.test;

import org.opengis.geometry.DirectPosition;
import org.opengis.referencing.crs.GeographicCRS;
import org.opengis.referencing.crs.ProjectedCRS;


/**
 * Modifies the tolerance threshold before to compare a calculated value against its expected value.
 * When a {@link TestCase} is run, GeoAPI invoke the {@link #adjust(double[], DirectPosition, CalculationType)
 * adjust(…)} method. The first given argument will be the default tolerance thresholds computed by the
 * {@link TestCase} being run. Implementation can modify those tolerances in an arbitrary number of dimensions.
 * Different implementations are available for different cases. For example:
 *
 * <ul>
 *   <li>Allowing a greater tolerance threshold along the vertical axis compared to the
 *       horizontal axis.</li>
 *   <li>In a geographic CRS, ignoring offsets of 360° in longitude.</li>
 *   <li>In a geographic CRS, ignoring the longitude value if the latitude is at a pole.</li>
 * </ul>
 *
 * @author  Martin Desruisseaux (Geomatys)
 * @version 3.1
 * @since   3.1
 */
public interface ToleranceModifier {
    /**
     * Converts λ and φ tolerance values from metres to degrees before comparing
     * geographic coordinates. The tolerance for the longitude (λ) and latitude (φ)
     * coordinate values are converted from metres to degrees using the standard length of one
     * nautical mile ({@value org.opengis.test.ToleranceModifiers#NAUTICAL_MILE} metres per
     * minute of angle). Next, the λ tolerance is adjusted according the distance of
     * the φ coordinate value to the pole. In the extreme case where the coordinate to compare
     * is located at a pole, then the tolerance is 360° in longitude values.
     *
     * <p>This modifier assumes that geographic coordinates are expressed in decimal degrees in
     * (<var>longitude</var>, <var>latitude</var>) order, as documented in the {@linkplain
     * org.opengis.referencing.operation.MathTransformFactory#createParameterizedTransform
     * parameterized transform contructor}.</p>
     *
     * @see ToleranceModifiers#geographic(int, int)
     */
    ToleranceModifier GEOGRAPHIC = new ToleranceModifiers.Geographic(0, 1);

    /**
     * Converts φ and λ tolerance values from metres to degrees before comparing
     * geographic coordinates. This modifier is identical to the {@link #GEOGRAPHIC} tolerance
     * modifier, except that φ and λ axes are interchanged. This is the most common
     * modifier used when testing {@link GeographicCRS} instances created from the
     * <a href="http://www.epsg.org">EPSG</a> database.
     *
     * @see ToleranceModifiers#geographic(int, int)
     */
    ToleranceModifier GEOGRAPHIC_φλ = new ToleranceModifiers.Geographic(1, 0);

    /**
     * Converts λ and φ tolerance values from metres to degrees before comparing
     * the result of an <i>reverse projection</i>. For <i>forward projections</i>
     * and all other calculations, the tolerance values are left unchanged.
     *
     * <p>The modifier performs the work documented in {@link #GEOGRAPHIC} if and only if the
     * {@link CalculationType} is {@link CalculationType#INVERSE_TRANSFORM INVERSE_TRANSFORM}.
     * For all other cases, the modifier does nothing.</p>
     *
     * @see ToleranceModifiers#projection(int, int)
     */
    ToleranceModifier PROJECTION = new ToleranceModifiers.Projection(0, 1);

    /**
     * Converts φ and λ tolerance values from metres to degrees before comparing
     * the result of an <i>reverse projection</i>. This modifier is identical to the
     * {@link #PROJECTION} tolerance modifier, except that φ and λ axes are
     * interchanged. This is the most common modifier used when testing {@link ProjectedCRS}
     * instances created from the <a href="http://www.epsg.org">EPSG</a> database.
     *
     * @see ToleranceModifiers#projection(int, int)
     */
    ToleranceModifier PROJECTION_FROM_φλ = new ToleranceModifiers.Projection(1, 0);

    /**
     * Makes the tolerance values relative to the coordinate values being compared. For each
     * dimension, this modifier multiplies the tolerance threshold by the coordinate value and
     * ensure that the result is not lower than the original threshold (in order to allow
     * comparisons of values close to zero):
     *
     * <blockquote><var>tolerance</var>[i] = max(<var>tolerance</var>[i],
     * <var>tolerance</var>[i] × <var>coordinate</var>[i])</blockquote>
     */
    ToleranceModifier RELATIVE = new ToleranceModifiers.Relative();

    /**
     * Adjusts the tolerance threshold for comparing the given coordinate along each dimensions.
     *
     * @param  tolerance   the default tolerance threshold determined by the {@link TestCase} being run.
     *                     This array can be modified in-place.
     * @param  coordinate  the coordinate being compared, in the <em>target</em> CRS.
     * @param  mode        indicates if the coordinate being compared is the result of a direct
     *                     or inverse transform, or if strict equality is requested.
     */
    void adjust(double[] tolerance, DirectPosition coordinate, CalculationType mode);
}
