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
package org.opengis.geoapi.internal;

import java.lang.annotation.Target;
import java.lang.annotation.Retention;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;


/**
 * Characteristics of a code list of enumeration. This is optional metadata used for GeoAPI internal purposes.
 * For example, the {@linkplain #capacity() capacity} gives the initial number of elements in a code list.
 *
 * @author  Martin Desruisseaux (Geomatys)
 * @version 3.1
 * @since   3.1
 */
@Target(TYPE)
@Retention(RUNTIME)
public @interface Vocabulary {
    /**
     * {@return the initial number of elements in the code list}.
     * This is used for determining an initial size of array list.
     */
    int capacity();

    /**
     * {@return the number of parameters other than the code name for user codes}.
     * This is the number of additional parameters in {@code valueOf(…)} method.
     * For example, some code lists have a parameter specifying a Java class.
     */
    int parameters() default 0;
}
