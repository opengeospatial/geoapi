package org.opengis.filter;

/**
 * Filter operator that checks if the named property of a feature is null.
 */
public interface PropertyIsNull extends Filter {
    
    /**
     *
     */
    public String getPropertyName();
    
    /**
     *
     */
    public void setPropertyName(String propName);
}
