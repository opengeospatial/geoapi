/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    http://www.geoapi.org
 *
 *    Copyright (C) 2004-2021 Open Geospatial Consortium, Inc.
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
import java.util.Collections;
import org.opengis.util.InternationalString;
import org.opengis.temporal.PeriodDuration;
import org.opengis.metadata.citation.OnlineResource;
import org.opengis.annotation.UML;

import static org.opengis.annotation.Obligation.*;
import static org.opengis.annotation.Specification.*;


/**
 * Technical means and media by which a resource is obtained from the distributor.
 *
 * @author  Martin Desruisseaux (IRD)
 * @author  Cory Horner (Refractions Research)
 * @version 3.1
 * @since   2.0
 */
@UML(identifier="MD_DigitalTransferOptions", specification=ISO_19115)
public interface DigitalTransferOptions {
    /**
     * Tiles, layers, geographic areas, <cite>etc.</cite>, in which data is available.
     * Units of distribution apply to both onLine and offLine distributions.
     *
     * @return tiles, layers, geographic areas, <cite>etc.</cite> in which data is available, or {@code null}.
     */
    @UML(identifier="unitsOfDistribution", obligation=OPTIONAL, specification=ISO_19115)
    default InternationalString getUnitsOfDistribution() {
        return null;
    }

    /**
     * Estimated size of a unit in the specified transfer format, expressed in megabytes.
     * The transfer size shall be greater than zero.
     * Returns {@code null} if the transfer size is unknown.
     *
     * @return estimated size of a unit in the specified transfer format in megabytes, or {@code null}.
     */
    @UML(identifier="transferSize", obligation=OPTIONAL, specification=ISO_19115)
    default Double getTransferSize() {
        return null;
    }

    /**
     * Information about online sources from which the resource can be obtained.
     *
     * @return online sources from which the resource can be obtained.
     */
    @UML(identifier="onLine", obligation=OPTIONAL, specification=ISO_19115)
    default Collection<? extends OnlineResource> getOnLines() {
        return Collections.emptyList();
    }

    /**
     * Information about offline media on which the resource can be obtained.
     *
     * @return offline media on which the resource can be obtained.
     *
     * @since 3.1
     */
    @UML(identifier="offLine", obligation=OPTIONAL, specification=ISO_19115)
    default Collection<? extends Medium> getOffLines() {
        return Collections.emptyList();
    }

    /**
     * Information about offline media on which the resource can be obtained.
     *
     * @return offline media on which the resource can be obtained, or {@code null}.
     *
     * @deprecated As of ISO 19115:2014, replaced by {@link #getOffLines()}.
     */
    @Deprecated
    Medium getOffLine();

    /**
     * Rate of occurrence of distribution.
     *
     * @return rate of occurrence of distribution, or {@code null} if none.
     *
     * @since 3.1
     */
    @UML(identifier="transferFrequency", obligation=OPTIONAL, specification=ISO_19115)
    default PeriodDuration getTransferFrequency() {
        return null;
    }

    /**
     * Formats of distribution.
     *
     * @return formats of distribution.
     *
     * @since 3.1
     */
    @UML(identifier="distributionFormat", obligation=OPTIONAL, specification=ISO_19115)
    default Collection<? extends Format> getDistributionFormats() {
        return Collections.emptyList();
    }
}
