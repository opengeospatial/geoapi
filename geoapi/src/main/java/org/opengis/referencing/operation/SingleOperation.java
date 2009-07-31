/*$************************************************************************************************
 **
 ** $Id$
 **
 ** $URL$
 **
 ** Copyright (C) 2003-2005 Open GIS Consortium, Inc.
 ** All Rights Reserved. http://www.opengis.org/legal/
 **
 *************************************************************************************************/
package org.opengis.referencing.operation;

import org.opengis.parameter.ParameterValueGroup;
import org.opengis.annotation.UML;

import static org.opengis.annotation.Obligation.*;
import static org.opengis.annotation.Specification.*;


/**
 * A parameterized mathematical operation on coordinates that transforms or converts
 * coordinates to another coordinate reference system. This coordinate operation thus
 * uses an operation method, usually with associated parameter values. This is a
 * single (not {@linkplain ConcatenatedOperation concatenated}) coordinate operation.
 *
 * @version <A HREF="http://portal.opengeospatial.org/files/?artifact_id=6716">Abstract specification 2.0</A>
 * @author  Martin Desruisseaux (IRD)
 * @since   GeoAPI 1.0
 *
 * @navassoc 1 - - OperationMethod
 * @navassoc 1 - - ParameterValueGroup
 */
@UML(identifier="CC_SingleOperation", specification=ISO_19111)
public interface SingleOperation extends CoordinateOperation {
    /**
     * Returns the operation method.
     *
     * @return The operation method.
     *
     * @since GeoAPI 2.3
     */
    @UML(identifier="method", obligation=MANDATORY, specification=ISO_19111)
    OperationMethod getMethod();

    /**
     * Returns the parameter values.
     *
     * @return The parameter values.
     *
     * @since GeoAPI 2.3
     */
    @UML(identifier="parameterValue", obligation=MANDATORY, specification=ISO_19111)
    ParameterValueGroup getParameterValues();
}
