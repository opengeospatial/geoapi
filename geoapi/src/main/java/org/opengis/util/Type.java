/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    Copyright © 2009-2023 Open Geospatial Consortium, Inc.
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

import java.util.Optional;
import org.opengis.annotation.UML;

import static org.opengis.annotation.Obligation.*;
import static org.opengis.annotation.Specification.*;


/**
 * Base interface of type definitions.
 * Every {@code Type} is {@linkplain #getTypeName identified} by a {@link TypeName}.
 * {@code Type} is either the base interface of meta-classes such as {@link RecordType},
 * or the return value of methods providing information about fields or members such as
 * {@link RecordType#getFieldTypes()}.
 *
 * <p>{@code Type} can optionally be associated to a Java class.
 * The Java class can be inferred from the {@link TypeName} using a naming convention,
 * or be {@linkplain #toJavaType() provided directly} by the implementation.</p>
 *
 * @author  Bryce Nordgren (USDA)
 * @author  Martin Desruisseaux (IRD)
 * @version 3.1
 * @since   2.3
 */
@UML(identifier="Type", specification=ISO_19103, version=2005)
public interface Type {
    /**
     * Returns the name that identifies this type.
     * This method can be think as the equivalent of the Java {@link Class#getName()} method.
     *
     * @return the name that identifies this type.
     */
    @UML(identifier="typeName", obligation=MANDATORY, specification=ISO_19103, version=2005)
    TypeName getTypeName();

    /**
     * Returns the Java type corresponding to the OGC/ISO type.
     * By default, the Java type is inferred from the name by delegating to {@link TypeName#toJavaType()}.
     *
     * @departure integration
     *   Added for integration with the Java language.
     *
     * @return the Java type (usually a {@link Class}) for this OGC/ISO type.
     *
     * @since 3.1
     */
    default Optional<java.lang.reflect.Type> toJavaType() {
        final TypeName name = getTypeName();
        return (name != null) ? name.toJavaType() : Optional.empty();
    }
}
