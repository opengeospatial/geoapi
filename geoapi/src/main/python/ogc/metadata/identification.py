#
#    GeoAPI - Programming interfaces for OGC/ISO standards
#    http://www.geoapi.org
#
#    Copyright (C) 2018 Open Geospatial Consortium, Inc.
#    All Rights Reserved. http://www.opengeospatial.org/ogc/legal
#

from abc import ABC, abstractproperty
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

class Identifier(ABC):
    """Value uniquely identifying an object within a namespace."""

    @abstractproperty
    def authority(self) -> Citation:
        """Citation for the code namespace and optionally the person or party responsible for maintenance of that namespace."""
        pass

    @abstractproperty
    def code(self) -> str:
        """Alphanumeric value identifying an instance in the namespace e.g. EPSG::4326."""
        pass

    @abstractproperty
    def codeSpace(self) -> str:
        """Identifier or namespace in which the code is valid."""
        pass

    @abstractproperty
    def version(self) -> str:
        """Version identifier for the namespace."""
        pass

    @abstractproperty
    def description(self) -> str:
        """Natural language description of the meaning of the code value E.G for codeSpace = EPSG, code = 4326: description = WGS-84" to "for codeSpace = EPSG, code = EPSG::4326: description = WGS-84."""
        pass

class BrowseGraphic(ABC):
    """Graphic that provides an illustration of the dataset (should include a legend for the graphic, if applicable)."""

    @abstractproperty
    def fileName(self):
        """Name of the file that contains a graphic that provides an illustration of the dataset."""
        pass

    @abstractproperty
    def fileDescription(self) -> str:
        """Text description of the illustration."""
        pass

    @abstractproperty
    def fileType(self) -> str:
        pass

    @abstractproperty
    def imageConstraints(self) -> Constraints:
        """Restriction on access and/or use of browse graphic."""
        pass

    @abstractproperty
    def linkage(self) -> OnlineResource:
        """Link to browse graphic."""
        pass

class KeywordClass(ABC):
    """Specification of a class to categorize keywords in a domain-specific vocabulary that has a binding to a formal ontology."""

    @abstractproperty
    def className(self) -> str:
        """Character string to label the keyword category in natural language."""
        pass

    @abstractproperty
    def conceptIdentifier(self):
        """URI of concept in ontology specified by the ontology attribute; this concept is labeled by the className: CharacterString."""
        pass

    @abstractproperty
    def ontology(self) -> Citation:
        """A reference that binds the keyword class to a formal conceptualization of a knowledge domain for use in semantic processingNOTE: Keywords in the associated MD_Keywords keyword list must be within the scope of this ontology."""
        pass

class Keywords(ABC):
    """Keywords, their type and reference source. NOTE: When the resource described is a service, one instance of MD_Keyword shall refer to the service taxonomy defined in ISO 19119, 8.3)."""

    @abstractproperty
    def keyword(self) -> str:
        """Commonly used word(s) or formalised word(s) or phrase(s) used to describe the subject."""
        pass

    @abstractproperty
    def type(self) -> KeywordTypeCode:
        """Subject matter used to group similar keywords."""
        pass

    @abstractproperty
    def thesaurusName(self) -> Citation:
        """Name of the formally registered thesaurus or a similar authoritative source of keywords."""
        pass

    @abstractproperty
    def keywordClass(self) -> KeywordClass:
        pass

class Usage(ABC):
    """Brief description of ways in which the resource(s) is/are currently or has been used."""

    @abstractproperty
    def specificUsage(self) -> str:
        """Brief description of the resource and/or resource series usage."""
        pass

    @abstractproperty
    def usageDateTime(self) -> datetime:
        """Date and time of the first use or range of uses of the resource and/or resource series."""
        pass

    @abstractproperty
    def userDeterminedLimitations(self) -> str:
        """Applications, determined by the user for which the resource and/or resource series is not suitable."""
        pass

    @abstractproperty
    def userContactInfo(self) -> Responsibility:
        """Identification of and means of communicating with person(s) and organisation(s) using the resource(s)."""
        pass

    @abstractproperty
    def response(self) -> str:
        """Response to the user-determined limitationsE.G.. 'this has been fixed in version x'."""
        pass

    @abstractproperty
    def additionalDocumentation(self) -> Citation:
        pass

    @abstractproperty
    def identifiedIssues(self) -> Citation:
        pass

class RepresentativeFraction(ABC):
    """Derived from ISO 19103 Scale where MD_RepresentativeFraction.denominator = 1 / Scale.measure And Scale.targetUnits = Scale.sourceUnits."""

    @abstractproperty
    def denominator(self) -> int:
        """The number below the line in a vulgar fraction."""
        pass

class Resolution(ABC):
    """Level of detail expressed as a scale factor, a distance or an angle."""

    @abstractproperty
    def equivalentScale(self) -> RepresentativeFraction:
        """Level of detail expressed as the scale of a comparable hardcopy map or chart."""
        pass

    @abstractproperty
    def distance(self) -> float:
        """Horizontal ground sample distance."""
        pass

    @abstractproperty
    def vertical(self) -> float:
        """Vertical sampling distance."""
        pass

    @abstractproperty
    def angularDistance(self) -> float:
        """Angular sampling measure."""
        pass

    @abstractproperty
    def levelOfDetail(self) -> str:
        """Brief textual description of the spatial resolution of the resource."""
        pass

class AssociatedResource(ABC):
    """Associated resource information. NOTE: An associated resource is a dataset composed of a collection of datasets."""

    @abstractproperty
    def name(self) -> Citation:
        """Citation information about the associated resource."""
        pass

    @abstractproperty
    def associationType(self) -> AssociationTypeCode:
        """Type of relation between the resources."""
        pass

    @abstractproperty
    def initiativeType(self) -> InitiativeTypeCode:
        """Type of initiative under which the associated resource was produced. NOTE: the activity that resulted in the associated resource."""
        pass

    @abstractproperty
    def metadataReference(self) -> Citation:
        """Reference to the metadata of the associated resource."""
        pass

class Identification(ABC):
    """Basic information required to uniquely identify a resource or resources."""

    @abstractproperty
    def citation(self) -> Citation:
        """Citation for the resource(s)."""
        pass

    @abstractproperty
    def abstract(self) -> str:
        """Brief narrative summary of the content of the resource(s)."""
        pass

    @abstractproperty
    def purpose(self) -> str:
        """Summary of the intentions with which the resource(s) was developed."""
        pass

    @abstractproperty
    def credit(self) -> str:
        """Recognition of those who contributed to the resource(s)."""
        pass

    @abstractproperty
    def status(self) -> ProgressCode:
        """Status of the resource(s)."""
        pass

    @abstractproperty
    def pointOfContact(self) -> Responsibility:
        """Identification of, and means of communication with, person(s) and organisation(s) associated with the resource(s)."""
        pass

    @abstractproperty
    def spatialRepresentationType(self) -> SpatialRepresentationTypeCode:
        """Method used to spatially represent geographic information."""
        pass

    @abstractproperty
    def spatialResolution(self) -> Resolution:
        """Factor which provides a general understanding of the density of spatial data in the resource."""
        pass

    @abstractproperty
    def temporalResolution(self) -> Duration:
        """Resolution of the resource with respect to time."""
        pass

    @abstractproperty
    def topicCategory(self) -> TopicCategoryCode:
        """Main theme(s) of the resource."""
        pass

    @abstractproperty
    def extent(self) -> Extent:
        """Spatial and temporal extent of the resource."""
        pass

    @abstractproperty
    def additionalDocumentation(self) -> Citation:
        """Other documentation associated with the resource."""
        pass

    @abstractproperty
    def processingLevel(self) -> Identifier:
        """Code that identifies the level of processing in the producers coding system of a resource eg. NOAA level 1B."""
        pass

    @abstractproperty
    def resourceMaintenance(self) -> MaintenanceInformation:
        pass

    @abstractproperty
    def graphicOverview(self) -> BrowseGraphic:
        pass

    @abstractproperty
    def resourceFormat(self) -> Format:
        pass

    @abstractproperty
    def descriptiveKeywords(self) -> Keywords:
        pass

    @abstractproperty
    def resourceSpecificUsage(self) -> Usage:
        pass

    @abstractproperty
    def resourceConstraints(self) -> Constraints:
        pass

    @abstractproperty
    def associatedResource(self) -> AssociatedResource:
        pass

class DataIdentification(Identification):
    """Information required to identify a resource."""

    @abstractproperty
    def environmentDescription(self) -> str:
        """Description of the resource in the producer's processing environment, including items such as the software, the computer operating system, file name, and the dataset size."""
        pass

    @abstractproperty
    def supplementalInformation(self) -> str:
        """Any other descriptive information about the resource."""
        pass

    @abstractproperty
    def characterSet(self):
        pass

    @abstractproperty
    def language(self):
        pass
