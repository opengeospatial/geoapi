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
package org.opengis.metadata.identification;

import org.opengis.annotation.UML;
import org.opengis.util.ControlledVocabulary;

import static org.opengis.annotation.Obligation.*;
import static org.opengis.annotation.Specification.*;


/**
 * High-level geographic data thematic classification to assist in the grouping and
 * search of available geographic data sets. Can be used to group keywords as well.
 * Listed examples are not exhaustive.
 *
 * <div class="note"><b>Note:</b>
 * it is understood there are overlaps between general categories and the user
 * is encouraged to select the one most appropriate.</div>
 *
 * @author  Martin Desruisseaux (IRD)
 * @author  Rémi Maréchal (Geomatys)
 * @version 4.0
 * @since   2.0
 */
@UML(identifier="MD_TopicCategoryCode", specification=ISO_19115)
public enum TopicCategory implements ControlledVocabulary {
    /**
     * Rearing of animals and/or cultivation of plants.
     *
     * <p><b>Examples:</b>
     * agriculture, irrigation, aquaculture, plantations, herding, pests and
     * diseases affecting crops and livestock.
     * </p>
     */
    @UML(identifier="farming", obligation=CONDITIONAL, specification=ISO_19115)
    FARMING("farming"),

    /**
     * Flora and/or fauna in natural environment.
     *
     * <p><b>Examples:</b>
     * wildlife, vegetation, biological sciences, ecology, wilderness, sealife, wetlands, habitat
     * </p>
     */
    @UML(identifier="biota", obligation=CONDITIONAL, specification=ISO_19115)
    BIOTA("biota"),

    /**
     * Legal land descriptions.
     *
     * <p><b>Examples:</b>
     * political and administrative boundaries.
     * </p>
     */
    @UML(identifier="boundaries", obligation=CONDITIONAL, specification=ISO_19115)
    BOUNDARIES("boundaries"),

    /**
     * Processes and phenomena of the atmosphere.
     *
     * <p><b>Examples:</b>
     * cloud cover, weather, climate, atmospheric conditions, climate change, precipitation.
     * </p>
     */
    @UML(identifier="climatologyMeteorologyAtmosphere", obligation=CONDITIONAL, specification=ISO_19115)
    CLIMATOLOGY_METEOROLOGY_ATMOSPHERE("climatologyMeteorologyAtmosphere"),

    /**
     * Economic activities, conditions and employment.
     *
     * <p><b>Examples:</b>
     * production, labour, revenue, commerce, industry, tourism and ecotourism,
     * forestry, fisheries, commercial or subsistence hunting,
     * exploration and exploitation of resources such as minerals, oil and gas.
     * </p>
     */
    @UML(identifier="economy", obligation=CONDITIONAL, specification=ISO_19115)
    ECONOMY("economy"),

    /**
     * Height above or below sea level.
     *
     * <p><b>Examples:</b>
     * altitude, bathymetry, digital elevation models, slope, derived products.
     * </p>
     */
    @UML(identifier="elevation", obligation=CONDITIONAL, specification=ISO_19115)
    ELEVATION("elevation"),

    /**
     * Environmental resources, protection and conservation.
     *
     * <p><b>Examples:</b>
     * environmental pollution, waste storage and treatment, environmental impact assessment,
     * monitoring environmental risk, nature reserves, landscape.
     * </p>
     */
    @UML(identifier="environment", obligation=CONDITIONAL, specification=ISO_19115)
    ENVIRONMENT("environment"),

    /**
     * Information pertaining to earth sciences.
     *
     * <p><b>Examples:</b>
     * geophysical features and processes, geology, minerals, sciences dealing with the composition,
     * structure and origin of the earth's rocks, risks of earthquakes, volcanic activity, landslides,
     * gravity information, soils, permafrost, hydrogeology, erosion.
     * </p>
     */
    @UML(identifier="geoscientificInformation", obligation=CONDITIONAL, specification=ISO_19115)
    GEOSCIENTIFIC_INFORMATION("geoscientificInformation"),

    /**
     * Health, health services, human ecology, and safety.
     *
     * <p><b>Examples:</b>
     * disease and illness, factors affecting health, hygiene, substance abuse,
     * mental and physical health, health services.
     * </p>
     */
    @UML(identifier="health", obligation=CONDITIONAL, specification=ISO_19115)
    HEALTH("health"),

    /**
     * Base maps.
     *
     * <p><b>Examples:</b>
     * land cover, topographic maps, imagery, unclassified images, annotations.
     * </p>
     */
    @UML(identifier="imageryBaseMapsEarthCover", obligation=CONDITIONAL, specification=ISO_19115)
    IMAGERY_BASE_MAPS_EARTH_COVER("imageryBaseMapsEarthCover"),

    /**
     * Military bases, structures, activities.
     *
     * <p><b>Examples:</b>
     * barracks, training grounds, military transportation, information collection.
     * </p>
     */
    @UML(identifier="intelligenceMilitary", obligation=CONDITIONAL, specification=ISO_19115)
    INTELLIGENCE_MILITARY("intelligenceMilitary"),

    /**
     * Inland water features, drainage systems and their characteristics.
     *
     * <p><b>Examples:</b>
     * rivers and glaciers, salt lakes, water utilization plans, dams, currents,
     * floods, water quality, hydrographic charts.
     * </p>
     */
    @UML(identifier="inlandWaters", obligation=CONDITIONAL, specification=ISO_19115)
    INLAND_WATERS("inlandWaters"),

    /**
     * Positional information and services.
     *
     * <p><b>Examples:</b>
     * addresses, geodetic networks, control points, postal zones and services, place names.
     * </p>
     */
    @UML(identifier="location", obligation=CONDITIONAL, specification=ISO_19115)
    LOCATION("location"),

    /**
     * Features and characteristics of salt water bodies (excluding inland waters).
     *
     * <p><b>Examples:</b>
     * tides, tidal waves, coastal information, reefs.
     * </p>
     */
    @UML(identifier="oceans", obligation=CONDITIONAL, specification=ISO_19115)
    OCEANS("oceans"),

    /**
     * Information used for appropriate actions for future use of the land.
     *
     * <p><b>Examples:</b>
     * land use maps, zoning maps, cadastral surveys, land ownership.
     * </p>
     */
    @UML(identifier="planningCadastre", obligation=CONDITIONAL, specification=ISO_19115)
    PLANNING_CADASTRE("planningCadastre"),

    /**
     * Characteristics of society and cultures.
     *
     * <p><b>Examples:</b>
     * settlements, anthropology, archaeology, education, traditional beliefs, manners and customs,
     * demographic data, recreational areas and activities, social impact assessments,
     * crime and justice, census information
     * </p>
     */
    @UML(identifier="society", obligation=CONDITIONAL, specification=ISO_19115)
    SOCIETY("society"),

    /**
     * Man-made construction.
     *
     * <p><b>Examples:</b>
     * buildings, museums, churches, factories, housing, monuments, shops, towers.
     * </p>
     */
    @UML(identifier="structure", obligation=CONDITIONAL, specification=ISO_19115)
    STRUCTURE("structure"),

    /**
     * Means and aids for conveying persons and/or goods.
     *
     * <p><b>Examples:</b>
     * roads, airports/airstrips, shipping routes, tunnels, nautical charts,
     * vehicle or vessel location, aeronautical charts, railways.
     * </p>
     */
    @UML(identifier="transportation", obligation=CONDITIONAL, specification=ISO_19115)
    TRANSPORTATION("transportation"),

    /**
     * Energy, water and waste systems and communications infrastructure and services.
     *
     * <p><b>Examples:</b>
     * hydroelectricity, geothermal, solar and nuclear sources of energy, water purification and
     * distribution, sewage collection and disposal, electricity and gas distribution,
     * data communication, telecommunication, radio, communication networks.
     * </p>
     */
    @UML(identifier="utilitiesCommunication", obligation=CONDITIONAL, specification=ISO_19115)
    UTILITIES_COMMUNICATION("utilitiesCommunication"),

    /**
     * Region more than 100 Km above the surface of the Earth.
     *
     * @since 3.1
     */
    @UML(identifier="extraTerrestrial", obligation=CONDITIONAL, specification=ISO_19115)
    EXTRA_TERRESTRIAL("extraTerrestrial"),

    /**
     * Information related to disaster.
     *
     * <p><b>Examples:</b>
     * site of the disaster, evacuation zone, disaster prevention facility,
     * disaster relief activity.</p>
     *
     * @since 3.1
     */
    @UML(identifier="disaster", obligation=CONDITIONAL, specification=ISO_19115)
    DISASTER("disaster");

    /**
     * The UML identifier.
     */
    private final String identifier;

    /**
     * Creates a new constant with the given UML identifier.
     *
     * @param identifier  the UML identifier.
     */
    private TopicCategory(final String identifier) {
        this.identifier = identifier;
    }

    /**
     * Returns the UML identifier for this enumeration constant.
     */
    @Override
    public String identifier() {
        return identifier;
    }

    /**
     * Returns the programmatic name of this constant together with its {@linkplain #identifier() identifier}.
     */
    @Override
    public String[] names() {
        return new String[] {name(), identifier};
    }

    /**
     * Returns all constants defined by this enumeration type.
     * Invoking this method is equivalent to invoking {@link #values()}, except that this
     * method can be invoked on an instance of the {@code ControlledVocabulary} interface
     * (i.e. the enumeration type does not need to be known at compile-time).
     *
     * @return all {@linkplain #values() values} for this enumeration.
     */
    @Override
    public TopicCategory[] family() {
        return values();
    }
}
