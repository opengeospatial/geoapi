/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    http://www.geoapi.org
 *
 *    Copyright (C) 2004-2021 Open Geospatial Consortium, Inc.
 *    All Rights Reserved. http://www.opengeospatial.org/ogc/legal
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

import java.util.List;
import org.opengis.metadata.Identifier;
import org.opengis.annotation.UML;

import static org.opengis.annotation.Obligation.*;
import static org.opengis.annotation.Specification.*;


/**
 * The definition of a group of related parameters used by an operation method.
 *
 * @departure rename
 *   GeoAPI uses a name which contains the "{@code Descriptor}" word for consistency with other
 *   libraries in Java (e.g. {@code ParameterListDescriptor} in Java Advanced Imaging).
 *
 * @author  Martin Desruisseaux (IRD)
 * @author  Jody Garnett (Refractions Research)
 * @version 3.1
 * @since   2.0
 *
 * @see ParameterValueGroup
 * @see ParameterDescriptor
 */
@UML(identifier="CC_OperationParameterGroup", specification=ISO_19111)
public interface ParameterDescriptorGroup extends GeneralParameterDescriptor {
    /**
     * Returns the parameters in this group.
     *
     * @return the parameter descriptors in this group.
     */
    @UML(identifier="parameter", obligation=MANDATORY, specification=ISO_19111)
    List<GeneralParameterDescriptor> descriptors();

    /**
     * Returns the parameter descriptor in this group for the specified
     * {@linkplain Identifier#getCode() identifier code}.
     *
     * @param  name  the case insensitive identifier code of the parameter to search for.
     * @return the parameter for the given identifier code.
     * @throws ParameterNotFoundException if there is no parameter for the given identifier code.
     *
     * @departure easeOfUse
     *   This method is not part of the ISO specification.
     *   It has been added in an attempt to make this interface easier to use.
     */
    GeneralParameterDescriptor descriptor(String name) throws ParameterNotFoundException;

    /**
     * Creates a new instance of {@linkplain ParameterValueGroup parameter value group}
     * initialized with the {@linkplain ParameterDescriptor#getDefaultValue() default values}.
     * While not a requirement, the {@linkplain ParameterValueGroup#getDescriptor() parameter
     * value descriptor} for the created group will typically be {@code this} descriptor instance.
     *
     * <p>The number of {@link ParameterValue} objects included must be between the
     * {@linkplain ParameterDescriptor#getMinimumOccurs() minimum} and
     * {@linkplain ParameterDescriptor#getMaximumOccurs() maximum occurrences} required.
     * For example:</p>
     *
     * <ul>
     *   <li>For {@link ParameterDescriptor} with multiplicity 1:* a {@code ParameterValue} will
     *       be included with the default value (even if this default value is null).</li>
     *   <li>For {@code ParameterDescriptor} with multiplicity 0:* no entry is required.
     *       {@code ParameterValue} entries may be created only as needed.</li>
     * </ul>
     *
     * @return a new parameter instance initialized to the default value.
     *
     * @departure extension
     *   This method is not part of the ISO specification. It is provided in GeoAPI as a kind of
     *   factory method.
     */
    @Override
    ParameterValueGroup createValue();
}
