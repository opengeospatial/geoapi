/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    Copyright © 2004-2023 Open Geospatial Consortium, Inc.
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
    default Double getBoundMin() {
        return null;
    }

    /**
     * Longest wavelength that the sensor is capable of collecting within a designated band.
     *
     * @return longest wavelength that the sensor is capable of collecting within a designated band,
     *         or {@code null} if unspecified.
     *
     * @since 3.1
     */
    @UML(identifier="boundMax", obligation=OPTIONAL, specification=ISO_19115)
    default Double getBoundMax() {
        return null;
    }

    /**
     * Units in which sensor wavelengths are expressed.
     *
     * @return units in which sensor wavelengths are expressed, or {@code null} if unspecified.
     *
     * @since 3.1
     */
    @UML(identifier="boundUnits", obligation=OPTIONAL, specification=ISO_19115)
    default Unit<Length> getBoundUnits() {
        return null;
    }

    /**
     * Designation of criterion for defining maximum and minimum wavelengths for a spectral band.
     *
     * @return criterion for defining maximum and minimum wavelengths.
     */
    @UML(identifier="bandBoundaryDefinition", obligation=OPTIONAL, specification=ISO_19115_2)
    default BandDefinition getBandBoundaryDefinition() {
        return null;
    }

    /**
     * Wavelength at which the response is the highest.
     * Returns {@code null} if unspecified.
     *
     * @return wavelength at which the response is the highest, or {@code null} if unspecified.
     */
    @UML(identifier="peakResponse", obligation=OPTIONAL, specification=ISO_19115)
    default Double getPeakResponse() {
        return null;
    }

    /**
     * Number of discrete numerical values in the grid data.
     * Returns {@code null} if unspecified.
     *
     * @return number of discrete numerical values in the grid data, or {@code null}.
     */
    @UML(identifier="toneGradation", obligation=OPTIONAL, specification=ISO_19115)
    default Integer getToneGradation() {
        return null;
    }

    /**
     * Polarization of the radiation transmitted.
     *
     * @return polarization of the radiation transmitted.
     */
    @UML(identifier="transmittedPolarisation", obligation=OPTIONAL, specification=ISO_19115_2)
    default PolarisationOrientation getTransmittedPolarisation() {
        return null;
    }

    /**
     * Polarization of the radiation detected.
     *
     * @return polarization of the radiation detected.
     */
    @UML(identifier="detectedPolarisation", obligation=OPTIONAL, specification=ISO_19115_2)
    default PolarisationOrientation getDetectedPolarisation() {
        return null;
    }
}
