
//  Class:  GeodesicString
            
package org.opengis.geometry.coordinate;
public interface GeodesicString extends 
					org.opengis.geometry.primitive.CurveSegment
		{
		void setControlPoint(PointArray controlPoint);
		PointArray getControlPoint();
		Geodesic[]  getAsGeodesics ( );
		};

         