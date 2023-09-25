/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    Copyright © 2023 Open Geospatial Consortium, Inc.
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

import java.util.Objects;
import java.util.Collection;
import org.opengis.metadata.extent.Extent;
import org.opengis.referencing.IdentifiedObject;
import org.opengis.referencing.ObjectDomain;
import org.opengis.util.InternationalString;


/**
 * Provides replacements for deprecated methods from legacy OGC/ISO specifications.
 * Methods in this class will be deleted when the corresponding deprecated methods
 * are removed from public API.
 *
 * @author  Martin Desruisseaux (Geomatys)
 * @version 3.1
 * @since   3.1
 */
public final class Legacy {
    /**
     * Do not allow instantiation of this class.
     */
    private Legacy() {
    }

    /**
     * Returns the first non-null scope found in the given collection.
     *
     * @param  domains  the value of {@link IdentifiedObject#getDomains()}.
     * @return a description of domain of usage, or {@code null} if none.
     */
    public static InternationalString getScope(final Collection<ObjectDomain> domains) {
        if (domains == null) return null;
        return domains.stream().map(ObjectDomain::getScope).filter(Objects::nonNull).findFirst().orElse(null);
    }

    /**
     * Returns the first non-null domain of validity found in the given collection.
     *
     * @param  domains  the value of {@link IdentifiedObject#getDomains()}.
     * @return the valid domain, or {@code null} if not available.
     */
    public static Extent getDomainOfValidity(final Collection<ObjectDomain> domains) {
        if (domains == null) return null;
        return domains.stream().map(ObjectDomain::getDomainOfValidity).filter(Objects::nonNull).findFirst().orElse(null);
    }
}
