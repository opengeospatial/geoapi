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
package org.opengis.metadata.acquisition;

import java.time.temporal.Temporal;
import org.opengis.annotation.UML;
import org.opengis.annotation.Classifier;
import org.opengis.annotation.Stereotype;

import static org.opengis.annotation.Obligation.*;
import static org.opengis.annotation.Specification.*;


/**
 * Range of date validity.
 *
 * @author  Cédric Briançon (Geomatys)
 * @version 3.1
 * @since   2.3
 */
@Classifier(Stereotype.DATATYPE)
@UML(identifier="MI_RequestedDate", specification=ISO_19115_2)
public interface RequestedDate {
    /**
     * Preferred date and time of collection.
     * Should be an instance of {@link java.time.LocalDateTime}, {@link java.time.OffsetDateTime} or
     * {@link java.time.ZonedDateTime}, depending how timezone is defined. Other types are also allowed.
     *
     * @return preferred date and time.
     */
    @UML(identifier="requestedDateOfCollection", obligation=MANDATORY, specification=ISO_19115_2)
    Temporal getRequestedDateOfCollection();

    /**
     * Latest date and time collection must be completed.
     * Should be an instance of {@link java.time.LocalDateTime}, {@link java.time.OffsetDateTime} or
     * {@link java.time.ZonedDateTime}, depending how timezone is defined. Other types are also allowed.
     *
     * @return latest date and time.
     */
    @UML(identifier="latestAcceptableDate", obligation=MANDATORY, specification=ISO_19115_2)
    Temporal getLatestAcceptableDate();
}
