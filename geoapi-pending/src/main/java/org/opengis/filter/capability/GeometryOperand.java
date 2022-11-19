/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    http://www.geoapi.org
 *
 *    Copyright (C) 2007-2021 Open Geospatial Consortium, Inc.
 *    All Rights Reserved. http://www.opengeospatial.org/ogc/legal
 *
 *    Permission to use, copy, and modify this software and its documentation, with
 *    or without modification, for any purpose and without fee or royalty is hereby
 *    granted, provided that you include the following on ALL copies of the software
 *    and documentation or portions thereof, including modifications, that you make:
 *
 *    1. The full text of this NOTICE in a location viewable to users of the
 *       redistributed or derivative work.
 *    2. Notice of any changes or modifications to the OGC files, including the
 *       date changes were made.
 *
 *    THIS SOFTWARE AND DOCUMENTATION IS PROVIDED "AS IS," AND COPYRIGHT HOLDERS MAKE
 *    NO REPRESENTATIONS OR WARRANTIES, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED
 *    TO, WARRANTIES OF MERCHANTABILITY OR FITNESS FOR ANY PARTICULAR PURPOSE OR THAT
 *    THE USE OF THE SOFTWARE OR DOCUMENTATION WILL NOT INFRINGE ANY THIRD PARTY
 *    PATENTS, COPYRIGHTS, TRADEMARKS OR OTHER RIGHTS.
 *
 *    COPYRIGHT HOLDERS WILL NOT BE LIABLE FOR ANY DIRECT, INDIRECT, SPECIAL OR
 *    CONSEQUENTIAL DAMAGES ARISING OUT OF ANY USE OF THE SOFTWARE OR DOCUMENTATION.
 *
 *    The name and trademarks of copyright holders may NOT be used in advertising or
 *    publicity pertaining to the software without specific, written prior permission.
 *    Title to copyright in this software and any associated documentation will at all
 *    times remain with copyright holders.
 */
package org.opengis.filter.capability;

import java.util.List;
import java.util.ArrayList;
import org.opengis.util.CodeList;


/**
 * Enumeration of the different {@code GeometryOperand} types.
 *
 * <pre>
 *  &lt;xsd:simpleType name="GeometryOperandType"&lt;
 *    &lt;xsd:restriction base="xsd:QName"&lt;
 *        &lt;xsd:enumeration value="gml:Envelope"/&lt;
 *        &lt;xsd:enumeration value="gml:Point"/&lt;
 *        &lt;xsd:enumeration value="gml:LineString"/&lt;
 *        &lt;xsd:enumeration value="gml:Polygon"/&lt;
 *        &lt;xsd:enumeration value="gml:ArcByCenterPoint"/&lt;
 *        &lt;xsd:enumeration value="gml:CircleByCenterPoint"/&lt;
 *        &lt;xsd:enumeration value="gml:Arc"/&lt;
 *        &lt;xsd:enumeration value="gml:Circle"/&lt;
 *        &lt;xsd:enumeration value="gml:ArcByBulge"/&lt;
 *        &lt;xsd:enumeration value="gml:Bezier"/&lt;
 *        &lt;xsd:enumeration value="gml:Clothoid"/&lt;
 *        &lt;xsd:enumeration value="gml:CubicSpline"/&lt;
 *        &lt;xsd:enumeration value="gml:Geodesic"/&lt;
 *        &lt;xsd:enumeration value="gml:OffsetCurve"/&lt;
 *        &lt;xsd:enumeration value="gml:Triangle"/&lt;
 *        &lt;xsd:enumeration value="gml:PolyhedralSurface"/&lt;
 *        &lt;xsd:enumeration value="gml:TriangulatedSurface"/&lt;
 *        &lt;xsd:enumeration value="gml:Tin"/&lt;
 *        &lt;xsd:enumeration value="gml:Solid"/&lt;
 *     &lt;/xsd:restriction&lt;
 *  &lt;/xsd:simpleType&lt;
 *  </pre>
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

    /**
     * List of all enumerations of this type.
     * Must be declared before any enum declaration.
     */
    private static final List<GeometryOperand> VALUES = new ArrayList<GeometryOperand>(19);

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
        super(name, VALUES);
    }

    /**
     * Returns the list of {@code GeometryOperand}s.
     *
     * @return the list of codes declared in the current JVM.
     */
    public static GeometryOperand[] values() {
        synchronized (VALUES) {
            return VALUES.toArray(new GeometryOperand[VALUES.size()]);
        }
    }

    /**
     * Returns the list of codes of the same kind than this code list element.
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
     * Returns the date type that matches the given string, or returns a
     * new one if none match it. More specifically, this methods returns the first instance for
     * which <code>{@linkplain #name() name()}.{@linkplain String#equals equals}(code)</code>
     * returns {@code true}. If no existing instance is found, then a new one is created for
     * the given name.
     *
     * @param  code  the name of the code to fetch or to create.
     * @return a code matching the given name.
     */
    public static GeometryOperand valueOf(final String code) {
        return valueOf(GeometryOperand.class, code);
    }
}
