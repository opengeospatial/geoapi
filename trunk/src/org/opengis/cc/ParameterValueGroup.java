/*
 * Copyright (C) 2003 Open GIS Consortium, Inc. All Rights Reserved. http://www.opengis.org/Legal/
 */
package org.opengis.cc;


/**
 * A group of related parameter values. The same group can be repeated more than once in an
 * {@link Operation} or higher level <code>ParameterValueGroup</code>, if those instances
 * contain different values of one or more <code>ParameterValue</code>s which suitably
 * distinquish among those groups.
 *  
 * @UML abstract CC_ParameterValueGroup
 * @author ISO 19111
 * @author <A HREF="http://www.opengis.org">OpenGIS&reg; consortium</A>
 * @version 2.0
 */
public interface ParameterValueGroup extends GeneralParameterValue {
    /**
     * Returns the group this value belong to.
     *
     * @return The group.
     * @UML association valuesOfGroup
     *
     * @revisit The name may not be really appropriate. Idealy, we would need a name applicable
     *          to a plain {@link GeneralOperationParameter} as well, and add the method in
     *          {@link GeneralParameterValue}.
     */
    public OperationParameterGroup getGroup();

    /**
     * Returns the value in this group.
     *
     * @return The values.
     * @UML association includesValue
     */
    public GeneralParameterValue[] getValues();
}
