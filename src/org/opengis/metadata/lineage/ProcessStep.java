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


/**
 * Description of the event, including related parameters or tolerances.
 *
 * @UML datatype LI_ProcessStep
 * @author ISO 19115
 * @author <A HREF="http://www.opengis.org">OpenGIS&reg; consortium</A>
 * @version <A HREF="http://www.opengis.org/docs/01-111.pdf">Abstract specification 5.0</A>
 */
public interface ProcessStep {
    /**
     * Description of the event, including related parameters or tolerances.
     *
     * @UML mandatory description
     */
    InternationalString getDescription();

    /**
     * Requirement or purpose for the process step.
     *
     * @UML optional rationale
     */
    InternationalString getRationale();

    /**
     * Date and time or range of date and time on or over which the process step occurred.
     *
     * @UML optional dateTime
     */
    Date getDate();

    /**
     * Identification of, and means of communication with, person(s) and
     * organization(s) associated with the process step.
     *
     * @UML optional processor
     */
    Set/*<ResponsibleParty>*/ getProcessors();

    /**
     * Information about the source data used in creating the data specified by the scope.
     *
     * @UML optional source
     */
    Set/*<Source>*/ getSources();
}
