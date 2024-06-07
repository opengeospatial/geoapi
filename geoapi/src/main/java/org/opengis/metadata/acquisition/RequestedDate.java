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

import java.util.Date;
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
     *
     * <div class="warning"><b>Upcoming API change — temporal schema</b><br>
     * As of Java 8, the {@code java.time} package is a better match for the different
     * types of date defined by ISO 19108 (<cite>Temporal Schema</cite>) or ISO 19103.
     * The return value of this method may be changed to {@link java.time.temporal.Temporal} in GeoAPI 4.0.
     * </div>
     *
     * @return preferred date and time.
     */
    @UML(identifier="requestedDateOfCollection", obligation=MANDATORY, specification=ISO_19115_2)
    Date getRequestedDateOfCollection();

    /**
     * Latest date and time collection must be completed.
     *
     * <div class="warning"><b>Upcoming API change — temporal schema</b><br>
     * As of Java 8, the {@code java.time} package is a better match for the different
     * types of date defined by ISO 19108 (<cite>Temporal Schema</cite>) or ISO 19103.
     * The return value of this method may be changed to {@link java.time.temporal.Temporal} in GeoAPI 4.0.
     * </div>
     *
     * @return latest date and time.
     */
    @UML(identifier="latestAcceptableDate", obligation=MANDATORY, specification=ISO_19115_2)
    Date getLatestAcceptableDate();
}
