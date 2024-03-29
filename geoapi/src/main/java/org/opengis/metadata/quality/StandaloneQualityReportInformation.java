/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    Copyright © 2022-2023 Open Geospatial Consortium, Inc.
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

import org.opengis.annotation.UML;
import org.opengis.metadata.citation.Citation;
import org.opengis.util.InternationalString;

import static org.opengis.annotation.Obligation.*;
import static org.opengis.annotation.Specification.*;


/**
 * Reference to an external standalone quality report.
 * In order to provide more details than reported as metadata,
 * a standalone quality report may additionally be created.
 * Its structure is free. However, the standalone quality report shall not replace the metadata.
 *
 * @author  Alexis Gaillard (IRD)
 * @author  Martin Desruisseaux (Geomatys)
 * @version 3.1
 * @since   3.1
 *
 * @todo Renamed in 19157:2022: {@code QualityEvaluationReportInformation}.
 */
@UML(identifier="DQ_StandaloneQualityReportInformation", specification=ISO_19157)
public interface StandaloneQualityReportInformation {
    /**
     * Reference to the associated standalone quality report.
     *
     * @return reference of the standalone quality report.
     */
    @UML(identifier="reportReference", obligation=MANDATORY, specification=ISO_19157)
    Citation getReportReference();

    /**
     * Abstract for the associated standalone quality report.
     *
     * @return abstract of the standalone quality report.
     */
    @UML(identifier="abstract", obligation=MANDATORY, specification=ISO_19157)
    InternationalString getAbstract();
}
