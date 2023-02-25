/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    Copyright © 2014-2023 Open Geospatial Consortium, Inc.
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
import org.opengis.annotation.UML;

import static org.opengis.annotation.Obligation.*;
import static org.opengis.annotation.Specification.ISO_19115;


/**
 * Information about content type for groups of attributes for a specific {@link RangeDimension}.
 *
 * @author  Rémi Maréchal (Geomatys)
 * @version 3.1
 * @since   3.1
 *
 * @see CoverageDescription#getAttributeGroups()
 */
@UML(identifier="MD_AttributeGroup", specification=ISO_19115)
public interface AttributeGroup {
    /**
     * Types of information represented by the value(s).
     *
     * @return types of information represented by the value(s).
     */
    @UML(identifier="contentType", obligation=MANDATORY, specification=ISO_19115)
    Collection<CoverageContentType> getContentTypes();

    /**
     * Information on an attribute of the resource.
     * Returns an empty collection if none.
     *
     * @return information on an attribute of the resource.
     */
    @UML(identifier="attribute", obligation=OPTIONAL, specification=ISO_19115)
    default Collection<? extends RangeDimension> getAttributes() {
        return Collections.emptyList();
    }
}
