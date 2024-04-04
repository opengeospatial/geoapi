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

import org.opengis.parameter.ParameterValueGroup;
import org.opengis.annotation.UML;
import org.opengis.annotation.Classifier;
import org.opengis.annotation.Stereotype;

import static org.opengis.annotation.Obligation.*;
import static org.opengis.annotation.Specification.*;


/**
 * A parameterized mathematical operation on coordinates that changes coordinates to another <abbr>CRS</abbr>.
 * This coordinate operation thus uses an operation method, usually with associated parameter values.
 * This is a single (not {@linkplain ConcatenatedOperation concatenated}) coordinate operation.
 *
 * @author  OGC Topic 2 (for abstract model and documentation)
 * @author  Martin Desruisseaux (Geomatys)
 * @version 3.1
 * @since   1.0
 */
@Classifier(Stereotype.ABSTRACT)
@UML(identifier="SingleOperation", specification=ISO_19111)
public interface SingleOperation extends CoordinateOperation {
    /**
     * Returns the algorithm or procedure used by this single operation.
     *
     * @return algorithm or procedure used by this single operation.
     */
    @UML(identifier="method", obligation=MANDATORY, specification=ISO_19111)
    OperationMethod getMethod();

    /**
     * Returns the parameter values used by this single operation.
     *
     * @return the parameter values used by this single operation.
     *
     * @departure easeOfUse
     *   The sequence if {@code GeneralParameterValue} is replaced by a {@code ParameterValueGroup}
     *   because it provides method for fetching parameters by their names.
     */
    @UML(identifier="parameterValue", obligation=MANDATORY, specification=ISO_19111)
    ParameterValueGroup getParameterValues();
}
