/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    http://www.geoapi.org
 *
 *    Copyright (C) 2004-2021 Open Geospatial Consortium, Inc.
 *    All Rights Reserved. http://www.opengeospatial.org/ogc/legal
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
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
    default Double getIlluminationElevationAngle() {
        return null;
    }

    /**
     * Illumination azimuth measured in degrees clockwise from true north at the time the
     * image is taken. For images from a scanning device, refer to the centre pixel of the image.
     *
     * @return a value between 0° and 360°, or {@code null} if unspecified.
     */
    @UML(identifier="illuminationAzimuthAngle", obligation=OPTIONAL, specification=ISO_19115)
    default Double getIlluminationAzimuthAngle() {
        return null;
    }

    /**
     * Conditions which affected the image.
     *
     * @return conditions which affected the image, or {@code null} if unspecified.
     */
    @UML(identifier="imagingCondition", obligation=OPTIONAL, specification=ISO_19115)
    default ImagingCondition getImagingCondition() {
        return null;
    }

    /**
     * Code in producer’s codespace that specifies the image quality.
     *
     * @return the image quality, or {@code null} if unspecified.
     */
    @UML(identifier="imageQualityCode", obligation=OPTIONAL, specification=ISO_19115)
    default Identifier getImageQualityCode() {
        return null;
    }

    /**
     * Area of the dataset obscured by clouds, expressed as a percentage of the spatial extent.
     *
     * @return a value between 0 and 100, or {@code null} if unspecified.
     */
    @UML(identifier="cloudCoverPercentage", obligation=OPTIONAL, specification=ISO_19115)
    default Double getCloudCoverPercentage() {
        return null;
    }

    /**
     * Count of the number of lossy compression cycles performed on the image.
     * Returns {@code null} if the information is not provided.
     *
     * @return the number of lossy compression cycles performed on the image,
     *         or {@code null} if unspecified.
     */
    @UML(identifier="compressionGenerationQuantity", obligation=OPTIONAL, specification=ISO_19115)
    default Integer getCompressionGenerationQuantity() {
        return null;
    }

    /**
     * Indication of whether or not triangulation has been performed upon the image.
     * Returns {@code null} if the information is not provided.
     *
     * @return whether or not triangulation has been performed upon the image,
     *         or {@code null} if unspecified.
     */
    @UML(identifier="triangulationIndicator", obligation=OPTIONAL, specification=ISO_19115)
    default Boolean getTriangulationIndicator() {
        return null;
    }

    /**
     * Indication of whether or not the radiometric calibration information for generating the
     * radiometrically calibrated standard data product is available.
     *
     * @return whether or not the radiometric calibration information is available,
     *         or {@code null} if unspecified.
     */
    @UML(identifier="radiometricCalibrationDataAvailability", obligation=OPTIONAL, specification=ISO_19115)
    default Boolean isRadiometricCalibrationDataAvailable() {
        return null;
    }

    /**
     * Indication of whether or not constants are available which allow for camera calibration corrections.
     *
     * @return whether or not constants are available for camera calibration corrections,
     *         or {@code null} if unspecified.
     */
    @UML(identifier="cameraCalibrationInformationAvailability", obligation=OPTIONAL, specification=ISO_19115)
    default Boolean isCameraCalibrationInformationAvailable() {
        return null;
    }

    /**
     * Indication of whether or not Calibration Reseau information is available.
     *
     * @return whether or not Calibration Reseau information is available,
     *         or {@code null} if unspecified.
     */
    @UML(identifier="filmDistortionInformationAvailability", obligation=OPTIONAL, specification=ISO_19115)
    default Boolean isFilmDistortionInformationAvailable() {
        return null;
    }

    /**
     * Indication of whether or not lens aberration correction information is available.
     *
     * @return whether or not lens aberration correction information is available,
     *         or {@code null} if unspecified.
     */
    @UML(identifier="lensDistortionInformationAvailability", obligation=OPTIONAL, specification=ISO_19115)
    default Boolean isLensDistortionInformationAvailable() {
        return null;
    }
}
