
//  Class:  Clothoid
            
package org.opengis.geometry.coordinate;
public interface Clothoid extends 
					org.opengis.geometry.primitive.CurveSegment
		{
		void setRefLocation(AffinePlacement refLocation);
		AffinePlacement getRefLocation();
		void setScaleFactor(double scaleFactor);
		double getScaleFactor();
		void setStartParameter(double startParameter);
		double getStartParameter();
		void setEndParameter(double endParameter);
		double getEndParameter();
		};

         