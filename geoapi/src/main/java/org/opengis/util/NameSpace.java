/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    Copyright © 2006-2011 Open Geospatial Consortium, Inc.
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
 * A domain in which {@linkplain GenericName names} given by character strings are defined.
 * Every {@link GenericName} must be valid in a namespace. For a {@link LocalName}, this means
 * that the name must exist in the current namespace. For a {@link ScopedName}, this means:
 * <p>
 * <ol>
 *   <li>The {@linkplain ScopedName#head head} of a {@code ScopedName} must be a {@code LocalName}
 *       which is valid in this namespace.</li>
 *   <li>The {@linkplain ScopedName#tail tail} must either be:
 *       <ul>
 *         <li>a {@code LocalName} which is valid in the {@code NameSpace} associated with the head, or</li>
 *         <li>another {@code ScopedName} with these same constraints on head and tail, applied to
 *             the {@code NameSpace} associated with the head.</li>
 *       </ul></li>
 * </ol>
 *
 * @author  Bryce Nordgren (USDA)
 * @author  Martin Desruisseaux (IRD)
 * @version 3.0
 * @since   2.1
 *
 * @navassoc 1 - - GenericName
 */
@UML(identifier="NameSpace", specification=ISO_19103)
public interface NameSpace {
    /**
     * Indicates whether this namespace is a "top level" namespace.  Global, or top-level
     * namespaces are not contained within another namespace.  There is no namespace called
     * "global" or "root" which contains all of the top-level namespaces.  Hence, this flag
     * indicates whether the namespace has a parent.
     *
     * @return {@code true} if this namespace has no parent.
     */
    @UML(identifier="isGlobal", obligation=MANDATORY, specification=ISO_19103)
    boolean isGlobal();

    /**
     * Represents the identifier of this namespace. If the {@linkplain #isGlobal global} attribute is
     * {@code true}, indicating that this is a top level {@code NameSpace}, then the name shall be a
     * {@linkplain LocalName local name}. If {@code false}, name shall be a fully-qualified name where
     * <code>name.{@linkplain GenericName#scope() scope()}.{@linkplain #isGlobal} == true</code>.
     *
     * @return The identifier of this namespace.
     */
    @UML(identifier="name", obligation=MANDATORY, specification=ISO_19103)
    GenericName name();
}
