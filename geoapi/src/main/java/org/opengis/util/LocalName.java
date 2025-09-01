/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    Copyright © 2004-2023 Open Geospatial Consortium, Inc.
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

import java.util.List;
import org.opengis.annotation.UML;

import static org.opengis.annotation.Obligation.*;
import static org.opengis.annotation.Specification.*;


/**
 * Identifier within a {@linkplain NameSpace name space} for a local object.
 * Local names are names which are directly accessible to and maintained by a {@link NameSpace}.
 * Names are local to one and only one name space.
 * The name space within which they are local is indicated by the {@linkplain #scope() scope}.
 *
 * @author  Martin Desruisseaux (IRD)
 * @author  Bryce Nordgren (USDA)
 * @version 3.0
 * @since   2.0
 *
 * @see NameFactory#createLocalName(NameSpace, CharSequence)
 */
@UML(identifier="LocalName", specification=ISO_19103)
public interface LocalName extends GenericName {
    /**
     * Returns the number of levels specified by this name, which is always 1 for a local name.
     *
     * @return always 1 for a local name.
     */
    @Override
    @UML(identifier="depth", obligation=MANDATORY, specification=ISO_19103)
    default int depth() {
        return 1;
    }

    /**
     * Returns the sequence of local names. Since this object is itself a locale name,
     * this method always returns a {@linkplain List#of(Object) singleton list}
     * containing only {@code this}.
     *
     * @return a singleton containing only {@code this}.
     */
    @Override
    @UML(identifier="parsedName", obligation=MANDATORY, specification=ISO_19103)
    default List<? extends LocalName> getParsedNames() {
        return List.of(this);
    }

    /**
     * Returns {@code this} since this object is already a local name.
     */
    @Override
    default LocalName head() {
        return this;
    }

    /**
     * Returns {@code this} since this object is already a local name.
     */
    @Override
    default LocalName tip() {
        return this;
    }

    /**
     * Returns a locale-independent string representation of this local name.
     *
     * @return the local-independent string representation of this name.
     */
    @Override
    @UML(identifier="aName", obligation=MANDATORY, specification=ISO_19103)
    String toString();
}
