/*$************************************************************************************************
 **
 ** $Id$
 **
 ** $Source$
 **
 ** Copyright (C) 2003 Open GIS Consortium, Inc. All Rights Reserved. http://www.opengis.org/Legal/
 **
 *************************************************************************************************/
package org.opengis.metadata.identification;

// J2SE direct dependencies
import java.util.Set;
import java.util.Locale;

// OpenGIS direct dependencies
import org.opengis.metadata.extent.Extent;
import org.opengis.metadata.extent.GeographicBoundingBox;
import org.opengis.metadata.extent.GeographicDescription;
import org.opengis.metadata.spatial.SpatialRepresentationType;
import org.opengis.util.InternationalString;

// Annotations
///import org.opengis.annotation.UML;
///import static org.opengis.annotation.Obligation.*;


/**
 * Information required to identify a dataset.
 *
 * @author ISO 19115
 * @author <A HREF="http://www.opengis.org">OpenGIS&reg; consortium</A>
 * @version <A HREF="http://www.opengis.org/docs/01-111.pdf">Abstract specification 5.0</A>
 */
///@UML (identifier="MD_DataIdentification")
public interface DataIdentification extends Identification {
    /**
     * Method used to spatially represent geographic information.
     */
/// @UML (identifier="spatialRepresentationType", obligation=OPTIONAL)
    Set/*<SpatialRepresentationType>*/ getSpatialRepresentationTypes();

    /**
     * Factor which provides a general understanding of the density of spatial data
     * in the dataset.
     */
/// @UML (identifier="spatialResolution", obligation=OPTIONAL)
    Set/*<Resolution>*/ getSpatialResolutions();

    /**
     * Language(s) used within the dataset.
     */
/// @UML (identifier="language", obligation=MANDATORY)
    Set/*<Locale>*/ getLanguage();

    /**
     * Full name of the character coding standard used for the dataset.
     *
     * @revisit We should use {@link java.nio.charset.Charset} if J2SE 1.4 is allowed.
     */
/// @UML (identifier="characterSet", obligation=CONDITIONAL)
    String getCharacterSet();

    /**
     * Main theme(s) of the datset.
     */
/// @UML (identifier="topicCategory", obligation=MANDATORY)
    Set/*<TopicCategory>*/ getTopicCategories();

    /**
     * Minimum bounding rectangle within which data is available.
     * Only one of <code>getGeographicBox()</code> and {@link #getGeographicDescription()}
     * should be provided.
     */
/// @UML (identifier="geographicBox", obligation=CONDITIONAL)
    Set/*<GeographicBoundingBox>*/ getGeographicBox();

    /**
     * Description of the geographic area within which data is available.
     * Only one of {@link #getGeographicBox()} and <code>getGeographicDescription()</code>
     * should be provided.
     */
/// @UML (identifier="geographicDescription", obligation=CONDITIONAL)
    Set/*<GeographicDescription>*/ getGeographicDescription();

    /**
     * Description of the dataset in the producer’s processing environment, including items
     * such as the software, the computer operating system, file name, and the dataset size.
     */
/// @UML (identifier="environmentDescription", obligation=OPTIONAL)
    InternationalString getEnvironmentDescription();

    /**
     * Additional extent information including the bounding polygon, vertical, and temporal
     * extent of the dataset.
     */
/// @UML (identifier="extent", obligation=OPTIONAL)
    Set/*<Extent>*/ getExtent();

    /**
     * Any other descriptive information about the dataset.
     */
/// @UML (identifier="supplementalInformation", obligation=OPTIONAL)
    InternationalString getSupplementalInformation();
}
