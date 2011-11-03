/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    http://www.geoapi.org
 *
 *    This file is hereby placed into the Public Domain.
 *    This means anyone is free to do whatever they wish with this file.
 */
package org.opengis.example.parameter;

import java.util.Set;
import java.net.URI;

import javax.measure.converter.ConversionException;
import javax.measure.unit.NonSI;
import javax.measure.unit.SI;
import javax.measure.unit.Unit;

import org.opengis.metadata.citation.Citation;
import org.opengis.parameter.InvalidParameterValueException;
import org.opengis.parameter.InvalidParameterTypeException;
import org.opengis.parameter.ParameterDescriptor;
import org.opengis.parameter.ParameterValue;

import org.opengis.example.referencing.SimpleIdentifiedObject;


/**
 * A relatively "simple" implementation of a parameter value for {@code double} values.
 * In order to keep the conceptual model simpler, this parameter value is also its own
 * descriptor. This is not quite a recommended practice (such descriptors are less suitable
 * for use in {@link java.util.HashMap}), but allow us to keep the amount of classes smaller
 * and closely related interfaces together.
 * <p>
 * For keeping things yet simpler, the {@linkplain #getValueClass() value class} is hard-coded as
 * {@link Double}, the {@linkplain #getUnit() units of measurement} are constrained to standard
 * units (metres, decimal degrees or dimensionless) and we care only about descriptor properties
 * ({@linkplain #getMinimumValue() minimum}, {@linkplain #getMaximumValue() maximum}, <i>etc.</i>)
 * determined by our own {@link Type} enumeration. The only mutable property in this class is the
 * numerical {@linkplain #value}.
 * <p>
 * The most interesting methods in this class are:
 * <p>
 * <ul>
 *   <li>{@link #getName()}, for the name of this parameter</li>
 *   <li>{@link #getUnit()}, for the unit of measurement.</li>
 *   <li>{@link #getMinimumValue()} and {@link #getMaximumValue()}, for the range of valid values</li>
 *   <li>{@link #getValue()}, {@link #doubleValue()} and {@link #doubleValue(Unit)}, for the actual parameter value</li>
 *   <li>{@link #setValue(double)}, {@link #setValue(double, Unit)} and {@link #setValue(Object)}, for setting the parameter value</li>
 * </ul>
 * <p>
 * Other methods can be ignored, since they are metadata or methods designed for parameter values
 * of other kind than {@code double}.
 *
 * @author  Martin Desruisseaux (Geomatys)
 * @version 3.1
 * @since   3.1
 */
public class SimpleParameter extends SimpleIdentifiedObject
        implements ParameterValue<Double>, ParameterDescriptor<Double>, Cloneable
{
    /**
     * Determines the range of values and the unit of measurement of a parameter.
     *
     * @author  Martin Desruisseaux (Geomatys)
     * @version 3.1
     * @since   3.1
     */
    public static enum Type {
        /**
         * Longitude as decimal degrees in the [-180° &hellip; +180°] range.
         */
        LONGITUDE(NonSI.DEGREE_ANGLE, -90.0, +90.0),

        /**
         * Latitude as decimal degrees in the [-90° &hellip; +90°] range.
         */
        LATITUDE(NonSI.DEGREE_ANGLE, -180.0, +180.0),

        /**
         * Any linear value as metres, unbounded.
         */
        LINEAR(SI.METRE, null, null),

        /**
         * Length as metres in the [0 &hellip; &infin;] range.
         */
        LENGTH(SI.METRE, 0.0, null),

        /**
         * Scale as dimensionless number in the [0 &hellip; &infin;] range.
         */
        SCALE(Unit.ONE, LENGTH.minimum, null);

        /** Value to be returned by {@link SimpleParameter#getUnit()}. */
        final Unit<?> unit;

        /** Value to be returned by {@link SimpleParameter}. */
        final Double minimum, maximum;

        /** Creates a new enum with the given unit of measurement and range of values. */
        private Type(final Unit<?> unit, final Double minimum, final Double maximum) {
            this.unit    = unit;
            this.minimum = minimum;
            this.maximum = maximum;
        }
    }

    /**
     * For cross-version compatibility.
     */
    private static final long serialVersionUID = -127301955366750847L;

    /**
     * The parameter type, which determines the range of values and the unit of measurement.
     * This field can be {@code null} if the parameter type is none of the enumerated ones.
     */
    protected final Type type;

    /**
     * The parameter value.
     */
    protected double value;

    /**
     * Creates a new parameter of the given authority and name.
     *
     * @param authority Organization responsible for definition of the parameter, or {@code null}.
     * @param name      The parameter name.
     * @param type      The parameter type, which determines the range of values and the unit of measurement.
     *                  This argument can be {@code null} if the parameter type is none of the enumerated ones.
     */
    public SimpleParameter(final Citation authority, final String name, final Type type) {
        super(authority, name);
        this.type = type;
    }

    /**
     * Returns the descriptor of the parameter value, which is this {@code this} object itself.
     */
    @Override
    public ParameterDescriptor<Double> getDescriptor() {
        return this;
    }

    /**
     * Returns the minimum number of times that values for this parameter are required.
     * The default implementation returns 1, meaning that a value shall alway be supplied
     * (the {@link #getValue()} method never return {@code null}).
     */
    @Override
    public int getMinimumOccurs() {
        return 1;
    }

    /**
     * Returns the maximum number of times that values for this parameter can be included.
     * This method unconditionally returns 1, which is the mandatory value for
     * {@link ParameterValue} implementations.
     */
    @Override
    public int getMaximumOccurs() {
        return 1;
    }

    /**
     * Unconditionally returns {@code Double.class}, which is the hard-coded type of values
     * in this parameter implementation.
     */
    @Override
    public Class<Double> getValueClass() {
        return Double.class;
    }

    /**
     * Returns the unit of measurement. Because this class implements both the <cite>value</cite>
     * and <cite>descriptor</cite> interfaces, the unit of measurement applies to the
     * {@linkplain #doubleValue() parameter value}, the {@linkplain #getDefaultValue default},
     * the {@linkplain #getMinimumValue minimum} and the {@linkplain #getMaximumValue maximum}
     * values.
     *
     * @return The unit of measurement, or {@code null} if unknown.
     */
    @Override
    public Unit<?> getUnit() {
        return (type != null) ? type.unit : null;
    }

    /**
     * Returns the minimum parameter value, or {@code null} if none.
     *
     * @return The minimum parameter value, or {@code null} if unbounded.
     */
    @Override
    public Comparable<Double> getMinimumValue() {
        return (type != null) ? type.minimum : null;
    }

    /**
     * Returns the maximum parameter value, or {@code null} if none.
     *
     * @return The maximum parameter value, or {@code null} if unbounded.
     */
    @Override
    public Comparable<Double> getMaximumValue() {
        return (type != null) ? type.maximum : null;
    }

    /**
     * Returns the set of allowed values when these are restricted to some finite set or returns
     * {@code null} otherwise. The default implementation unconditionally returns {@code null}.
     */
    @Override
    public Set<Double> getValidValues() {
        return null;
    }

    /**
     * Returns the default value for the parameter.
     * The default implementation unconditionally returns {@code null}.
     */
    @Override
    public Double getDefaultValue() {
        return null;
    }

    /**
     * Returns the parameter value as an object.
     *
     * @return The parameter value as an object.
     */
    @Override
    public Double getValue() {
        return value;
    }

    /**
     * Returns the numeric value represented by this parameter.
     *
     * @return The numeric value represented by this parameter.
     */
    @Override
    public double doubleValue() {
        return value;
    }

    /**
     * Returns the numeric value of the operation parameter in the specified unit of measure.
     * This convenience method applies unit conversion on the fly as needed.
     *
     * @param  unit The unit of measure for the value to be returned.
     * @return The numeric value represented by this parameter after conversion to {@code unit}.
     * @throws IllegalArgumentException if the specified unit is invalid for this parameter.
     * @throws IllegalStateException if there is no unit associated to this parameter value.
     */
    @Override
    public double doubleValue(final Unit<?> unit) throws IllegalArgumentException, IllegalStateException {
        if (type == null) {
            throw new IllegalStateException("No unit for parameter " + code);
        }
        try {
            return type.unit.getConverterToAny(unit).convert(value);
        } catch (ConversionException e) {
            throw new IllegalArgumentException(e);
        }
    }

    /**
     * Returns the integer value of an operation parameter, usually used for a count.
     *
     * @return The numeric value represented by this parameter after conversion to type {@code int}.
     * @throws InvalidParameterTypeException if the value can not be cast to an integer type.
     */
    @Override
    public int intValue() throws InvalidParameterTypeException {
        final int candidate = (int) value;
        if (candidate != value) {
            throw new InvalidParameterTypeException("Value " + value + " is not an integer.", code);
        }
        return candidate;
    }

    /**
     * Returns the boolean value of an operation parameter. The default implementation returns
     * {@code false} if the {@linkplain #value} is 0, {@code true} if the value is different
     * than 0 and {@link Double#NaN NaN}, or thrown an exception otherwise.
     *
     * @return The boolean value represented by this parameter.
     * @throws InvalidParameterTypeException if the value can not be converted to a boolean.
     */
    @Override
    public boolean booleanValue() throws InvalidParameterTypeException {
        if (Double.isNaN(value)) {
            throw new InvalidParameterTypeException("Value " + value + " is not a boolean.", code);
        }
        return (value != 0);
    }

    /**
     * Returns the string representation of an operation parameter value.
     *
     * @see #toString()
     */
    @Override
    public String stringValue() {
        String s = String.valueOf(value);
        if (type != null) {
            s = s + ' ' + type.unit;
        }
        return s;
    }

    /**
     * Returns the string representation of this parameter value. The default implementation returns
     * the concatenation of the {@linkplain SimpleIdentifiedObject#toString() identifier string},
     * the {@code " = "} string, then the {@linkplain #stringValue() string value}.
     *
     * @see #stringValue()
     */
    @Override
    public String toString() {
        return super.toString() + " = " + stringValue();
    }

    /**
     * Returns an ordered sequence of numeric values in the specified unit of measure.
     * The default implementation returns {@link #doubleValue(Unit)} in an array of length 1.
     *
     * @throws IllegalArgumentException if the specified unit is invalid for this parameter.
     * @throws IllegalStateException if there is no unit associated to this parameter value.
     */
    @Override
    public double[] doubleValueList(final Unit<?> unit) throws IllegalArgumentException, IllegalStateException {
        return new double[] {doubleValue(unit)};
    }

    /**
     * Returns an ordered sequence numeric values of an operation parameter list, where each value
     * has the same associated {@linkplain Unit unit of measure}. The default implementation returns
     * {@link #doubleValue()} in an array of length 1.
     *
     * @return The sequence of values represented by this parameter.
     */
    @Override
    public double[] doubleValueList() {
        return new double[] {doubleValue()};
    }

    /**
     * Returns an ordered sequence integer values of an operation parameter list.
     * The default implementation returns {@link #intValue()} in an array of length 1.
     *
     * @return The sequence of values represented by this parameter.
     */
    @Override
    public int[] intValueList() {
        return new int[] {intValue()};
    }

    /**
     * Thrown unconditionally the exception, since this parameter implementation can not
     * represent URI.
     *
     * @return Never returned.
     * @throws InvalidParameterTypeException Always thrown.
     */
    @Override
    public URI valueFile() throws InvalidParameterTypeException {
        throw new InvalidParameterTypeException("Value " + value + " is not a file.", code);
    }

    /**
     * Sets the parameter value as an array of floating point and their associated unit.
     * The default implementation ensures that the array length is exactly 1, then delegates
     * to {@link #setValue(double, Unit)}.
     *
     * @param  values The parameter values.
     * @param  unit The unit for the specified values.
     * @throws InvalidParameterValueException if this parameter can not be set to the given value.
     */
    @Override
    public void setValue(final double[] values, final Unit<?> unit) throws InvalidParameterValueException {
        if (values.length != 1) {
            throw new InvalidParameterValueException("Expected an array of length 1.", code, values);
        }
        setValue(values[0], unit);
    }

    /**
     * Sets the parameter to the given value and its associated unit.
     * The default implementation converts the given value to the {@linkplain #getUnit()
     * units associated to this parameter}, then delegates to {@link #setValue(double)}.
     *
     * @param  value The parameter value.
     * @param  unit The unit for the specified value.
     * @throws InvalidParameterValueException if this parameter can not be set to the given value.
     */
    @Override
    public void setValue(final double value, final Unit<?> unit) throws InvalidParameterValueException {
        if (type == null) {
            throw new InvalidParameterValueException("No unit expected for parameter " + code, code, unit);
        }
        try {
            setValue(unit.getConverterToAny(type.unit).convert(value));
        } catch (ConversionException e) {
            throw new InvalidParameterValueException(e.getLocalizedMessage(), code, unit);
        }
    }

    /**
     * Sets the parameter value as a floating point.
     *
     * @throws InvalidParameterValueException if the parameter value is out of range.
     */
    @Override
    public void setValue(final double value) {
        if (type != null) {
            Double limit = type.minimum;
            if (limit != null && value < limit) {
                throw new InvalidParameterValueException("Value " + value
                        + " shall be greater than or equals to " + limit, code, value);
            }
            limit = type.maximum;
            if (limit != null && value > limit) {
                throw new InvalidParameterValueException("Value " + value
                        + " shall be less than or equals to " + limit, code, value);
            }
        }
        this.value = value;
    }

    /**
     * Sets the parameter value as an integer.
     */
    @Override
    public void setValue(final int value) {
        setValue((double) value);
    }

    /**
     * Sets the parameter value as a boolean. The boolean value {@code true} is stored as the
     * numeric value 1, and the boolean value {@code false} is stored as the numeric value 0.
     */
    @Override
    public void setValue(final boolean value) {
        setValue(value ? 1 : 0);
    }

    /**
     * Sets the parameter value as an object. The object type can be any {@link Number},
     * or a {@link String} parseable as a floating point number.
     *
     * @param  value The parameter value.
     * @throws InvalidParameterValueException if the value can not be stored as a {@code double}.
     */
    @Override
    public void setValue(final Object value) throws InvalidParameterValueException {
        final double number;
        if (value instanceof Number) {
            number = ((Number) value).doubleValue();
        } else if (value instanceof CharSequence) {
            final String s = value.toString();
            try {
                number = Double.parseDouble(s);
            } catch (NumberFormatException e) {
                throw new InvalidParameterValueException(e.toString(), code, s);
            }
        } else {
            throw new InvalidParameterValueException(value.getClass() + " is not valid.", code, value);
        }
        setValue(number);
    }

    /**
     * Returns a new parameter with the same {@linkplain #authority authority}, {@linkplain #code code}
     * and {@link #type} than this parameter. The {@linkplain #value} is left uninitialized.
     *
     * @see #clone()
     */
    @Override
    public ParameterValue<Double> createValue() {
        return new SimpleParameter(authority, code, type);
    }

    /**
     * Returns a copy of this parameter value. This method is similar to {@link #createValue()}
     * except for the following:
     * <p>
     * <ul>
     *   <li>This method returns an instance of the same class.</li>
     *   <li>The {@linkplain #value} is initialized to the same value than the cloned parameter.</li>
     * </ul>
     *
     * @see #createValue()
     */
    @Override
    @SuppressWarnings("unchecked")
    public ParameterValue<Double> clone() {
        try {
            return (ParameterValue<Double>) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError(e); // Should never happen, since we are cloneable.
        }
    }

    /**
     * Compares the given object with this parameter for equality.
     */
    @Override
    public boolean equals(final Object object) {
        if (super.equals(object)) {
            final SimpleParameter other = (SimpleParameter) object;
            return type == other.type && Double.doubleToLongBits(value) == Double.doubleToLongBits(other.value);
        }
        return false;
    }

    /**
     * Returns a hash code value for this parameter.
     */
    @Override
    public int hashCode() {
        long code = Double.doubleToLongBits(value);
        if (type != null) {
            code += 31 * type.hashCode();
        }
        return (int) code + (int) (code >>> Integer.SIZE) + 37*super.hashCode();
    }
}
