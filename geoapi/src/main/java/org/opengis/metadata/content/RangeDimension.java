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
package org.opengis.metadata.content;

import org.opengis.util.InternationalString;
import org.opengis.util.MemberName;
import org.opengis.annotation.UML;

import static org.opengis.annotation.Obligation.*;
import static org.opengis.annotation.Specification.*;


/**
 * Information on the range of each dimension of a cell measurement value.
 *
 * @author  Martin Desruisseaux (IRD)
 * @author  Cory Horner (Refractions Research)
 * @version 3.0
 * @since   2.0
 *
 * @navassoc 1 - - MemberName
 */
@UML(identifier="MD_RangeDimension", specification=ISO_19115)
public interface RangeDimension {
    /**
     * Number that uniquely identifies instances of bands of wavelengths on which a sensor
     * operates.
     *
     * @return Identifier of bands on which a sensor operates, or {@code null}.
     */
    @UML(identifier="sequenceIdentifier", obligation=OPTIONAL, specification=ISO_19115)
    MemberName getSequenceIdentifier();

    /**
     * Description of the range of a cell measurement value.
     *
     * @return Description of the range of a cell measurement value, or {@code null}.
     */
    @UML(identifier="descriptor", obligation=OPTIONAL, specification=ISO_19115)
    InternationalString getDescriptor();
}
