/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    Copyright © 2019-2023 Open Geospatial Consortium, Inc.
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
package org.opengis.filter.capability;

import java.util.Collection;
import org.opengis.util.ScopedName;
import org.opengis.annotation.UML;

import static org.opengis.annotation.Obligation.MANDATORY;
import static org.opengis.annotation.Specification.ISO_19143;


/**
 * Advertisement of additional operators added to the filter syntax.
 *
 * @author  Martin Desruisseaux (Geomatys)
 * @version 3.1
 * @since   3.1
 *
 * @todo Consider omitting this interface at least of GeoAPI 3.1.
 *       It is not clear what "additional operator" is and how it differs from a function.
 */
@UML(identifier="ExtendedCapabilities", specification=ISO_19143)
public interface ExtendedCapabilities {
    /**
     * Returns a list of additional operator names.
     *
     * @return additional operator names.
     */
    @UML(identifier="additionalOperator", obligation=MANDATORY, specification=ISO_19143)
    Collection<? extends ScopedName> getAdditionalOperator();
}
