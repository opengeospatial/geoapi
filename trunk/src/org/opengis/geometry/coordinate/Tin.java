
//  Class:  Tin
            
package org.opengis.geometry.coordinate;
public interface Tin extends 
					TriangulatedSurface
		{
		void setStopLiness(LineString[] stopLines);
		LineString[] getStopLiness();
		void setBreakLiness(LineString[] breakLines);
		LineString[] getBreakLiness();
		void setMaxLength(org.opengis.measure.Distance maxLength);
		org.opengis.measure.Distance getMaxLength();
		void setControlPoints(Position[] controlPoint);
		Position[] getControlPoints();
		};

         