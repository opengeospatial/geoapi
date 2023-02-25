/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    Copyright © 2004-2011 Open Geospatial Consortium, Inc.
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

import org.opengis.metadata.citation.Citation;


/**
 * Base interface for all factories. Factories can be grouped in two categories:
 * <p>
 * <UL>
 *   <LI>{@linkplain org.opengis.referencing.AuthorityFactory Authority factories}
 *       creates objects from a compact string defined by an authority.</LI>
 *   <LI>{@linkplain org.opengis.referencing.ObjectFactory Object factories}
 *       allows applications to make objects that cannot be created by an authority
 *       factory. This factory is very flexible, whereas the authority factory is
 *       easier to use.</LI>
 * </UL>
 *
 * @departure harmonization
 *   This interface is not part of the OGC specification. It is added for uniformity,
 *   in order to provide a common base class for all factories.
 *
 * @author  Martin Desruisseaux (IRD)
 * @version 3.0
 * @since   1.0
 */
public interface Factory {
    /**
     * Returns the vendor responsible for creating this factory implementation. Many implementations
     * may be available for the same factory interface. Implementations can be managed by a
     * {@linkplain java.util.ServiceLoader service loader}.
     *
     * @return The vendor for this factory implementation.
     */
    Citation getVendor();
}
