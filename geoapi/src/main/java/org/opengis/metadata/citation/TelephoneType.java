/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    Copyright © 2014-2024 Open Geospatial Consortium, Inc.
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
package org.opengis.metadata.citation;

import org.opengis.annotation.UML;
import org.opengis.util.CodeList;
import org.opengis.geoapi.internal.Vocabulary;

import static org.opengis.annotation.Obligation.CONDITIONAL;
import static org.opengis.annotation.Specification.ISO_19115;


/**
 * Type of telephone.
 *
 * @author  Rémi Maréchal (Geomatys)
 * @version 3.1
 * @since   3.1
 */
@Vocabulary(capacity=3)
@UML(identifier="CI_TelephoneTypeCode", specification=ISO_19115)
public final class TelephoneType extends CodeList<TelephoneType> {
    /**
     * Serial number for compatibility with different versions.
     */
    private static final long serialVersionUID = -7767813100470887718L;

    /**
     * Telephone provides voice service.
     */
    @UML(identifier="voice", obligation=CONDITIONAL, specification=ISO_19115)
    public static final TelephoneType VOICE = new TelephoneType("VOICE");

    /**
     * Telephone provides facsimile service.
     */
    @UML(identifier="facsimile", obligation=CONDITIONAL, specification=ISO_19115)
    public static final TelephoneType FACSIMILE = new TelephoneType("FACSIMILE");

    /**
     * Telephone provides SMS service.
     */
    @UML(identifier="sms", obligation=CONDITIONAL, specification=ISO_19115)
    public static final TelephoneType SMS = new TelephoneType("SMS");

    /**
     * Constructs an element of the given name.
     *
     * @param name  the name of the new element. This name shall not be in use by another element of this type.
     */
    private TelephoneType(String name) {
        super(name);
    }

    /**
     * Returns the list of {@code TelephoneType}s.
     *
     * @return the list of codes declared in the current JVM.
     */
    public static TelephoneType[] values() {
        return values(TelephoneType.class);
    }

    /**
     * Returns the list of codes of the same kind as this code list element.
     * Invoking this method is equivalent to invoking {@link #values()}, except that
     * this method can be invoked on an instance of the parent {@code CodeList} class.
     *
     * @return all code {@linkplain #values() values} for this code list.
     */
    @Override
    public TelephoneType[] family() {
        return values();
    }

    /**
     * Returns the telephone type that matches the given string, or returns a new one if none match it.
     * This methods returns the first instance (in declaration order) for which the {@linkplain #name() name}
     * is {@linkplain String#equalsIgnoreCase(String) equals, ignoring case}, to the given name.
     * If no existing instance is found, then a new one is created for the given name.
     *
     * @param  code  the name of the code to fetch or to create.
     * @return a code matching the given name.
     */
    public static TelephoneType valueOf(String code) {
        return valueOf(TelephoneType.class, code, TelephoneType::new).get();
    }
}
