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

/**
 * In general, a query definition specifies a Boolean function of multiple binary-valued conditions.
 * This Boolean function of multiple conditions combines these conditions using AND, OR, and NOT
 * operators. Each of these conditions has three parts:
 * 
 * <ul>
 * <li>1. Metadata Element name or identification, to be compared </li>
 * <li>2. Comparison value, to be compared to the value of the Metadata Element</li>
 * <li>3. Type of comparison to perform, between the comparison value and the value of the Metadata Element</li>
 * </ul>
 * 
 * @author jeichar
 */
public interface QueryDefinition {
	/**
	 * Determines whether a CatalogEntry fits this query 
	 * @param entry A CatalogEntry to evaluate
	 * @return Returns true if the CatalogEntry fits the query.  Otherwise False is returned
	 */
	public boolean evaluate(CatalogEntry entry); 
}
