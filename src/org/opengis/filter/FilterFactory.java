package org.opengis.filter;

import org.opengis.filter.expression.Expression;

public interface FilterFactory {
    public Filter createFilter(Class interfaceToMakeAnInstanceOf);
    public Expression createExpression(Class interfaceToMakeAnInstanceOf);
}
