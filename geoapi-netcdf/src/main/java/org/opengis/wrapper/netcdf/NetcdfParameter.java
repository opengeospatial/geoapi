/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    http://www.geoapi.org
 *
 *    This file is hereby placed into the Public Domain.
 *    This means anyone is free to do whatever they wish with this file.
 *
 *    The netCDF wrappers are provided as code examples, in the hope to facilitate
 *    GeoAPI implementations backed by other libraries. Implementers can take this
 *    source code and use it for any purpose, commercial or non-commercial, copyrighted
 *    or open-source, with no legal obligation to acknowledge the borrowing/copying
 *    in any way.
 */
package org.opengis.wrapper.netcdf;

import java.util.Collection;
import java.util.Collections;
import java.util.Objects;
import java.net.URI;
import java.net.URISyntaxException;
import javax.measure.Unit;

import ucar.unidata.util.Parameter;

import org.opengis.util.GenericName;
import org.opengis.util.InternationalString;
import org.opengis.parameter.ParameterValue;
import org.opengis.parameter.ParameterDescriptor;
import org.opengis.parameter.ParameterDirection;
import org.opengis.parameter.InvalidParameterTypeException;
import org.opengis.parameter.InvalidParameterValueException;


/**
 * A {@link ParameterValue} implementation backed by a netCDF {@link Parameter} object.
 * The netCDF {@code Parameter} class is both a parameter value and its own descriptor. Consequently
 * this adapter implements both the {@link ParameterValue} and {@link ParameterDescriptor} interfaces.
 *
 * <p>NetCDF {@code Parameter} instances can store the following types:</p>
 * <blockquote><table border="1">
 *   <caption>Allowed parameter types</caption>
 *   <tr><th>Value type</th>       <th>Getter method</th>              <th>Setter method</th></tr>
 *   <tr><td>{@link String}</td>   <td>{@link #stringValue()}</td>     <td>{@link #setValue(Object)}</td></tr>
 *   <tr><td>{@code double}</td>   <td>{@link #doubleValue()}</td>     <td>{@link #setValue(double)}</td></tr>
 *   <tr><td>{@code double[]}</td> <td>{@link #doubleValueList()}</td> <td>{@link #setValue(Object)}</td></tr>
 * </table></blockquote>
 *
 * All other {@code fooValue()} methods delegate to one of the above-cited getter methods and
 * convert the result.
 *
 * @param   <T> The type of parameter value.
 * @author  Martin Desruisseaux (Geomatys)
 * @version 3.1
 * @since   3.1
 */
public class NetcdfParameter<T> extends NetcdfIdentifiedObject
        implements ParameterValue<T>, ParameterDescriptor<T>, Cloneable
{
    /**
     * Serial number for cross-version compatibility.
     */
    private static final long serialVersionUID = -9215966394791752911L;

    /**
     * The netCDF parameter, never {@code null}. A new netCDF parameter instance will be
     * assigned to this field when a setter method is invoked.
     */
    private Parameter parameter;

    /**
     * The aliases, or an empty collection if none.
     */
    private final Collection<GenericName> aliases;

    /**
     * Creates a new wrapper for the given netCDF parameter. The {@linkplain #getValueClass()
     * value class} will be inferred from the given parameter.
     *
     * <p><b>Type safety:</b><br>
     * This constructor can not have public access because we can not guarantee that the
     * {@code <T>} parameterized type is consistent with the given netCDF parameter object,
     * since the netCDF class is not parameterized. Use the one of the {@link #create(Parameter,
     * Collection) create} methods instead.</p>
     *
     * @param  parameter  the parameter to wrap.
     * @param  aliases    an immutable collection of aliases (typically the OGC and EPSG names),
     *                    or null or an empty collection if none. This collection is not cloned.
     */
    protected NetcdfParameter(final Parameter parameter, final Collection<GenericName> aliases) {
        Objects.requireNonNull(parameter);
        this.parameter = parameter;
        this.aliases   = (aliases != null) ? aliases : Collections.<GenericName>emptyList();
    }

    /**
     * Creates a new wrapper for the given netCDF parameter. The {@linkplain #getValueClass()
     * value class} will be inferred from the given parameter.
     *
     * @param  parameter  the parameter to wrap.
     * @param  aliases    an immutable collection of aliases (typically the OGC and EPSG names),
     *                    or null or an empty collection if none. This collection is not cloned.
     * @return the GeoAPI parameter for the given netCDF parameter.
     */
    @SuppressWarnings({"rawtypes", "unchecked"})
    public static NetcdfParameter<?> create(final Parameter parameter, final Collection<GenericName> aliases) {
        return new NetcdfParameter(parameter, aliases);
    }

    /**
     * Creates a new parameter for the given {@link String} value.
     *
     * <p>This method does not clone the arguments. If a non-null {@code aliases} collection
     * is given to this method, it shall be immutable.</p>
     *
     * @param  name     the parameter name.
     * @param  aliases  an immutable collection of aliases (typically the OGC and EPSG names),
     *                  or null or an empty collection if none. This collection is not cloned.
     * @param  value    the parameter value.
     * @return the GeoAPI parameter for the given netCDF parameter.
     */
    public static NetcdfParameter<String> create(String name, Collection<GenericName> aliases, String value) {
        return new NetcdfParameter<>(new Parameter(name, value), aliases);
    }

    /**
     * Creates a new parameter for the given {@code double} value.
     *
     * @param  name     the parameter name.
     * @param  aliases  an immutable collection of aliases (typically the OGC and EPSG names),
     *                  or null or an empty collection if none. This collection is not cloned.
     * @param  value    the parameter value.
     * @return the GeoAPI parameter for the given netCDF parameter.
     */
    public static NetcdfParameter<Double> create(String name, Collection<GenericName> aliases, double value) {
        return new NetcdfParameter<>(new Parameter(name, value), aliases);
    }

    /**
     * Creates a new parameter for the given {@code double[]} array.
     *
     * @param  name     the parameter name.
     * @param  aliases  an immutable collection of aliases (typically the OGC and EPSG names),
     *                  or null or an empty collection if none. This collection is not cloned.
     * @param  values   the parameter values.
     * @return the GeoAPI parameter for the given netCDF parameter.
     */
    public static NetcdfParameter<double[]> create(String name, Collection<GenericName> aliases, double... values) {
        return new NetcdfParameter<>(new Parameter(name, values), aliases);
    }

    /**
     * Returns the netCDF object wrapped by this adapter. Note that it may be different
     * than the netCDF object given to the constructor if a setter method has been invoked.
     *
     * @return netCDF parameter.
     */
    @Override
    public Parameter delegate() {
        return parameter;
    }

    /**
     * Returns the parameter name. This method delegates to {@link Parameter#getName()}.
     * The name returned by this method is the netCDF name. For the OGC or EPSG names,
     * see {@link #getAlias()}.
     *
     * @return the netCDF parameter name.
     *
     * @see Parameter#getName()
     */
    @Override
    public String getCode() {
        return parameter.getName();
    }

    /**
     * Returns the aliases given at construction time. If the collection is non-empty,
     * then it will typically contains two aliases: one for the OGC name and one for
     * the EPSG name. For the netCDF name, see {@link #getCode()}.
     */
    @Override
    public Collection<GenericName> getAlias() {
        return aliases;
    }

    /**
     * Returns the parameter descriptor, which is {@code this}.
     */
    @Override
    public ParameterDescriptor<T> getDescriptor() {
        return this;
    }

    /**
     * Returns {@code null}, since this simple class does not provide parameter description.
     */
    @Override
    public InternationalString getDescription() {
        return null;
    }

    /**
     * Returns {@code this}, since this simple class is used only as input parameter.
     */
    @Override
    public ParameterDirection getDirection() {
        return ParameterDirection.IN;
    }

    /**
     * Returns a new value for this descriptor.
     *
     * @return a new parameter value.
     */
    @Override
    public NetcdfParameter<T> createValue() {
        return new NetcdfParameter<>(parameter, aliases);
    }

    /**
     * Returns the parameter type typically as a {@link String}, {@link Double} or {@code double[]}
     * class. The above-cited types are the ones supported by the netCDF parameter implementation.
     */
    @Override
    @SuppressWarnings("unchecked")
    public Class<T> getValueClass() {
        return (Class<T>) getValueClass(parameter);
    }

    /**
     * Implementation of the public {@link #getValueClass()} method.
     *
     * @param  parameter  the netCDF parameter from which to infer the value class.
     */
    private static Class<?> getValueClass(final Parameter parameter) {
        if (parameter.isString()) return String.class;
        if (parameter.getLength() == 1) return Double.class;
        return double[].class;
    }

    /**
     * Returns the {@linkplain #getValue() current value} as the default value.
     * This is consistent with the {@link #createValue()} method, which creates
     * a copy of {@code NetcdfParameter} containing the same parameter value.
     */
    @Override
    public T getDefaultValue() {
        return getValue();
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
     * Returns the exception to throw for a getter method invoked with a parameter of the wrong type.
     *
     * @param  parameter  the netCDF parameter.
     * @param  requested  the requested type.
     * @return the exception to throw.
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
     * @throws IllegalArgumentException always thrown.
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
     * @return the parameter numeric value.
     * @throws InvalidParameterTypeException if the parameter value is not convertible
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
     * @return the parameter numeric value.
     * @throws InvalidParameterTypeException if the parameter value is not convertible
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
     * @return the parameter boolean value.
     * @throws InvalidParameterTypeException if the parameter value is not convertible
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
     * @return the parameter string value.
     * @throws InvalidParameterTypeException if the parameter value is not convertible
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
     * @throws IllegalArgumentException always thrown.
     */
    @Override
    public double[] doubleValueList(final Unit<?> unit) throws IllegalArgumentException {
        throw new IllegalArgumentException("This parameter has no unit information.");
    }

    /**
     * Returns the {@linkplain Parameter#getNumericValues() parameter numeric values}
     * as an array of floating point values.
     *
     * @return the parameter numeric values.
     * @throws InvalidParameterTypeException if the parameter value is not convertible
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
     * @return the parameter numeric values.
     * @throws InvalidParameterTypeException if the parameter value is not convertible
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
     * @return the parameter URI value.
     * @throws InvalidParameterTypeException if the parameter value is not convertible
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
     * @return the parameter value as an object.
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
     * @param  parameter  the netCDF parameter.
     * @param  value      the value given by the user.
     * @return the exception to throw.
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
     * @throws IllegalArgumentException always thrown.
     */
    @Override
    public void setValue(final double[] values, final Unit<?> unit) throws IllegalArgumentException {
        throw new IllegalArgumentException("This parameter has no unit information.");
    }

    /**
     * Unsupported operation, since the {@link Parameter} class has no information about unit
     * of measurement.
     *
     * @throws IllegalArgumentException always thrown.
     */
    @Override
    public void setValue(final double value, final Unit<?> unit) throws IllegalArgumentException {
        throw new IllegalArgumentException("This parameter has no unit information.");
    }

    /**
     * Sets this parameter to the given value. This method creates a new {@link Parameter}
     * instance with the given value.
     *
     * @throws InvalidParameterValueException if this parameter can not accept the {@code double} type.
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
     * @throws InvalidParameterValueException if this parameter can not accept the {@code int} type.
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
     * @throws InvalidParameterValueException if this parameter can not accept the {@code boolean} type.
     *
     * @see Parameter#Parameter(String, String)
     */
    @Override
    public void setValue(final boolean value) throws InvalidParameterValueException {
        setValue(Boolean.toString(value));
    }

    /**
     * Sets this parameter to the given {@link String}, {@code double[]} or {@link Number}.
     * If the given value is a number, then this method delegates to {@link #setValue(double)}.
     *
     * @throws InvalidParameterValueException if this parameter can not accept the given value.
     *
     * @see Parameter#Parameter(String, String)
     */
    @Override
    public void setValue(final Object value) throws InvalidParameterValueException {
        if (value instanceof Number) {
            setValue(((Number) value).doubleValue());
        } else if ((value instanceof double[]) && !parameter.isString()) {
            parameter = new Parameter(parameter.getName(), (double[]) value);
        } else if ((value instanceof CharSequence) && parameter.isString()) {
            parameter = new Parameter(parameter.getName(), value.toString());
        } else {
            throw invalidValue(parameter, value);
        }
    }

    /**
     * Compares this parameter with the given object for equality. This method compares
     * the {@linkplain #getAlias() aliases} in addition to the netCDF parameter object.
     *
     * @return {@code true} if this parameter is equals to the given object.
     */
    @Override
    public boolean equals(final Object other) {
        if (super.equals(other)) {
            return aliases.equals(((NetcdfParameter) other).aliases);
        }
        return false;
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
            throw new AssertionError(e);                // Should never happen since we are cloneable.
        }
    }
}
