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
package org.opengis.filter;

// OpenGIS direct dependencies
import org.opengis.annotation.XmlElement;
import org.opengis.filter.expression.Expression;


/**
 * Filter operator that checks if an expression's value is {@code null}.  A {@code null}
 * is equivalent to no value present. The value 0 is a valid value and is not considered
 * {@code null}.
 *
 * @version <A HREF="http://www.opengis.org/docs/02-059.pdf">Implementation specification 1.0</A>
 * @author Chris Dillard (SYS Technologies)
 * @since GeoAPI 2.0
 */
@XmlElement("PropertyIsNull")
public interface PropertyIsNull extends Filter {
    /**
     * Returns the expression whose value will be checked for {@code null}.
     */
    @XmlElement("PropertyName")
    Expression getExpression();
    
    /**
     * Expression whose value will be checked for {code null}
     * 
     * @param expression To be checked for {code null}
     */
    void setExpression( Expression expression );
}
