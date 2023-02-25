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
import java.lang.annotation.Documented;
import static java.lang.annotation.ElementType.*;


/**
 * An annotation mapping an interface, methods or fields to a profile.
 *
 * @author  Martin Desruisseaux (IRD)
 * @version 3.0
 * @since   2.0
 *
 * @navassoc - - - ComplianceLevel
 */
@Documented
@Target(METHOD)
public @interface Profile {
    /**
     * The level for the annoted element. {@link ComplianceLevel#CORE CORE} means
     * that all profiles should provides this element.
     *
     * @return The compliance level.
     */
    ComplianceLevel level();
}
