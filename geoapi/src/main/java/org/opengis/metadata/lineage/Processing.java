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
package org.opengis.metadata.lineage;

import java.util.Collection;

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
 * @version 3.0
 * @since   2.3
 *
 * @navassoc 1 - - Identifier
 * @navassoc - - - Citation
 * @navassoc - - - Algorithm
 */
@UML(identifier="LE_Processing", specification=ISO_19115_2)
public interface Processing {
    /**
     * Information to identify the processing package that produced the data.
     *
     * @return Identifier of the processing package that produced the data.
     */
    @UML(identifier="identifier", obligation=MANDATORY, specification=ISO_19115_2)
    Identifier getIdentifier();

    /**
     * Reference to document describing processing software.
     *
     * @return Document describing processing software.
     */
    @UML(identifier="softwareReference", obligation=OPTIONAL, specification=ISO_19115_2)
    Collection<? extends Citation> getSoftwareReferences();

    /**
     * Additional details about the processing procedures.
     *
     * @return Processing procedures.
     */
    @UML(identifier="procedureDescription", obligation=OPTIONAL, specification=ISO_19115_2)
    InternationalString getProcedureDescription();

    /**
     * Reference to documentation describing the processing.
     *
     * @return Documentation describing the processing.
     */
    @UML(identifier="documentation", obligation=OPTIONAL, specification=ISO_19115_2)
    Collection<? extends Citation> getDocumentations();

    /**
     * Parameters to control the processing operations, entered at run time.
     *
     * @return Parameters to control the processing operations.
     */
    @UML(identifier="runTimeParameters", obligation=OPTIONAL, specification=ISO_19115_2)
    InternationalString getRunTimeParameters();

    /**
     * Details of the methodology by which geographic information was derived from the
     * instrument readings.
     *
     * @return Methodology by which geographic information was derived from the
     * instrument readings.
     */
    @UML(identifier="algorithm", obligation=OPTIONAL, specification=ISO_19115_2)
    Collection<? extends Algorithm> getAlgorithms();
}
