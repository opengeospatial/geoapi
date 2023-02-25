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
package org.opengis.parameter;

import org.opengis.referencing.IdentifiedObject;
import org.opengis.annotation.UML;

import static org.opengis.annotation.Obligation.*;
import static org.opengis.annotation.Specification.*;


/**
 * Abstract definition of a parameter or group of parameters used by an operation method.
 *
 * @departure rename
 *   GeoAPI uses a name which contains the "<code>Descriptor</code>" word for consistency with other
 *   libraries in Java (e.g. <code>ParameterListDescriptor</code> in Java Advanced Imaging).
 *
 * @author  Martin Desruisseaux (IRD)
 * @author  Jody Garnett (Refractions Research)
 * @version 3.0
 * @since   2.0
 *
 * @see GeneralParameterValue
 *
 * @navassoc 1 - - GeneralParameterValue
 */
@UML(identifier="CC_GeneralOperationParameter", specification=ISO_19111)
public interface GeneralParameterDescriptor extends IdentifiedObject {
    /**
     * Creates a new instance of {@linkplain GeneralParameterValue parameter value or group}
     * initialized with the {@linkplain ParameterDescriptor#getDefaultValue default value(s)}.
     * The {@linkplain GeneralParameterValue#getDescriptor parameter value descriptor} for
     * the created parameter value(s) will be {@code this} object.
     *
     * @return A new parameter initialized to its default value.
     *
     * @departure extension
     *   This method is not part of the ISO specification. It is provided in GeoAPI as a kind of
     *   factory method.
     */
    GeneralParameterValue createValue();

    /**
     * The minimum number of times that values for this parameter group or
     * parameter are required. The default value is one. A value of 0 means
     * an optional parameter.
     *
     * @return The minimum occurrence.
     *
     * @see #getMaximumOccurs()
     */
    @UML(identifier="minimumOccurs", obligation=OPTIONAL, specification=ISO_19111)
    int getMinimumOccurs();

    /**
     * The maximum number of times that values for this parameter group or
     * parameter can be included. For a {@linkplain ParameterDescriptor single parameter},
     * the value is always 1. For a {@linkplain ParameterDescriptorGroup parameter group},
     * it may vary. The default value is one.
     *
     * @departure generalization
     *   Moved up (in the interface hierarchy) the <code>maximumOccurs</code> method from
     *   <code>ParameterDescriptorGroup</code> into this  super-interface, for parallelism
     *   with the <code>minimumOccurs</code> method.
     *
     * @return The maximum occurrence.
     *
     * @see #getMinimumOccurs()
     */
    @UML(identifier="CC_OperationParameterGroup.maximumOccurs", obligation=OPTIONAL, specification=ISO_19111)
    int getMaximumOccurs();
}
