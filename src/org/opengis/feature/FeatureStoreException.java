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
 * Indicates a problem with a FeatureStore, allows nested exception in an IOException.
 * <p>
 * Since this is a FeatureStore exception should we have a backpointer
 * to the FeatureStore? Or at least an indication of which data store caused
 * the problem?
 * </p>
 * 
 * @author Martin
 */
public class FeatureStoreException extends IOException {
    
    /**
     * serialVersionUID allowing cross compiler use of FeatureStoreException. 
     */
    private static final long serialVersionUID = -7266283714520657425L;

    public FeatureStoreException() {
        super();
    }

    public FeatureStoreException(final String message) {
        super(message);
    }

    public FeatureStoreException(final Throwable cause) {
        super();
        initCause(cause);
    }

    public FeatureStoreException(final String message, final Throwable cause) {
        super(message);
        initCause(cause);
    }
}