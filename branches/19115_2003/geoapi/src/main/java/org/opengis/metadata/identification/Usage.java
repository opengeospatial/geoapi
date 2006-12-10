/*$************************************************************************************************
 **
 ** $Id$
 **
 ** $Source: /cvsroot/geoapi/src/org/opengis/metadata/identification/Usage.java,v $
 **
 ** Copyright (C) 2004-2005 Open GIS Consortium, Inc.
 ** All Rights Reserved. http://www.opengis.org/legal/
 **
 *************************************************************************************************/
package org.opengis.metadata.identification;

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
 * Brief description of ways in which the resource(s) is/are currently used.
 *
 * @version <A HREF="http://www.opengis.org/docs/01-111.pdf">Abstract specification 5.0</A>
 * @author Martin Desruisseaux (IRD)
 * @since GeoAPI 2.0
 */
@UML(identifier="MD_Usage", specification=ISO_19115)
public interface Usage extends MetadataEntity{
    /**
     * Brief description of the resource and/or resource series usage.
     */
    @UML(identifier="specificUsage", obligation=MANDATORY, specification=ISO_19115)
    InternationalString getSpecificUsage();

    /**
     * Date and time of the first use or range of uses of the resource and/or resource series.
     */
    @UML(identifier="usageDateTime", obligation=OPTIONAL, specification=ISO_19115)
    Date getUsageDateTime();

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
