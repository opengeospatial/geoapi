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
 * A compact way of encoding a range check.
 * <p>
 * The lower and upper boundary values are inclusive.
 * </p>
 * @version <A HREF="http://www.opengis.org/docs/02-059.pdf">Implementation specification 1.0</A>
 * @author Chris Dillard (SYS Technologies)
 * @since GeoAPI 2.0
 */
@XmlElement("PropertyIsBetween")
public interface PropertyIsBetween extends Filter {
    /**
     * Returns the expression to be compared by this operator.
     */
    @XmlElement("expression")
    Expression getExpression();

    /**
     * Expression to be compared by this operator.
     * 
     * @param expression value to be compared 
     */
    void setExpression( Expression expression);
    
    /**
     * Returns the lower bounds (inclusive) an an expression.
     */
    @XmlElement("LowerBoundary")
    Expression getLowerBoundary();

    /**
     * Lower bounds (inclusive) of an expression
     * 
     * @param lowerBounds Expression of the lower bounds
     */
    void setLowerBoundary( Expression lowerBounds );
    
    /**
     * Returns the upper bounds (inclusive) as an expression.
     */
    @XmlElement("UpperBoundary")
    Expression getUpperBoundary();
    
    /**
     * Upper bounds (inclusive) as an expression
     * 
     * @param upperBounds Expression of the upper bounds
     */
    void setUpperBoundary( Expression upperBounds);
}
