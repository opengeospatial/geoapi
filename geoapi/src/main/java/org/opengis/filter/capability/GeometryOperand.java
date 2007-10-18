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
public enum GeometryOperand implements Name {

    Envelope( "http://www.opengis.net/gml", "Envelope"),
    Point( "http://www.opengis.net/gml", "Point" ),
    LineString( "http://www.opengis.net/gml", "LineString" ),
    Polygon( "http://www.opengis.net/gml", "Polygon" ),
    ArcByCenterPoint( "http://www.opengis.net/gml", "ArcByCenterPoint" ),
    CircleByCenterPoint( "http://www.opengis.net/gml", "CircleByCenterPoint" ),
    Arc( "http://www.opengis.net/gml", "Arc" ),
    Circle( "http://www.opengis.net/gml", "Circle" ),
    ArcByBulge( "http://www.opengis.net/gml", "ArcByBulge" ),
    Bezier( "http://www.opengis.net/gml", "Bezier" ),
    Clothoid( "http://www.opengis.net/gml", "Clothoid" ),
    CubicSpline( "http://www.opengis.net/gml", "CubicSpline" ),
    Geodesic( "http://www.opengis.net/gml", "Geodesic" ),
    OffsetCurve( "http://www.opengis.net/gml", "OffsetCurve" ),
    Triangle( "http://www.opengis.net/gml", "Triangle" ),
    PolyhedralSurface( "http://www.opengis.net/gml", "PolyhedralSurface" ),
    TriangulatedSurface( "http://www.opengis.net/gml", "TriangulatedSurface" ),
    Tin( "http://www.opengis.net/gml", "Tin" ),
    Solid( "http://www.opengis.net/gml", "Solid" );
    
    private final String namespaceURI;
    private final String name;
    
    GeometryOperand( String namespaceURI, String name ) {
        this.namespaceURI = namespaceURI;
        this.name = name;
    }

    public static GeometryOperand get( String namesapceURI, String name ) {
        
        if (name == null ) {
            return null;
        }
        
        if ( "http://www.opengis.net/gml".equals( namesapceURI ) ) {
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
