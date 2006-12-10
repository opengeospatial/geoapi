/*$************************************************************************************************
 **
 ** $Id$
 **
 ** $Source: /cvsroot/geoapi/src/org/opengis/metadata/lineage/ProcessStep.java,v $
 **
 ** Copyright (C) 2004-2005 Open GIS Consortium, Inc.
 ** All Rights Reserved. http://www.opengis.org/legal/
 **
 *************************************************************************************************/
package org.opengis.metadata.lineage;

// J2SE direct dependencies
import java.util.Collection;
import java.util.Date;

// OpenGIS direct dependencies
import org.opengis.util.InternationalString;
import org.opengis.metadata.MetadataEntity;
import org.opengis.metadata.citation.ResponsibleParty;

// Annotations
import org.opengis.annotation.UML;
import static org.opengis.annotation.Obligation.*;
import static org.opengis.annotation.Specification.*;


/**
 * Description of the event, including related parameters or tolerances.
 *
 * @version <A HREF="http://www.opengis.org/docs/01-111.pdf">Abstract specification 5.0</A>
 * @author Martin Desruisseaux (IRD)
 * @since GeoAPI 2.0
 */
@UML(identifier="LI_ProcessStep", specification=ISO_19115)
public interface ProcessStep extends MetadataEntity{
    /**
     * Description of the event, including related parameters or tolerances.
     */
    @UML(identifier="description", obligation=MANDATORY, specification=ISO_19115)
    InternationalString getDescription();

    /**
     * Requirement or purpose for the process step.
     */
    @UML(identifier="rationale", obligation=OPTIONAL, specification=ISO_19115)
    InternationalString getRationale();

    /**
     * Date and time or range of date and time on or over which the process step occurred.
     */
    @UML(identifier="dateTime", obligation=OPTIONAL, specification=ISO_19115)
    Date getDate();

    /**
     * Identification of, and means of communication with, person(s) and
     * organization(s) associated with the process step.
     */
    @UML(identifier="processor", obligation=OPTIONAL, specification=ISO_19115)
    Collection<ResponsibleParty> getProcessors();

    /**
     * Information about the source data used in creating the data specified by the scope.
     */
    @UML(identifier="source", obligation=OPTIONAL, specification=ISO_19115)
    Collection<Source> getSources();
}
