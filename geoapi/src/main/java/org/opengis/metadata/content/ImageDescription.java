/*$************************************************************************************************
 **
 ** $Id$
 **
 ** $URL$
 **
 ** Copyright (C) 2004-2005 Open GIS Consortium, Inc.
 ** All Rights Reserved. http://www.opengis.org/legal/
 **
 *************************************************************************************************/
package org.opengis.metadata.content;

// OpenGIS direct dependencies
import org.opengis.metadata.Identifier;

// Annotations
import org.opengis.annotation.UML;
import static org.opengis.annotation.Obligation.*;
import static org.opengis.annotation.Specification.*;


/**
 * Information about an image’s suitability for use.
 *
 * @version <A HREF="http://www.opengis.org/docs/01-111.pdf">Abstract specification 5.0</A>
 * @author Martin Desruisseaux (IRD)
 * @since GeoAPI 2.0
 */
@UML(identifier="MD_ImageDescription", specification=ISO_19115)
public interface ImageDescription extends CoverageDescription {
    /**
     * Illumination elevation measured in degrees clockwise from the target plane at
     * intersection of the optical line of sight with the Earth’s surface. For images from a
     * scanning device, refer to the centre pixel of the image.
     *
     * @return A value between -90° and +90°, or {@code null} if unspecified.
     */
    @UML(identifier="illuminationElevationAngle", obligation=OPTIONAL, specification=ISO_19115)
    Number getIlluminationElevationAngle();

    /**
     * Illumination azimuth measured in degrees clockwise from true north at the time the
     * image is taken. For images from a scanning device, refer to the centre pixel of the image.
     *
     * @return A value between 0° and 360°, or {@code null} if unspecified.
     */
    @UML(identifier="illuminationAzimuthAngle", obligation=OPTIONAL, specification=ISO_19115)
    Number getIlluminationAzimuthAngle();

    /**
     * Conditions affected the image.
     */
    @UML(identifier="imagingCondition", obligation=OPTIONAL, specification=ISO_19115)
    ImagingCondition getImagingCondition();

    /**
     * Specifies the image quality.
     */
    @UML(identifier="imageQualityCode", obligation=OPTIONAL, specification=ISO_19115)
    Identifier getImageQualityCode();

    /**
     * Area of the dataset obscured by clouds, expressed as a percentage of the spatial extent.
     *
     * @return A value between 0 and 100, or {@code null} if unknow.
     */
    @UML(identifier="cloudCoverPercentage", obligation=OPTIONAL, specification=ISO_19115)
    Number getCloudCoverPercentage();

    /**
     * Image distributor’s code that identifies the level of radiometric and geometric
     * processing that has been applied.
     */
    @UML(identifier="processingLevelCode", obligation=OPTIONAL, specification=ISO_19115)
    Identifier getProcessingLevelCode();

    /**
     * Count of the number the number of lossy compression cycles performed on the image.
     * Returns {@code null} if the information is not provided.
     */
    @UML(identifier="compressionGenerationQuantity", obligation=OPTIONAL, specification=ISO_19115)
    Integer getCompressionGenerationQuantity();

    /**
     * Indication of whether or not triangulation has been performed upon the image.
     * Returns {@code null} if the information is not provided.
     */
    @UML(identifier="triangulationIndicator", obligation=OPTIONAL, specification=ISO_19115)
    Boolean getTriangulationIndicator();

    /**
     * Indication of whether or not the radiometric calibration information for generating the
     * radiometrically calibrated standard data product is available.
     */
    @UML(identifier="radiometricCalibrationDataAvailability", obligation=OPTIONAL, specification=ISO_19115)
    boolean isRadiometricCalibrationDataAvailable();

    /**
     * Indication of whether or not constants are available which allow for camera calibration
     * corrections.
     */
    @UML(identifier="cameraCalibrationInformationAvailability", obligation=OPTIONAL, specification=ISO_19115)
    boolean isCameraCalibrationInformationAvailable();

    /**
     * Indication of whether or not Calibration Reseau information is available.
     */
    @UML(identifier="filmDistortionInformationAvailability", obligation=OPTIONAL, specification=ISO_19115)
    boolean isFilmDistortionInformationAvailable();

    /**
     * Indication of whether or not lens aberration correction information is available.
     */
    @UML(identifier="lensDistortionInformationAvailability", obligation=OPTIONAL, specification=ISO_19115)
    boolean isLensDistortionInformationAvailable();
}
