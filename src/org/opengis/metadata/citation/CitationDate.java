/*$************************************************************************************************
 **
 ** $Id$
 **
 ** $Source$
 **
 ** Copyright (C) 2003 Open GIS Consortium, Inc. All Rights Reserved. http://www.opengis.org/Legal/
 **
 *************************************************************************************************/
package org.opengis.metadata.citation;

// J2SE direct dependencies
import java.util.Date;

// Annotations
///import org.opengis.annotation.UML;
///import org.opengis.annotation.Profile;
///import static org.opengis.annotation.Obligation.*;
///import static org.opengis.annotation.ComplianceLevel.*;


/**
 * Reference date and event used to describe it.
 *
 * @author ISO 19115
 * @author <A HREF="http://www.opengis.org">OpenGIS&reg; consortium</A>
 * @version <A HREF="http://www.opengis.org/docs/01-111.pdf">Abstract specification 5.0</A>
 */
///@Profile (level=CORE)
///@UML (identifier="CI_Date")
public interface CitationDate {
    /**
     * Reference date for the cited resource.
     */
/// @Profile (level=CORE)
/// @UML (identifier="date", obligation=MANDATORY)
    Date getDate();

    /**
     * Event used for reference date.
     */
/// @Profile (level=CORE)
/// @UML (identifier="dateType", obligation=MANDATORY)
    DateType getDateType();
}
