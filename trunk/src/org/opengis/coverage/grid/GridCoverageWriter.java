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

// J2SE dependencies
import java.io.IOException;

// OpenGIS direct dependencies
import org.opengis.coverage.MetadataNameNotFoundException;


/**
 * Support for writing grid coverages into a persistent store.
 * Instance of <code>GridCoverageWriter</code> are obtained through
 * a call to {@link GridCoverageExchange#getWriter}.
 *
 * @author <A HREF="http://www.opengis.org">OpenGIS&reg; consortium</A>
 * @version 1.1
 *
 * @see GridCoverageExchange#getWriter
 * @see javax.imageio.ImageWriter
 */
public interface GridCoverageWriter {
    /**
     * Returns the format handled by this <code>GridCoverageWriter</code>.
     */
    Format getFormat();

    /**
     * Returns the output destination. This is the object passed to the
     * {@link GridCoverageExchange#getWriter} method.
     */
    Object getDestination();

    /**
     * Returns the list of metadata keywords associated with the {@linkplain #getDestination
     * output destination} as a whole (not associated with any particular grid coverage).
     * If no metadata is allowed, the array will be empty.
     *
     * @return The list of metadata keywords for the output destination.
     * @throws IOException if an error occurs during reading or writing.
     */
    String[] getMetadataNames();

    /**
     * Sets the metadata value for a given metadata name.
     *
     * @param  name Metadata keyword for which to set the metadata.
     * @param  value The metadata value for the given metadata name.
     * @throws IOException if an error occurs during writing.
     * @throws MetadataNameNotFoundException if the specified metadata name is not handled
     *         for this format.
     */
    void setMetadataValue(String name, String value) throws IOException, MetadataNameNotFoundException;

    /**
     * Writes the specified grid on the location specified by the name. If a grid
     * coverage is already in the destination, it will be overwritten. The name
     * will be ignored for format that doesn't support subnames.
     *
     * @param  name Name of grid coverage contained within the {@linkplain #getDestination
     *         output destination}.
     * @param  coverage The {@linkplain GridCoverage grid coverage} to write.
     * @param  parameters An optional set of parameters. Should be any or all of the
     *         parameters returned by {@link Format#getWriteParameters}.
     * @throws InvalidParameterNameException if a parameter in <code>parameters</code>
     *         doesn't have a recognized name.
     * @throws InvalidParameterValueException if a parameter in <code>parameters</code>
     *         doesn't have a valid value.
     * @throws FileFormatNotCompatibleWithGridCoverageException if the grid coverage
     *         can't be exported in the {@linkplain #getFormat writer format}.
     * @throws IOException if the export failed for some other input/output reason, including
     *         {@link javax.imageio.IIOException} if an error was thrown by the underlying
     *         image library.
     */
    void write(String name, GridCoverage coverage, Parameter[] parameters)
        throws InvalidParameterNameException, InvalidParameterValueException, IOException;
}
