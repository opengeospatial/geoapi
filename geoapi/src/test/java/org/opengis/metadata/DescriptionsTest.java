/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    Copyright © 2009-2011 Open Geospatial Consortium, Inc.
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
package org.opengis.metadata;

import java.util.Set;
import java.util.HashSet;
import java.util.Enumeration;
import java.util.ResourceBundle;
import java.util.MissingResourceException;
import java.util.Locale;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

import org.opengis.util.CodeList;
import org.opengis.annotation.UML;
import org.opengis.annotation.Specification;

import org.junit.*;
import static org.junit.Assert.*;


/**
 * Tests the content of {@code Descriptions.properties} file.
 *
 * @author  Martin Desruisseaux (Geomatys)
 * @version 3.0
 * @since   2.3
 */
public final class DescriptionsTest {
    /**
     * List of metadata interfaces and code lists.
     */
    private static final Class<?>[] METADATA = {
        org.opengis.metadata.ApplicationSchemaInformation.class,
        org.opengis.metadata.Datatype.class,
        org.opengis.metadata.ExtendedElementInformation.class,
        org.opengis.metadata.FeatureTypeList.class,
        org.opengis.metadata.Identifier.class,
        org.opengis.metadata.Metadata.class,
        org.opengis.metadata.MetadataExtensionInformation.class,
        org.opengis.metadata.Obligation.class,
        org.opengis.metadata.PortrayalCatalogueReference.class,
        org.opengis.metadata.acquisition.AcquisitionInformation.class,
        org.opengis.metadata.acquisition.Context.class,
        org.opengis.metadata.acquisition.EnvironmentalRecord.class,
        org.opengis.metadata.acquisition.Event.class,
        org.opengis.metadata.acquisition.GeometryType.class,
        org.opengis.metadata.acquisition.Instrument.class,
        org.opengis.metadata.acquisition.Objective.class,
        org.opengis.metadata.acquisition.ObjectiveType.class,
        org.opengis.metadata.acquisition.Operation.class,
        org.opengis.metadata.acquisition.OperationType.class,
        org.opengis.metadata.acquisition.Plan.class,
        org.opengis.metadata.acquisition.Platform.class,
        org.opengis.metadata.acquisition.PlatformPass.class,
        org.opengis.metadata.acquisition.Priority.class,
        org.opengis.metadata.acquisition.RequestedDate.class,
        org.opengis.metadata.acquisition.Requirement.class,
        org.opengis.metadata.acquisition.Sequence.class,
        org.opengis.metadata.acquisition.Trigger.class,
        org.opengis.metadata.citation.Address.class,
        org.opengis.metadata.citation.Citation.class,
        org.opengis.metadata.citation.CitationDate.class,
        org.opengis.metadata.citation.Contact.class,
        org.opengis.metadata.citation.DateType.class,
        org.opengis.metadata.citation.OnLineFunction.class,
        org.opengis.metadata.citation.OnlineResource.class,
        org.opengis.metadata.citation.PresentationForm.class,
        org.opengis.metadata.citation.ResponsibleParty.class,
        org.opengis.metadata.citation.Role.class,
        org.opengis.metadata.citation.Series.class,
        org.opengis.metadata.citation.Telephone.class,
        org.opengis.metadata.constraint.Classification.class,
        org.opengis.metadata.constraint.Constraints.class,
        org.opengis.metadata.constraint.LegalConstraints.class,
        org.opengis.metadata.constraint.Restriction.class,
        org.opengis.metadata.constraint.SecurityConstraints.class,
        org.opengis.metadata.content.Band.class,
        org.opengis.metadata.content.BandDefinition.class,
        org.opengis.metadata.content.ContentInformation.class,
        org.opengis.metadata.content.CoverageContentType.class,
        org.opengis.metadata.content.CoverageDescription.class,
        org.opengis.metadata.content.FeatureCatalogueDescription.class,
        org.opengis.metadata.content.ImageDescription.class,
        org.opengis.metadata.content.ImagingCondition.class,
        org.opengis.metadata.content.PolarizationOrientation.class,
        org.opengis.metadata.content.RangeDimension.class,
        org.opengis.metadata.content.RangeElementDescription.class,
        org.opengis.metadata.content.TransferFunctionType.class,
        org.opengis.metadata.distribution.DataFile.class,
        org.opengis.metadata.distribution.DigitalTransferOptions.class,
        org.opengis.metadata.distribution.Distribution.class,
        org.opengis.metadata.distribution.Distributor.class,
        org.opengis.metadata.distribution.Format.class,
        org.opengis.metadata.distribution.Medium.class,
        org.opengis.metadata.distribution.MediumFormat.class,
        org.opengis.metadata.distribution.MediumName.class,
        org.opengis.metadata.distribution.StandardOrderProcess.class,
        org.opengis.metadata.extent.BoundingPolygon.class,
        org.opengis.metadata.extent.Extent.class,
        org.opengis.metadata.extent.GeographicBoundingBox.class,
        org.opengis.metadata.extent.GeographicDescription.class,
        org.opengis.metadata.extent.GeographicExtent.class,
        org.opengis.metadata.extent.SpatialTemporalExtent.class,
        org.opengis.metadata.extent.TemporalExtent.class,
        org.opengis.metadata.extent.VerticalExtent.class,
        org.opengis.metadata.identification.AggregateInformation.class,
        org.opengis.metadata.identification.AssociationType.class,
        org.opengis.metadata.identification.BrowseGraphic.class,
        org.opengis.metadata.identification.CharacterSet.class,
        org.opengis.metadata.identification.DataIdentification.class,
        org.opengis.metadata.identification.Identification.class,
        org.opengis.metadata.identification.InitiativeType.class,
        org.opengis.metadata.identification.KeywordType.class,
        org.opengis.metadata.identification.Keywords.class,
        org.opengis.metadata.identification.Progress.class,
        org.opengis.metadata.identification.RepresentativeFraction.class,
        org.opengis.metadata.identification.Resolution.class,
        org.opengis.metadata.identification.ServiceIdentification.class,
        org.opengis.metadata.identification.TopicCategory.class,
        org.opengis.metadata.identification.Usage.class,
        org.opengis.metadata.lineage.Algorithm.class,
        org.opengis.metadata.lineage.Lineage.class,
        org.opengis.metadata.lineage.NominalResolution.class,
        org.opengis.metadata.lineage.Processing.class,
        org.opengis.metadata.lineage.ProcessStep.class,
        org.opengis.metadata.lineage.ProcessStepReport.class,
        org.opengis.metadata.lineage.Source.class,
        org.opengis.metadata.maintenance.MaintenanceFrequency.class,
        org.opengis.metadata.maintenance.MaintenanceInformation.class,
        org.opengis.metadata.maintenance.ScopeCode.class,
        org.opengis.metadata.maintenance.ScopeDescription.class,
        org.opengis.metadata.quality.AbsoluteExternalPositionalAccuracy.class,
        org.opengis.metadata.quality.AccuracyOfATimeMeasurement.class,
        org.opengis.metadata.quality.Completeness.class,
        org.opengis.metadata.quality.CompletenessCommission.class,
        org.opengis.metadata.quality.CompletenessOmission.class,
        org.opengis.metadata.quality.ConceptualConsistency.class,
        org.opengis.metadata.quality.ConformanceResult.class,
        org.opengis.metadata.quality.CoverageResult.class,
        org.opengis.metadata.quality.DataQuality.class,
        org.opengis.metadata.quality.DomainConsistency.class,
        org.opengis.metadata.quality.Element.class,
        org.opengis.metadata.quality.EvaluationMethodType.class,
        org.opengis.metadata.quality.FormatConsistency.class,
        org.opengis.metadata.quality.GriddedDataPositionalAccuracy.class,
        org.opengis.metadata.quality.LogicalConsistency.class,
        org.opengis.metadata.quality.NonQuantitativeAttributeAccuracy.class,
        org.opengis.metadata.quality.PositionalAccuracy.class,
        org.opengis.metadata.quality.QuantitativeAttributeAccuracy.class,
        org.opengis.metadata.quality.QuantitativeResult.class,
        org.opengis.metadata.quality.RelativeInternalPositionalAccuracy.class,
        org.opengis.metadata.quality.Result.class,
        org.opengis.metadata.quality.Scope.class,
        org.opengis.metadata.quality.TemporalAccuracy.class,
        org.opengis.metadata.quality.TemporalConsistency.class,
        org.opengis.metadata.quality.TemporalValidity.class,
        org.opengis.metadata.quality.ThematicAccuracy.class,
        org.opengis.metadata.quality.ThematicClassificationCorrectness.class,
        org.opengis.metadata.quality.TopologicalConsistency.class,
        org.opengis.metadata.quality.Usability.class,
        org.opengis.metadata.spatial.CellGeometry.class,
        org.opengis.metadata.spatial.Dimension.class,
        org.opengis.metadata.spatial.DimensionNameType.class,
        org.opengis.metadata.spatial.GCP.class,
        org.opengis.metadata.spatial.GCPCollection.class,
        org.opengis.metadata.spatial.GeolocationInformation.class,
        org.opengis.metadata.spatial.GeometricObjectType.class,
        org.opengis.metadata.spatial.GeometricObjects.class,
        org.opengis.metadata.spatial.Georectified.class,
        org.opengis.metadata.spatial.Georeferenceable.class,
        org.opengis.metadata.spatial.GridSpatialRepresentation.class,
        org.opengis.metadata.spatial.PixelOrientation.class,
        org.opengis.metadata.spatial.SpatialRepresentation.class,
        org.opengis.metadata.spatial.SpatialRepresentationType.class,
        org.opengis.metadata.spatial.TopologyLevel.class,
        org.opengis.metadata.spatial.VectorSpatialRepresentation.class
    };

    /**
     * Asserts that the given key exists in the given resource bundle.
     */
    private static void assertResourceExists(final ResourceBundle resources, final String identifier) {
        final String value;
        try {
            value = resources.getString(identifier);
        } catch (MissingResourceException e) {
            fail(e.toString());
            return;
        }
        assertNotNull(identifier, value);
    }

    /**
     * Ensures that every metadata interfaces have a description, and that there is
     * no extra definitions. This test is theoretically locale-sensitive since we search
     * for the resources in the current locale. However it should works for every locales
     * since the English locale is used as a fallback.
     */
    @Test
    public void testAll() {
        final ResourceBundle resources = ResourceBundle.getBundle("org.opengis.metadata.Descriptions");
        /*
         * Get the set of keys. We will remove entries from this set as we found them.
         * When this test is finished, the set of keys should be empty.
         */
        final Set<String> keys = new HashSet<String>();
        for (final Enumeration<String> e=resources.getKeys(); e.hasMoreElements();) {
            final String key = e.nextElement();
            assertTrue("Duplicated key" , keys.add(key));
        }
        for (final Class<?> type : METADATA) {
            UML uml = type.getAnnotation(UML.class);
            assertNotNull("Missing UML annotation", uml);
            final String classIdentifier = uml.identifier();
            if (CodeList.class.isAssignableFrom(type)) {
                /*
                 * Check a code list and its fields. Note that the fields without UML
                 * annotation (for example serialVersionUID) must be ignored. We also
                 * need to ignore ISO 19115-2 code list items for now.
                 */
                if (uml.specification() == Specification.ISO_19115_2) {
                    // Resources for ISO 19115-2 code lists are not yet provided.
                    continue;
                }
                assertResourceExists(resources, classIdentifier);
                assertTrue("Key not found", keys.remove(classIdentifier));
                for (final Field code : type.getDeclaredFields()) {
                    uml = code.getAnnotation(UML.class);
                    if (uml != null) {
                        if (uml.specification() == Specification.ISO_19115_2) {
                            // Resources for ISO 19115-2 code lists are not yet provided.
                            continue;
                        }
                        if (uml.identifier().equals("8859part12")) {
                            // Special case: "A future ISO/IEC 8-bit single-byte coded graphic
                            // character set" declared in ISO 19115 but not yet in gmxCodelists.xml.
                            continue;
                        }
                        final String identifier = classIdentifier + '.' + uml.identifier();
                        assertResourceExists(resources, identifier);
                        assertTrue("Key not found", keys.remove(identifier));
                    }
                }
            } else {
                /*
                 * Check a metadata interface and its methods. Note that a few methods are
                 * GeoAPI extensions without UML (e.g. explicit definition of hashCode()),
                 * which must be excluded.
                 */
                assertResourceExists(resources, classIdentifier);
                assertTrue("Key not found", keys.remove(classIdentifier));
                for (final Method method : type.getDeclaredMethods()) {
                    uml = method.getAnnotation(UML.class);
                    if (uml != null) {
                        final String identifier = classIdentifier + '.' + uml.identifier();
                        assertResourceExists(resources, identifier);
                        assertTrue("Key not found", keys.remove(identifier));
                    }
                }
            }
        }
        assertTrue("Some keys do not map any class or method", keys.isEmpty());
    }

    /**
     * Tests the content of a few specific items in the English locale.
     */
    @Test
    public void testEnglish() {
        final ResourceBundle resources = ResourceBundle.getBundle("org.opengis.metadata.Descriptions", Locale.ENGLISH);
        assertEquals("Unique identifier for the resource. Example: Universal Product Code (UPC), National Stock Number (NSN).",
                resources.getString("CI_Citation.identifier"));
    }
}
