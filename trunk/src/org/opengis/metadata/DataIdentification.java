/*$************************************************************************************************
 **
 ** $Id$
 **
 ** $Source$
 **
 ** Copyright (C) 2003 Open GIS Consortium, Inc. All Rights Reserved. http://www.opengis.org/Legal/
 **
 *************************************************************************************************/
package org.opengis.metadata;

// J2SE direct dependencies
import java.util.Locale;

// OpenGIS direct dependencies
import org.opengis.metadata.extent.Extent;
import org.opengis.metadata.extent.GeographicBoundingBox;
import org.opengis.metadata.extent.GeographicDescription;


/**
 * Information required to identify a dataset
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
//    SpatialRepresentation[] getSpatialRepresentations();

    /**
     * Factor which provides a general understanding of the density of spatial data
     * in the dataset.
     *
     * @UML optional spatialResolution
     */
//    Resolution[] getSpatialResolutions();

    /**
     * Language(s) used within the dataset.
     *
     * @UML mandatory language
     */
    Locale[] getLanguage();

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
//    TopicCategory[] getTopicCategory();

    /**
     * Minimum bounding rectangle within which data is available.
     * Only one of <code>getGeographicBox()</code> and {@link #getGeographicDescription()}
     * should be provided.
     *
     * @UML conditional geographicBox
     */
    GeographicBoundingBox[] getGeographicBox();

    /**
     * Description of the geographic area within which data is available.
     * Only one of {@link #getGeographicBox()} and <code>getGeographicDescription()</code>
     * should be provided.
     *
     * @UML conditional geographicDescription
     */
    GeographicDescription[] getGeographicDescription();

    /**
     * Description of the dataset in the producer’s processing environment, including items
     * such as the software, the computer operating system, file name, and the dataset size
     *
     * @param  locale The desired locale for the description to be returned, or <code>null</code>
     *         for a description in some default locale (may or may not be the
     *         {@linkplain Locale#getDefault() system default}).
     * @return The description in the given locale.
     *         If no description is available in the given locale, then some default locale is used.
     * @UML optional environmentDescription
     */
    String getEnvironmentDescription(Locale locale);

    /**
     * Additional extent information including the bounding polygon, vertical, and temporal
     * extent of the dataset.
     *
     * @UML optional extent
     */
    Extent[] getExtent();

    /**
     * Any other descriptive information about the dataset.
     *
     * @param  locale The desired locale for the description to be returned, or <code>null</code>
     *         for a description in some default locale (may or may not be the
     *         {@linkplain Locale#getDefault() system default}).
     * @return The description in the given locale.
     *         If no description is available in the given locale, then some default locale is used.
     * @UML optional supplementalInformation
     */
    String getSupplementalInformation(Locale locale);
}
