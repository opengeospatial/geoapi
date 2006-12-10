/*$************************************************************************************************
 **
 ** $Id$
 **
 ** $Source: /cvsroot/geoapi/src/org/opengis/metadata/distribution/DigitalTransferOptions.java,v $
 **
 ** Copyright (C) 2004-2005 Open GIS Consortium, Inc.
 ** All Rights Reserved. http://www.opengis.org/legal/
 **
 *************************************************************************************************/
package org.opengis.metadata.distribution;

// J2SE direct dependencies
import static org.opengis.annotation.ComplianceLevel.CORE;
import static org.opengis.annotation.Obligation.OPTIONAL;
import static org.opengis.annotation.Specification.ISO_19115;

import java.util.Collection;

import org.opengis.annotation.Profile;
import org.opengis.annotation.UML;
import org.opengis.metadata.MetadataEntity;
import org.opengis.metadata.citation.OnLineResource;
import org.opengis.util.InternationalString;


/**
 * Technical means and media by which a resource is obtained from the distributor.
 *
 * @version <A HREF="http://www.opengis.org/docs/01-111.pdf">Abstract specification 5.0</A>
 * @author Martin Desruisseaux (IRD)
 * @since GeoAPI 2.0
 */
@Profile (level=CORE)
@UML(identifier="MD_DigitalTransferOptions", specification=ISO_19115)
public interface DigitalTransferOptions extends MetadataEntity{
    /**
     * Tiles, layers, geographic areas, etc., in which data is available.
     */
    @UML(identifier="unitsOfDistribution", obligation=OPTIONAL, specification=ISO_19115)
    InternationalString getUnitsOfDistribution();

    /**
     * Estimated size of a unit in the specified transfer format, expressed in megabytes.
     * The transfer size is &gt; 0.0.
     * Returns {@code null} if the transfer size is unknown.
     */
    @UML(identifier="transferSize", obligation=OPTIONAL, specification=ISO_19115)
    Number getTransferSize();

    /**
     * Information about online sources from which the resource can be obtained.
     */
    @Profile (level=CORE)
    @UML(identifier="onLine", obligation=OPTIONAL, specification=ISO_19115)
    Collection<OnLineResource> getOnLines();

    /**
     * Information about offline media on which the resource can be obtained.
     */
    @UML(identifier="offLine", obligation=OPTIONAL, specification=ISO_19115)
    Medium getOffLine();
}
