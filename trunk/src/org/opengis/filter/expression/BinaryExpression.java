package org.opengis.filter.expression;

public interface BinaryExpression extends Expression {
    public Expression getExpression1();
    public void setExpression1(Expression expr);

    public Expression getExpression2();
    public void setExpression2(Expression expr);
}
