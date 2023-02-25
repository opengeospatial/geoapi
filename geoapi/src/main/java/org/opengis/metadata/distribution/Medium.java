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
package org.opengis.metadata.distribution;

import java.util.Collection;
import javax.measure.Unit;
import org.opengis.util.InternationalString;
import org.opengis.annotation.UML;

import static org.opengis.annotation.Obligation.*;
import static org.opengis.annotation.Specification.*;


/**
 * Information about the media on which the resource can be distributed.
 *
 * @author  Martin Desruisseaux (IRD)
 * @version 3.0.1
 * @since   2.0
 *
 * @navassoc 1 - - MediumName
 * @navassoc - - - MediumFormat
 */
@UML(identifier="MD_Medium", specification=ISO_19115)
public interface Medium {
    /**
     * Name of the medium on which the resource can be received.
     *
     * @return Name of the medium, or {@code null}.
     */
    @UML(identifier="name", obligation=OPTIONAL, specification=ISO_19115)
    MediumName getName();

    /**
     * Density at which the data is recorded.
     * The numbers shall be greater than zero.
     *
     * @return Density at which the data is recorded, or {@code null}.
     */
    @UML(identifier="density", obligation=OPTIONAL, specification=ISO_19115)
    Collection<Double> getDensities();

    /**
     * Units of measure for the recording density.
     *
     * @return Units of measure for the recording density, or {@code null}.
     *
     * @condition {@link #getDensities()} must be documented.
     */
    @UML(identifier="densityUnits", obligation=CONDITIONAL, specification=ISO_19115)
    Unit<?> getDensityUnits();

    /**
     * Number of items in the media identified.
     * Returns {@code null} if unknown.
     *
     * @return Number of items in the media identified, or {@code null}.
     */
    @UML(identifier="volumes", obligation=OPTIONAL, specification=ISO_19115)
    Integer getVolumes();

    /**
     * Method used to write to the medium.
     *
     * @return Method used to write to the medium, or {@code null}.
     */
    @UML(identifier="mediumFormat", obligation=OPTIONAL, specification=ISO_19115)
    Collection<MediumFormat> getMediumFormats();

    /**
     * Description of other limitations or requirements for using the medium.
     *
     * @return Description of other limitations for using the medium, or {@code null}.
     */
    @UML(identifier="mediumNote", obligation=OPTIONAL, specification=ISO_19115)
    InternationalString getMediumNote();
}
