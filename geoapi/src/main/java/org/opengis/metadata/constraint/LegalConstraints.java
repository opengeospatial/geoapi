/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    http://www.geoapi.org
 *
 *    Copyright (C) 2004-2021 Open Geospatial Consortium, Inc.
 *    All Rights Reserved. http://www.opengeospatial.org/ogc/legal
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
package org.opengis.metadata.constraint;

import java.util.Collection;
import java.util.Collections;
import org.opengis.util.InternationalString;
import org.opengis.annotation.UML;

import static org.opengis.annotation.Obligation.*;
import static org.opengis.annotation.Specification.*;


/**
 * Restrictions and legal prerequisites for accessing and using the resource.
 *
 * <p><b>Conditional properties:</b></p>
 * All methods in this interface have default methods. But despite that, at least one of
 * {@linkplain #getAccessConstraints() access constraints},
 * {@linkplain #getUseConstraints() use constraints},
 * {@linkplain #getUseLimitations() use limitations},
 * {@linkplain #getOtherConstraints() other constraints} and
 * {@linkplain #getReleasability() releasibility} shall be provided.
 *
 * @author  Martin Desruisseaux (IRD)
 * @author  Cory Horner (Refractions Research)
 * @version 3.1
 * @since   2.0
 */
@UML(identifier="MD_LegalConstraints", specification=ISO_19115)
public interface LegalConstraints extends Constraints {
    /**
     * Access constraints applied to assure the protection of privacy or intellectual property,
     * and any special restrictions or limitations on obtaining the resource or metadata.
     *
     * @return access constraints applied to assure the protection of privacy or intellectual property.
     *
     * @condition Mandatory if
     * {@linkplain #getUseConstraints() use constraints},
     * {@linkplain #getOtherConstraints() other constraints},
     * {@linkplain #getUseLimitations() use limitations} and
     * {@linkplain #getReleasability() releasibility} are null or empty.
     */
    @UML(identifier="accessConstraints", obligation=CONDITIONAL, specification=ISO_19115)
    default Collection<Restriction> getAccessConstraints() {
        return Collections.emptyList();
    }

    /**
     * Constraints applied to assure the protection of privacy or intellectual property, and any
     * special restrictions or limitations or warnings on using the resource or metadata.
     *
     * @return constraints applied to assure the protection of privacy or intellectual property.
     *
     * @condition Mandatory if
     * {@linkplain #getAccessConstraints() access constraints},
     * {@linkplain #getOtherConstraints() other constraints},
     * {@linkplain #getUseLimitations() use limitations} and
     * {@linkplain #getReleasability() releasibility} are null or empty.
     */
    @UML(identifier="useConstraints", obligation=CONDITIONAL, specification=ISO_19115)
    default Collection<Restriction> getUseConstraints() {
        return Collections.emptyList();
    }

    /**
     * Other restrictions and legal prerequisites for accessing and using the resource or metadata.
     *
     * @return other restrictions and legal prerequisites for accessing and using the resource.
     *
     * @condition Mandatory if the {@linkplain #getAccessConstraints() access constraints} or
     *            {@linkplain #getUseConstraints() use constraints} contain {@link Restriction#OTHER_RESTRICTIONS}.
     */
    @UML(identifier="otherConstraints", obligation=CONDITIONAL, specification=ISO_19115)
    default Collection<? extends InternationalString> getOtherConstraints() {
        return Collections.emptyList();
    }
}
