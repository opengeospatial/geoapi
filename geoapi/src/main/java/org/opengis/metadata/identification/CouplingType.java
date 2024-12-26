/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    Copyright © 2014-2023 Open Geospatial Consortium, Inc.
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
package org.opengis.metadata.identification;

import org.opengis.util.CodeList;
import org.opengis.annotation.UML;
import org.opengis.geoapi.internal.Vocabulary;

import static org.opengis.annotation.Obligation.CONDITIONAL;
import static org.opengis.annotation.Specification.ISO_19115;


/**
 * Class of information to which the referencing entity applies.
 *
 * @author  Rémi Maréchal (Geomatys)
 * @version 3.1
 * @since   3.1
 */
@Vocabulary(capacity=3)
@UML(identifier="SV_CouplingType", specification=ISO_19115)
public final class CouplingType  extends CodeList<CouplingType> {
    /**
     * Serial number for compatibility with different versions.
     */
    private static final long serialVersionUID = -6836260354860513463L;

    /**
     * Service instance is loosely coupled with a data instance, which means no
     * {@link DataIdentification} class has to be described.
     */
    @UML(identifier="loose", obligation=CONDITIONAL, specification=ISO_19115)
    public static final CouplingType LOOSE = new CouplingType("LOOSE");

    /**
     * Service instance is mixed coupled with a data instance, which means
     * {@link DataIdentification} describes the associated data instance and
     * additionally the service instance might work with other external data instances.
     */
    @UML(identifier="mixed", obligation=CONDITIONAL, specification=ISO_19115)
    public static final CouplingType MIXED = new CouplingType("MIXED");

    /**
     * Service instance is tightly coupled with a data instance, which means
     * {@link DataIdentification} class MUST be described.
     */
    @UML(identifier="tight", obligation=CONDITIONAL, specification=ISO_19115)
    public static final CouplingType TIGHT = new CouplingType("TIGHT");

    /**
     * Constructs an element of the given name.
     *
     * @param name  the name of the new element. This name shall not be in use by another element of this type.
     */
    private CouplingType(final String name) {
        super(name);
    }

    /**
     * Returns the list of {@code CouplingType}s.
     *
     * @return the list of codes declared in the current JVM.
     */
    @Override
    public CouplingType[] family() {
        return values();
    }

    /**
     * Returns the list of {@code CouplingType}s.
     *
     * @return the list of codes declared in the current JVM.
     */
    public static CouplingType[] values() {
        return values(CouplingType.class);
    }

    /**
     * Returns the coupling type that matches the given name, or returns a new value if there is no match.
     * This methods returns the first instance (in declaration order) for which the {@linkplain #name() name}
     * is {@linkplain String#equalsIgnoreCase(String) equals, ignoring case}, to the given name.
     * If no existing instance is found, then a new one is created for the given name.
     *
     * @param  code  the name of the code to fetch or to create.
     * @return a code matching the given name.
     */
    public static CouplingType valueOf(String code) {
        return valueOf(CouplingType.class, code, CouplingType::new).get();
    }
}
