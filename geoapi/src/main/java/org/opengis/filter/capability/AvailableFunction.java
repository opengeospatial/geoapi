/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    Copyright © 2006-2023 Open Geospatial Consortium, Inc.
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
package org.opengis.filter.capability;

import java.util.List;
import java.util.Collections;
import org.opengis.util.LocalName;
import org.opengis.util.ScopedName;
import org.opengis.annotation.UML;

import static org.opengis.annotation.Obligation.OPTIONAL;
import static org.opengis.annotation.Obligation.MANDATORY;
import static org.opengis.annotation.Specification.ISO_19143;


/**
 * A function that may be used in filter expressions.
 * The description of each function includes a declaration of the return type,
 * the list of arguments with optional names and expected types.
 *
 * @author  Torsten Friebe (lat/lon)
 * @author  Justin Deoliveira (The Open Planning Project)
 * @author  Martin Desruisseaux (Geomatys)
 * @version 3.1
 *
 * @see FilterCapabilities#getFunctions()
 *
 * @since 3.1
 */
@UML(identifier="AvailableFunctions", specification=ISO_19143)
public interface AvailableFunction {
    /**
     * Returns the function name.
     *
     * @return the function name.
     */
    @UML(identifier="name", obligation=MANDATORY, specification=ISO_19143)
    LocalName getName();

    /**
     * Returns the type of return value.
     *
     * @return the type of return value.
     */
    @UML(identifier="returns", obligation=MANDATORY, specification=ISO_19143)
    ScopedName getReturnType();

    /**
     * Returns the list of arguments expected by the function.
     *
     * @return arguments that the function accepts.
     */
    @UML(identifier="arguments", obligation=OPTIONAL, specification=ISO_19143)
    default List<? extends Argument> getArguments() {
        return Collections.emptyList();
    }
}
