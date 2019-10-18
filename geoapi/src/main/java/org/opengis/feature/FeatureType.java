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

import java.util.Set;
import java.util.Collection;
import org.opengis.annotation.UML;
import org.opengis.annotation.Classifier;
import org.opengis.annotation.Stereotype;
import org.opengis.util.GenericName;

import static org.opengis.annotation.Obligation.*;
import static org.opengis.annotation.Specification.ISO_19109;


/**
 * Abstraction of a real-world phenomena.
 * A {@code FeatureType} instance describes the class of all {@link Feature} instances of that type.
 *
 * <div class="note"><b>Analogy with Java reflection</b><br>
 * compared to the Java language, {@code FeatureType} is equivalent to {@link Class} while
 * {@code Feature} instances are equivalent to {@link Object} instances of that class.</div>
 *
 * <h2>Naming</h2>
 * The feature type {@linkplain #getName() name} is mandatory and should be unique.
 * Names can be {@linkplain org.opengis.util.ScopedName} for avoiding name collision.
 *
 * <h2>Properties and inheritance</h2>
 * Each feature type can provide descriptions for the following {@linkplain #getProperties(boolean) properties}:
 *
 * <ul>
 *   <li>{@linkplain AttributeType           Attributes}</li>
 *   <li>{@linkplain FeatureAssociationRole  Associations to other features}</li>
 *   <li>{@linkplain Operation               Operations}</li>
 * </ul>
 *
 * In addition, a feature type can inherit the properties of one or more other feature types.
 * Properties defined in the sub-type can override properties of the same name defined in the
 * {@linkplain #getSuperTypes() super-types}, provided that values of the sub-type property are
 * assignable to the super-type property.
 *
 * <div class="note"><b>Analogy with Java language</b><br>
 * compared to the Java language, the above rule is similar to overriding a method with a more specific return
 * type (a.k.a. <cite>covariant return type</cite>). This is also similar to Java arrays, which are implicitly
 * <cite>covariant</cite> (i.e. {@code String[]} can be casted to {@code CharSequence[]}, which is safe for read
 * operations but not for write operations — the later may throw {@link ArrayStoreException}).</div>
 *
 * @author  Jody Garnett (Refractions Research)
 * @author  Justin Deoliveira (The Open Planning Project)
 * @author  Martin Desruisseaux (Geomatys)
 * @version 3.1
 * @since   3.1
 *
 * @see Feature
 */
@Classifier(Stereotype.METACLASS)
@UML(identifier="FeatureType", specification=ISO_19109)
public interface FeatureType extends IdentifiedType {
    /**
     * Returns the name of this feature type.
     * For {@code FeatureType}, the name is mandatory.
     * The feature name is often an instance of {@link org.opengis.util.TypeName}, but this is not mandatory.
     *
     * @return the feature type name.
     */
    @Override
    @UML(identifier="name", obligation=MANDATORY, specification=ISO_19109)
    GenericName getName();

    /**
     * Returns {@code true} if the feature type acts as an abstract super-type.
     * Abstract types can not be {@linkplain #newInstance() instantiated}.
     *
     * @return {@code true} if the feature type acts as an abstract super-type.
     */
    @UML(identifier="isAbstract", obligation=MANDATORY, specification=ISO_19109)
    boolean isAbstract();

    /**
     * Returns {@code true} if this feature type contains only attributes constrained to the [1 … 1] multiplicity,
     * or operations (no feature association).
     * Such feature types can be handled as a {@link org.opengis.util.RecordType} instances.
     *
     * @return {@code true} if this feature type contains only simple attributes or operations.
     */
    boolean isSimple();

    /**
     * Returns {@code true} if and only if an attribute, operation or association role of the given name exists
     * in this feature type or in one of its super-types. If this method returns {@code true}, then calls to
     * <code>{@linkplain #getProperty(String) getProperty}(name)</code> will not throw
     * {@link PropertyNotFoundException}.
     *
     * @param  name  the name of the property to search.
     * @return whether an attribute, operation or association role exists for the given name.
     */
//  boolean hasProperty(String name);

    /**
     * Returns the attribute, operation or association role for the given name.
     *
     * @param  name  the name of the property to search.
     * @return the property for the given name.
     * @throws PropertyNotFoundException if the given argument is not a property name of this feature type.
     *
     * @see Feature#getProperty(String)
     */
    PropertyType getProperty(String name) throws PropertyNotFoundException;

    /**
     * Returns any feature operation, any feature attribute type and any feature association role that
     * carries characteristics of a feature type. The returned collection will include the properties
     * inherited from the {@linkplain #getSuperTypes() super-types} only if {@code includeSuperTypes}
     * is {@code true}.
     *
     * @param  includeSuperTypes {@code true} for including the properties inherited from the super-types,
     *         or {@code false} for returning only the properties defined explicitely in this type.
     * @return Feature operation, attribute type and association role that carries characteristics of this
     *         feature type (not including parent types).
     */
    @UML(identifier="carrierOfCharacteristics", obligation=OPTIONAL, specification=ISO_19109)
    Collection<? extends PropertyType> getProperties(boolean includeSuperTypes);

    /**
     * Returns the direct parents of this feature type.
     *
     * <div class="note"><b>Analogy with Java reflection</b><br>
     * if we compare {@code FeatureType} to {@link Class} in the Java language, then this method is equivalent
     * to {@link Class#getSuperclass()} except that feature types allow multi-inheritance.</div>
     *
     * @return the parents of this feature type, or an empty set if none.
     */
    @UML(identifier="superType", obligation=OPTIONAL, specification=ISO_19109)
    Set<? extends FeatureType> getSuperTypes();

    /**
     * Returns {@code true} if this type is same or a super-type of the given type.
     * Implementations should ensure that the following constraints are met:
     *
     * <ul>
     *   <li>If <var>A</var> is assignable from <var>B</var> and <var>B</var> is assignable from <var>C</var>,
     *       then <var>A</var> is assignable from <var>C</var>.</li>
     * </ul>
     *
     * <div class="note"><b>Analogy with Java reflection</b><br>
     * if we compare {@code FeatureType} to {@link Class} in the Java language, then this method is equivalent
     * to {@link Class#isAssignableFrom(Class)}.</div>
     *
     * @param  type  the type to be checked.
     * @return {@code true} if instances of the given type can be assigned to association of this type.
     */
    boolean isAssignableFrom(FeatureType type);

    /**
     * Creates a new feature instance of this type.
     *
     * <div class="note"><b>Analogy with Java reflection</b><br>
     * if we compare {@code FeatureType} to {@link Class} and {@code Feature} to {@link Object} in the Java language,
     * then this method is equivalent to {@link Class#newInstance()}.</div>
     *
     * @return a new feature instance.
     * @throws FeatureInstantiationException if this feature type {@linkplain #isAbstract() is abstract}
     *         or can not be instantiated for some other reason.
     * @throws UnsupportedOperationException if this type does not support new instance creation.
     */
    Feature newInstance() throws FeatureInstantiationException, UnsupportedOperationException;
}
