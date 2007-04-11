package org.opengis.example.mutable;

import java.beans.PropertyChangeListener;
import java.util.Collection;

/**
 * Implementation is responsible for making this thread safe. Two stratagies are recommended:
 * <ul>
 * <li>synchornized - use synchronized on all public methods
 * <li>lock - use an internal ReadWrite lock
 * </ul>
 * As usual it is recommended you use Java's PropertyChangeSupport class.
 * 
 * @author Jody
 */
public interface Citation {
	public static String ISBN_PROPERTY = "ISBN";
	public static String DATES_PROPERTY = "dates";
	
	/**
	 * @return ISBN property
	 */
	String getISBN();
	
	/**
	 * Set ISBN property.
	 * <p>
	 * Fires a ISBN_PROPERTY change event.
	 * @param isbn
	 */
    void setISBN( String isbn );
    
    /**
     * Direct access to dates (you can modify the collection in place).
     * <p>
     * Fires a DATES_PROPERTY change event as modified.
     * </p>
     * @return Access to citation dates (note a copy is returned)
     */
    Collection<CitationDate> getDates();
   
    void addPropertyChangeListener( PropertyChangeListener listener );
    void addPropertyChangeListener( String name, PropertyChangeListener listener );
    void removePropertyChangeListener( PropertyChangeListener listener );
    void removePropertyChangeListener( String name, PropertyChangeListener listener );
    PropertyChangeListener[] getPropertyChangeListeners();
}