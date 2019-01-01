/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    http://www.geoapi.org
 *
 *    Copyright (C) 2004-2019 Open Geospatial Consortium, Inc.
 *    All Rights Reserved. http://www.opengeospatial.org/ogc/legal
 *
 *    Permission to use, copy, and modify this software and its documentation, with
 *    or without modification, for any purpose and without fee or royalty is hereby
 *    granted, provided that you include the following on ALL copies of the software
 *    and documentation or portions thereof, including modifications, that you make:
 *
 *    1. The full text of this NOTICE in a location viewable to users of the
 *       redistributed or derivative work.
 *    2. Notice of any changes or modifications to the OGC files, including the
 *       date changes were made.
 *
 *    THIS SOFTWARE AND DOCUMENTATION IS PROVIDED "AS IS," AND COPYRIGHT HOLDERS MAKE
 *    NO REPRESENTATIONS OR WARRANTIES, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED
 *    TO, WARRANTIES OF MERCHANTABILITY OR FITNESS FOR ANY PARTICULAR PURPOSE OR THAT
 *    THE USE OF THE SOFTWARE OR DOCUMENTATION WILL NOT INFRINGE ANY THIRD PARTY
 *    PATENTS, COPYRIGHTS, TRADEMARKS OR OTHER RIGHTS.
 *
 *    COPYRIGHT HOLDERS WILL NOT BE LIABLE FOR ANY DIRECT, INDIRECT, SPECIAL OR
 *    CONSEQUENTIAL DAMAGES ARISING OUT OF ANY USE OF THE SOFTWARE OR DOCUMENTATION.
 *
 *    The name and trademarks of copyright holders may NOT be used in advertising or
 *    publicity pertaining to the software without specific, written prior permission.
 *    Title to copyright in this software and any associated documentation will at all
 *    times remain with copyright holders.
 */
package org.opengis.metadata.distribution;

import java.util.Collection;
import org.opengis.annotation.UML;
import org.opengis.annotation.Profile;
import org.opengis.util.InternationalString;

import static org.opengis.annotation.Obligation.*;
import static org.opengis.annotation.Specification.*;
import static org.opengis.annotation.ComplianceLevel.*;


/**
 * Information about the distributor of and options for obtaining the resource.
 *
 * @author  Martin Desruisseaux (IRD)
 * @version 3.1
 * @since   2.0
 */
@UML(identifier="MD_Distribution", specification=ISO_19115)
public interface Distribution {
    /**
     * Brief description of a set of distribution options.
     *
     * @return brief description of a set of distribution options.
     *
     * @since 3.1
     */
    @UML(identifier="description", obligation=OPTIONAL, specification=ISO_19115)
    InternationalString getDescription();

    /**
     * Provides a description of the format of the data to be distributed.
     *
     * @return description of the format of the data to be distributed.
     *
     * @condition Mandatory if {@link Distributor#getDistributorFormats()} is empty.
     *
     * @see org.opengis.metadata.identification.Identification#getResourceFormats()
     */
    @Profile(level=CORE)
    @UML(identifier="distributionFormat", obligation=CONDITIONAL, specification=ISO_19115)
    Collection<? extends Format> getDistributionFormats();

    /**
     * Provides information about the distributor.
     *
     * @return information about the distributor.
     */
    @UML(identifier="distributor", obligation=OPTIONAL, specification=ISO_19115)
    Collection<? extends Distributor> getDistributors();

    /**
     * Provides information about technical means and media by which a resource is obtained
     * from the distributor.
     *
     * @return technical means and media by which a resource is obtained from the distributor.
     */
    @Profile(level=CORE)
    @UML(identifier="transferOptions", obligation=OPTIONAL, specification=ISO_19115)
    Collection<? extends DigitalTransferOptions> getTransferOptions();
}
