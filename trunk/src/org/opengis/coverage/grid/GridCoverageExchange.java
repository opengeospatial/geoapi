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
 * Support for creation of grid coverages from persistent formats as well as exporting
 * a grid coverage to a persistent formats.
 * For example, it allows for creation of grid coverages from the GeoTIFF Well-known
 * binary format and exporting to the GeoTIFF file format.
 * Basic implementations only require creation of grid coverages from a file format or resource.
 *
 * @UML abstract CV_GridCoverageExchange
 * @author <A HREF="http://www.opengis.org">OpenGIS&reg; consortium</A>
 * @version 2.0
 *
 * @see javax.imageio.ImageReader
 * @see javax.imageio.ImageWriter
 */
public interface GridCoverageExchange {
    /**
     * Retrieve information on file formats or resources available with the
     * <code>GridCoverageExchange</code> implementation.
     *
     * @return Information on file formats or resources available with
     *         the <code>GridCoverageExchange</code> implementation.
     *
     * @UML operation getFormat
     * @UML mandatory numFormats
     */
    Format[] getFormats();

    /**
     * Returns a grid coverage reader that can manage the specified source
     *
     * @param  source An object that specifies somehow the data source. Can be a
     *         {@link java.lang.String}, an {@link java.io.InputStream}, a
     *         {@link java.nio.channels.FileChannel}, whatever. It's up to the associated
     *         grid coverage reader to make meaningful use of it.
     * @param  format the format of the source data. If null, this class will try to
     *         guess an appropriate grid coverage reader by inspecting the source object.
     * @return The grid coverage reader.
     * @throws IOException if an error occurs during reading.
     *
     * @revisit We need a mechanism to allow the right GridCoverageReader
     *          Something like an SPI. What if we can't find a GridCoverageReader?
     *          Do we return null or throw an Exception?
     */
    GridCoverageReader getReader(Object source, Format format) throws IOException;

    /**
     * Returns a GridCoverageWriter that can write the specified format.
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
     * @param  destination An object that specifies somehow the data destination.
     *         Can be a {@link java.lang.String}, an {@link java.io.OutputStream},
     *         a {@link java.nio.channels.FileChannel}, whatever. It's up to the
     *         associated grid coverage writer to make meaningful use of it.
     * @param  format the output format.
     * @return The grid coverage writer.
     * @throws IOException if an error occurs during reading.
     */
    GridCoverageWriter getWriter(Object destination, Format format) throws IOException;
}
