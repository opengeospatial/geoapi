/*$************************************************************************************************
 **
 ** $Id$
 **
 ** $URL$
 **
 ** Copyright (C) 2003 Open GIS Consortium, Inc. All Rights Reserved. http://www.opengis.org/Legal/
 **
 *************************************************************************************************/
package org.opengis.parameter;


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
     * Returns the first parameter in this group for the specified name. If no
     * {@linkplain OperationParameter operation parameter} or group is found for
     * the given name, then this method search recursively in subgroups (if any).
     *
     * @param  name The case insensitive name of the parameter to search for.
     * @return The parameter for the given name.
     * @throws ParameterNotFoundException if there is no parameter for the given name.
     */
    GeneralOperationParameter getParameter(String name) throws ParameterNotFoundException;
}
