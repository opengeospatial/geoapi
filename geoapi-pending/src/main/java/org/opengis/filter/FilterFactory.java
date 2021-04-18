/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    http://www.geoapi.org
 *
 *    Copyright (C) 2006-2021 Open Geospatial Consortium, Inc.
 *    All Rights Reserved. http://www.opengeospatial.org/ogc/legal
 *
 *    Permission to use, copy, and modify this software and its documentation, with
 *    or without modification, for any purpose and without fee or royalty is hereby
 *    granted, provided that you include the following on ALL copies of the software
 *    and documentation or portions thereof, including modifications, that you make:
 *
 *    1. The full text of this NOTICE in a location viewable to users of the
 *       redistributed or derivative work.
 *    2. Notice of any changes or modifications to the OGC files, including the
 *       date changes were made.
 *
 *    THIS SOFTWARE AND DOCUMENTATION IS PROVIDED "AS IS," AND COPYRIGHT HOLDERS MAKE
 *    NO REPRESENTATIONS OR WARRANTIES, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED
 *    TO, WARRANTIES OF MERCHANTABILITY OR FITNESS FOR ANY PARTICULAR PURPOSE OR THAT
 *    THE USE OF THE SOFTWARE OR DOCUMENTATION WILL NOT INFRINGE ANY THIRD PARTY
 *    PATENTS, COPYRIGHTS, TRADEMARKS OR OTHER RIGHTS.
 *
 *    COPYRIGHT HOLDERS WILL NOT BE LIABLE FOR ANY DIRECT, INDIRECT, SPECIAL OR
 *    CONSEQUENTIAL DAMAGES ARISING OUT OF ANY USE OF THE SOFTWARE OR DOCUMENTATION.
 *
 *    The name and trademarks of copyright holders may NOT be used in advertising or
 *    publicity pertaining to the software without specific, written prior permission.
 *    Title to copyright in this software and any associated documentation will at all
 *    times remain with copyright holders.
 */
package org.opengis.filter;

import java.util.List;
import java.util.Set;
import org.opengis.geometry.Envelope;
import org.opengis.util.GenericName;
import org.opengis.geometry.Geometry;


/**
 * Interface whose methods allow the caller to create instances of the various
 * {@link Filter} and {@link Expression} subclasses.
 *
 * @version <A HREF="http://www.opengis.org/docs/02-059.pdf">Implementation specification 1.0</A>
 * @version <A HREF="http://portal.opengeospatial.org/files/?artifact_id=39968">Implementation specification 2.0</A>
 * @author Chris Dillard (SYS Technologies)
 * @author Jody Garnett (Refractions Research Inc.)
 * @author Johann Sorel (Geomatys)
 * @since GeoAPI 2.0
 */
public interface FilterFactory {
    /**
     * The FilterCapabilities data structure is used to describe the abilities of
     * this FilterFactory, it includes restrictions on the available spatial operations,
     * scalar operations, lists the supported functions, and describes what geometry
     * literals are understood.
     * @return FilterCapabilities describing the abilities of this FilterFactory
     */
    // FilterCapabilities getCapabilities();

////////////////////////////////////////////////////////////////////////////////
//
//  IDENTIFIERS
//
////////////////////////////////////////////////////////////////////////////////
    /** Creates a new feautre id from a string */
    ResourceId featureId(String id);

    /** Creates a new gml object id from a string */
    ResourceId gmlObjectId(String id);

////////////////////////////////////////////////////////////////////////////////
//
//  FILTERS
//
////////////////////////////////////////////////////////////////////////////////

    /** {@code AND} filter between two filters. */
    LogicalOperator and(Filter f, Filter g);

    /** {@code AND} filter between a list of filters. */
    LogicalOperator and(List<Filter> f);

    /** {@code OR} filter between two filters. */
    LogicalOperator or(Filter f, Filter g);

    /** {@code OR} filter between a list of filters. */
    LogicalOperator or (List<Filter> f);

    /** Reverses the logical value of a filter. */
    LogicalOperator not(Filter f);

    /** Passes only for objects that have one of the IDs given to this object. */
    ResourceId id(Set<? extends ResourceId> ids);

    /** Retrieves the value of a {@linkplain org.opengis.feature.Feature feature}'s property. */
    ValueReference property(String name);

    /** Retrieves the value of a {@linkplain org.opengis.feature.Feature feature}'s property. */
    ValueReference property(GenericName name);

    /** A compact way of encoding a range check. */
    BetweenComparisonOperator between(Expression expr, Expression lower, Expression upper);

    /** Compares that two sub-expressions are equal to each other.
     * @todo should be equal (so equals can refer to geometry)
     */
    BinaryComparisonOperator equals(Expression expr1, Expression expr2);

    /** Compares that two sub-expressions are equal to eacher other */
    BinaryComparisonOperator equal(Expression expr1, Expression expr2, boolean matchCase, MatchAction matchAction);

    /** Checks that the first sub-expression is not equal to the second subexpression. */
    BinaryComparisonOperator notEqual(Expression expr1, Expression expr2 );

    /**
     * Checks that the first sub-expression is not equal to the second subexpression.
     *
     * @param expr1 first expression
     * @param expr2 second expression
     * @param matchCase true if the comparison should be case sensitive
     * @return evaluates to true of expr1 not equal to expr2
     */
    BinaryComparisonOperator notEqual(Expression expr1, Expression expr2, boolean matchCase, MatchAction matchAction);

    /** Checks that the first sub-expression is greater than the second subexpression. */
    BinaryComparisonOperator greater(Expression expr1, Expression expr2);

    /**
     * Checks that the first sub-expression is greater than the second subexpression.
     *
     * @param expr1 first expression
     * @param expr2 second expression
     * @param matchCase true if the comparison should be case sensitive
     * @return evaluates to true of expr1 is greater than expr2
     */
    BinaryComparisonOperator greater(Expression expr1, Expression expr2, boolean matchCase, MatchAction matchAction);

    /** Checks that the first sub-expression is greater or equal to the second subexpression. */
    BinaryComparisonOperator greaterOrEqual(Expression expr1, Expression expr2);

    /** Checks that the first sub-expression is greater or equal to the second subexpression. */
    BinaryComparisonOperator greaterOrEqual(Expression expr1, Expression expr2, boolean matchCase, MatchAction matchAction);

    /** Checks that its first sub-expression is less than its second subexpression. */
    BinaryComparisonOperator less(Expression expr1, Expression expr2);

    BinaryComparisonOperator less(Expression expr1, Expression expr2, boolean matchCase, MatchAction matchAction);

    /** Checks that its first sub-expression is less than or equal to its second subexpression. */
    BinaryComparisonOperator lessOrEqual(Expression expr1, Expression expr2);

    BinaryComparisonOperator lessOrEqual(Expression expr1, Expression expr2, boolean matchCase, MatchAction matchAction);

    /** Character string comparison operator with pattern matching and default wildcards. */
    LikeOperator like(Expression expr, String pattern);

    /** Character string comparison operator with pattern matching and specified wildcards. */
    LikeOperator like(Expression expr, String pattern, String wildcard, String singleChar, String escape);

    /** Character string comparison operator with pattern matching and specified wildcards. */
    LikeOperator like(Expression expr, String pattern, String wildcard, String singleChar, String escape, boolean matchCase);

    /** Checks if an expression's value is {@code null}. */
    NullOperator isNull(Expression expr);

    /** Checks if an expression's value is nil. */
    NilOperator isNil(Expression expr);

////////////////////////////////////////////////////////////////////////////////
//
//  SPATIAL FILTERS
//
////////////////////////////////////////////////////////////////////////////////

    /**
     * Creates an operator that checks if the bounding box of the feature's geometry overlaps the given bounding box.
     *
     * @param  propertyName  name of geometry property (for a {@link ValueReference} to access a feature's Geometry).
     * @param  minx          minimum "x" value (for a literal envelope).
     * @param  miny          minimum "y" value (for a literal envelope).
     * @param  maxx          maximum "x" value (for a literal envelope).
     * @param  maxy          maximum "y" value (for a literal envelope).
     * @param  srs           identifier of the Coordinate Reference System to use for a literal envelope.
     * @return operator that evaluates to {@code true} when the bounding box of the feature's geometry overlaps
     *         the bounding box provided in arguments to this method.
     *
     * @see FilterFactory2#bbox(Expression, Envelope)
     */
    BinarySpatialOperator bbox(String propertyName, double minx, double miny, double maxx, double maxy, String srs);

    /** Checks if the geometry expression overlaps the specified bounding box. */
    BinarySpatialOperator bbox(Expression geometry, double minx, double miny, double maxx, double maxy, String srs);

    /**
     * Checks if the bounding box of the feature's geometry overlaps the indicated bounds.
     * This method does not strictly confirm to the the Filter 1.0 specification, you may
     * use it to check expressions other than ValueReference.
     *
     * @param geometry  expression used to access a Geometry, in order to check for interaction with bounds
     * @param bounds    indicates the bounds to check geometry against
     */
    BinarySpatialOperator bbox(Expression geometry, Envelope bounds);

    /**
     * Creates an operator that checks if all of a feature's geometry is more distant than the given distance
     * from the given geometry.
     *
     * @param  propertyName  name of geometry property (for a {@link ValueReference} to access a feature's Geometry).
     * @param  geometry      the geometry from which to evaluate the distance.
     * @param  distance      minimal distance for evaluating the expression as {@code true}.
     * @param  units         units of the given {@code distance}.
     * @return operator that evaluates to {@code true} when all of a feature's geometry is more distant than
     *         the given distance from the given geometry.
     */
    DistanceOperator beyond(String propertyName, Geometry geometry, double distance, String units);

    /** Check if all of a geometry is more distant than the given distance from this object's geometry. */
    DistanceOperator beyond(Expression geometry1, Expression geometry2, double distance, String units);

    /** Checks if the the first geometric operand contains the second. */
    BinarySpatialOperator contains(String propertyName, Geometry geometry);

    /** Checks if the the first geometric operand contains the second. */
    BinarySpatialOperator contains(Expression geometry1, Expression geometry2);

    /** Checks if the first geometric operand crosses the second. */
    BinarySpatialOperator crosses(String propertyName, Geometry geometry);

    /** Checks if the first geometric operand crosses the second. */
    BinarySpatialOperator crosses(Expression geometry1, Expression geometry2);

    /** Checks if the first operand is disjoint from the second. */
    BinarySpatialOperator disjoint(String propertyName, Geometry geometry);

    /** Checks if the first operand is disjoint from the second. */
    BinarySpatialOperator disjoint(Expression geometry1, Expression geometry2);

    /** Checks if any part of the first geometry lies within the given distance of the second geometry. */
    DistanceOperator dwithin(String propertyName, Geometry geometry, double distance, String units);

    /** Checks if any part of the first geometry lies within the given distance of the second geometry. */
    DistanceOperator dwithin(Expression geometry1, Expression geometry2, double distance, String units);

    /** Checks if the geometry of the two operands are equal. */
    BinarySpatialOperator equals(String propertyName, Geometry geometry);

    /**
     * Checks if the geometry of the two operands are equal.
     * @todo should be equal, resolve conflict with BinaryComparisonOperator equals( Expression, Expression )
     */
    BinarySpatialOperator equal(Expression geometry1, Expression geometry2);

    /** Checks if the two geometric operands intersect. */
    BinarySpatialOperator intersects(String propertyName, Geometry geometry);

    /** Checks if the two geometric operands intersect. */
    BinarySpatialOperator intersects(Expression geometry1, Expression geometry2);

    /** Checks if the interior of the first geometry somewhere overlaps the interior of the second geometry. */
    BinarySpatialOperator overlaps(String propertyName, Geometry geometry);

    /** Checks if the interior of the first geometry somewhere overlaps the interior of the second geometry. */
    BinarySpatialOperator overlaps(Expression geometry1, Expression geometry2);

    /** Checks if the feature's geometry touches, but does not overlap with the geometry held by this object. */
    BinarySpatialOperator touches(String propertyName, Geometry geometry);

    /** Checks if the feature's geometry touches, but does not overlap with the geometry held by this object. */
    BinarySpatialOperator touches(Expression propertyName1, Expression geometry2);

    /** Checks if the feature's geometry is completely contained by the specified constant geometry. */
    BinarySpatialOperator within(String propertyName, Geometry geometry);

    /** Checks if the feature's geometry is completely contained by the specified constant geometry. */
    BinarySpatialOperator within(Expression geometry1, Expression geometry2);

////////////////////////////////////////////////////////////////////////////////
//
//  TEMPORAL FILTERS
//
////////////////////////////////////////////////////////////////////////////////

    /** Check if first expression is after the second. */
    TemporalOperator after(Expression expr1, Expression expr2);

    /** Sortcut filter for NOT (Before OR Meets OR MetBy OR After). */
    TemporalOperator anyInteracts(Expression expr1, Expression expr2);

    /** Check if first expression is before the second. */
    TemporalOperator before(Expression expr1, Expression expr2);

    /** Check if first expression begins at the second. */
    TemporalOperator begins(Expression expr1, Expression expr2);

    /** Check if first expression begun by the second. */
    TemporalOperator begunBy(Expression expr1, Expression expr2);

    /** Check if first expression is during the second. */
    TemporalOperator during(Expression expr1, Expression expr2);

    /** Check if first expression ends by the second. */
    TemporalOperator ends(Expression expr1, Expression expr2);

    /** Check if first expression is ended by the second. */
    TemporalOperator endedBy(Expression expr1, Expression expr2);

    /** Check if first expression meets the second. */
    TemporalOperator meets(Expression expr1, Expression expr2);

    /** Check if first expression is met by the second. */
    TemporalOperator metBy(Expression expr1, Expression expr2);

    /** Check if first expression is overlapped by the second. */
    TemporalOperator overlappedBy(Expression expr1, Expression expr2);

    /** Check if first expression iscontained in the second. */
    TemporalOperator tcontains(Expression expr1, Expression expr2);

    /** Check if first expression equal to the second. */
    TemporalOperator tequals(Expression expr1, Expression expr2);

    /** Check if first expression overlaps the second. */
    TemporalOperator toverlaps(Expression expr1, Expression expr2);


////////////////////////////////////////////////////////////////////////////////
//
//  EXPRESSIONS
//
////////////////////////////////////////////////////////////////////////////////

    /** Computes the numeric addition of the first and second operand. */
    Expression add(Expression expr1, Expression expr2);

    /** Computes the numeric quotient resulting from dividing the first operand by the second. */
    Expression divide(Expression expr1, Expression expr2);

    /** Computes the numeric product of their first and second operand. */
    Expression multiply(Expression expr1, Expression expr2);

    /** Computes the numeric difference between the first and second operand. */
    Expression subtract(Expression expr1, Expression expr2);

    /** Call into some implementation-specific function. */
    Expression function(String name, Expression ... args);

    /** A constant, literal value that can be used in expressions. */
    Literal  literal(Object obj);

    /** A constant, literal {@link Byte} value that can be used in expressions. */
    Literal  literal(byte b);

    /** A constant, literal {@link Short} value that can be used in expressions. */
    Literal  literal(short s);

    /** A constant, literal {@link Integer} value that can be used in expressions. */
    Literal  literal(int i);

    /** A constant, literal {@link Long} value that can be used in expressions. */
    Literal  literal(long l);

    /** A constant, literal {@link Float} value that can be used in expressions. */
    Literal  literal(float f);

    /** A constant, literal {@link Double} value that can be used in expressions. */
    Literal  literal(double d);

    /** A constant, literal {@link Character} value that can be used in expressions. */
    Literal  literal(char c);

    /** A constant, literal {@link Boolean} value that can be used in expressions. */
    Literal  literal(boolean b);

    ////////////////////////////////////////////////////////////////////////////////
    //
    //  SORT BY
    //
    //////////////////////////////////////////////////////////////////////////////    //
    /** Indicates an property by which contents should be sorted, along with intended order. */
    SortProperty sort(String propertyName, SortOrder order);
}
