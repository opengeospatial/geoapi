/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    http://www.geoapi.org
 *
 *    Copyright (C) 2009-2021 Open Geospatial Consortium, Inc.
 *    All Rights Reserved. http://www.opengeospatial.org/ogc/legal
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

import java.util.Collection;
import java.util.Collections;

import org.opengis.annotation.UML;
import org.opengis.metadata.Identifier;
import org.opengis.metadata.citation.Citation;
import org.opengis.util.InternationalString;

import static org.opengis.annotation.Obligation.*;
import static org.opengis.annotation.Specification.*;


/**
 * Comprehensive information about the procedure(s), process(es) and algorithm(s) applied
 * in the process step.
 *
 * @author  Cédric Briançon (Geomatys)
 * @version 3.1
 * @since   2.3
 */
@UML(identifier="LE_Processing", specification=ISO_19115_2)
public interface Processing {
    /**
     * Information to identify the processing package that produced the data.
     *
     * @return identifier of the processing package that produced the data.
     */
    @UML(identifier="identifier", obligation=MANDATORY, specification=ISO_19115_2)
    Identifier getIdentifier();

    /**
     * Reference to document describing processing software.
     *
     * @return document describing processing software.
     */
    @UML(identifier="softwareReference", obligation=OPTIONAL, specification=ISO_19115_2)
    default Collection<? extends Citation> getSoftwareReferences() {
        return Collections.emptyList();
    }

    /**
     * Additional details about the processing procedures.
     *
     * @return processing procedures, or {@code null}.
     */
    @UML(identifier="procedureDescription", obligation=OPTIONAL, specification=ISO_19115_2)
    default InternationalString getProcedureDescription() {
        return null;
    }

    /**
     * Reference to documentation describing the processing.
     *
     * @return documentation describing the processing.
     */
    @UML(identifier="documentation", obligation=OPTIONAL, specification=ISO_19115_2)
    default Collection<? extends Citation> getDocumentations() {
        return Collections.emptyList();
    }

    /**
     * Parameters to control the processing operations, entered at run time.
     *
     * @return parameters to control the processing operations, or {@code null}.
     */
    @UML(identifier="runTimeParameters", obligation=OPTIONAL, specification=ISO_19115_2)
    default InternationalString getRunTimeParameters() {
        return null;
    }

    /**
     * Details of the methodology by which geographic information was derived from the
     * instrument readings.
     *
     * @return methodology by which geographic information was derived from the instrument readings.
     */
    @UML(identifier="algorithm", obligation=OPTIONAL, specification=ISO_19115_2)
    default Collection<? extends Algorithm> getAlgorithms() {
        return Collections.emptyList();
    }
}
