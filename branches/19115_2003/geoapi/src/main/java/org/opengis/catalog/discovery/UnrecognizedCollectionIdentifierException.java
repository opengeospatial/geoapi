/*
 * Copyright (c) 2006 - Gipuzkoako Foru Aldundia
 * http://b5m.gipuzkoa.net.  All rights reserved.
 */
package org.opengis.catalog.discovery;

/**
 * <b>UnrecognizedCollectionIdentifierException class</b>
 * 
 * <p>
 * Metadata Identifier Unrecognized
 * </p>
 * 
 * @author Mauricio Pazos, Axios Engineering
 * @version $Id: UnrecognizedCollectionIdentifierException.java 848 2006-06-06
 *          04:40:00Z groldan $
 */
public final class UnrecognizedCollectionIdentifierException extends Exception {
    private static final long serialVersionUID = 510843372444514541L;

    /**
     * Creates a new UnrecognizedCollectionIdentifierException object.
     * 
     * @param msg
     *            descripción del fallo
     */
    public UnrecognizedCollectionIdentifierException(String msg) {
        super(msg);
    }

    /**
     * Creates a new UnrecognizedCollectionIdentifierException object.
     * 
     * @param msg
     *            descripción del fallo
     * @param e
     *            excepción
     */
    public UnrecognizedCollectionIdentifierException(String msg, Throwable e) {
        super(msg, e);
    }

}
