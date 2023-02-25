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
import org.opengis.metadata.extent.GeographicExtent;
import org.opengis.metadata.citation.Party;
import org.opengis.annotation.UML;

import static org.opengis.annotation.Obligation.*;
import static org.opengis.annotation.Specification.*;


/**
 * Description of the nature (type) of a geographic identifier.
 * Some of the mandatory properties are:
 * <ul>
 *   <li><b>name</b> (the name of the location type)</li>
 *   <li><b>identification</b> (the nature of the geographic identifier, for example name or code)</li>
 *   <li><b>definition</b> (the meaning of the location type)</li>
 *   <li><b>territory of use</b> (geographic area within which the location type occurs)</li>
 *   <li><b>owner</b> (who is responsible for this location type)</li>
 * </ul>
 *
 * <p>Location types shall be immutable:
 * a new version of the location type shall be created whenever any change occurs to any of its attributes.</p>
 *
 * @author  Martin Desruisseaux (Geomatys)
 * @version 3.1
 * @since   3.1
 *
 * @see Location
 */
@UML(identifier="SI_LocationType", specification=ISO_19112)
public interface LocationType {
    /**
     * Name of the location type.
     *
     * <div class="note"><b>Examples:</b>
     * “administrative area”, “town”, “locality”, “street”, “property”.</div>
     *
     * @return name of the location type.
     */
    @UML(identifier="name", obligation=MANDATORY, specification=ISO_19112)
    InternationalString getName();

    /**
     * Property used as the defining characteristic of the location type.
     *
     * <div class="note"><b>Examples:</b>
     * <cite>“local administration”</cite> for administrative areas,
     * <cite>“built environment”</cite> for towns or properties,
     * <cite>“access”</cite> for streets,
     * <cite>“electoral”</cite>,
     * <cite>“postal”</cite>.</div>
     *
     * @return property used as the defining characteristic of the location type.
     *
     * @see ReferenceSystemUsingIdentifiers#getTheme()
     */
    @UML(identifier="theme", obligation=MANDATORY, specification=ISO_19112)
    InternationalString getTheme();

    /**
     * Method(s) of uniquely identifying location instances.
     *
     * <div class="note"><b>Examples:</b>
     * “name”, “code”, “unique street reference number”, “geographic address”.
     * A location using “name” identifications may have the “Spain” {@linkplain Location#getGeographicIdentifier()
     * geographic identifier}, and a location using “postcode” identifications may have the “SW1P 3AD” geographic
     * identifier.
     * </div>
     *
     * @return method(s) of uniquely identifying location instances.
     *
     * @see Location#getGeographicIdentifier()
     */
    @UML(identifier="identification", obligation=MANDATORY, specification=ISO_19112)
    Collection<? extends InternationalString> getIdentifications();

    /**
     * The way in which location instances are defined.
     * The definition of the location type shall be in the form of one of the following:
     *
     * <ul>
     *   <li>an area defined by a set of boundaries, for example countries defined by their borders;</li>
     *   <li>a single feature, for example a street defined by its centre line, or a junction of two such streets;</li>
     *   <li>a collection of smaller features, for example trade areas defined by groups of countries.</li>
     * </ul>
     *
     * Where a location type is defined as a collection of smaller units, an instance of that location need not
     * have a well-defined boundary, for example a postal code defined as a collection of postal delivery points.
     *
     * @return the way in which location instances are defined.
     */
    @UML(identifier="definition", obligation=MANDATORY, specification=ISO_19112)
    InternationalString getDefinition();

    /**
     * Geographic area within which the location type occurs.
     *
     * <div class="note"><b>Examples:</b>
     * the geographic domain for a location type “rivers” might be “North America”.</div>
     *
     * @return geographic area within which the location type occurs.
     *
     * @see Gazetteer#getTerritoryOfUse()
     * @see ReferenceSystemUsingIdentifiers#getDomainOfValidity()
     */
    @UML(identifier="territoryOfUse", obligation=MANDATORY, specification=ISO_19112)
    GeographicExtent getTerritoryOfUse();

    /**
     * The reference system that comprises this location type.
     *
     * @return the reference system that comprises this location type.
     *
     * @see ReferenceSystemUsingIdentifiers#getLocationTypes()
     */
    @UML(identifier="referenceSystem", obligation=MANDATORY, specification=ISO_19112)
    ReferenceSystemUsingIdentifiers getReferenceSystem();

    /**
     * Name of organization or class of organization able to create and destroy location instances.
     *
     * @return organization or class of organization able to create and destroy location instances.
     *
     * @see Location#getAdministrator()
     * @see Gazetteer#getCustodian()
     * @see ReferenceSystemUsingIdentifiers#getOverallOwner()
     */
    @UML(identifier="owner", obligation=MANDATORY, specification=ISO_19112)
    Party getOwner();

    /**
     * Parent location types (location types of which this location type is a sub-division).
     * A location type can have more than one possible parent. For example, the parent of a
     * location type named <cite>“street”</cite> could be <cite>“locality”</cite>, <cite>“town”</cite>
     * or <cite>“administrative area”</cite>.
     *
     * @return parent location types, or an empty collection if none.
     *
     * @see Location#getParents()
     */
    @UML(identifier="parent", obligation=OPTIONAL, specification=ISO_19112)
    Collection<? extends LocationType> getParents();

    /**
     * Child location types (location types which sub-divides this location type).
     *
     * @return child location types, or an empty collection if none.
     *
     * @see Location#getChildren()
     */
    @UML(identifier="child", obligation=OPTIONAL, specification=ISO_19112)
    Collection<? extends LocationType> getChildren();
}
