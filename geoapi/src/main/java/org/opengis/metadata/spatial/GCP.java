/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    Copyright © 2009-2011 Open Geospatial Consortium, Inc.
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

import org.opengis.annotation.UML;
import org.opengis.geometry.DirectPosition;
import org.opengis.metadata.quality.Element;

import static org.opengis.annotation.Obligation.*;
import static org.opengis.annotation.Specification.*;


/**
 * Information on ground control point.
 *
 * @author  Cédric Briançon (Geomatys)
 * @version 3.0
 * @since   2.3
 *
 * @navassoc 1 - - DirectPosition
 * @navassoc - - - Element
 */
@UML(identifier="MI_GCP", specification=ISO_19115_2)
public interface GCP {
    /**
     * Geographic or map position of the control point, in either two or three dimensions.
     *
     * @return Geographic or map position of the control point.
     */
    @UML(identifier="geographicCoordinates", obligation=MANDATORY, specification=ISO_19115_2)
    DirectPosition getGeographicCoordinates();

    /**
     * Accuracy of a ground control point.
     *
     * @return Accuracy of a ground control point.
     */
    @UML(identifier="accuracyReport", obligation=OPTIONAL, specification=ISO_19115_2)
    Collection<? extends Element> getAccuracyReports();
}
