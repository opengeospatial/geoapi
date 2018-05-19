/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    http://www.geoapi.org
 *
 *    This file is hereby placed into the Public Domain.
 *    This means anyone is free to do whatever they wish with this file.
 *
 *    The netCDF wrappers are provided as code examples, in the hope to facilitate
 *    GeoAPI implementations backed by other libraries. Implementors can take this
 *    source code and use it for any purpose, commercial or non-commercial, copyrighted
 *    or open-source, with no legal obligation to acknowledge the borrowing/copying
 *    in any way.
 */
package org.opengis.wrapper.netcdf;

import ucar.unidata.util.Parameter;
import org.opengis.parameter.InvalidParameterTypeException;
import org.opengis.parameter.InvalidParameterValueException;


/**
 * A netCDF {@link Parameter} value at a given index. This implementation is used mostly for the
 * "{@code standard_parallel}" parameter. While OGC is used to store the two standard parallels
 * in two distinct parameters, netCDF rather stores the two standard parallels in a single array
 * of length 2. This {@code IndexedParameter} implementation wraps such {@code NetcdfParameter},
 * adds the suffix "{@code [1]}" or "{@code [2]}" to the parameter name and represents the value
 * at the corresponding index as a single scalar.
 *
 * @author  Martin Desruisseaux (Geomatys)
 * @version 3.1
 * @since   3.1
 */
final class IndexedParameter extends NetcdfParameter<Double> {
    /**
     * Serial number for cross-version compatibility.
     */
    private static final long serialVersionUID = -1441779449763543031L;

    /**
     * The index of the value to fetch.
     */
    private final int index;

    /**
     * Creates a new wrapper for the given netCDF parameter.
     * The given arguments shall comply to the following conditions:
     *
     * <ul>
     *   <li>The {@linkplain Parameter#getNumericValues() parameter values} must be an array
     *       of length 2 or more.</li>
     *   <li>The {@link AliasList#name} must starts by the
     *       {@linkplain Parameter#getName() parameter name}.</li>
     *   <li>The {@link AliasList#name} must ends by the "{@code [n]"} string where
     *       <var>n</var>-1 is the index of the value to read in the parameter array.</li>
     * </ul>
     *
     * @param parameter  the parameter to wrap.
     * @param aliases    an immutable collection of aliases (typically the OGC and EPSG names),
     *                   or null or an empty collection if none. This collection is not cloned.
     */
    IndexedParameter(final Parameter parameter, final AliasList aliases) {
        super(parameter, aliases);
        assert super.getValueClass().equals(double[].class) : parameter;
        final String name = aliases.name;
        final int e = name.length() - 1;
        final int s = name.lastIndexOf('[', e);
        assert name.charAt(e) == ']' : name;
        assert name.substring(0, s).equals(parameter.getName()) : name;
        index = Integer.parseInt(name.substring(s+1, e)) - 1;
    }

    /**
     * Returns the parameter name declared at construction time. This is different than the
     * parameter name of the netCDF object, because this instance has a {"[1]"} or {"[2]"}
     * suffix after the netCDF name.
     */
    @Override
    public String getCode() {
        return ((AliasList) getAlias()).name;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NetcdfParameter<Double> createValue() {
        return clone();
    }

    /**
     * Returns the parameter type.
     */
    @Override
    public Class<Double> getValueClass() {
        return Double.class;
    }

    /**
     * Returns the {@linkplain Parameter#getNumericValue() parameter numeric value}
     * as a floating point value.
     */
    @Override
    public double doubleValue() throws InvalidParameterTypeException {
        return delegate().getNumericValue(index);
    }

    /**
     * Unconditionally throw an exception.
     */
    @Override
    public double[] doubleValueList() throws InvalidParameterTypeException {
        throw new InvalidParameterTypeException("This parameter is not an array.", delegate().getName());
    }

    /**
     * Sets this parameter to the given value. This method modify the internal array of the
     * netCDF {@link Parameter} instance. This is usually not a recommended practice, since
     * those {@code Parameter} are usually considered immutable. However in our case we
     * really want to modify existing instance without creating new instance, because the
     * same instance need to be shared by many {@code IndexedParameter} wrappers: one for
     * each index.
     */
    @Override
    public void setValue(final double value) throws InvalidParameterValueException {
        delegate().getNumericValues()[index] = value;
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
        } else {
            throw new InvalidParameterTypeException("The value must be a number.", delegate().getName());
        }
    }

    /**
     * Compares this parameter with the given object for equality.
     */
    @Override
    public boolean equals(final Object other) {
        if (super.equals(other)) {
            return ((IndexedParameter) other).index == index;
        }
        return false;
    }
}
