package org.opengis.filter.expression;

public interface ExpressionVisitor {
    public Object visit(Add          expression, Object extraData);
    public Object visit(Divide       expression, Object extraData);
    public Object visit(Function     expression, Object extraData);
    public Object visit(Literal      expression, Object extraData);
    public Object visit(Multiply     expression, Object extraData);
    public Object visit(PropertyName expression, Object extraData);
    public Object visit(Subtract     expression, Object extraData);
}
