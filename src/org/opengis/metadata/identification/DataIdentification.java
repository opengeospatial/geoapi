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


/**
 * Information required to identify a dataset.
 *
 * @UML datatype MD_DateIdentification
 * @author ISO 19115
 * @author <A HREF="http://www.opengis.org">OpenGIS&reg; consortium</A>
 * @version <A HREF="http://www.opengis.org/docs/01-111.pdf">Abstract specification 5.0</A>
 */
public interface DataIdentification extends Identification {
    /**
     * Method used to spatially represent geographic information.
     *
     * @UML optional spatialRepresentationType
     */
    Set/*<SpatialRepresentationType>*/ getSpatialRepresentationTypes();

    /**
     * Factor which provides a general understanding of the density of spatial data
     * in the dataset.
     *
     * @UML optional spatialResolution
     */
    Set/*<Resolution>*/ getSpatialResolutions();

    /**
     * Language(s) used within the dataset.
     *
     * @UML mandatory language
     */
    Set/*<Locale>*/ getLanguage();

    /**
     * Full name of the character coding standard used for the dataset.
     *
     * @UML conditional characterSet
     *
     * @revisit We should use {@link java.nio.charset.Charset} if J2SE 1.4 is allowed.
     */
    String getCharacterSet();

    /**
     * Main theme(s) of the datset.
     *
     * @UML mandatory topicCategory
     */
    Set/*<TopicCategory>*/ getTopicCategories();

    /**
     * Minimum bounding rectangle within which data is available.
     * Only one of <code>getGeographicBox()</code> and {@link #getGeographicDescription()}
     * should be provided.
     *
     * @UML conditional geographicBox
     */
    Set/*<GeographicBoundingBox>*/ getGeographicBox();

    /**
     * Description of the geographic area within which data is available.
     * Only one of {@link #getGeographicBox()} and <code>getGeographicDescription()</code>
     * should be provided.
     *
     * @UML conditional geographicDescription
     */
    Set/*<GeographicDescription>*/ getGeographicDescription();

    /**
     * Description of the dataset in the producer’s processing environment, including items
     * such as the software, the computer operating system, file name, and the dataset size
     *
     * @UML optional environmentDescription
     */
    InternationalString getEnvironmentDescription();

    /**
     * Additional extent information including the bounding polygon, vertical, and temporal
     * extent of the dataset.
     *
     * @UML optional extent
     */
    Set/*<Extent>*/ getExtent();

    /**
     * Any other descriptive information about the dataset.
     *
     * @UML optional supplementalInformation
     */
    InternationalString getSupplementalInformation();
}
