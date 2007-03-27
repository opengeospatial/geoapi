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
package org.opengis.coverage.grid;

// J2SE direct dependencies
import java.io.IOException;

// Annotations
import org.opengis.annotation.UML;
import static org.opengis.annotation.Obligation.*;
import static org.opengis.annotation.Specification.*;


/**
 * Support for creation of grid coverages from persistent formats as well as exporting
 * a grid coverage to a persistent formats. For example, it allows for creation of grid
 * coverages from the GeoTIFF Well-known binary format and exporting to the GeoTIFF file format.
 * Basic implementations only require creation of grid coverages from a file format or resource.
 * More sophesticated implementations may extract the grid coverages from a database. In such
 * case, a {@code GridCoverageExchange} instance will hold a connection to a specific
 * database and the {@link #dispose} method will need to be invoked in order to close this
 * connection.
 *
 * <P>&nbsp;</P>
 * <TABLE WIDTH="80%" ALIGN="center" CELLPADDING="18" BORDER="4" BGCOLOR="#FFE0B0">
 *   <TR><TD>
 *     <P align="justify"><STRONG>WARNING: THIS CLASS WILL CHANGE.</STRONG> Current API is derived from OGC
 *     <A HREF="http://www.opengis.org/docs/01-004.pdf">Grid Coverages Implementation specification 1.0</A>.
 *     We plan to replace it by new interfaces derived from ISO 19123 (<CITE>Schema for coverage geometry
 *     and functions</CITE>). Current interfaces should be considered as legacy and are included in this
 *     distribution only because they were part of GeoAPI 1.0 release. We will try to preserve as much 
 *     compatibility as possible, but no migration plan has been determined yet.</P>
 *   </TD></TR>
 * </TABLE>
 *
 * @author Martin Desruisseaux (IRD)
 * @since GeoAPI 2.0
 * @deprecated In favor of migrating to ISO 19123 definition for Coverage.
 *
 * @see GridCoverageReader
 * @see GridCoverageWriter
 */
@UML(identifier="CV_GridCoverageExchange", specification=OGC_01004)
public interface GridCoverageExchange {
    /**
     * Retrieve information on file formats or resources available with the
     * {@code GridCoverageExchange} implementation.
     *
     * @return Information on file formats or resources available with
     *         the {@code GridCoverageExchange} implementation.
     */
    @UML(identifier="getFormat", obligation=MANDATORY, specification=OGC_01004)
    Format[] getFormats();

    /**
     * Returns a grid coverage reader that can manage the specified source
     *
     * @param  source An object that specifies somehow the data source. Can be a
     *         {@link java.lang.String}, an {@link java.io.InputStream}, a
     *         {@link java.nio.channels.FileChannel}, whatever. It's up to the associated
     *         grid coverage reader to make meaningful use of it.
     * @return The grid coverage reader.
     * @throws IOException if the format is not recognized, or if an error occurs during reading.
     */
    GridCoverageReader getReader(Object source) throws IOException;

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

    /**
     * Allows any resources held by this object to be released. The result of calling any other
     * method subsequent to a call to this method is undefined. Applications should call this
     * method when they know they will no longer be using this {@code GridCoverageExchange},
     * especially if it was holding a connection to a database.
     *
     * @throws IOException if an error occured while disposing resources
     *         (for example closing a database connection).
     */
    void dispose() throws IOException;
}
