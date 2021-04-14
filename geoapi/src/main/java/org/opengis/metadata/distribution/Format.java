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
import java.util.Iterator;
import org.opengis.util.InternationalString;
import org.opengis.metadata.citation.Citation;
import org.opengis.annotation.UML;
import org.opengis.annotation.Profile;

import static org.opengis.annotation.Obligation.*;
import static org.opengis.annotation.Specification.*;
import static org.opengis.annotation.ComplianceLevel.*;


/**
 * Description of the computer language construct that specifies the representation
 * of data objects in a record, file, message, storage device or transmission channel.
 *
 * @author  Martin Desruisseaux (IRD, Geomatys)
 * @version 3.1
 * @since   2.0
 */
@UML(identifier="MD_Format", specification=ISO_19115)
public interface Format {
    /**
     * Citation / URL of the specification format.
     *
     * @return citation / URL of the specification format.
     *
     * @since 3.1
     */
    @Profile(level=CORE)
    @UML(identifier="formatSpecificationCitation", obligation=MANDATORY, specification=ISO_19115)
    Citation getFormatSpecificationCitation();

    /**
     * Name of a subset, profile, or product specification of the format.
     *
     * @return name of a subset, profile, or product specification of the format, or {@code null}.
     *
     * @deprecated As of ISO 19115:2014, replaced by
     * <code>{@linkplain #getFormatSpecificationCitation()}.{@linkplain Citation#getTitle() getTitle()}</code>.
     */
    @Deprecated
    @UML(identifier="specification", obligation=OPTIONAL, specification=ISO_19115, version=2003)
    default InternationalString getSpecification() {
        Citation spec = getFormatSpecificationCitation();
        return (spec != null) ? spec.getTitle() : null;
    }

    /**
     * Name of the data transfer format(s).
     *
     * @return name of the data transfer format(s).
     *
     * @deprecated As of ISO 19115:2014, replaced by
     * <code>{@linkplain #getFormatSpecificationCitation()}.{@linkplain Citation#getAlternateTitles() getAlternateTitles()}</code>.
     * Note that citation alternate titles are often used for abbreviations.
     */
    @Deprecated
    @Profile(level=CORE)
    @UML(identifier="name", obligation=MANDATORY, specification=ISO_19115, version=2003)
    default InternationalString getName() {
        Citation spec = getFormatSpecificationCitation();
        if (spec != null) {
            Iterator<? extends InternationalString> it = spec.getAlternateTitles().iterator();
            if (it.hasNext()) it.next();
        }
        return null;
    }

    /**
     * Version of the format (date, number, <i>etc</i>).
     *
     * @return version of the format.
     *
     * @deprecated As of ISO 19115:2014, replaced by
     * <code>{@linkplain #getFormatSpecificationCitation()}.{@linkplain Citation#getEdition() getEdition()}</code>.
     */
    @Deprecated
    @Profile(level=CORE)
    @UML(identifier="version", obligation=MANDATORY, specification=ISO_19115, version=2003)
    default InternationalString getVersion() {
        Citation spec = getFormatSpecificationCitation();
        return (spec != null) ? spec.getEdition() : null;
    }

    /**
     * Amendment number of the format version.
     *
     * @return amendment number of the format version, or {@code null}.
     */
    @UML(identifier="amendmentNumber", obligation=OPTIONAL, specification=ISO_19115)
    default InternationalString getAmendmentNumber() {
        return null;
    }

    /**
     * Recommendations of algorithms or processes that can be applied to read or
     * expand resources to which compression techniques have been applied.
     *
     * @return processes that can be applied to read resources to which compression techniques have been applied,
     *         or {@code null}.
     */
    @UML(identifier="fileDecompressionTechnique", obligation=OPTIONAL, specification=ISO_19115)
    default InternationalString getFileDecompressionTechnique() {
        return null;
    }

    /**
     * Media used by the format.
     *
     * @return media used by the format.
     *
     * @since 3.1
     */
    @UML(identifier="medium", obligation=OPTIONAL, specification=ISO_19115)
    default Collection<? extends Medium> getMedia() {
        return Collections.emptyList();
    }

    /**
     * Provides information about the distributor's format.
     *
     * @return information about the distributor's format.
     */
    @UML(identifier="formatDistributor", obligation=OPTIONAL, specification=ISO_19115)
    default Collection<? extends Distributor> getFormatDistributors() {
        return Collections.emptyList();
    }
}
