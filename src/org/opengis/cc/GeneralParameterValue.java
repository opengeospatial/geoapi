/*
 * Copyright (C) 2003 Open GIS Consortium, Inc. All Rights Reserved. http://www.opengis.org/Legal/
 */
package org.opengis.cc;

// OpenGIS direct dependencies
import org.opengis.util.Cloneable;


/**
 * Abstract parameter value or group of parameter values.
 *  
 * @UML abstract CC_GeneralParameterValue
 * @author ISO 19111
 * @author <A HREF="http://www.opengis.org">OpenGIS&reg; consortium</A>
 * @version 2.0
 *
 * @see GeneralOperationParameter
 */
public interface GeneralParameterValue extends Cloneable {
    /**
     * Returns the abstract definition of this parameter or group of parameters.
     *
     * @return The abstract definition of this parameter or group of parameters.
     */
    public GeneralOperationParameter getDescriptor();

    /**
     * Returns a copy of this parameter value or group.
     *
     * @return A copy of this parameter value or group.
     */
    public GeneralParameterValue clone();
}
