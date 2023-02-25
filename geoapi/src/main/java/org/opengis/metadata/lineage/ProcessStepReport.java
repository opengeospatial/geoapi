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
package org.opengis.metadata.lineage;

import org.opengis.annotation.UML;
import org.opengis.util.InternationalString;

import static org.opengis.annotation.Obligation.*;
import static org.opengis.annotation.Specification.*;


/**
 * Report of what occurred during the process step.
 *
 * @author  Cédric Briançon (Geomatys)
 * @version 3.1
 * @since   2.3
 */
@UML(identifier="LE_ProcessStepReport", specification=ISO_19115_2)
public interface ProcessStepReport {
    /**
     * Name of the processing report.
     *
     * @return name of the processing report.
     */
    @UML(identifier="name", obligation=MANDATORY, specification=ISO_19115_2)
    InternationalString getName();

    /**
     * Textual description of what occurred during the process step.
     *
     * @return what occurred during the process step, or {@code null}.
     */
    @UML(identifier="description", obligation=OPTIONAL, specification=ISO_19115_2)
    default InternationalString getDescription() {
        return null;
    }

    /**
     * Type of file that contains the processing report.
     *
     * @return type of file that contains the processing report, or {@code null}.
     */
    @UML(identifier="fileType", obligation=OPTIONAL, specification=ISO_19115_2)
    default InternationalString getFileType() {
        return null;
    }
}
