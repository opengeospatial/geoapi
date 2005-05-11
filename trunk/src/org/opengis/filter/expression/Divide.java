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
 * Encodes the operation of division where the first argument is divided by the second argument.
 * Instances of this interface implement their {@link #evaluate evaluate} method by
 * computing the numeric quotient resulting from dividing the {@linkplain #getExpression1 first}
 * operand by the {@linkplain #getExpression2 second}. The second argument or expression cannot
 * evaluate to zero.
 *
 * @author <A HREF="http://www.opengis.org">OpenGIS&reg; consortium</A>
 * @version <A HREF="http://www.opengis.org/docs/02-059.pdf">Implementation specification 1.0</A>
 * @since 1.1
 */
@XmlSchema("http://schemas.opengis.net/filter/1.0.0/filter.xsd")
@UML(identifier="Div", specification=OGC_02_059)
public interface Divide extends BinaryExpression {
}
