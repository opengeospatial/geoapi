/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    http://www.geoapi.org
 *
 *    Copyright (C) 2006-2021 Open Geospatial Consortium, Inc.
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
import java.util.Collections;
import org.opengis.annotation.UML;
import org.opengis.annotation.Stereotype;
import org.opengis.annotation.Classifier;
import org.opengis.util.GenericName;

import static org.opengis.annotation.Obligation.*;
import static org.opengis.annotation.Specification.ISO_19109;


/**
 * Definition of an attribute in a feature type.
 * The name of attribute type is mandatory. The name {@linkplain org.opengis.util.GenericName#scope() scope}
 * is typically the name of the {@linkplain FeatureType feature type} containing this attribute, but this is
 * not mandatory. The scope could also be defined by the ontology for example.
 *
 * <div class="note"><b>Note:</b>
 * compared to the Java language, {@code AttributeType} is equivalent to {@link java.lang.reflect.Field}
 * while {@code FeatureType} is equivalent to {@link Class}.</div>
 *
 * <h2>Value type</h2>
 * Attributes can be used for both spatial and non-spatial properties.
 * Some examples are:
 *
 * <table class="ogc">
 *   <caption>Attribute value type examples</caption>
 *   <tr><th>Attribute name</th>      <th>Value type</th></tr>
 *   <tr><td>Building shape</td>      <td>{@link org.opengis.geometry.Geometry}</td></tr>
 *   <tr><td>Building owner</td>      <td>{@link org.opengis.metadata.citation.Responsibility}</td></tr>
 *   <tr><td>Horizontal accuracy</td> <td>{@link org.opengis.metadata.quality.PositionalAccuracy}</td></tr>
 * </table>
 *
 * <h2>Attribute characterization</h2>
 * An {@code Attribute} can be characterized by other attributes. For example, an attribute that carries a measurement
 * (e.g. air temperature) may have another attribute that holds the measurement accuracy (e.g. ±0.1°C).
 * Such accuracy can be stored as a <cite>characteristic</cite> of the measurement attribute.
 *
 * <p>The {@link #characteristics()} method in this {@code AttributeType} interface returns a description of all
 * characteristics that attributes of this type may have. The actual characteristics values can be stored on a
 * record-by-record basis in the {@link Attribute#characteristics()} map.
 * However, in the common case of characteristics having a constant value for all records in a dataset,
 * the constant can be given by the characteristic {@linkplain #getDefaultValue() default value} and
 * {@code Attribute.characteristics()} may return an empty map (at implementation choice).</p>
 *
 * @param <V> the type of attribute values. If the attribute supports multi-occurrences,
 *            then this is the type of elements (not the collection type).
 *
 * @author  Jody Garnett (Refractions Research)
 * @author  Justin Deoliveira (The Open Planning Project)
 * @author  Martin Desruisseaux (Geomatys)
 * @version 3.1
 * @since   3.1
 *
 * @see Attribute
 * @see DynamicAttributeType
 */
@Classifier(Stereotype.METACLASS)
@UML(identifier="AttributeType", specification=ISO_19109)
public interface AttributeType<V> extends PropertyType {
    /**
     * Returns the name of this attribute type.
     * For {@code AttributeType}, the name is mandatory.
     *
     * @return the attribute type name.
     */
    @Override
    @UML(identifier="name", obligation=MANDATORY, specification=ISO_19109)
    GenericName getName();

    /**
     * Returns the type of attribute values.
     *
     * @return the type of attribute values.
     */
    @UML(identifier="valueType", obligation=MANDATORY, specification=ISO_19109)
    Class<V> getValueClass();

    /*
     * ISO 19109 properties omitted for now:
     *
     *   - valueDomain : CharacterString
     *
     * Rational: a CharacterString is hardly programmatically usable. A Range would be better but too specific.
     * We could follow the GeoAPI path and define a "restrictions : Filter" property. That would be more generic,
     * but we are probably better to wait for Filter to be made submitted to a GeoAPI release.
     */

    /**
     * Returns the minimum number of attribute values.
     * The returned value is greater than or equal to zero.
     *
     * <p>To be valid, an {@code Attribute} instance of this {@code AttributeType} shall have at least
     * this minimum number of elements in its {@link Attribute#getValues() collection of values}.</p>
     *
     * @return the minimum number of attribute values.
     */
    @UML(identifier="cardinality", obligation=MANDATORY, specification=ISO_19109)
    int getMinimumOccurs();

    /**
     * Returns the maximum number of attribute values.
     * The returned value is greater than or equal to the {@link #getMinimumOccurs()} value.
     * If there is no maximum, then this method returns {@link Integer#MAX_VALUE}.
     *
     * <p>To be valid, an {@code Attribute} instance of this {@code AttributeType} shall have no more than
     * this maximum number of elements in its {@link Attribute#getValues() collection of values}.</p>
     *
     * @return the maximum number of attribute values, or {@link Integer#MAX_VALUE} if none.
     */
    @UML(identifier="cardinality", obligation=MANDATORY, specification=ISO_19109)
    int getMaximumOccurs();

    /**
     * Returns the default value for the attribute.
     * This value is used when an attribute is created and no value for it is specified.
     *
     * @return the default value for the attribute, or {@code null} if none.
     */
    V getDefaultValue();

    /**
     * Other attribute types that describe this attribute type.
     * See <cite>"Attribute characterization"</cite> in class Javadoc for more information.
     *
     * <div class="note"><b>Example:</b>
     * an attribute that carries a measurement (e.g. air temperature) may have another attribute
     * that holds the measurement accuracy.</div>
     *
     * The characteristics are enumerated in the {@linkplain Map#values() map values}.
     * The {@linkplain Map#keySet() map keys} are the {@code String} representations of
     * characteristics {@linkplain #getName() name}, for more convenient lookups.
     *
     * @return other attribute types that describe this attribute type, or an empty map if none.
     *
     * @see Attribute#characteristics()
     */
    @UML(identifier="characterizeBy", obligation=OPTIONAL, specification=ISO_19109)
    default Map<String,AttributeType<?>> characteristics() {
        return Collections.emptyMap();
    }
    /*
     * Note: ISO 19109 also defines the following member
     * for traversing the association in the opposite way:
     *
     *   - characterize the attribute type that is described by this attribute type.
     *
     * This member has been omitted for now.
     */

    /**
     * Creates a new attribute instance of this type initialized to the {@linkplain #getDefaultValue() default value}.
     *
     * @return a new attribute instance.
     * @throws UnsupportedOperationException if this type does not support new instance creation.
     */
    Attribute<V> newInstance() throws UnsupportedOperationException;
}
