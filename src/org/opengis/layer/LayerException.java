/*
 * $ Id $
 * $ Source $
 * Created on Apr 4, 2005
 */
package org.opengis.layer;

import java.io.IOException;


/**
 * The <code>LayerException</code> class...
 * 
 * @author SYS Technologies
 * @author crossley
 * @version $Revision $
 */
public class LayerException extends IOException {
    
    public LayerException() {
        super();
    }

    public LayerException(final String message) {
        super(message);
    }

    public LayerException(final Throwable cause) {
        super();
        initCause(cause);
    }

    public LayerException(final String message, final Throwable cause) {
        super(message);
        initCause(cause);
    }
}
