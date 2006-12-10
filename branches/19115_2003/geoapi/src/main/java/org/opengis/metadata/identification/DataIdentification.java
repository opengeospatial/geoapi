/*$************************************************************************************************
 **
 ** $Id$
 **
 ** $Source: /cvsroot/geoapi/src/org/opengis/metadata/identification/DataIdentification.java,v $
 **
 ** Copyright (C) 2004-2005 Open GIS Consortium, Inc.
 ** All Rights Reserved. http://www.opengis.org/legal/
 **
 *************************************************************************************************/
package org.opengis.metadata.identification;

// J2SE direct dependencies
import static org.opengis.annotation.ComplianceLevel.CORE;
import static org.opengis.annotation.Obligation.CONDITIONAL;
import static org.opengis.annotation.Obligation.MANDATORY;
import static org.opengis.annotation.Obligation.OPTIONAL;
import static org.opengis.annotation.Specification.ISO_19115;

import java.nio.charset.Charset;
import java.util.Collection;
import java.util.Locale;

import org.opengis.annotation.Profile;
import org.opengis.annotation.UML;
import org.opengis.metadata.extent.Extent;
import org.opengis.metadata.spatial.SpatialRepresentationType;
import org.opengis.util.InternationalString;


/**
 * Information required to identify a dataset.
 *
 * @version <A HREF="http://www.opengis.org/docs/01-111.pdf">Abstract specification 5.0</A>
 * @author Martin Desruisseaux (IRD)
 * @since GeoAPI 2.0
 */
@Profile (level=CORE)
@UML(identifier="MD_DataIdentification", specification=ISO_19115)
public interface DataIdentification extends Identification {
    /**
     * Method used to spatially represent geographic information.
     */
    @Profile (level=CORE)
    @UML(identifier="spatialRepresentationType", obligation=OPTIONAL, specification=ISO_19115)
    Collection<SpatialRepresentationType> getSpatialRepresentationTypes();

    /**
     * Factor which provides a general understanding of the density of spatial data
     * in the dataset.
     */
    @Profile (level=CORE)
    @UML(identifier="spatialResolution", obligation=OPTIONAL, specification=ISO_19115)
    Collection<Resolution> getSpatialResolutions();

    /**
     * Language(s) used within the dataset.
     */
    @Profile (level=CORE)
    @UML(identifier="language", obligation=MANDATORY, specification=ISO_19115)
    Collection<Locale> getLanguage();

    /**
     * Full name of the character coding standard used for the dataset.
     */
    @Profile (level=CORE)
    @UML(identifier="characterSet", obligation=CONDITIONAL, specification=ISO_19115)
    Collection<Charset> getCharacterSets();

    /**
     * Main theme(s) of the datset.
     */
    @Profile (level=CORE)
    @UML(identifier="topicCategory", obligation=MANDATORY, specification=ISO_19115)
    Collection<TopicCategory> getTopicCategories();

    /**
     * Minimum bounding rectangle within which data is available.
     * Only one of {@code getGeographicBox()} and {@link #getGeographicDescription()}
     * should be provided.
     */
    /* GR: non existent in ISO19115:2003, moved to EX_Extent::geographicElement
    @Profile (level=CORE)
    @UML(identifier="geographicBox", obligation=CONDITIONAL, specification=ISO_19115)
    Collection<GeographicBoundingBox> getGeographicBox();
    */

    /**
     * Description of the geographic area within which data is available.
     * Only one of {@link #getGeographicBox()} and {@code getGeographicDescription()}
     * should be provided.
     */
    /* GR: non existent in ISO19115:2003, moved to EX_Extent::geographicElement
    @Profile (level=CORE)
    @UML(identifier="geographicDescription", obligation=CONDITIONAL, specification=ISO_19115)
    Collection<GeographicDescription> getGeographicDescription();
    */

    /**
     * Description of the dataset in the producer’s processing environment, including items
     * such as the software, the computer operating system, file name, and the dataset size.
     */
    @UML(identifier="environmentDescription", obligation=OPTIONAL, specification=ISO_19115)
    InternationalString getEnvironmentDescription();

    /**
     * Additional extent information including the bounding polygon, vertical, and temporal
     * extent of the dataset.
     */
    @Profile (level=CORE)
    @UML(identifier="extent", obligation=OPTIONAL, specification=ISO_19115)
    Collection<Extent> getExtent();

    /**
     * Any other descriptive information about the dataset.
     */
    @UML(identifier="supplementalInformation", obligation=OPTIONAL, specification=ISO_19115)
    InternationalString getSupplementalInformation();
}
