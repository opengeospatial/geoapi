package org.opengis.filter;

public interface Not extends LogicOperator {
    public Filter getFilter();
    public void setFilter(Filter filter);
}
