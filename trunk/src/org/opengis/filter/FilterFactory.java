package org.opengis.filter;

import org.opengis.filter.expression.Add;
import org.opengis.filter.expression.Divide;
import org.opengis.filter.expression.Expression;
import org.opengis.filter.expression.Function;
import org.opengis.filter.expression.Literal;
import org.opengis.filter.expression.Multiply;
import org.opengis.filter.expression.PropertyName;
import org.opengis.filter.expression.Subtract;
import org.opengis.filter.spatial.BBOX;
import org.opengis.filter.spatial.Beyond;
import org.opengis.filter.spatial.Contains;
import org.opengis.filter.spatial.Crosses;
import org.opengis.filter.spatial.DWithin;
import org.opengis.filter.spatial.Disjoint;
import org.opengis.filter.spatial.Equals;
import org.opengis.filter.spatial.Intersects;
import org.opengis.filter.spatial.Overlaps;
import org.opengis.filter.spatial.Touches;
import org.opengis.filter.spatial.Within;
import org.opengis.spatialschema.geometry.Geometry;

/**
 * Interface whose methods allow the caller to create instances of the various
 * <code>Filter</code> and <code>Expression</code> subclasses.
 */
public interface FilterFactory {
////////////////////////////////////////////////////////////////////////////////
//
//  FILTERS
//
////////////////////////////////////////////////////////////////////////////////

    public And  and(Filter f, Filter g);
    public And  and(Filter [] f);
    public Or   or(Filter f, Filter g);
    public Or   or(Filter [] f);
    public Not  not(Filter f);

    public FeatureId     featureId(String [] ids);
    public PropertyName  property(String name);

    public PropertyIsBetween               between(Expression expr, Expression lower, Expression upper);
    public PropertyIsEqualTo               equals(Expression expr1, Expression expr2);
    public PropertyIsGreaterThan           greater(Expression expr1, Expression expr2);
    public PropertyIsGreaterThanOrEqualTo  greaterOrEqual(Expression expr1, Expression expr2);
    public PropertyIsLessThan              less(Expression expr1, Expression expr2);
    public PropertyIsLessThanOrEqualTo     lessOrEqual(Expression expr1, Expression expr2);
    public PropertyIsLike                  like(String propName, String pattern);
    public PropertyIsLike                  like(String propName, String pattern, String wildcard, String singleChar, String escape);
    public PropertyIsNull                  propIsNull(String propName);

////////////////////////////////////////////////////////////////////////////////
//
//  SPATIAL FILTERS
//
////////////////////////////////////////////////////////////////////////////////

    public BBOX        bbox(String propertyName, double minx, double miny, double maxx, double maxy, String srs);
    public Beyond      beyond(String propertyName, Geometry geometry, double distance, String units);
    public Contains    contains(String propertyName, Geometry geometry);
    public Crosses     crosses(String propertyName, Geometry geometry);
    public Disjoint    disjoint(String propertyName, Geometry geometry);
    public DWithin     dwithin(String propertyName, Geometry geometry, double distance, String units);
    public Equals      equals(String propertyName, Geometry geometry);
    public Intersects  intersects(String propertyName, Geometry geometry);
    public Overlaps    overlaps(String propertyName, Geometry geometry);
    public Touches     touches(String propertyName, Geometry geometry);
    public Within      within(String propertyName, Geometry geometry);

////////////////////////////////////////////////////////////////////////////////
//
//  EXPRESSIONS
//
////////////////////////////////////////////////////////////////////////////////

    public Add       add(Expression expr1, Expression expr2);
    public Divide    divide(Expression expr1, Expression expr2);
    public Multiply  multiply(Expression expr1, Expression expr2);
    public Subtract  subtract(Expression expr1, Expression expr2);

    public Function  function(String name, Expression[] args);
    public Function  function(String name, Expression arg1);
    public Function  function(String name, Expression arg1, Expression arg2);
    public Function  function(String name, Expression arg1, Expression arg2, Expression arg3);

    public Literal  literal(Object obj);
    public Literal  literal(byte b);
    public Literal  literal(short s);
    public Literal  literal(int i);
    public Literal  literal(long l);
    public Literal  literal(float f);
    public Literal  literal(double d);
    public Literal  literal(char c);
    public Literal  literal(boolean b);
}
