package org.opengis.filter;

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

public interface FilterVisitor {
    public Object visit(And filter,                            Object extraData);
    public Object visit(FeatureId filter,                      Object extraData);
    public Object visit(Not filter,                            Object extraData);
    public Object visit(Or filter,                             Object extraData);
    public Object visit(PropertyIsBetween filter,              Object extraData);
    public Object visit(PropertyIsEqualTo filter,              Object extraData);
    public Object visit(PropertyIsGreaterThan filter,          Object extraData);
    public Object visit(PropertyIsGreaterThanOrEqualTo filter, Object extraData);
    public Object visit(PropertyIsLessThan filter,             Object extraData);
    public Object visit(PropertyIsLessThanOrEqualTo filter,    Object extraData);
    public Object visit(PropertyIsLike filter,                 Object extraData);
    public Object visit(PropertyIsNull filter,                 Object extraData);

    public Object visit(BBOX filter,       Object extraData);
    public Object visit(Beyond filter,     Object extraData);
    public Object visit(Contains filter,   Object extraData);
    public Object visit(Crosses filter,    Object extraData);
    public Object visit(Disjoint filter,   Object extraData);
    public Object visit(DWithin filter,    Object extraData);
    public Object visit(Equals filter,     Object extraData);
    public Object visit(Intersects filter, Object extraData);
    public Object visit(Overlaps filter,   Object extraData);
    public Object visit(Touches filter,    Object extraData);
    public Object visit(Within filter,     Object extraData);
}
