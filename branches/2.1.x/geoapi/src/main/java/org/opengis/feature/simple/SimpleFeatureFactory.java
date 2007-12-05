package org.opengis.feature.simple;

import java.util.Date;
import java.util.List;

import org.opengis.feature.Attribute;
import org.opengis.feature.FeatureFactory;
import org.opengis.feature.GeometryAttribute;
import org.opengis.feature.type.AttributeDescriptor;
import org.opengis.feature.type.GeometryType;
import org.opengis.referencing.crs.CoordinateReferenceSystem;


/**
 * This interface denotes a factory capable of producing SimpleFeature.
 * <p>
 * This is an abstract factory describing how to create a set of classes
 * targeted towards a SimpleFeature implementation. The methods below define no
 * additional capability over Simp
 * </p>
 *
 * @author Jody Garnett
 */
public interface SimpleFeatureFactory {
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
            Object value, AttributeDescriptor desc, String id, CoordinateReferenceSystem crs);

    /**
     * Creates a new simple feature.
     *
     * @param attributes attributes order dicated by provided <code>type</code>
     * @param type Type of SimpleFeature to be created
     * @param id The id of the feature, (fid), may be null depending on the type.
     *
     *
     * @throws IllegalArgumentException If desc.getType() does not return an
     * instanceof {@link SimpleFeatureType}.
     */
    SimpleFeature createSimpleFeature(List attributes, SimpleFeatureType type, String id);

    /**
     * Createsa a new simple feature collection.
     *
     * @param type Type of SimpleFeatureCollection to be created
     * @param id The id of the feature collection
     *
     * @throws IllegalArgumentException If desc.getType() does not return an
     * instanceof {@link org.opengis.feature.simple.SimpleFeatureCollectionType}.
     */
    SimpleFeatureCollection createSimpleFeatureCollection(
            SimpleFeatureCollectionType type, String id);
}
