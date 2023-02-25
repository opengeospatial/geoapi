/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    Copyright © 2005-2011 Open Geospatial Consortium, Inc.
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
 * An annotation mapping each interface, methods or fields to
 * the UML identifier where they come from.
 *
 * @author  Martin Desruisseaux (IRD)
 * @version 3.0
 * @since   2.0
 *
 * @navassoc - - - String
 * @navassoc - - - Obligation
 * @navassoc - - - Specification
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
     * @return The UML identifier used in the standard.
     */
    String identifier();

    /**
     * The obligation declared in the UML. This metadata can be queried in order to
     * determine if a null value is allowed for the annotated method or not. If the
     * obligation is {@link Obligation#MANDATORY}, then null value are not allowed.
     *
     * @return The obligation declared in the standard.
     */
    Obligation obligation() default Obligation.MANDATORY;

    /**
     * The specification where this UML come from.
     *
     * @return The originating specification.
     */
    Specification specification();
}
