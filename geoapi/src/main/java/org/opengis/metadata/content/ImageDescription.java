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
package org.opengis.metadata.content;

import org.opengis.metadata.Identifier;
import org.opengis.annotation.UML;

import static org.opengis.annotation.Obligation.*;
import static org.opengis.annotation.Specification.*;


/**
 * Information about an image's suitability for use.
 *
 * @author  Martin Desruisseaux (IRD)
 * @author  Cory Horner (Refractions Research)
 * @author  Rémi Maréchal (Geomatys)
 * @version 3.1
 * @since   2.0
 */
@UML(identifier="MD_ImageDescription", specification=ISO_19115)
public interface ImageDescription extends CoverageDescription {
    /**
     * Illumination elevation measured in degrees clockwise from the target plane at
     * intersection of the optical line of sight with the Earth's surface. For images from a
     * scanning device, refer to the centre pixel of the image.
     *
     * @return a value between -90° and +90°, or {@code null} if unspecified.
     */
    @UML(identifier="illuminationElevationAngle", obligation=OPTIONAL, specification=ISO_19115)
    Double getIlluminationElevationAngle();

    /**
     * Illumination azimuth measured in degrees clockwise from true north at the time the
     * image is taken. For images from a scanning device, refer to the centre pixel of the image.
     *
     * @return a value between 0° and 360°, or {@code null} if unspecified.
     */
    @UML(identifier="illuminationAzimuthAngle", obligation=OPTIONAL, specification=ISO_19115)
    Double getIlluminationAzimuthAngle();

    /**
     * Conditions which affected the image.
     *
     * @return conditions which affected the image, or {@code null} if unspecified.
     */
    @UML(identifier="imagingCondition", obligation=OPTIONAL, specification=ISO_19115)
    ImagingCondition getImagingCondition();

    /**
     * Code in producer’s codespace that specifies the image quality.
     *
     * @return the image quality, or {@code null} if unspecified.
     */
    @UML(identifier="imageQualityCode", obligation=OPTIONAL, specification=ISO_19115)
    Identifier getImageQualityCode();

    /**
     * Area of the dataset obscured by clouds, expressed as a percentage of the spatial extent.
     *
     * @return a value between 0 and 100, or {@code null} if unspecified.
     */
    @UML(identifier="cloudCoverPercentage", obligation=OPTIONAL, specification=ISO_19115)
    Double getCloudCoverPercentage();

    /**
     * Count of the number of lossy compression cycles performed on the image.
     * Returns {@code null} if the information is not provided.
     *
     * @return the number of lossy compression cycles performed on the image,
     *         or {@code null} if unspecified.
     */
    @UML(identifier="compressionGenerationQuantity", obligation=OPTIONAL, specification=ISO_19115)
    Integer getCompressionGenerationQuantity();

    /**
     * Indication of whether or not triangulation has been performed upon the image.
     * Returns {@code null} if the information is not provided.
     *
     * @return whether or not triangulation has been performed upon the image,
     *         or {@code null} if unspecified.
     */
    @UML(identifier="triangulationIndicator", obligation=OPTIONAL, specification=ISO_19115)
    Boolean getTriangulationIndicator();

    /**
     * Indication of whether or not the radiometric calibration information for generating the
     * radiometrically calibrated standard data product is available.
     *
     * @return whether or not the radiometric calibration information is available,
     *         or {@code null} if unspecified.
     */
    @UML(identifier="radiometricCalibrationDataAvailability", obligation=OPTIONAL, specification=ISO_19115)
    Boolean isRadiometricCalibrationDataAvailable();

    /**
     * Indication of whether or not constants are available which allow for camera calibration corrections.
     *
     * @return whether or not constants are available for camera calibration corrections,
     *         or {@code null} if unspecified.
     */
    @UML(identifier="cameraCalibrationInformationAvailability", obligation=OPTIONAL, specification=ISO_19115)
    Boolean isCameraCalibrationInformationAvailable();

    /**
     * Indication of whether or not Calibration Reseau information is available.
     *
     * @return whether or not Calibration Reseau information is available,
     *         or {@code null} if unspecified.
     */
    @UML(identifier="filmDistortionInformationAvailability", obligation=OPTIONAL, specification=ISO_19115)
    Boolean isFilmDistortionInformationAvailable();

    /**
     * Indication of whether or not lens aberration correction information is available.
     *
     * @return whether or not lens aberration correction information is available,
     *         or {@code null} if unspecified.
     */
    @UML(identifier="lensDistortionInformationAvailability", obligation=OPTIONAL, specification=ISO_19115)
    Boolean isLensDistortionInformationAvailable();
}
