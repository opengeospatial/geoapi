CREATE TABLE public."Locale"
(
  code CHARACTER VARYING(5) NOT NULL,
  CONSTRAINT "Locale_primaryKey" PRIMARY KEY (code)
) WITHOUT OIDS;
INSERT INTO "Locale" VALUES ('en');
INSERT INTO "Locale" VALUES ('fr');
INSERT INTO "Locale" VALUES ('fr_CA');
INSERT INTO "Locale" VALUES ('es');

CREATE TABLE public."Unit"
(
  symbol CHARACTER VARYING(10) NOT NULL,
  CONSTRAINT "Unit_primaryKey" PRIMARY KEY (symbol)
) WITHOUT OIDS;
INSERT INTO "Unit" VALUES ('m');
INSERT INTO "Unit" VALUES ('cm');
INSERT INTO "Unit" VALUES ('km');

CREATE TABLE metadata."MD_DatatypeCode"
(
  "code" INT2 NOT NULL,
  "name" CHARACTER VARYING(32) NOT NULL,
  CONSTRAINT "DatatypeCode_primaryKey" PRIMARY KEY ("code"),
  CONSTRAINT "DatatypeCode_uniqueName" UNIQUE ("name")
) WITHOUT OIDS;
INSERT INTO metadata."MD_DatatypeCode" VALUES (1, 'class');
INSERT INTO metadata."MD_DatatypeCode" VALUES (2, 'codelist');
INSERT INTO metadata."MD_DatatypeCode" VALUES (3, 'enumeration');
INSERT INTO metadata."MD_DatatypeCode" VALUES (4, 'codelistElement');
INSERT INTO metadata."MD_DatatypeCode" VALUES (5, 'abstractClass');
INSERT INTO metadata."MD_DatatypeCode" VALUES (6, 'aggregateClass');
INSERT INTO metadata."MD_DatatypeCode" VALUES (7, 'specifiedClass');
INSERT INTO metadata."MD_DatatypeCode" VALUES (8, 'datatypeClass');
INSERT INTO metadata."MD_DatatypeCode" VALUES (9, 'interfaceClass');
INSERT INTO metadata."MD_DatatypeCode" VALUES (10, 'unionClass');
INSERT INTO metadata."MD_DatatypeCode" VALUES (11, 'metaclass');
INSERT INTO metadata."MD_DatatypeCode" VALUES (12, 'typeClass');
INSERT INTO metadata."MD_DatatypeCode" VALUES (13, 'characterString');
INSERT INTO metadata."MD_DatatypeCode" VALUES (14, 'integer');
INSERT INTO metadata."MD_DatatypeCode" VALUES (15, 'association');


CREATE TABLE metadata."MD_ObligationCode"
(
  "code" INT2 NOT NULL,
  "name" CHARACTER VARYING(32) NOT NULL,
  CONSTRAINT "ObligationCode_primaryKey" PRIMARY KEY ("code"),
  CONSTRAINT "ObligationCode_uniqueName" UNIQUE ("name")
) WITHOUT OIDS;
INSERT INTO metadata."MD_ObligationCode" VALUES (1, 'mandatory');
INSERT INTO metadata."MD_ObligationCode" VALUES (2, 'optional');
INSERT INTO metadata."MD_ObligationCode" VALUES (3, 'conditional');


CREATE TABLE metadata."CI_DateType"
(
  "code" INT2 NOT NULL,
  "name" CHARACTER VARYING(32) NOT NULL,
  CONSTRAINT "DateType_primaryKey" PRIMARY KEY ("code"),
  CONSTRAINT "DateType_uniqueName" UNIQUE ("name")
) WITHOUT OIDS;
INSERT INTO metadata."CI_DateType" VALUES (1, 'creation');
INSERT INTO metadata."CI_DateType" VALUES (2, 'publication');
INSERT INTO metadata."CI_DateType" VALUES (3, 'revision');


CREATE TABLE metadata."CI_OnLineFunctionCode"
(
  "code" INT2 NOT NULL,
  "name" CHARACTER VARYING(32) NOT NULL,
  CONSTRAINT "OnLineFunctionCode_primaryKey" PRIMARY KEY ("code"),
  CONSTRAINT "OnLineFunctionCode_uniqueName" UNIQUE ("name")
) WITHOUT OIDS;
INSERT INTO metadata."CI_OnLineFunctionCode" VALUES (1, 'download');
INSERT INTO metadata."CI_OnLineFunctionCode" VALUES (2, 'information');
INSERT INTO metadata."CI_OnLineFunctionCode" VALUES (3, 'offlineAccess');
INSERT INTO metadata."CI_OnLineFunctionCode" VALUES (4, 'order');
INSERT INTO metadata."CI_OnLineFunctionCode" VALUES (5, 'search');


CREATE TABLE metadata."CI_PresentationFormCode"
(
  "code" INT2 NOT NULL,
  "name" CHARACTER VARYING(32) NOT NULL,
  CONSTRAINT "PresentationFormCode_primaryKey" PRIMARY KEY ("code"),
  CONSTRAINT "PresentationFormCode_uniqueName" UNIQUE ("name")
) WITHOUT OIDS;
INSERT INTO metadata."CI_PresentationFormCode" VALUES (1, 'documentDigital');
INSERT INTO metadata."CI_PresentationFormCode" VALUES (2, 'documentHardcopy');
INSERT INTO metadata."CI_PresentationFormCode" VALUES (3, 'imageDigital');
INSERT INTO metadata."CI_PresentationFormCode" VALUES (4, 'imageHardcopy');
INSERT INTO metadata."CI_PresentationFormCode" VALUES (5, 'mapDigital');
INSERT INTO metadata."CI_PresentationFormCode" VALUES (6, 'mapHardcopy');
INSERT INTO metadata."CI_PresentationFormCode" VALUES (7, 'modelDigital');
INSERT INTO metadata."CI_PresentationFormCode" VALUES (8, 'modelHardcopy');
INSERT INTO metadata."CI_PresentationFormCode" VALUES (9, 'profileDigital');
INSERT INTO metadata."CI_PresentationFormCode" VALUES (10, 'profileHardcopy');
INSERT INTO metadata."CI_PresentationFormCode" VALUES (11, 'tableDigital');
INSERT INTO metadata."CI_PresentationFormCode" VALUES (12, 'tableHardcopy');
INSERT INTO metadata."CI_PresentationFormCode" VALUES (13, 'videoDigital');
INSERT INTO metadata."CI_PresentationFormCode" VALUES (14, 'videoHardcopy');


CREATE TABLE metadata."CI_RoleCode"
(
  "code" INT2 NOT NULL,
  "name" CHARACTER VARYING(32) NOT NULL,
  CONSTRAINT "RoleCode_primaryKey" PRIMARY KEY ("code"),
  CONSTRAINT "RoleCode_uniqueName" UNIQUE ("name")
) WITHOUT OIDS;
INSERT INTO metadata."CI_RoleCode" VALUES (1, 'resourceProvider');
INSERT INTO metadata."CI_RoleCode" VALUES (2, 'custodian');
INSERT INTO metadata."CI_RoleCode" VALUES (3, 'owner');
INSERT INTO metadata."CI_RoleCode" VALUES (4, 'user');
INSERT INTO metadata."CI_RoleCode" VALUES (5, 'distributor');
INSERT INTO metadata."CI_RoleCode" VALUES (6, 'originator');
INSERT INTO metadata."CI_RoleCode" VALUES (7, 'pointOfContact');
INSERT INTO metadata."CI_RoleCode" VALUES (8, 'principalInvestigator');
INSERT INTO metadata."CI_RoleCode" VALUES (9, 'processor');
INSERT INTO metadata."CI_RoleCode" VALUES (10, 'publisher');


CREATE TABLE metadata."MD_ClassificationCode"
(
  "code" INT2 NOT NULL,
  "name" CHARACTER VARYING(32) NOT NULL,
  CONSTRAINT "ClassificationCode_primaryKey" PRIMARY KEY ("code"),
  CONSTRAINT "ClassificationCode_uniqueName" UNIQUE ("name")
) WITHOUT OIDS;
INSERT INTO metadata."MD_ClassificationCode" VALUES (1, 'unclassified');
INSERT INTO metadata."MD_ClassificationCode" VALUES (2, 'restricted');
INSERT INTO metadata."MD_ClassificationCode" VALUES (3, 'confidential');
INSERT INTO metadata."MD_ClassificationCode" VALUES (4, 'secret');
INSERT INTO metadata."MD_ClassificationCode" VALUES (5, 'topsecret');


CREATE TABLE metadata."MD_RestrictionCode"
(
  "code" INT2 NOT NULL,
  "name" CHARACTER VARYING(32) NOT NULL,
  CONSTRAINT "RestrictionCode_primaryKey" PRIMARY KEY ("code"),
  CONSTRAINT "RestrictionCode_uniqueName" UNIQUE ("name")
) WITHOUT OIDS;
INSERT INTO metadata."MD_RestrictionCode" VALUES (1, 'copyright');
INSERT INTO metadata."MD_RestrictionCode" VALUES (2, 'patent');
INSERT INTO metadata."MD_RestrictionCode" VALUES (3, 'patentPending');
INSERT INTO metadata."MD_RestrictionCode" VALUES (4, 'trademark');
INSERT INTO metadata."MD_RestrictionCode" VALUES (5, 'license');
INSERT INTO metadata."MD_RestrictionCode" VALUES (6, 'intellectualPropertyRights');
INSERT INTO metadata."MD_RestrictionCode" VALUES (7, 'restricted');
INSERT INTO metadata."MD_RestrictionCode" VALUES (8, 'otherRestictions');


CREATE TABLE metadata."MD_CoverageContentTypeCode"
(
  "code" INT2 NOT NULL,
  "name" CHARACTER VARYING(32) NOT NULL,
  CONSTRAINT "CoverageContentTypeCode_primaryKey" PRIMARY KEY ("code"),
  CONSTRAINT "CoverageContentTypeCode_uniqueName" UNIQUE ("name")
) WITHOUT OIDS;
INSERT INTO metadata."MD_CoverageContentTypeCode" VALUES (1, 'image');
INSERT INTO metadata."MD_CoverageContentTypeCode" VALUES (2, 'thematicClassification');
INSERT INTO metadata."MD_CoverageContentTypeCode" VALUES (3, 'physicalMeasurement');


CREATE TABLE metadata."MD_ImagingConditionCode"
(
  "code" INT2 NOT NULL,
  "name" CHARACTER VARYING(32) NOT NULL,
  CONSTRAINT "ImagingConditionCode_primaryKey" PRIMARY KEY ("code"),
  CONSTRAINT "ImagingConditionCode_uniqueName" UNIQUE ("name")
) WITHOUT OIDS;
INSERT INTO metadata."MD_ImagingConditionCode" VALUES (1, 'blurredImage');
INSERT INTO metadata."MD_ImagingConditionCode" VALUES (2, 'cloud');
INSERT INTO metadata."MD_ImagingConditionCode" VALUES (3, 'degradingObliquity');
INSERT INTO metadata."MD_ImagingConditionCode" VALUES (4, 'fog');
INSERT INTO metadata."MD_ImagingConditionCode" VALUES (5, 'heavySmokeOrDust');
INSERT INTO metadata."MD_ImagingConditionCode" VALUES (6, 'night');
INSERT INTO metadata."MD_ImagingConditionCode" VALUES (7, 'rain');
INSERT INTO metadata."MD_ImagingConditionCode" VALUES (8, 'semiDarkness');
INSERT INTO metadata."MD_ImagingConditionCode" VALUES (9, 'shadow');
INSERT INTO metadata."MD_ImagingConditionCode" VALUES (10, 'snow');
INSERT INTO metadata."MD_ImagingConditionCode" VALUES (11, 'terrainMasking');


CREATE TABLE metadata."MD_MediumFormatCode"
(
  "code" INT2 NOT NULL,
  "name" CHARACTER VARYING(32) NOT NULL,
  CONSTRAINT "MediumFormatCode_primaryKey" PRIMARY KEY ("code"),
  CONSTRAINT "MediumFormatCode_uniqueName" UNIQUE ("name")
) WITHOUT OIDS;
INSERT INTO metadata."MD_MediumFormatCode" VALUES (1, 'cpio');
INSERT INTO metadata."MD_MediumFormatCode" VALUES (2, 'tar');
INSERT INTO metadata."MD_MediumFormatCode" VALUES (3, 'highSierra');
INSERT INTO metadata."MD_MediumFormatCode" VALUES (4, 'iso9660');
INSERT INTO metadata."MD_MediumFormatCode" VALUES (5, 'iso9660RockRidge');
INSERT INTO metadata."MD_MediumFormatCode" VALUES (6, 'iso9660AppleHFS');


CREATE TABLE metadata."MD_MediumNameCode"
(
  "code" INT2 NOT NULL,
  "name" CHARACTER VARYING(32) NOT NULL,
  CONSTRAINT "MediumNameCode_primaryKey" PRIMARY KEY ("code"),
  CONSTRAINT "MediumNameCode_uniqueName" UNIQUE ("name")
) WITHOUT OIDS;
INSERT INTO metadata."MD_MediumNameCode" VALUES (1, 'cdRom');
INSERT INTO metadata."MD_MediumNameCode" VALUES (2, 'dvd');
INSERT INTO metadata."MD_MediumNameCode" VALUES (3, 'dvdRom');
INSERT INTO metadata."MD_MediumNameCode" VALUES (4, '3halfInchFloppy');
INSERT INTO metadata."MD_MediumNameCode" VALUES (5, '5quarterInchFloppy');
INSERT INTO metadata."MD_MediumNameCode" VALUES (6, '7trackTape');
INSERT INTO metadata."MD_MediumNameCode" VALUES (7, '9trackTape');
INSERT INTO metadata."MD_MediumNameCode" VALUES (8, '3480Cartridge');
INSERT INTO metadata."MD_MediumNameCode" VALUES (9, '3490Cartridge');
INSERT INTO metadata."MD_MediumNameCode" VALUES (10, '3580Cartridge');
INSERT INTO metadata."MD_MediumNameCode" VALUES (11, '4mmCartridgeTape');
INSERT INTO metadata."MD_MediumNameCode" VALUES (12, '8mmCartridgeTape');
INSERT INTO metadata."MD_MediumNameCode" VALUES (13, '1quarterInchCartridgeTape');
INSERT INTO metadata."MD_MediumNameCode" VALUES (14, 'digitalLinearTape');
INSERT INTO metadata."MD_MediumNameCode" VALUES (15, 'onLine');
INSERT INTO metadata."MD_MediumNameCode" VALUES (16, 'satellite');
INSERT INTO metadata."MD_MediumNameCode" VALUES (17, 'telephoneLink');
INSERT INTO metadata."MD_MediumNameCode" VALUES (18, 'hardCopy');


CREATE TABLE metadata."MD_KeywordTypeCode"
(
  "code" INT2 NOT NULL,
  "name" CHARACTER VARYING(32) NOT NULL,
  CONSTRAINT "KeywordTypeCode_primaryKey" PRIMARY KEY ("code"),
  CONSTRAINT "KeywordTypeCode_uniqueName" UNIQUE ("name")
) WITHOUT OIDS;
INSERT INTO metadata."MD_KeywordTypeCode" VALUES (1, 'discipline');
INSERT INTO metadata."MD_KeywordTypeCode" VALUES (2, 'place');
INSERT INTO metadata."MD_KeywordTypeCode" VALUES (3, 'stratum');
INSERT INTO metadata."MD_KeywordTypeCode" VALUES (4, 'temporal');
INSERT INTO metadata."MD_KeywordTypeCode" VALUES (5, 'theme');


CREATE TABLE metadata."MD_ProgressCode"
(
  "code" INT2 NOT NULL,
  "name" CHARACTER VARYING(32) NOT NULL,
  CONSTRAINT "ProgressCode_primaryKey" PRIMARY KEY ("code"),
  CONSTRAINT "ProgressCode_uniqueName" UNIQUE ("name")
) WITHOUT OIDS;
INSERT INTO metadata."MD_ProgressCode" VALUES (1, 'completed');
INSERT INTO metadata."MD_ProgressCode" VALUES (2, 'historicalArchive');
INSERT INTO metadata."MD_ProgressCode" VALUES (3, 'obsolete');
INSERT INTO metadata."MD_ProgressCode" VALUES (4, 'onGoing');
INSERT INTO metadata."MD_ProgressCode" VALUES (5, 'planned');
INSERT INTO metadata."MD_ProgressCode" VALUES (6, 'required');
INSERT INTO metadata."MD_ProgressCode" VALUES (7, 'underdevelopment');


CREATE TABLE metadata."MD_TopicCategoryCode"
(
  "code" INT2 NOT NULL,
  "name" CHARACTER VARYING(32) NOT NULL,
  CONSTRAINT "TopicCategoryCode_primaryKey" PRIMARY KEY ("code"),
  CONSTRAINT "TopicCategoryCode_uniqueName" UNIQUE ("name")
) WITHOUT OIDS;
INSERT INTO metadata."MD_TopicCategoryCode" VALUES (1, 'farming');
INSERT INTO metadata."MD_TopicCategoryCode" VALUES (2, 'biota');
INSERT INTO metadata."MD_TopicCategoryCode" VALUES (3, 'boundaries');
INSERT INTO metadata."MD_TopicCategoryCode" VALUES (4, 'climatologyMeteorologyAtmosphere');
INSERT INTO metadata."MD_TopicCategoryCode" VALUES (5, 'economy');
INSERT INTO metadata."MD_TopicCategoryCode" VALUES (6, 'elevation');
INSERT INTO metadata."MD_TopicCategoryCode" VALUES (7, 'environment');
INSERT INTO metadata."MD_TopicCategoryCode" VALUES (8, 'geoscientificInformation');
INSERT INTO metadata."MD_TopicCategoryCode" VALUES (9, 'healt');
INSERT INTO metadata."MD_TopicCategoryCode" VALUES (10, 'imageryBaseMapsEarthCover');
INSERT INTO metadata."MD_TopicCategoryCode" VALUES (11, 'intelligenceMilitary');
INSERT INTO metadata."MD_TopicCategoryCode" VALUES (12, 'inlandWaters');
INSERT INTO metadata."MD_TopicCategoryCode" VALUES (13, 'location');
INSERT INTO metadata."MD_TopicCategoryCode" VALUES (14, 'oceans');
INSERT INTO metadata."MD_TopicCategoryCode" VALUES (15, 'planningCadastre');
INSERT INTO metadata."MD_TopicCategoryCode" VALUES (16, 'society');
INSERT INTO metadata."MD_TopicCategoryCode" VALUES (17, 'structure');
INSERT INTO metadata."MD_TopicCategoryCode" VALUES (18, 'transportation');
INSERT INTO metadata."MD_TopicCategoryCode" VALUES (19, 'utilitiesCommunication');


CREATE TABLE metadata."MD_MaintenanceFrequencyCode"
(
  "code" INT2 NOT NULL,
  "name" CHARACTER VARYING(32) NOT NULL,
  CONSTRAINT "MaintenanceFrequencyCode_primaryKey" PRIMARY KEY ("code"),
  CONSTRAINT "MaintenanceFrequencyCode_uniqueName" UNIQUE ("name")
) WITHOUT OIDS;
INSERT INTO metadata."MD_MaintenanceFrequencyCode" VALUES (1, 'continual');
INSERT INTO metadata."MD_MaintenanceFrequencyCode" VALUES (2, 'daily');
INSERT INTO metadata."MD_MaintenanceFrequencyCode" VALUES (3, 'weekly');
INSERT INTO metadata."MD_MaintenanceFrequencyCode" VALUES (4, 'fortnightly');
INSERT INTO metadata."MD_MaintenanceFrequencyCode" VALUES (5, 'monthly');
INSERT INTO metadata."MD_MaintenanceFrequencyCode" VALUES (6, 'quarterly');
INSERT INTO metadata."MD_MaintenanceFrequencyCode" VALUES (7, 'biannually');
INSERT INTO metadata."MD_MaintenanceFrequencyCode" VALUES (8, 'annually');
INSERT INTO metadata."MD_MaintenanceFrequencyCode" VALUES (9, 'asNeeded');
INSERT INTO metadata."MD_MaintenanceFrequencyCode" VALUES (10, 'irregular');
INSERT INTO metadata."MD_MaintenanceFrequencyCode" VALUES (11, 'notPlanned');
INSERT INTO metadata."MD_MaintenanceFrequencyCode" VALUES (12, 'unknow');


CREATE TABLE metadata."MD_ScopeCode"
(
  "code" INT2 NOT NULL,
  "name" CHARACTER VARYING(32) NOT NULL,
  CONSTRAINT "ScopeCode_primaryKey" PRIMARY KEY ("code"),
  CONSTRAINT "ScopeCode_uniqueName" UNIQUE ("name")
) WITHOUT OIDS;
INSERT INTO metadata."MD_ScopeCode" VALUES (1, 'attribute');
INSERT INTO metadata."MD_ScopeCode" VALUES (2, 'attributeType');
INSERT INTO metadata."MD_ScopeCode" VALUES (3, 'collectionHardware');
INSERT INTO metadata."MD_ScopeCode" VALUES (4, 'collectionSession');
INSERT INTO metadata."MD_ScopeCode" VALUES (5, 'dataset');
INSERT INTO metadata."MD_ScopeCode" VALUES (6, 'series');
INSERT INTO metadata."MD_ScopeCode" VALUES (7, 'nonGeographicDataset');
INSERT INTO metadata."MD_ScopeCode" VALUES (8, 'dimensionGroup');
INSERT INTO metadata."MD_ScopeCode" VALUES (9, 'feature');
INSERT INTO metadata."MD_ScopeCode" VALUES (10, 'featureType');
INSERT INTO metadata."MD_ScopeCode" VALUES (11, 'propertyType');
INSERT INTO metadata."MD_ScopeCode" VALUES (12, 'fieldSession');
INSERT INTO metadata."MD_ScopeCode" VALUES (13, 'software');
INSERT INTO metadata."MD_ScopeCode" VALUES (14, 'service');
INSERT INTO metadata."MD_ScopeCode" VALUES (15, 'model');


CREATE TABLE metadata."DQ_EvaluationMethodTypeCode"
(
  "code" INT2 NOT NULL,
  "name" CHARACTER VARYING(32) NOT NULL,
  CONSTRAINT "EvaluationMethodTypeCode_primaryKey" PRIMARY KEY ("code"),
  CONSTRAINT "EvaluationMethodTypeCode_uniqueName" UNIQUE ("name")
) WITHOUT OIDS;
INSERT INTO metadata."DQ_EvaluationMethodTypeCode" VALUES (1, 'directInternal');
INSERT INTO metadata."DQ_EvaluationMethodTypeCode" VALUES (2, 'directExternal');
INSERT INTO metadata."DQ_EvaluationMethodTypeCode" VALUES (3, 'indirect');


CREATE TABLE metadata."MD_CellGeometryCode"
(
  "code" INT2 NOT NULL,
  "name" CHARACTER VARYING(32) NOT NULL,
  CONSTRAINT "CellGeometryCode_primaryKey" PRIMARY KEY ("code"),
  CONSTRAINT "CellGeometryCode_uniqueName" UNIQUE ("name")
) WITHOUT OIDS;
INSERT INTO metadata."MD_CellGeometryCode" VALUES (1, 'point');
INSERT INTO metadata."MD_CellGeometryCode" VALUES (2, 'area');


CREATE TABLE metadata."MD_DimensionNameTypeCode"
(
  "code" INT2 NOT NULL,
  "name" CHARACTER VARYING(32) NOT NULL,
  CONSTRAINT "DimensionNameTypeCode_primaryKey" PRIMARY KEY ("code"),
  CONSTRAINT "DimensionNameTypeCode_uniqueName" UNIQUE ("name")
) WITHOUT OIDS;
INSERT INTO metadata."MD_DimensionNameTypeCode" VALUES (1, 'row');
INSERT INTO metadata."MD_DimensionNameTypeCode" VALUES (2, 'column');
INSERT INTO metadata."MD_DimensionNameTypeCode" VALUES (3, 'vertical');
INSERT INTO metadata."MD_DimensionNameTypeCode" VALUES (4, 'track');
INSERT INTO metadata."MD_DimensionNameTypeCode" VALUES (5, 'crossTrack');
INSERT INTO metadata."MD_DimensionNameTypeCode" VALUES (6, 'line');
INSERT INTO metadata."MD_DimensionNameTypeCode" VALUES (7, 'sample');
INSERT INTO metadata."MD_DimensionNameTypeCode" VALUES (8, 'time');


CREATE TABLE metadata."MD_GeometricObjectTypeCode"
(
  "code" INT2 NOT NULL,
  "name" CHARACTER VARYING(32) NOT NULL,
  CONSTRAINT "GeometricObjectTypeCode_primaryKey" PRIMARY KEY ("code"),
  CONSTRAINT "GeometricObjectTypeCode_uniqueName" UNIQUE ("name")
) WITHOUT OIDS;
INSERT INTO metadata."MD_GeometricObjectTypeCode" VALUES (1, 'complexes');
INSERT INTO metadata."MD_GeometricObjectTypeCode" VALUES (2, 'composites');
INSERT INTO metadata."MD_GeometricObjectTypeCode" VALUES (3, 'curve');
INSERT INTO metadata."MD_GeometricObjectTypeCode" VALUES (4, 'point');
INSERT INTO metadata."MD_GeometricObjectTypeCode" VALUES (5, 'solid');
INSERT INTO metadata."MD_GeometricObjectTypeCode" VALUES (6, 'surface');


CREATE TABLE metadata."MD_PixelOrientationCode"
(
  "code" INT2 NOT NULL,
  "name" CHARACTER VARYING(32) NOT NULL,
  CONSTRAINT "PixelOrientationCode_primaryKey" PRIMARY KEY ("code"),
  CONSTRAINT "PixelOrientationCode_uniqueName" UNIQUE ("name")
) WITHOUT OIDS;
INSERT INTO metadata."MD_PixelOrientationCode" VALUES (1, 'center');
INSERT INTO metadata."MD_PixelOrientationCode" VALUES (2, 'lowerLeft');
INSERT INTO metadata."MD_PixelOrientationCode" VALUES (3, 'lowerRight');
INSERT INTO metadata."MD_PixelOrientationCode" VALUES (4, 'upperRight');
INSERT INTO metadata."MD_PixelOrientationCode" VALUES (5, 'upperLeft');


CREATE TABLE metadata."MD_SpatialRepresentationTypeCode"
(
  "code" INT2 NOT NULL,
  "name" CHARACTER VARYING(32) NOT NULL,
  CONSTRAINT "SpatialRepresentationTypeCode_primaryKey" PRIMARY KEY ("code"),
  CONSTRAINT "SpatialRepresentationTypeCode_uniqueName" UNIQUE ("name")
) WITHOUT OIDS;
INSERT INTO metadata."MD_SpatialRepresentationTypeCode" VALUES (1, 'vector');
INSERT INTO metadata."MD_SpatialRepresentationTypeCode" VALUES (2, 'grid');
INSERT INTO metadata."MD_SpatialRepresentationTypeCode" VALUES (3, 'textTable');
INSERT INTO metadata."MD_SpatialRepresentationTypeCode" VALUES (4, 'tin');
INSERT INTO metadata."MD_SpatialRepresentationTypeCode" VALUES (5, 'stereoModel');
INSERT INTO metadata."MD_SpatialRepresentationTypeCode" VALUES (6, 'video');


CREATE TABLE metadata."MD_TopologyLevelCode"
(
  "code" INT2 NOT NULL,
  "name" CHARACTER VARYING(32) NOT NULL,
  CONSTRAINT "TopologyLevelCode_primaryKey" PRIMARY KEY ("code"),
  CONSTRAINT "TopologyLevelCode_uniqueName" UNIQUE ("name")
) WITHOUT OIDS;
INSERT INTO metadata."MD_TopologyLevelCode" VALUES (1, 'geometryOnly');
INSERT INTO metadata."MD_TopologyLevelCode" VALUES (2, 'topology1D');
INSERT INTO metadata."MD_TopologyLevelCode" VALUES (3, 'planarGraph');
INSERT INTO metadata."MD_TopologyLevelCode" VALUES (4, 'fullPlanarGraph');
INSERT INTO metadata."MD_TopologyLevelCode" VALUES (5, 'surfaceGraph');
INSERT INTO metadata."MD_TopologyLevelCode" VALUES (6, 'fullSurfaceGraph');
INSERT INTO metadata."MD_TopologyLevelCode" VALUES (7, 'topology3D');
INSERT INTO metadata."MD_TopologyLevelCode" VALUES (8, 'fullTopology3D');
INSERT INTO metadata."MD_TopologyLevelCode" VALUES (9, 'abstract');


CREATE TABLE metadata."MD_ApplicationSchemaInformation"
(
  "id" OID NOT NULL,
  "name" OID NOT NULL,
  "schemaLanguage" TEXT NOT NULL,
  "constraintLanguage" TEXT NOT NULL,
  "schemaAscii" TEXT,
  "graphicsFile" TEXT,
  "softwareDevelopmentFile" TEXT,
  "softwareDevelopmentFileFormat" TEXT,
  "featureCatalogueSupplement" OID,
  CONSTRAINT "ApplicationSchemaInformation_primaryKey" PRIMARY KEY (id)
) WITHOUT OIDS;


CREATE TABLE metadata."MD_ExtendedElementInformation"
(
  "id" OID NOT NULL,
  "name" TEXT NOT NULL,
  "shortName" TEXT,
  "domainCode" INTEGER,
  "definition" TEXT NOT NULL,
  "obligation" INT2,
  "condition" TEXT,
  "dataType" INT2 NOT NULL,
  "maximumOccurrence" INTEGER,
  "domainValue" TEXT,
  "parentEntity" TEXT NOT NULL,
  "rule" TEXT NOT NULL,
  "rationale" TEXT,
  "source" OID NOT NULL,
  CONSTRAINT "ExtendedElementInformation_primaryKey" PRIMARY KEY (id)
) WITHOUT OIDS;


CREATE TABLE metadata."MD_FeatureTypeList"
(
  "id" OID NOT NULL,
  "spatialObject" TEXT NOT NULL,
  "spatialSchemaName" TEXT NOT NULL,
  CONSTRAINT "FeatureTypeList_primaryKey" PRIMARY KEY (id)
) WITHOUT OIDS;


CREATE TABLE metadata."MD_Identifier"
(
  "id" OID NOT NULL,
  "code" TEXT NOT NULL,
  "authority" OID,
  CONSTRAINT "Identifier_primaryKey" PRIMARY KEY (id)
) WITHOUT OIDS;


CREATE TABLE metadata."MD_MetaData"
(
  "id" OID NOT NULL,
  "fileIdentifier" TEXT,
  "language" CHARACTER VARYING(5),
  "characterSet" TEXT,
  "parentIdentifier" TEXT,
  "hierarchyLevel" INT2,
  "hierarchyLevelName" TEXT,
  "contact" OID NOT NULL,
  "dateStamp" TIMESTAMP WITH TIME ZONE NOT NULL,
  "metadataStandardName" TEXT,
  "metadataStandardVersion" TEXT,
  "spatialRepresentationInfo" OID,
  "metadataExtensionInfo" OID,
  "identificationInfo" OID NOT NULL,
  "contentInfo" OID,
  "distributionInfo" OID,
  "dataQualityInfo" OID,
  "portrayalCatalogueInfo" OID,
  "metadataConstraints" OID,
  "applicationSchemaInfo" OID,
  "metadataMaintenance" OID,
  CONSTRAINT "MetaData_primaryKey" PRIMARY KEY (id)
) WITHOUT OIDS;


CREATE TABLE metadata."MD_MetadataExtensionInformation"
(
  "id" OID NOT NULL,
  "extensionOnLineResource" OID,
  "extendedElementInformation" OID,
  CONSTRAINT "MetadataExtensionInformation_primaryKey" PRIMARY KEY (id)
) WITHOUT OIDS;


CREATE TABLE metadata."MD_PortrayalCatalogueReference"
(
  "id" OID NOT NULL,
  "portrayalCatalogueCitation" OID NOT NULL,
  CONSTRAINT "PortrayalCatalogueReference_primaryKey" PRIMARY KEY (id)
) WITHOUT OIDS;


CREATE TABLE metadata."MD_SpatialAttributeSupplement"
(
  "id" OID NOT NULL,
  "theFeatureTypeList" OID NOT NULL,
  CONSTRAINT "SpatialAttributeSupplement_primaryKey" PRIMARY KEY (id)
) WITHOUT OIDS;


CREATE TABLE metadata."CI_Address"
(
  "id" OID NOT NULL,
  "deliveryPoint" TEXT,
  "city" TEXT,
  "administrativeArea" TEXT,
  "postalCode" TEXT,
  "country" TEXT,
  "electronicMailAddress" TEXT,
  CONSTRAINT "Address_primaryKey" PRIMARY KEY (id)
) WITHOUT OIDS;


CREATE TABLE metadata."CI_Citation"
(
  "id" OID NOT NULL,
  "title" TEXT NOT NULL,
  "alternateTitle" TEXT,
  "date" OID NOT NULL,
  "edition" TEXT,
  "editionDate" TIMESTAMP WITH TIME ZONE,
  "identifier" TEXT,
  "identifierType" TEXT,
  "citedResponsibleParty" OID,
  "presentationForm" INT2,
  "series" OID,
  "otherCitationDetails" TEXT,
  "collectiveTitle" TEXT,
  "ISBN" TEXT,
  "ISSN" TEXT,
  CONSTRAINT "Citation_primaryKey" PRIMARY KEY (id)
) WITHOUT OIDS;


CREATE TABLE metadata."CI_Date"
(
  "id" OID NOT NULL,
  "date" TIMESTAMP WITH TIME ZONE NOT NULL,
  "dateType" INT2 NOT NULL,
  CONSTRAINT "Date_primaryKey" PRIMARY KEY (id)
) WITHOUT OIDS;


CREATE TABLE metadata."CI_Contact"
(
  "id" OID NOT NULL,
  "phone" OID,
  "address" OID,
  "onLineResource" OID,
  "hoursOfService" TEXT,
  "contactInstructions" TEXT,
  CONSTRAINT "Contact_primaryKey" PRIMARY KEY (id)
) WITHOUT OIDS;


CREATE TABLE metadata."CI_OnlineResource"
(
  "id" OID NOT NULL,
  "linkage" TEXT NOT NULL,
  "protocol" TEXT,
  "applicationProfile" TEXT,
  "description" TEXT,
  "function" INT2,
  CONSTRAINT "OnlineResource_primaryKey" PRIMARY KEY (id)
) WITHOUT OIDS;


CREATE TABLE metadata."CI_ResponsibleParty"
(
  "id" OID NOT NULL,
  "individualName" TEXT,
  "organisationName" TEXT,
  "positionName" TEXT,
  "contactInfo" OID,
  "role" INT2 NOT NULL,
  CONSTRAINT "ResponsibleParty_primaryKey" PRIMARY KEY (id)
) WITHOUT OIDS;


CREATE TABLE metadata."CI_Series"
(
  "id" OID NOT NULL,
  "name" TEXT,
  "issueIdentification" TEXT,
  "page" TEXT,
  CONSTRAINT "Series_primaryKey" PRIMARY KEY (id)
) WITHOUT OIDS;


CREATE TABLE metadata."CI_Telephone"
(
  "id" OID NOT NULL,
  "voice" TEXT,
  "facsimile" TEXT,
  CONSTRAINT "Telephone_primaryKey" PRIMARY KEY (id)
) WITHOUT OIDS;


CREATE TABLE metadata."MD_Constraints"
(
  "id" OID NOT NULL,
  "useLimitation" TEXT,
  CONSTRAINT "Constraints_primaryKey" PRIMARY KEY (id)
) WITHOUT OIDS;


CREATE TABLE metadata."MD_LegalConstraints"
(
  "id" OID NOT NULL,
  "accessConstraints" INT2,
  "useConstraints" INT2,
  "otherConstraints" TEXT,
  CONSTRAINT "LegalConstraints_primaryKey" PRIMARY KEY (id)
) INHERITS (metadata."MD_Constraints") WITHOUT OIDS;


CREATE TABLE metadata."MD_SecurityConstraints"
(
  "id" OID NOT NULL,
  "classification" INT2 NOT NULL,
  "userNote" TEXT,
  "classificationSystem" TEXT,
  "handlingDescription" TEXT,
  CONSTRAINT "SecurityConstraints_primaryKey" PRIMARY KEY (id)
) INHERITS (metadata."MD_Constraints") WITHOUT OIDS;


CREATE TABLE metadata."MD_ContentInformation"
(
  "id" OID NOT NULL,
  CONSTRAINT "ContentInformation_primaryKey" PRIMARY KEY (id)
) WITHOUT OIDS;


CREATE TABLE metadata."MD_CoverageDescription"
(
  "id" OID NOT NULL,
  "contentType" INT2 NOT NULL,
  "dimension" OID,
  CONSTRAINT "CoverageDescription_primaryKey" PRIMARY KEY (id)
) INHERITS (metadata."MD_ContentInformation") WITHOUT OIDS;


CREATE TABLE metadata."MD_FeatureCatalogueDescription"
(
  "id" OID NOT NULL,
  "complianceCode" BOOLEAN,
  "language" CHARACTER VARYING(5),
  "includedWithDataset" BOOLEAN NOT NULL,
  "featureTypes" TEXT,
  "featureCatalogueCitation" OID NOT NULL,
  CONSTRAINT "FeatureCatalogueDescription_primaryKey" PRIMARY KEY (id)
) INHERITS (metadata."MD_ContentInformation") WITHOUT OIDS;


CREATE TABLE metadata."MD_ImageDescription"
(
  "id" OID NOT NULL,
  "illuminationElevationAngle" DOUBLE PRECISION,
  "illuminationAzimuthAngle" DOUBLE PRECISION,
  "imagingCondition" INT2,
  "imageQualityCode" OID,
  "cloudCoverPercentage" DOUBLE PRECISION,
  "processingLevelCode" OID,
  "compressionGenerationQuantity" INTEGER,
  "triangulationIndicator" BOOLEAN,
  "radiometricCalibrationDataAvailability" BOOLEAN,
  "cameraCalibrationInformationAvailability" BOOLEAN,
  "filmDistortionInformationAvailability" BOOLEAN,
  "lensDistortionInformationAvailability" BOOLEAN,
  CONSTRAINT "ImageDescription_primaryKey" PRIMARY KEY (id)
) INHERITS (metadata."MD_CoverageDescription") WITHOUT OIDS;


CREATE TABLE metadata."MD_RangeDimension"
(
  "id" OID NOT NULL,
  "sequenceIdentifier" TEXT,
  "descriptor" TEXT,
  CONSTRAINT "RangeDimension_primaryKey" PRIMARY KEY (id)
) WITHOUT OIDS;


CREATE TABLE metadata."MD_DigitalTransferOptions"
(
  "id" OID NOT NULL,
  "unitsOfDistribution" TEXT,
  "transferSize" DOUBLE PRECISION,
  "onLine" OID,
  "offLine" OID,
  CONSTRAINT "DigitalTransferOptions_primaryKey" PRIMARY KEY (id)
) WITHOUT OIDS;


CREATE TABLE metadata."MD_Distribution"
(
  "id" OID NOT NULL,
  "distributionFormat" OID,
  "distributor" OID,
  "transferOptions" OID,
  CONSTRAINT "Distribution_primaryKey" PRIMARY KEY (id)
) WITHOUT OIDS;


CREATE TABLE metadata."MD_Distributor"
(
  "id" OID NOT NULL,
  "distributorContact" OID NOT NULL,
  "distributionOrderProcess" OID,
  "distributorFormat" OID,
  "distributorTransferOptions" OID,
  CONSTRAINT "Distributor_primaryKey" PRIMARY KEY (id)
) WITHOUT OIDS;


CREATE TABLE metadata."MD_Format"
(
  "id" OID NOT NULL,
  "name" TEXT NOT NULL,
  "version" TEXT NOT NULL,
  "amendmentNumber" TEXT,
  "specification" TEXT,
  "fileDecompressionTechnique" TEXT,
  "formatDistributor" OID,
  CONSTRAINT "Format_primaryKey" PRIMARY KEY (id)
) WITHOUT OIDS;


CREATE TABLE metadata."MD_Medium"
(
  "id" OID NOT NULL,
  "name" INT2,
  "density" DOUBLE PRECISION,
  "densityUnits" CHARACTER VARYING(10),
  "volumes" INTEGER,
  "mediumFormat" INT2,
  "mediumNote" TEXT,
  CONSTRAINT "Medium_primaryKey" PRIMARY KEY (id)
) WITHOUT OIDS;


CREATE TABLE metadata."MD_StandardOrderProcess"
(
  "id" OID NOT NULL,
  "fees" TEXT,
  "plannedAvailableDateTime" TIMESTAMP WITH TIME ZONE,
  "orderingInstructions" TEXT,
  "turnaround" TEXT,
  CONSTRAINT "StandardOrderProcess_primaryKey" PRIMARY KEY (id)
) WITHOUT OIDS;


CREATE TABLE metadata."EX_Extent"
(
  "id" OID NOT NULL,
  "description" TEXT,
  "geographicElement" OID,
  "temporalElement" OID,
  "verticalElement" OID,
  CONSTRAINT "Extent_primaryKey" PRIMARY KEY (id)
) WITHOUT OIDS;


CREATE TABLE metadata."EX_GeographicExtent"
(
  "id" OID NOT NULL,
  "extentTypeCode" BOOLEAN,
  CONSTRAINT "GeographicExtent_primaryKey" PRIMARY KEY (id)
) WITHOUT OIDS;


CREATE TABLE metadata."EX_TemporalExtent"
(
  "id" OID NOT NULL,
  CONSTRAINT "TemporalExtent_primaryKey" PRIMARY KEY (id)
) WITHOUT OIDS;


CREATE TABLE metadata."EX_VerticalExtent"
(
  "id" OID NOT NULL,
  "minimumValue" DOUBLE PRECISION NOT NULL,
  "maximumValue" DOUBLE PRECISION NOT NULL,
  "unitOfMeasure" CHARACTER VARYING(10) NOT NULL,
  CONSTRAINT "VerticalExtent_primaryKey" PRIMARY KEY (id)
) WITHOUT OIDS;


CREATE TABLE metadata."MD_BrowseGraphic"
(
  "id" OID NOT NULL,
  "fileName" TEXT NOT NULL,
  "fileDescription" TEXT,
  "fileType" TEXT,
  CONSTRAINT "BrowseGraphic_primaryKey" PRIMARY KEY (id)
) WITHOUT OIDS;


CREATE TABLE metadata."MD_Identification"
(
  "id" OID NOT NULL,
  "citation" OID NOT NULL,
  "abstract" TEXT NOT NULL,
  "purpose" TEXT,
  "credit" TEXT,
  "status" INT2,
  "pointOfContact" OID,
  "resourceMaintenance" OID,
  "graphicOverview" OID,
  "resourceFormat" OID,
  "descriptiveKeywords" OID,
  "resourceSpecificUsage" OID,
  "resourceConstraints" OID,
  CONSTRAINT "Identification_primaryKey" PRIMARY KEY (id)
) WITHOUT OIDS;


CREATE TABLE metadata."MD_Keywords"
(
  "id" OID NOT NULL,
  "keyword" TEXT NOT NULL,
  "type" INT2,
  "thesaurusName" OID,
  CONSTRAINT "Keywords_primaryKey" PRIMARY KEY (id)
) WITHOUT OIDS;


CREATE TABLE metadata."MD_Resolution"
(
  "id" OID NOT NULL,
  "equivalentScale" DOUBLE PRECISION,
  "distance" DOUBLE PRECISION,
  CONSTRAINT "Resolution_primaryKey" PRIMARY KEY (id)
) WITHOUT OIDS;


CREATE TABLE metadata."MD_ServiceIdentification"
(
  "id" OID NOT NULL,
  CONSTRAINT "ServiceIdentification_primaryKey" PRIMARY KEY (id)
) INHERITS (metadata."MD_Identification") WITHOUT OIDS;


CREATE TABLE metadata."MD_Usage"
(
  "id" OID NOT NULL,
  "specificUsage" TEXT NOT NULL,
  "usageDateTime" TIMESTAMP WITH TIME ZONE,
  "userDeterminedLimitations" TEXT,
  "userContactInfo" OID NOT NULL,
  CONSTRAINT "Usage_primaryKey" PRIMARY KEY (id)
) WITHOUT OIDS;


CREATE TABLE metadata."LI_Lineage"
(
  "id" OID NOT NULL,
  "statement" TEXT,
  "processStep" OID,
  "source" OID,
  CONSTRAINT "Lineage_primaryKey" PRIMARY KEY (id)
) WITHOUT OIDS;


CREATE TABLE metadata."LI_ProcessStep"
(
  "id" OID NOT NULL,
  "description" TEXT NOT NULL,
  "rationale" TEXT,
  "dateTime" TIMESTAMP WITH TIME ZONE,
  "processor" OID,
  "source" OID,
  CONSTRAINT "ProcessStep_primaryKey" PRIMARY KEY (id)
) WITHOUT OIDS;


CREATE TABLE metadata."LI_Source"
(
  "id" OID NOT NULL,
  "description" TEXT NOT NULL,
  "scaleDenominator" BIGINT,
  "sourceCitation" OID,
  "sourceExtent" OID,
  "sourceStep" OID,
  CONSTRAINT "Source_primaryKey" PRIMARY KEY (id)
) WITHOUT OIDS;


CREATE TABLE metadata."MD_MaintenanceInformation"
(
  "id" OID NOT NULL,
  "maintenanceAndUpdateFrequency" INT2 NOT NULL,
  "dateOfNextUpdate" TIMESTAMP WITH TIME ZONE,
  "userDefinedMaintenanceFrequency" BIGINT,
  "updateScope" INT2,
  "updateScopeDescription" OID,
  "maintenanceNote" TEXT,
  CONSTRAINT "MaintenanceInformation_primaryKey" PRIMARY KEY (id)
) WITHOUT OIDS;


CREATE TABLE metadata."MD_ScopeDescription"
(
  "id" OID NOT NULL,
  CONSTRAINT "ScopeDescription_primaryKey" PRIMARY KEY (id)
) WITHOUT OIDS;


CREATE TABLE metadata."DQ_DataQuality"
(
  "id" OID NOT NULL,
  "scope" OID NOT NULL,
  "report" OID,
  "lineage" OID,
  CONSTRAINT "DataQuality_primaryKey" PRIMARY KEY (id)
) WITHOUT OIDS;


CREATE TABLE metadata."DQ_Element"
(
  "id" OID NOT NULL,
  "nameOfMeasure" TEXT,
  "measureIdentification" OID,
  "measureDescription" TEXT,
  "evaluationMethodType" INT2,
  "evaluationMethodDescription" TEXT,
  "evaluationProcedure" OID,
  "dateTime" TIMESTAMP WITH TIME ZONE,
  "result" OID NOT NULL,
  CONSTRAINT "Element_primaryKey" PRIMARY KEY (id)
) WITHOUT OIDS;


CREATE TABLE metadata."DQ_LogicalConsistency"
(
  "id" OID NOT NULL,
  CONSTRAINT "LogicalConsistency_primaryKey" PRIMARY KEY (id)
) INHERITS (metadata."DQ_Element") WITHOUT OIDS;


CREATE TABLE metadata."DQ_PositionalAccuracy"
(
  "id" OID NOT NULL,
  CONSTRAINT "PositionalAccuracy_primaryKey" PRIMARY KEY (id)
) INHERITS (metadata."DQ_Element") WITHOUT OIDS;


CREATE TABLE metadata."DQ_RelativeInternalPositionalAccuracy"
(
  "id" OID NOT NULL,
  CONSTRAINT "RelativeInternalPositionalAccuracy_primaryKey" PRIMARY KEY (id)
) INHERITS (metadata."DQ_PositionalAccuracy") WITHOUT OIDS;


CREATE TABLE metadata."DQ_Result"
(
  "id" OID NOT NULL,
  CONSTRAINT "Result_primaryKey" PRIMARY KEY (id)
) WITHOUT OIDS;


CREATE TABLE metadata."DQ_Scope"
(
  "id" OID NOT NULL,
  "level" INT2 NOT NULL,
  "extent" OID,
  CONSTRAINT "Scope_primaryKey" PRIMARY KEY (id)
) WITHOUT OIDS;


CREATE TABLE metadata."DQ_TemporalAccuracy"
(
  "id" OID NOT NULL,
  CONSTRAINT "TemporalAccuracy_primaryKey" PRIMARY KEY (id)
) INHERITS (metadata."DQ_Element") WITHOUT OIDS;


CREATE TABLE metadata."DQ_TemporalConsistency"
(
  "id" OID NOT NULL,
  CONSTRAINT "TemporalConsistency_primaryKey" PRIMARY KEY (id)
) INHERITS (metadata."DQ_TemporalAccuracy") WITHOUT OIDS;


CREATE TABLE metadata."DQ_TemporalValidity"
(
  "id" OID NOT NULL,
  CONSTRAINT "TemporalValidity_primaryKey" PRIMARY KEY (id)
) INHERITS (metadata."DQ_TemporalAccuracy") WITHOUT OIDS;


CREATE TABLE metadata."DQ_ThematicAccuracy"
(
  "id" OID NOT NULL,
  CONSTRAINT "ThematicAccuracy_primaryKey" PRIMARY KEY (id)
) INHERITS (metadata."DQ_Element") WITHOUT OIDS;


CREATE TABLE metadata."DQ_ThematicClassificationCorrectness"
(
  "id" OID NOT NULL,
  CONSTRAINT "ThematicClassificationCorrectness_primaryKey" PRIMARY KEY (id)
) INHERITS (metadata."DQ_ThematicAccuracy") WITHOUT OIDS;


CREATE TABLE metadata."DQ_TopologicalConsistency"
(
  "id" OID NOT NULL,
  CONSTRAINT "TopologicalConsistency_primaryKey" PRIMARY KEY (id)
) INHERITS (metadata."DQ_LogicalConsistency") WITHOUT OIDS;


CREATE TABLE metadata."MD_Dimension"
(
  "id" OID NOT NULL,
  "dimensionName" INT2 NOT NULL,
  "dimensionSize" INTEGER NOT NULL,
  "resolution" DOUBLE PRECISION,
  CONSTRAINT "Dimension_primaryKey" PRIMARY KEY (id)
) WITHOUT OIDS;


CREATE TABLE metadata."MD_GeometricObjects"
(
  "id" OID NOT NULL,
  "geometricObjectType" INT2 NOT NULL,
  "geometricObjectCount" INTEGER,
  CONSTRAINT "GeometricObjects_primaryKey" PRIMARY KEY (id)
) WITHOUT OIDS;


CREATE TABLE metadata."MD_SpatialRepresentation"
(
  "id" OID NOT NULL,
  CONSTRAINT "SpatialRepresentation_primaryKey" PRIMARY KEY (id)
) WITHOUT OIDS;


CREATE TABLE metadata."MD_VectorSpatialRepresentation"
(
  "id" OID NOT NULL,
  "topologyLevel" INT2,
  "geometricObjects" OID,
  CONSTRAINT "VectorSpatialRepresentation_primaryKey" PRIMARY KEY (id)
) INHERITS (metadata."MD_SpatialRepresentation") WITHOUT OIDS;


CREATE TABLE metadata."MD_Band"
(
  "id" OID NOT NULL,
  "maxValue" DOUBLE PRECISION,
  "minValue" DOUBLE PRECISION,
  "units" CHARACTER VARYING(10),
  "peakResponse" DOUBLE PRECISION,
  "bitsPerValue" INTEGER,
  "toneGradation" INTEGER,
  "scaleFactor" DOUBLE PRECISION,
  "offset" DOUBLE PRECISION,
  CONSTRAINT "Band_primaryKey" PRIMARY KEY (id)
) INHERITS (metadata."MD_RangeDimension") WITHOUT OIDS;


CREATE TABLE metadata."EX_BoundingPolygon"
(
  "id" OID NOT NULL,
  "polygon" PATH NOT NULL,
  CONSTRAINT "BoundingPolygon_primaryKey" PRIMARY KEY (id)
) INHERITS (metadata."EX_GeographicExtent") WITHOUT OIDS;


CREATE TABLE metadata."EX_GeographicBoundingBox"
(
  "id" OID NOT NULL,
  "westBoundLongitude" DOUBLE PRECISION NOT NULL,
  "eastBoundLongitude" DOUBLE PRECISION NOT NULL,
  "southBoundLatitude" DOUBLE PRECISION NOT NULL,
  "northBoundLatitude" DOUBLE PRECISION NOT NULL,
  CONSTRAINT "GeographicBoundingBox_primaryKey" PRIMARY KEY (id)
) INHERITS (metadata."EX_GeographicExtent") WITHOUT OIDS;


CREATE TABLE metadata."EX_GeographicDescription"
(
  "id" OID NOT NULL,
  "geographicIdentifier" OID NOT NULL,
  CONSTRAINT "GeographicDescription_primaryKey" PRIMARY KEY (id)
) INHERITS (metadata."EX_GeographicExtent") WITHOUT OIDS;


CREATE TABLE metadata."EX_SpatialTemporalExtent"
(
  "id" OID NOT NULL,
  "spatialExtent" OID NOT NULL,
  CONSTRAINT "SpatialTemporalExtent_primaryKey" PRIMARY KEY (id)
) INHERITS (metadata."EX_TemporalExtent") WITHOUT OIDS;


CREATE TABLE metadata."MD_DataIdentification"
(
  "id" OID NOT NULL,
  "spatialRepresentationType" INT2,
  "spatialResolution" OID,
  "language" CHARACTER VARYING(5) NOT NULL,
  "characterSet" TEXT,
  "topicCategory" INT2 NOT NULL,
  "geographicBox" OID,
  "geographicDescription" OID,
  "environmentDescription" TEXT,
  "extent" OID,
  "supplementalInformation" TEXT,
  CONSTRAINT "DataIdentification_primaryKey" PRIMARY KEY (id)
) INHERITS (metadata."MD_Identification") WITHOUT OIDS;


CREATE TABLE metadata."DQ_AbsoluteExternalPositionalAccuracy"
(
  "id" OID NOT NULL,
  CONSTRAINT "AbsoluteExternalPositionalAccuracy_primaryKey" PRIMARY KEY (id)
) INHERITS (metadata."DQ_PositionalAccuracy") WITHOUT OIDS;


CREATE TABLE metadata."DQ_AccuracyOfATimeMeasurement"
(
  "id" OID NOT NULL,
  CONSTRAINT "AccuracyOfATimeMeasurement_primaryKey" PRIMARY KEY (id)
) INHERITS (metadata."DQ_TemporalAccuracy") WITHOUT OIDS;


CREATE TABLE metadata."DQ_Completeness"
(
  "id" OID NOT NULL,
  CONSTRAINT "Completeness_primaryKey" PRIMARY KEY (id)
) INHERITS (metadata."DQ_Element") WITHOUT OIDS;


CREATE TABLE metadata."DQ_CompletenessCommission"
(
  "id" OID NOT NULL,
  CONSTRAINT "CompletenessCommission_primaryKey" PRIMARY KEY (id)
) INHERITS (metadata."DQ_Completeness") WITHOUT OIDS;


CREATE TABLE metadata."DQ_CompletenessOmission"
(
  "id" OID NOT NULL,
  CONSTRAINT "CompletenessOmission_primaryKey" PRIMARY KEY (id)
) INHERITS (metadata."DQ_Completeness") WITHOUT OIDS;


CREATE TABLE metadata."DQ_ConceptualConsistency"
(
  "id" OID NOT NULL,
  CONSTRAINT "ConceptualConsistency_primaryKey" PRIMARY KEY (id)
) INHERITS (metadata."DQ_LogicalConsistency") WITHOUT OIDS;


CREATE TABLE metadata."DQ_ConformanceResult"
(
  "id" OID NOT NULL,
  "specification" OID NOT NULL,
  "explanation" TEXT NOT NULL,
  "pass" BOOLEAN NOT NULL,
  CONSTRAINT "ConformanceResult_primaryKey" PRIMARY KEY (id)
) INHERITS (metadata."DQ_Result") WITHOUT OIDS;


CREATE TABLE metadata."DQ_DomainConsistency"
(
  "id" OID NOT NULL,
  CONSTRAINT "DomainConsistency_primaryKey" PRIMARY KEY (id)
) INHERITS (metadata."DQ_LogicalConsistency") WITHOUT OIDS;


CREATE TABLE metadata."DQ_FormalConsistency"
(
  "id" OID NOT NULL,
  CONSTRAINT "FormalConsistency_primaryKey" PRIMARY KEY (id)
) INHERITS (metadata."DQ_LogicalConsistency") WITHOUT OIDS;


CREATE TABLE metadata."DQ_GriddedDataPositionalAccuracy"
(
  "id" OID NOT NULL,
  CONSTRAINT "GriddedDataPositionalAccuracy_primaryKey" PRIMARY KEY (id)
) INHERITS (metadata."DQ_PositionalAccuracy") WITHOUT OIDS;


CREATE TABLE metadata."DQ_NonQuantitativeAttributeCorrectness"
(
  "id" OID NOT NULL,
  CONSTRAINT "NonQuantitativeAttributeCorrectness_primaryKey" PRIMARY KEY (id)
) INHERITS (metadata."DQ_ThematicAccuracy") WITHOUT OIDS;


CREATE TABLE metadata."DQ_QuantitativeAttributeAccuracy"
(
  "id" OID NOT NULL,
  CONSTRAINT "QuantitativeAttributeAccuracy_primaryKey" PRIMARY KEY (id)
) INHERITS (metadata."DQ_ThematicAccuracy") WITHOUT OIDS;


CREATE TABLE metadata."DQ_QuantitativeResult"
(
  "id" OID NOT NULL,
  "value" DOUBLE PRECISION NOT NULL,
  "valueUnit" CHARACTER VARYING(10),
  "errorStatistic" TEXT,
  CONSTRAINT "QuantitativeResult_primaryKey" PRIMARY KEY (id)
) INHERITS (metadata."DQ_Result") WITHOUT OIDS;


CREATE TABLE metadata."MD_GridSpatialRepresentation"
(
  "id" OID NOT NULL,
  "numberOfDimensions" INTEGER NOT NULL,
  "axisDimensionsProperties" OID NOT NULL,
  "cellGeometry" INT2 NOT NULL,
  "transformationParameterAvailability" BOOLEAN NOT NULL,
  CONSTRAINT "GridSpatialRepresentation_primaryKey" PRIMARY KEY (id)
) INHERITS (metadata."MD_SpatialRepresentation") WITHOUT OIDS;


CREATE TABLE metadata."MD_Georectified"
(
  "id" OID NOT NULL,
  "checkPointAvailability" BOOLEAN NOT NULL,
  "checkPointDescription" TEXT,
  "cornerPoints" PATH NOT NULL,
  "centerPoint" PATH,
  "pointInPixel" INT2 NOT NULL,
  "transformationDimensionDescription" TEXT,
  "transformationDimensionMapping" TEXT,
  CONSTRAINT "Georectified_primaryKey" PRIMARY KEY (id)
) INHERITS (metadata."MD_GridSpatialRepresentation") WITHOUT OIDS;


CREATE TABLE metadata."MD_Georeferenceable"
(
  "id" OID NOT NULL,
  "controlPointAvailability" BOOLEAN NOT NULL,
  "orientationParameterAvailability" BOOLEAN NOT NULL,
  "orientationParameterDescription" TEXT,
  "parameterCitation" OID,
  CONSTRAINT "Georeferenceable_primaryKey" PRIMARY KEY (id)
) INHERITS (metadata."MD_GridSpatialRepresentation") WITHOUT OIDS;



ALTER TABLE metadata."MD_ApplicationSchemaInformation" ADD CONSTRAINT "ApplicationSchemaInformation_name" FOREIGN KEY ("name") REFERENCES metadata."CI_Citation" (id) ON UPDATE CASCADE ON DELETE RESTRICT;
ALTER TABLE metadata."MD_ApplicationSchemaInformation" ADD CONSTRAINT "ApplicationSchemaInformation_featureCatalogueSupplement" FOREIGN KEY ("featureCatalogueSupplement") REFERENCES metadata."MD_SpatialAttributeSupplement" (id) ON UPDATE CASCADE ON DELETE RESTRICT;
ALTER TABLE metadata."MD_ExtendedElementInformation" ADD CONSTRAINT "ExtendedElementInformation_obligation" FOREIGN KEY ("obligation") REFERENCES metadata."MD_ObligationCode" (code) ON UPDATE CASCADE ON DELETE RESTRICT;
ALTER TABLE metadata."MD_ExtendedElementInformation" ADD CONSTRAINT "ExtendedElementInformation_dataType" FOREIGN KEY ("dataType") REFERENCES metadata."MD_DatatypeCode" (code) ON UPDATE CASCADE ON DELETE RESTRICT;
ALTER TABLE metadata."MD_ExtendedElementInformation" ADD CONSTRAINT "ExtendedElementInformation_source" FOREIGN KEY ("source") REFERENCES metadata."CI_ResponsibleParty" (id) ON UPDATE CASCADE ON DELETE RESTRICT;
ALTER TABLE metadata."MD_Identifier" ADD CONSTRAINT "Identifier_authority" FOREIGN KEY ("authority") REFERENCES metadata."CI_Citation" (id) ON UPDATE CASCADE ON DELETE RESTRICT;
ALTER TABLE metadata."MD_MetaData" ADD CONSTRAINT "MetaData_language" FOREIGN KEY ("language") REFERENCES "Locale" (code) ON UPDATE CASCADE ON DELETE RESTRICT;
ALTER TABLE metadata."MD_MetaData" ADD CONSTRAINT "MetaData_hierarchyLevel" FOREIGN KEY ("hierarchyLevel") REFERENCES metadata."MD_ScopeCode" (code) ON UPDATE CASCADE ON DELETE RESTRICT;
ALTER TABLE metadata."MD_MetaData" ADD CONSTRAINT "MetaData_contact" FOREIGN KEY ("contact") REFERENCES metadata."CI_ResponsibleParty" (id) ON UPDATE CASCADE ON DELETE RESTRICT;
ALTER TABLE metadata."MD_MetaData" ADD CONSTRAINT "MetaData_spatialRepresentationInfo" FOREIGN KEY ("spatialRepresentationInfo") REFERENCES metadata."MD_SpatialRepresentation" (id) ON UPDATE CASCADE ON DELETE RESTRICT;
ALTER TABLE metadata."MD_MetaData" ADD CONSTRAINT "MetaData_metadataExtensionInfo" FOREIGN KEY ("metadataExtensionInfo") REFERENCES metadata."MD_MetadataExtensionInformation" (id) ON UPDATE CASCADE ON DELETE RESTRICT;
ALTER TABLE metadata."MD_MetaData" ADD CONSTRAINT "MetaData_identificationInfo" FOREIGN KEY ("identificationInfo") REFERENCES metadata."MD_Identification" (id) ON UPDATE CASCADE ON DELETE RESTRICT;
ALTER TABLE metadata."MD_MetaData" ADD CONSTRAINT "MetaData_contentInfo" FOREIGN KEY ("contentInfo") REFERENCES metadata."MD_ContentInformation" (id) ON UPDATE CASCADE ON DELETE RESTRICT;
ALTER TABLE metadata."MD_MetaData" ADD CONSTRAINT "MetaData_distributionInfo" FOREIGN KEY ("distributionInfo") REFERENCES metadata."MD_Distribution" (id) ON UPDATE CASCADE ON DELETE RESTRICT;
ALTER TABLE metadata."MD_MetaData" ADD CONSTRAINT "MetaData_dataQualityInfo" FOREIGN KEY ("dataQualityInfo") REFERENCES metadata."DQ_DataQuality" (id) ON UPDATE CASCADE ON DELETE RESTRICT;
ALTER TABLE metadata."MD_MetaData" ADD CONSTRAINT "MetaData_portrayalCatalogueInfo" FOREIGN KEY ("portrayalCatalogueInfo") REFERENCES metadata."MD_PortrayalCatalogueReference" (id) ON UPDATE CASCADE ON DELETE RESTRICT;
ALTER TABLE metadata."MD_MetaData" ADD CONSTRAINT "MetaData_metadataConstraints" FOREIGN KEY ("metadataConstraints") REFERENCES metadata."MD_Constraints" (id) ON UPDATE CASCADE ON DELETE RESTRICT;
ALTER TABLE metadata."MD_MetaData" ADD CONSTRAINT "MetaData_applicationSchemaInfo" FOREIGN KEY ("applicationSchemaInfo") REFERENCES metadata."MD_ApplicationSchemaInformation" (id) ON UPDATE CASCADE ON DELETE RESTRICT;
ALTER TABLE metadata."MD_MetaData" ADD CONSTRAINT "MetaData_metadataMaintenance" FOREIGN KEY ("metadataMaintenance") REFERENCES metadata."MD_MaintenanceInformation" (id) ON UPDATE CASCADE ON DELETE RESTRICT;
ALTER TABLE metadata."MD_MetadataExtensionInformation" ADD CONSTRAINT "MetadataExtensionInformation_extensionOnLineResource" FOREIGN KEY ("extensionOnLineResource") REFERENCES metadata."CI_OnlineResource" (id) ON UPDATE CASCADE ON DELETE RESTRICT;
ALTER TABLE metadata."MD_MetadataExtensionInformation" ADD CONSTRAINT "MetadataExtensionInformation_extendedElementInformation" FOREIGN KEY ("extendedElementInformation") REFERENCES metadata."MD_ExtendedElementInformation" (id) ON UPDATE CASCADE ON DELETE RESTRICT;
ALTER TABLE metadata."MD_PortrayalCatalogueReference" ADD CONSTRAINT "PortrayalCatalogueReference_portrayalCatalogueCitation" FOREIGN KEY ("portrayalCatalogueCitation") REFERENCES metadata."CI_Citation" (id) ON UPDATE CASCADE ON DELETE RESTRICT;
ALTER TABLE metadata."MD_SpatialAttributeSupplement" ADD CONSTRAINT "SpatialAttributeSupplement_theFeatureTypeList" FOREIGN KEY ("theFeatureTypeList") REFERENCES metadata."MD_FeatureTypeList" (id) ON UPDATE CASCADE ON DELETE RESTRICT;
ALTER TABLE metadata."CI_Citation" ADD CONSTRAINT "Citation_date" FOREIGN KEY ("date") REFERENCES metadata."CI_Date" (id) ON UPDATE CASCADE ON DELETE RESTRICT;
ALTER TABLE metadata."CI_Citation" ADD CONSTRAINT "Citation_citedResponsibleParty" FOREIGN KEY ("citedResponsibleParty") REFERENCES metadata."CI_ResponsibleParty" (id) ON UPDATE CASCADE ON DELETE RESTRICT;
ALTER TABLE metadata."CI_Citation" ADD CONSTRAINT "Citation_presentationForm" FOREIGN KEY ("presentationForm") REFERENCES metadata."CI_PresentationFormCode" (code) ON UPDATE CASCADE ON DELETE RESTRICT;
ALTER TABLE metadata."CI_Citation" ADD CONSTRAINT "Citation_series" FOREIGN KEY ("series") REFERENCES metadata."CI_Series" (id) ON UPDATE CASCADE ON DELETE RESTRICT;
ALTER TABLE metadata."CI_Date" ADD CONSTRAINT "Date_dateType" FOREIGN KEY ("dateType") REFERENCES metadata."CI_DateType" (code) ON UPDATE CASCADE ON DELETE RESTRICT;
ALTER TABLE metadata."CI_Contact" ADD CONSTRAINT "Contact_phone" FOREIGN KEY ("phone") REFERENCES metadata."CI_Telephone" (id) ON UPDATE CASCADE ON DELETE RESTRICT;
ALTER TABLE metadata."CI_Contact" ADD CONSTRAINT "Contact_address" FOREIGN KEY ("address") REFERENCES metadata."CI_Address" (id) ON UPDATE CASCADE ON DELETE RESTRICT;
ALTER TABLE metadata."CI_Contact" ADD CONSTRAINT "Contact_onLineResource" FOREIGN KEY ("onLineResource") REFERENCES metadata."CI_OnlineResource" (id) ON UPDATE CASCADE ON DELETE RESTRICT;
ALTER TABLE metadata."CI_OnlineResource" ADD CONSTRAINT "OnlineResource_function" FOREIGN KEY ("function") REFERENCES metadata."CI_OnLineFunctionCode" (code) ON UPDATE CASCADE ON DELETE RESTRICT;
ALTER TABLE metadata."CI_ResponsibleParty" ADD CONSTRAINT "ResponsibleParty_contactInfo" FOREIGN KEY ("contactInfo") REFERENCES metadata."CI_Contact" (id) ON UPDATE CASCADE ON DELETE RESTRICT;
ALTER TABLE metadata."CI_ResponsibleParty" ADD CONSTRAINT "ResponsibleParty_role" FOREIGN KEY ("role") REFERENCES metadata."CI_RoleCode" (code) ON UPDATE CASCADE ON DELETE RESTRICT;
ALTER TABLE metadata."MD_LegalConstraints" ADD CONSTRAINT "LegalConstraints_accessConstraints" FOREIGN KEY ("accessConstraints") REFERENCES metadata."MD_RestrictionCode" (code) ON UPDATE CASCADE ON DELETE RESTRICT;
ALTER TABLE metadata."MD_LegalConstraints" ADD CONSTRAINT "LegalConstraints_useConstraints" FOREIGN KEY ("useConstraints") REFERENCES metadata."MD_RestrictionCode" (code) ON UPDATE CASCADE ON DELETE RESTRICT;
ALTER TABLE metadata."MD_SecurityConstraints" ADD CONSTRAINT "SecurityConstraints_classification" FOREIGN KEY ("classification") REFERENCES metadata."MD_ClassificationCode" (code) ON UPDATE CASCADE ON DELETE RESTRICT;
ALTER TABLE metadata."MD_CoverageDescription" ADD CONSTRAINT "CoverageDescription_contentType" FOREIGN KEY ("contentType") REFERENCES metadata."MD_CoverageContentTypeCode" (code) ON UPDATE CASCADE ON DELETE RESTRICT;
ALTER TABLE metadata."MD_CoverageDescription" ADD CONSTRAINT "CoverageDescription_dimension" FOREIGN KEY ("dimension") REFERENCES metadata."MD_RangeDimension" (id) ON UPDATE CASCADE ON DELETE RESTRICT;
ALTER TABLE metadata."MD_FeatureCatalogueDescription" ADD CONSTRAINT "FeatureCatalogueDescription_language" FOREIGN KEY ("language") REFERENCES "Locale" (code) ON UPDATE CASCADE ON DELETE RESTRICT;
ALTER TABLE metadata."MD_FeatureCatalogueDescription" ADD CONSTRAINT "FeatureCatalogueDescription_featureCatalogueCitation" FOREIGN KEY ("featureCatalogueCitation") REFERENCES metadata."CI_Citation" (id) ON UPDATE CASCADE ON DELETE RESTRICT;
ALTER TABLE metadata."MD_ImageDescription" ADD CONSTRAINT "ImageDescription_imagingCondition" FOREIGN KEY ("imagingCondition") REFERENCES metadata."MD_ImagingConditionCode" (code) ON UPDATE CASCADE ON DELETE RESTRICT;
ALTER TABLE metadata."MD_ImageDescription" ADD CONSTRAINT "ImageDescription_imageQualityCode" FOREIGN KEY ("imageQualityCode") REFERENCES metadata."MD_Identifier" (id) ON UPDATE CASCADE ON DELETE RESTRICT;
ALTER TABLE metadata."MD_ImageDescription" ADD CONSTRAINT "ImageDescription_processingLevelCode" FOREIGN KEY ("processingLevelCode") REFERENCES metadata."MD_Identifier" (id) ON UPDATE CASCADE ON DELETE RESTRICT;
ALTER TABLE metadata."MD_DigitalTransferOptions" ADD CONSTRAINT "DigitalTransferOptions_onLine" FOREIGN KEY ("onLine") REFERENCES metadata."CI_OnlineResource" (id) ON UPDATE CASCADE ON DELETE RESTRICT;
ALTER TABLE metadata."MD_DigitalTransferOptions" ADD CONSTRAINT "DigitalTransferOptions_offLine" FOREIGN KEY ("offLine") REFERENCES metadata."MD_Medium" (id) ON UPDATE CASCADE ON DELETE RESTRICT;
ALTER TABLE metadata."MD_Distribution" ADD CONSTRAINT "Distribution_distributionFormat" FOREIGN KEY ("distributionFormat") REFERENCES metadata."MD_Format" (id) ON UPDATE CASCADE ON DELETE RESTRICT;
ALTER TABLE metadata."MD_Distribution" ADD CONSTRAINT "Distribution_distributor" FOREIGN KEY ("distributor") REFERENCES metadata."MD_Distributor" (id) ON UPDATE CASCADE ON DELETE RESTRICT;
ALTER TABLE metadata."MD_Distribution" ADD CONSTRAINT "Distribution_transferOptions" FOREIGN KEY ("transferOptions") REFERENCES metadata."MD_DigitalTransferOptions" (id) ON UPDATE CASCADE ON DELETE RESTRICT;
ALTER TABLE metadata."MD_Distributor" ADD CONSTRAINT "Distributor_distributorContact" FOREIGN KEY ("distributorContact") REFERENCES metadata."CI_ResponsibleParty" (id) ON UPDATE CASCADE ON DELETE RESTRICT;
ALTER TABLE metadata."MD_Distributor" ADD CONSTRAINT "Distributor_distributionOrderProcess" FOREIGN KEY ("distributionOrderProcess") REFERENCES metadata."MD_StandardOrderProcess" (id) ON UPDATE CASCADE ON DELETE RESTRICT;
ALTER TABLE metadata."MD_Distributor" ADD CONSTRAINT "Distributor_distributorFormat" FOREIGN KEY ("distributorFormat") REFERENCES metadata."MD_Format" (id) ON UPDATE CASCADE ON DELETE RESTRICT;
ALTER TABLE metadata."MD_Distributor" ADD CONSTRAINT "Distributor_distributorTransferOptions" FOREIGN KEY ("distributorTransferOptions") REFERENCES metadata."MD_DigitalTransferOptions" (id) ON UPDATE CASCADE ON DELETE RESTRICT;
ALTER TABLE metadata."MD_Format" ADD CONSTRAINT "Format_formatDistributor" FOREIGN KEY ("formatDistributor") REFERENCES metadata."MD_Distributor" (id) ON UPDATE CASCADE ON DELETE RESTRICT;
ALTER TABLE metadata."MD_Medium" ADD CONSTRAINT "Medium_name" FOREIGN KEY ("name") REFERENCES metadata."MD_MediumNameCode" (code) ON UPDATE CASCADE ON DELETE RESTRICT;
ALTER TABLE metadata."MD_Medium" ADD CONSTRAINT "Medium_densityUnits" FOREIGN KEY ("densityUnits") REFERENCES "Unit" (symbol) ON UPDATE CASCADE ON DELETE RESTRICT;
ALTER TABLE metadata."MD_Medium" ADD CONSTRAINT "Medium_mediumFormat" FOREIGN KEY ("mediumFormat") REFERENCES metadata."MD_MediumFormatCode" (code) ON UPDATE CASCADE ON DELETE RESTRICT;
ALTER TABLE metadata."EX_Extent" ADD CONSTRAINT "Extent_geographicElement" FOREIGN KEY ("geographicElement") REFERENCES metadata."EX_GeographicExtent" (id) ON UPDATE CASCADE ON DELETE RESTRICT;
ALTER TABLE metadata."EX_Extent" ADD CONSTRAINT "Extent_temporalElement" FOREIGN KEY ("temporalElement") REFERENCES metadata."EX_TemporalExtent" (id) ON UPDATE CASCADE ON DELETE RESTRICT;
ALTER TABLE metadata."EX_Extent" ADD CONSTRAINT "Extent_verticalElement" FOREIGN KEY ("verticalElement") REFERENCES metadata."EX_VerticalExtent" (id) ON UPDATE CASCADE ON DELETE RESTRICT;
ALTER TABLE metadata."EX_VerticalExtent" ADD CONSTRAINT "VerticalExtent_unitOfMeasure" FOREIGN KEY ("unitOfMeasure") REFERENCES "Unit" (symbol) ON UPDATE CASCADE ON DELETE RESTRICT;
ALTER TABLE metadata."MD_Identification" ADD CONSTRAINT "Identification_citation" FOREIGN KEY ("citation") REFERENCES metadata."CI_Citation" (id) ON UPDATE CASCADE ON DELETE RESTRICT;
ALTER TABLE metadata."MD_Identification" ADD CONSTRAINT "Identification_status" FOREIGN KEY ("status") REFERENCES metadata."MD_ProgressCode" (code) ON UPDATE CASCADE ON DELETE RESTRICT;
ALTER TABLE metadata."MD_Identification" ADD CONSTRAINT "Identification_pointOfContact" FOREIGN KEY ("pointOfContact") REFERENCES metadata."CI_ResponsibleParty" (id) ON UPDATE CASCADE ON DELETE RESTRICT;
ALTER TABLE metadata."MD_Identification" ADD CONSTRAINT "Identification_resourceMaintenance" FOREIGN KEY ("resourceMaintenance") REFERENCES metadata."MD_MaintenanceInformation" (id) ON UPDATE CASCADE ON DELETE RESTRICT;
ALTER TABLE metadata."MD_Identification" ADD CONSTRAINT "Identification_graphicOverview" FOREIGN KEY ("graphicOverview") REFERENCES metadata."MD_BrowseGraphic" (id) ON UPDATE CASCADE ON DELETE RESTRICT;
ALTER TABLE metadata."MD_Identification" ADD CONSTRAINT "Identification_resourceFormat" FOREIGN KEY ("resourceFormat") REFERENCES metadata."MD_Format" (id) ON UPDATE CASCADE ON DELETE RESTRICT;
ALTER TABLE metadata."MD_Identification" ADD CONSTRAINT "Identification_descriptiveKeywords" FOREIGN KEY ("descriptiveKeywords") REFERENCES metadata."MD_Keywords" (id) ON UPDATE CASCADE ON DELETE RESTRICT;
ALTER TABLE metadata."MD_Identification" ADD CONSTRAINT "Identification_resourceSpecificUsage" FOREIGN KEY ("resourceSpecificUsage") REFERENCES metadata."MD_Usage" (id) ON UPDATE CASCADE ON DELETE RESTRICT;
ALTER TABLE metadata."MD_Identification" ADD CONSTRAINT "Identification_resourceConstraints" FOREIGN KEY ("resourceConstraints") REFERENCES metadata."MD_Constraints" (id) ON UPDATE CASCADE ON DELETE RESTRICT;
ALTER TABLE metadata."MD_Keywords" ADD CONSTRAINT "Keywords_type" FOREIGN KEY ("type") REFERENCES metadata."MD_KeywordTypeCode" (code) ON UPDATE CASCADE ON DELETE RESTRICT;
ALTER TABLE metadata."MD_Keywords" ADD CONSTRAINT "Keywords_thesaurusName" FOREIGN KEY ("thesaurusName") REFERENCES metadata."CI_Citation" (id) ON UPDATE CASCADE ON DELETE RESTRICT;
ALTER TABLE metadata."MD_Usage" ADD CONSTRAINT "Usage_userContactInfo" FOREIGN KEY ("userContactInfo") REFERENCES metadata."CI_ResponsibleParty" (id) ON UPDATE CASCADE ON DELETE RESTRICT;
ALTER TABLE metadata."LI_Lineage" ADD CONSTRAINT "Lineage_processStep" FOREIGN KEY ("processStep") REFERENCES metadata."LI_ProcessStep" (id) ON UPDATE CASCADE ON DELETE RESTRICT;
ALTER TABLE metadata."LI_Lineage" ADD CONSTRAINT "Lineage_source" FOREIGN KEY ("source") REFERENCES metadata."LI_Source" (id) ON UPDATE CASCADE ON DELETE RESTRICT;
ALTER TABLE metadata."LI_ProcessStep" ADD CONSTRAINT "ProcessStep_processor" FOREIGN KEY ("processor") REFERENCES metadata."CI_ResponsibleParty" (id) ON UPDATE CASCADE ON DELETE RESTRICT;
ALTER TABLE metadata."LI_ProcessStep" ADD CONSTRAINT "ProcessStep_source" FOREIGN KEY ("source") REFERENCES metadata."LI_Source" (id) ON UPDATE CASCADE ON DELETE RESTRICT;
ALTER TABLE metadata."LI_Source" ADD CONSTRAINT "Source_sourceCitation" FOREIGN KEY ("sourceCitation") REFERENCES metadata."CI_Citation" (id) ON UPDATE CASCADE ON DELETE RESTRICT;
ALTER TABLE metadata."LI_Source" ADD CONSTRAINT "Source_sourceExtent" FOREIGN KEY ("sourceExtent") REFERENCES metadata."EX_Extent" (id) ON UPDATE CASCADE ON DELETE RESTRICT;
ALTER TABLE metadata."LI_Source" ADD CONSTRAINT "Source_sourceStep" FOREIGN KEY ("sourceStep") REFERENCES metadata."LI_ProcessStep" (id) ON UPDATE CASCADE ON DELETE RESTRICT;
ALTER TABLE metadata."MD_MaintenanceInformation" ADD CONSTRAINT "MaintenanceInformation_maintenanceAndUpdateFrequency" FOREIGN KEY ("maintenanceAndUpdateFrequency") REFERENCES metadata."MD_MaintenanceFrequencyCode" (code) ON UPDATE CASCADE ON DELETE RESTRICT;
ALTER TABLE metadata."MD_MaintenanceInformation" ADD CONSTRAINT "MaintenanceInformation_updateScope" FOREIGN KEY ("updateScope") REFERENCES metadata."MD_ScopeCode" (code) ON UPDATE CASCADE ON DELETE RESTRICT;
ALTER TABLE metadata."MD_MaintenanceInformation" ADD CONSTRAINT "MaintenanceInformation_updateScopeDescription" FOREIGN KEY ("updateScopeDescription") REFERENCES metadata."MD_ScopeDescription" (id) ON UPDATE CASCADE ON DELETE RESTRICT;
ALTER TABLE metadata."DQ_DataQuality" ADD CONSTRAINT "DataQuality_scope" FOREIGN KEY ("scope") REFERENCES metadata."DQ_Scope" (id) ON UPDATE CASCADE ON DELETE RESTRICT;
ALTER TABLE metadata."DQ_DataQuality" ADD CONSTRAINT "DataQuality_report" FOREIGN KEY ("report") REFERENCES metadata."DQ_Element" (id) ON UPDATE CASCADE ON DELETE RESTRICT;
ALTER TABLE metadata."DQ_DataQuality" ADD CONSTRAINT "DataQuality_lineage" FOREIGN KEY ("lineage") REFERENCES metadata."LI_Lineage" (id) ON UPDATE CASCADE ON DELETE RESTRICT;
ALTER TABLE metadata."DQ_Element" ADD CONSTRAINT "Element_measureIdentification" FOREIGN KEY ("measureIdentification") REFERENCES metadata."MD_Identifier" (id) ON UPDATE CASCADE ON DELETE RESTRICT;
ALTER TABLE metadata."DQ_Element" ADD CONSTRAINT "Element_evaluationMethodType" FOREIGN KEY ("evaluationMethodType") REFERENCES metadata."DQ_EvaluationMethodTypeCode" (code) ON UPDATE CASCADE ON DELETE RESTRICT;
ALTER TABLE metadata."DQ_Element" ADD CONSTRAINT "Element_evaluationProcedure" FOREIGN KEY ("evaluationProcedure") REFERENCES metadata."CI_Citation" (id) ON UPDATE CASCADE ON DELETE RESTRICT;
ALTER TABLE metadata."DQ_Element" ADD CONSTRAINT "Element_result" FOREIGN KEY ("result") REFERENCES metadata."DQ_Result" (id) ON UPDATE CASCADE ON DELETE RESTRICT;
ALTER TABLE metadata."DQ_Scope" ADD CONSTRAINT "Scope_level" FOREIGN KEY ("level") REFERENCES metadata."MD_ScopeCode" (code) ON UPDATE CASCADE ON DELETE RESTRICT;
ALTER TABLE metadata."DQ_Scope" ADD CONSTRAINT "Scope_extent" FOREIGN KEY ("extent") REFERENCES metadata."EX_Extent" (id) ON UPDATE CASCADE ON DELETE RESTRICT;
ALTER TABLE metadata."MD_Dimension" ADD CONSTRAINT "Dimension_dimensionName" FOREIGN KEY ("dimensionName") REFERENCES metadata."MD_DimensionNameTypeCode" (code) ON UPDATE CASCADE ON DELETE RESTRICT;
ALTER TABLE metadata."MD_GeometricObjects" ADD CONSTRAINT "GeometricObjects_geometricObjectType" FOREIGN KEY ("geometricObjectType") REFERENCES metadata."MD_GeometricObjectTypeCode" (code) ON UPDATE CASCADE ON DELETE RESTRICT;
ALTER TABLE metadata."MD_VectorSpatialRepresentation" ADD CONSTRAINT "VectorSpatialRepresentation_topologyLevel" FOREIGN KEY ("topologyLevel") REFERENCES metadata."MD_TopologyLevelCode" (code) ON UPDATE CASCADE ON DELETE RESTRICT;
ALTER TABLE metadata."MD_VectorSpatialRepresentation" ADD CONSTRAINT "VectorSpatialRepresentation_geometricObjects" FOREIGN KEY ("geometricObjects") REFERENCES metadata."MD_GeometricObjects" (id) ON UPDATE CASCADE ON DELETE RESTRICT;
ALTER TABLE metadata."MD_Band" ADD CONSTRAINT "Band_units" FOREIGN KEY ("units") REFERENCES "Unit" (symbol) ON UPDATE CASCADE ON DELETE RESTRICT;
ALTER TABLE metadata."EX_GeographicDescription" ADD CONSTRAINT "GeographicDescription_geographicIdentifier" FOREIGN KEY ("geographicIdentifier") REFERENCES metadata."MD_Identifier" (id) ON UPDATE CASCADE ON DELETE RESTRICT;
ALTER TABLE metadata."EX_SpatialTemporalExtent" ADD CONSTRAINT "SpatialTemporalExtent_spatialExtent" FOREIGN KEY ("spatialExtent") REFERENCES metadata."EX_GeographicExtent" (id) ON UPDATE CASCADE ON DELETE RESTRICT;
ALTER TABLE metadata."MD_DataIdentification" ADD CONSTRAINT "DataIdentification_spatialRepresentationType" FOREIGN KEY ("spatialRepresentationType") REFERENCES metadata."MD_SpatialRepresentationTypeCode" (code) ON UPDATE CASCADE ON DELETE RESTRICT;
ALTER TABLE metadata."MD_DataIdentification" ADD CONSTRAINT "DataIdentification_spatialResolution" FOREIGN KEY ("spatialResolution") REFERENCES metadata."MD_Resolution" (id) ON UPDATE CASCADE ON DELETE RESTRICT;
ALTER TABLE metadata."MD_DataIdentification" ADD CONSTRAINT "DataIdentification_language" FOREIGN KEY ("language") REFERENCES "Locale" (code) ON UPDATE CASCADE ON DELETE RESTRICT;
ALTER TABLE metadata."MD_DataIdentification" ADD CONSTRAINT "DataIdentification_topicCategory" FOREIGN KEY ("topicCategory") REFERENCES metadata."MD_TopicCategoryCode" (code) ON UPDATE CASCADE ON DELETE RESTRICT;
ALTER TABLE metadata."MD_DataIdentification" ADD CONSTRAINT "DataIdentification_geographicBox" FOREIGN KEY ("geographicBox") REFERENCES metadata."EX_GeographicBoundingBox" (id) ON UPDATE CASCADE ON DELETE RESTRICT;
ALTER TABLE metadata."MD_DataIdentification" ADD CONSTRAINT "DataIdentification_geographicDescription" FOREIGN KEY ("geographicDescription") REFERENCES metadata."EX_GeographicDescription" (id) ON UPDATE CASCADE ON DELETE RESTRICT;
ALTER TABLE metadata."MD_DataIdentification" ADD CONSTRAINT "DataIdentification_extent" FOREIGN KEY ("extent") REFERENCES metadata."EX_Extent" (id) ON UPDATE CASCADE ON DELETE RESTRICT;
ALTER TABLE metadata."DQ_ConformanceResult" ADD CONSTRAINT "ConformanceResult_specification" FOREIGN KEY ("specification") REFERENCES metadata."CI_Citation" (id) ON UPDATE CASCADE ON DELETE RESTRICT;
ALTER TABLE metadata."DQ_QuantitativeResult" ADD CONSTRAINT "QuantitativeResult_valueUnit" FOREIGN KEY ("valueUnit") REFERENCES "Unit" (symbol) ON UPDATE CASCADE ON DELETE RESTRICT;
ALTER TABLE metadata."MD_GridSpatialRepresentation" ADD CONSTRAINT "GridSpatialRepresentation_axisDimensionsProperties" FOREIGN KEY ("axisDimensionsProperties") REFERENCES metadata."MD_Dimension" (id) ON UPDATE CASCADE ON DELETE RESTRICT;
ALTER TABLE metadata."MD_GridSpatialRepresentation" ADD CONSTRAINT "GridSpatialRepresentation_cellGeometry" FOREIGN KEY ("cellGeometry") REFERENCES metadata."MD_CellGeometryCode" (code) ON UPDATE CASCADE ON DELETE RESTRICT;
ALTER TABLE metadata."MD_Georectified" ADD CONSTRAINT "Georectified_pointInPixel" FOREIGN KEY ("pointInPixel") REFERENCES metadata."MD_PixelOrientationCode" (code) ON UPDATE CASCADE ON DELETE RESTRICT;
ALTER TABLE metadata."MD_Georeferenceable" ADD CONSTRAINT "Georeferenceable_parameterCitation" FOREIGN KEY ("parameterCitation") REFERENCES metadata."CI_Citation" (id) ON UPDATE CASCADE ON DELETE RESTRICT;
