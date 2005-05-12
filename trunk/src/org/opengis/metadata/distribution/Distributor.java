/*$************************************************************************************************
 **
 ** $Id$
 **
 ** $Source$
 **
 ** Copyright (C) 2003 Open GIS Consortium, Inc. All Rights Reserved. http://www.opengis.org/Legal/
 **
 *************************************************************************************************/
package org.opengis.metadata.distribution;

// J2SE direct dependencies
import java.util.Collection;

// OpenGIS direct dependencies
import org.opengis.metadata.citation.ResponsibleParty;

// Annotations
import org.opengis.annotation.UML;
import static org.opengis.annotation.Obligation.*;
import static org.opengis.annotation.Specification.*;


/**
 * Information about the distributor.
 *
 * @author ISO 19115
 * @author <A HREF="http://www.opengis.org">OpenGIS&reg; consortium</A>
 * @version <A HREF="http://www.opengis.org/docs/01-111.pdf">Abstract specification 5.0</A>
 * @since GeoAPI 1.1
 */
@UML(identifier="MD_Distributor", specification=ISO_19115)
public interface Distributor {
    /**
     * Party from whom the resource may be obtained. This list need not be exhaustive.
     */
    @UML(identifier="distributorContact", obligation=MANDATORY, specification=ISO_19115)
    ResponsibleParty getDistributorContact();

    /**
     * Provides information about how the resource may be obtained, and related
     * instructions and fee information.
     */
    @UML(identifier="distributionOrderProcess", obligation=OPTIONAL, specification=ISO_19115)
    Collection<StandardOrderProcess> getDistributionOrderProcesses();

    /**
     * Provides information about the format used by the distributor.
     */
    @UML(identifier="distributorFormat", obligation=CONDITIONAL, specification=ISO_19115)
    Collection<Format> getDistributorFormats();

    /**
     * Provides information about the technical means and media used by the distributor.
     */
    @UML(identifier="distributorTransferOptions", obligation=OPTIONAL, specification=ISO_19115)
    Collection<DigitalTransferOptions> getDistributorTransferOptions();
}
