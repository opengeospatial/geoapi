/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    Copyright © 2004-2023 Open Geospatial Consortium, Inc.
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
package org.opengis.parameter;

import org.opengis.annotation.UML;
import org.opengis.annotation.Classifier;
import org.opengis.annotation.Stereotype;

import static org.opengis.annotation.Obligation.*;
import static org.opengis.annotation.Specification.*;


/**
 * Abstract parameter value or group of parameter values.
 *
 * @author  Martin Desruisseaux (IRD)
 * @author  Jody Garnett (Refractions Research)
 * @version 3.1
 * @since   1.0
 *
 * @see GeneralParameterDescriptor
 */
@Classifier(Stereotype.ABSTRACT)
@UML(identifier="CC_GeneralParameterValue", specification=ISO_19111, version=2007)
public interface GeneralParameterValue {
    /**
     * Returns the abstract definition of this parameter or group of parameters.
     *
     * @return the abstract definition of this parameter or group of parameters.
     */
    @UML(identifier="parameter", obligation=MANDATORY, specification=ISO_19111)
    GeneralParameterDescriptor getDescriptor();

    /**
     * Returns a copy of this parameter value or group.
     *
     * @return a copy of this parameter value or group.
     */
    GeneralParameterValue clone();
}
