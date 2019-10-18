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

import java.awt.geom.AffineTransform;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import org.gdal.gdal.Dataset;
import org.opengis.metadata.spatial.CellGeometry;
import org.opengis.metadata.spatial.Dimension;
import org.opengis.metadata.spatial.DimensionNameType;
import org.opengis.metadata.spatial.GridSpatialRepresentation;
import org.opengis.referencing.crs.CoordinateReferenceSystem;


/**
 * Information about the grid of a GDAL raster.
 *
 * @author  Martin Desruisseaux (Geomatys)
 * @version 3.1
 * @since   3.1
 *
 * @see <a href="http://gdal.org/gdal_datamodel.html">GDAL data model</a>
 * @see <a href="http://gdal.org/java/org/gdal/gdal/Dataset.html">Java API for GDAL Dataset</a>
 */
class GridGeometry implements GridSpatialRepresentation {
    /**
     * Raster shape (size, number of bands).
     */
    private final int xSize, ySize, numBands;

    /**
     * Transformation from grid coordinates to geographic or projected coordinates.
     * The (0,0) pixel coordinates map to the top-left pixel corner (not center).
     */
    private final AffineTransform gridToCRS;

    /**
     * The coordinate reference system. This is the target of {@link #gridToCRS}.
     * May be {@code null} if the CRS is unknown or unsupported.
     */
    private final CoordinateReferenceSystem crs;

    /**
     * Fetches metadata from the given GDAL dataset.
     */
    GridGeometry(final Dataset ds) throws IOException {
        if (ds == null) {
            throw new IOException("DataSet is closed.");
        }
        xSize    = ds.getRasterXSize();
        ySize    = ds.getRasterYSize();
        numBands = ds.getRasterCount();
        final double[] gt = ds.GetGeoTransform();
        gridToCRS = new AffineTransform(gt[1], gt[4], gt[2], gt[5], gt[0], gt[3]);
        crs = CRS.create(ds.GetProjection());
    }

    /**
     * Information about the <var>x</var> or <var>y</var> axis of a raster.
     */
    private static final class Axis implements Dimension {
        /** 0 for <var>x</var>, 1 for <var>y</var> (more may be added in the future). */
        private final byte dimension;

        /** Number of pixels in this dimension. */
        private final int size;

        /** Stores information about the given raster dimension. */
        Axis(final byte dimension, final int size) {
            this.dimension = dimension;
            this.size      = size;
        }

        /** Tells whether this dimension is about x or y axis. */
        @Override public DimensionNameType getDimensionName() {
            switch (dimension) {
                case 0:  return DimensionNameType.COLUMN;
                case 1:  return DimensionNameType.ROW;
                default: return null;
            }
        }

        /** Number of elements along the axis. */
        @Override public Integer getDimensionSize() {
            return size;
        }

        /** String representation for debugging purpose. */
        @Override public String toString() {
            final StringBuilder b = new StringBuilder().append(size).append(' ').append(getDimensionName().identifier());
            if (size >= 2) b.append('s');
            return b.toString();
        }
    }

    /**
     * Returns the length of the list to be returned by {@link #getAxisDimensionProperties()}.
     */
    @Override
    public final Integer getNumberOfDimensions() {
        return 2;      // Must match the length of the list created below.
    }

    /**
     * Returns information about each axes of the grid.
     */
    @Override
    public final List<Dimension> getAxisDimensionProperties() {
        return Arrays.<Dimension>asList(new Axis((byte) 0, xSize), new Axis((byte) 1, ySize));
    }

    /**
     * Tells whether each point represents a cell, and area or a volume.
     */
    @Override
    public CellGeometry getCellGeometry() {
        return null;
    }

    /**
     * Indication of whether or not parameters for transformation exists.
     */
    @Override
    public final boolean isTransformationParameterAvailable() {
        return false;
    }
}
