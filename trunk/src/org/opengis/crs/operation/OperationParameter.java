/*$************************************************************************************************
 **
 ** $Id$
 **
 ** $Source$
 **
 ** Copyright (C) 2003 Open GIS Consortium, Inc. All Rights Reserved. http://www.opengis.org/Legal/
 **
 *************************************************************************************************/
package org.opengis.crs.operation;


/**
 * The definition of a parameter used by an operation method. Most parameter values are
 * numeric, but other types of parameter values are possible.
 *  
 * @UML abstract CC_OperationParameter
 * @author ISO 19111
 * @author <A HREF="http://www.opengis.org">OpenGIS&reg; consortium</A>
 * @version <A HREF="http://www.opengis.org/docs/03-073r1.zip">Abstract specification 2.0</A>
 *
 * @see ParameterValue
 * @see OperationParameterGroup
 */
public interface OperationParameter extends GeneralOperationParameter {
    /**
     * Returns the class that describe the type of the parameter.
     *
     * @return The parameter value class.
     */
    Class getValueClass();

    /**
     * Returns the default value for the parameter. The return type can be any type
     * including a {@link Number} or a {@link String}. If there is no default value,
     * then this method returns <code>null</code>.
     *
     * @return The default value, or <code>null</code> in none.
     */
    Object getDefaultValue();

    /**
     * Returns the minimum parameter value. If there is no minimum value, then
     * this method returns {@link Double#NEGATIVE_INFINITY}. If minimum value
     * is inappropriate for the {@linkplain #getValueClass parameter type},
     * then this method returns {@link Double#NaN}.
     *
     * @return The minimum parameter value.
     */
    double getMinimumValue();

    /**
     * Returns the maximum parameter value. If there is no maximum value, then
     * this method returns {@link Double#POSITIVE_INFINITY}. If maximum value
     * is inappropriate for the {@linkplain #getValueClass parameter type},
     * then this method returns {@link Double#NaN}.
     *
     * @return The maximum parameter value.
     */
    double getMaximumValue();
}
