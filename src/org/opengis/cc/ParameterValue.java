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
     * @see #setValue(Object)
     */
    public Object getValueAsObject();

    /**
     * Returns the parameter value as a floating point.
     *
     * @return The parameter value.
     * @see #setValue(double)
     */
    public double getValue();

    /**
     * Set the parameter value as an object.
     *
     * @param  value The parameter value.
     * @throws ClassCastException if the <code>value</code> type is inappropriate for this object.
     * @throws IllegalArgumentException if the value is illegal for some other reason
     *         (for example the value is numeric and out of range).
     * @see #getValueAsObject()
     */
    public void setValue(Object value) throws ClassCastException, IllegalArgumentException;

    /**
     * Set the parameter value as an integer.
     *
     * @param  value The parameter value.
     * @throws ClassCastException if the value can't be stored as an integer.
     * @throws IllegalArgumentException if the value is illegal for some other reason
     *         (for example a value out of range).
     */
    public void setValue(int value) throws ClassCastException, IllegalArgumentException;

    /**
     * Set the parameter value as a floating point.
     *
     * @param value The parameter value.
     * @throws ClassCastException if the value can't be stored as a floating point.
     * @throws IllegalArgumentException if the value is illegal for some other reason
     *         (for example a value out of range).
     * @see #getValue()
     */
    public void setValue(double value) throws ClassCastException, IllegalArgumentException;

    /**
     * Returns a copy of this parameter value.
     *
     * @return A copy of this parameter value.
     */
    public ParameterValue clone();
}
