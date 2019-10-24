/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    http://www.geoapi.org
 *
 *    Copyright (C) 2006-2019 Open Geospatial Consortium, Inc.
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


/**
 * An instance of a {@link FeatureType} containing values for a real-world phenomena.
 * Each feature instance can provide values for the following properties:
 *
 * <ul>
 *   <li>{@linkplain Attribute          Attributes}, which may be {@linkplain DynamicAttribute dynamic}</li>
 *   <li>{@linkplain FeatureAssociation Associations to other features}</li>
 *   <li>{@linkplain Operation          Operations}</li>
 * </ul>
 *
 * {@code Feature} can be instantiated by calls to {@link FeatureType#newInstance()}.
 *
 * <h2>Simple features</h2>
 * A feature is said “simple” if it complies to all the following conditions:
 * <ul>
 *   <li>the feature allows only attributes and operations (no associations),</li>
 *   <li>the multiplicity of all attributes is constrained to [1 … 1].</li>
 * </ul>
 *
 * <h2>Moving features</h2>
 * A feature is a moving feature if it complies to at least one of the following conditions:
 * <ul>
 *   <li>the feature contains {@linkplain DynamicAttribute dynamic attributes},</li>
 *   <li>the geometry value of an {@linkplain Attribute attribute} is a trajectory.</li>
 * </ul>
 *
 * @author  Jody Garnett (Refractions Research, Inc.)
 * @author  Justin Deoliveira (The Open Planning Project)
 * @author  Martin Desruisseaux (Geomatys)
 * @version 3.1
 * @since   3.1
 *
 * @see FeatureType
 */
public interface Feature {
    /**
     * Returns information about the feature (name, characteristics, <i>etc.</i>).
     *
     * @return information about the feature.
     */
    FeatureType getType();

    /**
     * Returns the property (attribute, feature association or operation result) of the given name.
     * If the property type is a parameterless {@link Operation}, then this method may return
     * the result of {@linkplain Operation#apply executing} the operation on this feature,
     * at implementation choice.
     *
     * <div class="note"><b>Tip:</b> this method returns the property <em>instance</em>.
     * If only the property <em>value</em> is desired, then {@link #getPropertyValue(String)} is preferred
     * since it gives to implementations a chance to avoid the creation of {@link Attribute} or
     * {@link FeatureAssociation} instances.</div>
     *
     * @param  name  the property name.
     * @return the property of the given name (never {@code null}).
     * @throws PropertyNotFoundException if the given argument is not a property name of this feature.
     *
     * @see #getPropertyValue(String)
     * @see FeatureType#getProperty(String)
     */
    Property getProperty(String name) throws PropertyNotFoundException;

    /**
     * Sets the property (attribute or feature association).
     * The given property shall comply to the following conditions:
     *
     * <ul>
     *   <li>It must be non-null.</li>
     *   <li>Its {@linkplain Property#getName() name} shall be the name of the property to set in this feature.</li>
     *   <li>Its type shall be the same instance than the {@linkplain FeatureType#getProperty(String) property type}
     *       defined by the feature type for the above name. In other words, the following condition shall hold:</li>
     * </ul>
     *
     * <blockquote><pre>assert property.getType() == getType().getProperty(property.getName());</pre></blockquote>
     *
     * <div class="note"><b>Note:</b> this method is useful for storing non-default {@code Attribute} or
     * {@code Association} implementations in this feature. When default implementations are sufficient,
     * the {@link #setPropertyValue(String, Object)} method is preferred.</div>
     *
     * @param  property  the property to set.
     * @throws PropertyNotFoundException if the name of the given property is not a property name of this feature.
     * @throws InvalidPropertyValueException if the value of the given property is not valid.
     * @throws IllegalArgumentException if the property can not be set for another reason
     *         (e.g. a library may accept only some specific property instances).
     *
     * @see #setPropertyValue(String, Object)
     */
    void setProperty(Property property) throws IllegalArgumentException;

    /**
     * Returns the value for the property of the given name.
     * This convenience method is equivalent to invoking {@link #getProperty(String)} for the given name,
     * then to perform one of the following actions depending on the property type and the multiplicity:
     *
     * <table class="ogc">
     *   <caption>Class of returned value</caption>
     *   <tr><th>Property type</th>                  <th>max. occurs</th> <th>Method invoked</th>                         <th>Return type</th></tr>
     *   <tr><td>{@link AttributeType}</td>          <td>0 or 1</td>      <td>{@link Attribute#getValue()}</td>           <td>{@link Object}</td></tr>
     *   <tr><td>{@code AttributeType}</td>          <td>2 or more</td>   <td>{@link Attribute#getValues()}</td>          <td>{@code Collection<?>}</td></tr>
     *   <tr><td>{@link FeatureAssociationRole}</td> <td>0 or 1</td>      <td>{@link FeatureAssociation#getValue()}</td>  <td>{@link Feature}</td></tr>
     *   <tr><td>{@code FeatureAssociationRole}</td> <td>2 or more</td>   <td>{@link FeatureAssociation#getValues()}</td> <td>{@code Collection<Feature>}</td></tr>
     * </table>
     *
     * <div class="note"><b>Note:</b> “max. occurs” is the {@linkplain AttributeType#getMaximumOccurs()
     * maximum number of occurrences} and does not depend on the actual number of values. If an attribute allows
     * more than one value, then this method will always return a collection for that attribute even if the collection
     * is empty.</div>
     *
     * @param  name  the property name.
     * @return the value for the given property, or {@code null} if none.
     * @throws PropertyNotFoundException if the given argument is not an attribute or association name of this feature.
     *
     * @see Attribute#getValue()
     * @see FeatureAssociation#getValue()
     */
    Object getPropertyValue(String name) throws PropertyNotFoundException;

    /**
     * Sets the value for the property of the given name.
     *
     * <div class="note"><b>Note on validation</b>:
     * the verifications performed by this method is implementation dependent.
     * For performance reasons, an implementation may verify only the most basic constraints
     * and offer an other method for performing more extensive validation.
     * Implementations should document their validation process.</div>
     *
     * @param  name   the property name.
     * @param  value  the new value for the given property (may be {@code null}).
     * @throws PropertyNotFoundException if the given name is not an attribute or association name of this feature.
     * @throws ClassCastException if the value is not assignable to the expected value class.
     * @throws InvalidPropertyValueException if the given value is not valid for a reason other than its type.
     *
     * @see Attribute#setValue(Object)
     * @see FeatureAssociation#setValue(Feature)
     */
    void setPropertyValue(final String name, final Object value) throws IllegalArgumentException;
}
