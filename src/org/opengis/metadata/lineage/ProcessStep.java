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
import java.util.Date;
import java.util.Locale;

// OpenGIS direct dependencies
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
     * @param  locale The desired locale for the description to be returned, or <code>null</code>
     *         for a description in some default locale (may or may not be the
     *         {@linkplain Locale#getDefault() system default}).
     * @return The description in the given locale.
     *         If no description is available in the given locale, then some default locale is used.
     * @UML mandatory description
     */
    String getDescription(Locale locale);

    /**
     * Requirement or purpose for the process step.
     *
     * @param  locale The desired locale for the rational to be returned, or <code>null</code>
     *         for a rational in some default locale (may or may not be the
     *         {@linkplain Locale#getDefault() system default}).
     * @return The rational in the given locale.
     *         If no rational is available in the given locale, then some default locale is used.
     * @UML optional rationale
     */
    String getRationale(Locale locale);

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
    ResponsibleParty[] getProcessors();

    /**
     * Information about the source data used in creating the data specified by the scope.
     *
     * @UML optional source
     */
    Source[] getSources();
}
