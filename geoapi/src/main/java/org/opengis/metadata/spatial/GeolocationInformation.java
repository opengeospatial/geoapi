/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    Copyright © 2009-2023 Open Geospatial Consortium, Inc.
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
package org.opengis.metadata.spatial;

import java.util.Collection;
import java.util.Collections;

import org.opengis.annotation.UML;
import org.opengis.annotation.Classifier;
import org.opengis.annotation.Stereotype;
import org.opengis.metadata.quality.DataQuality;

import static org.opengis.annotation.Obligation.*;
import static org.opengis.annotation.Specification.*;


/**
 * Information used to determine geographic location corresponding to image location.
 *
 * @author  Cédric Briançon (Geomatys)
 * @version 3.1
 * @since   2.3
 */
@Classifier(Stereotype.ABSTRACT)
@UML(identifier="MI_GeolocationInformation", specification=ISO_19115_2)
public interface GeolocationInformation {
    /**
     * Provides an overall assessment of quality of geolocation information.
     *
     * @return an overall assessment of quality of geolocation information.
     */
    @UML(identifier="qualityInfo", obligation=OPTIONAL, specification=ISO_19115_2)
    default Collection<? extends DataQuality> getQualityInfo() {
        return Collections.emptyList();
    }
}
