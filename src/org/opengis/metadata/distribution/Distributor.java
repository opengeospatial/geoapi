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

// OpenGIS direct dependencies
import org.opengis.metadata.citation.ResponsibleParty;


/**
 * Information about the distributor.
 *
 * @UML datatype MD_Distributor
 * @author ISO 19115
 * @author <A HREF="http://www.opengis.org">OpenGIS&reg; consortium</A>
 * @version <A HREF="http://www.opengis.org/docs/01-111.pdf">Abstract specification 5.0</A>
 */
public interface Distributor {
    /**
     * Party from whom the resource may be obtained. This list need not be exhaustive.
     *
     * @UML mandatory distributorContact
     */
    ResponsibleParty getDistributorContact();

    /**
     * Provides information about how the resource may be obtained, and related
     * instructions and fee information.
     *
     * @UML optional distributionOrderProcess
     */
    StandardOrderProcess[] getDistributionOrderProcesses();

    /**
     * Provides information about the format used by the distributor.
     *
     * @UML conditional distributorFormat
     */
    Format[] getDistributorFormat();

    /**
     * Provides information about the technical means and media used by the distributor.
     *
     * @UML optional distributorTransferOptions
     */
    DigitalTransferOptions[] getDistributorTransferOptions();
}
