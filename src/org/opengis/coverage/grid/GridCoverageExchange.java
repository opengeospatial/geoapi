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
import java.io.FileNotFoundException;         // For Javadoc
import javax.imageio.IIOException;            // For Javadoc
import javax.imageio.ImageReader;             // For Javadoc
import javax.imageio.ImageWriter;             // For Javadoc
import javax.imageio.stream.ImageInputStream; // For Javadoc

// OpenGIS direct dependencies
import org.opengis.crs.crs.CRS;
import org.opengis.crs.operation.Conversion;
// import org.opengis.ct.MathTransform;
import org.opengis.coverage.MetadataNameNotFoundException;


/**
 * Support for creation of grid coverages from persistent formats as well as exporting
 * a grid coverage to a persistent formats.
 * For example, it allows for creation of grid coverages from the GeoTIFF Well-known
 * binary format and exporting to the GeoTIFF file format.
 * Basic implementations only require creation of grid coverages from a file format or resource.
 *
 * @UML abstract CV_GridCoverageExchange
 * @author <A HREF="http://www.opengis.org">OpenGIS&reg; consortium</A>
 * @version 1.1
 *
 * @see ImageReader
 * @see ImageWriter
 *
 * @revisit Using this interface means reopening the same file every time a new information
 *          is required for a given file. It would be better to gets this interface once from
 *          a given file or {@link ImageInputStream}, then invokes various methods without the
 *          file name argument. It would also allows {@link #getMetadataValue} method to work.
 *          <STRONG>IN SUMMARY: </STRONG> This interface need to be splitted into something like
 *          a <code>GridCoverageExchange</code>, <code>GridCoverageReader</code> and
 *          <code>GridCoverageWriter</code> interfaces.
 */
public interface GridCoverageExchange {
    /**
     * The number of formats supported by the <code>GridCoverageExchange</code>.
     *
     * @return The number of formats supported by the <code>GridCoverageExchange</code>.
     * @UML mandatory numFormats
     */
    int getNumFormats();

    /**
     * Retrieve information on file formats or resources available with the
     * <code>GridCoverageExchange</code> implementation. Indices start at zero.
     *
     * @param  index Index for which to retrieve the format information.
     * @return information on file formats or resources available with
     *         the <code>GridCoverageExchange</code> implementation.
     * @throws IndexOutOfBoundsException if <code>index</code> is out of bounds.
     * @UML operation getFormat
     */
    Format getFormat(int index) throws IndexOutOfBoundsException;

    /**
     * List of metadata keywords for the interface.
     * If no metadata is available, the sequnce will be empty.
     *
     * @return The list of metadata keywords for the interface.
     * @UML mandatory metadataNames
     */
    String[] getMetadataNames();

    /**
     * Retrieve the metadata value for a given metadata name.
     *
     * @param  name Metadata keyword for which to retrieve metadata.
     * @return The metadata value for the given metadata name.
     * @throws MetadataNameNotFoundException if there is no value for the specified metadata name.
     * @UML operation getMetadataValue
     */
    String getMetadataValue(String name) throws MetadataNameNotFoundException;

    /**
     * Create a new {@linkplain GridCoverage grid coverage} from a grid coverage file.
     * This method is meant to allow implementations to create a <code>GridCoverage</code>.
     * from any file format. An implementation can support any number of formats which is
     * determined from the {@link Format} interface.
     *
     * @param name File name (including path) from which to create a grid coverage interface.
     *             This file name can be any valid file name within the underlying operating
     *             system of the server or a valid string, such as a URL which specifics a grid
     *             coverage. Each implementation must determine if file name is valid for it's
     *             own use.
     *
     * @return a new {@linkplain GridCoverage grid coverage}.
     * @throws CannotCreateGridCoverageException if the coverage can't be created for a logical
     *         reason (for example an unsupported format, or an inconsistency found in the data).
     * @throws IOException if a read operation failed for some other input/output reason, including
     *         {@link FileNotFoundException} if no file with the given <code>name</code> can be
     *         found, or {@link IIOException} if an error was thrown by the underlying image
     *         library.
     *
     * @UML operation createFromName
     */
    GridCoverage createFromName(String name) throws IOException;

    /**
     * Retrieve the list of grid coverages contained within the given file or resource.
     * Each grid can have a different coordinate system, number of dimensions and grid geometry.
     * For example, a HDF-EOS file (GRID.HDF) contains 6 grid coverages each having a different
     * projection.
     *
     * An empty sequence will be returned if no sub names exist.
     *
     * @param name File name (including path) from which to retrieve the grid coverage names.
     *             This file name can be any valid file name within the underlying operating
     *             system of the server or a valid string, such as a URL which specifics a
     *             grid coverage. Each implementation must determine if file name is valid
     *             for it s own use. Implementations can support many different of file formats.
     *
     * @return The list of grid coverages contained within the given file or resource.
     * @throws IOException if a read operation failed.
     *
     * @UML operation listSubNames
     */
    String[] listSubNames(String name) throws IOException;

    /**
     * Create a new {@linkplain GridCoverage grid coverage} from a file where the file contains
     * many grid coverages. This method is meant to allow implementations to create a
     * <code>GridCoverage</code> from any file format which contains many grid
     * coverages. An example of such a format is HDF-EOS format.
     *
     * @param name File name (including path) from which to create a grid coverage interface.
     *             This file name can be any valid file name within the underlying operating
     *             system of the server or a valid string, such as a URL which specifics a
     *             grid coverage. Each implementation must determine if name is valid for it's
     *             own use.
     * @param  subName Name of grid coverage contained in file name or resource.
     * @return a new {@linkplain GridCoverage grid coverage} from a file where the file
     *         contains many grid coverages.
     * @throws CannotCreateGridCoverageException if the coverage can't be created for a logical
     *         reason (for example an unsupported format, or an inconsistency found in the data).
     * @throws IOException if a read operation failed for some other input/output reason, including
     *         {@link FileNotFoundException} if no file with the given <code>name</code> can be
     *         found, or {@link IIOException} if an error was thrown by the underlying image
     *         library.
     *
     * @UML operation createFromSubName
     */
    GridCoverage createFromSubName(String name, String subName) throws IOException;

    /**
     * Export a grid coverage to a persistent file format.
     * The file format types are implementation specific.
     * The file format name is determined from the {@link Format} interface.
     * Sample file formats include:
     *
     * <blockquote><table>
     *   <tr><td>"GeoTIFF"</td>  <td>&nbsp;&nbsp;- GeoTIFF</td></tr>
     *   <tr><td>"PIX"</td>      <td>&nbsp;&nbsp;- PCI Geomatics PIX</td></tr>
     *   <tr><td>"HDF-EOS"</td>  <td>&nbsp;&nbsp;- NASA HDF-EOS</td></tr>
     *   <tr><td>"NITF"</td>     <td>&nbsp;&nbsp;- National Image Transfer Format</td></tr>
     *   <tr><td>"STDS-DEM"</td> <td>&nbsp;&nbsp;- Standard Transfer Data Standard</td></tr>
     * </table></blockquote>
     *
     * Other file format names are implementation dependent.
     *
     * @param gridCoverage Source grid coverage.
     * @param fileFormat String which indicates exported file format.
     * @param fileName File name to store grid coverage.
     *                 This file name can be any valid file name within the
     *                 underlying operating system of the server.
     * @param creationOptions Options to use for creating the file.
     *                 These options are implementation specific are the valid
     *                 options is determined from the {@link Format} interface.
     * @throws InvalidParameterNameException if a parameter in <code>creationOptions</code>
     *         doesn't have a recognized name.
     * @throws InvalidParameterValueException if a parameter in <code>creationOptions</code>
     *         doesn't have a valid value.
     * @throws FileFormatNotCompatibleWithGridCoverageException if the grid coverage
     *         can't be exported in the specified format.
     * @throws IOException if the export failed for some other input/output reason, including
     *         {@link IIOException} if an error was thrown by the underlying image library.
     * @UML operation exportTo
     */
    void exportTo(GridCoverage gridCoverage, String fileFormat, String fileName, Parameter[] creationOptions)
            throws InvalidParameterNameException, InvalidParameterValueException, IOException;

    /**
     * Create a new coverage with a different coordinate reference system.
     *
     * @param gridCoverage Source grid coverage.
     * @param crs Coordinate system of the new grid coverage.
     * @param gridToCoordinateSystem Math transform to assign to grid coverage.
     * @return a new coverage with a different coordinate reference system.
     * @throws RemoteException if a remote method call failed.
     * @UML operation move
     *
     * @revisit <UL>
     *            <LI>The <code>gridToCoordinateSystem</code> was <code>MathTransform</code>; we don't
     *                know yet what will be the equivalent in the new CRS architecture.</LI>
     *            <LI>This method sound like a convenience method for
     * <code>{@link org.opengis.coverage.processing.GridCoverageProcessor GridCoverageProcessor}("Resample", ...)</code>
     *                operation. Is <code>GridCoverageExchange</code> really the right place to define it?</LI>
     *          </UL>
     */
    GridCoverage move(GridCoverage gridCoverage, CRS crs, Conversion gridToCoordinateSystem);
}
