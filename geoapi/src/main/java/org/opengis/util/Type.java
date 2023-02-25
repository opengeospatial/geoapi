/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    Copyright © 2009-2011 Open Geospatial Consortium, Inc.
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
package org.opengis.util;

import org.opengis.annotation.UML;

import static org.opengis.annotation.Obligation.*;
import static org.opengis.annotation.Specification.*;


/**
 * The type definition. Every {@code Type} is {@linkplain #getTypeName identified} by a
 * {@link TypeName}.
 * <p>
 * This class can be think as the equivalent of the Java {@link Class} class.
 *
 * @author  Bryce Nordgren (USDA)
 * @author  Martin Desruisseaux (IRD)
 * @version 3.0
 * @since   2.3
 *
 * @navassoc 1 - - TypeName
 */
@UML(identifier="Type", specification=ISO_19103)
public interface Type {
    /**
     * Returns the name that identifies this type.
     * This method can be think as the equivalent of the Java {@link Class#getName()} method.
     *
     * @return The name that identifies this type.
     */
    @UML(identifier="typeName", obligation=MANDATORY, specification=ISO_19103)
    TypeName getTypeName();
}
