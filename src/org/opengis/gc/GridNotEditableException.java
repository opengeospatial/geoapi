/*
 * Copyright (C) 2003 Open GIS Consortium, Inc. All Rights Reserved. http://www.opengis.org/Legal/
 */
package org.opengis.gc;


/**
 * Throws when an attempt is made to write in a non-editable grid.
 *
 * @UML exception GC_GridNotEditable
 * @author <A HREF="http://www.opengis.org">OpenGIS&reg; consortium</A>
 * @version 1.1
 *
 * @see GridCoverage#isDataEditable
 */
public class GridNotEditableException extends IllegalStateException {
    /**
     * Serial number for interoperability with different versions.
     */
    private static final long serialVersionUID = 612186655921122650L;

    /**
     * Creates an exception with no message.
     */
    public GridNotEditableException() {
        super();
    }

    /**
     * Creates an exception with the specified message.
     *
     * @param  message The detail message. The detail message is saved for 
     *         later retrieval by the {@link #getMessage()} method.
     */
    public GridNotEditableException(String message) {
        super(message);
    }
}
