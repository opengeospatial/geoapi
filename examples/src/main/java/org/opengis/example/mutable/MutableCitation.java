/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    http://www.geoapi.org
 *
 *    Copyright (C) 2007-2010 Open Geospatial Consortium, Inc.
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
package org.opengis.example.mutable;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
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
