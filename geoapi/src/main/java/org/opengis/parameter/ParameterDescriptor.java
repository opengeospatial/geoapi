/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    http://www.geoapi.org
 *
 *    Copyright (C) 2004-2021 Open Geospatial Consortium, Inc.
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
package org.opengis.parameter;

import java.util.Set;
import javax.measure.Unit;
import org.opengis.util.TypeName;
import org.opengis.util.CodeList;
import org.opengis.annotation.UML;

import static org.opengis.annotation.Obligation.*;
import static org.opengis.annotation.Specification.*;


/**
 * The definition of a parameter used by an operation method.
 * Most parameter values are numeric, but other types of parameter values are possible.
 * A parameter descriptor contains the following properties:
 * <ul>
 *   <li>The parameter {@linkplain #getName() name}.</li>
 *   <li>The {@linkplain #getValueClass() class of values}. This is usually {@link Double}, {@code double[]},
 *       {@link Integer}, {@code int[]}, {@link Boolean}, {@link String} or {@link java.net.URI}.</li>
 *   <li>Whether this parameter is optional or mandatory. This is specified by the {@linkplain #getMinimumOccurs()
 *       minimum occurrences} number, which can be 0 or 1 respectively.</li>
 *   <li>The {@linkplain #getDefaultValue() default value} and its {@linkplain #getUnit() unit of measurement}.</li>
 *   <li>The domain of values, as a {@linkplain #getMinimumValue() minimum value}, {@linkplain #getMaximumValue()
 *       maximum value} or an enumeration of {@linkplain #getValidValues() valid values}.</li>
 * </ul>
 *
 * @param  <T>  the type of parameter values.
 *
 * @departure rename
 *   GeoAPI uses a name which contains the "{@code Descriptor}" word for consistency with other
 *   libraries in Java (e.g. {@code ParameterListDescriptor} in Java Advanced Imaging).
 *
 * @author  Martin Desruisseaux (IRD)
 * @author  Jody Garnett (Refractions Research)
 * @version 3.1
 * @since   2.0
 *
 * @see ParameterValue
 * @see ParameterDescriptorGroup
 */
@UML(identifier="CC_OperationParameter", specification=ISO_19111)
public interface ParameterDescriptor<T> extends GeneralParameterDescriptor {
    /**
     * Returns the name that describes the type of parameter values.
     * This is closely related to the {@link Class} returned by {@link #getValueClass()}:
     *
     * <ul>
     *   <li>If the value class is a collection ({@link java.util.Map}, {@link Set}, {@link java.util.List} or array),
     *       then this method returns the type of <em>elements</em> in the collection.</li>
     *   <li>Otherwise this method returns the value class using the mapping documented in {@link TypeName} javadoc
     *       or using an implementation-dependent mapping.</li>
     * </ul>
     *
     * @return value type of the parameter.
     *
     * @since 3.1
     */
    @UML(identifier="DQM_Parameter.valueType", obligation=MANDATORY, specification=ISO_19157)
    TypeName getValueType();

    /**
     * Returns the class that describes the type of parameter values.
     * This is usually (but not restricted to) {@link Boolean}, {@link Integer}, {@link Double}, {@link String}
     * or {@link java.net.URI} when the parameter contains a single value.
     * If the parameter can contain multiple values, then the class may be {@code int[]}, {@code double[]},
     * {@link java.util.List}, {@link Set} or {@link java.util.Map}.
     *
     * @return the type of parameter values.
     */
    @UML(identifier="GC_ParameterInfo.type", obligation=MANDATORY, specification=OGC_01004)
    Class<T> getValueClass();

    /**
     * Returns the set of allowed values when these are restricted to some finite set or returns
     * {@code null} otherwise. The returned set usually contains {@linkplain CodeList code list}
     * or enumeration elements.
     *
     * <div class="note"><b>Note:</b>
     * it is not necessary to provide this property when all values from the code list or enumeration are allowed.
     * </div>
     *
     * @return a finite set of valid values (usually from a {@code CodeList}),
     *         or {@code null} if it does not apply or if there is no restriction.
     *
     * @departure extension
     *   This method is not part of ISO specification. It is provided as a complement of information.
     */
    default Set<T> getValidValues() {
        return null;                    // Really null, not an empty collection, because we mean "no restriction".
    }

    /**
     * Returns the minimum parameter value.
     * If there is no minimum value, or if the minimum value is inappropriate for the
     * {@linkplain #getValueClass() value class}, then this method returns {@code null}.
     *
     * @return the minimum parameter value (often an instance of {@link Double}), or {@code null}.
     *
     * @see #getMaximumValue()
     * @see #getUnit()
     */
    @UML(identifier="GC_ParameterInfo.minimumValue", obligation=OPTIONAL, specification=OGC_01004)
    default Comparable<T> getMinimumValue() {
        return null;
    }

    /**
     * Returns the maximum parameter value.
     * If there is no maximum value, or if the maximum value is inappropriate for the
     * {@linkplain #getValueClass() value class}, then this method returns {@code null}.
     *
     * @return the maximum parameter value (often an instance of {@link Double}), or {@code null}.
     *
     * @see #getMinimumValue()
     * @see #getUnit()
     */
    @UML(identifier="GC_ParameterInfo.maximumValue", obligation=OPTIONAL, specification=OGC_01004)
    default Comparable<T> getMaximumValue() {
        return null;
    }

    /**
     * Returns the default value for the parameter.
     * If there is no default value, then this method returns {@code null}.
     *
     * @return the default value, or {@code null} in none.
     *
     * @see #getUnit()
     */
    @UML(identifier="GC_ParameterInfo.defaultValue", obligation=OPTIONAL, specification=OGC_01004)
    default T getDefaultValue() {
        return null;
    }

    /**
     * Returns the unit of measurement for the minimum, maximum and default values.
     * This attribute applies only if the value is of numeric type
     * (usually an instance of {@link Double}).
     *
     * @return the unit for numeric value, or {@code null} if it does not apply to the value type.
     *
     * @departure extension
     *   This method is not part of ISO specification. It is provided as a complement of information.
     *
     * @see #getMinimumValue()
     * @see #getMaximumValue()
     * @see #getDefaultValue()
     */
    default Unit<?> getUnit() {
        return null;
    }

    /**
     * Creates a new instance of parameter value initialized with the default value.
     * While not a requirement, the {@linkplain ParameterValue#getDescriptor() parameter value descriptor}
     * for the created parameter value will typically be {@code this} descriptor instance.
     *
     * @return a new parameter value initialized to the default value.
     *
     * @departure extension
     *   This method is not part of the ISO specification.
     *   It is provided in GeoAPI as a kind of factory method.
     *
     * @see #getDefaultValue()
     */
    @Override
    ParameterValue<T> createValue();
}
