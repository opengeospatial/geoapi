/*$************************************************************************************************
 **
 ** $Id$
 **
 ** $Source$
 **
 ** Copyright (C) 2003 Open GIS Consortium, Inc. All Rights Reserved. http://www.opengis.org/Legal/
 **
 *************************************************************************************************/
package org.opengis.coverage.grid;

// J2SE direct dependencies
import java.io.IOException;


/**
 * Thrown when a {@linkplain GridCoverage grid coverage} can't be exported in the specified
 * format.
 *
 * @UML exception GC_FileFormatNotCompatibleWithGridCoverage
 * @author <A HREF="http://www.opengis.org">OpenGIS&reg; consortium</A>
 * @version <A HREF="http://www.opengis.org/docs/01-004.pdf">Grid Coverage specification 1.0</A>
 *
 * @see GridCoverageWriter#write
 *
 * @revisit In a J2SE 1.4 profile, this exception should extends
 *          {@link javax.imageio.IIOException}.
 *          This is also quite a long name. Can we make it shorter?
 */
public class FileFormatNotCompatibleWithGridCoverageException extends IOException {
    /**
     * Serial number for interoperability with different versions.
     */
    private static final long serialVersionUID = 9063391579226867676L;

    /**
     * Creates an exception with no message.
     */
    public FileFormatNotCompatibleWithGridCoverageException() {
        super();
    }

    /**
     * Creates an exception with the specified message.
     *
     * @param  message The detail message. The detail message is saved for 
     *         later retrieval by the {@link #getMessage()} method.
     */
    public FileFormatNotCompatibleWithGridCoverageException(String message) {
        super(message);
    }
}
