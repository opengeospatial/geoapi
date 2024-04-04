/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    Copyright © 2017-2024 Open Geospatial Consortium, Inc.
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
package org.opengis.referencing.gazetteer;

import java.util.Collection;
import org.opengis.metadata.Identifier;
import org.opengis.metadata.extent.Extent;
import org.opengis.metadata.citation.Party;
import org.opengis.util.InternationalString;
import org.opengis.referencing.ReferenceSystem;
import org.opengis.referencing.ReferenceIdentifier;
import org.opengis.annotation.UML;

import static org.opengis.annotation.Obligation.*;
import static org.opengis.annotation.Specification.*;


/**
 * Spatial reference system using geographic identifiers instead of coordinates for describing locations.
 * A spatial reference system using geographic identifiers shall comprise at least one location types.
 * Each location instance shall be uniquely identified by means of a geographic identifier.
 * A spatial reference system using geographic identifiers shall be minimally described by the following attributes:
 *
 * <ul>
 *   <li>name</li>
 *   <li>theme</li>
 *   <li>overall owner</li>
 *   <li>territory of use</li>
 * </ul>
 *
 * Spatial reference systems shall be immutable: a new version of the spatial reference system shall be created
 * whenever any {@link LocationType} is created or destroyed, or a new version of a {@code LocationType} is created.
 * The version reference is included in the {@linkplain #getName() name} of the spatial reference system.
 *
 * @departure rename
 *   The name has been shortened by omitting the <code>Spatial</code> and <code>Geographic</code> parts of the name.
 *   This is consistent with the change of <code>getDomainOfValidity()</code> return type, which had to be relaxed
 *   from geographic extent to more generic extent.
 *
 * @author  Martin Desruisseaux (Geomatys)
 * @version 3.1
 * @since   3.1
 */
@UML(identifier="SI_SpatialReferenceSystemUsingGeographicIdentifiers", specification=ISO_19112)
public interface ReferenceSystemUsingIdentifiers extends ReferenceSystem {
    /**
     * Key for the <code>{@value}</code> property to be given to the
     * object factory {@code createFoo(…)} methods.
     * This is used for setting the value to be returned by {@link #getTheme()}.
     *
     * @see #getTheme()
     */
    String THEME_KEY = "theme";

    /**
     * Key for the <code>{@value}</code> property to be given to the
     * object factory {@code createFoo(…)} methods.
     * This is used for setting the value to be returned by {@link #getOverallOwner()}.
     *
     * @see #getOverallOwner()
     */
    String OVERALL_OWNER_KEY = "overallOwner";

    /**
     * Identifier of the spatial reference system.
     *
     * @departure constraint
     *   ISO 19112 defines <code>name</code> as a <code>CharacterString</code>. But ISO 19111,
     *   which defines the parent type, defines <code>name</code> as a <code>MD_Identifier</code>.
     *   The type in this sub-interface had to be changed to the same type as the parent interface.
     *   Furthermore, ISO 19112 said that a version reference can be included in the name,
     *   in which case <code>Identifier.getVersion()</code> can be used.
     *
     * @return identifier of the spatial reference system.
     */
    @Override
    @UML(identifier="name", obligation=MANDATORY, specification=ISO_19112)
    ReferenceIdentifier getName();

    /**
     * Geographic area within which the reference system occurs.
     *
     * @departure constraint
     *   ISO 19112 defines <code>domainOfValidity</code> as an <code>EX_GeographicExtent</code>.
     *   But ISO 19111, which defines the parent type, defines <code>domainOfValidity</code> as an
     *   <code>EX_Extent</code>. The type in this sub-interface had to be changed to the same type
     *   than the parent interface.
     *
     * @return the reference system valid domain, or {@code null} if not available.
     *
     * @see LocationType#getTerritoryOfUse()
     * @see Gazetteer#getTerritoryOfUse()
     *
     * @deprecated Replaced by {@link #getDomains()} as of ISO 19111:2019.
     */
    @Override
    @Deprecated(since = "3.1")
    @UML(identifier="domainOfValidity", obligation=MANDATORY, specification=ISO_19112)
    default Extent getDomainOfValidity() {
        return ReferenceSystem.super.getDomainOfValidity();
    }

    /**
     * Property used to characterize the spatial reference system.
     *
     * @return property used to characterize the spatial reference system.
     *
     * @see LocationType#getTheme()
     */
    @UML(identifier="theme", obligation=MANDATORY, specification=ISO_19112)
    InternationalString getTheme();

    /**
     * Authority with overall responsibility for the spatial reference system.
     *
     * @return authority with overall responsibility for the spatial reference system.
     *
     * @see LocationType#getOwner()
     * @see Gazetteer#getCustodian()
     * @see Location#getAdministrator()
     */
    @UML(identifier="overallOwner", obligation=MANDATORY, specification=ISO_19112)
    Party getOverallOwner();

    /**
     * Description of location type(s) in the spatial reference system.
     *
     * @return description of location type(s) in the spatial reference system.
     *
     * @see Gazetteer#getLocationTypes()
     * @see LocationType#getReferenceSystem()
     */
    @UML(identifier="locationType", obligation=MANDATORY, specification=ISO_19112)
    Collection<? extends LocationType> getLocationTypes();
}
