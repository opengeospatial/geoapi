/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    http://www.geoapi.org
 *
 *    Copyright (C) 2006-2010 Open Geospatial Consortium, Inc.
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
package org.opengis.feature.type;

import java.util.Collection;
import java.util.List;

import org.opengis.feature.simple.SimpleFeatureType;
import org.opengis.filter.Filter;
import org.opengis.referencing.crs.CoordinateReferenceSystem;
import org.opengis.util.InternationalString;

/**
 * Factory for types and descriptors.
 * <p>
 * Implementations of this interface should not contain any "special logic" for
 * creating types. Method implementations should be straight through calls to a
 * constructor.
 * </p>
 * @author Gabriel Roldan (Axios Engineering)
 * @author Justin Deoliveira (The Open Planning Project)
 */
public interface FeatureTypeFactory {
    /**
     * Creates a schema.
     *
     * @param namespaceURI The uri of the schema.
     */
    Schema createSchema(String namespaceURI);

    /**
     * Creates an association descriptor.
     *
     * @param type
     *  The type of the described association.
     * @param name
     *  The name of the described association.
     * @param minOccurs
     *  The minimum number of occurences of the described association.
     * @param maxOCcurs
     *  The maximum number of occurences of the described association.
     * @param isNillable
     *  Flag indicating wether the association is allowed to be <code>null</code>.
     */
    AssociationDescriptor createAssociationDescriptor(
        AssociationType type, Name name, int minOccurs, int maxOCcurs,
        boolean isNillable
    );

    /**
     * Creates an attribute descriptor.
     *
     * @param type
     *  The type of the described attribute.
     * @param name
     *  The name of the described attribute.
     * @param minOccurs
     *  The minimum number of occurences of the described attribute.
     * @param maxOccurs
     *  The maximum number of occurences of the described attribute.
     * @param isNillable
     *  Flag indicating if the described attribute may have a null value.
     * @param defaulValue
     *  The default value of the described attribute.
     */
    AttributeDescriptor createAttributeDescriptor(
        AttributeType type, Name name, int minOccurs, int maxOccurs,
        boolean isNillable, Object defaultValue
    );

    /**
     * Creates a geometry descriptor.
     *
     * @param type
     *  The type of the described attribute.
     * @param name
     *  The name of the described attribute.
     * @param minOccurs
     *  The minimum number of occurences of the described attribute.
     * @param maxOccurs
     *  The maximum number of occurences of the described attribute.
     * @param isNillable
     *  Flag indicating if the described attribute may have a null value.
     * @param defaulValue
     *  The default value of the described attribute.
     */
    GeometryDescriptor createGeometryDescriptor(
        GeometryType type, Name name, int minOccurs, int maxOccurs,
        boolean isNillable, Object defaultValue
    );

    /**
     * Creates an association type.
     *
     * @param name
     *  The name of the type.
     * @param relatedType
     *  The type of attributes referenced by the association.
     * @param isAbstract
     *  Flag indicating if the type is abstract.
     * @param restrictions
     *  Set of restrictions on the association.
     * @param superType
     *  Parent type.
     * @param description
     *  A description of the type..
     */
    AssociationType createAssociationType(
        Name name, AttributeType relatedType, boolean isAbstract,
        List<Filter> restrictions, AssociationType superType,
        InternationalString description
    );

    /**
     * Creates an attribute type.
     *
     * @param name
     *  The name of the type.
     * @param binding
     *  The class that values of attributes of the type.
     * @param isIdentifiable
     *  Flag indicating if the attribute is identifiable.
     * @param isAbstract
     *  Flag indicating if the type is abstract.
     * @param restrictions
     *  Set of restrictions on the attribute.
     * @param superType
     *  Parent type.
     * @param description
     *  A description of the type.
     */
    AttributeType createAttributeType(
        Name name, Class<?> binding, boolean isIdentifiable, boolean isAbstract,
        List<Filter> restrictions, AttributeType superType, InternationalString description
    );

    /**
     * Creates a geometric attribute type.
     *
     * @param name
     *  The name of the type.
     * @param binding
     *  The class of values of attributes of the type.
     * @param crs
     *  The coordinate reference system of the type.
     * @param isIdentifiable
     *  Flag indicating if the attribute is identifiable.
     * @param isAbstract
     *  Flag indicating if the type is abstract.
     * @param restrictions
     *  Set of restrictions on the attribute.
     * @param superType
     *  Parent type.
     * @param description
     *  A description of the type.
     */
    GeometryType createGeometryType(
        Name name, Class<?> binding, CoordinateReferenceSystem crs, boolean isIdentifiable,
        boolean isAbstract, List<Filter> restrictions, AttributeType superType,
        InternationalString description
    );

    /**
     * Creates a complex type.
     *
     * @param name
     *  The name of the type.
     * @param schema
     *  Collection of property descriptors which define the type.
     * @param isIdentifiable
     *  Flag indicating if the attribute is identifiable.
     * @param isAbstract
     *  Flag indicating if the type is abstract.
     * @param restrictions
     *  Set of restrictions on the attribute.
     * @param superType
     *  Parent type.
     * @param description
     *  A description of the type.
     */
    ComplexType createComplexType(
        Name name, Collection<PropertyDescriptor> schema, boolean isIdentifiable,
        boolean isAbstract, List<Filter> restrictions, AttributeType superType,
        InternationalString description
    );

    /**
     * Creates a feature type.
     *
     * @param name
     *  The name of the type.
     * @param schema
     *  Collection of property descriptors which define the type.
     * @param isAbstract
     *  Flag indicating if the type is abstract.
     * @param restrictions
     *  Set of restrictions on the attribute.
     * @param superType
     *  Parent type.
     * @param description
     *  A description of the type.
     */
    FeatureType createFeatureType(
        Name name, Collection<PropertyDescriptor> schema,
        GeometryDescriptor defaultGeometry, boolean isAbstract,
        List<Filter> restrictions, AttributeType superType, InternationalString description
    );

    /**
     * Creates a simple feature type.
     *
     * @param name
     *  The name of the type.
     * @param schema
     *  List of attribute descriptors which define the type.
     * @param isAbstract
     *  Flag indicating if the type is abstract.
     * @param restrictions
     *  Set of restrictions on the attribute.
     * @param superType
     *  Parent type.
     * @param description
     *  A description of the type.
     */
    SimpleFeatureType createSimpleFeatureType(
        Name name, List<AttributeDescriptor> schema, GeometryDescriptor defaultGeometry,
        boolean isAbstract, List<Filter> restrictions, AttributeType superType,
        InternationalString description
    );
}
