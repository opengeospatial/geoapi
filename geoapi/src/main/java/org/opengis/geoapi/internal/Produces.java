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
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;


/**
 * Declaration of the types of objects created by a factory.
 * For a given class <var>T</var> of objects, whether a factory of class <var>F</var>
 * may be able to create instances of <var>T</var> can be tested with the following code:
 *
 * {@snippet lang="java" :
 * for (Class<?> p : F.getAnnotation(Produces.class).value()) {
 *     if (p.isAssignableFrom(T)) {
 *         return true;
 *     }
 * }
 * return false;
 * }
 *
 * This annotation does not need to list all subtypes.
 * A parent type can be declared if the annotated factory is the only one to produce the subtypes.
 * For example, {@link org.opengis.referencing.crs.CRSAuthorityFactory} declares that it produces
 * instances of {@link org.opengis.referencing.crs.CoordinateReferenceSystem} without enumerating
 * all <abbr>CRS</abbr> subtypes, because no other GeoAPI factory produce <abbr>CRS</abbr>.
 *
 * <h2>Why not in public API</h2>
 * This annotation is helpful for {@link org.opengis.referencing.RegisterOperations} default methods,
 * but there is no evidence yet that it would be more generally useful. Furthermore, the use of base
 * types instead of enumerating all types assumes a closed world, where all annotated factories are
 * known in advance. If this annotation was made public, we would need to explain that it is only for
 * GeoAPI factories, and we would need to add the annotation on all factories (it is currently applied
 * only on authority factories).
 *
 * @author  Martin Desruisseaux (Geomatys)
 * @version 3.1
 * @since   3.1
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface Produces {
    /**
     * Returns the types of objects created by a factory.
     * A parent type can be declared when all subtypes should be created by the same factory.
     *
     * @return the base types of objects created by a factory.
     */
    Class<?>[] value();
}
