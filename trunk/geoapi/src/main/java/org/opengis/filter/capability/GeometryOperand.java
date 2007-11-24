package org.opengis.filter.capability;

import org.opengis.feature.type.Name;

/**
 * Enumeration of the different GeometryOperand types.
 * <p>
 * <pre>
 *  &lt;xsd:simpleType name="GeometryOperandType">
 *    &lt;xsd:restriction base="xsd:QName">
 *        &lt;xsd:enumeration value="gml:Envelope"/>
 *        &lt;xsd:enumeration value="gml:Point"/>
 *        &lt;xsd:enumeration value="gml:LineString"/>
 *        &lt;xsd:enumeration value="gml:Polygon"/>
 *        &lt;xsd:enumeration value="gml:ArcByCenterPoint"/>
 *        &lt;xsd:enumeration value="gml:CircleByCenterPoint"/>
 *        &lt;xsd:enumeration value="gml:Arc"/>
 *        &lt;xsd:enumeration value="gml:Circle"/>
 *        &lt;xsd:enumeration value="gml:ArcByBulge"/>
 *        &lt;xsd:enumeration value="gml:Bezier"/>
 *        &lt;xsd:enumeration value="gml:Clothoid"/>
 *        &lt;xsd:enumeration value="gml:CubicSpline"/>
 *        &lt;xsd:enumeration value="gml:Geodesic"/>
 *        &lt;xsd:enumeration value="gml:OffsetCurve"/>
 *        &lt;xsd:enumeration value="gml:Triangle"/>
 *        &lt;xsd:enumeration value="gml:PolyhedralSurface"/>
 *        &lt;xsd:enumeration value="gml:TriangulatedSurface"/>
 *        &lt;xsd:enumeration value="gml:Tin"/>
 *        &lt;xsd:enumeration value="gml:Solid"/>
 *     &lt;/xsd:restriction>
 *  &lt;/xsd:simpleType>
 *  </pre>
 * </p>
 * @author Justin Deoliveira, The Open Planning Project
 *
 */
public class GeometryOperand implements Name {

    public static final GeometryOperand Envelope =
        new GeometryOperand( "http://www.opengis.net/gml", "Envelope");
    public static final GeometryOperand Point =
        new GeometryOperand( "http://www.opengis.net/gml", "Point" );
    public static final GeometryOperand LineString =
        new GeometryOperand( "http://www.opengis.net/gml", "LineString" );
    public static final GeometryOperand Polygon =
        new GeometryOperand( "http://www.opengis.net/gml", "Polygon" );
    public static final GeometryOperand ArcByCenterPoint =
        new GeometryOperand( "http://www.opengis.net/gml", "ArcByCenterPoint" );
    public static final GeometryOperand CircleByCenterPoint =
        new GeometryOperand( "http://www.opengis.net/gml", "CircleByCenterPoint" );
    public static final GeometryOperand Arc =
        new GeometryOperand( "http://www.opengis.net/gml", "Arc" );
    public static final GeometryOperand Circle =
        new GeometryOperand( "http://www.opengis.net/gml", "Circle" );
    public static final GeometryOperand ArcByBulge =
        new GeometryOperand( "http://www.opengis.net/gml", "ArcByBulge" );
    public static final GeometryOperand Bezier =
        new GeometryOperand( "http://www.opengis.net/gml", "Bezier" );
    public static final GeometryOperand Clothoid =
        new GeometryOperand( "http://www.opengis.net/gml", "Clothoid" );
    public static final GeometryOperand CubicSpline =
        new GeometryOperand( "http://www.opengis.net/gml", "CubicSpline" );
    public static final GeometryOperand Geodesic =
        new GeometryOperand( "http://www.opengis.net/gml", "Geodesic" );
    public static final GeometryOperand OffsetCurve =
        new GeometryOperand( "http://www.opengis.net/gml", "OffsetCurve" );
    public static final GeometryOperand Triangle =
        new GeometryOperand( "http://www.opengis.net/gml", "Triangle" );
    public static final GeometryOperand PolyhedralSurface =
        new GeometryOperand( "http://www.opengis.net/gml", "PolyhedralSurface" );
    public static final GeometryOperand TriangulatedSurface =
        new GeometryOperand( "http://www.opengis.net/gml", "TriangulatedSurface" );
    public static final GeometryOperand Tin =
        new GeometryOperand( "http://www.opengis.net/gml", "Tin" );
    public static final GeometryOperand Solid =
        new GeometryOperand( "http://www.opengis.net/gml", "Solid" );

    private final String namespaceURI;
    private final String name;

    GeometryOperand( String namespaceURI, String name ) {
        this.namespaceURI = namespaceURI;
        this.name = name;
    }

    public static GeometryOperand get( String namespaceURI, String name ) {

        if (name == null ) {
            return null;
        }

        if ( namespaceURI == null || "".equals( namespaceURI ) ) {
            namespaceURI = "http://www.opengis.net/gml";
        }

        if ( !"http://www.opengis.net/gml".equals( namespaceURI ) ) {
            return null;
        }

        if ( "Envelope".equals( name ) ) {
            return Envelope;
        }
        if ( "Point".equals( name ) ) {
            return Point;
        }
        if ( "LineString".equals( name ) ) {
            return LineString;
        }
        if ( "Polygon".equals( name ) ) {
            return Polygon;
        }
        if ( "ArcByCenterPoint".equals( name ) ) {
            return ArcByCenterPoint;
        }
        if ( "CircleByCenterPoint".equals( name ) ) {
            return CircleByCenterPoint;
        }
        if ( "Arc".equals( name ) ) {
            return Arc;
        }
        if ( "Circle".equals( name ) ) {
            return Circle;
        }
        if ( "ArcByBulge".equals( name ) ) {
            return ArcByBulge;
        }
        if ( "Bezier".equals( name ) ) {
            return Bezier;
        }
        if ( "Clothoid".equals( name ) ) {
            return Clothoid;
        }
        if ( "CubicSpline".equals( name ) ) {
            return CubicSpline;
        }
        if ( "Geodesic".equals( name ) ) {
            return Geodesic;
        }
        if ( "OffsetCurve".equals( name ) ) {
            return OffsetCurve;
        }
        if ( "Triangle".equals( name ) ) {
            return Triangle;
        }
        if ( "PolyhedralSurface".equals( name ) ) {
            return PolyhedralSurface;
        }
        if ( "TriangulatedSurface".equals( name ) ) {
            return TriangulatedSurface;
        }
        if ( "Tin".equals( name ) ) {
            return Tin;
        }
        if ( "Solid".equals( name ) ) {
            return Solid;
        }

        return null;
    }

    public String getLocalPart() {
        return name;
    }

    public String getNamespaceURI() {
        return namespaceURI;
    }

    public String getURI() {
        return namespaceURI + "/" + name;
    }

    public boolean isGlobal() {
        return false;
    }
}
