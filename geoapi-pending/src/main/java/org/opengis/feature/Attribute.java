/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    http://www.geoapi.org
 *
 *    Copyright (C) 2006-2013 Open Geospatial Consortium, Inc.
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

import org.opengis.feature.type.AttributeDescriptor;
import org.opengis.feature.type.AttributeType;
import org.opengis.filter.identity.Identifier;

/**
 * An extension of Property for an attribute, or data.
 * <p>
 * The notion of an "attribute" is similar to that of an attribute in UML.
 * </p>
 * <p>
 * This interface is capable of modelling "primitive data", things like strings,
 * numerics, dates, etc... However for "complex data" (that is non-primitive
 * data types which are made up other primitive data types), a specific
 * sub-interface is used, see {@link ComplexAttribute}.
 * </p>
 * <p>
 * An analogy for an attribute is a "field" in a java object. A field also
 * brings together a field name, value and type.
 * </p>
 *
 * <p>
 * <h3>Identifiable</h3>
 *
 * When an attribute is identifiable the {@link #getID()} method returns a
 * unique identifier for the attribute. The type of the attribute is used to
 * determine identifiability.
 *
 * <pre>
 * Attribute attribute = ...;
 * if ( attribute.getType().isIdentified() ) {
 *   String id = attribute.getID();
 * }
 * </pre>
 * </p>
 * <h3>Validation</h3>
 * 
 * An attribute may hold any value at runtime; checking that the value meets the constraints
 * supplied by the AttributeType is the work of the validate() method.
 * 
 * @see Property
 *
 * @author Jody Garnett (Refractions Research)
 * @author Justin Deoliveira (The Open Planning Project)
 */
public interface Attribute extends Property {

    /**
     * Override of {@link Property#getDescriptor()} which type narrows to
     * {@link AttributeDescriptor}.
     *
     * @see Property#getDescriptor()
     * @return The attribute descriptor, may be null if this is a top level type
     */
    AttributeDescriptor getDescriptor();

    /**
     * Override of {@link Property#getType()} which type narrows to
     * {@link AttributeType}.
     *
     * @see Property#getType()
     * @return The attribute type.
     */
    AttributeType getType();

    /**
     * Unique Identifier for the attribute.
     * <p>
     * This value is non-null in the case that
     * <code>getType().isIdentifiable()</code> is <code>true</code>.
     * </p>
     *
     * @return A unique identifier for the attribute, or <code>null</code> if
     *         the attribute is non-identifiable.
     */
    Identifier getIdentifier();
    
    /**
     * Check the attribute value against the constraints provided by the AttributeDescriptor.
     * <p>
     * Please note this method checks the value only - it should have the correct java binding,
     * it should only be null if isNillable is true; and if a value is provided it should
     * satisfy all of the restrictions provided.
     * <p>
     * To check the the number of times an attribute is used (minOccurs and maxOccurs) please
     * use ComplexAttribute.validate().
     * 
     * @thorws IllegalAttributeException If value fails validation
     */
    void validate() throws IllegalAttributeException;
}
