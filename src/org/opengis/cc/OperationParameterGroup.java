/*
 * Copyright (C) 2003 Open GIS Consortium, Inc. All Rights Reserved. http://www.opengis.org/Legal/
 */
package org.opengis.cc;

// OpenGIS direct dependencies
import org.opengis.rs.Info;


/**
 * The definition of a group of related parameters used by an operation method.
 *  
 * @UML abstract CC_OperationParameterGroup
 * @author ISO 19111
 * @author <A HREF="http://www.opengis.org">OpenGIS&reg; consortium</A>
 * @version 2.0
 */
public interface OperationParameterGroup extends GeneralOperationParameter, Info {
    /**
     * Returns the parameters in this group.
     *
     * @return The parameters.
     */
    public GeneralOperationParameter[] getParameters();

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
}
