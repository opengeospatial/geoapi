/*$************************************************************************************************
 **
 ** $Id$
 **
 ** $Source$
 **
 ** Copyright (C) 2003 Open GIS Consortium, Inc. All Rights Reserved. http://www.opengis.org/Legal/
 **
 *************************************************************************************************/
package org.opengis.metadata.identification;

// J2SE direct dependencies
import java.util.Collection;
import java.util.Date;

// OpenGIS direct dependencies
import org.opengis.util.InternationalString;
import org.opengis.metadata.citation.ResponsibleParty;

// Annotations
///import org.opengis.annotation.UML;
///import static org.opengis.annotation.Obligation.*;


/**
 * Brief description of ways in which the resource(s) is/are currently used.
 *
 * @author ISO 19115
 * @author <A HREF="http://www.opengis.org">OpenGIS&reg; consortium</A>
 * @version <A HREF="http://www.opengis.org/docs/01-111.pdf">Abstract specification 5.0</A>
 */
///@UML (identifier="MD_Usage")
public interface Usage {
    /**
     * Brief description of the resource and/or resource series usage.
     */
/// @UML (identifier="specificUsage", obligation=MANDATORY)
    InternationalString getSpecificUsage();

    /**
     * Date and time of the first use or range of uses of the resource and/or resource series.
     */
/// @UML (identifier="usageDateTime", obligation=OPTIONAL)
    Date getUsageDate();

    /**
     * Applications, determined by the user for which the resource and/or resource series
     * is not suitable.
     */
/// @UML (identifier="userDeterminedLimitations", obligation=OPTIONAL)
    InternationalString getUserDeterminedLimitations();

    /**
     * Identification of and means of communicating with person(s) and organization(s)
     * using the resource(s).
     */
/// @UML (identifier="userContactInfo", obligation=MANDATORY)
    Collection/*<ResponsibleParty>*/ getUserContactInfo();
}
