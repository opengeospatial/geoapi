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

//OpenGIS direct dependencies
import org.opengis.util.CodeList;
import org.opengis.crs.crs.CRS;         // For Javadoc
import org.opengis.crs.operation.Conversion;  // For Javadoc


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
 * @revisit Do we really need this enumeration? We could uses {@link Class} instead.
 */
public final class ParameterType extends CodeList {
    /**
     * Serial number for compatibility with different versions.
     */
    private static final long serialVersionUID = -5571839103492463917L;

    /**
     * Integer parameter ({@link Integer}).
     *
     * @UML conditional GC_IntegerType
     */
    public static final ParameterType INTEGER_TYPE = new ParameterType("INTEGER_TYPE", 0);

    /**
     * Floating point parameter ({@link Float} or {@link Double}).
     *
     * @UML conditional GC_FloatingPointType
     */
    public static final ParameterType FLOATING_POINT_TYPE = new ParameterType("FLOATING_POINT_TYPE", 1);

    /**
     * String parameter ({@link String}).
     *
     * @UML conditional GC_StringType
     */
    public static final ParameterType STRING_TYPE = new ParameterType("STRING_TYPE", 2);

    /**
     * Sequence of numbers.
     *
     * @UML conditional GC_VectorType
     */
    public static final ParameterType VECTOR_TYPE = new ParameterType("VECTOR_TYPE", 3);

    /**
     * Grid coverage instance ({@link GridCoverage}).
     *
     * @UML conditional GC_GridCoverageType
     */
    public static final ParameterType GRID_COVERAGE_TYPE = new ParameterType("GRID_COVERAGE_TYPE", 4);

    /**
     * Conversion instance ({@linkplain Conversion}).
     *
     * @UML conditional CT_MathTransformType
     */
    public static final ParameterType CONVERSION_TYPE = new ParameterType("CONVERSION_TYPE", 5);

    /**
     * Coordinate reference system instance ({@link CRS}).
     *
     * @UML conditional CS_CoordinateSystemType
     */
    public static final ParameterType CRS_TYPE = new ParameterType("CRS_TYPE", 6);

    /**
     * Grid geometry instance ({@link GridGeometry}).
     *
     * @UML conditional GC_GridGeometryType
     */
    public static final ParameterType GRID_GEOMETRY_TYPE = new ParameterType("GRID_GEOMETRY_TYPE", 7);

    /**
     * Byte in value packing enumeration ({@link ByteInValuePacking}).
     *
     * @UML conditional GC_ByteInValuePackingType
     */
    public static final ParameterType BYTE_IN_VALUE_PACKING_TYPE = new ParameterType("BYTE_IN_VALUE_PACKING_TYPE", 8);

    /**
     * Value in byte packing enumeration ({@link ValueInBytePacking}).
     *
     * @UML conditional GC_ValueInBytePackingType
     */
    public static final ParameterType VALUE_IN_BYTE_PACKING_TYPE = new ParameterType("VALUE_IN_BYTE_PACKING_TYPE", 9);

    /**
     * List of all enumerations of this type.
     */
    private static final ParameterType[] VALUES = new ParameterType[] {
            INTEGER_TYPE, FLOATING_POINT_TYPE, STRING_TYPE, VECTOR_TYPE, GRID_COVERAGE_TYPE,
            CONVERSION_TYPE, CRS_TYPE, GRID_GEOMETRY_TYPE, BYTE_IN_VALUE_PACKING_TYPE,
            VALUE_IN_BYTE_PACKING_TYPE };

    /**
     * Constructs an enum with the given name.
     */
    private ParameterType(final String name, final int ordinal) {
        super(name, ordinal);
    }

    /**
     * Returns the list of <code>ParameterType</code>s.
     */
    public static ParameterType[] values() {
        return (ParameterType[]) VALUES.clone();
    }

    /**
     * Returns the list of enumerations of the same kind than this enum.
     */
    public /*{ParameterType}*/ CodeList[] family() {
        return values();
    }
}
