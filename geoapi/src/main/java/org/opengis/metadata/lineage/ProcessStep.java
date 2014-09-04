/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    http://www.geoapi.org
 *
 *    Copyright (C) 2004-2011 Open Geospatial Consortium, Inc.
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
import org.opengis.metadata.citation.ResponsibleParty;
import org.opengis.annotation.UML;

import static org.opengis.annotation.Obligation.*;
import static org.opengis.annotation.Specification.*;


/**
 * Description of the event, including related parameters or tolerances.
 *
 * @author  Martin Desruisseaux (IRD)
 * @author  Cédric Briançon (Geomatys)
 * @version 3.0
 * @since   2.0
 *
 * @navassoc - - - ResponsibleParty
 * @navassoc - - - Source
 */
@UML(identifier="LI_ProcessStep", specification=ISO_19115)
public interface ProcessStep {
    /**
     * Description of the event, including related parameters or tolerances.
     *
     * @return Description of the event.
     */
    @UML(identifier="description", obligation=MANDATORY, specification=ISO_19115)
    InternationalString getDescription();

    /**
     * Requirement or purpose for the process step.
     *
     * @return Requirement or purpose for the process step, or {@code null}.
     */
    @UML(identifier="rationale", obligation=OPTIONAL, specification=ISO_19115)
    InternationalString getRationale();

    /**
     * Date and time or range of date and time on or over which the process step occurred.
     * <p>
     * <TABLE WIDTH="80%" ALIGN="center" CELLPADDING="18" BORDER="4" BGCOLOR="#FFE0B0">
     *   <TR><TD>
     *     <P align="justify"><B>Warning:</B> The return type of this method may change
     *     in GeoAPI 3.1 release. It may be replaced by a type matching more closely
     *     either ISO 19108 (<cite>Temporal Schema</cite>) or ISO 19103.</P>
     *   </TD></TR>
     * </TABLE>
     *
     * @return Date on or over which the process step occurred, or {@code null}.
     */
    @UML(identifier="dateTime", obligation=OPTIONAL, specification=ISO_19115)
    Date getDate();

    /**
     * Identification of, and means of communication with, person(s) and
     * organization(s) associated with the process step.
     *
     * @return Means of communication with person(s) and organization(s) associated
     *         with the process step.
     */
    @UML(identifier="processor", obligation=OPTIONAL, specification=ISO_19115)
    Collection<? extends ResponsibleParty> getProcessors();

    /**
     * Information about the source data used in creating the data specified by the scope.
     *
     * @return Information about the source data used in creating the data.
     */
    @UML(identifier="source", obligation=OPTIONAL, specification=ISO_19115)
    Collection<? extends Source> getSources();

    /**
     * Description of the product generated as a result of the process step.
     *
     * @return Product generated as a result of the process step.
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
     * @return Procedure by which the algorithm was applied to derive geographic data
     *         from the raw instrument measurements
     *
     * @since 2.3
     */
    @UML(identifier="processingInformation", obligation=OPTIONAL, specification=ISO_19115_2)
    Processing getProcessingInformation();

    /**
     * Report generated by the process step.
     *
     * @return Report generated by the process step.
     *
     * @since 2.3
     */
    @UML(identifier="report", obligation=OPTIONAL, specification=ISO_19115_2)
    Collection<? extends ProcessStepReport> getReports();
}
