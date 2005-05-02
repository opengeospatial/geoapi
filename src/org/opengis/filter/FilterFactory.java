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

// J2SE direct dependencies
import java.util.List;

// OpenGIS direct dependencies
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
 * {@link Filter} and {@link Expression} subclasses.
 *
 * @author <A HREF="http://www.opengis.org">OpenGIS&reg; consortium</A>
 * @version <A HREF="http://www.opengis.org/docs/02-059.pdf">Filter encoding implementation specification 1.0</A>
 * @since 1.1
 */
public interface FilterFactory {
////////////////////////////////////////////////////////////////////////////////
//
//  FILTERS
//
////////////////////////////////////////////////////////////////////////////////

    And and(Filter f, Filter g);
    And and(List<Filter> f);
    Or  or (Filter f, Filter g);
    Or  or (List<Filter> f);
    Not not(Filter f);

    FeatureId     featureId(String [] ids);
    PropertyName  property(String name);

    PropertyIsBetween               between(Expression expr, Expression lower, Expression upper);
    PropertyIsEqualTo               equals(Expression expr1, Expression expr2);
    PropertyIsGreaterThan           greater(Expression expr1, Expression expr2);
    PropertyIsGreaterThanOrEqualTo  greaterOrEqual(Expression expr1, Expression expr2);
    PropertyIsLessThan              less(Expression expr1, Expression expr2);
    PropertyIsLessThanOrEqualTo     lessOrEqual(Expression expr1, Expression expr2);
    PropertyIsLike                  like(Expression expr, String pattern);
    PropertyIsLike                  like(Expression expr, String pattern, String wildcard, String singleChar, String escape);
    PropertyIsNull                  propIsNull(Expression expr);

////////////////////////////////////////////////////////////////////////////////
//
//  SPATIAL FILTERS
//
////////////////////////////////////////////////////////////////////////////////

    BBOX        bbox(String propertyName, double minx, double miny, double maxx, double maxy, String srs);
    Beyond      beyond(String propertyName, Geometry geometry, double distance, String units);
    Contains    contains(String propertyName, Geometry geometry);
    Crosses     crosses(String propertyName, Geometry geometry);
    Disjoint    disjoint(String propertyName, Geometry geometry);
    DWithin     dwithin(String propertyName, Geometry geometry, double distance, String units);
    Equals      equals(String propertyName, Geometry geometry);
    Intersects  intersects(String propertyName, Geometry geometry);
    Overlaps    overlaps(String propertyName, Geometry geometry);
    Touches     touches(String propertyName, Geometry geometry);
    Within      within(String propertyName, Geometry geometry);

////////////////////////////////////////////////////////////////////////////////
//
//  EXPRESSIONS
//
////////////////////////////////////////////////////////////////////////////////

    Add       add(Expression expr1, Expression expr2);
    Divide    divide(Expression expr1, Expression expr2);
    Multiply  multiply(Expression expr1, Expression expr2);
    Subtract  subtract(Expression expr1, Expression expr2);

    Function  function(String name, Expression[] args);
    Function  function(String name, Expression arg1);
    Function  function(String name, Expression arg1, Expression arg2);
    Function  function(String name, Expression arg1, Expression arg2, Expression arg3);

    Literal  literal(Object obj);
    Literal  literal(byte b);
    Literal  literal(short s);
    Literal  literal(int i);
    Literal  literal(long l);
    Literal  literal(float f);
    Literal  literal(double d);
    Literal  literal(char c);
    Literal  literal(boolean b);
}
