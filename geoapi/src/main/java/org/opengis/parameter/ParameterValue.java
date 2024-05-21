/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    Copyright © 2003-2024 Open Geospatial Consortium, Inc.
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
package org.opengis.parameter;

import java.net.URI;
import javax.measure.Unit;
import javax.measure.UnitConverter;
import javax.measure.IncommensurableException;
import org.opengis.annotation.UML;
import org.opengis.annotation.Classifier;
import org.opengis.annotation.Stereotype;
import org.opengis.metadata.citation.Citation;
import org.opengis.metadata.Identifier;
import org.opengis.geometry.Geometry;

import static org.opengis.annotation.Obligation.*;
import static org.opengis.annotation.Specification.*;


/**
 * A single parameter value used by an operation method. Most CRS parameter values are numeric and can be obtained
 * by the {@link #intValue()} or {@link #doubleValue()} methods. But other types of parameter values are possible
 * and can be handled by the more generic {@link #getValue()} and {@link #setValue(Object)} methods.
 *
 * <p>All {@code xxxValue()} methods in this interface are convenience methods converting the value from {@code Object}
 * to some commonly used types. Those types are specified in ISO 19111 as a union of attributes, listed below with the
 * corresponding getter and setter methods:</p>
 *
 * <table class="ogc">
 * <caption>Common value types</caption>
 *   <tr><th>ISO attribute</th>     <th>Java type</th>        <th>Getter method</th>                  <th>Setter method</th></tr>
 *   <tr><td></td>                  <td>{@link Object}</td>   <td>{@link #getValue()}</td>            <td>{@link #setValue(Object)}</td></tr>
 *   <tr><td>stringValue</td>       <td>{@link String}</td>   <td>{@link #stringValue()}</td>         <td>{@link #setValue(Object)}</td></tr>
 *   <tr><td>value</td>             <td>{@code double}</td>   <td>{@link #doubleValue()}</td>         <td>{@link #setValue(double)}</td></tr>
 *   <tr><td></td>                  <td>{@code double}</td>   <td>{@link #doubleValue(Unit)}</td>     <td>{@link #setValue(double, Unit)}</td></tr>
 *   <tr><td>valueList</td>         <td>{@code double[]}</td> <td>{@link #doubleValueList()}</td>     <td>{@link #setValue(Object)}</td></tr>
 *   <tr><td></td>                  <td>{@code double[]}</td> <td>{@link #doubleValueList(Unit)}</td> <td>{@link #setValue(double[], Unit)}</td></tr>
 *   <tr><td>integerValue</td>      <td>{@code int}</td>      <td>{@link #intValue()}</td>            <td>{@link #setValue(int)}</td></tr>
 *   <tr><td>integerValueList</td>  <td>{@code int[]}</td>    <td>{@link #intValueList()}</td>        <td>{@link #setValue(Object)}</td></tr>
 *   <tr><td>booleanValue</td>      <td>{@code boolean}</td>  <td>{@link #booleanValue()}</td>        <td>{@link #setValue(boolean)}</td></tr>
 *   <tr><td>valueFile</td>         <td>{@link URI}</td>      <td>{@link #valueFile()}</td>           <td>{@link #setValue(Object)}</td></tr>
 *   <tr><td>valueFileCitation</td> <td>{@link Citation}</td> <td>{@link #getValue()}</td>            <td>{@link #setValue(Object)}</td></tr>
 *   <tr><td>geographicObject</td>  <td>{@link Geometry}</td> <td>{@link #getValue()}</td>            <td>{@link #setValue(Object)}</td></tr>
 * </table>
 *
 * The type and constraints on parameter values are given by the {@linkplain #getDescriptor() descriptor},
 * Instances of {@code ParameterValue} are created by the {@link ParameterDescriptor#createValue()} method.
 *
 * @departure integration
 *   This interface merges the {@code OperationParameterValue} interface and {@code ParameterValue} union.
 *   The {@code valueFileCitation} and {@code geographicObject} properties were omitted because those more
 *   complex objects can be specified by setting the {@code <T>} parameterized type to
 *   {@link Citation} and {@link Geometry} respectively.
 *
 * @param  <T>  the type of parameter values.
 *
 * @author  OGC Topic 2 (for abstract model and documentation)
 * @author  Martin Desruisseaux (IRD, Geomatys)
 * @author  Jody Garnett (Refractions Research)
 * @version 3.1
 * @since   1.0
 *
 * @see ParameterDescriptor
 * @see ParameterValueGroup
 */
@Classifier(Stereotype.UNION)
@UML(identifier="OperationParameterValue", specification=ISO_19111)
public interface ParameterValue<T> extends GeneralParameterValue {
    /**
     * Returns the abstract definition of this parameter value.
     *
     * @return the abstract definition of this parameter value.
     */
    @Override
    @UML(identifier="parameter", obligation=MANDATORY, specification=ISO_19111)
    ParameterDescriptor<T> getDescriptor();

    /**
     * Returns the name of this parameter, for error messages only.
     * All parameter shall have a name since it is a mandatory property,
     * but if this parameter is nevertheless unnamed, an arbitrary value is returned.
     *
     * @return name of this parameter for error message.
     */
    private String getName() {
        ParameterDescriptor<?> descriptor = getDescriptor();
        if (descriptor != null) {
            Identifier name = descriptor.getName();
            if (name != null) {
                String code = name.getCode();
                if (code != null) {
                    return code;
                }
            }
        }
        return "unnamed";
    }

    /**
     * Returns a message saying that the value of this parameter cannot be converted to the specified unit.
     *
     * @param  unit  the illegal unit of measurement.
     * @return a message saying that this parameter value cannot be converted.
     */
    private String cannotConvert(final Unit<?> unit) {
        return "The “" + getName() + "” parameter value cannot be converted to “" + unit + "” units.";
    }

    /**
     * Returns the unit of measure of the parameter value.
     * If the parameter value has no unit (for example because it is a {@link String} type),
     * then this method returns {@code null}. Note that "no unit" doesn't means
     * "dimensionless".
     *
     * @return the unit of measure of the parameter value.
     *
     * @see #doubleValue()
     * @see #doubleValueList()
     * @see #getValue()
     */
    Unit<?> getUnit();

    /**
     * Returns the numeric value of this parameter in the specified unit of measure.
     * This convenience method applies unit conversion on the fly as needed.
     * The default implementation invokes {@link #getValue()} and {@link #getUnit()},
     * then tries to cast and convert the value.
     *
     * @param  unit  the unit of measure for the value to be returned.
     * @return the numeric value represented by this parameter after conversion to type
     *         {@code double} and conversion to {@code unit}.
     * @throws IllegalArgumentException if the specified unit is invalid for this parameter.
     * @throws InvalidParameterTypeException if the value is not a numeric type.
     * @throws IllegalStateException if the value cannot be returned for another reason.
     *
     * @see #getUnit()
     * @see #setValue(double,Unit)
     * @see #doubleValueList(Unit)
     */
    default double doubleValue(final Unit<?> unit) throws IllegalArgumentException, IllegalStateException {
        final T value = getValue();
        final Number number;
        try {
            number = (Number) value;
        } catch (ClassCastException cause) {
            throw new InvalidParameterTypeException(cause, getName());
        }
        final Unit<?> source = getUnit();
        try {
            return source.getConverterToAny(unit).convert(number).doubleValue();
        } catch (IncommensurableException cause) {
            throw new IllegalArgumentException(cannotConvert(unit), cause);
        }
    }

    /**
     * Returns the numeric value of this operation parameter.
     * The unit of measurement is specified by {@link #getUnit()}.
     * The default implementation invokes {@link #getValue()}, then tries to cast the value.
     *
     * @return the numeric value represented by this parameter after conversion to type {@code double}.
     * @throws InvalidParameterTypeException if the value is not a numeric type.
     * @throws IllegalStateException if the value cannot be returned for another reason.
     * @unitof Measure
     *
     * @departure rename
     *   Renamed the method from "{@code value}" to "{@code doubleValue}" for consistency
     *   with {@code Number.doubleValue()} and the other "{@code *Value}" methods defined
     *   in this interface.
     *
     * @see #getUnit()
     * @see #setValue(double)
     * @see #doubleValueList()
     */
    @UML(identifier="ParameterValue.value", obligation=CONDITIONAL, specification=ISO_19111)
    default double doubleValue() throws IllegalStateException {
        final T value = getValue();
        try {
            return ((Number) value).doubleValue();
        } catch (NullPointerException | ClassCastException cause) {
            throw new InvalidParameterTypeException(cause, getName());
        }
    }

    /**
     * Returns the integer value of this parameter, usually used for a count.
     * An integer value does not have an associated unit of measure.
     * The default implementation invokes {@link #getValue()}, then tries to cast the value.
     *
     * @return the numeric value represented by this parameter after conversion to type {@code int}.
     * @throws InvalidParameterTypeException if the value is not an integer type.
     * @throws IllegalStateException if the value cannot be returned for another reason.
     *
     * @departure rename
     *   Renamed the method from "{@code integerValue}" to "{@code intValue}" for
     *   consistency with {@code Number.intValue()} and the {@code int} Java primitive type.
     *
     * @see #setValue(int)
     * @see #intValueList()
     */
    @UML(identifier="ParameterValue.integerValue", obligation=CONDITIONAL, specification=ISO_19111)
    default int intValue() throws IllegalStateException {
        final T value = getValue();
        try {
            return Math.toIntExact(((Number) value).longValue());
        } catch (NullPointerException | ClassCastException | ArithmeticException cause) {
            throw new InvalidParameterTypeException(cause, getName());
        }
    }

    /**
     * Returns the Boolean value of this parameter.
     * A Boolean value does not have an associated unit of measure.
     * The default implementation invokes {@link #getValue()}, then tries to cast the value.
     *
     * @return the Boolean value represented by this parameter.
     * @throws InvalidParameterTypeException if the value is not a Boolean type.
     * @throws IllegalStateException if the value cannot be returned for another reason.
     *
     * @see #setValue(boolean)
     */
    @UML(identifier="ParameterValue.booleanValue", obligation=CONDITIONAL, specification=ISO_19111)
    default boolean booleanValue() throws IllegalStateException {
        final T value = getValue();
        try {
            return (Boolean) value;
        } catch (NullPointerException | ClassCastException cause) {
            throw new InvalidParameterTypeException(cause, getName());
        }
    }

    /**
     * Returns the string value of this parameter.
     * A string value does not have an associated unit of measure.
     * The default implementation invokes {@link #getValue()}, then tries to cast the value.
     *
     * @return the string value represented by this parameter.
     * @throws InvalidParameterTypeException if the value is not a string.
     * @throws IllegalStateException if the value cannot be returned for another reason.
     *
     * @see #getValue()
     * @see #setValue(Object)
     */
    @UML(identifier="ParameterValue.stringValue", obligation=CONDITIONAL, specification=ISO_19111)
    default String stringValue() throws IllegalStateException {
        final T value = getValue();
        try {
            return (String) value;
        } catch (ClassCastException cause) {
            throw new InvalidParameterTypeException(cause, getName());
        }
    }

    /**
     * Returns an ordered sequence of numeric values in the specified unit of measure.
     * This convenience method applies unit conversions on the fly as needed.
     * The default implementation invokes {@link #doubleValueList()}, then tries to convert the values.
     *
     * @param  unit  the unit of measure for the value to be returned.
     * @return the sequence of values represented by this parameter after conversion to type
     *         {@code double} and conversion to {@code unit}.
     * @throws IllegalArgumentException if the specified unit is invalid for this parameter.
     * @throws InvalidParameterTypeException if the value is not an array of {@code double}s.
     * @throws IllegalStateException if the value cannot be returned for another reason.
     *
     * @see #getUnit()
     * @see #setValue(double[],Unit)
     * @see #doubleValue(Unit)
     */
    default double[] doubleValueList(Unit<?> unit) throws IllegalArgumentException, IllegalStateException {
        double[] values = doubleValueList();
        final Unit<?> source = getUnit();
        try {
            final UnitConverter c = source.getConverterToAny(unit);
            if (!c.isIdentity()) {
                values = values.clone();
                for (int i=0; i < values.length; i++) {
                    values[i] = c.convert(values[i]);
                }
            }
        } catch (IncommensurableException cause) {
            throw new InvalidParameterTypeException(cause, getName());
        }
        return values;
    }

    /**
     * Returns an ordered sequence of two or more numeric values of this parameter,
     * where each value has the same associated unit of measure.
     * The default implementation invokes {@link #getValue()}, then tries to cast the value.
     *
     * @return the sequence of values represented by this parameter.
     * @throws InvalidParameterTypeException if the value is not an array of {@code double}s.
     * @throws IllegalStateException if the value cannot be returned for another reason.
     * @unitof Measure
     *
     * @departure rename
     *   Renamed the method from "{@code valueList}" to "{@code doubleValueList}" both for
     *   consistency with {@code doubleValue()} and also because, like {@code doubleValue()},
     *   this method returns an array of {@code double} values rather than a {@code Measure}
     *   object.
     *
     * @see #getUnit()
     * @see #setValue(Object)
     * @see #doubleValue()
     */
    @UML(identifier="ParameterValue.valueList", obligation=CONDITIONAL, specification=ISO_19111)
    default double[] doubleValueList() throws IllegalStateException {
        final T value = getValue();
        try {
            return (double[]) value;
        } catch (ClassCastException cause) {
            throw new InvalidParameterTypeException(cause, getName());
        }
    }

    /**
     * Returns an ordered sequence of two or more integer values of this parameter, usually used for counts.
     * These integer values do not have an associated unit of measure.
     * The default implementation invokes {@link #getValue()}, then tries to cast the value.
     *
     * @return the sequence of values represented by this parameter.
     * @throws InvalidParameterTypeException if the value is not an array of {@code int}s.
     * @throws IllegalStateException if the value cannot be returned for another reason.
     *
     * @departure rename
     *   Renamed the attribute from "{@code integerValueList}" to "{@code intValueList}"
     *   for consistency with {@code intValue()}.
     *
     * @see #setValue(Object)
     * @see #intValue()
     */
    @UML(identifier="ParameterValue.integerValueList", obligation=CONDITIONAL, specification=ISO_19111)
    default int[] intValueList() throws IllegalStateException {
        final T value = getValue();
        try {
            return (int[]) value;
        } catch (ClassCastException cause) {
            throw new InvalidParameterTypeException(cause, getName());
        }
    }

    /**
     * Returns a reference to a file or a part of a file containing one or more parameter values.
     * When referencing a part of a file, that file must contain multiple identified parts, such
     * as an XML encoded document. Furthermore, the referenced file or part of a file can reference
     * another part of the same or different files, as allowed in XML documents.
     *
     * <p>The default implementation invokes {@link #getValue()}, then tries to cast the value.</p>
     *
     * @return the reference to a file containing parameter values.
     * @throws InvalidParameterTypeException if the value is not a reference to a file or a URI.
     * @throws IllegalStateException if the value cannot be returned for another reason.
     *
     * @see #getValue()
     * @see #setValue(Object)
     */
    @UML(identifier="ParameterValue.valueFile", obligation=CONDITIONAL, specification=ISO_19111)
    default URI valueFile() throws IllegalStateException {
        final T value = getValue();
        try {
            return (URI) value;
        } catch (ClassCastException cause) {
            throw new InvalidParameterTypeException(cause, getName());
        }
    }

    /**
     * Returns the parameter value as an object. The object type is typically (but not restricted to)
     * {@link Double}, {@link Integer}, {@link Boolean}, {@link String}, {@link URI}, {@code double[]} or {@code int[]}.
     * If no value has been set, then this method returns the
     * {@linkplain ParameterDescriptor#getDefaultValue() default value} (which may be null).
     *
     * @return the parameter value as an object, or {@code null} if no value has been set and
     *         there is no default value.
     *
     * @see #setValue(Object)
     */
    T getValue();

    /**
     * Sets the parameter value as an array of floating point and their associated unit.
     *
     * @param  values  the parameter values.
     * @param  unit    the unit for the specified value.
     * @throws InvalidParameterValueException if the floating point type is inappropriate for this
     *         parameter, or if the value is illegal for some other reason (for example a value out
     *         of range).
     */
    void setValue(double[] values, Unit<?> unit) throws InvalidParameterValueException;

    /**
     * Sets the parameter value as a floating point and its associated unit.
     *
     * @param  value  the parameter value.
     * @param  unit   the unit for the specified values.
     * @throws InvalidParameterValueException if the floating point type is inappropriate for this
     *         parameter, or if the value is illegal for some other reason (for example a value out
     *         of range).
     *
     * @see #setValue(double)
     * @see #doubleValue(Unit)
     */
    void setValue(double value, Unit<?> unit) throws InvalidParameterValueException;

    /**
     * Sets the parameter value as a floating point.
     * The default implementation delegates to {@link #setValue(Object)}.
     *
     * @param  value  the parameter value.
     * @throws InvalidParameterValueException if the floating point type is inappropriate for this
     *         parameter, or if the value is illegal for some other reason (for example a value out
     *         of range).
     *
     * @see #setValue(double,Unit)
     * @see #doubleValue()
     */
    default void setValue(double value) throws InvalidParameterValueException {
        setValue((Object) value);
    }

    /**
     * Sets the parameter value as an integer.
     * The default implementation delegates to {@link #setValue(Object)}.
     *
     * @param  value  the parameter value.
     * @throws InvalidParameterValueException if the integer type is inappropriate for this parameter,
     *         or if the value is illegal for some other reason (for example a value out of range).
     *
     * @see #intValue()
     */
    default void setValue(int value) throws InvalidParameterValueException {
        setValue((Object) value);
    }

    /**
     * Sets the parameter value as a Boolean.
     * The default implementation delegates to {@link #setValue(Object)}.
     *
     * @param  value  the parameter value.
     * @throws InvalidParameterValueException if the Boolean type is inappropriate for this parameter.
     *
     * @see #booleanValue()
     */
    default void setValue(boolean value) throws InvalidParameterValueException {
        setValue((Object) value);
    }

    /**
     * Sets the parameter value as an object. The object type is typically (but not restricted to)
     * {@link Double}, {@link Integer}, {@link Boolean}, {@link String}, {@link URI}, {@code double[]} or {@code int[]}.
     *
     * <p>The argument is not restricted to the parameterized type {@code T} because the type
     * is typically unknown (as in <code>group.{@linkplain ParameterValueGroup#parameter(String)
     * parameter}("<var>name</var>").setValue(<var>value</var>)</code>) and
     * because some implementations may choose to convert a wider range of types.</p>
     *
     * @param  value  the parameter value.
     * @throws InvalidParameterValueException if the type of {@code value} is inappropriate
     *         for this parameter, or if the value is illegal for some other reason (for example
     *         the value is numeric and out of range).
     *
     * @see #getValue()
     */
    void setValue(Object value) throws InvalidParameterValueException;

    /**
     * Returns a copy of this parameter value.
     *
     * @return a copy of this parameter value.
     */
    @Override
    ParameterValue<T> clone();
}
