/*
 * Copyright (C) 2003 Open GIS Consortium, Inc. All Rights Reserved. http://www.opengis.org/Legal/
 */
package org.opengis.cc;


/**
 * A parameter value used by an operation method. Most parameter values are numeric,
 * but other types of parameter values are possible.
 *
 * @author <A HREF="http://www.opengis.org">OpenGIS&reg; consortium</A>
 * @version 2.0
 *
 * @see OperationParameter
 * @see ParameterValueGroup
 */
public interface ParameterValue extends GeneralParameterValue {
    /**
     * Returns the abstract definition of this parameter value.
     *
     * @return The abstract definition of this parameter value.
     */
    public OperationParameter getDescriptor();

    /**
     * Returns the parameter value as an object.
     *
     * @return The parameter value as an object.
     * @see #intValue
     * @see #doubleValue
     * @see #setValue(Object)
     */
    public Object getValue();

    /**
     * Returns the parameter value as an integer. This may involve rounding or truncation.
     *
     * @return The numeric value represented by this parameter after conversion to type <code>int</code>.
     * @throws InvalidParameterTypeException if the value can't be converted to an <code>int</code>.
     * @see #getValue
     * @see #doubleValue
     * @see #setValue(int)
     */
    public int intValue() throws InvalidParameterTypeException;

    /**
     * Returns the parameter value as a floating point.
     *
     * @return The numeric value represented by this parameter after conversion to type <code>double</code>.
     * @throws InvalidParameterTypeException if the value can't be converted to a <code>double</code>.
     * @see #getValue
     * @see #intValue
     * @see #setValue(double)
     */
    public double doubleValue() throws InvalidParameterTypeException;

    /**
     * Set the parameter value as an object.
     *
     * @param  value The parameter value.
     * @throws InvalidParameterValueException if the type of <code>value</code> is inappropriate
     *         for this parameter, or if the value is illegal for some other reason (for example
     *         the value is numeric and out of range).
     *
     * @see #doubleValue()
     */
    public void setValue(Object value) throws InvalidParameterValueException;

    /**
     * Set the parameter value as an integer.
     *
     * @param  value The parameter value.
     * @throws InvalidParameterValueException if the value can't be stored as an integer, or if
     *         the value is illegal for some other reason (for example a value out of range).
     *
     * @see #intValue()
     */
    public void setValue(int value) throws InvalidParameterValueException;

    /**
     * Set the parameter value as a floating point.
     *
     * @param value The parameter value.
     * @throws InvalidParameterValueException if the value can't be stored as a floating point, or
     *         if the value is illegal for some other reason (for example a value out of range).
     *
     * @see #getValue()
     */
    public void setValue(double value) throws InvalidParameterValueException;

    /**
     * Returns a copy of this parameter value.
     *
     * @return A copy of this parameter value.
     */
    public ParameterValue clone();
}
