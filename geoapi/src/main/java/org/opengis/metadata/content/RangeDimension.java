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
package org.opengis.metadata.content;

import java.util.Collection;
import java.util.Collections;
import org.opengis.util.InternationalString;
import org.opengis.util.MemberName;
import org.opengis.metadata.Identifier;
import org.opengis.annotation.UML;

import static org.opengis.annotation.Obligation.*;
import static org.opengis.annotation.Specification.*;


/**
 * Information on the range of each dimension of a cell measurement value.
 *
 * @author  Martin Desruisseaux (IRD)
 * @author  Cory Horner (Refractions Research)
 * @author  Rémi Maréchal (Geomatys)
 * @version 3.1
 * @since   2.0
 *
 * @see AttributeGroup#getAttributes()
 */
@UML(identifier="MD_RangeDimension", specification=ISO_19115)
public interface RangeDimension {
    /**
     * Unique name or number that identifies attributes included in the coverage.
     *
     * @return unique name or number, or {@code null} if none.
     */
    @UML(identifier="sequenceIdentifier", obligation=OPTIONAL, specification=ISO_19115)
    default MemberName getSequenceIdentifier() {
        return null;
    }

    /**
     * Description of the attribute.
     *
     * @return description of the attribute, or {@code null} if none.
     *
     * @since 3.1
     */
    @UML(identifier="description", obligation=OPTIONAL, specification=ISO_19115)
    default InternationalString getDescription() {
        return null;
    }

    /**
     * Description of the range of a cell measurement value.
     *
     * @return description of the range of a cell measurement value, or {@code null} if none.
     *
     * @deprecated As of ISO 19115:2014, renamed {@link #getDescription()}.
     */
    @Deprecated
    @UML(identifier="descriptor", obligation=OPTIONAL, specification=ISO_19115, version=2003)
    default InternationalString getDescriptor() {
        return getDescription();
    }

    /**
     * Identifiers for each attribute included in the resource. These identifiers
     * can be used to provide names for the attribute from a standard set of names.
     *
     * @return identifiers for each attribute included in the resource.
     *
     * @since 3.1
     */
    @UML(identifier="name", obligation=OPTIONAL, specification=ISO_19115)
    default Collection<? extends Identifier> getNames() {
        return Collections.emptyList();
    }
}
