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
 * Abstract base class for filters that compare exactly two values against each
 * other.  The nature of the comparison is dependent on the subclass.
 *
 * @version <A HREF="http://www.opengis.org/docs/02-059.pdf">Implementation specification 1.0</A>
 * @author Chris Dillard (SYS Technologies)
 * @since GeoAPI 2.0
 */
@XmlElement("BinaryComparisonOpType")
public interface BinaryComparisonOperator extends Filter {
    /**
     * Returns the first of the two expressions to be compared by this operator.
     */
    @XmlElement("expression")
    Expression getExpression1();
    
    /**
     * The first of two expression to be compared by this operator.
     * <p>
     * When parsing the BNF form of Expression this is the "left hand side".
     * </p>
     * @param expression first of two expressions compared 
     */
    void setExpression1( Expression expression);

    /**
     * Returns the second of the two expressions to be compared by this operator.
     */
    @XmlElement("expression")
    Expression getExpression2();
    
    /**
     * The second of two expression to be compared by this operator.
     * <p>
     * When parsing the BNF form of Expression this is the "right hand side".
     * </p>
     * @param expression second of two expressions compared 
     */
    void setExpression2( Expression expression);
}
