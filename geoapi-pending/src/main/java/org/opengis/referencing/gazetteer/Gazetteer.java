/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    Copyright © 2016-2023 Open Geospatial Consortium, Inc.
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
import org.opengis.util.InternationalString;
import org.opengis.metadata.Identifier;
import org.opengis.metadata.citation.Party;
import org.opengis.metadata.extent.GeographicExtent;
import org.opengis.referencing.crs.CoordinateReferenceSystem;
import org.opengis.annotation.UML;

import static org.opengis.annotation.Obligation.*;
import static org.opengis.annotation.Specification.*;


/**
 * A directory of geographic identifiers describing location instances.
 * Gazetteers contain additional information regarding the position of each location instance.
 * It may include a coordinate reference, but it may also be purely descriptive.
 * If it contains a coordinate reference, this will enable transformation from the spatial reference system
 * using geographic identifiers to the coordinate reference system.
 * If it contains a descriptive reference, this will be a spatial reference using a different spatial reference system
 * with geographic identifiers, for example the postcode of a property.
 * For any location type, there may be more than one gazetteer.
 *
 * @author  Martin Desruisseaux (Geomatys)
 * @version 3.1
 * @since   3.1
 */
@UML(identifier="SI_Gazetteer", specification=ISO_19112)
public interface Gazetteer {
    /**
     * Name of the gazetteer.
     * Version date of the gazetteer is included in the name.
     *
     * @departure harmonization
     *   ISO 19112 defines this property as a <code>CharacterString</code> (free text). GeoAPI changes the type
     *   to <code>Identifier</code> for consistency with <code>ReferenceSystemUsingIdentifiers.name</code> and
     *   for providing a specific place where to store the version information.
     *
     * @return name of the gazetteer, including version date.
     */
    @UML(identifier="identifier", obligation=MANDATORY, specification=ISO_19112)
    Identifier getIdentifier();

    /**
     * Description of the location types contained in the gazetteer.
     *
     * <div class="note"><b>Examples:</b>
     * “streets of London”, “rivers of North America”.</div>
     *
     * @return description of the location types contained in the gazetteer, or {@code null} if none.
     *
     * @see ReferenceSystemUsingIdentifiers#getScope()
     */
    @UML(identifier="scope", obligation=OPTIONAL, specification=ISO_19112)
    InternationalString getScope();

    /**
     * Geographic domain covered by the gazetteer.
     *
     * <div class="note"><b>Examples:</b>
     * “North America” for the geographic domain of a gazetteer of rivers,
     * and “London” for a gazetteer of streets.</div>
     *
     * @return geographic domain covered by the gazetteer.
     *
     * @see LocationType#getTerritoryOfUse()
     * @see ReferenceSystemUsingIdentifiers#getDomainOfValidity()
     */
    @UML(identifier="territoryOfUse", obligation=MANDATORY, specification=ISO_19112)
    GeographicExtent getTerritoryOfUse();

    /**
     * Name of the organization responsible for maintenance of the gazetteer.
     *
     * @return organization responsible for maintenance of the gazetteer.
     *
     * @see Location#getAdministrator()
     * @see LocationType#getOwner()
     * @see ReferenceSystemUsingIdentifiers#getOverallOwner()
     */
    @UML(identifier="custodian", obligation=MANDATORY, specification=ISO_19112)
    Party getCustodian();

    /**
     * Coordinate reference system used in the gazetteer for describing position.
     *
     * @departure rename
     *   Renamed <code>coordinateSystem</code> as <code>coordinateReferenceSystem</code>
     *   for consistency with the type and usage in the rest of GeoAPI.
     *
     * @return coordinate reference system used in the gazetteer, or {@code null} if none.
     */
    @UML(identifier="coordinateSystem", obligation=OPTIONAL, specification=ISO_19112)
    CoordinateReferenceSystem getCoordinateReferenceSystem();

    /**
     * Description of location type(s) for which instances are recorded in the gazetteer.
     *
     * @return description of location type(s) for which instances are recorded in the gazetteer.
     *
     * @see ReferenceSystemUsingIdentifiers#getLocationTypes()
     */
    @UML(identifier="locationType", obligation=MANDATORY, specification=ISO_19112)
    Collection<? extends LocationType> getLocationTypes();
}
