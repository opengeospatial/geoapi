/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    Copyright © 2005-2023 Open Geospatial Consortium, Inc.
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
package org.opengis.annotation;

import java.lang.annotation.Target;
import java.lang.annotation.Retention;
import java.lang.annotation.Documented;
import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.*;


/**
 * An annotation mapping each interface, methods or fields to the UML identifier where they come from.
 *
 * @author  Martin Desruisseaux (IRD, Geomatys)
 * @version 3.1
 * @since   2.0
 */
@Documented
@Retention(RUNTIME)
@Target({TYPE, FIELD, METHOD})
public @interface UML {
    /**
     * The UML identifier for the annotated interface, method or code list element.
     * Scripts can use this identifier in order to maps a GeoAPI method to the UML
     * entity where it come from.
     *
     * @return the UML identifier used in the standard.
     *
     * @see ResourceBundles#classIndex()
     */
    String identifier();

    /**
     * The obligation declared in the UML. This metadata can be queried in order to
     * determine if a null value is allowed for the annotated method or not. If the
     * obligation is {@link Obligation#MANDATORY}, then null value are not allowed.
     *
     * @return the obligation declared in the standard.
     */
    Obligation obligation() default Obligation.MANDATORY;

    /**
     * The specification where this UML come from.
     *
     * @return the originating specification.
     */
    Specification specification();

    /**
     * The version of the specification where this UML come from,
     * or 0 for the {@linkplain Specification#defaultVersion() default version}.
     * The valid version numbers are listed in {@link Specification} enumeration constants.
     *
     * <p><b>When older standard versions are used:</b></p>
     * The vast majority of non-deprecated GeoAPI methods leave {@code UML.version()} to its default value,
     * meaning that the {@code Specification} default version (usually latest OGC/ISO version) is used.
     * However, there is a few exceptions when an older version of an OGC or ISO standard is preferred.
     * Examples:
     *
     * <ul>
     *   <li>{@linkplain Specification#ISO_19115 ISO 19115}:2003 defined {@code PT_Locale} in a way closer
     *       to the {@link java.util.Locale java.util.Locale} model than the newer ISO 19115:2014.</li>
     * </ul>
     *
     * @return the specification version, or 0 for the default (usually latest) specification.
     *
     * @see Specification#defaultVersion()
     *
     * @since 3.1
     */
    short version() default 0;
}
