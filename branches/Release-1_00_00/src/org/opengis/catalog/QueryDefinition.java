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
 * @author <A HREF="http://www.opengis.org">OpenGIS&reg; consortium</A> 
 * @version <A HREF="http://www.opengis.org/docs/99-113.pdf">Catalog Services</A> 
 * @UML inferred from Section 3.1.1.1 <i>Query Functions</i> in the
 *      <A HREF="http://www.opengis.org/docs/99-113.pdf">OGC Abstract
 *      Catalog Services </A> Specification 
 */
public interface QueryDefinition {
    /**
     * Determines whether a CatalogEntry fits this query.
     *
     * @param entry A CatalogEntry to evaluate.
     * @return Returns <code>true</code> if the specified {@linkplain CatalogEntry catalog entry}
     *                 fits the query. Otherwise <code>false</code> is returned.
     *
     * @UML inferred from Section 3.1.1.1 <i>Query Functions</i> in the
     *      <A HREF="http://www.opengis.org/docs/99-113.pdf">OGC Abstract
     *      Catalog Services </A> Specification 
     */
    public boolean evaluate(CatalogEntry entry);
}
