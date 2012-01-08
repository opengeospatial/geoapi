/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    http://www.geoapi.org
 *
 *    This file is hereby placed into the Public Domain.
 *    This means anyone is free to do whatever they wish with this file.
 *
 *    The NetCDF wrappers are provided as code examples, in the hope to facilitate
 *    GeoAPI implementations backed by other libraries. Implementors can take this
 *    source code and use it for any purpose, commercial or non-commercial, copyrighted
 *    or open-source, with no legal obligation to acknowledge the borrowing/copying
 *    in any way.
 */
package org.opengis.wrapper.netcdf;

import java.util.Set;
import java.io.Serializable;
import java.net.URI;
import java.net.URISyntaxException;
import javax.measure.unit.Unit;

import ucar.unidata.util.Parameter;

import org.opengis.parameter.ParameterValue;
import org.opengis.parameter.ParameterDescriptor;
import org.opengis.parameter.InvalidParameterTypeException;
import org.opengis.parameter.InvalidParameterValueException;


/**
 * Wraps a NetCDF {@link Parameter} object in a GeoAPI {@link ParameterValue}. The NetCDF
 * {@code Parameter} class is both a parameter value and its own descriptor. Consequently
 * this adapter implements both the {@link ParameterValue} and {@link ParameterDescriptor}
 * interfaces.
 * <p>
 * NetCDF {@code Parameter} instances can store {@link String}, {@code double} and {@code double[]}
 * values. Those values can be obtained by the {@link #stringValue()}, {@link #doubleValue()} and
 * {@link #doubleValueList()} methods in the {@code ParameterValue} interface. All other
 * {@code fooValue()} methods delegate to one of the above-cited methods and convert the result.
 *
 * @param   <T> The type of parameter value.
 * @author  Martin Desruisseaux (Geomatys)
 * @version 3.1
 * @since   3.1
 */
public class NetcdfParameter<T> extends NetcdfIdentifiedObject
        implements org.opengis.example.parameter.Parameter<T>, Serializable, Cloneable
{
    /**
     * Serial number for cross-version compatibility.
     */
    private static final long serialVersionUID = -8942583683212743205L;

    /**
     * The NetCDF parameter, never {@code null}. A new NetCDF parameter instance will be
     * assigned to this field when a setter method is invoked.
     */
    private Parameter parameter;

    /**
     * Creates a new wrapper for the given NetCDF parameter. The {@linkplain #getValueClass()
     * value class} will be inferred from the given parameter.
     * <p>
     * This constructor can not have public access because we can not guarantee that the
     * {@code <T>} parameterized type is consistent with the given NetCDF parameter object,
     * since the NetCDF class is not parameterized. New instances of {@code NetcdfParameter}
     * shall be created only from methods having a {@code ParameterValue<?>} return type.
     *
     * @param parameter The parameter to wrap.
     */
    protected NetcdfParameter(final Parameter parameter) {
        this.parameter = parameter;
    }

    /**
     * Creates a new wrapper for the given NetCDF parameter. The {@linkplain #getValueClass()
     * value class} will be inferred from the given parameter.
     *
     * @param  parameter The parameter to wrap.
     * @return The GeoAPI parameter for the given NetCDF parameter.
     */
    @SuppressWarnings("rawtypes")
    public static NetcdfParameter<?> create(final Parameter parameter) {
        return new NetcdfParameter(parameter);
    }

    /**
     * Returns the NetCDF object wrapped by this adapter. Note that it may be different
     * than the NetCDF object given to the constructor if a setter method has been invoked.
     */
    @Override
    public Parameter delegate() {
        return parameter;
    }

    /**
     * Returns the parameter name. This method delegates to {@link Parameter#getName()}.
     *
     * @see Parameter#getName()
     */
    @Override
    public String getCode() {
        return parameter.getName();
    }

    /**
     * Returns the parameter descriptor, which is {@code this}.
     */
    @Override
    public ParameterDescriptor<T> getDescriptor() {
        return this;
    }

    /**
     * Returns a new value for this descriptor.
     */
    @Override
    public NetcdfParameter<T> createValue() {
        return new NetcdfParameter<T>(parameter);
    }

    /**
     * Returns the parameter type typically as a {@link String}, {@link Double} or {@code double[]}
     * class. The above-cited types are the ones supported by the NetCDF parameter implementation.
     */
    @Override
    @SuppressWarnings("unchecked")
    public Class<T> getValueClass() {
        return (Class<T>) getValueClass(parameter);
    }

    /**
     * Implementation of the public {@link #getValueClass()} method.
     *
     * @param parameter The NetCDF parameter from which to infer the value class.
     */
    private static Class<?> getValueClass(final Parameter parameter) {
        if (parameter.isString()) return String.class;
        if (parameter.getLength() == 1) return Double.class;
        return double[].class;
    }

    /**
     * Returns {@code null} since the {@link Parameter} class does not define a set of valid values.
     */
    @Override
    public Set<T> getValidValues() {
        return null;
    }

    /**
     * Returns the {@linkplain #getValue() current value} as the default value.
     * This is consistent with the {@link #createValue()} method, which creates
     * a copy of {@code NetcdfParameter} containing the same parameter value.
     */
    @Override
    public T getDefaultValue() {
        return getValue();
    }

    /**
     * Returns {@code null} since there is no descriptor defining a minimal value.
     */
    @Override
    public Comparable<T> getMinimumValue() {
        return null;
    }

    /**
     * Returns {@code null} since there is no descriptor defining a maximal value.
     */
    @Override
    public Comparable<T> getMaximumValue() {
        return null;
    }

    /**
     * Returns {@code null} since the parameter does not hold information about the unit of measure.
     */
    @Override
    public Unit<?> getUnit() {
        return null;
    }

    /**
     * Returns 0 since this parameter is assumed optional.
     */
    @Override
    public int getMinimumOccurs() {
        return 0;
    }

    /**
     * Returns 1 since this parameter is not a group of parameters.
     */
    @Override
    public int getMaximumOccurs() {
        return 1;
    }

    /**
     * Returns the exception to throw for a getter method invoked with a parameter of the wrong type.
     *
     * @param  parameter The NetCDF parameter.
     * @param  requested The requested type.
     * @return The exception to throw.
     */
    private static InvalidParameterTypeException invalidType(
            final Parameter parameter, final Class<?> requested)
    {
        final String name = parameter.getName();
        return new InvalidParameterTypeException("Can not convert '" + name +
                "' from type " + getValueClass(parameter).getSimpleName() + " to type " +
                requested.getSimpleName() + '.', name);
    }

    /**
     * Always throws an exception since this method doesn't know the parameter unit.
     *
     * @throws IllegalArgumentException Always thrown.
     */
    @Override
    public double doubleValue(final Unit<?> unit) throws IllegalArgumentException {
        throw new IllegalArgumentException("This parameter has no unit information.");
    }

    /**
     * Returns the {@linkplain Parameter#getNumericValue() parameter numeric value}
     * as a floating point value. This method first ensures that the parameter value
     * {@linkplain Parameter#isString() is not a string} and that the
     * {@linkplain Parameter#getLength() number of numeric values} is equals to 1,
     * then delegates to the {@link Parameter#getNumericValue()} method.
     *
     * @return The parameter numeric value.
     * @throws InvalidParameterTypeException If the parameter value is not convertible
     *         to the {@code double} type.
     *
     * @see Parameter#getNumericValue()
     */
    @Override
    public double doubleValue() throws InvalidParameterTypeException {
        final Parameter parameter = this.parameter;
        if (parameter.isString() || parameter.getLength() != 1) {
            throw invalidType(parameter, Double.class);
        }
        return parameter.getNumericValue();
    }

    /**
     * Returns the {@linkplain Parameter#getNumericValue() parameter numeric value} as
     * an integer value. This method invokes {@link #doubleValue()}, casts the result
     * to the {@code int} type and ensures that there is no loss of accuracy.
     *
     * @return The parameter numeric value.
     * @throws InvalidParameterTypeException If the parameter value is not convertible
     *         to the {@code int} type.
     *
     * @see Parameter#getNumericValue()
     */
    @Override
    public int intValue() throws InvalidParameterTypeException {
        final double value = doubleValue();
        final int result = (int) value;
        if (result != value) {
            throw invalidType(parameter, Integer.class);
        }
        return result;
    }

    /**
     * Returns the parameter value as a boolean value. This method invokes {@link #stringValue()},
     * then returns {@code true} or {@code false} if the string is equals, ignoring cases, to
     * {@code "true"} or {@code "false"} respectively.
     *
     * @return The parameter boolean value.
     * @throws InvalidParameterTypeException If the parameter value is not convertible
     *         to the {@code boolean} type.
     *
     * @see Parameter#getStringValue()
     */
    @Override
    public boolean booleanValue() throws InvalidParameterTypeException {
        final String value = stringValue().trim();
        if (value.equalsIgnoreCase("true"))  return true;
        if (value.equalsIgnoreCase("false")) return false;
        throw invalidType(parameter, Boolean.class);
    }

    /**
     * Returns the {@linkplain Parameter#getStringValue() parameter string value}.
     *
     * @return The parameter string value.
     * @throws InvalidParameterTypeException If the parameter value is not convertible
     *         to the {@link String} type.
     *
     * @see Parameter#getStringValue()
     */
    @Override
    public String stringValue() throws InvalidParameterTypeException {
        final Parameter parameter = this.parameter;
        if (!parameter.isString()) {
            throw invalidType(parameter, String.class);
        }
        return parameter.getStringValue();
    }

    /**
     * Always throws an exception since this method doesn't know the parameter unit.
     *
     * @throws IllegalArgumentException Always thrown.
     */
    @Override
    public double[] doubleValueList(final Unit<?> unit) throws IllegalArgumentException {
        throw new IllegalArgumentException("This parameter has no unit information.");
    }

    /**
     * Returns the {@linkplain Parameter#getNumericValues() parameter numeric values}
     * as an array of floating point values.
     *
     * @return The parameter numeric values.
     * @throws InvalidParameterTypeException If the parameter value is not convertible
     *         to the {@code double[]} type.
     *
     * @see Parameter#getNumericValues()
     */
    @Override
    public double[] doubleValueList() throws InvalidParameterTypeException {
        final Parameter parameter = this.parameter;
        if (parameter.isString()) {
            throw invalidType(parameter, double[].class);
        }
        return parameter.getNumericValues();
    }

    /**
     * Returns the {@linkplain Parameter#getNumericValues() parameter numeric values}
     * as an array of integer values. This method invokes {@link #doubleValueList()},
     * casts each value to the {@code int} type and ensures that there is no loss of
     * accuracy.
     *
     * @return The parameter numeric values.
     * @throws InvalidParameterTypeException If the parameter value is not convertible
     *         to the {@code int[]} type.
     *
     * @see Parameter#getNumericValues()
     */
    @Override
    public int[] intValueList() throws InvalidParameterTypeException {
        final double[] values = doubleValueList();
        final int[] result = new int[values.length];
        for (int i=0; i<result.length; i++) {
            result[i] = (int) values[i];
            if (result[i] != values[i]) {
                throw invalidType(parameter, int[].class);
            }
        }
        return result;
    }

    /**
     * Returns the {@linkplain Parameter#getStringValue() parameter string value} as a URI.
     * This method invokes {@link #stringValue()}, then parse the string as a URI.
     *
     * @return The parameter URI value.
     * @throws InvalidParameterTypeException If the parameter value is not convertible
     *         to the {@link URI} type.
     *
     * @see Parameter#getStringValue()
     */
    @Override
    public URI valueFile() throws InvalidParameterTypeException {
        try {
            return new URI(stringValue());
        } catch (URISyntaxException cause) {
            final InvalidParameterTypeException e = invalidType(parameter, URI.class);
            e.initCause(cause);
            throw e;
        }
    }

    /**
     * Returns the parameter value as an object. This method delegates to one of the
     * {@code fooValue()} method depending on the {@linkplain #getValueClass() value class},
     *
     * @return The parameter value as an object.
     */
    @Override
    public T getValue() {
        final Class<T> type = getValueClass();
        final Object value;
             if (type.isAssignableFrom(String  .class)) value = stringValue();
        else if (type.isAssignableFrom(Double  .class)) value = doubleValue();
        else if (type.isAssignableFrom(Integer .class)) value = intValue();
        else if (type.isAssignableFrom(double[].class)) value = doubleValueList();
        else if (type.isAssignableFrom(int[]   .class)) value = intValueList();
        else if (type.isAssignableFrom(Boolean .class)) value = booleanValue();
        else if (type.isAssignableFrom(URI     .class)) value = valueFile();
        else throw new InvalidParameterTypeException("Unknown type: " + type.getSimpleName(), parameter.getName());
        return type.cast(value);
    }

    /**
     * Returns the exception to throw for a setter method invoked with a parameter of the wrong type.
     *
     * @param  parameter The NetCDF parameter.
     * @param  value The value given by the user.
     * @return The exception to throw.
     */
    private static InvalidParameterValueException invalidValue(
            final Parameter parameter, final Object value)
    {
        final String name = parameter.getName();
        return new InvalidParameterValueException("Can not convert '" + name + "' from type " +
                value.getClass().getSimpleName() + " to type " + getValueClass(parameter).getSimpleName() + '.',
                name, value);
    }

    /**
     * Unsupported operation, since the {@link Parameter} class has no information about unit
     * of measurement.
     *
     * @throws IllegalArgumentException Always thrown.
     */
    @Override
    public void setValue(final double[] values, final Unit<?> unit) throws IllegalArgumentException {
        throw new IllegalArgumentException("This parameter has no unit information.");
    }

    /**
     * Unsupported operation, since the {@link Parameter} class has no information about unit
     * of measurement.
     *
     * @throws IllegalArgumentException Always thrown.
     */
    @Override
    public void setValue(final double value, final Unit<?> unit) throws IllegalArgumentException {
        throw new IllegalArgumentException("This parameter has no unit information.");
    }

    /**
     * Sets this parameter to the given value. This method creates a new {@link Parameter}
     * instance with the given value.
     *
     * @throws InvalidParameterValueException If this parameter can not accept the {@code double} type.
     *
     * @see Parameter#Parameter(String, double)
     */
    @Override
    public void setValue(final double value) throws InvalidParameterValueException {
        final Parameter parameter = this.parameter;
        if (parameter.isString() || parameter.getLength() != 1) {
            throw invalidValue(parameter, value);
        }
        this.parameter = new Parameter(getCode(), value);
    }

    /**
     * Sets this parameter to the given value. This method casts the given value to the
     * {@code double} type and delegates to {@link #setValue(double)}.
     *
     * @throws InvalidParameterValueException If this parameter can not accept the {@code int} type.
     *
     * @see Parameter#Parameter(String, double)
     */
    @Override
    public void setValue(final int value) throws InvalidParameterValueException {
        setValue((double) value);
    }

    /**
     * Sets this parameter to the given boolean. This method formats the given value to the
     * {@code "true"} or {@code "false"} string, then delegates to {@link #setValue(Object)}.
     *
     * @throws InvalidParameterValueException If this parameter can not accept the {@code boolean} type.
     *
     * @see Parameter#Parameter(String, String)
     */
    @Override
    public void setValue(final boolean value) throws InvalidParameterValueException {
        setValue(Boolean.toString(value));
    }

    /**
     * Sets this parameter to the given {@link String} or {@link Number}. If the given
     * value is a number, then this method delegates to {@link #setValue(double)}.
     *
     * @throws InvalidParameterValueException If this parameter can not accept the given value.
     *
     * @see Parameter#Parameter(String, String)
     */
    @Override
    public void setValue(final Object value) throws InvalidParameterValueException {
        if (value instanceof Number) {
            setValue(((Number) value).doubleValue());
        } else if ((value instanceof CharSequence) && parameter.isString()) {
            parameter = new Parameter(parameter.getName(), value.toString());
        } else {
            throw invalidValue(parameter, value);
        }
    }

    /**
     * Returns a copy of this parameter value.
     */
    @Override
    @SuppressWarnings("unchecked")
    public NetcdfParameter<T> clone() {
        try {
            return (NetcdfParameter<T>) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError(e); // Should never happen since we are cloneable.
        }
    }
}
