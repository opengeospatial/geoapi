/*$************************************************************************************************
 **
 ** $Id$
 **
 ** $Source$
 **
 ** Copyright (C) 2003 Open GIS Consortium, Inc. All Rights Reserved. http://www.opengis.org/Legal/
 **
 *************************************************************************************************/
package org.opengis.metadata.lineage;

// J2SE direct dependencies
import java.util.Set;
import java.util.Date;

// OpenGIS direct dependencies
import org.opengis.util.InternationalString;
import org.opengis.metadata.citation.ResponsibleParty;

// Annotations
///import org.opengis.annotation.UML;
///import static org.opengis.annotation.Obligation.*;


/**
 * Description of the event, including related parameters or tolerances.
 *
 * @author ISO 19115
 * @author <A HREF="http://www.opengis.org">OpenGIS&reg; consortium</A>
 * @version <A HREF="http://www.opengis.org/docs/01-111.pdf">Abstract specification 5.0</A>
 */
///@UML (identifier="LI_ProcessStep")
public interface ProcessStep {
    /**
     * Description of the event, including related parameters or tolerances.
     */
/// @UML (identifier="description", obligation=MANDATORY)
    InternationalString getDescription();

    /**
     * Requirement or purpose for the process step.
     */
/// @UML (identifier="rationale", obligation=OPTIONAL)
    InternationalString getRationale();

    /**
     * Date and time or range of date and time on or over which the process step occurred.
     */
/// @UML (identifier="dateTime", obligation=OPTIONAL)
    Date getDate();

    /**
     * Identification of, and means of communication with, person(s) and
     * organization(s) associated with the process step.
     */
/// @UML (identifier="processor", obligation=OPTIONAL)
    Set/*<ResponsibleParty>*/ getProcessors();

    /**
     * Information about the source data used in creating the data specified by the scope.
     */
/// @UML (identifier="source", obligation=OPTIONAL)
    Set/*<Source>*/ getSources();
}
