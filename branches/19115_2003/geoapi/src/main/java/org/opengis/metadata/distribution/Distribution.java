/*$************************************************************************************************
 **
 ** $Id$
 **
 ** $Source: /cvsroot/geoapi/src/org/opengis/metadata/distribution/Distribution.java,v $
 **
 ** Copyright (C) 2004-2005 Open GIS Consortium, Inc.
 ** All Rights Reserved. http://www.opengis.org/legal/
 **
 *************************************************************************************************/
package org.opengis.metadata.distribution;

// J2SE dependencies
import static org.opengis.annotation.ComplianceLevel.CORE;
import static org.opengis.annotation.Obligation.CONDITIONAL;
import static org.opengis.annotation.Obligation.OPTIONAL;
import static org.opengis.annotation.Specification.ISO_19115;

import java.util.Collection;

import org.opengis.annotation.Profile;
import org.opengis.annotation.UML;
import org.opengis.metadata.MetadataEntity;


/**
 * Information about the distributor of and options for obtaining the resource.
 *
 * @version <A HREF="http://www.opengis.org/docs/01-111.pdf">Abstract specification 5.0</A>
 * @author Martin Desruisseaux (IRD)
 * @since GeoAPI 2.0
 */
@Profile (level=CORE)
@UML(identifier="MD_Distribution", specification=ISO_19115)
public interface Distribution extends MetadataEntity{
    /**
     * Provides a description of the format of the data to be distributed.
     */
    @Profile (level=CORE)
    @UML(identifier="distributionFormat", obligation=CONDITIONAL, specification=ISO_19115)
    Collection<Format> getDistributionFormats();

    /**
     * Provides information about the distributor.
     */
    @UML(identifier="distributor", obligation=OPTIONAL, specification=ISO_19115)
    Collection<Distributor> getDistributors();

    /**
     * Provides information about technical means and media by which a resource is obtained
     * from the distributor.
     */
    @Profile (level=CORE)
    @UML(identifier="transferOptions", obligation=OPTIONAL, specification=ISO_19115)
    Collection<DigitalTransferOptions> getTransferOptions();
}
