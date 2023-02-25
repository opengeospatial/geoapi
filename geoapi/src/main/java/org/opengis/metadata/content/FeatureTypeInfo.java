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

import org.opengis.annotation.UML;
import org.opengis.util.GenericName;

import static org.opengis.annotation.Obligation.*;
import static org.opengis.annotation.Specification.ISO_19115;


/**
 * Information about the occurring feature type.
 *
 * @author  Rémi Maréchal (Geomatys)
 * @version 3.1
 * @since   3.1
 */
@UML(identifier="MD_FeatureTypeInfo", specification=ISO_19115)
public interface FeatureTypeInfo {
    /**
     * Name of the feature type.
     *
     * @return name of the feature type.
     *
     * @see org.opengis.feature.FeatureType#getName()
     */
    @UML(identifier="featureTypeName", obligation=MANDATORY, specification=ISO_19115)
    GenericName getFeatureTypeName();

    /**
     * Number of occurrence of feature instances for this feature types.
     * May be {@code null} if unspecified.
     *
     * @return number of occurrence of feature instances for this feature types, or {@code null} if none.
     */
    @UML(identifier="featureInstanceCount", obligation=OPTIONAL, specification=ISO_19115)
    default Integer getFeatureInstanceCount() {
        return null;
    }
}
