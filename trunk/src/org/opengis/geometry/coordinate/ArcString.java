
//  Class:  ArcString
            
package org.opengis.geometry.coordinate;
public interface ArcString extends 
					org.opengis.geometry.primitive.CurveSegment
		{
		void setNumArc(int numArc);
		int getNumArc();
		void setControlPoints(PointArray controlPoints);
		PointArray getControlPoints();
		Arc[]  getAsArcs ( );
		};

         