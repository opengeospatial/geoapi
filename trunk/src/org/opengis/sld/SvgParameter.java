/*
 * Created on Aug 3, 2004
 */
package org.opengis.sld;

import org.opengis.filter.expression.Expression;

/**
 * Not finished: Needs setters and class documentation.
 */
public interface SvgParameter {
    /**
     * Returns the parameter whose value is either contained or computed by this
     * node.
     */
    public String getParameterName();

    /**
     * Returns the text content of this element.
     * If this element has child elements, this must return null.
     */
    public String getLiteralContent();

    /**
     * Returns the child expression that should be evaluated to compute the
     * value for this object.
     */
    public Expression getExpression();
}
