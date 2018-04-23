/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    http://www.geoapi.org
 *
 *    Copyright (C) 2006-2018 Open Geospatial Consortium, Inc.
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

import java.util.Map;
import java.util.Collection;
import org.opengis.util.GenericName;


/**
 * An instance of an {@link AttributeType} containing the value of an attribute in a feature.
 * {@code Attribute} holds two main information:
 *
 * <ul>
 *   <li>A reference to an {@link AttributeType} which define the base Java type and domain of valid values.</li>
 *   <li>A value, which may be a singleton ([0 … 1] cardinality) or multi-valued ([0 … ∞] cardinality).</li>
 * </ul>
 *
 * <div class="note"><b>Analogy with Java language</b><br>
 * an attribute is similar to a "field" in a Java object. A field also brings together a field name, value and type.
 * However attributes are limited to fields of primitive types or arrays of primitive types.
 * For a field of other Java type, use {@link FeatureAssociation} instead.
 * </div>
 *
 * {@code Attribute} can be instantiated by calls to {@link AttributeType#newInstance()}.
 *
 * @param <V> The type of attribute values.
 *
 * @author  Jody Garnett (Refractions Research, Inc.)
 * @author  Justin Deoliveira (The Open Planning Project)
 * @author  Martin Desruisseaux (Geomatys)
 * @version 3.1
 * @since   3.1
 *
 * @see AttributeType
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
     * <div class="note"><b>Note on validation</b><br>
     * the verifications performed by this method is implementation dependent.
     * For performance reasons, an implementation may verify only the most basic constraints
     * and offer an other method for performing more extensive validation.
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
     *
     * <div class="note"><b>Implementation note</b><br>
     * there is different approaches in the way that collection elements are related to this property values:
     * <ul>
     *   <li>The collection may be a snapshot of property values at the method invocation time.</li>
     *   <li>The collection may be an unmodifiable view of properties values.</li>
     *   <li>The collection may be <cite>live</cite> (changes in the collection are reflected in this attribute, and vis-versa).</li>
     * </ul>
     * This method does not mandate a particular approach.
     * However implementations should document which policy they choose.
     * </div>
     *
     * @return the attribute values.
     */
    Collection<V> getValues();

    /**
     * Sets the attribute values. All previous values are replaced by the given collection.
     *
     * <div class="note"><b>Note on validation</b><br>
     * the verifications performed by this method is implementation dependent.
     * For performance reasons, an implementation may verify only the most basic constraints
     * and offer an other method for performing more extensive validation.
     * Implementations should document their validation process.</div>
     *
     * @param  values  the new values.
     * @throws InvalidPropertyValueException if this method verifies argument validity and the given values
     *         do not met the attribute constraints.
     */
    void setValues(Collection<? extends V> values) throws InvalidPropertyValueException;

    /**
     * Other attributes that describes this attribute. For example if this attribute carries a measurement,
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
     * @return other attribute types that describes this attribute type, or an empty map if none.
     *
     * @see AttributeType#characteristics()
     */
    Map<String,Attribute<?>> characteristics();
}
