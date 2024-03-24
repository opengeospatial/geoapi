/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    Copyright © 2003-2023 Open Geospatial Consortium, Inc.
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
package org.opengis.referencing.operation;

import java.util.Map;
import org.opengis.parameter.ParameterValueGroup;
import org.opengis.referencing.crs.CoordinateReferenceSystem;
import org.opengis.annotation.UML;

import static org.opengis.annotation.Obligation.*;
import static org.opengis.annotation.Specification.*;


/**
 * Operation in which the parameter values are defined rather than empirically derived.
 * Coordinate conversions are coordinate operations that make use of exact,
 * defined (rather than measured or computed), and therefore error-free parameter values.
 * Corresponding pairs of coordinate tuples in each of the two coordinate reference systems
 * connected through a coordinate conversion have a fixed arithmetic relationship.
 * Additionally one of the two tuples cannot exist without specification of the coordinate conversion
 * and the source coordinate reference system.
 * Coordinate conversions are therefore intimately related to the concept of
 * {@linkplain org.opengis.referencing.crs.DerivedCRS Derived <abbr>CRS</abbr>}.
 *
 * <h2>Examples</h2>
 * The best-known examples of coordinate conversions are map projections.
 * A map projection is a conversion from an ellipsoidal coordinate system
 * to a Cartesian coordinate system in <abbr>CRS</abbr>s associated to the same datum.
 * Another example is the change of units such as from radians to degrees or feet to meters.
 *
 * @author  OGC Topic 2 (for abstract model and documentation)
 * @author  Martin Desruisseaux (IRD, Geomatys)
 * @version 3.1
 * @since   1.0
 *
 * @see Transformation
 * @see CoordinateOperationFactory#createDefiningConversion(Map, OperationMethod, ParameterValueGroup)
 */
@UML(identifier="Conversion", specification=ISO_19111)
public interface Conversion extends SingleOperation {
    /**
     * Returns the <abbr>CRS</abbr> from which coordinates are changed.
     * If this conversion is part of a derived <abbr>CRS</abbr>, then the source <abbr>CRS</abbr>
     * (if not null) shall be the same as the {@link org.opengis.referencing.crs.DerivedCRS#getBaseCRS()} value.
     *
     * @return the <abbr>CRS</abbr> from which coordinates are changed, or {@code null} if not available.
     */
    @Override
    @UML(identifier="sourceCRS", obligation=OPTIONAL, specification=ISO_19111)
    CoordinateReferenceSystem getSourceCRS();

    /**
     * Returns the <abbr>CRS</abbr> to which coordinates are changed.
     * If this conversion is part of a derived <abbr>CRS</abbr>, then the target <abbr>CRS</abbr>
     * (if not null) shall be the same as the {@link org.opengis.referencing.crs.DerivedCRS} instance.
     *
     * @return the <abbr>CRS</abbr> to which coordinates are changed, or {@code null} if not available.
     */
    @Override
    @UML(identifier="targetCRS", obligation=OPTIONAL, specification=ISO_19111)
    CoordinateReferenceSystem getTargetCRS();

    /**
     * This attribute is declared in {@code CoordinateOperation} but is not used in a conversion.
     *
     * @return usually {@code null}.
     */
    @Override
    @UML(identifier="operationVersion", obligation=FORBIDDEN, specification=ISO_19111)
    default String getOperationVersion() {
        return null;
    }
}
