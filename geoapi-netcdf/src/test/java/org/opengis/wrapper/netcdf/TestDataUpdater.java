/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    http://www.geoapi.org
 *
 *    This file is hereby placed into the Public Domain.
 *    This means anyone is free to do whatever they wish with this file.
 *
 *    The NetCDF wrappers are provided as code examples, in the hope to facilitate
 *    GeoAPI implementations backed by other libraries. Implementors can take this
 *    source code and use it for any purpose, commercial or non-commercial, copyrighted
 *    or open-source, with no legal obligation to acknowledge the borrowing/copying
 *    in any way.
 */
package org.opengis.wrapper.netcdf;

import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import ucar.nc2.Variable;
import ucar.nc2.Dimension;
import ucar.nc2.Attribute;
import ucar.nc2.NetcdfFile;
import ucar.nc2.NetcdfFileWriter;
import ucar.ma2.Array;

import static org.junit.Assert.*;


/**
 * A helper class for editing the netCDF test files provided in the {@code geoapi-conformance} module.
 * This is used for filtering the dimensions, variables and attributes of an existing netCDF file
 * in order to simplify it. This is also used for adding new attributes.
 *
 * <p>This class is used only as a helper tools on a case-by-case basis.
 * Its code need to be updated for each purpose.</p>
 *
 * @author  Martin Desruisseaux (Geomatys)
 * @version 3.1
 * @since   3.1
 */
final class TestDataUpdater {
    /**
     * Entry point to be updated for each purpose. Intentionally empty for the version in the source code repository;
     * must be updated by the developers according their purpose.
     *
     * @param  args ignored.
     * @throws Exception if an I/O error occurred on an error specific to the UCAR netCDF library.
     */
    public static void main(String[] args) throws Exception {
        final TestDataUpdater updater = new TestDataUpdater();
        /*
         * If there is a need to crop data, set the value here:
         */
//      updater.crop = true;
//      updater.upperX = ...;
//      updater.upperY = ...;
//      updater.upperZ = ...;
        /*
         * Update the following paths as needed:
         */
//      updater.filter("myInputFile.nc",
//                     "myOutputFile.nc");
    }

    /**
     * Whether to crop the data. If {@code true}, then {@link #lowerX}, {@link #lowerY}, {@link #upperX} and {@link #upperY}
     * must be given. Otherwise those values are ignored.
     */
    private boolean crop;

    /**
     * Index of the first cell to copy, inclusive.
     */
    private int lowerX, lowerY, lowerZ;

    /**
     * Index of the last cell to copy, exclusive.
     */
    private int upperX, upperY, upperZ;

    /**
     * Copies the given netCDF file with a filtering of dimensions, variables and attributes.
     */
    private void filter(final String input, final String output) throws Exception {
        try (NetcdfFile file = NetcdfFile.open(input)) {
            try (NetcdfFileWriter writer = NetcdfFileWriter.createNew(NetcdfFileWriter.Version.netcdf3, output)) {
                /*
                 * Copy only a subset of the dimensions.  We keep the dimensions required for describing spatio-temporal positions
                 * and discard all other dimensions. Those other dimensions are used in some files for storing additional metadata
                 * for which we did not defined a mapping to ISO metadata.
                 */
                final Map<String,Dimension> dimensions = new HashMap<>();
                final Map<String,Integer>   origins    = new HashMap<>();           // Used only if we crop data.
                for (final Dimension dim : file.getDimensions()) {
                    String name = dim.getShortName();
                    int length = dim.getLength();
                    int origin = 0;
                    switch (name) {
                        case "x0":   if (crop) length = upperX - (origin = lowerX); break;
                        case "y0":   if (crop) length = upperY - (origin = lowerY); break;
                        case "z0":   if (crop) length = upperZ - (origin = lowerZ); break;
                        case "time": break;
                        default: continue;      // Skip all dimensions not in above list.
                    }
                    assertNull(name, dimensions.put(name, writer.addDimension(null, name, length)));
                    assertNull(name, origins.put(name, origin));
                }
                /*
                 * Copy only the variables for which we have all dimensions. Then copy variable attributes except empty strings
                 * or Not-A-Number (NaN) values. Also arbitrarily exclude an hard-coded list of variables in order to make the
                 * test file simpler.
                 */
                final Map<String,Variable> variables = new HashMap<>();
nextVar:        for (final Variable var : file.getVariables()) {
                    final String name = var.getShortName();
                    switch (name) {
                        case "start_time":
                        case "stop_time":
                        case "forecast_period":
                        case "forecast_reference_time": continue;                   // Exclude variable for simplicity.
                    }
                    final List<Dimension> varDims = new ArrayList<>(4);
                    for (Dimension dim : var.getDimensions()) {
                        dim = dimensions.get(dim.getShortName());
                        if (dim == null) continue nextVar;                          // Exclude variable.
                        varDims.add(dim);
                    }
                    final Variable target = writer.addVariable(null, name, var.getDataType(), varDims);
                    assertNull(name, variables.put(name, target));
                    for (Attribute attr : var.getAttributes()) {
                        attr = filter(attr, var.getDimensions().isEmpty());
                        if (attr != null) {
                            target.addAttribute(attr);
                        }
                    }
                }
                /*
                 * Copy some of the global attributes, or generate new attributes specific to GeoAPI test file.
                 */
                for (Attribute attr : file.getGlobalAttributes()) {
                    attr = filter(attr, true);
                    if (attr != null) {
                        writer.addGroupAttribute(null, attr);
                    }
                }
                writer.addGroupAttribute(null, new Attribute("date_metadata_modified", "2018-05-14T14:45Z"));
                writer.addGroupAttribute(null, new Attribute("history", "Decimated and modified by GeoAPI for inclusion in conformance test suite."));
                writer.addGroupAttribute(null, new Attribute("purpose", "Conformance tests"));
                writer.addGroupAttribute(null, new Attribute("comment", "For testing purpose only."));
                /*
                 * Copy data, optionally cropping them to a sub-area.
                 */
                writer.create();
                for (final Variable source : file.getVariables()) {
                    final Variable target = variables.get(source.getShortName());
                    if (target != null) {
                        final Array data;
                        if (crop) {
                            final int[] shape  = source.getShape();
                            final int[] origin = new int[shape.length];
                            final List<Dimension> srcDim = source.getDimensions();
                            for (int i = srcDim.size(); --i >= 0;) {
                                final String name = srcDim.get(i).getShortName();
                                shape [i] = dimensions.get(name).getLength();
                                origin[i] = origins.get(name);
                            }
                            data = source.read(origin, shape);
                        } else {
                            data = source.read();
                        }
                        writer.write(target, data);
                    }
                }
            }
        }
    }

    /**
     * Returns the attribute to write in the filtered file, or {@code null} for omitting the given attribute.
     * This method returns the given attribute as-is if there is no change to apply.
     */
    private static Attribute filter(Attribute attr, final boolean isMetadata) {
        final String name = attr.getShortName();
        switch (name) {
            case "_CoordinateAxisType": break;              // Included if not NaN.
            case "_FillValue":
            case "missing_value": {                         // Included only if associated to actual data.
                if (isMetadata) return null;
                break;
            }
            case "history":                                 // To be excluded or replaced by our own value.
            case "purpose":
            case "comment":
            case "bounds":
            case "date_metadata_modified":
            case "FileFormat": return null;
            default: {                                      // Exclude all non-standard attributes other than above.
                if (name.startsWith("_")) return null;
                break;
            }
        }
        for (int i = attr.getLength(); --i >= 0;) {
            final Object value = attr.getValue(i);
            if (value instanceof CharSequence) {
                final String text = value.toString();
                final String modified = text.trim();
                if (!modified.isEmpty()) {
                    if (!modified.equals(text) && attr.getLength() == 1) {
                        return new Attribute(name, modified);
                    }
                    return attr;
                }
            }
            if (value instanceof Number) {
                if (!Double.isNaN(((Number) value).doubleValue())) {
                    return attr;
                }
            }
        }
        return null;
    }
}
