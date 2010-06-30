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
package org.opengis.feature;

import org.opengis.feature.type.AssociationDescriptor;
import org.opengis.feature.type.AssociationType;
import org.opengis.feature.type.AttributeType;

/**
 * Extension of Property to represent an Association, or relationship, between two attributes.
 * <p>
 * The notion of an "association" is similar to that of an association in UML
 * and is used to model a relationship between entities.
 * </p>
 * Examples of such a relationship could be:
 * <ul>
 * <li>aggregation: An attribute may contain another attribute
 * <li>spatial: A feature is spatial related to another (touches, intersects, etc..)
 * <li>temporal: An is a previous version of another attribute in a versioning system
 * </ul>
 * </p>
 * <h2>Example</h2>
 * <p>
 * The value of an association is an {@link Attribute}. As an example consider
 * the following xml complex type definitions:
 * <pre>
 *   &lt;complexType name="fooType">
 *     ...
 *   &lt;/complexType>
 *   &lt;element name="foo" type="fooType"/>
 *
 *   &lt;complexType name="barType">
 *     &lt;sequence>
 *       &lt;element name="intAttribute" type="xs:int"/>
 *       &lt;element name="stringAttribute" type="xs:string"/>
 *       &lt;element name="fooAssociation" type="xlink:href"/>
 *     &lt;/sequence>
 *   &lt;/complexType>
 *   &lt;element name="bar" type="barType"/>
 * </pre>
 * In the above, "fooType" is an identifiable type. Now consider the following
 * section of an xml instance document:
 * <pre>
 *   &lt;foo id="someId">
 *     ...
 *   &lt;/foo>
 *   ...
 *   &lt;bar>
 *     &lt;intAttribute>1&lt;/intAttribute>
 *     &lt;stringAttribute>one&lt;/stringAttribute>
 *     &lt;fooAssociation>someId&lt;/fooAssociation>
 *   &lt;/bar>
 * </pre>
 * Realizing this as objects with attributes and associations we get:
 * <pre>
 *   ComplexAttribute bar = ...;
 *
 *   //intAttribute
 *   Attribute intAttribute = (Attribute) bar.getProperty( "intAttribute" );
 *   intAttribute.getValue() == 1
 *
 *   //stringAttribute
 *   Attribute stringAttribute = (Attribute) bar.getProperty( "stringAttribute" );
 *   stringAttribute.getValue() == "one"
 *
 *   //fooAssociation
 *   Association fooAssociation = (Association) bar.getProperty( "fooAssociation" );
 *   Attribute foo =  fooAssociation.getValue();
 * </pre>
 * </p>
 * 
 * @author Jody Garnett, Refractions Research
 * @author Justin Deoliveira, The Open Planning Project
 */
public interface Association extends Property {

    /**
     * Description of the relationship between two attributes.
     * 
     * Override of {@link Property#getDescriptor()} which type narrows to
     * {@link AssociationDescriptor}.
     *
     * @see Property#getDescriptor()
     * @return AssociationDescriptor used to describe the relationship between two attributes; because
     *         two attributes are required the descriptor should not be null.
     */
    AssociationDescriptor getDescriptor();

     /**
      * Type of association represented.
      * <p>
      * 
     * Override of {@link Property#getType()} which type narrows to
     * {@link AssociationType}.
     *
     * @see Property#getType()
     */
    AssociationType getType();

    /**
     * Override of {@link Property#getValue()} which type narrows to
     * {@link Attribute}.
     *
     * @see Property#getValue()
     */
    Attribute getValue();

    /**
     * Override of {@link Property#setValue(Object)} which specifies that
     * <tt>newValue</tt> should be an instance of {@link Attribute}.
     *
     * @throws IllegalArgumentException If <tt>newValue</tt> is not an attribute.
     */
    void setValue(Object newValue) throws IllegalArgumentException;

    /**
     * Returns the type of the associated attribute.
     * <p>
     * This method is a convenience for:
     * <pre>
     * getType().getRelatedType()
     * </pre>
     * <p>
     *
     * @return type of the attribute of the association.
     */
    AttributeType getRelatedType();
}
