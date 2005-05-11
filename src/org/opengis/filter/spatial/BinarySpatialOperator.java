/*$************************************************************************************************
 **
 ** $Id$
 **
 ** $Source$
 **
 ** Copyright (C) 2003 Open GIS Consortium, Inc. All Rights Reserved. http://www.opengis.org/Legal/
 **
 *************************************************************************************************/
package org.opengis.filter.spatial;

// OpenGIS direct dependencies
import org.opengis.filter.expression.Expression;

// Annotations
import org.opengis.annotation.UML;
import org.opengis.annotation.XmlSchema;
import static org.opengis.annotation.Obligation.*;
import static org.opengis.annotation.Specification.*;


/**
 * Abstract superclass for filter operators that perform some sort of spatial
 * comparison on two geometric objects.
 *
 * @author <A HREF="http://www.opengis.org">OpenGIS&reg; consortium</A>
 * @version <A HREF="http://www.opengis.org/docs/02-059.pdf">Implementation specification 1.0</A>
 * @since 1.1
 */
@XmlSchema("http://schemas.opengis.net/filter/1.0.0/filter.xsd")
@UML(identifier="BinarySpatialOpType", specification=OGC_02_059)
public interface BinarySpatialOperator extends SpatialOperator {
    /**
     * Returns an expression that will be evaluated to determine the first
     * operand to the spatial predicate represented by this operator.  The
     * result of evaluating this expression must be a geometry object.
     */
    @UML(identifier="expression", obligation=MANDATORY, specification=OGC_02_059)
    Expression getExpression1();

    /**
     * Returns an expression that will be evaluated to determine the second
     * operand to the spatial predicate represented by this operator.  The
     * result of evaluating this expression must be a geometry object.
     */
    @UML(identifier="expression", obligation=MANDATORY, specification=OGC_02_059)
    Expression getExpression2();
}
