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
package org.opengis.metadata.citation;

import org.opengis.util.CodeList;
import org.opengis.annotation.UML;
import org.opengis.geoapi.internal.Vocabulary;

import static org.opengis.annotation.Obligation.*;
import static org.opengis.annotation.Specification.*;


/**
 * Identification of when a given event occurred
 *
 * @author  Martin Desruisseaux (IRD)
 * @author  Rémi Maréchal (Geomatys)
 * @version 3.1
 * @since   2.0
 */
@Vocabulary(capacity=16)
@UML(identifier="CI_DateTypeCode", specification=ISO_19115)
public final class DateType extends CodeList<DateType> {
    /**
     * Serial number for compatibility with different versions.
     */
    private static final long serialVersionUID = 9031571038833329544L;

    /**
     * Date identifies when the resource was brought into existence.
     */
    @UML(identifier="creation", obligation=CONDITIONAL, specification=ISO_19115)
    public static final DateType CREATION = new DateType("CREATION");

    /**
     * Date identifies when the resource was issued.
     */
    @UML(identifier="publication", obligation=CONDITIONAL, specification=ISO_19115)
    public static final DateType PUBLICATION = new DateType("PUBLICATION");

    /**
     * Date identifies when the resource was examined or re-examined and improved or amended.
     */
    @UML(identifier="revision", obligation=CONDITIONAL, specification=ISO_19115)
    public static final DateType REVISION = new DateType("REVISION");

    /**
     * Date identifies when resource expires.
     *
     * @since 3.1
     */
    @UML(identifier="expiry", obligation=CONDITIONAL, specification=ISO_19115)
    public static final DateType EXPIRY = new DateType("EXPIRY");

    /**
     * Date identifies when resource was last updated.
     *
     * @since 3.1
     */
    @UML(identifier="lastUpdate", obligation=CONDITIONAL, specification=ISO_19115)
    public static final DateType LAST_UPDATE = new DateType("LAST_UPDATE");

    /**
     * Date identifies when the resource was examined or re-examined and improved or amended.
     *
     * @since 3.1
     */
    @UML(identifier="lastRevision", obligation=CONDITIONAL, specification=ISO_19115)
    public static final DateType LAST_REVISION = new DateType("LAST_REVISION");

    /**
     * Date identifies when the resource will be next updated.
     *
     * @since 3.1
     */
    @UML(identifier="nextUpdate", obligation=CONDITIONAL, specification=ISO_19115)
    public static final DateType NEXT_UPDATE = new DateType("NEXT_UPDATE");

    /**
     * Date identifies when resource became not available or obtainable.
     *
     * @since 3.1
     */
    @UML(identifier="unavailable", obligation=CONDITIONAL, specification=ISO_19115)
    public static final DateType UNAVAILABLE = new DateType("UNAVAILABLE");

    /**
     * Date identifies when resource became in force.
     *
     * @since 3.1
     */
    @UML(identifier="inForce", obligation=CONDITIONAL, specification=ISO_19115)
    public static final DateType IN_FORCE = new DateType("IN_FORCE");

    /**
     * Date identifies when the resource was adopted.
     *
     * @since 3.1
     */
    @UML(identifier="adopted", obligation=CONDITIONAL, specification=ISO_19115)
    public static final DateType ADOPTED = new DateType("ADOPTED");

    /**
     * Date identifies when the resource was deprecated.
     *
     * @since 3.1
     */
    @UML(identifier="deprecated", obligation=CONDITIONAL, specification=ISO_19115)
    public static final DateType DEPRECATED = new DateType("DEPRECATED");

    /**
     * Date identifies when resource was superseded or replaced by another resource.
     *
     * @since 3.1
     */
    @UML(identifier="superseded", obligation=CONDITIONAL, specification=ISO_19115)
    public static final DateType SUPERSEDED = new DateType("SUPERSEDED");

    /**
     * Time at which the data are considered to become valid.
     *
     * <div class="note"><b>Note:</b>
     * there could be quite a delay between creation and validity begins.</div>
     *
     * @since 3.1
     */
    @UML(identifier="validityBegins", obligation=CONDITIONAL, specification=ISO_19115)
    public static final DateType VALIDITY_BEGINS = new DateType("VALIDITY_BEGINS");

    /**
     * Time at which the data are no longer considered to be valid.
     *
     * @since 3.1
     */
    @UML(identifier="validityExpires", obligation=CONDITIONAL, specification=ISO_19115)
    public static final DateType VALIDITY_EXPIRES = new DateType("VALIDITY_EXPIRES");

    /**
     * The date that the resource shall be released for public access.
     *
     * @since 3.1
     */
    @UML(identifier="released", obligation=CONDITIONAL, specification=ISO_19115)
    public static final DateType RELEASED = new DateType("RELEASED");

    /**
     * Date identifies when an instance of the resource was distributed.
     *
     * @since 3.1
     */
    @UML(identifier="distribution", obligation=CONDITIONAL, specification=ISO_19115)
    public static final DateType DISTRIBUTION = new DateType("DISTRIBUTION");

    /**
     * Constructs an element of the given name.
     *
     * @param name  the name of the new element. This name shall not be in use by another element of this type.
     */
    private DateType(final String name) {
        super(name);
    }

    /**
     * Returns the list of {@code DateType}s.
     *
     * @return the list of codes declared in the current JVM.
     */
    public static DateType[] values() {
        return values(DateType.class);
    }

    /**
     * Returns the list of codes of the same kind as this code list element.
     * Invoking this method is equivalent to invoking {@link #values()}, except that
     * this method can be invoked on an instance of the parent {@code CodeList} class.
     *
     * @return all code {@linkplain #values() values} for this code list.
     */
    @Override
    public DateType[] family() {
        return values();
    }

    /**
     * Returns the date type that matches the given name, or returns a new value if there is no match.
     * This methods returns the first instance (in declaration order) for which the {@linkplain #name() name}
     * is {@linkplain String#equalsIgnoreCase(String) equals, ignoring case}, to the given name.
     * If no existing instance is found, then a new one is created for the given name.
     *
     * @param  code  the name of the code to fetch or to create.
     * @return a code matching the given name.
     */
    public static DateType valueOf(String code) {
        return valueOf(DateType.class, code, DateType::new).get();
    }
}
