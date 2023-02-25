/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    http://www.geoapi.org
 *
 *    Copyright (C) 2006-2021 Open Geospatial Consortium, Inc.
 *    All Rights Reserved. http://www.opengeospatial.org/ogc/legal
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

import java.util.Map;
import java.util.Collection;
import org.opengis.util.GenericName;


/**
 * An instance of an {@link AttributeType} containing the value of an attribute in a feature.
 * {@code Attribute} holds three main information:
 *
 * <ul>
 *   <li>A {@linkplain #getType() reference to an attribute type}
 *       which defines the base Java type and domain of valid values.</li>
 *   <li>One or more {@linkplain #getValues() values}, which may be a singleton ([0 … 1] multiplicity)
 *       or multi-valued ([0 … ∞] multiplicity).</li>
 *   <li>Optional {@linkplain #characteristics() characteristics} about the attribute
 *       (e.g. a <var>temperature</var> attribute may have a characteristic holding the measurement <var>accuracy</var>).
 *       Characteristics are often, but not necessarily, constant for all attributes of the same type in a dataset.</li>
 * </ul>
 *
 * <div class="note"><b>Analogy with Java language</b>:
 * an attribute is similar to a "field" in a Java object. A field also brings together a field name, value and type,
 * optionally completed by annotations. The value types are typically {@link String}, {@link Number} or collections
 * of them, but other Java type are allowed except {@link Feature}.
 * For storing a {@code Feature} value, use {@link FeatureAssociation} instead.
 * </div>
 *
 * <p>{@code Attribute} can be instantiated by calls to {@link AttributeType#newInstance()}.</p>
 *
 * @param <V> the type of attribute values. If the attribute supports multi-occurrences,
 *            then this is the type of elements (not the collection type).
 *
 * @author  Jody Garnett (Refractions Research, Inc.)
 * @author  Justin Deoliveira (The Open Planning Project)
 * @author  Martin Desruisseaux (Geomatys)
 * @version 3.1
 * @since   3.1
 *
 * @see AttributeType
 * @see DynamicAttribute
 */
public interface Attribute<V> extends Property {
    /**
     * Returns the name of this attribute as defined by its {@linkplain #getType() type}.
     * This convenience method delegates to {@link AttributeType#getName()}.
     *
     * @return the attribute name specified by its type.
     */
    @Override
    GenericName getName();

    /**
     * Returns information about the attribute (base Java class, domain of values, <i>etc.</i>).
     *
     * @return information about the attribute.
     */
    AttributeType<V> getType();

    /**
     * Returns the attribute value, or {@code null} if none. This convenience method can be invoked
     * in the common case where the {@linkplain AttributeType#getMaximumOccurs() maximum number}
     * of attribute values is restricted to 1 or 0.
     *
     * @return the attribute value (may be {@code null}).
     * @throws MultiValuedPropertyException if this attribute contains more than one value.
     *
     * @see Feature#getPropertyValue(String)
     */
    @Override
    V getValue() throws MultiValuedPropertyException;

    /**
     * Sets the attribute value. All previous values are replaced by the given singleton.
     *
     * <div class="note"><b>Note on validation</b>:
     * the verifications performed by this method is implementation dependent.
     * For performance reasons, an implementation may verify only the most basic constraints
     * and offer another method for performing more extensive validation.
     * Implementations should document their validation process.</div>
     *
     * @param  value  the new value, or {@code null} for removing all values from this attribute.
     * @throws InvalidPropertyValueException if this method verifies argument validity and the given value
     *         does not met the attribute constraints.
     *
     * @see Feature#setPropertyValue(String, Object)
     */
    void setValue(V value) throws InvalidPropertyValueException;

    /**
     * Returns all attribute values, or an empty collection if none.
     * This method supports arbitrary cardinality of attribute values.
     * In the common case where the {@linkplain AttributeType#getMaximumOccurs() maximum number of occurrences}
     * is restricted to 1, {@link #getValue()} is a convenient alternative.
     *
     * <div class="note"><b>Implementation note</b>:
     * there is different approaches in the way that collection elements are related to this property values:
     * <ul>
     *   <li>The collection may be a snapshot of property values at the method invocation time.</li>
     *   <li>The collection may be an unmodifiable view of properties values.</li>
     *   <li>The collection may be <cite>live</cite> (changes in the collection are reflected in this attribute, and vis-versa).</li>
     * </ul>
     * This method does not mandate a particular approach.
     * However, implementations should document which policy they choose.
     * </div>
     *
     * @return the attribute values.
     */
    Collection<V> getValues();

    /**
     * Sets the attribute values. All previous values are replaced by the given collection.
     *
     * <div class="note"><b>Note on validation</b>:
     * the verifications performed by this method is implementation dependent.
     * For performance reasons, an implementation may verify only the most basic constraints
     * and offer another method for performing more extensive validation.
     * Implementations should document their validation process.</div>
     *
     * @param  values  the new values.
     * @throws InvalidPropertyValueException if this method verifies argument validity and the given values
     *         do not met the attribute constraints.
     */
    void setValues(Collection<? extends V> values) throws InvalidPropertyValueException;

    /**
     * Other attributes that describe this attribute. For example if this attribute carries a measurement,
     * then a characteristic of this attribute could be the measurement accuracy.
     * See <cite>"Attribute characterization"</cite> in {@link AttributeType} Javadoc for more information.
     *
     * <p>Attributes having a value equals to their {@linkplain AttributeType#getDefaultValue() default value}
     * do not need to appear in this characteristics map. For example all temperature measurements in a dataset
     * may have the same accuracy, which can be specified only once in the {@link AttributeType#characteristics()}
     * map instead of being repeated in every {@code Attribute.characteristics()} maps.</p>
     *
     * <p>The characteristic values are enumerated in the {@linkplain Map#values() map values}.
     * The {@linkplain Map#keySet() map keys} are the {@code String} representations of
     * characteristics {@linkplain AttributeType#getName() name}, for more convenient lookups.</p>
     *
     * @return other attribute types that describe this attribute type, or an empty map if none.
     *
     * @see AttributeType#characteristics()
     */
    Map<String,Attribute<?>> characteristics();
}
