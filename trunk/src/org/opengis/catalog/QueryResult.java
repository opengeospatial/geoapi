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
 * @author <A HREF="http://www.opengis.org">OpenGIS&reg; consortium</A> 
 * @version <A HREF="http://www.opengis.org/docs/99-113.pdf">Catalog Services</A> 
 * @UML inferred from Section 3.1.1.1 <i>Query Functions</i> in the
 *      <A HREF="http://www.opengis.org/docs/99-113.pdf">OGC Abstract
 *      Catalog Services </A> Specification 
 */
public interface QueryResult {
	/**
	 * Gets the number of CatalogEntries found
	 * @return the number of CatalogEntries in this result object
     * @UML inferred from Section 3.1.1.1 <i>Query Functions</i> in the
     *      <A HREF="http://www.opengis.org/docs/99-113.pdf">OGC Abstract
     *      Catalog Services </A> Specification 
	 */
	public int getNumEntries();
	/**
	 * Returns the indexed CatalogEntry
	 * @param index
	 *            the index of the CatalogEntry requested
	 * @return the requested CatalogEntry
     * @UML inferred from Section 3.1.1.1 <i>Query Functions</i> in the
     *      <A HREF="http://www.opengis.org/docs/99-113.pdf">OGC Abstract
     *      Catalog Services </A> Specification 
	 */
	public CatalogEntry getEntry(int index);
	/**
	 * returns an iterator that can be used to iterate through CatalogEntries
	 * @return An iterator for iterating through the CatalogEntries
     * @UML inferred from Section 3.1.1.1 <i>Query Functions</i> in the
     *      <A HREF="http://www.opengis.org/docs/99-113.pdf">OGC Abstract
     *      Catalog Services </A> Specification 
	 */
	public Iterator metaDataIterator();
}