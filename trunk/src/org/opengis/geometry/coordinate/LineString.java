
//  Class:  LineString
            
package org.opengis.geometry.coordinate;
public interface LineString extends 
					org.opengis.geometry.primitive.CurveSegment
		{
		void setControlPoint(PointArray controlPoint);
		PointArray getControlPoint();
		LineSegment[]  getAsLineSegments ( );
		};

         