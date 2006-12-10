/*$************************************************************************************************
 **
 ** $Id$
 **
 ** $Source: /cvsroot/geoapi/src/org/opengis/metadata/citation/CitationDate.java,v $
 **
 ** Copyright (C) 2004-2005 Open GIS Consortium, Inc.
 ** All Rights Reserved. http://www.opengis.org/legal/
 **
 *************************************************************************************************/
package org.opengis.metadata.citation;

// J2SE direct dependencies
import static org.opengis.annotation.ComplianceLevel.CORE;
import static org.opengis.annotation.Obligation.MANDATORY;
import static org.opengis.annotation.Specification.ISO_19115;

import java.util.Date;

import org.opengis.annotation.Profile;
import org.opengis.annotation.UML;
import org.opengis.metadata.MetadataEntity;


/**
 * Reference date and event used to describe it.
 *
 * @version <A HREF="http://www.opengis.org/docs/01-111.pdf">Abstract specification 5.0</A>
 * @author Martin Desruisseaux (IRD)
 * @since GeoAPI 2.0
 */
@Profile (level=CORE)
@UML(identifier="CI_Date", specification=ISO_19115)
public interface CitationDate extends MetadataEntity{
    /**
     * Reference date for the cited resource.
     */
    @Profile (level=CORE)
    @UML(identifier="date", obligation=MANDATORY, specification=ISO_19115)
    Date getDate();

    /**
     * Event used for reference date.
     */
    @Profile (level=CORE)
    @UML(identifier="dateType", obligation=MANDATORY, specification=ISO_19115)
    DateType getDateType();
}
