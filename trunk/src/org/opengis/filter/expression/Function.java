/*$************************************************************************************************
 **
 ** $Id$
 **
 ** $Source$
 **
 ** Copyright (C) 2003 Open GIS Consortium, Inc. All Rights Reserved. http://www.opengis.org/Legal/
 **
 *************************************************************************************************/
package org.opengis.filter.expression;

// Annotations
import org.opengis.annotation.UML;
import org.opengis.annotation.XmlSchema;
import static org.opengis.annotation.Specification.*;


/**
 * Instances of this class represent a function call into some implementation-specific
 * function.  This is included for completeness with respect to the
 * OGC Filter specification.  However, no functions are required to be supported
 * by that specification.
 *
 * @author <A HREF="http://www.opengis.org">OpenGIS&reg; consortium</A>
 * @version <A HREF="http://www.opengis.org/docs/02-059.pdf">Implementation specification 1.0</A>
 * @since 1.1
 */
@UML(identifier="Function", specification=OGC_02_059)
public interface Function extends Expression {
    /**
     * Returns the name of the function to be called.  For example, this might
     * be "{@code cos}" or "{@code atan2}".
     */
    String getName();

    /**
     * Returns the subexpressions that will be evaluated to provide the
     * parameters to the function.
     */
    Expression[] getParameters();
}
