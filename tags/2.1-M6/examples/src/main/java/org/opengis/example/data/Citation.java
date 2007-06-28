package org.opengis.example.data;

import java.util.Collection;

public interface Citation {
	String getISBN();    
    void setISBN( String isbn );
    
    /**
     * Accss to citations dates.
     * @return Access to citation dates (note a copy is returned)
     */
    Collection<CitationDate> getDates();
    
    void setDates( Collection<CitationDate> dates );
    
    /**
     * Hashcode as defined by getDates().hashCode() | getISBN().hashCode().
     * @return getDates().hashCode() | getISBN().hashCode()
     */
    public int hashCode();
    
    /**
     * Euqals if provided obj has the same ISBN and Dates.
     * 
     * @param obj Other object
     * @return <true> if obj is a Citation with the same ISBN and Dates
     */
    public boolean equals(Object obj);
}