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
 * The definition of a group of related parameters used by an operation method.
 *  
 * @UML abstract CC_OperationParameterGroup
 * @author ISO 19111
 * @author <A HREF="http://www.opengis.org">OpenGIS&reg; consortium</A>
 * @version 2.0
 *
 * @see ParameterValueGroup
 * @see OperationParameter
 */
public interface OperationParameterGroup extends GeneralOperationParameter {
    /**
     * The maximum number of times that values for this parameter group or
     * parameter can be included. The default value is one.
     *
     * @return The maximum occurrences.
     * @UML optional maximumOccurs
     *
     * @see #getMinimumOccurs
     *
     * @revisit Why this method is not declared in the same interface than
     *          {@link #getMinimumOccurs}?
     */
    public int getMaximumOccurs();

    /**
     * Returns the parameters in this group.
     *
     * @return The parameters.
     * @UML association includesParameter
     */
    public GeneralOperationParameter[] getParameters();

    /**
     * Returns the first parameter in this group for the specified name. If no
     * {@linkplain OperationParameter operation parameter} or group is found for
     * the given name, then this method search recursively in subgroups (if any).
     *
     * @param  name The case insensitive name of the parameter to search for.
     * @return The parameter for the given name.
     * @throws InvalidParameterNameException if there is no parameter for the given name.
     */
    public GeneralOperationParameter getParameter(String name) throws InvalidParameterNameException;
}
