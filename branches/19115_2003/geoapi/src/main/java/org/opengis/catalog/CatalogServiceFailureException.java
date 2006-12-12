/*
 * Copyright (c) 2006 - Gipuzkoako Foru Aldundia
 * http://b5m.gipuzkoa.net.  All rights reserved.
 */
package org.opengis.catalog;

/**
 * Catalog Service Failure Exception
 * 
 * <p>
 * Throw this exception if the service fail and cant return a valid value.
 * </p>
 * 
 * @author Mauricio Pazos, Axios Engineering
 * @version $Id$
 */
public final class CatalogServiceFailureException extends Exception {
    /**
     * 
     */
    private static final long serialVersionUID = -7050198531338883034L;

    /**
     * Creates a new QueryFailedException object.
     */
    public CatalogServiceFailureException() {
        super();

    }

    /**
     * Creates a new QueryFailedException object.
     * 
     * @param arg0
     *            DOCUMENT ME!
     */
    public CatalogServiceFailureException(String arg0) {
        super(arg0);
    }

    /**
     * Creates a new QueryFailedException object.
     * 
     * @param arg0
     *            DOCUMENT ME!
     * @param arg1
     *            DOCUMENT ME!
     */
    public CatalogServiceFailureException(String arg0, Throwable arg1) {
        super(arg0, arg1);
    }

    /**
     * Creates a new QueryFailedException object.
     * 
     * @param arg0
     *            DOCUMENT ME!
     */
    public CatalogServiceFailureException(Throwable arg0) {
        super(arg0);
    }
}
