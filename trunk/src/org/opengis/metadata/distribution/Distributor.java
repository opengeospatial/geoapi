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
import java.util.Set;

// OpenGIS direct dependencies
import org.opengis.metadata.citation.ResponsibleParty;

// Annotations
///import org.opengis.annotation.UML;
///import static org.opengis.annotation.Obligation.*;


/**
 * Information about the distributor.
 *
 * @author ISO 19115
 * @author <A HREF="http://www.opengis.org">OpenGIS&reg; consortium</A>
 * @version <A HREF="http://www.opengis.org/docs/01-111.pdf">Abstract specification 5.0</A>
 */
///@UML (identifier="MD_Distributor")
public interface Distributor {
    /**
     * Party from whom the resource may be obtained. This list need not be exhaustive.
     */
/// @UML (identifier="distributorContact", obligation=MANDATORY)
    ResponsibleParty getDistributorContact();

    /**
     * Provides information about how the resource may be obtained, and related
     * instructions and fee information.
     */
/// @UML (identifier="distributionOrderProcess", obligation=OPTIONAL)
    Set/*<StandardOrderProcess>*/ getDistributionOrderProcesses();

    /**
     * Provides information about the format used by the distributor.
     */
/// @UML (identifier="distributorFormat", obligation=CONDITIONAL)
    Set/*<Format>*/ getDistributorFormats();

    /**
     * Provides information about the technical means and media used by the distributor.
     */
/// @UML (identifier="distributorTransferOptions", obligation=OPTIONAL)
    Set/*<DigitalTransferOptions>*/ getDistributorTransferOptions();
}
