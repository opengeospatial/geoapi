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
import org.opengis.parameter.ParameterValueGroup;

// Annotations
import org.opengis.annotation.UML;
import static org.opengis.annotation.Obligation.*;
import static org.opengis.annotation.Specification.*;


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
 *
 * @revisit OpenGIS is considering to remove this class from UML diagrams. In this case, all
 *          methods in this interface will move to the {@link SingleOperation} interface. The
 *          removal of {@code Operation} interface would simplify the API and brings more
 *          flexibility: {@link ConcatenatedOperation} would be a legal component in both
 *          other {@link ConcatenatedOperation} and in {@link PassThroughOperation}.
 */
@UML (identifier="CC_Operation", specification=ISO_19111)
public interface Operation extends SingleOperation {
    /**
     * Returns the operation method.
     *
     * @return The operation method.
     */
    @UML (identifier="usesMethod", obligation=MANDATORY, specification=ISO_19111)
    OperationMethod getMethod();

    /**
     * Returns the parameter values.
     *
     * @return The parameter values, or an empty array if none.
     *
     * @rename Added "<code>Parameter</code>" prefix for more consistency with the return type.
     */
    @UML (identifier="usesValue", obligation=MANDATORY, specification=ISO_19111)
    ParameterValueGroup getParameterValues();
}
