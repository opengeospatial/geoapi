/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    http://www.geoapi.org
 *
 *    Copyright (C) 2004-2019 Open Geospatial Consortium, Inc.
 *    All Rights Reserved. http://www.opengeospatial.org/ogc/legal
 *
 *    Permission to use, copy, and modify this software and its documentation, with
 *    or without modification, for any purpose and without fee or royalty is hereby
 *    granted, provided that you include the following on ALL copies of the software
 *    and documentation or portions thereof, including modifications, that you make:
 *
 *    1. The full text of this NOTICE in a location viewable to users of the
 *       redistributed or derivative work.
 *    2. Notice of any changes or modifications to the OGC files, including the
 *       date changes were made.
 *
 *    THIS SOFTWARE AND DOCUMENTATION IS PROVIDED "AS IS," AND COPYRIGHT HOLDERS MAKE
 *    NO REPRESENTATIONS OR WARRANTIES, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED
 *    TO, WARRANTIES OF MERCHANTABILITY OR FITNESS FOR ANY PARTICULAR PURPOSE OR THAT
 *    THE USE OF THE SOFTWARE OR DOCUMENTATION WILL NOT INFRINGE ANY THIRD PARTY
 *    PATENTS, COPYRIGHTS, TRADEMARKS OR OTHER RIGHTS.
 *
 *    COPYRIGHT HOLDERS WILL NOT BE LIABLE FOR ANY DIRECT, INDIRECT, SPECIAL OR
 *    CONSEQUENTIAL DAMAGES ARISING OUT OF ANY USE OF THE SOFTWARE OR DOCUMENTATION.
 *
 *    The name and trademarks of copyright holders may NOT be used in advertising or
 *    publicity pertaining to the software without specific, written prior permission.
 *    Title to copyright in this software and any associated documentation will at all
 *    times remain with copyright holders.
 */
package org.opengis.metadata.lineage;

import java.util.Collection;
import java.util.Date;
import org.opengis.util.InternationalString;
import org.opengis.metadata.citation.Citation;
import org.opengis.metadata.citation.Responsibility;
import org.opengis.metadata.maintenance.Scope;
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
    InternationalString getRationale();

    /**
     * Date and time or range of date and time on or over which the process step occurred.
     *
     * <div class="warning"><b>Upcoming API change — temporal schema</b><br>
     * The return type of this method may change in GeoAPI 4.0 release. It may be replaced by a
     * type matching more closely either ISO 19108 (<cite>Temporal Schema</cite>) or ISO 19103.
     * </div>
     *
     * @return date on or over which the process step occurred, or {@code null} if none.
     */
    @UML(identifier="stepDateTime", obligation=OPTIONAL, specification=ISO_19115, version=2003)
    Date getDate();

    /**
     * Identification of, and means of communication with, person(s) and
     * organization(s) associated with the process step.
     *
     * @return means of communication with person(s) and organization(s) associated with the process step.
     */
    @UML(identifier="processor", obligation=OPTIONAL, specification=ISO_19115)
    Collection<? extends Responsibility> getProcessors();

    /**
     * Process step documentation.
     * Returns an empty collection if none.
     *
     * @return process step documentation.
     *
     * @since 3.1
     */
    @UML(identifier="reference", obligation=OPTIONAL, specification=ISO_19115)
    Collection<? extends Citation> getReferences();

    /**
     * Type of resource and / or extent to which the process step applies.
     *
     * @return type of resource and / or extent to which the process step applies, or {@code null} if none.
     *
     * @since 3.1
     */
    @UML(identifier="scope", obligation=OPTIONAL, specification=ISO_19115)
    Scope getScope();

    /**
     * Information about the source data used in creating the data specified by the scope.
     * Returns an empty collection if none.
     *
     * @return information about the source data used in creating the data.
     */
    @UML(identifier="source", obligation=OPTIONAL, specification=ISO_19115)
    Collection<? extends Source> getSources();

    /**
     * Description of the product generated as a result of the process step.
     *
     * @return product generated as a result of the process step.
     *
     * @since 2.3
     */
    @UML(identifier="output", obligation=OPTIONAL, specification=ISO_19115_2)
    Collection<? extends Source> getOutputs();

    /**
     * Comprehensive information about the procedure by which the algorithm was applied
     * to derive geographic data from the raw instrument measurements, such as datasets,
     * software used, and the processing environment.
     *
     * @return procedure by which the algorithm was applied to derive geographic data,
     *         or {@code null}.
     *
     * @since 2.3
     */
    @UML(identifier="processingInformation", obligation=OPTIONAL, specification=ISO_19115_2)
    Processing getProcessingInformation();

    /**
     * Report generated by the process step.
     *
     * @return report generated by the process step.
     *
     * @since 2.3
     */
    @UML(identifier="report", obligation=OPTIONAL, specification=ISO_19115_2)
    Collection<? extends ProcessStepReport> getReports();
}
