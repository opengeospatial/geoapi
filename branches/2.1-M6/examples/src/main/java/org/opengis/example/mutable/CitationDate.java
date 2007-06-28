package org.opengis.example.mutable;

import java.util.Date;

/**
 * Implementation required to be thread-safe.
 * 
 * @author Jody
 */
public interface CitationDate {
     Date getDate();
     void setDate( Date date );
}
