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

// OpenGIS direct dependencies
import org.opengis.referencing.Info;


/**
 * Abstract definition of a parameter or group of parameters used by an operation method.
 *  
 * @UML abstract CC_GeneralOperationParameter
 * @author ISO 19111
 * @author <A HREF="http://www.opengis.org">OpenGIS&reg; consortium</A>
 * @version <A HREF="http://www.opengis.org/docs/03-073r1.zip">Abstract specification 2.0</A>
 *
 * @see GeneralParameterValue
 */
public interface GeneralOperationParameter extends Info {
    /**
     * Creates a new instance of {@linkplain GeneralParameterValue parameter value or group}
     * initialized with the {@linkplain OperationParameter#getDefaultValue default value(s)}.
     * The {@linkplain GeneralParameterValue#getDescriptor parameter value descriptor} for
     * the created parameter value(s) will be <code>this</code> object.
     */
    GeneralParameterValue createValue();

    /**
     * The minimum number of times that values for this parameter group or
     * parameter are required. The default value is one. A value of 0 means
     * an optional parameter.
     *
     * @return The minimum occurrences.
     * @UML optional minimumOccurs
     *
     * @see #getMaximumOccurs
     */
    int getMinimumOccurs();

    /**
     * The maximum number of times that values for this parameter group or
     * parameter can be included. The default value is one.
     *
     * @return The maximum occurrences.
     * @UML optional OperationParameterGroup.maximumOccurs
     *
     * @see #getMinimumOccurs
     */
    int getMaximumOccurs();
}
