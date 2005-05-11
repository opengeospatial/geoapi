/*$************************************************************************************************
 **
 ** $Id$
 **
 ** $Source$
 **
 ** Copyright (C) 2003 Open GIS Consortium, Inc. All Rights Reserved. http://www.opengis.org/Legal/
 **
 *************************************************************************************************/
package org.opengis.filter;

// OpenGIS direct dependencies
import org.opengis.filter.expression.Expression;

// Annotations
import org.opengis.annotation.UML;
import org.opengis.annotation.XmlSchema;
import static org.opengis.annotation.Obligation.*;
import static org.opengis.annotation.Specification.*;


/**
 * A compact way of encoding a range check. The lower and upper boundary values are inclusive.
 *
 * @author <A HREF="http://www.opengis.org">OpenGIS&reg; consortium</A>
 * @version <A HREF="http://www.opengis.org/docs/02-059.pdf">Implementation specification 1.0</A>
 * @since 1.1
 */
@XmlSchema("http://schemas.opengis.net/filter/1.0.0/filter.xsd")
@UML(identifier="PropertyIsBetween", specification=OGC_02_059)
public interface PropertyIsBetween extends Filter {
    /**
     * Returns the expression to be compared by this operator.
     */
    @UML(identifier="expression", obligation=MANDATORY, specification=OGC_02_059)
    Expression getExpression();

    /**
     * Returns the lower bounds (inclusive) an an expression.
     */
    @UML(identifier="LowerBoundary", obligation=MANDATORY, specification=OGC_02_059)
    Expression getLowerBoundary();

    /**
     * Returns the upper bounds (inclusive) as an expression.
     */
    @UML(identifier="UpperBoundary", obligation=MANDATORY, specification=OGC_02_059)
    Expression getUpperBoundary();
}
