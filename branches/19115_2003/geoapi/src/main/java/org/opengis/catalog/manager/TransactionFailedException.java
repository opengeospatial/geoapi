/*
 * Copyright (c) 2006 - Gipuzkoako Foru Aldundia
 * http://b5m.gipuzkoa.net.  All rights reserved.
 */
package org.opengis.catalog.manager;

/**
 * TransactionException 
 * 
 * <p>
 * Esta Excepción se produce en caso de un fallo durante la transacción
 * del subsistema de mantenimiento de datos.
 * </p>
 *
 * @author Mauricio Pazos, Axios Engineering
 * @version $Id$
 */
public final class TransactionFailedException extends Exception {
    private static final long serialVersionUID = 809042024592351650L;

    /**
     * Creates a new TransactionException object.
     *
     * @param msg descripción del fallo
     */
    public TransactionFailedException(String msg) {
        super(msg);
    }

    /**
     * Creates a new TransactionException object.
     *
     * @param msg descripción del fallo
     * @param e excepción
     */
    public TransactionFailedException(String msg, Throwable e) {
        super(msg, e);
    }
}
