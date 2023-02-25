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
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.*;


/**
 * Maps an interface or a method to the XML type, element or attribute.
 * Interfaces usually map to XML types, while methods map to XML element
 * or attribute. It is not the purpose of this annotation to differentiate
 * types from attributes, since this distinction can already be inferred from
 * Java reflection. This annotation, completed with reflection if needed, should
 * only provides enough information for finding the corresponding XML element in
 * the {@linkplain XmlSchema schema}.
 *
 * @author  Martin Desruisseaux (IRD)
 * @since   GeoAPI 2.0
 */
@Documented
@Target({TYPE,METHOD,FIELD})
@Retention(RUNTIME)
public @interface XmlElement {
    /**
     * The name of the element in the XML schema.
     *
     * @return the XML element name.
     */
    String value();
}
