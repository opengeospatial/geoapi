
//  Class:  OffsetCurve
            
package org.opengis.geometry.coordinate;
public interface OffsetCurve extends 
					org.opengis.geometry.primitive.CurveSegment
		{
		void setDistance(org.opengis.measure.Length distance);
		org.opengis.measure.Length getDistance();
		void setRefDirections(double[] refDirection);
		double[] getRefDirections();
		void setBaseCurve(org.opengis.geometry.primitive.CurveSegment baseCurve);
		org.opengis.geometry.primitive.CurveSegment getBaseCurve();
		};

         