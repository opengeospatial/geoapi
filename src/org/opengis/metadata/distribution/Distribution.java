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


/**
 * Information about the distributor of and options for obtaining the resource.
 *
 * @UML datatype MD_Distribution
 * @author ISO 19115
 * @author <A HREF="http://www.opengis.org">OpenGIS&reg; consortium</A>
 * @version <A HREF="http://www.opengis.org/docs/01-111.pdf">Abstract specification 5.0</A>
 */
public interface Distribution {
    /**
     * Provides a description of the format of the data to be distributed.
     *
     * @UML conditional distributionFormat
     */
    Format[] getDistributionFormats();

    /**
     * Provides information about the distributor.
     *
     * @UML optional distributor
     */
    Distributor[] getDistributors();

    /**
     * Provides information about technical means and media by which a resource is obtained
     * from the distributor.
     *
     * @UML optional transferOptions
     */
    DigitalTransferOptions[] getTransferOptions();
}
