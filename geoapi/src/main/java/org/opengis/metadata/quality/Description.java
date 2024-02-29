/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    Copyright © 2005-2023 Open Geospatial Consortium, Inc.
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
package org.opengis.metadata.quality;

import org.opengis.util.InternationalString;
import org.opengis.annotation.UML;
import org.opengis.metadata.identification.BrowseGraphic;

import static org.opengis.annotation.Obligation.*;
import static org.opengis.annotation.Specification.*;


/**
 * Data quality measure description.
 * A description contains a mandatory text and an optional illustration.
 *
 * @author  Alexis Gaillard (Geomatys)
 * @author  Martin Desruisseaux (Geomatys)
 * @version 3.1
 * @since   3.1
 *
 * @todo Renamed in 19157:2022: {@code MeasureDescription}.
 */
@UML(identifier="DQM_Description", specification=ISO_19157)
public interface Description {
    /**
     * Text description.
     *
     * @return text description.
     */
    @UML(identifier="textDescription", obligation=MANDATORY, specification=ISO_19157)
    InternationalString getTextDescription();

    /**
     * Illustration.
     *
     * @return description illustration, or {@code null} if none.
     */
    @UML(identifier="extendedDescription", obligation=OPTIONAL, specification=ISO_19157)
    default BrowseGraphic getExtendedDescription() {
        return null;
    }
}
