/*
 * Copyright (C) 2003 Open GIS Consortium, Inc. All Rights Reserved. http://www.opengis.org/Legal/
 */
package org.opengis.cc;


/**
 * A group of related parameter values. The same group can be repeated more than once in an
 * {@link Operation} or higher level <code>ParameterValueGroup</code>, if those instances
 * contain different values of one or more {@link ParameterValue}s which suitably
 * distinquish among those groups.
 *  
 * @UML abstract CC_ParameterValueGroup
 * @author ISO 19111
 * @author <A HREF="http://www.opengis.org">OpenGIS&reg; consortium</A>
 * @version 2.0
 *
 * @see OperationParameterGroup
 * @see ParameterValue
 */
public interface ParameterValueGroup extends GeneralParameterValue {
    /**
     * Returns the group this value belong to.
     *
     * @return The abstract definition of this group of parameters.
     * @UML association valuesOfGroup
     *
     * @rename Renamed <CODE>getDescriptor()</CODE> because <CODE>getGroup()</CODE> seems too
     *         restrictive, misleading (this method returns an abstract definition of a group
     *         of parameters, not the actual group), and for consistency with usage in other
     *         Java extensions (e.g.
     *         {@link javax.media.jai.ParameterList.html#getParameterListDescriptor ParameterList}).
     */
    public OperationParameterGroup getDescriptor();

    /**
     * Returns the values in this group.
     *
     * @return The values.
     * @UML association includesValue
     */
    public GeneralParameterValue[] getValues();

    /**
     * Returns the first value in this group for the specified name. If no
     * {@linkplain ParameterValue parameter value} or group is found for the
     * given name, then this method search recursively in subgroups (if any).
     *
     * @param  name The parameter to search for.
     * @return The parameter value for the given name.
     * @throws IllegalArgumentException if there is no parameter for the given name.
     */
    public GeneralParameterValue getValue(String name) throws IllegalArgumentException;

    /**
     * Returns a copy of this group of parameter values.
     * Included parameter values and subgroups are cloned recursively.
     *
     * @return A copy of this group of parameter values.
     */
    public ParameterValueGroup clone();
}
