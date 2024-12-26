/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    Copyright © 2019-2024 Open Geospatial Consortium, Inc.
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

import org.opengis.annotation.UML;
import org.opengis.util.CodeList;
import org.opengis.geoapi.internal.Vocabulary;

import static org.opengis.annotation.Obligation.*;
import static org.opengis.annotation.Specification.*;


/**
 * Polarization of the antenna relative to the waveform.
 *
 * <div class="warning"><b>Upcoming API change</b><br>
 * This class will be renamed {@code PolarisationOrientation} in GeoAPI 4.0
 * for compliance with ISO 19115-2:2019.
 * </div>
 *
 * @author  Cédric Briançon (Geomatys)
 * @version 3.1
 * @since   3.0
 */
@Vocabulary(capacity=6)
@UML(identifier="MI_PolarisationOrientationCode", specification=ISO_19115_2)
public final class PolarizationOrientation extends CodeList<PolarizationOrientation> {
    /**
     * Serial number for compatibility with different versions.
     */
    private static final long serialVersionUID = -8653877364510456891L;

    /**
     * Polarization of the sensor oriented in the horizontal plane in relation to swath direction.
     */
    @UML(identifier="horizontal", obligation=CONDITIONAL, specification=ISO_19115_2)
    public static final PolarizationOrientation HORIZONTAL = new PolarizationOrientation("HORIZONTAL");

    /**
     * Polarization of the sensor oriented in the vertical plane in relation to swath direction.
     */
    @UML(identifier="vertical", obligation=CONDITIONAL, specification=ISO_19115_2)
    public static final PolarizationOrientation VERTICAL = new PolarizationOrientation("VERTICAL");

    /**
     * Polarization of the sensor oriented in the left circular plane in relation to swath direction.
     */
    @UML(identifier="leftCircular", obligation=CONDITIONAL, specification=ISO_19115_2)
    public static final PolarizationOrientation LEFT_CIRCULAR = new PolarizationOrientation("LEFT_CIRCULAR");

    /**
     * Polarization of the sensor oriented in the right circular plane in relation to swath direction.
     */
    @UML(identifier="rightCircular", obligation=CONDITIONAL, specification=ISO_19115_2)
    public static final PolarizationOrientation RIGHT_CIRCULAR = new PolarizationOrientation("RIGHT_CIRCULAR");

    /**
     * Polarization of the sensor oriented in the angle between +90 ° and 0 ° parallel to swath direction.
     */
    @UML(identifier="theta", obligation=CONDITIONAL, specification=ISO_19115_2)
    public static final PolarizationOrientation THETA = new PolarizationOrientation("THETA");

    /**
     * Polarization of the sensor oriented in the +90 ° and 0 ° perpendicular to swath direction.
     */
    @UML(identifier="phi", obligation=CONDITIONAL, specification=ISO_19115_2)
    public static final PolarizationOrientation PHI = new PolarizationOrientation("PHI");

    /**
     * Constructs an element of the given name.
     *
     * @param name  the name of the new element. This name shall not be in use by another element of this type.
     */
    private PolarizationOrientation(final String name) {
        super(name);
    }

    /**
     * Returns the list of {@code PolarisationOrientation}s.
     *
     * @return the list of codes declared in the current JVM.
     */
    public static PolarizationOrientation[] values() {
        return values(PolarizationOrientation.class);
    }

    /**
     * Returns the list of codes of the same kind as this code list element.
     * Invoking this method is equivalent to invoking {@link #values()}, except that
     * this method can be invoked on an instance of the parent {@code CodeList} class.
     *
     * @return all code {@linkplain #values() values} for this code list.
     */
    @Override
    public PolarizationOrientation[] family() {
        return values();
    }

    /**
     * Returns the polarization orientation that matches the given name, or returns a new value if there is no match.
     * This methods returns the first instance (in declaration order) for which the {@linkplain #name() name}
     * is {@linkplain String#equalsIgnoreCase(String) equals, ignoring case}, to the given name.
     * If no existing instance is found, then a new one is created for the given name.
     *
     * @param  code  the name of the code to fetch or to create.
     * @return a code matching the given name.
     */
    public static PolarizationOrientation valueOf(String code) {
        return valueOf(PolarizationOrientation.class, code, PolarizationOrientation::new).get();
    }
}
