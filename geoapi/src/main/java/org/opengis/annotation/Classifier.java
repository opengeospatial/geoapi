/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    Copyright © 2013-2023 Open Geospatial Consortium, Inc.
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
 * An annotation specifying the stereotype (abstract, datatype, union, <i>etc.</i>) of an interface.
 * The UML class diagrams in ISO/OGC specifications declare some members as abstract, meaning that
 * instances of those interfaces are expected to implement one of their sub-interfaces.
 * While there is nothing like "abstract interface" and "concrete interface" in the Java language,
 * we nevertheless communicate ISO/OGC intent using this annotation.
 *
 * <p>Implementations are not required to represent "abstract interfaces" by Java abstract classes.
 * This annotation is provided merely for informative purpose for testing tools, implementations
 * based on Java reflection, or widgets among other usages.</p>
 *
 * <p>If this annotation is not present, then the default value is {@link Stereotype#TYPE}.</p>
 *
 * @author  Martin Desruisseaux (Geomatys)
 * @version 3.1
 * @since   3.1
 *
 * @see <a href="http://en.wikipedia.org/wiki/Classifier_%28UML%29">Classifier on Wikipedia</a>
 */
@Documented
@Retention(RUNTIME)
@Target(TYPE)
public @interface Classifier {
    /**
     * Returns the type of modeling element (type, datatype, abstract or union).
     *
     * @return the type of modeling element as declared in the OGC/ISO UML diagram.
     */
    Stereotype value();
}
