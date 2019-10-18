/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    http://www.geoapi.org
 *
 *    This file is hereby placed into the Public Domain.
 *    This means anyone is free to do whatever they wish with this file.
 *
 *    The netCDF wrappers are provided as code examples, in the hope to facilitate
 *    GeoAPI implementations backed by other libraries. Implementers can take this
 *    source code and use it for any purpose, commercial or non-commercial, copyrighted
 *    or open-source, with no legal obligation to acknowledge the borrowing/copying
 *    in any way.
 */
package org.opengis.wrapper.netcdf;

import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.Comparator;
import ucar.nc2.Variable;
import ucar.nc2.Dimension;
import ucar.nc2.Attribute;
import ucar.nc2.NetcdfFile;
import ucar.nc2.NetcdfFileWriter;
import ucar.ma2.DataType;
import ucar.ma2.Array;
import ucar.ma2.Index;

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
 * @version 4.0
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
         * If there is a need to crop data, set the values here:
         */
//      updater.crop = true;
//      updater.upperX = ...;
//      updater.upperY = ...;
//      updater.upperZ = ...;
        /*
         * If float values should be converted to shorts, set the transfer function coefficients here.
         */
//      updater.pack   =  "SST";
//      updater.scale  =  0.0011;
//      updater.offset = -1.85;
        /*
         * Update the following paths as needed:
         */
//      updater.filter("myInputFile.nc",
//                     "myOutputFile.nc");
    }

    /**
     * Whether to crop the data. If {@code true}, then {@link #lowerX}, {@link #lowerY}, {@link #upperX}
     * and {@link #upperY} fields shall be assigned a value. Otherwise those values are ignored.
     */
    private boolean crop;

    /**
     * Index of the first cell to copy, inclusive.
     * Used only if {@link #crop} is {@code true}.
     */
    private int lowerX, lowerY, lowerZ;

    /**
     * Index of the last cell to copy, exclusive.
     * Used only if {@link #crop} is {@code true}.
     */
    private int upperX, upperY, upperZ;

    /**
     * Whether to pack data in {@code short} values. If non-null, then {@link #scale} and {@link #offset} fields
     * shall be assigned a value. Otherwise those values are ignored. The value is the name of the variable to pack.
     */
    private String pack;

    /**
     * The transfer function for converting integers to float values.
     * Used only if {@link #crop} is {@code true}.
     */
    private double scale, offset;

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
                        case "x0": case "lon": if (crop) length = upperX - (origin = lowerX); break;
                        case "y0": case "lat": if (crop) length = upperY - (origin = lowerY); break;
                        case "z0":             if (crop) length = upperZ - (origin = lowerZ); break;
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
                        case "reftime":
                        case "valtime":
                        case "forecast_period":
                        case "forecast_reference_time": continue;                   // Exclude variable for simplicity.
                    }
                    final List<Dimension> varDims = new ArrayList<>(4);
                    for (Dimension dim : var.getDimensions()) {
                        final String dn = dim.getShortName();
                        if (dim.getLength() == 1 && dn.equals("record")) {
                            continue;                                               // Ignore some dimensions of length 1.
                        }
                        dim = dimensions.get(dn);
                        if (dim == null) continue nextVar;                          // Exclude variable.
                        varDims.add(dim);
                    }
                    DataType type = var.getDataType();
                    if (name.equals(pack)) {
                        type = DataType.SHORT;
                    }
                    final Variable target = writer.addVariable(null, name, type, varDims);
                    assertNull(name, variables.put(name, target));
                    for (Attribute attr : var.getAttributes()) {
                        attr = filter(attr, var.getDimensions().isEmpty());
                        if (attr != null) {
                            target.addAttribute(attr);
                        }
                    }
                    if (pack.equals(name)) {
                        target.addAttribute(new Attribute("scale_factor", scale));
                        target.addAttribute(new Attribute("add_offset",   offset));
                    }
                }
                /*
                 * Copy some of the global attributes, or generate new attributes specific to GeoAPI test file.
                 * Attributes are sorted in an arbitrary order hard-coded in the AttributeComparator inner class.
                 */
                final List<Attribute> globalAttributes = new ArrayList<>();
                for (Attribute attr : file.getGlobalAttributes()) {
                    attr = filter(attr, true);
                    if (attr != null) {
                        globalAttributes.add(attr);
                    }
                }
                globalAttributes.add(new Attribute("Conventions",            "CF-1.4"));
                globalAttributes.add(new Attribute("date_modified",          "2018-05-15T13:00"));      // Edit with today date.
                globalAttributes.add(new Attribute("date_metadata_modified", "2018-05-15T13:01"));      // Idem.
                globalAttributes.add(new Attribute("history",                "Decimated and modified by GeoAPI for inclusion in conformance test suite."));
                globalAttributes.add(new Attribute("purpose",                "GeoAPI conformance tests"));
                globalAttributes.add(new Attribute("comment",                "For testing purpose only."));
                for (Attribute attr : AttributeComparator.sort(globalAttributes)) {
                    writer.addGroupAttribute(null, attr);
                }
                /*
                 * Copy data, optionally cropping them to a sub-area and packing them to short integer values.
                 */
                writer.create();
                for (final Variable source : file.getVariables()) {
                    final Variable target = variables.get(source.getShortName());
                    if (target != null) {
                        Array data;
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
                        while (data.getRank() > target.getRank()) {
                            data = data.slice(0, 0);
                        }
                        /*
                         * Replace float values by short values if requested.
                         */
                        if (source.getShortName().equals(pack)) {
                            final int[] shape = data.getShape();
                            final int size = Math.toIntExact(Index.computeSize(shape));
                            final Array packed = Array.factory(target.getDataType(), shape);
                            final Index index = packed.getIndex();
                            for (int i=0; i<size; i++) {
                                for (int r=i, j=shape.length; --j >= 0;) {
                                    final int s = shape[j];
                                    index.setDim(j, r % s);
                                    r /= s;
                                }
                                double value = data.getDouble(index);
                                value = (value - offset) / scale;
                                packed.setShort(index, (short) Math.max(0, Math.min(0x7FFF, Math.round(value))));
                            }
                            data = packed;
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
            case "version":
            case "purpose":
            case "comment":
            case "record":
            case "bounds":
            case "navigation":
            case "date_modified":
            case "date_metadata_modified":
            case "Conventions":
            case "FileFormat": return null;
            default: {                                      // Exclude all non-standard attributes other than above.
                if (name.startsWith("_") || name.startsWith("GRIB")) {
                    return null;
                }
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

    /**
     * A comparator for sorting attributes in some arbitrary order.
     */
    private static final class AttributeComparator implements Comparator<Attribute> {
        /** The desired attribute order. */
        private final List<String> order = Arrays.asList(
                "Conventions",
                "Metadata_Conventions",
                "title",
                "purpose",          // We put the purpose early because we use it for notifying that the file is for testing purpose.
                "summary",
                "source",
                "institution",
                "topic_category",
                "keywords",
                "keywords_vocabulary",
                "geospatial_lat_min",
                "geospatial_lat_max",
                "geospatial_lon_min",
                "geospatial_lon_max",
                "geospatial_vertical_min",
                "geospatial_vertical_max",
                "geospatial_vertical_positive",
                "geospatial_lat_resolution",
                "geospatial_lon_resolution",
                "time_coverage_start",
                "time_coverage_duration",
                "cdm_data_type",
                "id",
                "naming_authority",
                "creator_name",
                "creator_email",
                "date_created",
                "date_modified",
                "date_metadata_modified",
                "history",
                "comment",
                "license");

        /** Creates a new comparator. */
        private AttributeComparator() {
        }

        /** Compares the given attributes for order. */
        @Override public int compare(final Attribute o1, final Attribute o2) {
            return order.indexOf(o1.getShortName()) - order.indexOf(o2.getShortName());
        }

        /** Returns the attributes from the given list in order determined by this comparator. */
        static Attribute[] sort(final List<Attribute> attributes) {
            final Attribute[] a = attributes.toArray(new Attribute[attributes.size()]);
            Arrays.sort(a, new AttributeComparator());
            return a;
        }
    };
}
