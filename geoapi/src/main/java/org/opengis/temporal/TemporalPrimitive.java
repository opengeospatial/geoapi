/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    Copyright © 2005-2024 Open Geospatial Consortium, Inc.
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
import org.opengis.filter.TemporalOperatorName;
import org.opengis.annotation.UML;

import static org.opengis.annotation.Obligation.OPTIONAL;
import static org.opengis.annotation.Specification.ISO_19108;


/**
 * A non-decomposed element of temporal object or topology of time.
 * The two primitives in the temporal dimension are the instant and the period.
 *
 * @author  ISO 19108 (for abstract model and documentation)
 * @author  Stephane Fellah (Image Matters)
 * @author  Alexander Petkov
 * @author  Martin Desruisseaux (Geomatys)
 * @author  Remi Marechal (Geomatys).
 * @version 3.1
 * @since   2.3
 */
@UML(identifier="TM_Primitive", specification=ISO_19108)
public interface TemporalPrimitive {
    /**
     * Determines the position of this primitive relative to another temporal primitive.
     * The relative position is identified by an operator which evaluates to {@code true}
     * when the two operands are {@code this} and {@code other}.
     *
     * <h4>Valid return values</h4>
     * <p>If the two primitives are {@link Instant} instances,
     * then this method can return one of the following values:
     * {@link TemporalOperatorName#BEFORE before},
     * {@link TemporalOperatorName#EQUALS equals} or
     * {@link TemporalOperatorName#AFTER  after}.</p>
     *
     * <p>If the first primitive is a {@link Period} and the second primitive is an {@link Instant},
     * then this method can return one of the following values:
     * {@link TemporalOperatorName#BEFORE   before},
     * {@link TemporalOperatorName#ENDED_BY ended by},
     * {@link TemporalOperatorName#CONTAINS contains},
     * {@link TemporalOperatorName#BEGUN_BY begun by} or
     * {@link TemporalOperatorName#AFTER    after}.</p>
     *
     * <p>If the first primitive is an {@link Instant} and the second primitive is a {@link Period},
     * then this method can return one of the following values:
     * {@link TemporalOperatorName#BEFORE before},
     * {@link TemporalOperatorName#BEGINS begins},
     * {@link TemporalOperatorName#DURING during},
     * {@link TemporalOperatorName#ENDS   ends} or
     * {@link TemporalOperatorName#AFTER  after}.</p>
     *
     * <p>If the two primitives are {@link Period} instances, then this method can return
     * any of the 13 values except {@link TemporalOperatorName#ANY_INTERACTS any interacts}.</p>
     *
     * <h4>Exceptions</h4>
     * This method shall throw a {@link DateTimeException} if any input value is indeterminate.
     * {@code DateTimeException} can also be thrown if the temporal objects used by the primitives do not
     * support {@linkplain java.time.temporal.TemporalField temporal fields} that this method can compare.
     *
     * @departure harmonization
     *   ISO 19108 defines a {@code TM_RelativePosition} code list which is identical to the
     *   ISO 19143 {@code TemporalOperatorName} code list, except that the latter has one more
     *   value for "any interacts". GeoAPI uses the latter for avoiding duplication.
     *
     * @param  other the other primitive for which to determine the relative position.
     * @return a temporal operator which is true when evaluated between this primitive and the other primitive.
     * @throws UnsupportedOperationException if this operation is not supported.
     * @throws DateTimeException if the temporal objects cannot be compared.
     *
     * @since 3.1
     */
    @UML(identifier="TM_Order.relativePosition", obligation=OPTIONAL, specification=ISO_19108)
    default TemporalOperatorName findRelativePosition(TemporalPrimitive other) {
        throw new UnsupportedOperationException();
    }
}
