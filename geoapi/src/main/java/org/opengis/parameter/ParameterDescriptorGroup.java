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

import java.util.List;
import org.opengis.metadata.Identifier;
import org.opengis.annotation.UML;

import static org.opengis.annotation.Obligation.*;
import static org.opengis.annotation.Specification.*;


/**
 * The definition of a group of related parameters used by an operation method.
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
 * @see ParameterValueGroup
 * @see ParameterDescriptor
 *
 * @navassoc - - - GeneralParameterDescriptor
 */
@UML(identifier="CC_OperationParameterGroup", specification=ISO_19111)
public interface ParameterDescriptorGroup extends GeneralParameterDescriptor {
    /**
     * Creates a new instance of {@linkplain ParameterValueGroup parameter value group}
     * initialized with the {@linkplain ParameterDescriptor#getDefaultValue default values}.
     * The {@linkplain ParameterValueGroup#getDescriptor parameter value descriptor}
     * for the created group will be {@code this} object.
     *
     * The number of {@link ParameterValue} objects included must be between the
     * {@linkplain ParameterDescriptor#getMinimumOccurs minimum} and
     * {@linkplain ParameterDescriptor#getMaximumOccurs maximum occurences} required.
     * For example:
     * <ul>
     * <li>For {@link ParameterDescriptor} with cardinality 1:* a {@link ParameterValue} will
     *     be included with the {@linkplain ParameterDescriptor#getDefaultValue default value}
     *     (even if this default value is null).</li>
     * <li>For {@link ParameterDescriptor} with cardinality 0:* no entry is required.
     *     {@link ParameterValue} entries may be created only as needed.</li>
     * </ul>
     *
     * @return A new parameter instance initialized to the default value.
     *
     * @departure extension
     *   This method is not part of the ISO specification. It is provided in GeoAPI as a kind of
     *   factory method.
     */
    ParameterValueGroup createValue();

    /**
     * Returns the parameters in this group.
     *
     * @return The descriptor of this group.
     */
    @UML(identifier="parameter", obligation=MANDATORY, specification=ISO_19111)
    List<GeneralParameterDescriptor> descriptors();

    /**
     * Returns the parameter descriptor in this group for the specified
     * {@linkplain Identifier#getCode identifier code}.
     *
     * @param  name The case insensitive {@linkplain Identifier#getCode identifier code} of the
     *              parameter to search for.
     * @return The parameter for the given identifier code.
     * @throws ParameterNotFoundException if there is no parameter for the given identifier code.
     *
     * @departure easeOfUse
     *   This method is not part of the ISO specification. It has been added in an attempt to make
     *   this interface easier to use.
     */
    GeneralParameterDescriptor descriptor(String name) throws ParameterNotFoundException;
}
