/*$************************************************************************************************
 **
 ** $Id$
 **
 ** $URL$
 **
 ** Copyright (C) 2004-2005 Open GIS Consortium, Inc.
 ** All Rights Reserved. http://www.opengis.org/legal/
 **
 *************************************************************************************************/
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
 * @version <A HREF="http://www.opengeospatial.org/standards/as#01-111">ISO 19115</A>
 * @author  Martin Desruisseaux (IRD)
 * @author  Cédric Briançon (Geomatys)
 * @since   GeoAPI 2.0
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
     * @since GeoAPI 2.3
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
     * @since GeoAPI 2.3
     */
    @UML(identifier="processingInformation", obligation=OPTIONAL, specification=ISO_19115_2)
    Processing getProcessingInformation();

    /**
     * Report generated by the process step.
     *
     * @return Report generated by the process step.
     *
     * @since GeoAPI 2.3
     */
    @UML(identifier="report", obligation=OPTIONAL, specification=ISO_19115_2)
    Collection<? extends ProcessStepReport> getReports();
}