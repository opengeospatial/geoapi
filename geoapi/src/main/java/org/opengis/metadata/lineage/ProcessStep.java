/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    Copyright © 2004-2023 Open Geospatial Consortium, Inc.
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
import java.util.Collections;
import java.util.Date;
import org.opengis.util.InternationalString;
import org.opengis.metadata.citation.Citation;
import org.opengis.metadata.citation.Responsibility;
import org.opengis.metadata.maintenance.Scope;
import org.opengis.temporal.TemporalPrimitive;
import org.opengis.geoapi.internal.Legacy;
import org.opengis.annotation.UML;

import static org.opengis.annotation.Obligation.*;
import static org.opengis.annotation.Specification.*;


/**
 * Information about an event or transformation in the life of resource.
 * This includes the process used to maintain the resource.
 *
 * @author  Martin Desruisseaux (IRD)
 * @author  Cédric Briançon (Geomatys)
 * @author  Rémi Maréchal (Geomatys)
 * @version 3.1
 * @since   2.0
 */
@UML(identifier="LI_ProcessStep", specification=ISO_19115)
public interface ProcessStep {
    /**
     * Description of the event, including related parameters or tolerances.
     *
     * @return description of the event.
     */
    @UML(identifier="description", obligation=MANDATORY, specification=ISO_19115)
    InternationalString getDescription();

    /**
     * Requirement or purpose for the process step.
     *
     * @return requirement or purpose for the process step, or {@code null} if none.
     */
    @UML(identifier="rationale", obligation=OPTIONAL, specification=ISO_19115)
    default InternationalString getRationale() {
        return null;
    }

    /**
     * Date and time on which the process step occurred.
     *
     * @return date on which the process step occurred, or {@code null} if none.
     *
     * @deprecated Replaced by {@link #getStepDateTime()} as of ISO 19115:2014.
     */
    @Deprecated(since="3.1")
    @UML(identifier="dateTime", obligation=OPTIONAL, specification=ISO_19115, version=2003)
    default Date getDate() {
        TemporalPrimitive p = getStepDateTime();
        return (p != null) ? Legacy.toDate(p.position().orElse(null)) : null;
    }

    /**
     * Date and time or range of date and time on or over which the process step occurred.
     *
     * @return date on or over which the process step occurred, or {@code null} if none.
     *
     * @since 3.1
     */
    @UML(identifier="stepDateTime", obligation=OPTIONAL, specification=ISO_19115)
    default TemporalPrimitive getStepDateTime() {
        return null;
    }

    /**
     * Identification of, and means of communication with, person(s) and
     * organization(s) associated with the process step.
     *
     * @return means of communication with person(s) and organization(s) associated with the process step.
     */
    @UML(identifier="processor", obligation=OPTIONAL, specification=ISO_19115)
    default Collection<? extends Responsibility> getProcessors() {
        return Collections.emptyList();
    }

    /**
     * Process step documentation.
     * Returns an empty collection if none.
     *
     * @return process step documentation.
     *
     * @since 3.1
     */
    @UML(identifier="reference", obligation=OPTIONAL, specification=ISO_19115)
    default Collection<? extends Citation> getReferences() {
        return Collections.emptyList();
    }

    /**
     * Type of resource and / or extent to which the process step applies.
     *
     * @return type of resource and / or extent to which the process step applies, or {@code null} if none.
     *
     * @since 3.1
     */
    @UML(identifier="scope", obligation=OPTIONAL, specification=ISO_19115)
    default Scope getScope() {
        return null;
    }

    /**
     * Information about the source data used in creating the data specified by the scope.
     * Returns an empty collection if none.
     *
     * @return information about the source data used in creating the data.
     */
    @UML(identifier="source", obligation=OPTIONAL, specification=ISO_19115)
    default Collection<? extends Source> getSources() {
        return Collections.emptyList();
    }

    /**
     * Description of the product generated as a result of the process step.
     *
     * @return product generated as a result of the process step.
     */
    @UML(identifier="output", obligation=OPTIONAL, specification=ISO_19115_2)
    default Collection<? extends Source> getOutputs() {
        return Collections.emptyList();
    }

    /**
     * Comprehensive information about the procedure by which the algorithm was applied
     * to derive geographic data from the raw instrument measurements, such as datasets,
     * software used, and the processing environment.
     *
     * @return procedure by which the algorithm was applied to derive geographic data,
     *         or {@code null}.
     */
    @UML(identifier="processingInformation", obligation=OPTIONAL, specification=ISO_19115_2)
    default Processing getProcessingInformation() {
        return null;
    }

    /**
     * Report generated by the process step.
     *
     * @return report generated by the process step.
     */
    @UML(identifier="report", obligation=OPTIONAL, specification=ISO_19115_2)
    default Collection<? extends ProcessStepReport> getReports() {
        return Collections.emptyList();
    }
}
