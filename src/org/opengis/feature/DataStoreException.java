/*$************************************************************************************************
 **
 ** $Id$
 **
 ** $Source$
 **
 ** Copyright (C) 2003 Open GIS Consortium, Inc. All Rights Reserved. http://www.opengis.org/Legal/
 **
 *************************************************************************************************/
package org.opengis.feature;

import java.io.IOException;

/**
 * Indicates a problem with a DataStore, allows nested exception in an IOException.
 * <p>
 * Since this is a DataStore exception should we have a backpointer
 * to the DataStore? Or at least an indication of which data store caused
 * the problem?
 * </p>
 * 
 * @author Martin
 */
public class DataStoreException extends IOException {
    
    /**
     * serialVersionUID allowing cross compiler use of DataStoreException. 
     */
    private static final long serialVersionUID = -7266283714520657425L;

    public DataStoreException() {
        super();
    }

    public DataStoreException(final String message) {
        super(message);
    }

    public DataStoreException(final Throwable cause) {
        super();
        initCause(cause);
    }

    public DataStoreException(final String message, final Throwable cause) {
        super(message);
        initCause(cause);
    }
}