/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    http://www.geoapi.org
 *
 *    Copyright (C) 2006-2021 Open Geospatial Consortium, Inc.
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
package org.opengis.filter.query;

import java.util.List;
import org.opengis.annotation.XmlElement;
import org.opengis.util.GenericName;


/**
 * A fundamental type of query expression is the ad hoc query expression. It is ad hoc in the sense that the
 * query is not known before the time it is being executed as, for example, a stored query would be.
 *
 * <p>An ad hoc query expression is a query expression that contains the names of one or more resource types to
 * query, an optional projection clause enumerating the properties of the resource to present in the response, an
 * option selection clause that constraints the properties of those resources types in order to define a result set
 * and an optional sorting clause specifying the order in which the result set is presented.</p>
 *
 * @version <A HREF="http://portal.opengeospatial.org/files/?artifact_id=39968">Implementation specification 2.0</A>
 * @author Johann Sorel (Geomatys)
 * @since GeoAPI 3.1
 */
@XmlElement("AdhocQueryExpression")
public interface AdhocQueryExpression extends QueryExpression {
    /**
     * The mandatory typeNames parameter shall be used within an ad hoc query expression to encode the names
     * of one or more correlated resource types to be queried.
     *
     * @return List of names, never null.
     */
    @XmlElement("typeNames")
    List<? extends GenericName> getPropertyNames();

    /**
     * The optional aliases parameter may be used within an ad hoc query expression to specify alternate names for
     * the resource type names specified as the value of the typeNames parameter.
     *
     * <p>A resource type alias may be used anywhere; the resource type name may be
     * used within the context of the query expression.</p>
     *
     * <p>The number of list elements in the value of the aliases parameter shall match the number of corresponding
     * resource type names in the value of the typeNames parameter and shall be correlated 1:1.</p>
     *
     * @return List of names, never null.
     */
    @XmlElement("aliases")
    List<? extends GenericName> getAliases();

    /**
     * A projection clause encodes a list of optional resource properties that shall be available in a query response.
     *
     * While it can be any type of objects, the common case is list of PropertyName.
     */
    @XmlElement("AbstractProjectionClause")
    List<Object> getProjectionClause();

    /**
     * The selection clause defines a set of query predicates that shall be applied to a dataset in order to define a
     * subset of data to be operated upon.
     *
     * While it can be any type of objects, the common case is a Filter.
     */
    @XmlElement("AbstractSelectionClause")
    Object getSelectionClause();

    @XmlElement("AbstractSortingClause")
    Object getSortingClause();

}
