/*
 * Copyright (C) 2003 Open GIS Consortium, Inc. All Rights Reserved. http://www.opengis.org/Legal/
 */
package org.opengis.gc;

//J2SE direct dependencies
import java.util.List;
import java.util.Arrays;
import java.util.Collections;
import java.nio.ByteOrder;         // For Javadoc

//OpenGIS direct dependencies
import org.opengis.util.CodeList;
import org.opengis.sc.CRS;         // For Javadoc
import org.opengis.cc.Conversion;  // For Javadoc


/**
 * Specifies the type of a parameter value.
 * A sequence of parameters is used to pass a variable number of arguments to
 * an operation and each of these parameters can have a different parameter type.
 * An operation requiring a string and grid coverage would use two parameters for
 * type {@link #STRING_TYPE} and {@link #GRID_COVERAGE_TYPE}.
 *
 * @UML codelist GC_ParameterType
 * @author <A HREF="http://www.opengis.org">OpenGIS&reg; consortium</A>
 * @version 1.1
 *
 * @revisit Localize. Defines serialVersionUID.
 */
public final class ParameterType extends CodeList {
    /**
     * Integer parameter ({@link Integer}).
     *
     * @UML conditional GC_IntegerType
     */
    public static final ParameterType INTEGER_TYPE = new ParameterType(0, "INTEGER_TYPE");

    /**
     * Floating point parameter ({@link Float} or {@link Double}).
     *
     * @UML conditional GC_FloatingPointType
     */
    public static final ParameterType FLOATING_POINT_TYPE = new ParameterType(1, "FLOATING_POINT_TYPE");

    /**
     * String parameter ({@link String}).
     *
     * @UML conditional GC_StringType
     */
    public static final ParameterType STRING_TYPE = new ParameterType(2, "STRING_TYPE");

    /**
     * Sequence of numbers.
     *
     * @UML conditional GC_VectorType
     */
    public static final ParameterType VECTOR_TYPE = new ParameterType(3, "VECTOR_TYPE");

    /**
     * Grid coverage instance ({@link GridCoverage}).
     *
     * @UML conditional GC_GridCoverageType
     */
    public static final ParameterType GRID_COVERAGE_TYPE = new ParameterType(4, "GRID_COVERAGE_TYPE");

    /**
     * Conversion instance ({@linkplain Conversion}).
     *
     * @UML conditional CT_MathTransformType
     */
    public static final ParameterType CONVERSION_TYPE = new ParameterType(5, "CONVERSION_TYPE");

    /**
     * Coordinate reference system instance ({@link CRS}).
     *
     * @UML conditional CS_CoordinateSystemType
     */
    public static final ParameterType CRS_TYPE = new ParameterType(6, "CRS_TYPE");

    /**
     * Grid geometry instance ({@link GridGeometry}).
     *
     * @UML conditional GC_GridGeometryType
     */
    public static final ParameterType GRID_GEOMETRY_TYPE = new ParameterType(7, "GRID_GEOMETRY_TYPE");

    /**
     * Byte in value packing enumeration ({@link ByteInValuePacking}).
     *
     * @UML conditional GC_ByteInValuePackingType
     */
    public static final ParameterType BYTE_IN_VALUE_PACKING_TYPE = new ParameterType(8, "BYTE_IN_VALUE_PACKING_TYPE");

    /**
     * Value in byte packing enumeration ({@link ValueInBytePacking}).
     *
     * @UML conditional GC_ValueInBytePackingType
     */
    public static final ParameterType VALUE_IN_BYTE_PACKING_TYPE = new ParameterType(9, "VALUE_IN_BYTE_PACKING_TYPE");

    /**
     * List of all enumerations of this type.
     */
    private static final List VALUES = Collections.unmodifiableList(Arrays.asList(new ParameterType[]{
            INTEGER_TYPE, FLOATING_POINT_TYPE, STRING_TYPE, VECTOR_TYPE, GRID_COVERAGE_TYPE,
            CONVERSION_TYPE, CRS_TYPE, GRID_GEOMETRY_TYPE, BYTE_IN_VALUE_PACKING_TYPE,
            VALUE_IN_BYTE_PACKING_TYPE}));

    /**
     * Constructs an enum with the given name.
     */
    private ParameterType(final int ordinal, final String name) {
        super(ordinal, name);
    }

    /**
     * Returns the list of <code>ParameterType</code>s.
     */
    public static List values() {
        return VALUES;
    }

    /**
     * Returns the list of enumerations of the same kind than this enum.
     */
    public List family() {
        return VALUES;
    }
}
