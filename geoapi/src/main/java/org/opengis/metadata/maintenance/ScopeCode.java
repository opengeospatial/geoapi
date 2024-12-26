/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    Copyright © 2004-2024 Open Geospatial Consortium, Inc.
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
package org.opengis.metadata.maintenance;

import org.opengis.util.CodeList;
import org.opengis.annotation.UML;
import org.opengis.geoapi.internal.Vocabulary;

import static org.opengis.annotation.Obligation.*;
import static org.opengis.annotation.Specification.*;


/**
 * Class of information to which the referencing entity applies.
 *
 * @author  Martin Desruisseaux (IRD)
 * @author  Cory Horner (Refractions Research)
 * @author  Rémi Maréchal (Geomatys)
 * @version 3.1
 * @since   2.0
 *
 * @see Scope
 */
@Vocabulary(capacity=26)
@UML(identifier="MD_ScopeCode", specification=ISO_19115)
public final class ScopeCode extends CodeList<ScopeCode> {
    /**
     * Serial number for compatibility with different versions.
     */
    private static final long serialVersionUID = -4542429199783894255L;

    /**
     * Information applies to the collection hardware class.
     */
    @UML(identifier="collectionHardware", obligation=CONDITIONAL, specification=ISO_19115)
    public static final ScopeCode COLLECTION_HARDWARE = new ScopeCode("COLLECTION_HARDWARE");

    /**
     * Information applies to the collection session.
     */
    @UML(identifier="collectionSession", obligation=CONDITIONAL, specification=ISO_19115)
    public static final ScopeCode COLLECTION_SESSION = new ScopeCode("COLLECTION_SESSION");

    /**
     * Information applies to a collection of spatial data which share similar characteristics of theme,
     * source date, resolution, and methodology. The exact definition of what constitutes a series
     * is determined by the data provider. Examples of data series metadata entries may include:
     *
     * <ul>
     *   <li>A flight line of digital aerial photographs collected during a single flight with one camera and film type.</li>
     *   <li>A continuous scan swathe collected from a satellite using the same sensors on a single orbital pass.</li>
     *   <li>A collection of raster map data captured from a common series of paper maps.</li>
     *   <li>A collection of vector datasets depicting surface hydrography with associated attribution
     *        for multiple administrative areas within a country.</li>
     * </ul>
     */
    @UML(identifier="series", obligation=CONDITIONAL, specification=ISO_19115)
    public static final ScopeCode SERIES = new ScopeCode("SERIES");

    /**
     * Information applies to a consistent spatial data product that can be provided by a
     * data distributor. A dataset may be a member of a data {@linkplain #SERIES series}.
     * A dataset may be composed of a set of feature {@linkplain #FEATURE_TYPE types} and
     * {@linkplain #FEATURE instances}, and attribute {@linkplain #ATTRIBUTE_TYPE types} and
     * {@linkplain #ATTRIBUTE instances}.
     *
     * @see ScopeDescription#getDataset()
     */
    @UML(identifier="dataset", obligation=CONDITIONAL, specification=ISO_19115)
    public static final ScopeCode DATASET = new ScopeCode("DATASET");

    /**
     * Information applies to non-geographic data.
     */
    @UML(identifier="nonGeographicDataset", obligation=CONDITIONAL, specification=ISO_19115)
    public static final ScopeCode NON_GEOGRAPHIC_DATASET = new ScopeCode("NON_GEOGRAPHIC_DATASET");

    /**
     * Information applies to a dimension group.
     */
    @UML(identifier="dimensionGroup", obligation=CONDITIONAL, specification=ISO_19115)
    public static final ScopeCode DIMENSION_GROUP = new ScopeCode("DIMENSION_GROUP");

    /**
     * Information applies to a group of spatial primitives (geometric objects) that have a
     * common type. Example (compare with {@linkplain #FEATURE}):
     *
     * <ul>
     *   <li>All bridges within a dataset.</li>
     * </ul>
     *
     * Feature type metadata are grouped in {@linkplain #DATASET dataset}s.
     *
     * @see ScopeDescription#getFeatures()
     */
    @UML(identifier="featureType", obligation=CONDITIONAL, specification=ISO_19115)
    public static final ScopeCode FEATURE_TYPE = new ScopeCode("FEATURE_TYPE");

    /**
     * Information applies to spatial constructs (features) that have a direct correspondence
     * with a real world object. Examples (compare with {@linkplain #FEATURE_TYPE}):
     *
     * <ul>
     *   <li>The Sydney harbour bridge.</li>
     *   <li>The Golden Gate bridge, in San Francisco.</li>
     * </ul>
     *
     * Feature instance metadata are grouped in {@linkplain #DATASET dataset}s.
     *
     * @see ScopeDescription#getFeatureInstances()
     */
    @UML(identifier="feature", obligation=CONDITIONAL, specification=ISO_19115)
    public static final ScopeCode FEATURE = new ScopeCode("FEATURE");

    /**
     * Information applies to the characteristic of a feature.
     * Attribute types are the digital parameters that describe a common aspect of grouped spatial
     * primitives (geometric objects). Example (compare with {@linkplain #ATTRIBUTE}):
     *
     * <ul>
     *   <li>Overhead clearance associated with a bridge.</li>
     * </ul>
     *
     * Attribute type metadata are grouped in {@linkplain #DATASET dataset}s.
     *
     * @see ScopeDescription#getAttributes()
     */
    @UML(identifier="attributeType", obligation=CONDITIONAL, specification=ISO_19115)
    public static final ScopeCode ATTRIBUTE_TYPE = new ScopeCode("ATTRIBUTE_TYPE");

    /**
     * Information applies to the attribute value.
     * Attribute instances are the digital parameters that describe an aspect of a feature instance.
     * Example (compare with {@linkplain #ATTRIBUTE_TYPE}):
     *
     * <ul>
     *   <li>The overhead clearance associated with a specific bridge across a road.</li>
     * </ul>
     *
     * Attribute instance metadata are grouped in {@linkplain #DATASET dataset}s.
     *
     * @see ScopeDescription#getAttributeInstances()
     */
    @UML(identifier="attribute", obligation=CONDITIONAL, specification=ISO_19115)
    public static final ScopeCode ATTRIBUTE = new ScopeCode("ATTRIBUTE");

    /**
     * Information applies to a property type.
     */
    @UML(identifier="propertyType", obligation=CONDITIONAL, specification=ISO_19115)
    public static final ScopeCode PROPERTY_TYPE = new ScopeCode("PROPERTY_TYPE");

    /**
     * Information applies to a field session.
     */
    @UML(identifier="fieldSession", obligation=CONDITIONAL, specification=ISO_19115)
    public static final ScopeCode FIELD_SESSION = new ScopeCode("FIELD_SESSION");

    /**
     * Information applies to a computer program or routine.
     */
    @UML(identifier="software", obligation=CONDITIONAL, specification=ISO_19115)
    public static final ScopeCode SOFTWARE = new ScopeCode("SOFTWARE");

    /**
     * Information applies to a capability which a service provider entity makes available
     * to a service user entity through a set of interfaces that define a behaviour, such as
     * a use case.
     */
    @UML(identifier="service", obligation=CONDITIONAL, specification=ISO_19115)
    public static final ScopeCode SERVICE = new ScopeCode("SERVICE");

    /**
     * Information applies to a copy or imitation of an existing or hypothetical object.
     */
    @UML(identifier="model", obligation=CONDITIONAL, specification=ISO_19115)
    public static final ScopeCode MODEL = new ScopeCode("MODEL");

    /**
     * Information applies to a tile, a spatial subset of geographic data.
     */
    @UML(identifier="tile", obligation=CONDITIONAL, specification=ISO_19115)
    public static final ScopeCode TILE = new ScopeCode("TILE");

    /**
     * Information applies to metadata.
     *
     * @since 3.1
     */
    @UML(identifier="metadata", obligation=CONDITIONAL, specification=ISO_19115)
    public static final ScopeCode METADATA = new ScopeCode("METADATA");

    /**
     * Information applies to an initiative.
     *
     * @since 3.1
     */
    @UML(identifier="initiative", obligation=CONDITIONAL, specification=ISO_19115)
    public static final ScopeCode INITIATIVE = new ScopeCode("INITIATIVE");

    /**
     * Information applies to a sample.
     *
     * @since 3.1
     */
    @UML(identifier="sample", obligation=CONDITIONAL, specification=ISO_19115)
    public static final ScopeCode SAMPLE = new ScopeCode("SAMPLE");

    /**
     * Information applies to a document.
     *
     * @since 3.1
     */
    @UML(identifier="document", obligation=CONDITIONAL, specification=ISO_19115)
    public static final ScopeCode DOCUMENT = new ScopeCode("DOCUMENT");

    /**
     * Information applies to a repository.
     *
     * @since 3.1
     */
    @UML(identifier="repository", obligation=CONDITIONAL, specification=ISO_19115)
    public static final ScopeCode REPOSITORY = new ScopeCode("REPOSITORY");

    /**
     * Information applies to an aggregate resource.
     *
     * @since 3.1
     */
    @UML(identifier="aggregate", obligation=CONDITIONAL, specification=ISO_19115)
    public static final ScopeCode AGGREGATE = new ScopeCode("AGGREGATE");

    /**
     * Metadata describing an ISO 19131 data product specification.
     *
     * @since 3.1
     */
    @UML(identifier="product", obligation=CONDITIONAL, specification=ISO_19115)
    public static final ScopeCode PRODUCT = new ScopeCode("PRODUCT");

    /**
     * Information applies to an unstructured set.
     *
     * @since 3.1
     */
    @UML(identifier="collection", obligation=CONDITIONAL, specification=ISO_19115)
    public static final ScopeCode COLLECTION = new ScopeCode("COLLECTION");

    /**
     * Information applies to a coverage.
     *
     * @since 3.1
     */
    @UML(identifier="coverage", obligation=CONDITIONAL, specification=ISO_19115)
    public static final ScopeCode COVERAGE = new ScopeCode("COVERAGE");

    /**
     * Information resource hosted on a specific set of hardware and accessible over network.
     *
     * @since 3.1
     */
    @UML(identifier="application", obligation=CONDITIONAL, specification=ISO_19115)
    public static final ScopeCode APPLICATION = new ScopeCode("APPLICATION");

    /**
     * Constructs an element of the given name.
     *
     * @param name  the name of the new element. This name shall not be in use by another element of this type.
     */
    private ScopeCode(final String name) {
        super(name);
    }

    /**
     * Returns the list of {@code ScopeCode}s.
     *
     * @return the list of codes declared in the current JVM.
     */
    public static ScopeCode[] values() {
        return values(ScopeCode.class);
    }

    /**
     * Returns the list of codes of the same kind as this code list element.
     * Invoking this method is equivalent to invoking {@link #values()}, except that
     * this method can be invoked on an instance of the parent {@code CodeList} class.
     *
     * @return all code {@linkplain #values() values} for this code list.
     */
    @Override
    public ScopeCode[] family() {
        return values();
    }

    /**
     * Returns the scope code that matches the given name, or returns a new value if there is no match.
     * This methods returns the first instance (in declaration order) for which the {@linkplain #name() name}
     * is {@linkplain String#equalsIgnoreCase(String) equals, ignoring case}, to the given name.
     * If no existing instance is found, then a new one is created for the given name.
     *
     * @param  code  the name of the code to fetch or to create.
     * @return a code matching the given name.
     */
    public static ScopeCode valueOf(String code) {
        return valueOf(ScopeCode.class, code, ScopeCode::new).get();
    }
}
