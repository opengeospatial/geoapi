package org.opengis.filter;

/**
 * <B>PENDING</B>: How should this work given that we allow Features to have
 * composite primary keys?  Things would be a lot easier if Features were
 * required to have a String ID.  Should we pass in an array of objects instead?
 */
public interface FeatureId extends Filter {
    
    /**
     *
     */
    public String getFID();
    
    /**
     *
     */
    public void setFID(String fid);
}
