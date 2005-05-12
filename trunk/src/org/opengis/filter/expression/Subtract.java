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
import org.opengis.annotation.XmlElement;


/**
 * Encodes the operation of subtraction where the second argument is subtracted from the first.
 * Instances of this interface implement their {@link #evaluate evaluate} method by
 * computing the numeric difference between the {@linkplain #getExpression1 first} and
 * {@linkplain #getExpression2 second} operand.
 *
 * @author <A HREF="http://www.opengis.org">OpenGIS&reg; consortium</A>
 * @version <A HREF="http://www.opengis.org/docs/02-059.pdf">Implementation specification 1.0</A>
 * @since GeoAPI 1.1
 */
@XmlElement("Sub")
public interface Subtract extends BinaryExpression {
}
