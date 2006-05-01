/*$************************************************************************************************
 **
 ** $Id$
 **
 ** $URL$
 **
 ** Copyright (C) 2005 Open GIS Consortium, Inc.
 ** All Rights Reserved. http://www.opengis.org/legal/
 **
 *************************************************************************************************/
package org.opengis.filter.expression;

// Annotations
import org.opengis.annotation.XmlElement;


/**
 * Instances of this interface provide a constant, literal value that can be
 * used in expressions.  The {@link #evaluate evaluate} method of this class
 * must return the same value as {@link #getValue()}.
 *
 * @version <A HREF="http://www.opengis.org/docs/02-059.pdf">Implementation specification 1.0</A>
 * @author Chris Dillard (SYS Technologies)
 * @since GeoAPI 2.0
 */
@XmlElement("Literal")
public interface Literal extends Expression {
    /**
     * Returns the constant value held by this object.
     */
    Object getValue();
}
