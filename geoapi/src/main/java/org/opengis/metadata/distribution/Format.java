/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    http://www.geoapi.org
 *
 *    Copyright (C) 2004-2011 Open Geospatial Consortium, Inc.
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
import org.opengis.util.InternationalString;
import org.opengis.annotation.UML;
import org.opengis.annotation.Profile;

import static org.opengis.annotation.Obligation.*;
import static org.opengis.annotation.Specification.*;
import static org.opengis.annotation.ComplianceLevel.*;


/**
 * Description of the computer language construct that specifies the representation
 * of data objects in a record, file, message, storage device or transmission channel.
 *
 * @author  Martin Desruisseaux (IRD)
 * @version 3.0
 * @since   2.0
 *
 * @navassoc - - - Distributor
 */
@UML(identifier="MD_Format", specification=ISO_19115)
public interface Format {
    /**
     * Name of the data transfer format(s).
     *
     * @return Name of the data transfer format(s).
     */
    @Profile(level=CORE)
    @UML(identifier="name", obligation=MANDATORY, specification=ISO_19115)
    InternationalString getName();

    /**
     * Version of the format (date, number, <i>etc</i>).
     *
     * @return Version of the format.
     */
    @Profile(level=CORE)
    @UML(identifier="version", obligation=MANDATORY, specification=ISO_19115)
    InternationalString getVersion();

    /**
     * Amendment number of the format version.
     *
     * @return Amendment number of the format version, or {@code null}.
     */
    @UML(identifier="amendmentNumber", obligation=OPTIONAL, specification=ISO_19115)
    InternationalString getAmendmentNumber();

    /**
     * Name of a subset, profile, or product specification of the format.
     *
     * @return Name of a subset, profile, or product specification of the format, or {@code null}.
     */
    @UML(identifier="specification", obligation=OPTIONAL, specification=ISO_19115)
    InternationalString getSpecification();

    /**
     * Recommendations of algorithms or processes that can be applied to read or
     * expand resources to which compression techniques have been applied.
     *
     * @return Processes that can be applied to read resources to which compression techniques have
     *         been applied, or {@code null}.
     */
    @UML(identifier="fileDecompressionTechnique", obligation=OPTIONAL, specification=ISO_19115)
    InternationalString getFileDecompressionTechnique();

    /**
     * Provides information about the distributor's format.
     *
     * @return Information about the distributor's format.
     */
    @UML(identifier="formatDistributor", obligation=OPTIONAL, specification=ISO_19115)
    Collection<? extends Distributor> getFormatDistributors();
}
