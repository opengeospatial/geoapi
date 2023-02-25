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

import java.util.Set;
import java.util.Collection;                    // For javadoc
import org.opengis.geometry.DirectPosition;
import org.opengis.util.Record;
import org.opengis.annotation.UML;

import static org.opengis.annotation.Obligation.*;
import static org.opengis.annotation.Specification.*;


/**
 * Basis for interpolating feature attribute values within a {@linkplain ContinuousCoverage
 * continuous coverage}. {@code ValueObject}s may be generated in the execution of an
 * {@link Coverage#evaluate(DirectPosition,Collection) evaluate} operation, and need not
 * be persistent.
 *
 * @version ISO 19123:2004
 * @author  Martin Desruisseaux (IRD)
 * @since   GeoAPI 2.1
 */
@UML(identifier="CV_ValueObject", specification=ISO_19123)
public interface ValueObject {
    /**
     * Returns the set of <var>geometry</var>-<var>value</var> pairs that provide the basis for
     * constructing this {@code ValueObject} and for evaluating the {@linkplain ContinuousCoverage
     * continuous coverage} at {@linkplain DirectPosition direct positions} within this value object.
     *
     * @return the control values.
     */
    @UML(identifier="controlValue", obligation=MANDATORY, specification=ISO_19123)
    Set<? extends GeometryValuePair> getControlValues();

    /**
     * The domain object constructed from the {@linkplain GeometryValuePair#getGeometry
     * domain objects} of the <var>geometry</var>-<var>value</var> pairs that are linked
     * to this value object by the {@linkplain #getControlValues control values}.
     *
     * @return the domain.
     */
    @UML(identifier="geometry", obligation=MANDATORY, specification=ISO_19123)
    DomainObject<?> getGeometry();

    /**
     * Holds the values of the parameters required to execute the interpolate operation, as
     * specified by the {@linkplain ContinuousCoverage#getInterpolationParameterTypes
     * interpolation parameter types} attribute of the continuous coverage.
     *
     * @return the interpolation parameters.
     *
     * @todo Consider leveraging the parameter package.
     */
    @UML(identifier="interpolationParameters", obligation=OPTIONAL, specification=ISO_19123)
    Record getInterpolationParameters();

    /**
     * Returns the record of feature attribute values computed for the specified direct position.
     *
     * @param p The position where to compute values.
     * @return the feature attribute values.
     */
    @UML(identifier="interpolate", obligation=MANDATORY, specification=ISO_19123)
    Record interpolate(DirectPosition p);
}
