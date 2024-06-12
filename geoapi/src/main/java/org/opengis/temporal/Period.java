/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    Copyright © 2006-2023 Open Geospatial Consortium, Inc.
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
package org.opengis.temporal;

import java.time.DateTimeException;
import java.time.temporal.TemporalAmount;
import org.opengis.annotation.UML;

import static org.opengis.annotation.Obligation.*;
import static org.opengis.annotation.Specification.*;


/**
 * A one-dimensional temporal primitive that represent extent in time.
 * A period is an open interval bounded by beginning and ending points.
 * Those two points <em>should</em> be of the same class. For example,
 * if the {@linkplain #getBeginning() beginning} is an instance of {@link java.time.LocalDate},
 * then the {@link #getEnding() ending} should also be an instance of {@code LocalDate}.
 * If the two points are {@linkplain org.opengis.geometry.DirectPosition direct positions},
 * then they <em>shall</em> be associated to the same {@linkplain org.opengis.referencing.crs.TemporalCRS}.
 *
 * @author  ISO 19108 (for abstract model and documentation)
 * @author  Stephane Fellah (Image Matters)
 * @author  Alexander Petkov
 * @author  Martin Desruisseaux (Geomatys)
 * @author  Remi Marechal (Geomatys).
 * @since   3.1
 * @version 3.1
 */
@UML(identifier="TM_Period", specification=ISO_19108)
public interface Period extends TemporalPrimitive {
    /**
     * Returns the beginning instant at which this period starts.
     * Its position on the timeline shall be before the period {@linkplain #getEnding() ending}.
     *
     * @return the beginning instant at which this period starts.
     */
    @UML(identifier="Beginning", obligation=MANDATORY, specification=ISO_19108)
    Instant getBeginning();

    /**
     * Returns the ending instant at which this period ends.
     * Its position on the timeline shall be after the period {@linkplain #getBeginning() beginning}.
     *
     * @return the ending instant at which this period ends.
     */
    @UML(identifier="Ending", obligation=MANDATORY, specification=ISO_19108)
    Instant getEnding();

    /**
     * Returns the duration of this period (optional operation).
     * This is the {@linkplain Instant#distance(TemporalPrimitive) distance}
     * between the period beginning and ending positions.
     * The default implementation is as below:
     *
     * {@snippet lang="java" :
     * return getBeginning().distance(getEnding());
     * }
     *
     * <h4>Exceptions</h4>
     * This method shall throw an {@link IndeterminatePositionException} if the temporal
     * position of a bound is {@linkplain #getIndeterminatePosition() indeterminate}.
     * A {@link DateTimeException} may also be thrown if the coordinates are
     * {@link org.opengis.referencing.cs.CoordinateDataType#INTEGER ordinal values}.
     *
     * @return duration of this period.
     * @throws UnsupportedOperationException if this operation is not supported.
     * @throws IndeterminatePositionException if the temporal position of a bound is indeterminate.
     * @throws DateTimeException if the duration cannot be computed between the temporal objects used as bounds.
     * @throws ArithmeticException if the duration exceeds the capacity of the implementation.
     *
     * @see Instant#distance(TemporalPrimitive)
     * @see java.time.Duration#between(Temporal, Temporal)
     */
    @UML(identifier="TM_Separation.length", obligation=OPTIONAL, specification=ISO_19108)
    default TemporalAmount length() {
        return getBeginning().distance(getEnding());
    }
}
