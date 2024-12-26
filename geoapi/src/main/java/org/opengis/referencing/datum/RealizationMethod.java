/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    Copyright © 2024 Open Geospatial Consortium, Inc.
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
package org.opengis.referencing.datum;

import org.opengis.util.CodeList;
import org.opengis.annotation.UML;
import org.opengis.geoapi.internal.Vocabulary;

import static org.opengis.annotation.Obligation.*;
import static org.opengis.annotation.Specification.*;


/**
 * Specification of the method by which the vertical reference frame is realized.
 *
 * @see VerticalDatum#getRealizationMethod()
 *
 * @author  OGC Topic 2 (for abstract model and documentation)
 * @author  Martin Desruisseaux (Geomatys)
 * @version 3.1
 * @since   3.1
 */
@Vocabulary(capacity=3)
@UML(identifier="RealizationMethod", specification=ISO_19111)
public final class RealizationMethod extends CodeList<RealizationMethod> {
    /**
     * Serial number for compatibility with different versions.
     */
    private static final long serialVersionUID = -4801740301491126879L;

    /**
     * Realization is by adjustment of a levelling network fixed to one or more tide gauges.
     */
    @UML(identifier="levelling", obligation=CONDITIONAL, specification=ISO_19111)
    public static final RealizationMethod LEVELLING = new RealizationMethod("LEVELLING");

    /**
     * Realization is through a geoid height model or a height correction model.
     * This is applied to a specified geodetic <abbr>CRS</abbr>.
     */
    @UML(identifier="geoid", obligation=CONDITIONAL, specification=ISO_19111)
    public static final RealizationMethod GEOID = new RealizationMethod("GEOID");

    /**
     * Realization is through a tidal model or by tidal predictions.
     */
    @UML(identifier="tidal", obligation=CONDITIONAL, specification=ISO_19111)
    public static final RealizationMethod TIDAL = new RealizationMethod("TIDAL");

    /**
     * Constructs an element of the given name.
     *
     * @param name  the name of the new element. This name shall not be in use by another element of this type.
     */
    private RealizationMethod(final String name) {
        super(name);
    }

    /**
     * Returns the list of {@code RealizationMethod}s.
     *
     * @return the list of codes declared in the current JVM.
     */
    public static RealizationMethod[] values() {
        return values(RealizationMethod.class);
    }

    /**
     * Returns the list of codes of the same kind as this code list element.
     * Invoking this method is equivalent to invoking {@link #values()}, except that
     * this method can be invoked on an instance of the parent {@code CodeList} class.
     *
     * @return all code {@linkplain #values() values} for this code list.
     */
    @Override
    public RealizationMethod[] family() {
        return values();
    }

    /**
     * Returns the realization method that matches the given name, or returns a new value if there is no match.
     * This methods returns the first instance (in declaration order) for which the {@linkplain #name() name}
     * is {@linkplain String#equalsIgnoreCase(String) equals, ignoring case}, to the given name.
     * If no existing instance is found, then a new one is created for the given name.
     *
     * @param  code  the name of the code to fetch or to create.
     * @return a code matching the given name.
     */
    public static RealizationMethod valueOf(String code) {
        return valueOf(RealizationMethod.class, code, RealizationMethod::new).get();
    }
}
