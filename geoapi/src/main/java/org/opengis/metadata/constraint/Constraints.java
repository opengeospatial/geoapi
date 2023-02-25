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
import org.opengis.metadata.citation.Citation;
import org.opengis.metadata.citation.Responsibility;
import org.opengis.metadata.identification.BrowseGraphic;
import org.opengis.metadata.maintenance.Scope;
import org.opengis.annotation.UML;

import static org.opengis.annotation.Obligation.*;
import static org.opengis.annotation.Specification.*;


/**
 * Restrictions on the access and use of a resource or metadata.
 *
 * @author  Martin Desruisseaux (IRD)
 * @author  Rémi Maréchal (Geomatys)
 * @version 3.1
 * @since   2.0
 */
@UML(identifier="MD_Constraints", specification=ISO_19115)
public interface Constraints {
    /**
     * Limitation affecting the fitness for use of the resource.
     * Returns an empty collection if none.
     *
     * <div class="note"><b>Example:</b> not to be used for navigation.</div>
     *
     * @return limitation affecting the fitness for use of the resource.
     */
    @UML(identifier="useLimitation", obligation=OPTIONAL, specification=ISO_19115)
    default Collection<? extends InternationalString> getUseLimitations() {
        return Collections.emptyList();
    }

    /**
     * Spatial and / or temporal extents and or levels of the application of the constraints restrictions.
     *
     * @return extents or levels of the application of the constraints restrictions, or {@code null} if none.
     *
     * @since 3.1
     */
    @UML(identifier="constraintApplicationScope", obligation=OPTIONAL, specification=ISO_19115)
    default Scope getConstraintApplicationScope() {
        return null;
    }

    /**
     * Graphics / symbols indicating the constraint.
     * Returns an empty collection if none.
     *
     * @return graphics or symbols indicating the constraint.
     *
     * @since 3.1
     */
    @UML(identifier="graphic", obligation=OPTIONAL, specification=ISO_19115)
    default Collection<? extends BrowseGraphic> getGraphics() {
        return Collections.emptyList();
    }

    /**
     * Citations for the limitation of constraint.
     * Returns an empty collection if none.
     *
     * <div class="note"><b>Example:</b> copyright statement, license agreement, <i>etc</i>.</div>
     *
     * @return citations for the limitation of constraint, or {@code null} if none.
     *
     * @since 3.1
     */
    @UML(identifier="reference", obligation=OPTIONAL, specification=ISO_19115)
    default Collection<? extends Citation> getReferences() {
        return Collections.emptyList();
    }

    /**
     * Information concerning the parties to whom the resource can or cannot be released.
     *
     * @return information concerning the parties to whom the resource can or cannot be released, or {@code null} if none.
     *
     * @since 3.1
     */
    @UML(identifier="releasability", obligation=OPTIONAL, specification=ISO_19115)
    default Releasability getReleasability() {
        return null;
    }

    /**
     * Parties responsible for the resource constraints.
     * Returns an empty collection if none.
     *
     * @return parties responsible for the resource constraints.
     *
     * @since 3.1
     */
    @UML(identifier="responsibleParty", obligation=OPTIONAL, specification=ISO_19115)
    default Collection<? extends Responsibility> getResponsibleParties() {
        return Collections.emptyList();
    }
}
