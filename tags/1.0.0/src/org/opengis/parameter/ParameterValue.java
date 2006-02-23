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

// J2SE direct dependencies and extensions
import java.net.URL;
import javax.units.Unit;


/**
 * A parameter value used by an operation method. Most parameter values are numeric, but
 * other types of parameter values are possible. The parameter type can be fetch with the
 * <code>{@linkplain #getValue()}.{@linkplain Object#getClass() getClass()}</code> idiom.
 * The {@link #getValue()} and {@link #setValue(Object)} methods can be invoked at any time.
 * Others getters and setters are parameter-type dependents.
 *
 * @UML abstract CC_ParameterValue
 * @author <A HREF="http://www.opengis.org">OpenGIS&reg; consortium</A>
 * @version <A HREF="http://www.opengis.org/docs/03-073r1.zip">Abstract specification 2.0</A>
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
/// OperationParameter getDescriptor();

    /**
     * Returns the unit of measure of the {@linkplain #doubleValue() parameter value}.
     * If the parameter value has no unit (for example because it is a {@link String} type),
     * then this method returns <code>null</code>. Note that "no unit" doesn't means
     * "dimensionless".
     *
     * @return The unit of measure, or <code>null</code> if none.
     * @UML conditional value
     *
     * @see #doubleValue()
     * @see #doubleValueList
     * @see #getValue
     */
    Unit getUnit();

    /**
     * Returns the numeric value of the coordinate operation parameter in the specified unit
     * of measure. This convenience method apply unit conversion on the fly as needed.
     *
     * @param  unit The unit of measure for the value to be returned.
     * @return The numeric value represented by this parameter after conversion to type
     *         <code>double</code> and conversion to <code>unit</code>.
     * @throws InvalidParameterTypeException if the value is not a numeric type.
     * @throws IllegalArgumentException if the specified unit is invalid for this parameter.
     *
     * @see #getUnit
     * @see #setValue(double,Unit)
     * @see #doubleValueList(Unit)
     */
    double doubleValue(Unit unit) throws InvalidParameterTypeException;

    /**
     * Returns the numeric value of the coordinate operation parameter with its
     * associated {@linkplain #getUnit unit of measure}.
     *
     * @return The numeric value represented by this parameter after conversion to type <code>double</code>.
     * @throws InvalidParameterTypeException if the value is not a numeric type.
     * @UML conditional value
     * @unitof Measure
     *
     * @rename Renamed <code>value</code> to <code>doubleValue</code> for consistency with
     *         {@link Number#doubleValue} and the other <code>fooValue</code> in this interface.
     *         Also because {@link #getValue} is already used for an {@link Object} type, for
     *         consistency with {@link #setValue(Object)}.
     *
     * @see #getUnit
     * @see #setValue(double)
     * @see #doubleValueList
     */
    double doubleValue() throws InvalidParameterTypeException;

    /**
     * Returns the positive integer value of an operation parameter, usually used
     * for a count. An integer value does not have an associated unit of measure.
     *
     * @return The numeric value represented by this parameter after conversion to type <code>int</code>.
     * @throws InvalidParameterTypeException if the value is not an integer type.
     * @UML conditional integerValue
     *
     * @rename Renamed <code>integerValue</code> to <code>intValue</code> for consistency with
     *         {@link Number#intValue} and the Java primitive type <code>int</code>.
     *
     * @see #setValue(int)
     * @see #intValueList
     */
    int intValue() throws InvalidParameterTypeException;

    /**
     * Returns the boolean value of an operation parameter
     * A boolean value does not have an associated unit of measure.
     *
     * @return The boolean value represented by this parameter.
     * @throws InvalidParameterTypeException if the value is not a boolean type.
     * @UML conditional booleanValue
     *
     * @see #setValue(boolean)
     */
    boolean booleanValue() throws InvalidParameterTypeException;

    /**
     * Returns the string value of an operation parameter.
     * A string value does not have an associated unit of measure.
     *
     * @return The string value represented by this parameter.
     * @throws InvalidParameterTypeException if the value is not a string.
     * @UML conditional stringValue
     *
     * @see #getValue
     * @see #setValue(Object)
     */
    String stringValue() throws InvalidParameterTypeException;

    /**
     * Returns an ordered sequence of numeric values in the specified unit of measure.
     * This convenience method apply unit conversion on the fly as needed.
     *
     * @param  unit The unit of measure for the value to be returned.
     * @return The sequence of values represented by this parameter after conversion to type
     *         <code>double</code> and conversion to <code>unit</code>.
     * @throws InvalidParameterTypeException if the value is not an array of <code>double</code>s.
     * @throws IllegalArgumentException if the specified unit is invalid for this parameter.
     *
     * @see #getUnit
     * @see #setValue(double[],Unit)
     * @see #doubleValue(Unit)
     */
    double[] doubleValueList(final Unit unit) throws InvalidParameterTypeException;

    /**
     * Returns an ordered sequence of two or more numeric values of an operation parameter
     * list, where each value has the same associated {@linkplain Unit unit of measure}.
     *
     * @return The sequence of values represented by this parameter.
     * @throws InvalidParameterTypeException if the value is not an array of <code>double</code>s.
     * @UML conditional valueList
     * @unitof Measure
     *
     * @see #getUnit
     * @see #setValue(Object)
     * @see #doubleValue()
     *
     * @rename Renamed <code>valueList</code> as <code>doubleValueList</code> for consistency
     *         with {@link #doubleValue()}. Also because, like <code>doubleValue()</code>, this
     *         method returns a <code>double</code> value rather than a <code>Measure</code>
     *         object.
     */
    double[] doubleValueList() throws InvalidParameterTypeException;

    /**
     * Returns an ordered sequence of two or more integer values of an operation parameter list,
     * usually used for counts. These integer values do not have an associated unit of measure.
     *
     * @return The sequence of values represented by this parameter.
     * @throws InvalidParameterTypeException if the value is not an array of <code>int</code>s.
     * @UML conditional integerValueList
     *
     * @see #setValue(Object)
     * @see #intValue
     *
     * @rename Renamed <code>valueList</code> as <code>doubleValueList</code> for consistency
     *         with {@link #doubleValue()}. Also because, like <code>doubleValue()</code>, this
     *         method returns a <code>double</code> value rather than a <code>Measure</code>
     *         object.
     */
    int[] intValueList() throws InvalidParameterTypeException;

    /**
     * Returns a reference to a file or a part of a file containing one or more parameter
     * values. When referencing a part of a file, that file must contain multiple identified
     * parts, such as an XML encoded document. Furthermore, the referenced file or part of a
     * file can reference another part of the same or different files, as allowed in XML documents.
     *
     * @return The reference to a file containing parameter values.
     * @throws InvalidParameterTypeException if the value is not a reference to a file or an URL.
     * @UML conditional valueFile
     *
     * @see #getValue
     * @see #setValue(Object)
     */
    URL valueFile() throws InvalidParameterTypeException;

    /**
     * Returns the parameter value as an object. The object type is typically a {@link Double},
     * {@link Integer}, {@link Boolean}, {@link String}, {@link URL}, <code>double[]</code> or
     * <code>int[]</code>.
     *
     * @return The parameter value as an object.
     *
     * @see #setValue(Object)
     */
    Object getValue();

    /**
     * Set the parameter value as an array of floating point and their associated unit.
     *
     * @param  values The parameter values.
     * @param  unit The unit for the specified value.
     * @throws InvalidParameterValueException if the floating point type is inappropriate for this
     *         parameter, or if the value is illegal for some other reason (for example a value out
     *         of range).
     */
    void setValue(double[] values, Unit unit) throws InvalidParameterValueException;

    /**
     * Set the parameter value as a floating point and its associated unit.
     *
     * @param  value The parameter value.
     * @param  unit The unit for the specified value.
     * @throws InvalidParameterValueException if the floating point type is inappropriate for this
     *         parameter, or if the value is illegal for some other reason (for example a value out
     *         of range).
     *
     * @see #setValue(double)
     * @see #doubleValue(Unit)
     */
    void setValue(double value, Unit unit) throws InvalidParameterValueException;

    /**
     * Set the parameter value as a floating point.
     *
     * @param value The parameter value.
     * @throws InvalidParameterValueException if the floating point type is inappropriate for this
     *         parameter, or if the value is illegal for some other reason (for example a value out
     *         of range).
     *
     * @see #setValue(double,Unit)
     * @see #doubleValue()
     */
    void setValue(double value) throws InvalidParameterValueException;

    /**
     * Set the parameter value as an integer.
     *
     * @param  value The parameter value.
     * @throws InvalidParameterValueException if the integer type is inappropriate for this parameter,
     *         or if the value is illegal for some other reason (for example a value out of range).
     *
     * @see #intValue
     */
    void setValue(int value) throws InvalidParameterValueException;

    /**
     * Set the parameter value as a boolean.
     *
     * @param  value The parameter value.
     * @throws InvalidParameterValueException if the boolean type is inappropriate for this parameter.
     *
     * @see #booleanValue
     */
    void setValue(boolean value) throws InvalidParameterValueException;

    /**
     * Set the parameter value as an object. The object type is typically a {@link Double},
     * {@link Integer}, {@link Boolean}, {@link String}, {@link URL}, <code>double[]</code>
     * or <code>int[]</code>.
     *
     * @param  value The parameter value.
     * @throws InvalidParameterValueException if the type of <code>value</code> is inappropriate
     *         for this parameter, or if the value is illegal for some other reason (for example
     *         the value is numeric and out of range).
     *
     * @see #getValue
     */
    void setValue(Object value) throws InvalidParameterValueException;

    /**
     * Returns a copy of this parameter value.
     *
     * @return A copy of this parameter value.
     */
    /*{ParameterValue}*/ Object clone();
}
