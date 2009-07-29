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

import javax.measure.quantity.Length;
import javax.measure.unit.Unit;
import org.opengis.annotation.UML;

import static org.opengis.annotation.Obligation.*;
import static org.opengis.annotation.Specification.*;


/**
 * Range of wavelengths in the electromagnetic spectrum.
 *
 * @version <A HREF="http://www.opengeospatial.org/standards/as#01-111">ISO 19115</A>
 * @author  Martin Desruisseaux (IRD)
 * @author  Cory Horner (Refractions Research)
 * @author  Cédric Briançon (Geomatys)
 * @since   GeoAPI 2.0
 */
@UML(identifier="MD_Band", specification=ISO_19115)
public interface Band extends RangeDimension {
    /**
     * Longest wavelength that the sensor is capable of collecting within a designated band.
     * Returns {@code null} if unspecified.
     *
     * @return Longest wavelength that the sensor is capable of collecting within a designated band,
     *         or {@code null}.
     */
    @UML(identifier="maxValue", obligation=OPTIONAL, specification=ISO_19115)
    Double getMaxValue();

    /**
     * Shortest wavelength that the sensor is capable of collecting within a designated band.
     * Returns {@code null} if unspecified.
     *
     * @return Shortest wavelength that the sensor is capable of collecting within a designated band,
     *         or {@code null}.
     */
    @UML(identifier="minValue", obligation=OPTIONAL, specification=ISO_19115)
    Double getMinValue();

    /**
     * Units in which sensor wavelengths are expressed.
     *
     * @return Units in which sensor wavelengths are expressed, or {@code null}.
     *
     * @condition Should be non-null if {@linkplain #getMinValue min value} or
     *            {@linkplain #getMaxValue max value} are provided.
     */
    @UML(identifier="units", obligation=CONDITIONAL, specification=ISO_19115)
    Unit<Length> getUnits();

    /**
     * Wavelength at which the response is the highest.
     * Returns {@code null} if unspecified.
     *
     * @return Wavelength at which the response is the highest, or {@code null}.
     */
    @UML(identifier="peakResponse", obligation=OPTIONAL, specification=ISO_19115)
    Double getPeakResponse();

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
     * @since GeoAPI 2.3
     */
    @UML(identifier="bandBoundaryDefinition", obligation=OPTIONAL, specification=ISO_19115_2)
    BandDefinition getBandBoundaryDefinition();

    /**
     * Smallest distance between which separate points can be distinguished, as specified in
     * instrument design.
     *
     * @return Smallest distance between which separate points can be distinguished.
     *
     * @unitOf Distance
     *
     * @since GeoAPI 2.3
     */
    @UML(identifier="nominalSpatialResolution", obligation=OPTIONAL, specification=ISO_19115_2)
    Double getNominalSpatialResolution();

    /**
     * Type of transfer function to be used when scaling a physical value for a given element.
     *
     * @return Type of transfer function.
     *
     * @since GeoAPI 2.3
     */
    @UML(identifier="transferFunctionType", obligation=OPTIONAL, specification=ISO_19115_2)
    TransferFunctionType getTransferFunctionType();

    /**
     * Polarization of the radiation transmitted.
     *
     * @return Polarization of the radiation transmitted.
     *
     * @since GeoAPI 2.3
     */
    @UML(identifier="transmittedPolarization", obligation=OPTIONAL, specification=ISO_19115_2)
    PolarizationOrientation getTransmittedPolarization();

    /**
     * Polarization of the radiation detected.
     *
     * @return Polarization of the radiation detected.
     *
     * @since GeoAPI 2.3
     */
    @UML(identifier="detectedPolarization", obligation=OPTIONAL, specification=ISO_19115_2)
    PolarizationOrientation getDetectedPolarization();
}
