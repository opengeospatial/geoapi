package org.opengis.metadata;

import org.opengis.metadata.citation.Address;
import org.opengis.metadata.citation.Citation;
import org.opengis.metadata.citation.CitationDate;
import org.opengis.metadata.citation.Contact;
import org.opengis.metadata.citation.OnLineResource;
import org.opengis.metadata.citation.ResponsibleParty;
import org.opengis.metadata.citation.Series;
import org.opengis.metadata.citation.Telephone;
import org.opengis.metadata.constraint.Constraints;
import org.opengis.metadata.constraint.LegalConstraints;
import org.opengis.metadata.constraint.SecurityConstraints;
import org.opengis.metadata.content.Band;
import org.opengis.metadata.content.ContentInformation;
import org.opengis.metadata.content.CoverageDescription;
import org.opengis.metadata.content.FeatureCatalogueDescription;
import org.opengis.metadata.content.ImageDescription;
import org.opengis.metadata.content.RangeDimension;
import org.opengis.metadata.distribution.DigitalTransferOptions;
import org.opengis.metadata.distribution.Distribution;
import org.opengis.metadata.distribution.Distributor;
import org.opengis.metadata.distribution.Format;
import org.opengis.metadata.distribution.Medium;
import org.opengis.metadata.distribution.StandardOrderProcess;
import org.opengis.metadata.extent.BoundingPolygon;
import org.opengis.metadata.extent.Extent;
import org.opengis.metadata.extent.GeographicBoundingBox;
import org.opengis.metadata.extent.GeographicDescription;
import org.opengis.metadata.extent.GeographicExtent;
import org.opengis.metadata.extent.SpatialTemporalExtent;
import org.opengis.metadata.extent.TemporalExtent;
import org.opengis.metadata.extent.VerticalExtent;
import org.opengis.metadata.identification.AggregateInformation;
import org.opengis.metadata.identification.BrowseGraphic;
import org.opengis.metadata.identification.DataIdentification;
import org.opengis.metadata.identification.Identification;
import org.opengis.metadata.identification.Keywords;
import org.opengis.metadata.identification.Resolution;
import org.opengis.metadata.identification.Usage;
import org.opengis.metadata.lineage.Lineage;
import org.opengis.metadata.lineage.ProcessStep;
import org.opengis.metadata.lineage.Source;
import org.opengis.metadata.maintenance.MaintenanceInformation;
import org.opengis.metadata.maintenance.ScopeDescription;
import org.opengis.metadata.quality.AbsoluteExternalPositionalAccuracy;
import org.opengis.metadata.quality.AccuracyOfATimeMeasurement;
import org.opengis.metadata.quality.Completeness;
import org.opengis.metadata.quality.CompletenessCommission;
import org.opengis.metadata.quality.CompletenessOmission;
import org.opengis.metadata.quality.ConceptualConsistency;
import org.opengis.metadata.quality.ConformanceResult;
import org.opengis.metadata.quality.DataQuality;
import org.opengis.metadata.quality.DomainConsistency;
import org.opengis.metadata.quality.Element;
import org.opengis.metadata.quality.FormalConsistency;
import org.opengis.metadata.quality.GriddedDataPositionalAccuracy;
import org.opengis.metadata.quality.LogicalConsistency;
import org.opengis.metadata.quality.NonQuantitativeAttributeCorrectness;
import org.opengis.metadata.quality.PositionalAccuracy;
import org.opengis.metadata.quality.QuantitativeAttributeAccuracy;
import org.opengis.metadata.quality.QuantitativeResult;
import org.opengis.metadata.quality.RelativeInternalPositionalAccuracy;
import org.opengis.metadata.quality.Result;
import org.opengis.metadata.quality.Scope;
import org.opengis.metadata.quality.TemporalAccuracy;
import org.opengis.metadata.quality.TemporalConsistency;
import org.opengis.metadata.quality.TemporalValidity;
import org.opengis.metadata.quality.ThematicAccuracy;
import org.opengis.metadata.quality.ThematicClassificationCorrectness;
import org.opengis.metadata.quality.TopologicalConsistency;
import org.opengis.metadata.spatial.Dimension;
import org.opengis.metadata.spatial.GeometricObjects;
import org.opengis.metadata.spatial.Georectified;
import org.opengis.metadata.spatial.Georeferenceable;
import org.opengis.metadata.spatial.GridSpatialRepresentation;
import org.opengis.metadata.spatial.SpatialRepresentation;
import org.opengis.metadata.spatial.VectorSpatialRepresentation;
import org.opengis.service.OperationMetadata;
import org.opengis.service.Parameter;
import org.opengis.service.ServiceProvider;

/**
 * <p>
 * Visitor for Metadata structure
 * </p>
 * @author Axios Engineering S.L.
 * @version $Id$
  */
public interface MetadataVisitor {
	
	/**
	 * Visits MetaData
	 * 
	 * @param md instance of Metadata Entity
	 */
	void visit(MetadataEntity  md);

	void visit(AbsoluteExternalPositionalAccuracy elem);
	
	void visit(AccuracyOfATimeMeasurement element);
	
	void visit(Address element);
	
	void visit(AggregateInformation aggregateInfo);
	
	void visit(ApplicationSchemaInformation element);
	
	void visit(Band element);
	
	void visit(BoundingPolygon element);
	
	void visit(BrowseGraphic element);
	
	void visit(CitationDate element);
	
	void visit(Citation element);
	
	void visit(CompletenessCommission element);
	
	void visit(Completeness element);
	
	void visit(CompletenessOmission element);
	
	void visit(ConceptualConsistency element);
	
	void visit(ConformanceResult element);
	
	void visit(Constraints element);
	
	void visit(Contact element);
	
	void visit(ContentInformation element);

	void visit(CoverageDescription element);
	
	void visit(DataIdentification element);
	
	void visit(DataQuality element);
	
	void visit(DigitalTransferOptions element);
	
	void visit(Dimension element);
	
	void visit(Distribution element);
	
	void visit(Distributor element);
	
	void visit(DomainConsistency element);
	
	void visit(Element element);
	
	void visit(ExtendedElementInformation element);
	
	void visit(Extent element);
	
	void visit(FeatureCatalogueDescription element);
	
	void visit(FeatureTypeList element);
	
	void visit(FormalConsistency element);
	
	void visit(Format element);
	
	void visit(GeographicBoundingBox element);
	
	void visit(GeographicDescription element);
	
	void visit(GeographicExtent element);
	
	void visit(GeometricObjects element);
	
	void visit(Georectified element);
	
	void visit(Georeferenceable element);
	
	void visit(GriddedDataPositionalAccuracy element);
	
	void visit(GridSpatialRepresentation element);
	
	void visit(Identification element);
	
	void visit(Identifier element);
	
	void visit(ImageDescription element);
	
	void visit(Keywords element);
	
	void visit(LegalConstraints element);
	
	void visit(Lineage element);
	
	void visit(LogicalConsistency element);
	
	void visit(MaintenanceInformation element);	
	
	void visit(Medium element);
	
	void visit(MetadataExtensionInformation element);
	
	void visit(MetaData metadata);
	
	void visit(NonQuantitativeAttributeCorrectness element);
	
	void visit(OnLineResource element);
	
	void visit(OperationMetadata element);
	
	void visit(Parameter element);
	
	void visit(PortrayalCatalogueReference element);
	
	void visit(PositionalAccuracy element);
	
	void visit(ProcessStep element);
	
	void visit(QuantitativeAttributeAccuracy element);
	
	void visit(QuantitativeResult element);
	
	void visit(RangeDimension element);
	
	void visit(RelativeInternalPositionalAccuracy element);
	
	void visit(Resolution element);
	
	void visit(ResponsibleParty element);
	
	void visit(Result element);
	
	void visit(ScopeDescription element);
	
	void visit(Scope element);
	
	void visit(SecurityConstraints element);
	
	void visit(Series element);
	
	void visit(org.opengis.service.ServiceIdentification element);
	
	void visit(ServiceProvider element);
	
	void visit(Source element);
	
	void visit(SpatialAttributeSupplement element);
	
	void visit(SpatialRepresentation element);
	
	void visit(SpatialTemporalExtent element);
	
	void visit(StandardOrderProcess element);
	
	void visit(Telephone element);
	
	void visit(TemporalAccuracy element);
	
	void visit(TemporalConsistency element);
	
	void visit(TemporalExtent element);
	
	void visit(TemporalValidity element);
	
	void visit(ThematicAccuracy element);
	
	void visit(ThematicClassificationCorrectness element);
	
	void visit(TopologicalConsistency element);
	
	void visit(Usage element);
	
	void visit(VectorSpatialRepresentation element);
	
	void visit(VerticalExtent element);
	

}
