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
package org.opengis.metadata.extent;

import java.util.Collection;
import org.opengis.util.InternationalString;
import org.opengis.annotation.UML;
import org.opengis.annotation.Profile;
import org.opengis.annotation.Classifier;
import org.opengis.annotation.Stereotype;

import static org.opengis.annotation.Obligation.*;
import static org.opengis.annotation.Specification.*;
import static org.opengis.annotation.ComplianceLevel.*;


/**
 * Information about spatial, vertical, and temporal extent of the resource.
 * This interface has four optional attributes:
 * {@linkplain #getGeographicElements() geographic elements},
 * {@linkplain #getVerticalElements() vertical elements},
 * {@linkplain #getTemporalElements() temporal elements} and an element called
 * {@linkplain #getDescription() description}.
 * At least one of the four shall be used.
 *
 * @author  Martin Desruisseaux (IRD)
 * @version 3.1
 * @since   1.0
 */
@Classifier(Stereotype.DATATYPE)
@UML(identifier="EX_Extent", specification=ISO_19115)
public interface Extent {
    /**
     * The spatial and temporal extent for the referring object.
     *
     * @return the spatial and temporal extent, or {@code null} in none.
     *
     * @condition Mandatory if {@linkplain #getGeographicElements() geographic element},
     *            {@linkplain #getVerticalElements() vertical element} and
     *            {@linkplain #getTemporalElements() temporal element} are not provided.
     */
    @UML(identifier="description", obligation=CONDITIONAL, specification=ISO_19115)
    InternationalString getDescription();

    /**
     * Provides geographic component of the extent of the referring object.
     *
     * @return the geographic extent, or an empty set if none.
     *
     * @condition Mandatory if {@linkplain #getDescription() description},
     *            {@linkplain #getVerticalElements() vertical element} and
     *            {@linkplain #getTemporalElements() temporal element} are not provided.
     */
    @Profile(level=CORE)
    @UML(identifier="geographicElement", obligation=CONDITIONAL, specification=ISO_19115)
    Collection<? extends GeographicExtent> getGeographicElements();

    /**
     * Provides vertical component of the extent of the referring object.
     *
     * @return the vertical extent, or an empty set if none.
     *
     * @condition Mandatory if {@linkplain #getDescription() description},
     *            {@linkplain #getGeographicElements() geographic element} and
     *            {@linkplain #getTemporalElements() temporal element} are not provided.
     */
    @Profile(level=CORE)
    @UML(identifier="verticalElement", obligation=CONDITIONAL, specification=ISO_19115)
    Collection<? extends VerticalExtent> getVerticalElements();

    /**
     * Provides temporal component of the extent of the referring object.
     *
     * @return the temporal extent, or an empty set if none.
     *
     * @condition Mandatory if {@linkplain #getDescription() description},
     *            {@linkplain #getGeographicElements() geographic element} and
     *            {@linkplain #getVerticalElements() vertical element} are not provided.
     */
    @Profile(level=CORE)
    @UML(identifier="temporalElement", obligation=CONDITIONAL, specification=ISO_19115)
    Collection<? extends TemporalExtent> getTemporalElements();
}
