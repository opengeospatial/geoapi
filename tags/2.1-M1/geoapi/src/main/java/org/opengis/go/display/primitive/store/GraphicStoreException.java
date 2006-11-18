/*
 * $ Id $
 * $ Source $
 * Created on Jan 10, 2005
 */
package org.opengis.go.display.primitive.store;

import java.io.IOException;


/**
 * The <code>GraphicStoreException</code> class...
 * 
 * @author SYS Technologies
 * @author crossley
 * @version $Revision $
 */
public class GraphicStoreException extends IOException {
    
    public GraphicStoreException() {
        super();
    }

    public GraphicStoreException(final String message) {
        super(message);
    }

    public GraphicStoreException(final Throwable cause) {
        super();
        initCause(cause);
    }

    public GraphicStoreException(final String message, final Throwable cause) {
        super(message);
        initCause(cause);
    }
}
