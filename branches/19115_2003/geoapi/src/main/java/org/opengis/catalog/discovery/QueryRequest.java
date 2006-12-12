/*
 * Copyright (c) 2006 - Gipuzkoako Foru Aldundia
 * http://b5m.gipuzkoa.net.  All rights reserved.
 */
package org.opengis.catalog.discovery;

import java.util.List;

import org.opengis.catalog.InvalidParameterValueException;
import org.opengis.filter.sort.SortBy;

/**
 * <b>QueryRequest interfase</b>
 * <p>
 * DOCUMENT ME!
 * </p>
 * 
 * @author Axios Engineering S.L.
 * @version $Id$
 */
public interface QueryRequest extends Cloneable {
    /**
     * Returns the first result set resource to be returned for this operation
     * request
     * 
     * @return a positive integer, defaulting to <code>1</code> , indicating
     *         the first result set resource to be returned for this operation
     *         request.
     */
    int getCursorPosition();

    /**
     * Specifies the maximum number of result set resources to be returned.
     * 
     * @return the maximum number of result set resources to be returned, if
     *         set, or <code>10</code> as the default value.
     */
    int getIteratorSize();

    /**
     * A catalogue may contain references to several different resource types.
     * This parameter provides for the selection of one of those types for
     * processing.
     * 
     * @return non-null (mandatory) value for the kind of resource to query the
     *         catalogue for.
     */
    ResourceType getResourceType();

    /**
     * Provides sorting information to the server for formatting data returned
     * to the client.
     * 
     * @return the sorting parameters for the request in the form of a list of
     *         {@link SortBy} objects, or an empty list if not specified.
     */
    List getSortSpec();

    /**
     * The query language and predicate expressing query constraints
     * 
     * @todo remove QueryExpression and use direcly Filter for java api
     * 
     * @return non-null query expression
     */
    QueryExpression getQueryExpression();

    /**
     * DOCUMENT ME!
     * 
     * @return DOCUMENT ME!
     */
    Object clone();

    /**
     * Sets the first result set resource to be returned for this operation
     * request
     * 
     * @param cursor
     *            positive integer indicating the result index (starting at 1)
     *            to return from.
     */
    void setCursorPosition(int cursor) throws InvalidParameterValueException;
}
