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
 * Abstract base class for the various filter expressions that compute some
 * value from two input values.
 *
 * @version <A HREF="http://www.opengis.org/docs/02-059.pdf">Implementation specification 1.0</A>
 * @author Chris Dillard (SYS Technologies)
 * @since GeoAPI 2.0
 */
@XmlElement("BinaryOperatorType")
public interface BinaryExpression extends Expression {
    /**
     * Returns the expression that represents the first (left) value that will
     * be used in the computation of another value.
     */
    @XmlElement("expression")
    Expression getExpression1();

    /**
     * The first of two expression to be cused in computation.
     * <p>
     * When parsing the BNF form of Expression this is the "left hand side".
     * </p>
     * @param expression first of two expressions compared 
     */
    void setExpression1( Expression expression);
    
    /**
     * Returns the expression that represents the second (right) value that will
     * be used in the computation of another value.
     */
    @XmlElement("expression")
    Expression getExpression2();
    
    /**
     * The second of two expression to be cused in computation.
     * <p>
     * When parsing the BNF form of Expression this is the "right hand side".
     * </p>
     * @param expression first of two expressions compared 
     */
    void setExpression2( Expression expression);
}
