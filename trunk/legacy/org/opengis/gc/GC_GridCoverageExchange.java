/*
 * OpenGIS® Grid Coverage Implementation Specification
 *
 * This Java profile is derived from OpenGIS's specification
 * available on their public web site:
 *
 *     http://www.opengis.org/techno/implementation.htm
 *
 * You can redistribute it, but should not modify it unless
 * for greater OpenGIS compliance.
 */
package org.opengis.coverage.grid;

// Remote Method Invocation
import java.rmi.Remote;
import java.rmi.RemoteException;

// CSS dependencies
import org.opengis.crs.cs.CS_CoordinateSystem;
import org.opengis.ct.CT_MathTransform;


/**
 * Support for creation of grid coverages from persistent formats as well as exporting
 * a grid coverage to a persistent formats.
 * For example, it allows for creation of grid coverages from the GeoTIFF Well-known
 * binary format and exporting to the GeoTIFF file format.
 * Basic implementations only require creation of grid coverages from a file format or resource.
 *
 * @version 1.00
 * @since   1.00
 */
public interface GC_GridCoverageExchange extends Remote {
    /**
     * The number of formats supported by the <code>GC_GridCoverageExchange</code>.
     *
     * @return the number of formats supported by the <code>GC_GridCoverageExchange</code>.
     * @throws RemoteException if a remote method call failed.
     */
    int getNumFormats() throws RemoteException;

    /**
     * List of metadata keywords for the interface.
     * If no metadata is available, the sequnce will be empty.
     *
     * @return the list of metadata keywords for the interface.
     * @throws RemoteException if a remote method call failed.
     */
    String[] getMetadataNames() throws RemoteException;

    /**
     * Retrieve information on file formats or resources available with the
     * <code>GC_GridCoverageExchange</code> implementation.
     * Indices start at zero.
     *
     * @param index Index for which to retrieve the format information.
     * @return information on file formats or resources available with
     *         the <code>GC_GridCoverageExchange</code> implementation.
     * @throws RemoteException if a remote method call failed.
     */
    GC_Format getFormat(int index) throws RemoteException;

    /**
     * Retrieve the metadata value for a given metadata name.
     *
     * @param name Metadata keyword for which to retrieve metadata.
     * @return the metadata value for the given metadata name.
     * @throws RemoteException if a remote method call failed.
     */
    String getMetadataValue(String name) throws RemoteException;

    /**
     * Create a new {@link GC_GridCoverage} from a grid coverage file.
     * This method is meant to allow implementations to create a <code>GC_GridCoverage</code>.
     * from any file format. An implementation can support any number of formats which is
     * determined from the {@link GC_Format} interface.
     *
     * @param name File name (including path) from which to create a grid coverage interface.
     *             This file name can be any valid file name within the underlying operating
     *             system of the server or a valid string, such as a URL which specifics a grid
     *             coverage. Each implementation must determine if file name is valid for it's
     *             own use.
     *
     * @return a new {@link GC_GridCoverage}.
     * @throws RemoteException if a remote method call failed.
     */
    GC_GridCoverage createFromName(String name) throws RemoteException;

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
     * @return The list of grid coverages contained within the given file or resource.
     * @throws RemoteException if a remote method call failed.
     */
    String[] listSubNames(String name) throws RemoteException;

    /**
     * Create a new {@link GC_GridCoverage} from a file where the file contains many
     * grid coverages. This method is meant to allow implementations to create a
     * <code>GC_GridCoverage</code> from any file format which contains many grid
     * coverages. An example of such a format is HDF-EOS format.
     *
     * @param name File name (including path) from which to create a grid coverage interface.
     *             This file name can be any valid file name within the underlying operating
     *             system of the server or a valid string, such as a URL which specifics a
     *             grid coverage. Each implementation must determine if name is valid for it's
     *             own use.
     * @param subName Name of grid coverage contained in file name or resource.
     * @return a new {@link GC_GridCoverage} from a file where the file contains many grid coverages.
     * @throws RemoteException if a remote method call failed.
     */
    GC_GridCoverage createFromSubName(String name, String subName) throws RemoteException;

    /**
     * Export a grid coverage to a persistent file format.
     * The file format types are implementation specific.
     * The file format name is determined from the {@link GC_Format} interface.
     * Sample file formats include:
     *
     * <blockquote><pre>
     *   "GeoTIFF"   - GeoTIFF
     *   "PIX"       - PCI Geomatics PIX
     *   "HDF-EOS"   - NASA HDF-EOS
     *   "NITF"      - National Image Transfer Format
     *   "STDS-DEM"  - Standard Transfer Data Standard
     * </pre></blockquote>
     *
     * Other file format names are implementation dependent.
     *
     * @param gridCoverage Source grid coverage.
     * @param fileFormat String which indicates exported file format.
     * @param fileName File name to store grid coverage.
     *                 This file name can be any valid file name within the
     *                 underlying operating system of the server.
     *  @param creationOptions Options to use for creating the file.
     *                 These options are implementation specific are the valid
     *                 options is determined from the {@link GC_Format} interface.
     * @throws RemoteException if a remote method call failed.
     */
    void exportTo(GC_GridCoverage gridCoverage, String fileFormat, String fileName, GC_Parameter[] creationOptions) throws RemoteException;

    /**
     * Create a new coverage with a different coordinate reference system.
     *
     * @param gridCoverage Source grid coverage.
     * @param coordsys Coordinate system of the new grid coverage.
     * @param gridToCoordinateSystem Math transform to assign to grid coverage.
     * @return a new coverage with a different coordinate reference system.
     * @throws RemoteException if a remote method call failed.
     */
    GC_GridCoverage move(GC_GridCoverage gridCoverage, CS_CoordinateSystem coordsys, CT_MathTransform gridToCoordinateSystem) throws RemoteException;
}
