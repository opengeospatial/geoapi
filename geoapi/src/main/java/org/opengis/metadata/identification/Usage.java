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
package org.opengis.metadata.identification;

// J2SE direct dependencies
import static org.opengis.annotation.Obligation.MANDATORY;
import static org.opengis.annotation.Obligation.OPTIONAL;
import static org.opengis.annotation.Specification.ISO_19115;

import java.util.Collection;
import java.util.Date;

import org.opengis.annotation.UML;
import org.opengis.metadata.citation.ResponsibleParty;
import org.opengis.util.InternationalString;


/**
 * Brief description of ways in which the resource(s) is/are currently used.
 *
 * @version <A HREF="http://www.opengis.org/docs/01-111.pdf">Abstract specification 5.0</A>
 * @author Martin Desruisseaux (IRD)
 * @since GeoAPI 2.0
 */
@UML(identifier="MD_Usage", specification=ISO_19115)
public interface Usage {
    /**
     * Brief description of the resource and/or resource series usage.
     */
    @UML(identifier="specificUsage", obligation=MANDATORY, specification=ISO_19115)
    InternationalString getSpecificUsage();

    /**
     * Date and time of the first use or range of uses of the resource and/or resource series.
     */
    @UML(identifier="usageDateTime", obligation=OPTIONAL, specification=ISO_19115)
    Date getUsageDate();

    /**
     * Applications, determined by the user for which the resource and/or resource series
     * is not suitable.
     */
    @UML(identifier="userDeterminedLimitations", obligation=OPTIONAL, specification=ISO_19115)
    InternationalString getUserDeterminedLimitations();

    /**
     * Identification of and means of communicating with person(s) and organization(s)
     * using the resource(s).
     */
    @UML(identifier="userContactInfo", obligation=MANDATORY, specification=ISO_19115)
    Collection<ResponsibleParty> getUserContactInfo();
}
