/*$************************************************************************************************
 **
 ** $Id$
 **
 ** $Source$
 **
 ** Copyright (C) 2003 Open GIS Consortium, Inc. All Rights Reserved. http://www.opengis.org/Legal/
 **
 *************************************************************************************************/
package org.opengis.metadata.content;

// OpenGIS direct dependencies
import org.opengis.metadata.Identifier;


/**
 * Information about an image’s suitability for use.
 *
 * @UML datatype MD_ImageDescription
 * @author ISO 19115
 * @author <A HREF="http://www.opengis.org">OpenGIS&reg; consortium</A>
 * @version <A HREF="http://www.opengis.org/docs/01-111.pdf">Abstract specification 5.0</A>
 */
public interface ImageDescription extends CoverageDescription {
    /**
     * Illumination elevation measured in degrees clockwise from the target plane at
     * intersection of the optical line of sight with the Earth’s surface. For images from a
     * scanning device, refer to the centre pixel of the image.
     *
     * @return A value between -90° and +90°, or <code>null</code> if unspecified.
     * @UML optional illuminationElevationAngle
     */
    Number getIlluminationElevationAngle();

    /**
     * Illumination azimuth measured in degrees clockwise from true north at the time the
     * image is taken. For images from a scanning device, refer to the centre pixel of the image.
     *
     * @return A value between 0° and 360°, or <code>null</code> if unspecified.
     * @UML optional illuminationAzimuthAngle
     */
    Number getIlluminationAzimuthAngle();

    /**
     * Conditions affected the image.
     *
     * @UML optional imagingCondition
     */
    ImagingCondition getImagingCondition();

    /**
     * Specifies the image quality.
     *
     * @UML optional imageQualityCode
     */
    Identifier getImageQualityCode();

    /**
     * Area of the dataset obscured by clouds, expressed as a percentage of the spatial extent.
     *
     * @return A value between 0 and 100, or <code>null</code> if unknow.
     * @UML optional cloudCoverPercentage
     */
    Number getCloudCoverPercentage();

    /**
     * Image distributor’s code that identifies the level of radiometric and geometric
     * processing that has been applied.
     *
     * @UML optional processingLevelCode
     */
    Identifier getProcessingLevelCode();

    /**
     * Count of the number the number of lossy compression cycles performed on the image.
     * Returns <code>null</code> if the information is not provided.
     *
     * @UML optional compressionGenerationQuantity
     */
    Integer getCompressionGenerationQuantity();

    /**
     * Indication of whether or not triangulation has been performed upon the image.
     * Returns <code>null</code> if the information is not provided.
     *
     * @UML optional triangulationIndicator
     */
    Boolean getTriangulationIndicator();

    /**
     * Indication of whether or not the radiometric calibration information for generating the
     * radiometrically calibrated standard data product is available.
     *
     * @UML optional radiometricCalibrationDataAvailability
     */
    boolean isRadiometricCalibrationDataAvailable();

    /**
     * Indication of whether or not constants are available which allow for camera calibration
     * corrections.
     *
     * @UML optional cameraCalibrationInformationAvailability
     */
    boolean isCameraCalibrationInformationAvailable();

    /**
     * Indication of whether or not Calibration Reseau information is available.
     *
     * @UML optional filmDistortionInformationAvailability
     */
    boolean isFilmDistortionInformationAvailable();

    /**
     * Indication of whether or not lens aberration correction information is available.
     *
     * @UML optional lensDistortionInformationAvailability
     */
    boolean isLensDistortionInformationAvailable();
}
