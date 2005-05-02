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
import org.opengis.annotation.XmlSchema;


/**
 * Instances of this interface provide a constant, literal value that can be
 * used in expressions.  The {@link #evaluate evaluate} method of this class
 * must return the same value as {@link #getValue()}.
 *
 * @author <A HREF="http://www.opengis.org">OpenGIS&reg; consortium</A>
 * @version <A HREF="http://www.opengis.org/docs/02-059.pdf">Filter encoding implementation specification 1.0</A>
 * @since 1.1
 */
@XmlSchema(URL="filter.xsd", element="Literal")
public interface Literal extends Expression {
    /**
     * Returns the constant value held by this object.
     */
    Object getValue();
}
