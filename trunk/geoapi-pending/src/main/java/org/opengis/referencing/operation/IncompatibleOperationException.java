/*$************************************************************************************************
 **
 ** $Id$
 **
 ** $URL$
 **
 ** Copyright (C) 2003-2005 Open GIS Consortium, Inc.
 ** All Rights Reserved. http://www.opengis.org/legal/
 **
 *************************************************************************************************/
package org.opengis.referencing.operation;

import org.opengis.annotation.Extension;


/**
 * Thrown when an operation is applied in a manner inconsistent with one or both of
 * two particular CRS objects.
 *
 * @author  Jesse Crossley (SYS Technologies)
 * @since   GeoAPI 1.0
 *
 * @deprecated This GeoAPI extension is not used in any GeoAPI interface.
 */
@Extension
@Deprecated
public class IncompatibleOperationException extends Exception {
    /**
     * Serial number for interoperability with different versions.
     */
    private static final long serialVersionUID = 3197174832430350656L;

    /**
     * The invalid Operation name.
     */
    private final String operationName;

    /**
     * Creates an exception with the specified message and operation name.
     *
     * @param  message The detail message. The detail message is saved for
     *         later retrieval by the {@link #getMessage()} method.
     * @param  operationName The invalid operation name.
     */
    public IncompatibleOperationException(String message, String operationName) {
        super(message);
        this.operationName = operationName;
    }

    /**
     * Returns the invalid operation name.
     *
     * @return The invalid operation name.
     */
    public String getOperationName() {
        return operationName;
    }
}
