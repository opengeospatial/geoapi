/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    Copyright © 2004-2011 Open Geospatial Consortium, Inc.
 *    http://www.geoapi.org
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

import javax.measure.quantity.Length;
import javax.measure.Unit;
import org.opengis.annotation.UML;

import static org.opengis.annotation.Obligation.*;
import static org.opengis.annotation.Specification.*;


/**
 * Range of wavelengths in the electromagnetic spectrum.
 *
 * @author  Martin Desruisseaux (IRD)
 * @author  Cory Horner (Refractions Research)
 * @author  Cédric Briançon (Geomatys)
 * @version 3.0.1
 * @since   2.0
 *
 * @navassoc 1 - - Unit
 * @navassoc 1 - - BandDefinition
 * @navassoc 1 - - TransferFunctionType
 * @navassoc - - - PolarizationOrientation
 */
@UML(identifier="MD_Band", specification=ISO_19115)
public interface Band extends RangeDimension {
    /**
     * Longest wavelength that the sensor is capable of collecting within a designated band.
     * Returns {@code null} if unspecified.
     *
     * @return Longest wavelength that the sensor is capable of collecting within a designated band,
     *         or {@code null} if unknown. The units of measurement is given by {@link #getUnits()}.
     */
    @UML(identifier="maxValue", obligation=OPTIONAL, specification=ISO_19115)
    Double getMaxValue();

    /**
     * Shortest wavelength that the sensor is capable of collecting within a designated band.
     * Returns {@code null} if unspecified.
     *
     * @return Shortest wavelength that the sensor is capable of collecting within a designated band,
     *         or {@code null} if unknown. The units of measurement is given by {@link #getUnits()}.
     */
    @UML(identifier="minValue", obligation=OPTIONAL, specification=ISO_19115)
    Double getMinValue();

    /**
     * Wavelength at which the response is the highest.
     * Returns {@code null} if unspecified.
     *
     * @return Wavelength at which the response is the highest, or {@code null} if unknown.
     *         The units of measurement is given by {@link #getUnits()}.
     */
    @UML(identifier="peakResponse", obligation=OPTIONAL, specification=ISO_19115)
    Double getPeakResponse();

    /**
     * Units in which sensor wavelengths are expressed.
     *
     * @return Units in which sensor wavelengths are expressed, or {@code null}.
     *
     * @condition {@linkplain #getMinValue min value}, {@linkplain #getMaxValue max value}
     *            or {@linkplain #getPeakResponse() peak response} is provided.
     */
    @UML(identifier="units", obligation=CONDITIONAL, specification=ISO_19115)
    Unit<Length> getUnits();

    /**
     * Maximum number of significant bits in the uncompressed representation for the value
     * in each band of each pixel.
     * Returns {@code null} if unspecified.
     *
     * @return Maximum number of significant bits in the uncompressed representation, or {@code null}.
     */
    @UML(identifier="bitsPerValue", obligation=OPTIONAL, specification=ISO_19115)
    Integer getBitsPerValue();

    /**
     * Number of discrete numerical values in the grid data.
     * Returns {@code null} if unspecified.
     *
     * @return Number of discrete numerical values in the grid data, or {@code null}.
     */
    @UML(identifier="toneGradation", obligation=OPTIONAL, specification=ISO_19115)
    Integer getToneGradation();

    /**
     * Scale factor which has been applied to the cell value.
     * Returns {@code null} if unspecified.
     *
     * @return Scale factor which has been applied to the cell value, or {@code null}.
     */
    @UML(identifier="scaleFactor", obligation=OPTIONAL, specification=ISO_19115)
    Double getScaleFactor();

    /**
     * The physical value corresponding to a cell value of zero.
     * Returns {@code null} if unspecified.
     *
     * @return The physical value corresponding to a cell value of zero, or {@code null}.
     */
    @UML(identifier="offset", obligation=OPTIONAL, specification=ISO_19115)
    Double getOffset();

    /**
     * Designation of criterion for defining maximum and minimum wavelengths for a spectral band.
     *
     * @return Criterion for defining maximum and minimum wavelengths.
     *
     * @since 2.3
     */
    @UML(identifier="bandBoundaryDefinition", obligation=OPTIONAL, specification=ISO_19115_2)
    BandDefinition getBandBoundaryDefinition();

    /**
     * Smallest distance between which separate points can be distinguished, as specified in
     * instrument design.
     * <p>
     * <TABLE WIDTH="80%" ALIGN="center" CELLPADDING="18" BORDER="4" BGCOLOR="#FFE0B0">
     *   <TR><TD>
     *     <P align="justify"><B>Warning:</B> The return type of this method may change in GeoAPI
     *     3.1. It may be replaced by the {@link javax.measure.quantity.Length} type in order to
     *     provide unit of measurement together with the value.</P>
     *   </TD></TR>
     * </TABLE>
     *
     * @return Smallest distance between which separate points can be distinguished.
     * @unitof Distance
     *
     * @since 2.3
     */
    @UML(identifier="nominalSpatialResolution", obligation=OPTIONAL, specification=ISO_19115_2)
    Double getNominalSpatialResolution();

    /**
     * Type of transfer function to be used when scaling a physical value for a given element.
     *
     * @return Type of transfer function.
     *
     * @since 2.3
     */
    @UML(identifier="transferFunctionType", obligation=OPTIONAL, specification=ISO_19115_2)
    TransferFunctionType getTransferFunctionType();

    /**
     * Polarization of the radiation transmitted.
     *
     * @return Polarization of the radiation transmitted.
     *
     * @since 2.3
     */
    @UML(identifier="transmittedPolarization", obligation=OPTIONAL, specification=ISO_19115_2)
    PolarizationOrientation getTransmittedPolarization();

    /**
     * Polarization of the radiation detected.
     *
     * @return Polarization of the radiation detected.
     *
     * @since 2.3
     */
    @UML(identifier="detectedPolarization", obligation=OPTIONAL, specification=ISO_19115_2)
    PolarizationOrientation getDetectedPolarization();
}
