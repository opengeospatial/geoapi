/*$************************************************************************************************
 **
 ** $Id$
 **
 ** $Source$
 **
 ** Copyright (C) 2003 Open GIS Consortium, Inc. All Rights Reserved. http://www.opengis.org/Legal/
 **
 *************************************************************************************************/
package org.opengis.parameter;

// J2SE direct dependencies
import java.util.Set;
import javax.units.Unit;


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
     * Creates a new instance of {@linkplain ParameterValue parameter value}
     * initialized with the {@linkplain #getDefaultValue default value}.
     * The {@linkplain ParameterValue#getDescriptor parameter value descriptor}
     * for the created parameter value will be <code>this</code> object.
     */
/// ParameterValue createValue();

    /**
     * Returns the class that describe the type of the parameter.
     *
     * @return The parameter value class.
     *
     * @UML mandatory type in Grid Coverage specification
     */
    Class getValueClass();

    /**
     * If this parameter allows only a finite set of values, returns this set.
     * This set is usually a {linkplain org.opengis.util.CodeList code list} or
     * enumerations. This method returns <code>null</code> if this parameter
     * doesn't limits values to a finite set.
     *
     * @return A finite set of valid values (usually from a
     *         {linkplain org.opengis.util.CodeList code list}),
     *         or <code>null</code> if it doesn't apply.
     */
    Set/*<? extends Object>*/ getValidValues();

    /**
     * Returns the default value for the parameter. The return type can be any type
     * including a {@link Number} or a {@link String}. If there is no default value,
     * then this method returns <code>null</code>.
     *
     * @return The default value, or <code>null</code> in none.
     *
     * @UML optional defaultValue in Grid Coverage specification
     */
    Object getDefaultValue();

    /**
     * Returns the minimum parameter value. If there is no minimum value, or if minimum
     * value is inappropriate for the {@linkplain #getValueClass parameter type}, then
     * this method returns <code>null</code>.
     *
     * @return The minimum parameter value (often an instance of {@link Double}), or <code>null</code>.
     *
     * @UML optional minimumValue in Grid Coverage specification
     */
    Comparable getMinimumValue();

    /**
     * Returns the maximum parameter value. If there is no maximum value, or if maximum
     * value is inappropriate for the {@linkplain #getValueClass parameter type}, then
     * this method returns <code>null</code>.
     *
     * @return The minimum parameter value (often an instance of {@link Double}), or <code>null</code>.
     *
     * @UML optional maximumValue in Grid Coverage specification
     */
    Comparable getMaximumValue();

    /**
     * Returns the unit for
     * {@linkplain #getDefaultValue default},
     * {@linkplain #getMinimumValue minimum} and
     * {@linkplain #getMaximumValue maximum} values.
     * This attribute apply only if the values is of numeric type (usually an instance
     * of {@link Double}).
     *
     * @return The unit for numeric value, or <code>null</code> if it
     *         doesn't apply to the value type.
     */
    Unit getUnit();
}
