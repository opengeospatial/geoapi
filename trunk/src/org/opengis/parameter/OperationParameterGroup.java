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

// OpenGIS dependencies
import org.opengis.referencing.Identifier;  // For javadoc


/**
 * The definition of a group of related parameters used by an operation method.
 *  
 * @UML abstract CC_OperationParameterGroup
 * @author ISO 19111
 * @author <A HREF="http://www.opengis.org">OpenGIS&reg; consortium</A>
 * @version <A HREF="http://www.opengis.org/docs/03-073r1.zip">Abstract specification 2.0</A>
 *
 * @see ParameterValueGroup
 * @see OperationParameter
 */
public interface OperationParameterGroup extends GeneralOperationParameter {
    /**
     * Creates a new instance of {@linkplain ParameterValueGroup parameter value group}
     * initialized with the {@linkplain OperationParameter#getDefaultValue default values}.
     * The {@linkplain ParameterValueGroup#getDescriptor parameter value descriptor}
     * for the created group will be <code>this</code> object.
     */
/// ParameterValueGroup createValue();

    /**
     * Returns the parameters in this group.
     *
     * @return The parameters.
     * @UML association includesParameter
     */
    GeneralOperationParameter[] getParameters();

    /**
     * Returns the first parameter in this group for the specified {@linkplain Identifier#getCode
     * identifier code}. If no {@linkplain OperationParameter operation parameter} is found for
     * the given code, then this method search recursively in subgroups (if any). This convenience
     * method provides a way to get and set parameter information by name. For example the following
     * idiom fetches the default value for the <code>"false_easting"</code> parameter:
     * <br><br>
     * <blockquote><code>
     * Object defaultValue = getParameter("false_easting").{@linkplain OperationParameter#getDefaultValue() getDefaultValue()};
     * </code></blockquote>
     *
     * @param  name The case insensitive {@linkplain Identifier#getCode identifier code} of the
     *              parameter to search for. If this string contains the <code>':'</code> character,
     *              then the part before <code>':'</code> is the {@linkplain Identifier#getCodeSpace
     *              code space}.
     * @return The parameter for the given identifier code.
     * @throws ParameterNotFoundException if there is no parameter for the given identifier code.
     */
    OperationParameter getParameter(String name) throws ParameterNotFoundException;
}
