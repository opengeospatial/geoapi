/*
 * Copyright (C) 2003 Open GIS Consortium, Inc. All Rights Reserved. http://www.opengis.org/Legal/
 */
package org.opengis.gc;

// J2SE direct depencies
import java.io.IOException;
import javax.imageio.IIOException; // For Javadoc


/**
 * Throws when a {@linkplain GridCoverage grid coverage} can't be created.
 *
 * @UML exception GC_CannotCreateGridCoverage
 * @author <A HREF="http://www.opengis.org">OpenGIS&reg; consortium</A>
 * @version 1.1
 *
 * @see GridCoverageExchange#createFromName
 * @see GridCoverageExchange#createFromSubName
 *
 * @revisit In a J2SE 1.4 profile, this exception should extends {@link IIOException}.
 */
public class CannotCreateGridCoverageException extends IOException {
    /**
     * Serial number for interoperability with different versions.
     */
    private static final long serialVersionUID = 3768704221879769389L;

    /**
     * Creates an exception with no message.
     */
    public CannotCreateGridCoverageException() {
        super();
    }

    /**
     * Creates an exception with the specified message.
     *
     * @param  message The detail message. The detail message is saved for 
     *         later retrieval by the {@link #getMessage()} method.
     */
    public CannotCreateGridCoverageException(String message) {
        super(message);
    }
}
