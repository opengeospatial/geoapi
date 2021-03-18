#
#    GeoAPI - Programming interfaces for OGC/ISO standards
#    http://www.geoapi.org
#
#    Copyright (C) 2018-2021 Open Geospatial Consortium, Inc.
#    All Rights Reserved. http://www.opengeospatial.org/ogc/legal
#

from abc import ABC, abstractmethod
from typing import Sequence
from enum import Enum



class AssociationTypeCode(Enum):
    CROSS_REFERENCE = "crossReference"
    LARGER_WORK_CITATION = "largerWorkCitation"
    PART_OF_SEAMLESS_DATABASE = "partOfSeamlessDatabase"
    SOURCE = "source"
    STEREO_MATE = "stereoMate"
    IS_COMPOSED_OF = "isComposedOf"
    COLLECTIVE_TITLE = "collectiveTitle"
    SERIES = "series"
    DEPENDENCY = "dependency"
    REVISION_OF = "revisionOf"



class InitiativeTypeCode(Enum):
    CAMPAIGN = "campaign"
    COLLECTION = "collection"
    EXERCISE = "exercise"
    EXPERIMENT = "experiment"
    INVESTIGATION = "investigation"
    MISSION = "mission"
    SENSOR = "sensor"
    OPERATION = "operation"
    PLATFORM = "platform"
    PROCESS = "process"
    PROGRAM = "program"
    PROJECT = "project"
    STUDY = "study"
    TASK = "task"
    TRIAL = "trial"



class KeywordTypeCode(Enum):
    DISCIPLINE = "discipline"
    PLACE = "place"
    STRATUM = "stratum"
    TEMPORAL = "temporal"
    THEME = "theme"
    DATA_CENTRE = "dataCentre"
    FEATURE_TYPE = "featureType"
    INSTRUMENT = "instrument"
    PLATFORM = "platform"
    PROCESS = "process"
    PROJECT = "project"
    SERVICE = "service"
    PRODUCT = "product"
    SUB_TOPIC_CATEGORY = "subTopicCategory"
    TAXON = "taxon"



class ProgressCode(Enum):
    COMPLETED = "completed"
    HISTORICAL_ARCHIVE = "historicalArchive"
    OBSOLETE = "obsolete"
    ON_GOING = "onGoing"
    PLANNED = "planned"
    REQUIRED = "required"
    UNDER_DEVELOPMENT = "underDevelopment"
    FINAL = "final"
    PENDING = "pending"
    RETIRED = "retired"
    SUPERSEDED = "superseded"
    TENTATIVE = "tentative"
    VALID = "valid"
    ACCEPTED = "accepted"
    NOT_ACCEPTED = "notAccepted"
    WITHDRAWN = "withdrawn"
    PROPOSED = "proposed"
    DEPRECATED = "deprecated"



class TopicCategoryCode(Enum):
    FARMING = "farming"
    BIOTA = "biota"
    BOUNDARIES = "boundaries"
    CLIMATOLOGY_METEOROLOGY_ATMOSPHERE = "climatologyMeteorologyAtmosphere"
    ECONOMY = "economy"
    ELEVATION = "elevation"
    ENVIRONMENT = "environment"
    GEOSCIENTIFIC_INFORMATION = "geoscientificInformation"
    HEALTH = "health"
    IMAGERY_BASE_MAPS_EARTH_COVER = "imageryBaseMapsEarthCover"
    INTELLIGENCE_MILITARY = "intelligenceMilitary"
    INLAND_WATERS = "inlandWaters"
    LOCATION = "location"
    OCEANS = "oceans"
    PLANNING_CADASTRE = "planningCadastre"
    SOCIETY = "society"
    STRUCTURE = "structure"
    TRANSPORTATION = "transportation"
    UTILITIES_COMMUNICATION = "utilitiesCommunication"
    EXTRA_TERRESTRIAL = "extraTerrestrial"
    DISASTER = "disaster"



from opengis.metadata.constraints import Constraints
from opengis.metadata.citation import OnlineResource, Citation, Responsibility, Identifier

class BrowseGraphic(ABC):
    """Graphic that provides an illustration of the dataset (should include a legend for the graphic, if applicable)."""

    @property
    @abstractmethod
    def file_name(self):
        """Name of the file that contains a graphic that provides an illustration of the dataset."""
        pass

    @property
    def file_description(self) -> str:
        """Text description of the illustration."""
        return None

    @property
    def file_type(self) -> str:
        """Format in which the illustration is encoded."""
        return None

    @property
    def image_constraints(self) -> Sequence[Constraints]:
        """Restriction on access and/or use of browse graphic."""
        return None

    @property
    def linkage(self) -> Sequence[OnlineResource]:
        """Link to browse graphic."""
        return None



class KeywordClass(ABC):
    """Specification of a class to categorize keywords in a domain-specific vocabulary that has a binding to a formal ontology."""

    @property
    @abstractmethod
    def class_name(self) -> str:
        """Character string to label the keyword category in natural language."""
        pass

    @property
    def concept_identifier(self):
        """URI of concept in ontology specified by the ontology attribute; this concept is labeled by the className: CharacterString."""
        return None

    @property
    @abstractmethod
    def ontology(self) -> Citation:
        """A reference that binds the keyword class to a formal conceptualization of a knowledge domain for use in semantic processingNOTE: Keywords in the associated MD_Keywords keyword list must be within the scope of this ontology."""
        pass



class Keywords(ABC):
    """Keywords, their type and reference source. NOTE: When the resource described is a service, one instance of MD_Keyword shall refer to the service taxonomy defined in ISO 19119, 8.3)."""

    @property
    @abstractmethod
    def keyword(self) -> Sequence[str]:
        """Commonly used word(s) or formalised word(s) or phrase(s) used to describe the subject."""
        pass

    @property
    def type(self) -> KeywordTypeCode:
        """Subject matter used to group similar keywords."""
        return None

    @property
    def thesaurus_name(self) -> Citation:
        """Name of the formally registered thesaurus or a similar authoritative source of keywords."""
        return None

    @property
    def keyword_class(self) -> KeywordClass:
        return None



from datetime import datetime

class Usage(ABC):
    """Brief description of ways in which the resource(s) is/are currently or has been used."""

    @property
    @abstractmethod
    def specific_usage(self) -> str:
        """Brief description of the resource and/or resource series usage."""
        pass

    @property
    def usage_date_time(self) -> datetime:
        """Date and time of the first use or range of uses of the resource and/or resource series."""
        return None

    @property
    def user_determined_limitations(self) -> str:
        """Applications, determined by the user for which the resource and/or resource series is not suitable."""
        return None

    @property
    def user_contact_info(self) -> Sequence[Responsibility]:
        """Identification of and means of communicating with person(s) and organisation(s) using the resource(s)."""
        return None

    @property
    def response(self) -> Sequence[str]:
        """Response to the user-determined limitationsE.G.. 'this has been fixed in version x'."""
        return None

    @property
    def additional_documentation(self) -> Sequence[Citation]:
        return None

    @property
    def identified_issues(self) -> Sequence[Citation]:
        return None



class RepresentativeFraction(ABC):
    """Derived from ISO 19103 Scale where MD_RepresentativeFraction.denominator = 1 / Scale.measure And Scale.targetUnits = Scale.sourceUnits."""

    @property
    @abstractmethod
    def denominator(self) -> int:
        """The number below the line in a vulgar fraction."""
        pass



class Resolution(ABC):
    """Level of detail expressed as a scale factor, a distance or an angle."""

    @property
    def equivalent_scale(self) -> RepresentativeFraction:
        """Level of detail expressed as the scale of a comparable hardcopy map or chart."""
        return None

    @property
    def distance(self) -> float:
        """Horizontal ground sample distance."""
        return None

    @property
    def vertical(self) -> float:
        """Vertical sampling distance."""
        return None

    @property
    def angular_distance(self) -> float:
        """Angular sampling measure."""
        return None

    @property
    def level_of_detail(self) -> str:
        """Brief textual description of the spatial resolution of the resource."""
        return None



class AssociatedResource(ABC):
    """Associated resource information. NOTE: An associated resource is a dataset composed of a collection of datasets."""

    @property
    def name(self) -> Citation:
        """Citation information about the associated resource."""
        return None

    @property
    @abstractmethod
    def association_type(self) -> AssociationTypeCode:
        """Type of relation between the resources."""
        pass

    @property
    def initiative_type(self) -> InitiativeTypeCode:
        """Type of initiative under which the associated resource was produced. NOTE: the activity that resulted in the associated resource."""
        return None

    @property
    def metadata_reference(self) -> Citation:
        """Reference to the metadata of the associated resource."""
        return None



from opengis.metadata.representation import SpatialRepresentationTypeCode
from opengis.metadata.extent import Extent
from opengis.metadata.maintenance import MaintenanceInformation
from opengis.metadata.distribution import Format

class Identification(ABC):
    """Basic information required to uniquely identify a resource or resources."""

    @property
    @abstractmethod
    def citation(self) -> Citation:
        """Citation for the resource(s)."""
        pass

    @property
    @abstractmethod
    def abstract(self) -> str:
        """Brief narrative summary of the content of the resource(s)."""
        pass

    @property
    def purpose(self) -> str:
        """Summary of the intentions with which the resource(s) was developed."""
        return None

    @property
    def credit(self) -> Sequence[str]:
        """Recognition of those who contributed to the resource(s)."""
        return None

    @property
    def status(self) -> Sequence[ProgressCode]:
        """Status of the resource(s)."""
        return None

    @property
    def point_of_contact(self) -> Sequence[Responsibility]:
        """Identification of, and means of communication with, person(s) and organisation(s) associated with the resource(s)."""
        return None

    @property
    def spatial_representation_type(self) -> Sequence[SpatialRepresentationTypeCode]:
        """Method used to spatially represent geographic information."""
        return None

    @property
    def spatial_resolution(self) -> Sequence[Resolution]:
        """Factor which provides a general understanding of the density of spatial data in the resource."""
        return None

    @property
    def temporal_resolution(self):
        """Resolution of the resource with respect to time."""
        return None

    @property
    def topic_category(self) -> Sequence[TopicCategoryCode]:
        """Main theme(s) of the resource."""
        return None

    @property
    def extent(self) -> Sequence[Extent]:
        """Spatial and temporal extent of the resource."""
        return None

    @property
    def additional_documentation(self) -> Sequence[Citation]:
        """Other documentation associated with the resource."""
        return None

    @property
    def processing_level(self) -> Identifier:
        """Code that identifies the level of processing in the producers coding system of a resource eg. NOAA level 1B."""
        return None

    @property
    def resource_maintenance(self) -> Sequence[MaintenanceInformation]:
        return None

    @property
    def graphic_overview(self) -> Sequence[BrowseGraphic]:
        return None

    @property
    def resource_format(self) -> Sequence[Format]:
        return None

    @property
    def descriptive_keywords(self) -> Sequence[Keywords]:
        return None

    @property
    def resource_specific_usage(self) -> Sequence[Usage]:
        return None

    @property
    def resource_constraints(self) -> Sequence[Constraints]:
        return None

    @property
    def associated_resource(self) -> Sequence[AssociatedResource]:
        return None



class DataIdentification(Identification):
    """Information required to identify a resource."""

    @property
    def default_locale(self):
        """Provides information about an alternatively used localized character string for a linguistic extension."""
        return None

    @property
    def environment_description(self) -> str:
        """Description of the resource in the producer's processing environment, including items such as the software, the computer operating system, file name, and the dataset size."""
        return None

    @property
    def supplemental_information(self) -> str:
        """Any other descriptive information about the resource."""
        return None
