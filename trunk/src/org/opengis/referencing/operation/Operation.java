/*$************************************************************************************************
 **
 ** $Id$
 **
 ** $Source$
 **
 ** Copyright (C) 2003 Open GIS Consortium, Inc. All Rights Reserved. http://www.opengis.org/Legal/
 **
 *************************************************************************************************/
package org.opengis.referencing.operation;

// OpenGIS direct dependencies
import org.opengis.parameter.GeneralParameterValue;

// Annotations
///import org.opengis.annotation.UML;
///import static org.opengis.annotation.Obligation.*;


/**
 * A parameterized mathematical operation on coordinates that transforms or converts
 * coordinates to another coordinate reference system. This coordinate operation thus
 * uses an operation method, usually with associated parameter values.
 *  
 * @author ISO 19111
 * @author <A HREF="http://www.opengis.org">OpenGIS&reg; consortium</A>
 * @version <A HREF="http://www.opengis.org/docs/03-073r1.zip">Abstract specification 2.0</A>
 *
 * @see OperationMethod
 */
///@UML (identifier="CC_Operation")
public interface Operation extends SingleOperation {
    /**
     * Returns the operation method.
     *
     * @return The operation method.
     */
/// @UML (identifier="usesMethod", obligation=MANDATORY)
    OperationMethod getMethod();

    /**
     * Returns the parameter values.
     *
     * @return The parameter values, or an empty array if none.
     *
     * @rename Added "<code>Parameter</code>" prefix for more consistency with the return type.
     *
     * @revisit Change the return type to <code>ParameterValueGroup</code>
     *          for consistency with the rest of the API (this is the only
     *          method to return an array).
     */
/// @UML (identifier="usesValue", obligation=MANDATORY)
    GeneralParameterValue[] getParameterValues();
}
