/*$************************************************************************************************
 **
 ** $Id$
 **
 ** $URL$
 **
 ** Copyright (C) 2003 Open GIS Consortium, Inc. All Rights Reserved. http://www.opengis.org/Legal/
 **
 *************************************************************************************************/
package org.opengis.referencing.operation;

// OpenGIS direct dependencies
import org.opengis.parameter.GeneralParameterValue;


/**
 * A parameterized mathematical operation on coordinates that transforms or converts
 * coordinates to another coordinate reference system. This coordinate operation thus
 * uses an operation method, usually with associated parameter values.
 *  
 * @UML abstract CC_Operation
 * @author ISO 19111
 * @author <A HREF="http://www.opengis.org">OpenGIS&reg; consortium</A>
 * @version <A HREF="http://www.opengis.org/docs/03-073r1.zip">Abstract specification 2.0</A>
 *
 * @see OperationMethod
 */
public interface Operation extends SingleOperation {
    /**
     * Returns the operation method.
     *
     * @return The operation method.
     * @UML association usesMethod
     */
    OperationMethod getMethod();

    /**
     * Returns the parameter values.
     *
     * @return The parameter values, or an empty array if none.
     * @UML association usesValue
     *
     * @rename Added "<code>Parameter</code>" prefix for more consistency with the return type.
     */
    GeneralParameterValue[] getParameterValues();
}
