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
 * <p>
 * This interface is a minimal definition based on the 
 * <A HREF="http://www.opengis.org/docs/99-113.pdf">OGC Abstract Catalog Services</A> Specification 
 * Section 3.1.1.1 <i>Query Functions</i>
 * </p> 
 * 
 * @author jeichar
 */
public interface QueryResult {
	/**
	 * Gets the number of CatalogEntries found
	 * <p>
	 * See Section 3.1.1.1.1 <i>Query Functions </i> in the <A
	 * HREF="http://www.opengis.org/docs/99-113.pdf">OGC Abstract Catalog
	 * Services </A> Specification
	 * </p>
	 * @return the number of CatalogEntries in this result object
	 */
	public int getNumEntries();
	/**
	 * Returns the indexed CatalogEntry
	 * <p>
	 * See Section 3.1.1.1.1 <i>Query Functions </i> in the <A
	 * HREF="http://www.opengis.org/docs/99-113.pdf">OGC Abstract Catalog
	 * Services </A> Specification
	 * </p>
	 * @param index the index of the CatalogEntry requested
	 * @return the requested CatalogEntry
	 */
	public CatalogEntry getEntry(int index);
	/**
	 * returns an iterator that can be used to iterate through CatalogEntries
	 * <p>
	 * See Section 3.1.1.1.1 <i>Query Functions </i> in the <A
	 * HREF="http://www.opengis.org/docs/99-113.pdf">OGC Abstract Catalog
	 * Services </A> Specification
	 * </p>
	 * @return An iterator for iterating through the CatalogEntries
	 */
	public Iterator metaDataIterator();
}
