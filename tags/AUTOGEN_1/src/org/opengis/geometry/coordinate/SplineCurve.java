
//  Class:  SplineCurve
            
package org.opengis.geometry.coordinate;
public interface SplineCurve extends 
					org.opengis.geometry.primitive.CurveSegment
		{
		void setDegree(int degree);
		int getDegree();
		void setKnots(Knot[] knot);
		Knot[] getKnots();
		void setControlPoints(PointArray controlPoints);
		PointArray getControlPoints();
		};

         