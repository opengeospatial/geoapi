/*$************************************************************************************************
 **
 ** $Id$
 **
 ** $Source$
 **
 ** Copyright (C) 2003 Open GIS Consortium, Inc. All Rights Reserved. http://www.opengis.org/Legal/
 **
 *************************************************************************************************/

package org.opengis.catalog;

import java.util.Iterator;

/**
 * A collection of CatalogEntries 
 * 
 * @author jeichar
 */
public interface QueryResult {
	/**
	 * Gets the number of CatalogEntries found
	 * @return the number of CatalogEntries in this result object
	 */
	public int getNumEntries();
	/**
	 * Returns the indexed CatalogEntry
	 * @param index the index of the CatalogEntry requested
	 * @return the requested CatalogEntry
	 */
	public CatalogEntry getEntry(int index);
	/**
	 * returns an iterator that can be used to iterate through CatalogEntries
	 * @return An iterator for iterating through the CatalogEntries
	 */
	public Iterator metaDataIterator();
}
