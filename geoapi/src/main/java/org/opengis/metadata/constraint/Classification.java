/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    Copyright © 2004-2024 Open Geospatial Consortium, Inc.
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
package org.opengis.metadata.constraint;

import org.opengis.util.CodeList;
import org.opengis.annotation.UML;
import org.opengis.geoapi.internal.Vocabulary;

import static org.opengis.annotation.Obligation.*;
import static org.opengis.annotation.Specification.*;


/**
 * Name of the handling restrictions on the dataset.
 *
 * @author  Martin Desruisseaux (IRD)
 * @author  Rémi Maréchal (Geomatys)
 * @version 3.1
 * @since   2.0
 */
@Vocabulary(capacity=9)
@UML(identifier="MD_ClassificationCode", specification=ISO_19115)
public final class Classification extends CodeList<Classification> {
    /**
     * Serial number for compatibility with different versions.
     */
    private static final long serialVersionUID = -549174931332214797L;

    /**
     * Available for general disclosure.
     */
    @UML(identifier="unclassified", obligation=CONDITIONAL, specification=ISO_19115)
    public static final Classification UNCLASSIFIED = new Classification("UNCLASSIFIED");

    /**
     * Not for general disclosure.
     */
    @UML(identifier="restricted", obligation=CONDITIONAL, specification=ISO_19115)
    public static final Classification RESTRICTED = new Classification("RESTRICTED");

    /**
     * Available for someone who can be entrusted with information.
     */
    @UML(identifier="confidential", obligation=CONDITIONAL, specification=ISO_19115)
    public static final Classification CONFIDENTIAL = new Classification("CONFIDENTIAL");

    /**
     * Kept or meant to be kept private, unknown, or hidden from all but a select group of people.
     */
    @UML(identifier="secret", obligation=CONDITIONAL, specification=ISO_19115)
    public static final Classification SECRET = new Classification("SECRET");

    /**
     * Of the highest secrecy.
     */
    @UML(identifier="topSecret", obligation=CONDITIONAL, specification=ISO_19115)
    public static final Classification TOP_SECRET = new Classification("TOP_SECRET");

    /**
     * Although unclassified, requires strict controls over its distribution.
     *
     * @since 3.1
     */
    @UML(identifier="sensitiveButUnclassified", obligation=CONDITIONAL, specification=ISO_19115)
    public static final Classification SENSITIVE_BUT_UNCLASSIFIED = new Classification("SENSITIVE_BUT_UNCLASSIFIED");

    /**
     * Unclassified information that is to be used only for official purposes
     * determinate by the designating body.
     *
     * @since 3.1
     */
    @UML(identifier="forOfficialUseOnly", obligation=CONDITIONAL, specification=ISO_19115)
    public static final Classification FOR_OFFICIAL_USE_ONLY = new Classification("FOR_OFFICIAL_USE_ONLY");

    /**
     * Compromise of the information could cause damage.
     *
     * @since 3.1
     */
    @UML(identifier="protected", obligation=CONDITIONAL, specification=ISO_19115)
    public static final Classification PROTECTED = new Classification("PROTECTED");

    /**
     * Dissemination limited by designating body.
     *
     * @since 3.1
     */
    @UML(identifier="limitedDistribution", obligation=CONDITIONAL, specification=ISO_19115)
    public static final Classification LIMITED_DISTRIBUTION = new Classification("LIMITED_DISTRIBUTION");

    /**
     * Constructs an element of the given name.
     *
     * @param name  the name of the new element. This name shall not be in use by another element of this type.
     */
    private Classification(final String name) {
        super(name);
    }

    /**
     * Returns the list of {@code Classification}s.
     *
     * @return the list of codes declared in the current JVM.
     */
    public static Classification[] values() {
        return values(Classification.class);
    }

    /**
     * Returns the list of codes of the same kind as this code list element.
     * Invoking this method is equivalent to invoking {@link #values()}, except that
     * this method can be invoked on an instance of the parent {@code CodeList} class.
     *
     * @return all code {@linkplain #values() values} for this code list.
     */
    @Override
    public Classification[] family() {
        return values();
    }

    /**
     * Returns the classification that matches the given string, or returns a new one if none match it.
     * This methods returns the first instance (in declaration order) for which the {@linkplain #name() name}
     * is {@linkplain String#equalsIgnoreCase(String) equals, ignoring case}, to the given name.
     * If no existing instance is found, then a new one is created for the given name.
     *
     * @param  code  the name of the code to fetch or to create.
     * @return a code matching the given name.
     */
    public static Classification valueOf(String code) {
        return valueOf(Classification.class, code, Classification::new).get();
    }
}
