/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    http://www.geoapi.org
 *
 *    Copyright (C) 2006-2014 Open Geospatial Consortium, Inc.
 *    All Rights Reserved. http://www.opengeospatial.org/ogc/legal
 *
 *    Permission to use, copy, and modify this software and its documentation, with
 *    or without modification, for any purpose and without fee or royalty is hereby
 *    granted, provided that you include the following on ALL copies of the software
 *    and documentation or portions thereof, including modifications, that you make:
 *
 *    1. The full text of this NOTICE in a location viewable to users of the
 *       redistributed or derivative work.
 *    2. Notice of any changes or modifications to the OGC files, including the
 *       date changes were made.
 *
 *    THIS SOFTWARE AND DOCUMENTATION IS PROVIDED "AS IS," AND COPYRIGHT HOLDERS MAKE
 *    NO REPRESENTATIONS OR WARRANTIES, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED
 *    TO, WARRANTIES OF MERCHANTABILITY OR FITNESS FOR ANY PARTICULAR PURPOSE OR THAT
 *    THE USE OF THE SOFTWARE OR DOCUMENTATION WILL NOT INFRINGE ANY THIRD PARTY
 *    PATENTS, COPYRIGHTS, TRADEMARKS OR OTHER RIGHTS.
 *
 *    COPYRIGHT HOLDERS WILL NOT BE LIABLE FOR ANY DIRECT, INDIRECT, SPECIAL OR
 *    CONSEQUENTIAL DAMAGES ARISING OUT OF ANY USE OF THE SOFTWARE OR DOCUMENTATION.
 *
 *    The name and trademarks of copyright holders may NOT be used in advertising or
 *    publicity pertaining to the software without specific, written prior permission.
 *    Title to copyright in this software and any associated documentation will at all
 *    times remain with copyright holders.
 */
package org.opengis.feature;

import java.util.Collection;

import org.opengis.feature.simple.SimpleFeature;
import org.opengis.feature.simple.SimpleFeatureType;
import org.opengis.feature.type.AssociationDescriptor;
import org.opengis.feature.type.AttributeDescriptor;
import org.opengis.feature.type.ComplexType;
import org.opengis.feature.type.FeatureType;
import org.opengis.feature.type.GeometryDescriptor;
import org.opengis.feature.type.GeometryType;
import org.opengis.referencing.crs.CoordinateReferenceSystem;

/**
 * Factory for creation of attributes, associations, and features.
 * <p>
 * Implementations of this interface should not contain any "special logic" for
 * creating attributes and features. Method implementations should be straight
 * through calls to a constructor.
 * </p>
 *
 * @author Gabriel Roldan (Axios Engineering)
 * @author Justin Deoliveira (The Open Planning Project)
 * @since 2.2
 */
public interface FeatureFactory {

    /**
     * Creates an association.
     *
     * @param value The value of the association, an attribute.
     * @param descriptor The association descriptor.
     */
    Association createAssociation(Attribute value, AssociationDescriptor descriptor);

    /**
     * Creates an attribute.
     *
     * @param value The value of the attribute, may be <code>null</code>.
     * @param descriptor The attribute descriptor.
     * @param id The id of the attribute, may be <code>null</code>.
     *
     */
    Attribute createAttribute(Object value, AttributeDescriptor descriptor, String id);

    /**
     * Creates a geometry attribute.
     * <p>
     *  <code>descriptor.getType()</code> must be an instance of {@link GeometryType}.
     * </p>
     * @param value The value of the attribute, may be <code>null</code>.
     * @param descriptor The attribute descriptor.
     * @param id The id of the attribute, may be <code>null</code>.
     * @param crs The coordinate reference system of the attribute, may be <code>null</code>.
     *
     */
    GeometryAttribute createGeometryAttribute(
        Object geometry, GeometryDescriptor descriptor, String id, CoordinateReferenceSystem crs
    );

    /**
     * Creates a complex attribute.
     * <p>
     * <code>descriptor.getType()</code> must be an instance of {@link ComplexType}.
     * </p>
     * @param value The value of the attribute, a collection of properties.
     * @param descriptor The attribute descriptor.
     * @param id The id of the attribute, may be <code>null</code>.
     *
     */
    ComplexAttribute createComplexAttribute(
        Collection<Property> value, AttributeDescriptor descriptor, String id
    );

    /**
     * Creates a complex attribute.
     *
     * @param value The value of the attribute, a collection of properties.
     * @param type The type of the attribute.
     * @param id The id of the attribute, may be <code>null</code>.
     *
     */
    ComplexAttribute createComplexAttribute(
        Collection<Property> value, ComplexType type, String id
    );

    /**
     * Creates a feature.
     * <p>
     *   <code>descriptor.getType()</code> must be an instance of {@link FeatureType}.
     * </p>
     * @param value The value of the feature, a collection of properties.
     * @param descriptor The attribute descriptor.
     * @param id The id of the feature.
     *
     */
    Feature createFeature(Collection<Property> value, AttributeDescriptor descriptor, String id);

    /**
     * Creates a feature.
     *
     * @param value The value of the feature, a collection of properties.
     * @param type The type of the feature.
     * @param id The id of the feature.
     *
     */
    Feature createFeature(Collection<Property> value, FeatureType type, String id);

    /**
     * Create a SimpleFeature from an array of objects.
     * <p>
     * Please note that the provided array may be used directly by an implementation.
     *
     * @param array Object array of values; this array may beused directly.
     * @param type The type of the simple feature.
     * @param id The id of the feature.
     */
    SimpleFeature createSimpleFeature( Object[] array, SimpleFeatureType type, String id );

    /**
     * Creates a simple feature.
     * <p>
     *   <code>descriptor.getType()</code> must be an instance of {@link SimpleFeatureType}.
     * </p>
     * @param array Object array of values; this array may be used directly.
     * @param descriptor The attribute descriptor.
     * @param id The id of the feature.
     *
     */
    SimpleFeature createSimpleFeature( Object[] array, AttributeDescriptor decsriptor, String id);
}
