/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    Copyright © 2006-2023 Open Geospatial Consortium, Inc.
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
     * {@snippet lang="java" :
     * assert property.getType() == getType().getProperty(property.getName());
     * }
     *
     * <div class="note"><b>Note:</b> this method is useful for storing non-default {@code Attribute} or
     * {@code Association} implementations in this feature. When default implementations are sufficient,
     * the {@link #setPropertyValue(String, Object)} method is preferred.</div>
     *
     * @param  property  the property to set.
     * @throws PropertyNotFoundException if the name of the given property is not a property name of this feature.
     * @throws InvalidPropertyValueException if the value of the given property is not valid.
     * @throws IllegalArgumentException if the property cannot be set for another reason
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
     * @return value of the specified property,
     *         or the {@linkplain AttributeType#getDefaultValue() default value} (which may be {@code null}} if none.
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
     * and offer another method for performing more extensive validation.
     * Implementations should document their validation process.</div>
     *
     * @param  name   the property name.
     * @param  value  the new value for the specified property (may be {@code null}).
     * @throws PropertyNotFoundException if the given name is not an attribute or association name of this feature.
     * @throws ClassCastException if the value is not assignable to the expected value class.
     * @throws InvalidPropertyValueException if the given value is not valid for a reason other than its type.
     *
     * @see Attribute#setValue(Object)
     * @see FeatureAssociation#setValue(Feature)
     */
    void setPropertyValue(final String name, final Object value) throws IllegalArgumentException;

    /**
     * Returns the value for the property of the given name if that property exists, or a fallback value otherwise.
     * This method is equivalent to the following code, but potentially more efficient when the property does not exist:
     *
     * {@snippet lang="java" :
     * try {
     *     return getPropertyValue(name);
     * } catch (PropertyNotFoundException ignore) {
     *     return missingPropertyFallback
     * }}
     *
     * Note that if a property of the given name exists but has no value, then this method returns
     * the {@linkplain AttributeType#getDefaultValue() default value} (which may be {@code null}).
     * <cite>Property without value</cite> is not equivalent to <cite>non-existent property</cite>.
     *
     * @param  name  the property name.
     * @param  missingPropertyFallback  the (potentially {@code null}) value to return
     *         if no attribute or association of the given name exists.
     * @return value or default value of the specified property, or {@code missingPropertyFallback}
     *         if no attribute or association of that name exists. This value may be {@code null}.
     */
    Object getValueOrFallback(String name, Object missingPropertyFallback);
}
