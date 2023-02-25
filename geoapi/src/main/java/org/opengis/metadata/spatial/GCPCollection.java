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

import org.opengis.annotation.UML;
import org.opengis.referencing.ReferenceSystem;
import org.opengis.util.InternationalString;

import static org.opengis.annotation.Obligation.*;
import static org.opengis.annotation.Specification.*;


/**
 * Information about a control point collection.
 *
 * @author  Cédric Briançon (Geomatys)
 * @version 3.0
 * @since   2.3
 */
@UML(identifier="MI_GCPCollection", specification=ISO_19115_2)
public interface GCPCollection extends GeolocationInformation {
    /**
     * Identifier of the GCP collection.
     *
     * @return the identifier.
     */
    @UML(identifier="collectionIdentification", obligation=MANDATORY, specification=ISO_19115_2)
    Integer getCollectionIdentification();

    /**
     * Name of the GCP collection.
     *
     * @return name of the GCP collection.
     */
    @UML(identifier="collectionName", obligation=MANDATORY, specification=ISO_19115_2)
    InternationalString getCollectionName();

    /**
     * Coordinate system in which the ground control points are defined.
     *
     * @return coordinate system in which the ground control points are defined.
     */
    @UML(identifier="coordinateReferenceSystem", obligation=MANDATORY, specification=ISO_19115_2)
    ReferenceSystem getCoordinateReferenceSystem();

    /**
     * Ground control point(s) used in the collection.
     *
     * @return ground control point(s).
     */
    @UML(identifier="gcp", obligation=MANDATORY, specification=ISO_19115_2)
    Collection<? extends GCP> getGCPs();
}
