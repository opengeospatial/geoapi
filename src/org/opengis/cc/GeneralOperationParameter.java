/*
 * Copyright (C) 2003 Open GIS Consortium, Inc. All Rights Reserved. http://www.opengis.org/Legal/
 */
package org.opengis.cc;


/**
 * Abstract definition of a parameter or group of parameters used by an operation method.
 *  
 * @UML abstract CC_GeneralOperationParameter
 * @author ISO 19111
 * @author <A HREF="http://www.opengis.org">OpenGIS&reg; consortium</A>
 * @version 2.0
 *
 * @see GeneralParameterValue
 */
public interface GeneralOperationParameter {
    /**
     * The minimum number of times that values for this parameter group or
     * parameter are required. The default value is one.
     *
     * @return The minimum occurrences.
     * @UML optional minimumOccurs
     *
     * @see OperationParameterGroup#getMaximumOccurs
     */
    public int getMinimumOccurs();
}
