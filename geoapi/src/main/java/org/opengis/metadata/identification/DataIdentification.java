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
package org.opengis.metadata.identification;

import java.util.Collection;
import java.util.Locale;
import org.opengis.metadata.Metadata;
import org.opengis.metadata.extent.Extent;
import org.opengis.metadata.maintenance.ScopeCode;
import org.opengis.metadata.spatial.SpatialRepresentationType;
import org.opengis.util.InternationalString;
import org.opengis.annotation.UML;
import org.opengis.annotation.Profile;

import static org.opengis.annotation.Obligation.*;
import static org.opengis.annotation.Specification.*;
import static org.opengis.annotation.ComplianceLevel.*;


/**
 * Information required to identify a dataset.
 *
 * @author  Martin Desruisseaux (IRD)
 * @version 3.0
 * @since   2.0
 *
 * @navassoc - - - SpatialRepresentationType
 * @navassoc - - - Resolution
 * @navassoc - - - CharacterSet
 * @navassoc - - - TopicCategory
 * @navassoc - - - Extent
 */
@UML(identifier="MD_DataIdentification", specification=ISO_19115)
public interface DataIdentification extends Identification {
    /**
     * Method used to spatially represent geographic information.
     *
     * @return Method(s) used to spatially represent geographic information.
     */
    @Profile(level=CORE)
    @UML(identifier="spatialRepresentationType", obligation=OPTIONAL, specification=ISO_19115)
    Collection<SpatialRepresentationType> getSpatialRepresentationTypes();

    /**
     * Factor which provides a general understanding of the density of spatial data
     * in the dataset.
     *
     * @return Factor which provides a general understanding of the density of spatial data.
     */
    @Profile(level=CORE)
    @UML(identifier="spatialResolution", obligation=OPTIONAL, specification=ISO_19115)
    Collection<? extends Resolution> getSpatialResolutions();

    /**
     * Language(s) used within the dataset.
     *
     * @return Language(s) used.
     */
    @Profile(level=CORE)
    @UML(identifier="language", obligation=MANDATORY, specification=ISO_19115)
    Collection<Locale> getLanguages();

    /**
     * Full name of the character coding standard(s) used for the dataset.
     *
     * @return Name(s) of the character coding standard(s) used.
     *
     * @condition ISO/IEC 10646-1 not used.
     */
    @Profile(level=CORE)
    @UML(identifier="characterSet", obligation=CONDITIONAL, specification=ISO_19115)
    Collection<CharacterSet> getCharacterSets();

    /**
     * Main theme(s) of the dataset.
     *
     * @return Main theme(s).
     *
     * @condition If {@linkplain Metadata#getHierarchyLevels() hierarchy level} equals
     *            {@link ScopeCode#DATASET}.
     */
    @Profile(level=CORE)
    @UML(identifier="topicCategory", obligation=CONDITIONAL, specification=ISO_19115)
    Collection<TopicCategory> getTopicCategories();

    /**
     * Description of the dataset in the producer's processing environment, including items
     * such as the software, the computer operating system, file name, and the dataset size.
     *
     * @return Description of the dataset in the producer's processing environment, or {@code null}.
     */
    @UML(identifier="environmentDescription", obligation=OPTIONAL, specification=ISO_19115)
    InternationalString getEnvironmentDescription();

    /**
     * Additional extent information including the bounding polygon, vertical, and temporal
     * extent of the dataset.
     *
     * @return Additional extent information.
     *
     * @condition If hierarchyLevel equals dataset? either extent.geographicElement.EX_GeographicBoundingBox
     *            or extent.geographicElement.EX_GeographicDescription is required.
     */
    @Profile(level=CORE)
    @UML(identifier="extent", obligation=CONDITIONAL, specification=ISO_19115)
    Collection<? extends Extent> getExtents();

    /**
     * Any other descriptive information about the dataset.
     *
     * @return Other descriptive information, or {@code null}.
     */
    @UML(identifier="supplementalInformation", obligation=OPTIONAL, specification=ISO_19115)
    InternationalString getSupplementalInformation();
}
