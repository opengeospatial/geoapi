/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    Copyright © 2018-2023 Open Geospatial Consortium, Inc.
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
package org.opengis.geoapi;

import java.lang.reflect.Type;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.WildcardType;


/**
 * All interfaces, code lists, enumerations and exceptions declared in GeoAPI.
 * The array of types that are members of a category is given by {@link #types()}.
 *
 * @author  Martin Desruisseaux (Geomatys)
 * @version 3.1
 * @since   3.1
 */
public enum Content {
    /**
     * All GeoAPI interfaces, in approximate dependency order.
     * It is not possible to list the interfaces in strict dependency order because of circular dependencies.
     * But this list nevertheless tries to provide such order in a "best effort" basis.
     */
    @SuppressWarnings("deprecation")
    INTERFACES(org.opengis.util                    .InternationalString                 .class,
               org.opengis.util                    .ControlledVocabulary                .class,
               org.opengis.util                    .NameSpace                           .class,
               org.opengis.util                    .GenericName                         .class,
               org.opengis.util                    .LocalName                           .class,
               org.opengis.util                    .ScopedName                          .class,
               org.opengis.util                    .TypeName                            .class,
               org.opengis.util                    .Type                                .class,
               org.opengis.util                    .MemberName                          .class,
               org.opengis.util                    .RecordSchema                        .class,
               org.opengis.util                    .RecordType                          .class,
               org.opengis.util                    .Record                              .class,
               org.opengis.temporal                .TemporalPrimitive                   .class,
               org.opengis.temporal                .TemporalPosition                    .class,
               org.opengis.temporal                .Duration                            .class,
               org.opengis.temporal                .PeriodDuration                      .class,
               org.opengis.metadata.citation       .Series                              .class,
               org.opengis.metadata.citation       .Address                             .class,
               org.opengis.metadata.citation       .Telephone                           .class,
               org.opengis.metadata.citation       .OnlineResource                      .class,
               org.opengis.metadata.citation       .Contact                             .class,
               org.opengis.metadata.citation       .Party                               .class,
               org.opengis.metadata.citation       .Responsibility                      .class,
               org.opengis.metadata.citation       .ResponsibleParty                    .class,
               org.opengis.metadata.citation       .Individual                          .class,
               org.opengis.metadata.citation       .Organisation                        .class,
               org.opengis.metadata.citation       .CitationDate                        .class,
               org.opengis.metadata.citation       .Citation                            .class,
               org.opengis.metadata                .Identifier                          .class,     // Depends on Citation
               org.opengis.metadata.extent         .GeographicExtent                    .class,
               org.opengis.metadata.extent         .GeographicBoundingBox               .class,
               org.opengis.metadata.extent         .GeographicDescription               .class,     // Depends on Identifier
               org.opengis.metadata.extent         .BoundingPolygon                     .class,
               org.opengis.metadata.extent         .VerticalExtent                      .class,
               org.opengis.metadata.extent         .TemporalExtent                      .class,
               org.opengis.metadata.extent         .SpatialTemporalExtent               .class,
               org.opengis.metadata.extent         .Extent                              .class,
               org.opengis.referencing             .ReferenceIdentifier                 .class,
               org.opengis.referencing             .ObjectDomain                        .class,     // Depends on Extent
               org.opengis.referencing             .IdentifiedObject                    .class,     // A parameter dependency.
               org.opengis.parameter               .GeneralParameterDescriptor          .class,
               org.opengis.parameter               .GeneralParameterValue               .class,
               org.opengis.parameter               .ParameterDescriptor                 .class,
               org.opengis.parameter               .ParameterValue                      .class,
               org.opengis.parameter               .ParameterDescriptorGroup            .class,
               org.opengis.parameter               .ParameterValueGroup                 .class,
               org.opengis.metadata.maintenance    .ScopeDescription                    .class,
               org.opengis.metadata.maintenance    .Scope                               .class,
               org.opengis.metadata.maintenance    .MaintenanceInformation              .class,
               org.opengis.metadata.distribution   .Medium                              .class,
               org.opengis.metadata.distribution   .Format                              .class,
               org.opengis.metadata.distribution   .DataFile                            .class,
               org.opengis.metadata.distribution   .DigitalTransferOptions              .class,
               org.opengis.metadata.distribution   .StandardOrderProcess                .class,
               org.opengis.metadata.distribution   .Distributor                         .class,
               org.opengis.metadata.distribution   .Distribution                        .class,
               org.opengis.metadata.constraint     .Releasability                       .class,
               org.opengis.metadata.constraint     .Constraints                         .class,
               org.opengis.metadata.constraint     .LegalConstraints                    .class,
               org.opengis.metadata.constraint     .SecurityConstraints                 .class,
               org.opengis.metadata.identification .BrowseGraphic                       .class,
               org.opengis.metadata.identification .KeywordClass                        .class,
               org.opengis.metadata.identification .Keywords                            .class,
               org.opengis.metadata.identification .Usage                               .class,
               org.opengis.metadata.identification .RepresentativeFraction              .class,
               org.opengis.metadata.identification .Resolution                          .class,
               org.opengis.metadata.identification .AssociatedResource                  .class,
               org.opengis.metadata.identification .AggregateInformation                .class,
               org.opengis.metadata.identification .Identification                      .class,
               org.opengis.metadata.identification .DataIdentification                  .class,
               org.opengis.metadata.identification .OperationMetadata                   .class,
               org.opengis.metadata.identification .OperationChainMetadata              .class,
               org.opengis.metadata.identification .CoupledResource                     .class,
               org.opengis.metadata.identification .ServiceIdentification               .class,
               org.opengis.referencing             .ReferenceSystem                     .class,     // Depends on Extent.
               org.opengis.referencing.datum       .Datum                               .class,
               org.opengis.referencing.cs          .CoordinateSystemAxis                .class,
               org.opengis.referencing.cs          .CoordinateSystem                    .class,
               org.opengis.referencing.crs         .CoordinateReferenceSystem           .class,
               org.opengis.geometry.coordinate     .Position                            .class,
               org.opengis.geometry                .DirectPosition                      .class,
               org.opengis.geometry                .Envelope                            .class,
               org.opengis.geometry                .Geometry                            .class,
               org.opengis.coordinate              .CoordinateMetadata                  .class,
               org.opengis.coordinate              .CoordinateSet                       .class,
               org.opengis.geometry.primitive      .Point                               .class,
               org.opengis.metadata.acquisition    .Instrument                          .class,
               org.opengis.metadata.acquisition    .Platform                            .class,
               org.opengis.metadata.acquisition    .PlatformPass                        .class,
               org.opengis.metadata.acquisition    .Event                               .class,
               org.opengis.metadata.acquisition    .EnvironmentalRecord                 .class,
               org.opengis.metadata.acquisition    .Objective                           .class,
               org.opengis.metadata.acquisition    .Operation                           .class,
               org.opengis.metadata.acquisition    .RequestedDate                       .class,
               org.opengis.metadata.acquisition    .Requirement                         .class,
               org.opengis.metadata.acquisition    .Plan                                .class,
               org.opengis.metadata.acquisition    .AcquisitionInformation              .class,
               org.opengis.metadata.content        .RangeElementDescription             .class,
               org.opengis.metadata.content        .RangeDimension                      .class,
               org.opengis.metadata.content        .AttributeGroup                      .class,
               org.opengis.metadata.content        .SampleDimension                     .class,
               org.opengis.metadata.content        .Band                                .class,
               org.opengis.metadata.content        .ContentInformation                  .class,
               org.opengis.metadata.content        .CoverageDescription                 .class,
               org.opengis.metadata.content        .ImageDescription                    .class,
               org.opengis.metadata.content        .FeatureTypeInfo                     .class,
               org.opengis.metadata.content        .FeatureCatalogueDescription         .class,
               org.opengis.metadata.lineage        .NominalResolution                   .class,
               org.opengis.metadata.lineage        .Source                              .class,
               org.opengis.metadata.lineage        .Algorithm                           .class,
               org.opengis.metadata.lineage        .Processing                          .class,
               org.opengis.metadata.lineage        .ProcessStepReport                   .class,
               org.opengis.metadata.lineage        .ProcessStep                         .class,
               org.opengis.metadata.lineage        .Lineage                             .class,
               org.opengis.metadata.quality        .Scope                               .class,
               org.opengis.metadata.quality        .StandaloneQualityReportInformation  .class,
               org.opengis.metadata.quality        .Result                              .class,
               org.opengis.metadata.quality        .EvaluationMethod                    .class,
               org.opengis.metadata.quality        .SourceReference                     .class,
               org.opengis.metadata.quality        .Description                         .class,
               org.opengis.metadata.quality        .BasicMeasure                        .class,
               org.opengis.metadata.quality        .Measure                             .class,
               org.opengis.metadata.quality        .MeasureReference                    .class,
               org.opengis.metadata.quality        .Element                             .class,
               org.opengis.metadata.quality        .DataQuality                         .class,
               org.opengis.metadata.quality        .TemporalQuality                     .class,
               org.opengis.metadata.quality        .Metaquality                         .class,
               org.opengis.metadata.quality        .Confidence                          .class,
               org.opengis.metadata.quality        .Usability                           .class,
               org.opengis.metadata.quality        .Representativity                    .class,
               org.opengis.metadata.quality        .DataEvaluation                      .class,
               org.opengis.metadata.quality        .SampleBasedInspection               .class,
               org.opengis.metadata.quality        .IndirectEvaluation                  .class,
               org.opengis.metadata.quality        .Homogeneity                         .class,
               org.opengis.metadata.quality        .FullInspection                      .class,
               org.opengis.metadata.quality        .DescriptiveResult                   .class,
               org.opengis.metadata.quality        .AggregationDerivation               .class,
               org.opengis.metadata.quality        .PositionalAccuracy                  .class,
               org.opengis.metadata.quality        .AbsoluteExternalPositionalAccuracy  .class,
               org.opengis.metadata.quality        .GriddedDataPositionalAccuracy       .class,
               org.opengis.metadata.quality        .RelativeInternalPositionalAccuracy  .class,
               org.opengis.metadata.quality        .TemporalAccuracy                    .class,
               org.opengis.metadata.quality        .TemporalConsistency                 .class,
               org.opengis.metadata.quality        .TemporalValidity                    .class,
               org.opengis.metadata.quality        .AccuracyOfATimeMeasurement          .class,
               org.opengis.metadata.quality        .ThematicAccuracy                    .class,
               org.opengis.metadata.quality        .ThematicClassificationCorrectness   .class,
               org.opengis.metadata.quality        .QuantitativeAttributeAccuracy       .class,
               org.opengis.metadata.quality        .NonQuantitativeAttributeCorrectness .class,
               org.opengis.metadata.quality        .NonQuantitativeAttributeAccuracy    .class,
               org.opengis.metadata.quality        .LogicalConsistency                  .class,
               org.opengis.metadata.quality        .ConceptualConsistency               .class,
               org.opengis.metadata.quality        .DomainConsistency                   .class,
               org.opengis.metadata.quality        .FormatConsistency                   .class,
               org.opengis.metadata.quality        .TopologicalConsistency              .class,
               org.opengis.metadata.quality        .Completeness                        .class,
               org.opengis.metadata.quality        .CompletenessCommission              .class,
               org.opengis.metadata.quality        .CompletenessOmission                .class,
               org.opengis.metadata.quality        .ConformanceResult                   .class,
               org.opengis.metadata.quality        .CoverageResult                      .class,
               org.opengis.metadata.quality        .QuantitativeResult                  .class,
               org.opengis.metadata.spatial        .Dimension                           .class,
               org.opengis.metadata.spatial        .GeolocationInformation              .class,
               org.opengis.metadata.spatial        .GCP                                 .class,
               org.opengis.metadata.spatial        .GCPCollection                       .class,
               org.opengis.metadata.spatial        .GeometricObjects                    .class,
               org.opengis.metadata.spatial        .SpatialRepresentation               .class,
               org.opengis.metadata.spatial        .GridSpatialRepresentation           .class,
               org.opengis.metadata.spatial        .VectorSpatialRepresentation         .class,
               org.opengis.metadata.spatial        .Georectified                        .class,
               org.opengis.metadata.spatial        .Georeferenceable                    .class,
               org.opengis.metadata                .ApplicationSchemaInformation        .class,
               org.opengis.metadata                .ExtendedElementInformation          .class,
               org.opengis.metadata                .MetadataExtensionInformation        .class,
               org.opengis.metadata                .PortrayalCatalogueReference         .class,
               org.opengis.metadata                .MetadataScope                       .class,
               org.opengis.metadata                .Metadata                            .class,
               org.opengis.referencing.datum       .Ellipsoid                           .class,
               org.opengis.referencing.datum       .PrimeMeridian                       .class,
               org.opengis.referencing.datum       .GeodeticDatum                       .class,
               org.opengis.referencing.datum       .EngineeringDatum                    .class,
               org.opengis.referencing.datum       .ImageDatum                          .class,
               org.opengis.referencing.datum       .ParametricDatum                     .class,
               org.opengis.referencing.datum       .TemporalDatum                       .class,
               org.opengis.referencing.datum       .VerticalDatum                       .class,
               org.opengis.referencing.cs          .AffineCS                            .class,
               org.opengis.referencing.cs          .CartesianCS                         .class,
               org.opengis.referencing.cs          .CylindricalCS                       .class,
               org.opengis.referencing.cs          .EllipsoidalCS                       .class,
               org.opengis.referencing.cs          .LinearCS                            .class,
               org.opengis.referencing.cs          .ParametricCS                        .class,
               org.opengis.referencing.cs          .PolarCS                             .class,
               org.opengis.referencing.cs          .SphericalCS                         .class,
               org.opengis.referencing.cs          .TimeCS                              .class,
               org.opengis.referencing.cs          .UserDefinedCS                       .class,
               org.opengis.referencing.cs          .VerticalCS                          .class,
               org.opengis.referencing.crs         .SingleCRS                           .class,
               org.opengis.referencing.crs         .CompoundCRS                         .class,
               org.opengis.referencing.crs         .EngineeringCRS                      .class,
               org.opengis.referencing.crs         .GeodeticCRS                         .class,
               org.opengis.referencing.crs         .GeographicCRS                       .class,
               org.opengis.referencing.crs         .GeocentricCRS                       .class,
               org.opengis.referencing.crs         .ImageCRS                            .class,
               org.opengis.referencing.crs         .ParametricCRS                       .class,
               org.opengis.referencing.crs         .TemporalCRS                         .class,
               org.opengis.referencing.crs         .VerticalCRS                         .class,
               org.opengis.referencing.operation   .Formula                             .class,
               org.opengis.referencing.operation   .OperationMethod                     .class,
               org.opengis.referencing.operation   .Matrix                              .class,
               org.opengis.referencing.operation   .MathTransform                       .class,
               org.opengis.referencing.operation   .MathTransform1D                     .class,
               org.opengis.referencing.operation   .MathTransform2D                     .class,
               org.opengis.referencing.operation   .CoordinateOperation                 .class,
               org.opengis.referencing.operation   .SingleOperation                     .class,
               org.opengis.referencing.operation   .ConcatenatedOperation               .class,
               org.opengis.referencing.operation   .Conversion                          .class,
               org.opengis.referencing.operation   .Projection                          .class,
               org.opengis.referencing.operation   .Transformation                      .class,
               org.opengis.referencing.operation   .PassThroughOperation                .class,
               org.opengis.referencing.crs         .GeneralDerivedCRS                   .class,
               org.opengis.referencing.crs         .DerivedCRS                          .class,
               org.opengis.referencing.crs         .ProjectedCRS                        .class,
               org.opengis.referencing.operation   .ConicProjection                     .class,
               org.opengis.referencing.operation   .CylindricalProjection               .class,
               org.opengis.referencing.operation   .PlanarProjection                    .class,
               org.opengis.feature                 .IdentifiedType                      .class,
               org.opengis.feature                 .PropertyType                        .class,
               org.opengis.feature                 .Property                            .class,
               org.opengis.feature                 .AttributeType                       .class,
               org.opengis.feature                 .Attribute                           .class,
               org.opengis.feature                 .DynamicAttributeType                .class,
               org.opengis.feature                 .DynamicAttribute                    .class,
               org.opengis.feature                 .FeatureType                         .class,
               org.opengis.feature                 .Feature                             .class,
               org.opengis.feature                 .FeatureAssociation                  .class,
               org.opengis.feature                 .FeatureAssociationRole              .class,
               org.opengis.feature                 .Operation                           .class,
               org.opengis.util                    .Factory                             .class,
               org.opengis.util                    .NameFactory                         .class,
               org.opengis.referencing             .ObjectFactory                       .class,
               org.opengis.referencing             .AuthorityFactory                    .class,
               org.opengis.referencing.datum       .DatumFactory                        .class,
               org.opengis.referencing.datum       .DatumAuthorityFactory               .class,
               org.opengis.referencing.cs          .CSFactory                           .class,
               org.opengis.referencing.cs          .CSAuthorityFactory                  .class,
               org.opengis.referencing.crs         .CRSFactory                          .class,
               org.opengis.referencing.crs         .CRSAuthorityFactory                 .class,
               org.opengis.referencing.operation   .MathTransformFactory                .class,
               org.opengis.referencing.operation   .CoordinateOperationFactory          .class,
               org.opengis.referencing.operation   .CoordinateOperationAuthorityFactory .class,
               org.opengis.filter                  .Expression                          .class,
               org.opengis.filter                  .Literal                             .class,
               org.opengis.filter                  .ValueReference                      .class,
               org.opengis.filter                  .Filter                              .class,
               org.opengis.filter                  .ComparisonOperator                  .class,
               org.opengis.filter                  .BinaryComparisonOperator            .class,
               org.opengis.filter                  .BetweenComparisonOperator           .class,
               org.opengis.filter                  .LikeOperator                        .class,
               org.opengis.filter                  .NullOperator                        .class,
               org.opengis.filter                  .NilOperator                         .class,
               org.opengis.filter                  .SpatialOperator                     .class,
               org.opengis.filter                  .BinarySpatialOperator               .class,
               org.opengis.filter                  .DistanceOperator                    .class,
               org.opengis.filter                  .TemporalOperator                    .class,
               org.opengis.filter                  .LogicalOperator                     .class,
               org.opengis.filter                  .Version                             .class,
               org.opengis.filter                  .ResourceId                          .class,
               org.opengis.filter                  .SortProperty                        .class,
               org.opengis.filter                  .SortBy                              .class,
               org.opengis.filter                  .QueryExpression                     .class,
               org.opengis.filter.capability       .Conformance                         .class,
               org.opengis.filter.capability       .IdCapabilities                      .class,
               org.opengis.filter.capability       .ScalarCapabilities                  .class,
               org.opengis.filter.capability       .SpatialCapabilities                 .class,
               org.opengis.filter.capability       .TemporalCapabilities                .class,
               org.opengis.filter.capability       .AvailableFunction                   .class,
               org.opengis.filter.capability       .ExtendedCapabilities                .class,
               org.opengis.filter.capability       .FilterCapabilities                  .class,
               org.opengis.filter                  .FilterFactory                       .class),

    /**
     * All code list classes.
     */
    @SuppressWarnings("deprecation")
    CODE_LISTS(
            org.opengis.metadata                .Datatype                     .class,
            org.opengis.metadata.acquisition    .Context                      .class,
            org.opengis.metadata.acquisition    .GeometryType                 .class,
            org.opengis.metadata.acquisition    .ObjectiveType                .class,
            org.opengis.metadata.acquisition    .OperationType                .class,
            org.opengis.metadata.acquisition    .Priority                     .class,
            org.opengis.metadata.acquisition    .Sequence                     .class,
            org.opengis.metadata.acquisition    .Trigger                      .class,
            org.opengis.metadata.citation       .DateType                     .class,
            org.opengis.metadata.citation       .OnLineFunction               .class,
            org.opengis.metadata.citation       .PresentationForm             .class,
            org.opengis.metadata.citation       .Role                         .class,
            org.opengis.metadata.citation       .TelephoneType                .class,
            org.opengis.metadata.constraint     .Classification               .class,
            org.opengis.metadata.constraint     .Restriction                  .class,
            org.opengis.metadata.content        .BandDefinition               .class,
            org.opengis.metadata.content        .CoverageContentType          .class,
            org.opengis.metadata.content        .ImagingCondition             .class,
            org.opengis.metadata.content        .PolarisationOrientation      .class,
            org.opengis.metadata.content        .TransferFunctionType         .class,
            org.opengis.metadata.distribution   .MediumFormat                 .class,
            org.opengis.metadata.identification .AssociationType              .class,
            org.opengis.metadata.identification .CharacterSet                 .class,
            org.opengis.metadata.identification .CouplingType                 .class,
            org.opengis.metadata.identification .DistributedComputingPlatform .class,
            org.opengis.metadata.identification .InitiativeType               .class,
            org.opengis.metadata.identification .KeywordType                  .class,
            org.opengis.metadata.identification .Progress                     .class,
            org.opengis.metadata.maintenance    .MaintenanceFrequency         .class,
            org.opengis.metadata.maintenance    .ScopeCode                    .class,
            org.opengis.metadata.quality        .EvaluationMethodType         .class,
            org.opengis.metadata.quality        .ValueStructure               .class,
            org.opengis.metadata.spatial        .CellGeometry                 .class,
            org.opengis.metadata.spatial        .DimensionNameType            .class,
            org.opengis.metadata.spatial        .GeometricObjectType          .class,
            org.opengis.metadata.spatial        .SpatialRepresentationType    .class,
            org.opengis.metadata.spatial        .TopologyLevel                .class,
            org.opengis.referencing             .ReferenceSystemType          .class,
            org.opengis.referencing.cs          .AxisDirection                .class,
            org.opengis.referencing.cs          .RangeMeaning                 .class,
            org.opengis.referencing.datum       .PixelInCell                  .class,
            org.opengis.referencing.datum       .VerticalDatumType            .class,
            org.opengis.filter                  .ComparisonOperatorName       .class,
            org.opengis.filter                  .SpatialOperatorName          .class,
            org.opengis.filter                  .DistanceOperatorName         .class,
            org.opengis.filter                  .TemporalOperatorName         .class,
            org.opengis.filter                  .LogicalOperatorName          .class),

    /**
     * All enumeration classes.
     */
    ENUMERATIONS(org.opengis.annotation              .Obligation         .class,
                 org.opengis.metadata.identification .TopicCategory      .class,
                 org.opengis.metadata.spatial        .PixelOrientation   .class,
                 org.opengis.parameter               .ParameterDirection .class,
                 org.opengis.filter                  .MatchAction        .class,
                 org.opengis.filter                  .VersionAction      .class,
                 org.opengis.filter                  .SortOrder          .class),

    /**
     * The union of code lists and enumerations.
     */
    CONTROLLED_VOCABULARY(CODE_LISTS, ENUMERATIONS),

    /**
     * All exceptions.
     */
    EXCEPTIONS(org.opengis.util                  .FactoryException                     .class,
               org.opengis.util                  .UnimplementedServiceException        .class,
               org.opengis.util                  .NoSuchIdentifierException            .class,
               org.opengis.referencing           .NoSuchAuthorityCodeException         .class,
               org.opengis.referencing.operation .OperationNotFoundException           .class,
               org.opengis.referencing.operation .TransformException                   .class,
               org.opengis.referencing.operation .NoninvertibleTransformException      .class,
               org.opengis.parameter             .ParameterNotFoundException           .class,
               org.opengis.parameter             .InvalidParameterNameException        .class,
               org.opengis.parameter             .InvalidParameterTypeException        .class,
               org.opengis.parameter             .InvalidParameterValueException       .class,
               org.opengis.parameter             .InvalidParameterCardinalityException .class,
               org.opengis.geometry              .MismatchedDimensionException         .class,
               org.opengis.feature               .MismatchedFeatureException           .class,
               org.opengis.feature               .FeatureInstantiationException        .class,
               org.opengis.feature               .FeatureOperationException            .class,
               org.opengis.feature               .PropertyNotFoundException            .class,
               org.opengis.feature               .OutOfTemporalDomainException         .class,
               org.opengis.feature               .InvalidPropertyValueException        .class,
               org.opengis.feature               .MultiValuedPropertyException         .class,
               org.opengis.filter                .InvalidFilterValueException          .class,
               org.opengis.filter                .DuplicateSortKeyException            .class),

    /**
     * All interfaces, code lists, enumerations and exceptions.
     */
    ALL(CONTROLLED_VOCABULARY, INTERFACES, EXCEPTIONS);

    /**
     * All types that are members of the category identified by this enumeration value.
     */
    private final Class<?>[] types;

    /**
     * Creates a new enumeration with the given members.
     *
     * @param  types  all types that are members of the category identified by this enumeration value.
     */
    private Content(final Class<?>... types) {
        this.types = types;
    }

    /**
     * Creates a new enumeration as the union of existing enumerations.
     *
     * @param  others  the other enumerations to combine in this one.
     */
    private Content(final Content... others) {
        int length = 0;
        for (final Content other : others) {
            length += other.types.length;
        }
        types = new Class<?>[length];
        length = 0;
        for (final Content other : others) {
            final int n = other.types.length;
            System.arraycopy(other.types, 0, types, length, n);
            length += n;
        }
    }

    /**
     * Returns {@code true} if this enumeration is for enumerations, code lists, or combination of both.
     *
     * @return {@code true} if {@link #ENUMERATIONS}, {@link #CODE_LISTS} or {@link #CONTROLLED_VOCABULARY}.
     */
    public boolean isControlledVocabulary() {
        switch (this) {
            case ENUMERATIONS:
            case CODE_LISTS:
            case CONTROLLED_VOCABULARY: return true;
            default: return false;
        }
    }

    /**
     * Returns all types in that are members of the category identified by this enumeration value.
     *
     * @return types that are members of this category.
     */
    public Class<?>[] types() {
        return types.clone();
    }

    /**
     * Returns the element type of the given field. If the field type is parameterized, then
     * this method returns the upper bound. Otherwise this method returns the value type.
     *
     * @param  field  the field for which to obtain the (eventually parameterized) type.
     * @return the property element type.
     */
    public static Class<?> typeOf(final Field field) {
        Class<?> c = getActualTypeArgument(field.getGenericType());
        if (c == null) {
            c = field.getType();
        }
        return c;
    }

    /**
     * Returns the element type of the return value or single parameter type of the given method.
     * If the return type or the single parameter is a parameterized type, then this method returns
     * the upper bound. Otherwise this method returns the type directly.
     *
     * @param  method  the method for which to obtain the (eventually parameterized) return type.
     * @return the return element type (may be {@link Void#TYPE}).
     */
    public static Class<?> typeOf(final Method method) {
        Class<?> c = getActualTypeArgument(method.getGenericReturnType());
        if (c == null) {
            final Type[] parameters = method.getGenericParameterTypes();
            if (parameters != null && parameters.length == 1) {
                c = getActualTypeArgument(parameters[0]);
            }
        }
        if (c == null) {
            c = method.getReturnType();
        }
        return c;
    }

    /**
     * Returns {@link ParameterizedType#getActualTypeArguments()} as a {@link Class} or {@code null}.
     *
     * @param  type  the type to convert to a class.
     * @return the given type as a class, or {@code null}.
     */
    private static Class<?> getActualTypeArgument(Type type) {
        if (type instanceof ParameterizedType) {
            Type[] p = ((ParameterizedType) type).getActualTypeArguments();
            while (p.length == 1) {
                type = p[0];
                if (type instanceof WildcardType) {
                    p = ((WildcardType) type).getUpperBounds();
                } else {
                    if (type instanceof ParameterizedType) {
                        type = ((ParameterizedType) type).getRawType();
                    }
                    if (type instanceof Class<?>) {
                        return (Class<?>) type;
                    }
                    break;                                      // Unknown type.
                }
            }
        }
        return null;
    }
}
