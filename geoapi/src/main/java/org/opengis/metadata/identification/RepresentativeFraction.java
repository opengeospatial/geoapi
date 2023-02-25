/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    Copyright © 2007-2011 Open Geospatial Consortium, Inc.
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
package org.opengis.metadata.identification;

import org.opengis.annotation.UML;

import static org.opengis.annotation.Obligation.MANDATORY;
import static org.opengis.annotation.Specification.ISO_19115;


/**
 * A scale defined as the inverse of a denominator. This is derived from ISO 19103 {@code Scale}
 * where {@linkplain #getDenominator denominator} = 1 / <var>scale</var>.
 * <p>
 * Implementations are encouraged to extend {@link Number} in a manner equivalent to:
 *
 * <blockquote><pre>
 *  class MyRepresentedFraction extends Number implements RepresentedFraction {
 *      ...
 *      public double doubleValue() {
 *          return 1.0 / (double) denominator;
 *      }
 *      public float floatValue() {
 *          return 1.0f / (float) denominator;
 *      }
 *      public long longValue() {
 *          return 1 / denominator; // Result is zero except for denominator=[0,1].
 *      }
 *      ...
 *  }
 * </pre></blockquote>
 *
 * @author  Ely Conn (Leica Geosystems Geospatial Imaging, LLC)
 * @version 3.0
 * @since   2.1
 */
@UML(identifier="MD_RepresentativeFraction", specification=ISO_19115)
public interface RepresentativeFraction {
    /**
     * Returns the scale value in a form usable for computation.
     *
     * @return <code>1.0 / (double) {@linkplain #getDenominator()}</code>
     *
     * @since 2.2
     */
    double doubleValue();

    /**
     * The number below the line in a vulgar fraction.
     *
     * @return The denominator.
     */
    @UML(identifier = "denominator", obligation = MANDATORY, specification = ISO_19115)
    long getDenominator();

    /**
     * Compares this representative fraction with the specified object for equality.
     * {@code RepresentativeFraction} is a data object - {@code equals} is defined
     * according to {@link #getDenominator()};
     * <p>
     * Implementations should match the following:
     *
     * <blockquote><pre>
     * public boolean equals(Object object) {
     *     if (object instanceof RepresentativeFraction) {
     *         final RepresentativeFraction that = (RepresentativeFraction) object;
     *         return getDenominator() == that.getDenominator();
     *     }
     *     return false;
     * }
     * </pre></blockquote>
     *
     * @param other The object to compare with.
     * @return {@code true} if {@code other} is a {@code RepresentedFraction} with the same
     *         {@linkplain #getDenominator denominator} value.
     */
    @Override
    boolean equals(Object other);

    /**
     * Returns a hash value for this representative fraction.
     * {@code RepresentativeFraction} is a data object - {@code hashcode} is defined
     * according to {@link #getDenominator()}.
     * <p>
     * Implementations should match the following:
     *
     * <blockquote><pre>
     * public int hashCode() {
     *     return (int) getDenominator();
     * }
     * </pre></blockquote>
     *
     * @return A hash code value for this representative fraction.
     */
    @Override
    int hashCode();
}
