/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    Copyright © 2008-2023 Open Geospatial Consortium, Inc.
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
 * Maps a method to the XML parameter. This annotation can be used with reflection for
 * finding the corresponding XML parameter in the {@linkplain XmlSchema schema}.
 *
 * <p>This annotation is used for documentation purpose only and may be removed in a future version.
 * The reason is that GeoAPI tries to publish interfaces based on UML instead of XSD schemas.</p>
 *
 * @author  Johann Sorel (Geomatys)
 * @since   GeoAPI 2.2
 */
@Documented
@Target(METHOD)
@Retention(SOURCE)                  // Used for documentation purpose only.
public @interface XmlParameter {
    /**
     * The name of the element in the XML schema.
     *
     * @return the XML parameter name.
     */
    String value();
}
