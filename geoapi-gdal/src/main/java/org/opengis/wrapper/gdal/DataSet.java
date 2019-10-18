/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    http://www.geoapi.org
 *
 *    This file is hereby placed into the Public Domain.
 *    This means anyone is free to do whatever they wish with this file.
 *
 *    The GDAL wrappers are provided as code examples, in the hope to facilitate
 *    GeoAPI implementations backed by other libraries. Implementers can take this
 *    source code and use it for any purpose, commercial or non-commercial, copyrighted
 *    or open-source, with no legal obligation to acknowledge the borrowing/copying
 *    in any way.
 */
package org.opengis.wrapper.gdal;

import java.io.Closeable;
import java.io.IOException;
import java.nio.file.Path;
import org.opengis.metadata.Metadata;
import org.gdal.gdal.Dataset;
import org.gdal.gdal.gdal;


/**
 * A file opened by GDAL.
 *
 * @author  Martin Desruisseaux (Geomatys)
 * @version 3.1
 * @since   3.1
 */
public class DataSet implements Closeable {
    /**
     * The GDAL data set, or {@code null} if the dataset has been closed.
     */
    private Dataset ds;

    /**
     * The raster metadata, fetched when first needed.
     */
    private Metadata metadata;

    /**
     * Opens a dataset for the given file in read-only mode.
     *
     * @param  file  the file to open.
     * @throws IOException if the given file can not be opened.
     */
    public DataSet(final Path file) throws IOException {
        ds = gdal.Open(file.toString());
        if (ds == null) {
            String msg = gdal.GetLastErrorMsg();
            if (msg == null) {
                msg = "Can not open \"" + file + "\".";
            }
            throw new GDALException(msg);
        }
    }

    /**
     * Returns information about the dataset as ISO 19115 metadata.
     *
     * @return information about the dataset.
     * @throws IOException if an error occurred while fetching the metadata.
     */
    public synchronized Metadata getMetadata() throws IOException {
        if (metadata == null) {
            metadata = new RasterMetadata(ds);
        }
        return metadata;
    }

    /*
     * See http://www.gdal.org/gdal_tutorial.html
     */

    /**
     * Disposes native resources used by this dataset.
     * If this method is invoked more than once, invocations after the first call have no effect.
     */
    @Override
    public synchronized void close() {
        final Dataset data = ds;
        if (data != null) {
            ds = null;                  // Discard now in case of failure below.
            data.delete();
        }
    }
}
