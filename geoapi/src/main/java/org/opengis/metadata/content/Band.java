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
 * @author  Rémi Maréchal (Geomatys)
 * @version 3.1
 * @since   2.0
 */
@UML(identifier="MD_Band", specification=ISO_19115)
public interface Band extends SampleDimension {
    /**
     * Shortest wavelength that the sensor is capable of collecting within a designated band.
     *
     * @return shortest wavelength that the sensor is capable of collecting within a designated band,
     *         or {@code null} if unspecified.
     *
     * @since 3.1
     */
    @UML(identifier="boundMin", obligation=OPTIONAL, specification=ISO_19115)
    Double getBoundMin();

    /**
     * Longest wavelength that the sensor is capable of collecting within a designated band.
     *
     * @return longest wavelength that the sensor is capable of collecting within a designated band,
     *         or {@code null} if unspecified.
     *
     * @since 3.1
     */
    @UML(identifier="boundMax", obligation=OPTIONAL, specification=ISO_19115)
    Double getBoundMax();

    /**
     * Units in which sensor wavelengths are expressed.
     *
     * @return units in which sensor wavelengths are expressed, or {@code null} if unspecified.
     *
     * @since 3.1
     */
    @UML(identifier="boundUnits", obligation=OPTIONAL, specification=ISO_19115)
    Unit<Length> getBoundUnits();

    /**
     * Designation of criterion for defining maximum and minimum wavelengths for a spectral band.
     *
     * @return criterion for defining maximum and minimum wavelengths.
     *
     * @since 2.3
     */
    @UML(identifier="bandBoundaryDefinition", obligation=OPTIONAL, specification=ISO_19115_2)
    BandDefinition getBandBoundaryDefinition();

    /**
     * Wavelength at which the response is the highest.
     * Returns {@code null} if unspecified.
     *
     * @return wavelength at which the response is the highest, or {@code null} if unspecified.
     */
    @UML(identifier="peakResponse", obligation=OPTIONAL, specification=ISO_19115)
    Double getPeakResponse();

    /**
     * Number of discrete numerical values in the grid data.
     * Returns {@code null} if unspecified.
     *
     * @return number of discrete numerical values in the grid data, or {@code null}.
     */
    @UML(identifier="toneGradation", obligation=OPTIONAL, specification=ISO_19115)
    Integer getToneGradation();

    /**
     * Polarization of the radiation transmitted.
     *
     * @return polarization of the radiation transmitted.
     *
     * @since 2.3
     */
    @UML(identifier="transmittedPolarization", obligation=OPTIONAL, specification=ISO_19115_2)
    PolarizationOrientation getTransmittedPolarization();

    /**
     * Polarization of the radiation detected.
     *
     * @return polarization of the radiation detected.
     *
     * @since 2.3
     */
    @UML(identifier="detectedPolarization", obligation=OPTIONAL, specification=ISO_19115_2)
    PolarizationOrientation getDetectedPolarization();
}
