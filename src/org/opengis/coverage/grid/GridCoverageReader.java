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
import java.io.FileNotFoundException;  // For Javadoc

// OpenGIS direct dependencies
import org.opengis.coverage.MetadataNameNotFoundException;
import org.opengis.parameter.ParameterNotFoundException;
import org.opengis.parameter.InvalidParameterNameException;
import org.opengis.parameter.InvalidParameterValueException;


/**
 * Support for reading grid coverages out of a persisten store.
 * Instance of <code>GridCoverageReader</code> are obtained through
 * a call to {@link GridCoverageExchange#getReader}.
 *
 * @author <A HREF="http://www.opengis.org">OpenGIS&reg; consortium</A>
 * @version 2.0
 *
 * @see GridCoverageExchange#getReader
 * @see javax.imageio.ImageReader
 */
public interface GridCoverageReader {
    /**
     * Returns the format handled by this <code>GridCoverageReader</code>.
     */
    Format getFormat();

    /**
     * Returns the input source. This is the object passed to the
     * {@link GridCoverageExchange#getReader} method.
     */
    Object getSource();

    /**
     * Returns the list of metadata keywords associated with the {@linkplain #getSource
     * input source} as a whole (not associated with any particular grid coverage).
     * If no metadata is available, the array will be empty.
     *
     * @return The list of metadata keywords for the input source.
     * @throws IOException if an error occurs during reading.
     */
    String[] getMetadataNames() throws IOException;

    /**
     * Retrieve the metadata value for a given metadata name.
     *
     * @param  name Metadata keyword for which to retrieve metadata.
     * @return The metadata value for the given metadata name. Should be one of
     *         the name returned by {@link #getMetadataNames}.
     * @throws IOException if an error occurs during reading.
     * @throws MetadataNameNotFoundException if there is no value for the specified metadata name.
     */
    String getMetadataValue(String name) throws IOException, MetadataNameNotFoundException;

    /**
     * Retrieve the list of grid coverages contained within the {@linkplain #getSource
     * input source}. Each grid can have a different coordinate system, number of dimensions
     * and grid geometry. For example, a HDF-EOS file (GRID.HDF) contains 6 grid coverages
     * each having a different projection. An empty array will be returned if no sub names
     * exist.
     *
     * @return The list of grid coverages contained within the input source.
     * @throws IOException if an error occurs during reading.
     *
     * @revisit The javadoc should also be more explicit about hierarchical format.
     *          Should the names be returned as paths?
     *          Explain what to return if the GridCoverage are accessible by index
     *          only. A proposal is to name them "grid1", "grid2", etc.
     */
    String[] listSubNames() throws IOException;

    /**
     * Read the specified grid coverage for the specified name.
     *
     * @param  name Name of grid coverage contained within the {@linkplain #getSource
     *         input source}. Should be one of the names returned by {@link #listSubNames}.
     * @param  parameters An optional set of parameters. Should be any or all of the
     *         parameters returned by {@link Format#getReadParameters}.
     * @return A new {@linkplain GridCoverage grid coverage} from the input source.
     * @throws InvalidParameterNameException if a parameter in <code>parameters</code>
     *         doesn't have a recognized name.
     * @throws InvalidParameterValueException if a parameter in <code>parameters</code>
     *         doesn't have a valid value.
     * @throws ParameterNotFoundException if a parameter was required for the operation but was
     *         not provided in the <code>parameters</code> list.
     * @throws CannotCreateGridCoverageException if the coverage can't be created for a logical
     *         reason (for example an unsupported format, or an inconsistency found in the data).
     * @throws IOException if a read operation failed for some other input/output reason, including
     *         {@link FileNotFoundException} if no file with the given <code>name</code> can be
     *         found, or {@link javax.imageio.IIOException} if an error was thrown by the
     *         underlying image library.
     */
    GridCoverage read(String name, Parameter[] parameters)
            throws InvalidParameterNameException, InvalidParameterValueException, ParameterNotFoundException, IOException;

    /**
     * Read the grid coverage at the specified index.
     *
     * @param  index Index of grid coverage contained within the {@linkplain #getSource
     *         input source}.
     * @param  parameters An optional set of parameters. Should be any or all of the
     *         parameters returned by {@link Format#getReadParameters}.
     * @return A new {@linkplain GridCoverage grid coverage} from the input source.
     * @throws InvalidParameterNameException if a parameter in <code>parameters</code>
     *         doesn't have a recognized name.
     * @throws InvalidParameterValueException if a parameter in <code>parameters</code>
     *         doesn't have a valid value.
     * @throws ParameterNotFoundException if a parameter was required for the operation but was
     *         not provided in the <code>parameters</code> list.
     * @throws CannotCreateGridCoverageException if the coverage can't be created for a logical
     *         reason (for example an unsupported format, or an inconsistency found in the data).
     * @throws IOException if a read operation failed for some other input/output reason, including
     *         {@link FileNotFoundException} if no file with the given <code>name</code> can be
     *         found, or {@link javax.imageio.IIOException} if an error was thrown by the
     *         underlying image library.
     */
    GridCoverage read(int index, Parameter[] parameters)
            throws InvalidParameterNameException, InvalidParameterValueException, ParameterNotFoundException, IOException;
}
