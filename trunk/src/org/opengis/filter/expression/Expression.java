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

// OpenGIS direct dependencies
import org.opengis.feature.Feature;

// Annotations
import org.opengis.annotation.UML;
import org.opengis.annotation.XmlSchema;
import org.opengis.annotation.Extension;
import static org.opengis.annotation.Specification.*;


/**
 * Abstract super-interface for all the OGC Filter elements that compute values,
 * potentially using {@linkplain Feature feature} attributes in the computation.
 *
 * @author <A HREF="http://www.opengis.org">OpenGIS&reg; consortium</A>
 * @version <A HREF="http://www.opengis.org/docs/02-059.pdf">Implementation specification 1.0</A>
 * @since 1.1
 */
@XmlSchema("http://schemas.opengis.net/filter/1.0.0/filter.xsd")
@UML(identifier="expression", specification=OGC_02_059)
public interface Expression {
    /**
     * Evaluates the given expression based on the content of the given feature.
     */
    @Extension
    Object evaluate(Feature feature);

    /**
     * Accepts a visitor. Subclasses must implement with a method whose content* is the following:
     * <pre>return visitor.{@linkplain ExpressionVisitor#visit visit}(this, extraData);</pre>
     */
    @Extension
    Object accept(ExpressionVisitor visitor, Object extraData);
}
