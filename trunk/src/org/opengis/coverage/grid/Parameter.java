/*
 * Copyright (C) 2003 Open GIS Consortium, Inc. All Rights Reserved. http://www.opengis.org/Legal/
 */
package org.opengis.coverage.grid;


/**
 * The parameter required for a grid coverage processing operation.
 * This structure contains the parameter name (as defined from the
 * {@link ParameterInfo} structure) and it's value.
 *
 * @UML datatype CV_Parameter
 * @author <A HREF="http://www.opengis.org">OpenGIS&reg; consortium</A>
 * @version 1.1
 *
 * @revisit This interface duplicates {@link org.opengis.crs.operation.ParameterValue}.
 */
public interface Parameter {
    /**
     * Returns the informations associated to this parameter.
     */
    ParameterInfo getParameterInfo();

    /**
     * Parameter name.
     *
     * @UML mandatory name
     */
    String getName();

    /**
     * The value for parameter.
     * The type {@link Object} can be any type including a {@link Number},
     * a {@link String} or an instance of an interface. For example, a grid
     * processor operation will typically require a parameter for the input
     * grid coverage. This parameter may have <code>"Source"</code> as the
     * parameter name and the instance of the grid coverage as the value.
     *
     * @UML mandatory value
     */
    Object getValue();

    /**
     * Set the value for this parameter.
     *
     * @param  value The new value.
     * @throws InvalidParameterValueException if <code>value</code> if illegal for this parameter.
     */
    void setValue(Object value) throws InvalidParameterValueException;

    /**
     * Set the value for this parameter as an integer.
     *
     * @param  value The new value.
     * @throws InvalidParameterValueException if <code>value</code> is illegal for this parameter.
     */
    void setValue(int value) throws InvalidParameterValueException;

    /**
     * Set the value for this parameter as a floating point.
     *
     * @param  value The new value.
     * @throws InvalidParameterValueException if <code>value</code> is illegal for this parameter.
     */
    void setValue(double value) throws InvalidParameterValueException;
}
