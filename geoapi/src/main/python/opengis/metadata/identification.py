# ===-----------------------------------------------------------------------===
#    GeoAPI - Python interfaces (abstractions) for OGC/ISO standards
#    Copyright © 2013-2024 Open Geospatial Consortium, Inc.
#    http: //www.geoapi.org
#
#    Licensed under the Apache License, Version 2.0 (the "License");
#    you may not use this file except in compliance with the License.
#    You may obtain a copy of the License at
#
#        http: //www.apache.org/licenses/LICENSE-2.0
#
#    Unless required by applicable law or agreed to in writing, software
#    distributed under the License is distributed on an "AS IS" BASIS,
#    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
#    See the License for the specific language governing permissions and
#    limitations under the License.
# ===-----------------------------------------------------------------------===
"""This is the `identification` module.

This module contains geographic metadata structures regarding identification
information codelists and common base classes derived from the
ISO 19115-1:2014 international standard.
"""

__author__ = "OGC Topic 11 (for abstract model and documentation), " +\
    "Martin Desruisseaux (Geomatys), David Meaux (Geomatys)"

from abc import ABC, abstractmethod
from collections.abc import Sequence
from datetime import datetime, timedelta
from enum import Enum
from typing import Optional

from opengis.metadata.citation import (
    Citation,
    Identifier,
    OnlineResource,
    Responsibility,
)
from opengis.metadata.constraints import Constraints
from opengis.metadata.distribution import Format
from opengis.metadata.extent import Extent
from opengis.metadata.maintenance import MaintenanceInformation
from opengis.metadata.representation import SpatialRepresentationTypeCode
from opengis.util.measure import Angle, Distance


class AssociationTypeCode(Enum):
    """Justification for the correlation of two resources."""

    CROSS_REFERENCE = "crossReference"
    """Reference from one resource to another."""

    LARGER_WORK_CITATION = "largerWorkCitation"
    """Reference to a master resource of which this one is a part."""

    PART_OF_SEAMLESS_DATABASE = "partOfSeamlessDatabase"
    """Part of same structured set of data held in a computer."""

    # deprecated Removed in ISO 19115:2014.
    # SOURCE = "source"
    # """
    # Mapping and charting information from which the dataset content
    # originates.
    # """

    STEREO_MATE = "stereoMate"
    """
    Part of a set of imagery that when used together, provides
    three-dimensional images.
    """

    IS_COMPOSED_OF = "isComposedOf"
    """Reference to resources that are parts of this resource."""

    COLLECTIVE_TITLE = "collectiveTitle"
    """
    Common title for a collection of resources.

    NOTE: Title identifies elements of a series collectively, combined with
    information about what volumes are available at the source cite.
    """

    SERIES = "series"
    """
    Associated through a common heritage such as produced to a common product
    specification.
    """

    DEPENDENCY = "dependency"
    """Associated through a dependency."""

    REVISION_OF = "revisionOf"
    """Resource is a revision of associated resource."""


class InitiativeTypeCode(Enum):
    """Type of aggregation activity in which resources are related."""

    CAMPAIGN = "campaign"
    """Series of organized planned actions."""

    COLLECTION = "collection"
    """Accumulation of resources assembled for a specific purpose."""

    EXERCISE = "exercise"
    """Specific performance of a function or group of functions."""

    EXPERIMENT = "experiment"
    """Process designed to find if something is effective or valid."""

    INVESTIGATION = "investigation"
    """Search or systematic inquiry."""

    MISSION = "mission"
    """Specific operation of a data collection system."""

    SENSOR = "sensor"
    """Device or piece of equipment which detects or records."""

    OPERATION = "operation"
    """Action that is part of a series of actions."""

    PLATFORM = "platform"
    """Vehicle or other support base that holds a sensor."""

    PROCESS = "process"
    """Method of doing something involving a number of steps."""

    PROGRAM = "program"
    """Specific planned activity."""

    PROJECT = "project"
    """Organized undertaking, research, or development."""

    STUDY = "study"
    """Examination or investigation."""

    TASK = "task"
    """Piece of work."""

    TRIAL = "trial"
    """Process of testing to discover or demonstrate something."""


class KeywordTypeCode(Enum):
    """Methods used to group similar keywords."""

    DISCIPLINE = "discipline"
    """Keyword identifies a branch of instruction or specialized learning."""

    PLACE = "place"
    """Keyword identifies a location."""

    STRATUM = "stratum"
    """
    Keyword identifies the layer(s) of any deposited substance or levels
    within an ordered system.
    """

    TEMPORAL = "temporal"
    """Keyword identifies a time period related to the resource."""

    THEME = "theme"
    """Keyword identifies a particular subject or topic."""

    DATA_CENTRE = "dataCentre"
    """
    Keyword identifies a repository or archive that manages and
    distributes data.
    """

    FEATURE_TYPE = "featureType"
    """
    Keyword identifies a resource containing or about a collection of feature
    instances with common characteristics.
    """

    INSTRUMENT = "instrument"
    """
    Keyword identifies a device used to measure or compare physical properties.
    """

    PLATFORM = "platform"
    """Keyword identifies a structure upon which an instrument is mounted."""

    PROCESS = "process"
    """Keyword identifies a series of actions or natural occurrences."""

    PROJECT = "project"
    """
    Keyword identifies an endeavour undertaken to create or modify a product
    or service.
    """

    SERVICE = "service"
    """
    Keyword identifies an activity carried out by one party for the benefit
    of another.
    """

    PRODUCT = "product"
    """Keyword identifies a type of product."""

    SUB_TOPIC_CATEGORY = "subTopicCategory"
    """
    Refinement of a topic category for the purpose of geographic
    data classification.
    """

    TAXON = "taxon"
    """Keyword identifies a taxonomy of the resource."""


class ProgressCode(Enum):
    """Status of the resource."""

    COMPLETED = "completed"
    """Has been completed."""

    HISTORICAL_ARCHIVE = "historicalArchive"
    """Stored in an offline storage facility."""

    OBSOLETE = "obsolete"
    """No longer relevant."""

    ON_GOING = "onGoing"
    """Continually being updated."""

    PLANNED = "planned"
    """
    Fixed date has been established upon or by which the resource will be
    created or updated.
    """

    REQUIRED = "required"
    """Needs to be generated or updated."""

    UNDER_DEVELOPMENT = "underDevelopment"
    """Currently in the process of being created."""

    FINAL = "final"
    """Progress concluded and no changes will be accepted."""

    PENDING = "pending"
    """Committed to, but not yet addressed."""

    RETIRED = "retired"
    """
    Item is no longer recommended for use. It has not been superseded
    by another item.
    """

    SUPERSEDED = "superseded"
    """Replaced by new."""

    TENTATIVE = "tentative"
    """Provisional changes likely before resource becomes final or complete."""

    VALID = "valid"
    """Acceptable under specific conditions."""

    ACCEPTED = "accepted"
    """Agreed to by sponsor."""

    NOT_ACCEPTED = "notAccepted"
    """Rejected by sponsor."""

    WITHDRAWN = "withdrawn"
    """Removed from consideration."""

    PROPOSED = "proposed"
    """Suggested that development needs to be undertaken."""

    DEPRECATED = "deprecated"
    """
    Resource superseded and will become obsolete, use only
    for historical purposes.
    """


class TopicCategoryCode(Enum):
    """
    High-level geographic data thematic classification to assist in the
    grouping and search of available geographic datasets.

    NOTE 1: Can be used to group keywords as well. Listed examples are not
    exhaustive.

    NOTE 2: Is is understto there are overlaps between general categories and
    the user is encouraged to select the one most appropriate.
    """

    FARMING = "farming"
    """
    Rearing of animals and/or cultivation of plants.

    EXAMPLES: Agriculture, irrigation, aquaculture, plantations, herding,
    pests and diseases affecting crops and livestock.
    """

    BIOTA = "biota"
    """
    Flora and/or fauna in natural environment.

    EXAMPLES: Wildlife, vegetation, biological sciences, ecology, wilderness,
    sealife, wetlands, habitat.
    """

    BOUNDARIES = "boundaries"
    """
    Legal land descriptions, maritime boundaries.

    EXAMPLES: Political and administrative boundaries, territorial seas, EEZ,
    port security zones.
    """

    CLIMATOLOGY_METEOROLOGY_ATMOSPHERE = "climatologyMeteorologyAtmosphere"
    """
    Processes and phenomena of the atmosphere.

    EXAMPLES: Cloud cover, weather, climate, atmospheric conditions,
    climate change, precipitation.
    """

    ECONOMY = "economy"
    """
    Economic activities, conditions and employment.

    EXAMPLES: Production, labour, revenue, commerce, industry, tourism and
    ecotourism, forestry, fisheries, commercial or subsistence hunting,
    exploration and exploitation of resources such as minerals, oil and gas.
    """

    ELEVATION = "elevation"
    """
    Height above or below a vertical datum.

    EXAMPLES: Altitude, bathymetry, digital elevation models, slope,
    derived products.
    """

    ENVIRONMENT = "environment"
    """
    Environmental resources, protection and conservation.

    EXAMPLES: Environmental pollution, waste storage and treatment,
    environmental impact assessment, monitoring environmental risk,
    nature reserves, landscape.
    """

    GEOSCIENTIFIC_INFORMATION = "geoscientificInformation"
    """
    Information pertaining to earth sciences.

    EXAMPLES: Geophysical features and processes, geology, minerals,
    sciences dealing with the composition, structure and origin of the
    Earth's rocks, risks of earthquakes, volcanic activity, landslides,
    gravity information, soils, permafrost, hydrogeology, erosion.
    """

    HEALTH = "health"
    """
    Health, health services, human ecology, and safety.

    EXAMPLES: Disease and illness, factors affecting health, hygiene,
    substance abuse, mental and physical health, health services.
    """

    IMAGERY_BASE_MAPS_EARTH_COVER = "imageryBaseMapsEarthCover"
    """
    Base maps.

    EXAMPLES: Land cover, topographic maps, imagery, unclassified images,
    annotations.
    """

    INTELLIGENCE_MILITARY = "intelligenceMilitary"
    """
    Military bases, structures, activities.

    EXAMPLES: Barracks, training grounds, military transportation,
    information collection.
    """

    INLAND_WATERS = "inlandWaters"
    """
    Inland water features, drainage systems and their characteristics.

    EXAMPLES: Rivers and glaciers, salt lakes, water utilization plans, dams,
    currents, floods, water quality, hydrologic information.
    """

    LOCATION = "location"
    """
    Positional information and services.

    EXAMPLES: Addresses, geodetic networks, control points, postal zones and
    services, place names.
    """

    OCEANS = "oceans"
    """
    Features and characteristics of salt water bodies (excluding
    inland waters).

    EXAMPLES: Tides, tsunamis, coastal information, reefs.
    """

    PLANNING_CADASTRE = "planningCadastre"
    """
    Information used for appropriate actions for future use of the land.

    EXAMPLES: Land use maps, zoning maps, cadastral surveys, land ownership.
    """

    SOCIETY = "society"
    """
    Characteristics of society and cultures.

    EXAMPLES: Settlements, anthropology, archaeology, education,
    traditional beliefs, manners and customs, demographic data,
    recreational areas and activities, social impact assessments,
    crime and justice, census information.
    """

    STRUCTURE = "structure"
    """
    Man-made construction.

    EXAMPLES: Buildings, museums, churches, factories, housing, monuments,
    shops, towers.
    """

    TRANSPORTATION = "transportation"
    """
    Means and aids for conveying persons and/or goods.

    EXAMPLES: Roads, airports/airstrips, shipping routes, tunnels,
    nautical charts, vehicle or vessel location, aeronautical charts,
    railways.
    """

    UTILITIES_COMMUNICATION = "utilitiesCommunication"
    """
    Energy, water and waste systems and communications infrastructure
    and services.

    EXAMPLES: Hydroelectricity, geothermal, solar and nuclear sources
    of energy, water purification and distribution, sewage collection and
    disposal, electricity and gas distribution, data communication,
    telecommunication, radio, communication networks.
    """

    EXTRA_TERRESTRIAL = "extraTerrestrial"
    """Region more than 100 km above the surface of the Earth."""

    DISASTER = "disaster"
    """
    Information related to disasters.

    EXAMPLES: Site of the disaster, evacuation zone,
    disaster-prevention facility, disaster relief activities.
    """


class BrowseGraphic(ABC):
    """
    Graphic that provides an illustration of a resource/dataset

    NOTE: should include a legend for the graphic, if applicable.

    Example: A dataset, an organisation logo, security constraint, or citation
    graphic.
    """

    @property
    @abstractmethod
    def file_name(self) -> str:
        """
        Name of the file that contains a graphic that provides an illustration
        of the resource/dataset.
        """

    @property
    @abstractmethod
    def file_description(self) -> Optional[str]:
        """Text description of the illustration."""

    @property
    @abstractmethod
    def file_type(self) -> Optional[str]:
        """
        Format in which the illustration is encoded.

        Example: EPS, GIF, JPEG, PBM, PS, TIFF, PDF.
        """

    @property
    @abstractmethod
    def image_constraints(self) -> Optional[Sequence[Constraints]]:
        """Restriction on access and/or use of browse graphic."""

    @property
    @abstractmethod
    def linkage(self) -> Optional[Sequence[OnlineResource]]:
        """Link to browse graphic."""


class KeywordClass(ABC):
    """
    Specification of a class to categorize keywords in a domain-specific
    vocabulary that has a binding to a formal ontology.
    """

    @property
    @abstractmethod
    def class_name(self) -> str:
        """
        Character string to label the keyword category in natural language.
        """

    @property
    @abstractmethod
    def concept_identifier(self) -> Optional[str]:
        """
        URI (as a string) of concept in ontology specified by the ontology
        attribute; this concept is labeled by a `str`.
        """

    @property
    @abstractmethod
    def ontology(self) -> Citation:
        """
        A reference that binds the keyword class to a formal conceptualization
        of a knowledge domain for use in semantic processing NOTE: Keywords in
        the associated `Keywords` keyword list must be within the scope of
        this ontology.
        """


class Keywords(ABC):
    """
    Keywords, their type and reference source.

    NOTE: When the resource described is a service, one instance of `Keyword`
    shall refer to the service taxonomy defined in ISO 19119, 8.3).
    """

    @property
    @abstractmethod
    def keyword(self) -> Sequence[str]:
        """
        Commonly used word(s) or formalised word(s) or phrase(s) used to
        describe the subject.
        """

    @property
    @abstractmethod
    def type(self) -> Optional[KeywordTypeCode]:
        """Subject matter used to group similar keywords."""

    @property
    @abstractmethod
    def thesaurus_name(self) -> Optional[Citation]:
        """
        Name of the formally registered thesaurus or a similar authoritative
        source of keywords.
        """

    @property
    @abstractmethod
    def keyword_class(self) -> Optional[KeywordClass]:
        """
        Association of an `Keywords` instance with an `KeywordClass` to
        provide user-defined categorization of groups of keywords that extend
        or are orthogonal to the standardized KeywordTypeCodes and are
        associated with an ontology that allows additional semantic query
        processing.

        NOTE: The thesaurus citation specifies a collection of
        instances from some ontology, but is not an ontology. It might be a
        list of places that include rivers, mountains, counties and cities.
        There might be a Laconte County, the city Laconte, the Laconte River,
        and Mt. Laconte; when searching it is useful for the user to be able to
        restrict the search to rivers only.
        """


class Usage(ABC):
    """
    Brief description of ways in which the resource(s) is/are currently or has
    been used.
    """

    @property
    @abstractmethod
    def specific_usage(self) -> str:
        """Brief description of the resource and/or resource series usage."""

    @property
    @abstractmethod
    def usage_date_time(self) -> Optional[Sequence[datetime]]:
        """
        Date and time of the first use or range of uses of the resource and/or
        resource series.
        """

    @property
    @abstractmethod
    def user_determined_limitations(self) -> str:
        """
        Applications, determined by the user for which the resource and/or
        resource series is not suitable.
        """

    @property
    @abstractmethod
    def user_contact_info(self) -> Optional[Sequence[Responsibility]]:
        """
        Identification of and means of communicating with person(s) and
        organisation(s) using the resource(s).
        """

    @property
    @abstractmethod
    def response(self) -> Optional[Sequence[str]]:
        """
        Response to the user-determined limitations, e.g., 'This has been fixed
        in version x.'
        """

    @property
    @abstractmethod
    def additional_documentation(self) -> Optional[Sequence[Citation]]:
        """Publications that describe usage of data."""

    @property
    @abstractmethod
    def identified_issues(self) -> Optional[Sequence[Citation]]:
        """
        Citation of a description of known issues associated with the resource
        along with proposed solutions if available.
        """


class RepresentativeFraction(ABC):
    """
    Derived from ISO 19103 Scale where
    `denominator` = 1 / `Scale.measure`
    and `Scale.target_units` = `Scale.source_units`.
    """

    @property
    @abstractmethod
    def denominator(self) -> int:
        """
        The number below the line in a vulgar fraction.

        Domain: > 0
        """


class Resolution(ABC):
    """Level of detail expressed as a scale factor, a distance, or an angle."""

    @property
    @abstractmethod
    def equivalent_scale(self) -> Optional[RepresentativeFraction]:
        """
        Level of detail expressed as the scale of a comparable hardcopy map or
        chart.

        MANDATORY: if `distance`, `vertical`, `angular_distance`,
            or `level_of_detail` not documented.
        """

    @property
    @abstractmethod
    def distance(self) -> Optional[Distance]:
        """
        Horizontal ground sample distance.

        MANDATORY: if `equivalent_scale`, `vertical`, `angular_distance`,
            or `level_of_detail` not documented.
        """

    @property
    @abstractmethod
    def vertical(self) -> Optional[Distance]:
        """
        Vertical sampling distance.

        MANDATORY: if `equivalent_scale`, `distance`, `angular_distance`,
            or `level_of_detail` not documented.
        """

    @property
    @abstractmethod
    def angular_distance(self) -> Optional[Angle]:
        """
        Angular sampling measure.

        MANDATORY: if `equivalent_scale`, `distance`, `vertical`,
            or `level_of_detail` not documented.
        """

    @property
    @abstractmethod
    def level_of_detail(self) -> Optional[str]:
        """
        Brief textual description of the spatial resolution of the resource.

        MANDATORY: if `equivalent_scale`, `distance`, `vertical`,
            or `angular_distance` not documented.
        """


class AssociatedResource(ABC):
    """
    Associated resource information.

    NOTE: An associated resource is a dataset composed of a collection
    of datasets.
    """

    @property
    @abstractmethod
    def name(self) -> Optional[Citation]:
        """
        Citation information about the associated resource.

        MANDATORY: if `metadata_reference` not documented.
        """

    @property
    @abstractmethod
    def association_type(self) -> AssociationTypeCode:
        """Type of relation between the resources."""

    @property
    @abstractmethod
    def initiative_type(self) -> Optional[InitiativeTypeCode]:
        """
        Type of initiative under which the associated resource was produced.

        NOTE: the activity that resulted in the associated resource.
        """

    @property
    @abstractmethod
    def metadata_reference(self) -> Optional[Citation]:
        """
        Reference to the metadata of the associated resource.

        MANDATORY: if `name` not documented.
        """


class Identification(ABC):
    """
    Basic information required to uniquely identify a resource or resources.
    """

    @property
    @abstractmethod
    def citation(self) -> Citation:
        """Citation for the resource(s)."""

    @property
    @abstractmethod
    def abstract(self) -> str:
        """Brief narrative summary of the content of the resource(s)."""

    @abstractmethod
    def purpose(self) -> Optional[str]:
        """
        Summary of the intentions with which the resource(s) was developed.
        """

    @property
    @abstractmethod
    def credit(self) -> Optional[Sequence[str]]:
        """Recognition of those who contributed to the resource(s)."""

    @property
    @abstractmethod
    def status(self) -> Optional[Sequence[ProgressCode]]:
        """Status of the resource(s)."""

    @property
    @abstractmethod
    def point_of_contact(self) -> Optional[Sequence[Responsibility]]:
        """
        Identification of, and means of communication with, person(s) and
        organisation(s) associated with the resource(s).
        """

    @property
    @abstractmethod
    def spatial_representation_type(self) -> Optional[Sequence[
        SpatialRepresentationTypeCode
    ]]:
        """Method used to spatially represent geographic information."""

    @property
    @abstractmethod
    def spatial_resolution(self) -> Optional[Sequence[Resolution]]:
        """
        Factor which provides a general understanding of the density of
        spatial data in the resource.
        """

    @property
    @abstractmethod
    def temporal_resolution(self) -> Optional[Sequence[timedelta]]:
        """Smallest resolvable temporal period in a resource."""

    @property
    @abstractmethod
    def topic_category(self) -> Optional[Sequence[TopicCategoryCode]]:
        """
        Main theme(s) of the resource.

        MANDATORY: if `Metadata.metadata_scope.resource_scope` == 'dataset'
            OR `Metadata.metadata_scope.resource_scope` == 'series'.
        """

    @property
    @abstractmethod
    def extent(self) -> Optional[Sequence[Extent]]:
        """
        Spatial and temporal extent of the resource.

        MANDATORY: if `Metadata.metadata_scope.resource_scope` == 'dataset'
            then `extent.geographic_element.GeographicBoundingBox`
            or  `extent.geographic_element.Geographicdescription` is required.
        """

    @property
    @abstractmethod
    def additional_documentation(self) -> Optional[Sequence[Citation]]:
        """
        Other documentation associated with the resource, e.g.,
        Related articles, publications, user guides, data dictionaries.
        """

    @property
    @abstractmethod
    def processing_level(self) -> Optional[Identifier]:
        """
        Code that identifies the level of processing in the producers coding
        system of a resource, e.g., NOAA level 1B.
        """

    @property
    @abstractmethod
    def resource_maintenance(self) -> Optional[Sequence[
        MaintenanceInformation
    ]]:
        """
        Information about the frequency of resource updates and the scope of
        those updates.
        """

    @property
    @abstractmethod
    def graphic_overview(self) -> Optional[Sequence[BrowseGraphic]]:
        """
        Graphic that illustrates the resource (should include a legend for
        the graphic).
        """

    @property
    @abstractmethod
    def resource_format(self) -> Optional[Sequence[Format]]:
        """Description of the format of the resource."""

    @property
    @abstractmethod
    def descriptive_keywords(self) -> Optional[Sequence[Keywords]]:
        """Category keywords, their type and reference source."""

    @property
    @abstractmethod
    def resource_specific_usage(self) -> Optional[Sequence[Usage]]:
        """
        Basic information about specific application(s) for which the resource
        has been or is being used by different users.
        """

    @property
    @abstractmethod
    def resource_constraints(self) -> Optional[Sequence[Constraints]]:
        """Information about constraints which apply to the resource."""

    @property
    @abstractmethod
    def associated_resource(self) -> Optional[Sequence[AssociatedResource]]:
        """Associated resource information."""


class DataIdentification(Identification):
    """Information required to identify a resource."""

    @property
    @abstractmethod
    def default_locale(self) -> Optional[str]:
        """
        Language and character set used within the resource. A string
        conforming to IETF BCP 47.

        MANDATORY: if a language is used in the resource.
        """

    @property
    @abstractmethod
    def other_locale(self) -> Optional[Sequence[str]]:
        """
        Alternate localised language(s) and character set(s) used within
        the resource. A string conforming to IETF BCP 47.
        """

    @property
    @abstractmethod
    def environment_description(self) -> Optional[str]:
        """
        Description of the resource in the producer's processing environment,
        including items such as the software, the computer operating system,
        file name, and the dataset size.
        """

    @property
    @abstractmethod
    def supplemental_information(self) -> Optional[str]:
        """Any other descriptive information about the resource."""
