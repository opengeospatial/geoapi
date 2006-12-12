/*$************************************************************************************************
 **
 ** $Id$
 **
 ** $Source: /cvsroot/geoapi/src/org/opengis/metadata/identification/Identification.java,v $
 **
 ** Copyright (C) 2004-2005 Open GIS Consortium, Inc.
 ** All Rights Reserved. http://www.opengis.org/legal/
 **
 *************************************************************************************************/
package org.opengis.metadata.identification;

// J2SE direct dependencies
import static org.opengis.annotation.ComplianceLevel.CORE;
import static org.opengis.annotation.Obligation.MANDATORY;
import static org.opengis.annotation.Obligation.OPTIONAL;
import static org.opengis.annotation.Specification.ISO_19115;

import org.opengis.annotation.Profile;
import org.opengis.annotation.UML;
import org.opengis.metadata.Identifier;
import org.opengis.metadata.MetadataEntity;
import org.opengis.metadata.citation.Citation;


/**
 * Aggregate dataset information.
 * <p>
 * Information about datasets that are aggregate parts of the dataset that the metadata describes.
 * </p>
 *
 * @version <A HREF="http://www.opengis.org/docs/01-111.pdf">Abstract specification 5.0</A>
 * @author Gabriel Roldan, Axios Engineering
 * @since GeoAPI 2.1
 */
@Profile (level=CORE)
@UML(identifier="MD_AggregateInformation", specification=ISO_19115)
public interface AggregateInformation extends MetadataEntity{
    /**
     * citation information about the aggregate dataset.
     */
    @Profile (level=CORE)
    @UML(identifier="aggregateDataSetName", obligation=OPTIONAL, specification=ISO_19115)
    Citation getAggregateDataSetName();

    /**
     * Identification information about aggregate dataset.
     */
    @Profile (level=CORE)
    @UML(identifier="aggregateDataSetIdentifier", obligation=OPTIONAL, specification=ISO_19115)
    Identifier getAggregateDataSetIdentifier();

    /**
     * Association type of the aggregate dataset.
     */
    @UML(identifier="associationType", obligation=MANDATORY, specification=ISO_19115)
    AssociationType getAssociationType();

    /**
     * Type of initiative under which the aggregate dataset was produced.
     */
    @UML(identifier="initiativeType", obligation=OPTIONAL, specification=ISO_19115)
    InitiativeType getInitiativeType();

}
