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

import org.opengis.util.CodeList;
import org.opengis.geoapi.internal.Vocabulary;


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
@Vocabulary(capacity=19)
public final class GeometryOperand extends CodeList<GeometryOperand> {
    /**
     * For cross-version compatibility.
     */
    private static final long serialVersionUID = 6517166553565301182L;

    /** {@code "http://www.opengis.net/gml/Envelope"} */
    public static final GeometryOperand Envelope = new GeometryOperand("Envelope");

    /** {@code "http://www.opengis.net/gml/Point"} */
    public static final GeometryOperand Point = new GeometryOperand("Point");

    /** {@code "http://www.opengis.net/gml/LineString"} */
    public static final GeometryOperand LineString = new GeometryOperand("LineString");

    /** {@code "http://www.opengis.net/gml/Polygon"} */
    public static final GeometryOperand Polygon = new GeometryOperand("Polygon");

    /** {@code "http://www.opengis.net/gml/ArcByCenterPoint"} */
    public static final GeometryOperand ArcByCenterPoint = new GeometryOperand("ArcByCenterPoint");

    /** {@code "http://www.opengis.net/gml/CircleByCenterPoint"} */
    public static final GeometryOperand CircleByCenterPoint = new GeometryOperand("CircleByCenterPoint");

    /** {@code "http://www.opengis.net/gml/Arc"} */
    public static final GeometryOperand Arc = new GeometryOperand("Arc");

    /** {@code "http://www.opengis.net/gml/Circle"} */
    public static final GeometryOperand Circle = new GeometryOperand("Circle");

    /** {@code "http://www.opengis.net/gml/ArcByBulge"} */
    public static final GeometryOperand ArcByBulge = new GeometryOperand("ArcByBulge");

    /** {@code "http://www.opengis.net/gml/Bezier"} */
    public static final GeometryOperand Bezier = new GeometryOperand("Bezier");

    /** {@code "http://www.opengis.net/gml/Clothoid"} */
    public static final GeometryOperand Clothoid = new GeometryOperand("Clothoid");

    /** {@code "http://www.opengis.net/gml/CubicSpline"} */
    public static final GeometryOperand CubicSpline = new GeometryOperand("CubicSpline");

    /** {@code "http://www.opengis.net/gml/Geodesic"} */
    public static final GeometryOperand Geodesic = new GeometryOperand("Geodesic");

    /** {@code "http://www.opengis.net/gml/OffsetCurve"} */
    public static final GeometryOperand OffsetCurve = new GeometryOperand("OffsetCurve");

    /** {@code "http://www.opengis.net/gml/Triangle"} */
    public static final GeometryOperand Triangle = new GeometryOperand("Triangle");

    /** {@code "http://www.opengis.net/gml/PolyhedralSurface"} */
    public static final GeometryOperand PolyhedralSurface = new GeometryOperand("PolyhedralSurface");

    /** {@code "http://www.opengis.net/gml/TriangulatedSurface"} */
    public static final GeometryOperand TriangulatedSurface = new GeometryOperand("TriangulatedSurface");

    /** {@code "http://www.opengis.net/gml/Tin"} */
    public static final GeometryOperand Tin = new GeometryOperand("Tin");

    /** {@code "http://www.opengis.net/gml/Solid"} */
    public static final GeometryOperand Solid = new GeometryOperand("Solid");

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
        return values(GeometryOperand.class);
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
        return valueOf(GeometryOperand.class, code, GeometryOperand::new).get();
    }
}
