package org.opengis.example.mutable;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class MutableCitation implements Citation {
	final private PropertyChangeSupport listeners = new PropertyChangeSupport(this);
	private Collection<CitationDate> dates = Collections.synchronizedList( new CitationDates());
	private String isbn;
	
	public synchronized Collection<CitationDate> getDates() {
		return dates;
	}

	public synchronized String getISBN() {
		return isbn;
	}

	public synchronized void setISBN(String code) {
		listeners.firePropertyChange(ISBN_PROPERTY, isbn, isbn = code);
	}

	public synchronized void addPropertyChangeListener(
			PropertyChangeListener listener) {
		listeners.addPropertyChangeListener(listener);
	}

	public synchronized void addPropertyChangeListener(String property,
			PropertyChangeListener listener) {
		listeners.addPropertyChangeListener(property, listener);
	}

	public synchronized void removePropertyChangeListener(
			PropertyChangeListener listener) {
		listeners.removePropertyChangeListener(listener);
	}

	public synchronized void removePropertyChangeListener(String property,
			PropertyChangeListener listener) {
		listeners.removePropertyChangeListener(property, listener);
	}

	public synchronized PropertyChangeListener[] getPropertyChangeListeners() {
		return listeners.getPropertyChangeListeners();
	}
	class CitationDates extends ArrayList<CitationDate>{
		private static final long serialVersionUID = 0L;		
		public CitationDate set( int index, CitationDate date ){
			CitationDate old = super.set( index, date );
			listeners.fireIndexedPropertyChange( DATES_PROPERTY, index, old, date );
			return old;			
		}
		public boolean add( CitationDate date){
			try {
			   return super.add( date );
			}
			finally {
				listeners.fireIndexedPropertyChange( DATES_PROPERTY, size()-1, null, date );
			}
		}
		public void add(int index, CitationDate element) {
			super.add(index, element);
			listeners.fireIndexedPropertyChange( DATES_PROPERTY, index, null, element );
		}
		public CitationDate remove(int index) {
			CitationDate old = super.remove(index);
			listeners.fireIndexedPropertyChange( DATES_PROPERTY, index, old, null );
			return old;
		}
		public boolean remove(Object o) {
			int index = super.indexOf( o );
			if( index == -1 ) return false;
			
			super.remove(o);
			listeners.fireIndexedPropertyChange( DATES_PROPERTY, index, o, null );
			return true;
		}		
	}
}
