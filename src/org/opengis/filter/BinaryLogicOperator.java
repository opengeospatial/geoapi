package org.opengis.filter;

/**
 *
 */
public interface BinaryLogicOperator extends LogicOperator {
    
    /**
     *
     */
    public Filter getFilter1();
    
    /**
     * @param filter
     */
    public void setFilter1(Filter filter);

    /**
     *
     */
    public Filter getFilter2();
    
    /**
     * @param filter
     */
    public void setFilter2(Filter filter);
}
