package org.opengis.feature;

import java.util.Collection;

import org.opengis.feature.type.AssociationDescriptor;
import org.opengis.feature.type.AttributeDescriptor;
import org.opengis.feature.type.ComplexType;
import org.opengis.feature.type.FeatureCollectionType;
import org.opengis.feature.type.FeatureType;
import org.opengis.feature.type.GeometryType;
import org.opengis.referencing.crs.CRSFactory;
import org.opengis.referencing.crs.CoordinateReferenceSystem;
import org.opengis.geometry.coordinate.GeometryFactory;


/**
 * Plays the role of making actual instances of types in this puzzle.
 *
 * @author Gabriel Roldan (Axios Engineering)
 * @author Justin Deoliveira (The Open Planning Project)
 *
 */
public interface FeatureFactory {
    /**
     * @return The CRS factory used to create CRS info for created attributes.
     */
    CRSFactory getCRSFactory();

    /**
     * Sets the CRS factory used to create CRS info for created attributes.
     * <p>
     * Creating a CRS for a both Features and GeometryAttributes is a common
     * need when creating content, this serves to cut down on the number of
     * dependencies needed.
     * </p>
     */
    void setCRSFactory(CRSFactory crsFactory);

    /**
     * @return The factory used to create geometric data.
     */
    GeometryFactory getGeometryFactory();

    /**
     * Sets the factory used to create geometric data.
     * <p>
     * Creating spatial data is a common need when creating features, this
     * serves to cut down on the number of dependencies needed.
     * </p>
     */
    void setGeometryFactory(GeometryFactory geometryFactory);

    /**
     * Creates an association (always nested).
     *
     * @param value The value of the association, an attribute.
     * @param descriptor The descriptor.
     */
    Association createAssociation(Attribute value, AssociationDescriptor descriptor);

    /**
     * Creates a new attribute (always nested).
     * <p>
     * As currently defined this factory allows for the explicit creation of:
     * <ul>
     * <li>BooleanAttribute
     * <li>NumericAttribute
     * <li>TextAttribute
     * <li>TemporalAttribute
     * <li>GeometryAttribute (formal part of the model)
     * </ul>
     *
     * @param value The value of the attribute, may be null depending on type.
     * @param descriptor The attribute descriptor.
     * @param id The id of the attribute, may be null depending on type.
     *
     */
    Attribute createAttribute(Object value, AttributeDescriptor descriptor, String id);

    /**
     * Creates a new geometry attribute (always nested).
     *
     * @param value The initial value of the attribute, may be null depending on
     * the type of the type of the attribute.
     * @param desc The attribute descriptor.
     * @param id The id of the attribute, may be null depending on the type.
     * @param crs The coordinate reference system of the attribute, may be null.
     *
     * @throws IllegalArgumentException If desc.getType() does not return an
     * instanceof {@link GeometryType}.
     */
    GeometryAttribute createGeometryAttribute(
            Object geometry, AttributeDescriptor desc, String id, CoordinateReferenceSystem crs);

    /**
     * Creates a nested complex attribute.
     *
     * @param value The initial value of the attribute, may be null depending on
     * the type of the attribute.
     * @param id The id of the attribute, may be null depending on the type.
     * @param desc The attribute descriptor.
     *
     * @throws IllegalArgumentException If desc.getType() does not return an
     * instanceof {@link ComplexType}.
     */
    ComplexAttribute createComplexAttribute(
            Collection<Property> value, AttributeDescriptor desc, String id);

    /**
     * Create attribute based explicitly on type (not nested).
     *
     * @param value
     * @param type
     * @param id
     * @return
     */
    ComplexAttribute createComplexAttribute(
            Collection<Property> value, ComplexType type, String id);

    /**
     * Creates a nested feature.
     *
     * @param id The id of the feature, (fid), may be null depending on the type.
     * @param desc The attribute descriptor.
     * @param value The initial value of the attribute, may be null depending on
     * the type of the feature.
     *
     * @throws IllegalArgumentException If desc.getType() does not return an
     * instanceof {@link FeatureType}.
     */
    Feature createFeature(Collection<Property> value, AttributeDescriptor desc, String id);

    /**
     * Create a new feature based on type (not nested)
     *
     * @param value
     * @param type
     * @param id
     * @return
     */
    Feature createFeature(Collection<Property> value, FeatureType type, String id);

    /**
     * Createsa a nested feature collection.
     *
     * @param value The initial value of the attribute, may be null depending on
     * the type of the feature.
     * @param desc The attribute descriptor.
     * @param id The id of the feature collection, may be null depending on the
     * type.
     *
     * @throws IllegalArgumentException If desc.getType() does not return an
     * instanceof {@link FeatureCollectionType}.
     */
    FeatureCollection createFeatureCollection(Collection<Property> value, AttributeDescriptor desc, String id);

    /**
     * Create a new feature collection based on type (not nested).
     *
     * @param value
     * @param type
     * @param id
     * @return
     */
    FeatureCollection createFeatureCollection(Collection<Property> value, FeatureCollectionType type, String id);
}

