/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    http://www.geoapi.org
 *
 *    Copyright (C) 2004-2011 Open Geospatial Consortium, Inc.
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
import org.opengis.util.CodeList;
import org.opengis.annotation.UML;

import static org.opengis.annotation.Obligation.*;
import static org.opengis.annotation.Specification.*;


/**
 * The definition of a parameter used by an operation method. Most parameter values are
 * numeric, but other types of parameter values are possible.
 *
 * @param <T> The type of parameter values.
 *
 * @departure rename
 *   GeoAPI uses a name which contains the "<code>Descriptor</code>" word for consistency with other
 *   libraries in Java (e.g. <code>ParameterListDescriptor</code> in Java Advanced Imaging).
 *
 * @author  Martin Desruisseaux (IRD)
 * @author  Jody Garnett (Refractions Research)
 * @version 3.0.1
 * @since   2.0
 *
 * @see ParameterValue
 * @see ParameterDescriptorGroup
 */
@UML(identifier="CC_OperationParameter", specification=ISO_19111)
public interface ParameterDescriptor<T> extends GeneralParameterDescriptor {
    /**
     * Creates a new instance of {@linkplain ParameterValue parameter value} initialized with the
     * {@linkplain #getDefaultValue default value}. The {@linkplain ParameterValue#getDescriptor
     * parameter value descriptor} for the created parameter value will be {@code this} object.
     *
     * @departure extension
     *   This method is not part of the ISO specification. It is provided in GeoAPI as a kind of
     *   factory method.
     */
    ParameterValue<T> createValue();

    /**
     * Returns the class that describe the type of the parameter.
     *
     * @return The type of parameter values.
     */
    @UML(identifier="GC_ParameterInfo.type", obligation=MANDATORY, specification=OGC_01004)
    Class<T> getValueClass();

    /**
     * Returns the set of allowed values when these are restricted to some finite set or returns
     * {@code null} otherwise. The returned set usually contains {@linkplain CodeList code list}
     * or enumeration elements.
     *
     * @return A finite set of valid values (usually from a {@linkplain CodeList code list}),
     *         or {@code null} if it doesn't apply.
     *
     * @departure extension
     *   This method is not part of ISO specification. It is provided as a complement of information.
     */
    Set<T> getValidValues();

    /**
     * Returns the default value for the parameter. The return type can be any type
     * including a {@link Number} or a {@link String}. If there is no default value,
     * then this method returns {@code null}.
     *
     * @return The default value, or {@code null} in none.
     */
    @UML(identifier="GC_ParameterInfo.defaultValue", obligation=OPTIONAL, specification=OGC_01004)
    T getDefaultValue();

    /**
     * Returns the minimum parameter value.
     *
     * If there is no minimum value, or if minimum
     * value is inappropriate for the {@linkplain #getValueClass parameter type}, then
     * this method returns {@code null}.
     * <p>
     * When the getValueClass() is an array or Collection getMinimumValue
     * may be used to constrain the contained elements.
     * </p>
     * @return The minimum parameter value (often an instance of {@link Double}), or {@code null}.
     */
    @UML(identifier="GC_ParameterInfo.minimumValue", obligation=OPTIONAL, specification=OGC_01004)
    Comparable<T> getMinimumValue();

    /**
     * Returns the maximum parameter value.
     *
     * If there is no maximum value, or if maximum
     * value is inappropriate for the {@linkplain #getValueClass parameter type}, then
     * this method returns {@code null}.
     * <p>
     * When the getValueClass() is an array or Collection getMaximumValue
     * may be used to constraint the contained elements.
     *
     * @return The minimum parameter value (often an instance of {@link Double}), or {@code null}.
     */
    @UML(identifier="GC_ParameterInfo.maximumValue", obligation=OPTIONAL, specification=OGC_01004)
    Comparable<T> getMaximumValue();

    /**
     * Returns the unit for
     * {@linkplain #getDefaultValue default},
     * {@linkplain #getMinimumValue minimum} and
     * {@linkplain #getMaximumValue maximum} values.
     * This attribute apply only if the values is of numeric type (usually an instance
     * of {@link Double}).
     *
     * @return The unit for numeric value, or {@code null} if it doesn't apply to the value type.
     *
     * @departure extension
     *   This method is not part of ISO specification. It is provided as a complement of information.
     */
    Unit<?> getUnit();
}
