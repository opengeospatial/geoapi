/*
 * Copyright (C) 2003 Open GIS Consortium, Inc. All Rights Reserved. http://www.opengis.org/Legal/
 */
package org.opengis.cv;

/**
 * Throws when a requested metadata is not found.
 *
 * @UML exception CV_MetadataNameNotFound
 * @author <A HREF="http://www.opengis.org">OpenGIS&reg; consortium</A>
 * @version 1.1
 *
 * @see Coverage#getMetadataValue
 * @see SampleDimension#getMetadataValue
 */
public class MetadataNameNotFoundException extends IllegalArgumentException {
    /**
     * Serial number for interoperability with different versions.
     */
    private static final long serialVersionUID = 3217010469714161299L;

    /**
     * Creates an exception with no message.
     */
    public MetadataNameNotFoundException() {
        super();
    }

    /**
     * Creates an exception with the specified message.
     *
     * @param  message The detail message. The detail message is saved for 
     *         later retrieval by the {@link #getMessage()} method.
     */
    public MetadataNameNotFoundException(String message) {
        super(message);
    }
}
