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
package org.opengis.filter;

import java.util.List;
import java.util.Optional;
import org.opengis.util.TypeName;
import org.opengis.annotation.UML;
import org.opengis.annotation.Classifier;
import org.opengis.annotation.Stereotype;

import static org.opengis.annotation.Obligation.*;
import static org.opengis.annotation.Specification.*;


/**
 * An expression with a query that is not known before the time it is being executed.
 * An ad hoc query expression contains:
 *
 * <ul>
 *   <li>the names of one or more resource types to query,</li>
 *   <li>an optional projection clause enumerating the properties of the resource to present in the response,</li>
 *   <li>an option selection clause that constraints the properties of those resources types
 *       in order to define a result set,</li>
 *   <li>an optional sorting clause specifying the order in which the result set is presented.</li>
 * </ul>
 *
 * @author  Johann Sorel (Geomatys)
 * @author  Martin Desruisseaux (Geomatys)
 * @version 3.1
 * @since   3.1
 */
@Classifier(Stereotype.ABSTRACT)
@UML(identifier="AdhocQueryExpression", specification=ISO_19143)
public interface AdhocQueryExpression extends QueryExpression {
    /**
     * Names of one or more correlated resource types to be queried.
     *
     * @return names of resource types to be queries.
     *
     * @departure easeOfUse
     *   The filter specification associates a class {@code Type} with the following properties,
     *   where {@code ofType} and {@code ofKind} are in an union (which means that exactly one of
     *   those 2 properties shall be provided):
     *   <ul>
     *     <li>{@code ofType} as a {@link org.opengis.util.ScopedName}.</li>
     *     <li>{@code ofKind} as a {@link org.opengis.util.ScopedName}.</li>
     *     <li>{@code alias}  as a {@link org.opengis.util.LocalName}.
     *         "the optional alias may be used within an ad hoc query expression
     *          to specify alternate names for the resource type name."</li>
     *   </ul>
     *   Since we have no details about {@code ofType} and {@code ofKind} yet, we omitted the
     *   {@code Type} interface for now. If we add this functionality in a future version,
     *   we may consider creating a sub-interface of {@link TypeName} instead.
     */
    @UML(identifier="types", obligation=MANDATORY, specification=ISO_19143)
    List<? extends TypeName> getTypeNames();

    /**
     * Optional resource properties that shall be available in a query response.
     * While it can be any type of objects, the common case is list of {@link ValueReference}.
     *
     * @return the projection clauses, or an empty list if none.
     *
     * @todo According specification, elements should be sub-types of {@code AbstractAdhocProjectionClause}.
     */
    @UML(identifier="projection", obligation=OPTIONAL, specification=ISO_19143)
    List<?> getProjectionClauses();

    /**
     * Query predicates that shall be applied to a dataset in order to define a subset of data to be operated upon.
     * While it can be any type of objects, the common case is a {@link Filter}.
     *
     * @return the query clause.
     *
     * @todo According specification, should be a sub-types of {@code AbstractSelectionClause}.
     */
    @UML(identifier="selection", obligation=OPTIONAL, specification=ISO_19143)
    Optional<?> getSelectionClause();

    /**
     * Asserts the order in which resources shall appear in response to an ad hoc query expression.
     *
     * @return order in which resources shall appear.
     *
     * @todo According specification, should be a sub-type of {@code AbstractSortingClause}.
     */
    @UML(identifier="sorting", obligation=OPTIONAL, specification=ISO_19143)
    Optional<?> getSortingClause();
}
