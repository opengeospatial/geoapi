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
import java.util.Set;

// Annotations
///import org.opengis.annotation.UML;
///import static org.opengis.annotation.Obligation.*;


/**
 * Information about the distributor of and options for obtaining the resource.
 *
 * @author ISO 19115
 * @author <A HREF="http://www.opengis.org">OpenGIS&reg; consortium</A>
 * @version <A HREF="http://www.opengis.org/docs/01-111.pdf">Abstract specification 5.0</A>
 */
///@UML (identifier="MD_Distribution")
public interface Distribution {
    /**
     * Provides a description of the format of the data to be distributed.
     */
/// @UML (identifier="distributionFormat", obligation=CONDITIONAL)
    Set/*<Format>*/ getDistributionFormats();

    /**
     * Provides information about the distributor.
     */
/// @UML (identifier="distributor", obligation=OPTIONAL)
    Set/*<Distributor>*/ getDistributors();

    /**
     * Provides information about technical means and media by which a resource is obtained
     * from the distributor.
     */
/// @UML (identifier="transferOptions", obligation=OPTIONAL)
    Set/*<DigitalTransferOptions>*/ getTransferOptions();
}
