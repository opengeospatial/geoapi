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

// J2SE dependencies
import java.util.Collection;

// Annotations
///import org.opengis.annotation.UML;
///import org.opengis.annotation.Profile;
///import static org.opengis.annotation.Obligation.*;
///import static org.opengis.annotation.ComplianceLevel.*;


/**
 * Information about the distributor of and options for obtaining the resource.
 *
 * @author ISO 19115
 * @author <A HREF="http://www.opengis.org">OpenGIS&reg; consortium</A>
 * @version <A HREF="http://www.opengis.org/docs/01-111.pdf">Abstract specification 5.0</A>
 */
///@Profile (level=CORE)
///@UML (identifier="MD_Distribution")
public interface Distribution {
    /**
     * Provides a description of the format of the data to be distributed.
     */
/// @Profile (level=CORE)
/// @UML (identifier="distributionFormat", obligation=CONDITIONAL)
    Collection/*<Format>*/ getDistributionFormats();

    /**
     * Provides information about the distributor.
     */
/// @UML (identifier="distributor", obligation=OPTIONAL)
    Collection/*<Distributor>*/ getDistributors();

    /**
     * Provides information about technical means and media by which a resource is obtained
     * from the distributor.
     */
/// @Profile (level=CORE)
/// @UML (identifier="transferOptions", obligation=OPTIONAL)
    Collection/*<DigitalTransferOptions>*/ getTransferOptions();
}
