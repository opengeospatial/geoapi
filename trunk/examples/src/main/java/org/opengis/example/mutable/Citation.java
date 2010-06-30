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
    String ISBN_PROPERTY = "ISBN";
    String DATES_PROPERTY = "dates";

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
