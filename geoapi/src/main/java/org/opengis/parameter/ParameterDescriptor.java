/*$************************************************************************************************
 **
 ** $Id$
 **
 ** $URL$
 **
 ** Copyright (C) 2003-2005 Open GIS Consortium, Inc.
 ** All Rights Reserved. http://www.opengis.org/legal/
 **
 *************************************************************************************************/
package org.opengis.parameter;

import java.util.Set;
import javax.units.Unit;
import org.opengis.annotation.UML;
import org.opengis.annotation.Extension;

import static org.opengis.annotation.Obligation.*;
import static org.opengis.annotation.Specification.*;


/**
 * The definition of a parameter used by an operation method. Most parameter values are
 * numeric, but other types of parameter values are possible.
 *  
 * @version <A HREF="http://portal.opengeospatial.org/files/?artifact_id=6716">Abstract specification 2.0</A>
 * @author ISO/DIS 19111
 * @author Martin Desruisseaux (IRD)
 * @author Jody Garnett (Refractions Research)
 * @since GeoAPI 2.0
 *
 * @see ParameterValue
 * @see ParameterDescriptorGroup
 */
@UML(identifier="CC_OperationParameter", specification=ISO_19111)
public interface ParameterDescriptor extends GeneralParameterDescriptor {
    /**
     * Creates a new instance of {@linkplain ParameterValue parameter value}
     * initialized with the {@linkplain #getDefaultValue default value}.
     * The {@linkplain ParameterValue#getDescriptor parameter value descriptor}
     * for the created parameter value will be {@code this} object.
     */
/// @Extension
/// ParameterValue createValue();

    /**
     * Returns the class that describe the type of the parameter.
     */
    @UML(identifier="GC_ParameterInfo.type", obligation=MANDATORY, specification=ISO_19111)
    Class getValueClass();

    /**
     * If this parameter allows only a finite set of values, returns this set.
     * This set is usually a {linkplain org.opengis.util.CodeList code list} or
     * enumerations. This method returns {@code null} if this parameter
     * doesn't limits values to a finite set.
     * <p>
     * Note even when CodeList is used one can use this set to allow only a
     * subset of the permissable values provided by the CodeList.
     * </p>
     * <p>
     * When the getValueClass() is an array or Collection getValidValues()
     * may be used to constrain the contained elements.
     * </p>
     * @return A finite set of valid values (usually from a
     *         {linkplain org.opengis.util.CodeList code list}),
     *         or {@code null} if it doesn't apply.
     */
    @Extension
    Set<? extends Object> getValidValues();

    /**
     * Returns the default value for the parameter. The return type can be any type
     * including a {@link Number} or a {@link String}. If there is no default value,
     * then this method returns {@code null}.
     *
     * @return The default value, or {@code null} in none.
     */
    @UML(identifier="GC_ParameterInfo.defaultValue", obligation=OPTIONAL, specification=ISO_19111)
    Object getDefaultValue();

    /**
     * Returns the minimum parameter value.
     * 
     * If there is no minimum value, or if minimum
     * value is inappropriate for the {@linkplain #getValueClass parameter type}, then
     * this method returns {@code null}.
     * <p>
     * When the getValueClass() is an array or Collection getMinimumValue
     * may be used to constrain the contained elements.
     * </p>
     * @return The minimum parameter value (often an instance of {@link Double}), or {@code null}.
     */
    @UML(identifier="GC_ParameterInfo.minimumValue", obligation=OPTIONAL, specification=ISO_19111)
    Comparable getMinimumValue();

    /**
     * Returns the maximum parameter value.
     * 
     * If there is no maximum value, or if maximum
     * value is inappropriate for the {@linkplain #getValueClass parameter type}, then
     * this method returns {@code null}.
     * <p>
     * When the getValueClass() is an array or Collection getMaximumValue
     * may be used to constratin the contained elements.
     * </p>
     * @return The minimum parameter value (often an instance of {@link Double}), or {@code null}.
     */
    @UML(identifier="GC_ParameterInfo.maximumValue", obligation=OPTIONAL, specification=ISO_19111)
    Comparable getMaximumValue();

    /**
     * Returns the unit for
     * {@linkplain #getDefaultValue default},
     * {@linkplain #getMinimumValue minimum} and
     * {@linkplain #getMaximumValue maximum} values.
     * This attribute apply only if the values is of numeric type (usually an instance
     * of {@link Double}).
     *
     * @return The unit for numeric value, or {@code null} if it doesn't apply to the value type.
     */
    @Extension
    Unit getUnit();
}
