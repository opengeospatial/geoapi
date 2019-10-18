/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    http://www.geoapi.org
 *
 *    This file is hereby placed into the Public Domain.
 *    This means anyone is free to do whatever they wish with this file.
 *
 *    The Proj.4 wrappers are provided as code examples, in the hope to facilitate
 *    GeoAPI implementations backed by other libraries. Implementers can take this
 *    source code and use it for any purpose, commercial or non-commercial, copyrighted
 *    or open-source, with no legal obligation to acknowledge the borrowing/copying
 *    in any way.
 */
package org.opengis.wrapper.proj4;

import java.net.URI;
import java.util.Collection;
import javax.measure.Unit;
import javax.measure.IncommensurableException;

import org.opengis.util.GenericName;
import org.opengis.metadata.Identifier;
import org.opengis.parameter.ParameterValue;
import org.opengis.parameter.ParameterDescriptor;
import org.opengis.parameter.ParameterDirection;
import org.opengis.parameter.InvalidParameterTypeException;
import org.opengis.parameter.InvalidParameterValueException;


/**
 * A relatively "simple" implementation of a parameter value for {@code double} values.
 * In order to keep the conceptual model simpler, this parameter value is also its own
 * descriptor. This is not quite a recommended practice (such descriptors are less suitable
 * for use in {@link java.util.HashMap}), but allow us to keep the amount of classes smaller
 * and closely related interfaces together.
 *
 * <p>This class is a simplified version of the {@link org.opengis.example.parameter.PJParameter}
 * class. See the later for more information.</p>
 *
 * @author  Martin Desruisseaux (Geomatys)
 * @version 3.1
 * @since   3.1
 */
final class PJParameter extends PJObject implements ParameterValue<Double>, ParameterDescriptor<Double>, Cloneable {
    /**
     * The parameter value.
     *
     * @see #doubleValue()
     * @see #setValue(double)
     */
    private double value;

    /**
     * Creates a new parameter with the given identifier and aliases.
     */
    PJParameter(final Identifier identifier, final Collection<GenericName> aliases) {
        super(identifier, aliases);
    }

    /**
     * Creates a new parameter as a copy of the given one.
     */
    PJParameter(final ParameterValue<?> param) {
        super(param.getDescriptor());
        value = param.doubleValue();
    }

    /**
     * Returns the descriptor of the parameter value, which is {@code this}.
     */
    @Override
    public ParameterDescriptor<Double> getDescriptor() {
        return this;
    }

    /**
     * Returns {@code this}, since this simple class is used only as input parameter.
     */
    @Override
    public ParameterDirection getDirection() {
        return ParameterDirection.IN;
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
     * Returns {@code null}, since this simple class has no information about units of measurement.
     */
    @Override
    public Unit<?> getUnit() {
        return null;
    }

    /**
     * Returns the parameter {@linkplain #value} as an object.
     */
    @Override
    public Double getValue() {
        return value;
    }

    /**
     * Returns the numeric {@linkplain #value} represented by this parameter.
     */
    @Override
    public double doubleValue() {
        return value;
    }

    /**
     * Returns the standard unit used by Proj.4 for any value in the given unit.
     */
    private static Unit<?> getStandardUnit(final Unit<?> unit) {
        if (unit.isCompatible(Units.METRE))  return Units.METRE;
        if (unit.isCompatible(Units.DEGREE)) return Units.DEGREE;
        if (unit.isCompatible(Units.ONE))    return Units.ONE;
        return null;
    }

    /**
     * Returns the numeric value of the operation parameter in the specified unit of measure.
     * This convenience method applies unit conversion from metres or decimal degrees as needed.
     *
     * @param  unit  the unit of measure for the value to be returned.
     * @return the numeric value represented by this parameter after conversion to {@code unit}.
     */
    @Override
    public double doubleValue(final Unit<?> unit) {
        double c = value;
        final Unit<?> standardUnit = getStandardUnit(unit);
        if (standardUnit != null) try {
            c = standardUnit.getConverterToAny(unit).convert(c);
        } catch (IncommensurableException e) {
            throw new IllegalArgumentException(e);              // Should never happen actually.
        }
        return c;
    }

    /**
     * Creates an exception for an invalid type.
     */
    private InvalidParameterTypeException invalidType(final String type) {
        return new InvalidParameterTypeException("Value " + value + " is not " + type + '.', name.getCode());
    }

    /**
     * Returns the value as an integer if it can be casted without information lost,
     * or throw an exception otherwise.
     */
    @Override
    public int intValue() throws InvalidParameterTypeException {
        final int r = (int) value;
        if (r == value) {
            return r;
        }
        throw invalidType("an integer");
    }

    /**
     * Unsupported operation, since this parameter is not a boolean.
     */
    @Override
    public boolean booleanValue() throws InvalidParameterTypeException {
        throw invalidType("a boolean");
    }

    /**
     * Unsupported operation, since this parameter is not a string.
     */
    @Override
    public String stringValue() throws InvalidParameterTypeException {
        throw invalidType("a string");
    }

    /**
     * Unsupported operation, since this parameter is not an array.
     */
    @Override
    public double[] doubleValueList(final Unit<?> unit) throws IllegalArgumentException, IllegalStateException {
        throw invalidType("an array");
    }

    /**
     * Unsupported operation, since this parameter is not an array.
     */
    @Override
    public double[] doubleValueList() throws InvalidParameterTypeException {
        throw invalidType("an array");
    }

    /**
     * Unsupported operation, since this parameter is not an array.
     */
    @Override
    public int[] intValueList() throws InvalidParameterTypeException {
        throw invalidType("an array");
    }

    /**
     * Unsupported operation, since this parameter is not a file.
     */
    @Override
    public URI valueFile() throws InvalidParameterTypeException {
        throw invalidType("a file");
    }

    /**
     * Sets the parameter to the given value, after conversion to metres or decimal degrees.
     */
    @Override
    public void setValue(double value, final Unit<?> unit) {
        final Unit<?> standardUnit = getStandardUnit(unit);
        if (standardUnit != null) try {
            value = unit.getConverterToAny(standardUnit).convert(value);
        } catch (IncommensurableException e) {
            throw new IllegalArgumentException(e);              // Should never happen actually.
        }
        this.value = value;
    }

    /**
     * Sets the parameter value as a floating point.
     */
    @Override
    public void setValue(final double value) throws InvalidParameterValueException {
        this.value = value;
    }

    /**
     * Creates an exception for an invalid value.
     */
    private InvalidParameterValueException invalidValue(final String type, final Object value) {
        return new InvalidParameterValueException("This parameter does not support " + type + '.', name.getCode(), value);
    }

    /**
     * Unsupported operation, since this parameter is not for arrays.
     */
    @Override
    public void setValue(final double[] values, final Unit<?> unit) throws InvalidParameterValueException {
        throw invalidValue("arrays", values);
    }

    /**
     * Sets the parameter value as an integer, converted to floating point.
     */
    @Override
    public void setValue(final int value) throws InvalidParameterValueException {
        this.value = value;
    }

    /**
     * Unsupported operation, since this parameter is not for booleans.
     */
    @Override
    public void setValue(final boolean value) throws InvalidParameterValueException {
        throw invalidValue("booleans", value);
    }

    /**
     * Unsupported operation, since this parameter is not for strings.
     */
    @Override
    public void setValue(final Object value) throws InvalidParameterValueException {
        throw invalidValue("strings", value);
    }

    /**
     * Returns a new parameter with the same {@linkplain #name} than this parameter.
     * The {@linkplain #value} is left to their default value.
     *
     * @see #clone()
     */
    @Override
    public PJParameter createValue() {
        return new PJParameter(name, aliases);
    }

    /**
     * Returns a copy of this parameter value. This method is similar to {@link #createValue()}
     * except that the {@linkplain #value} is initialized to the same value than the cloned parameter.
     */
    @Override
    @SuppressWarnings("CloneDoesntCallSuperClone")          // Okay since this class is final.
    public PJParameter clone() {
        return new PJParameter(this);
    }

    /**
     * Returns the string representation of this parameter value.
     */
    @Override
    public String toString() {
        return super.toString() + " = " + value;
    }
}
