/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    Copyright © 2004-2011 Open Geospatial Consortium, Inc.
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

import org.opengis.parameter.ParameterValueGroup;
import org.opengis.annotation.UML;

import static org.opengis.annotation.Obligation.*;
import static org.opengis.annotation.Specification.*;


/**
 * A parameterized mathematical operation on coordinates that transforms or converts
 * coordinates to another coordinate reference system. This coordinate operation thus
 * uses an operation method, usually with associated parameter values. This is a
 * single (not {@linkplain ConcatenatedOperation concatenated}) coordinate operation.
 *
 * @author  Martin Desruisseaux (IRD)
 * @version 3.0
 * @since   1.0
 *
 * @navassoc 1 - - OperationMethod
 * @navassoc 1 - - ParameterValueGroup
 */
@UML(identifier="CC_SingleOperation", specification=ISO_19111)
public interface SingleOperation extends CoordinateOperation {
    /**
     * Returns the operation method.
     *
     * @return The operation method.
     *
     * @since 2.3
     */
    @UML(identifier="method", obligation=MANDATORY, specification=ISO_19111)
    OperationMethod getMethod();

    /**
     * Returns the parameter values.
     *
     * @return The parameter values.
     *
     * @since 2.3
     */
    @UML(identifier="parameterValue", obligation=MANDATORY, specification=ISO_19111)
    ParameterValueGroup getParameterValues();
}
