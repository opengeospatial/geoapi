/*
 * $ Id $
 * $ Source $
 * Created on Apr 4, 2005
 */
package org.opengis.layer.source;

import java.io.IOException;


/**
 * The <code>LayerSourceException</code> class...
 * 
 * @author SYS Technologies
 * @author crossley
 * @version $Revision $
 */
public class LayerSourceException extends IOException {
    
    public LayerSourceException() {
        super();
    }

    public LayerSourceException(final String message) {
        super(message);
    }

    public LayerSourceException(final Throwable cause) {
        super();
        initCause(cause);
    }

    public LayerSourceException(final String message, final Throwable cause) {
        super(message);
        initCause(cause);
    }
}
