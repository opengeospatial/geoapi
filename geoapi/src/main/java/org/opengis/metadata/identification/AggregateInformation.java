/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    http://www.geoapi.org
 *
 *    Copyright (C) 2007-2021 Open Geospatial Consortium, Inc.
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
package org.opengis.metadata.identification;

import org.opengis.metadata.Identifier;
import org.opengis.metadata.citation.Citation;
import org.opengis.annotation.UML;

import static org.opengis.annotation.Obligation.*;
import static org.opengis.annotation.Specification.ISO_19115;


/**
 * Aggregate dataset information.
 *
 * @author  Ely Conn (Leica Geosystems Geospatial Imaging, LLC)
 * @version 3.0
 * @since   2.1
 *
 * @deprecated As of ISO 19115:2014, replaced by {@link AssociatedResource}.
 */
@Deprecated
@UML(identifier="MD_AggregateInformation", specification=ISO_19115, version=2003)
public interface AggregateInformation extends AssociatedResource {
    /**
     * Citation information about the aggregate dataset.
     *
     * @return citation information about the aggregate dataset, or {@code null}.
     *
     * @condition {@linkplain #getAggregateDataSetIdentifier() Aggregate data set identifier} not provided.
     *
     * @deprecated As of ISO 19115:2014, replaced by {@link #getName()}.
     */
    @Deprecated
    @UML(identifier="aggregateDataSetName", obligation=CONDITIONAL, specification=ISO_19115, version=2003)
    Citation getAggregateDataSetName();

    /**
     * Identification information about aggregate dataset.
     *
     * @return identification information about aggregate dataset, or {@code null}.
     *
     * @condition {@linkplain #getAggregateDataSetName() Aggregate data set name} not provided.
     *
     * @deprecated As of ISO 19115:2014, replaced by an identifier of {@link #getAggregateDataSetName()}.
     */
    @Deprecated
    @UML(identifier="aggregateDataSetIdentifier", obligation=CONDITIONAL, specification=ISO_19115, version=2003)
    Identifier getAggregateDataSetIdentifier();
}
