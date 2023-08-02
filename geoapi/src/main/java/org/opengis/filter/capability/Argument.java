/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    Copyright © 2019-2023 Open Geospatial Consortium, Inc.
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

import org.opengis.util.LocalName;
import org.opengis.util.TypeName;
import org.opengis.annotation.UML;

import static org.opengis.annotation.Obligation.MANDATORY;
import static org.opengis.annotation.Specification.ISO_19143;


/**
 * Description of an argument of a function.
 *
 * @author  Martin Desruisseaux (Geomatys)
 * @version 3.1
 *
 * @see AvailableFunction#getArguments()
 *
 * @since 3.1
 */
@UML(identifier="Argument", specification=ISO_19143)
public interface Argument {
    /**
     * Name of the argument.
     *
     * @return argument name.
     */
    @UML(identifier="name", obligation=MANDATORY, specification=ISO_19143)
    LocalName getName();

    /**
     * Name of the type of the argument.
     *
     * @return argument type.
     *
     * @departure integration
     *   OGC/ISO defines the return type as {@link org.opengis.util.ScopedName}.
     *   However this is at odd with other classes returning type information,
     *   which use {@code TypeName}. The use of type name is also a step toward
     *   possible parameter harmonization described in
     *   <a href="https://github.com/opengeospatial/geoapi/issues/89">issue #89</a>.
     */
    @UML(identifier="type", obligation=MANDATORY, specification=ISO_19143)
    TypeName getValueType();
}
