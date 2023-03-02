/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    Copyright © 2009-2023 Open Geospatial Consortium, Inc.
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

import java.util.ArrayList;
import java.util.List;

import org.opengis.annotation.UML;
import org.opengis.util.CodeList;

import static org.opengis.annotation.Obligation.*;
import static org.opengis.annotation.Specification.*;


/**
 * Polarization of the antenna relative to the waveform.
 *
 * @author  Cédric Briançon (Geomatys)
 * @version 4.0
 * @since   3.0
 */
@UML(identifier="MI_PolarisationOrientationCode", specification=ISO_19115_2)
public final class PolarisationOrientation extends CodeList<PolarisationOrientation> {
    /**
     * Serial number for compatibility with different versions.
     */
    private static final long serialVersionUID = -8653877364510456891L;

    /**
     * List of all enumerations of this type.
     * Must be declared before any enum declaration.
     */
    private static final List<PolarisationOrientation> VALUES = new ArrayList<>(6);

    /**
     * Polarization of the sensor oriented in the horizontal plane in relation to swath direction.
     */
    @UML(identifier="horizontal", obligation=CONDITIONAL, specification=ISO_19115_2)
    public static final PolarisationOrientation HORIZONTAL = new PolarisationOrientation("HORIZONTAL");

    /**
     * Polarization of the sensor oriented in the vertical plane in relation to swath direction.
     */
    @UML(identifier="vertical", obligation=CONDITIONAL, specification=ISO_19115_2)
    public static final PolarisationOrientation VERTICAL = new PolarisationOrientation("VERTICAL");

    /**
     * Polarization of the sensor oriented in the left circular plane in relation to swath direction.
     */
    @UML(identifier="leftCircular", obligation=CONDITIONAL, specification=ISO_19115_2)
    public static final PolarisationOrientation LEFT_CIRCULAR = new PolarisationOrientation("LEFT_CIRCULAR");

    /**
     * Polarization of the sensor oriented in the right circular plane in relation to swath direction.
     */
    @UML(identifier="rightCircular", obligation=CONDITIONAL, specification=ISO_19115_2)
    public static final PolarisationOrientation RIGHT_CIRCULAR = new PolarisationOrientation("RIGHT_CIRCULAR");

    /**
     * Polarization of the sensor oriented in the angle between +90 ° and 0 ° parallel to swath direction.
     */
    @UML(identifier="theta", obligation=CONDITIONAL, specification=ISO_19115_2)
    public static final PolarisationOrientation THETA = new PolarisationOrientation("THETA");

    /**
     * Polarization of the sensor oriented in the +90 ° and 0 ° perpendicular to swath direction.
     */
    @UML(identifier="phi", obligation=CONDITIONAL, specification=ISO_19115_2)
    public static final PolarisationOrientation PHI = new PolarisationOrientation("PHI");

    /**
     * Constructs an element of the given name. The new element is
     * automatically added to the list returned by {@link #values()}.
     *
     * @param name  the name of the new element. This name shall not be in use by another element of this type.
     */
    private PolarisationOrientation(final String name) {
        super(name, VALUES);
    }

    /**
     * Returns the list of {@code PolarisationOrientation}s.
     *
     * @return the list of codes declared in the current JVM.
     */
    public static PolarisationOrientation[] values() {
        synchronized (VALUES) {
            return VALUES.toArray(PolarisationOrientation[]::new);
        }
    }

    /**
     * Returns the list of codes of the same kind than this code list element.
     * Invoking this method is equivalent to invoking {@link #values()}, except that
     * this method can be invoked on an instance of the parent {@code CodeList} class.
     *
     * @return all code {@linkplain #values() values} for this code list.
     */
    @Override
    public PolarisationOrientation[] family() {
        return values();
    }

    /**
     * Returns the transfer function type that matches the given string, or returns a
     * new one if none match it. More specifically, this methods returns the first instance for
     * which <code>{@linkplain #name() name()}.{@linkplain String#equals equals}(code)</code>
     * returns {@code true}. If no existing instance is found, then a new one is created for
     * the given name.
     *
     * @param  code  the name of the code to fetch or to create.
     * @return a code matching the given name.
     */
    public static PolarisationOrientation valueOf(String code) {
        return valueOf(PolarisationOrientation.class, code);
    }
}
