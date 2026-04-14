/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    Copyright © 2007-2024 Open Geospatial Consortium, Inc.
 *    http://www.geoapi.org
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */
package org.opengis.filter.capability;

import java.util.List;
import org.opengis.util.CodeList;


/**
 * Enumeration of the different {@code GeometryOperand} types.
 *
 * {@snippet lang="xml" :
 * <xsd:simpleType name="GeometryOperandType">
 *   <xsd:restriction base="xsd:QName">
 *       <xsd:enumeration value="gml:Envelope"/>
 *       <xsd:enumeration value="gml:Point"/>
 *       <xsd:enumeration value="gml:LineString"/>
 *       <xsd:enumeration value="gml:Polygon"/>
 *       <xsd:enumeration value="gml:ArcByCenterPoint"/>
 *       <xsd:enumeration value="gml:CircleByCenterPoint"/>
 *       <xsd:enumeration value="gml:Arc"/>
 *       <xsd:enumeration value="gml:Circle"/>
 *       <xsd:enumeration value="gml:ArcByBulge"/>
 *       <xsd:enumeration value="gml:Bezier"/>
 *       <xsd:enumeration value="gml:Clothoid"/>
 *       <xsd:enumeration value="gml:CubicSpline"/>
 *       <xsd:enumeration value="gml:Geodesic"/>
 *       <xsd:enumeration value="gml:OffsetCurve"/>
 *       <xsd:enumeration value="gml:Triangle"/>
 *       <xsd:enumeration value="gml:PolyhedralSurface"/>
 *       <xsd:enumeration value="gml:TriangulatedSurface"/>
 *       <xsd:enumeration value="gml:Tin"/>
 *       <xsd:enumeration value="gml:Solid"/>
 *    </xsd:restriction>
 * </xsd:simpleType>
 * }
 *
 * @author Justin Deoliveira (The Open Planning Project)
 * @author Martin Desruisseaux (Geomatys)
 *
 * @deprecated This is replaced by scoped names in
 * {@link org.opengis.filter.capability.SpatialCapabilities#getGeometryOperands()}.
 */
@Deprecated
public final class GeometryOperand extends CodeList<GeometryOperand> {
    /**
     * For cross-version compatibility.
     */
    private static final long serialVersionUID = 6517166553565301182L;

    /** {@code "http://www.opengis.net/gml/Envelope"} */
    public static final GeometryOperand Envelope;

    /** {@code "http://www.opengis.net/gml/Point"} */
    public static final GeometryOperand Point;

    /** {@code "http://www.opengis.net/gml/LineString"} */
    public static final GeometryOperand LineString;

    /** {@code "http://www.opengis.net/gml/Polygon"} */
    public static final GeometryOperand Polygon;

    /** {@code "http://www.opengis.net/gml/ArcByCenterPoint"} */
    public static final GeometryOperand ArcByCenterPoint;

    /** {@code "http://www.opengis.net/gml/CircleByCenterPoint"} */
    public static final GeometryOperand CircleByCenterPoint;

    /** {@code "http://www.opengis.net/gml/Arc"} */
    public static final GeometryOperand Arc;

    /** {@code "http://www.opengis.net/gml/Circle"} */
    public static final GeometryOperand Circle;

    /** {@code "http://www.opengis.net/gml/ArcByBulge"} */
    public static final GeometryOperand ArcByBulge;

    /** {@code "http://www.opengis.net/gml/Bezier"} */
    public static final GeometryOperand Bezier;

    /** {@code "http://www.opengis.net/gml/Clothoid"} */
    public static final GeometryOperand Clothoid;

    /** {@code "http://www.opengis.net/gml/CubicSpline"} */
    public static final GeometryOperand CubicSpline;

    /** {@code "http://www.opengis.net/gml/Geodesic"} */
    public static final GeometryOperand Geodesic;

    /** {@code "http://www.opengis.net/gml/OffsetCurve"} */
    public static final GeometryOperand OffsetCurve;

    /** {@code "http://www.opengis.net/gml/Triangle"} */
    public static final GeometryOperand Triangle;

    /** {@code "http://www.opengis.net/gml/PolyhedralSurface"} */
    public static final GeometryOperand PolyhedralSurface;

    /** {@code "http://www.opengis.net/gml/TriangulatedSurface"} */
    public static final GeometryOperand TriangulatedSurface;

    /** {@code "http://www.opengis.net/gml/Tin"} */
    public static final GeometryOperand Tin;

    /** {@code "http://www.opengis.net/gml/Solid"} */
    public static final GeometryOperand Solid;

    /**
     * All code list values created in the currently running <abbr>JVM</abbr>.
     */
    private static final List<GeometryOperand> VALUES = initialValues(
        // Inline assignments for getting compiler error if a field is missing or duplicated.
        Envelope            = new GeometryOperand("Envelope"),
        Point               = new GeometryOperand("Point"),
        LineString          = new GeometryOperand("LineString"),
        Polygon             = new GeometryOperand("Polygon"),
        ArcByCenterPoint    = new GeometryOperand("ArcByCenterPoint"),
        CircleByCenterPoint = new GeometryOperand("CircleByCenterPoint"),
        Arc                 = new GeometryOperand("Arc"),
        Circle              = new GeometryOperand("Circle"),
        ArcByBulge          = new GeometryOperand("ArcByBulge"),
        Bezier              = new GeometryOperand("Bezier"),
        Clothoid            = new GeometryOperand("Clothoid"),
        CubicSpline         = new GeometryOperand("CubicSpline"),
        Geodesic            = new GeometryOperand("Geodesic"),
        OffsetCurve         = new GeometryOperand("OffsetCurve"),
        Triangle            = new GeometryOperand("Triangle"),
        PolyhedralSurface   = new GeometryOperand("PolyhedralSurface"),
        TriangulatedSurface = new GeometryOperand("TriangulatedSurface"),
        Tin                 = new GeometryOperand("Tin"),
        Solid               = new GeometryOperand("Solid"));

    /**
     * Creates an operand in the {@code "http://www.opengis.net/gml"} namespace.
     *
     * @param  name  the name of the new element. This name must not be in use by another element of this type.
     */
    private GeometryOperand(final String name) {
        super(name);
    }

    /**
     * Returns the list of {@code GeometryOperand}s.
     *
     * @return the list of codes declared in the current JVM.
     */
    public static GeometryOperand[] values() {
        return VALUES.toArray(GeometryOperand[]::new);
    }

    /**
     * Returns the list of codes of the same kind as this code list element.
     * Invoking this method is equivalent to invoking {@link #values()}, except that
     * this method can be invoked on an instance of the parent {@code CodeList} class.
     *
     * @return all code {@linkplain #values() values} for this code list.
     */
    @Override
    public GeometryOperand[] family() {
        return values();
    }

    /**
     * Returns the date type that matches the given name, or returns a new value if there is no match.
     * This methods returns the first instance (in declaration order) for which the {@linkplain #name() name}
     * is {@linkplain String#equalsIgnoreCase(String) equals, ignoring case}, to the given name.
     * If no existing instance is found, then a new one is created for the given name.
     *
     * @param  code  the name of the code to fetch or to create.
     * @return a code matching the given name.
     */
    public static GeometryOperand valueOf(final String code) {
        return valueOf(VALUES, code, GeometryOperand::new);
    }
}
