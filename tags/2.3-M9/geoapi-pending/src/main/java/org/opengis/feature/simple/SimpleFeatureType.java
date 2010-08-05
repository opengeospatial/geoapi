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
package org.opengis.feature.simple;

import java.util.List;

import org.opengis.feature.type.AttributeDescriptor;
import org.opengis.feature.type.AttributeType;
import org.opengis.feature.type.FeatureType;
import org.opengis.feature.type.Name;

/**
 * The type of a SimpleFeature.
 * <p>
 * The definition of a "simple feature" can be summed up as the following:
 * <ul>
 * <li>made up of only non-complex attributes, no associations
 * <li>attributes are of multiplicity 1
 * <li>attributes are ordered
 * <li>attribute names are unqualified (namespaceURI == null)
 * </ul>
 * </p>
 * <p>
 * <h3>Attribute Indexing</h3>
 * The attributes which compose a simple feature type are ordered. For this
 * reason attributes are available via a simple index. Given the following type
 * definition:
 *
 * <pre>
 *   &lt;complexType name=&quot;mySimpleType&quot;/&gt;
 *     &lt;sequence&gt;
 *        &lt;element name=&quot;foo&quot; type=&quot;xs:string&quot;/&gt;
 *        &lt;element name=&quot;bar&quot; type=&quot;xs:integer&quot;/&gt;
 *     &lt;/sequence&gt;
 *   &lt;/complexType&gt;
 * </pre>
 *
 * <br>
 * The attribute descriptor are addressable via index:
 *
 * <pre>
 *   SimpleFeatureType type = ...;
 *
 *   AttributeDescriptor foo = type.getAttribute( 0 );
 *   AttributeDescriptor bar-= type.getAttribute( 1 );
 * </pre>
 *
 * <p>
 * <h3>Attribute Multiplicity</h3>
 * With simple feature types, the multiplicity of attributes is always assumed
 * to be 1, ie, <code>getMinOccurs() == 1</code> and
 * <code>getMaxOccurs() == 1</code>. A consequence of this is that attributes
 * from a simple feature always line up 1 to 1 with the descriptors from the
 * type:
 *
 * <pre>
 *   SimpleFeature feature = ...;
 *   SimpleFeatureType type = feature.getType();
 *
 *   type.getAttribute( 0 ).getDescriptor() == type.getAttribute( 0 );
 *   type.getAttribute( 1 ).getDescriptor() == type.getAttribute( 1 );
 * </pre>
 *
 * </p>
 *
 * <p>
 * <h3>Attribute Naming</h3>
 * The names of attributes in a simple feature type are never namespace
 * qualified. For this reason there is no difference between accessing an
 * attribute with {@link #getDescriptor(String)} and {@link #getDescriptor(Name)}.
 * </p>
 *
 * @author Jody Garnett, Refractions Research
 * @author Justin Deoliveira, The Open Planning Project
 */
public interface SimpleFeatureType extends FeatureType {

    /**
     * The local name for this FeatureType.
     *
     * Specifically this method returns <code>getName().getLocalPart().</code>
     * @return The local name for this FeatureType.
     */
    String getTypeName();

    /**
     * The list of attribute descriptors which make up the feature type.
     * <p>
     * This method is a convenience for:
     *
     * <pre>
     * return (List&lt;AttributeDescriptor&gt;) getProperties();
     * </pre>
     *
     * </p>
     *
     * @return The ordered list of attribute descriptors.
     */
    List<AttributeDescriptor> getAttributeDescriptors();

    /**
     * Returns the attribute descriptor which matches the specified name.
     * <p>
     * This method is convenience for:
     *
     * <pre>
     * return (AttributeDescriptor) getProperty(name);
     * </pre>
     *
     * </p>
     * <p>
     * This method returns <code>null</code> if no such attribute exists.
     * </p>
     *
     * @param name
     *            The name of the descriptor to return.
     *
     * @return The attribute descriptor matching the specified name, or
     *         <code>null</code> if no such attribute exists.
     */
    AttributeDescriptor getDescriptor(String name);

    /**
     * Returns the attribute descriptor which matches the specified name.
     * <p>
     * This method is convenience for:
     *
     * <pre>
     * return (AttributeDescriptor) getProperty(name);
     * </pre>
     *
     * </p>
     * <p>
     * This method returns <code>null</code> if no such attribute exists.
     * </p>
     *
     * @param name
     *            The name of the descriptor to return.
     *
     * @return The attribute descriptor matching the specified name, or
     *         <code>null</code> if no such attribute exists.
     */
    AttributeDescriptor getDescriptor(Name name);

    /**
     * Returns the attribute descriptor at the specified index.
     * <p>
     * This method is convenience for:
     *
     * <pre>
     * return (AttributeDescriptor) ((List) getProperties()).get(index);
     * </pre>
     *
     * </p>
     *
     * @param name
     *            The name of the descriptor to return.
     *
     * @return The attribute descriptor at the specified index.
     *
     * @throws IndexOutOfBoundsException
     *             When the index is out of bounds.
     */
    AttributeDescriptor getDescriptor(int index)
            throws IndexOutOfBoundsException;

    /**
     * Returns the number of attributes composing the feature type
     * <p>
     * This method is convenience for <code>getAttributes().size()</code>.
     * </p>
     * @return The number of attributes.
     */
    int getAttributeCount();

    /**
     * Returns the types of all the attributes which make up the feature.
     * <p>
     * This method is convenience for:
     *
     * <pre>
     * List types = new ArrayList();
     * for (Property p : getProperties()) {
     *     types.add(p.getType());
     * }
     * return types;
     * </pre>
     *
     * </p>
     *
     * @return The list of attribute types.
     */
    List<AttributeType> getTypes();

    /**
     * Returns the type of the attribute which matches the specified name.
     * <p>
     * This method is convenience for:
     *
     * <pre>
     * return (AttributeType) getProperty(name).getType();
     * </pre>
     *
     * </p>
     * <p>
     * If there is no such attribute which matches <tt>name</tt>,
     * <code>null</code> is returned.
     * </p>
     *
     * @param name
     *            The name of the attribute whose type to return.
     *
     * @return The attribute type matching the specified name, or
     *         <code>null</code>.
     */
    AttributeType getType(String name);

    /**
     * Returns the type of the attribute which matches the specified name.
     * <p>
     * This method is convenience for:
     *
     * <pre>
     * return (AttributeType) getProperty(name).getType();
     * </pre>
     *
     * </p>
     * <p>
     * If there is no such attribute which matches <tt>name</tt>,
     * <code>null</code> is returned.
     * </p>
     *
     * @param name
     *            The name of the attribute whose type to return.
     *
     * @return The attribute type matching the specified name, or
     *         <code>null</code>.
     */
    AttributeType getType(Name name);

    /**
     * Returns the type of the attribute at the specified index.
     * <p>
     * This method is convenience for:
     *
     * <pre>
     *   return (AttributeType)((List)getProperties()).get(index)).getType();
     * </pre>
     *
     * </p>
     *
     * @param index
     *            The index of the attribute whose type to return.
     *
     * @return The attribute type at the specified index.
     *
     * @throws IndexOutOfBoundsException
     *             When the index is out of bounds.
     */
    AttributeType getType(int index) throws IndexOutOfBoundsException;

    /**
     * Returns the index of the attribute which matches the specified name.
     * <p>
     * -1 is returned in the instance there is no attribute matching the
     * specified name.
     * </p>
     *
     * @param name
     *            The name of the attribute whose index to return.
     *
     * @return index of named attribute, or -1 if not found.
     */
    int indexOf(String name);

    /**
     * Returns the index of the attribute which matches the specified name.
     * <p>
     * -1 is returned in the instance there is no attribute matching the
     * specified name.
     * </p>
     *
     * @param name
     *            The name of the attribute whose index to return.
     *
     * @return index of named attribute, or -1 if not found.
     */
    int indexOf(Name name);
}
