
//  Class:  Conic
            
package org.opengis.geometry.coordinate;
public interface Conic extends 
					org.opengis.geometry.primitive.CurveSegment
		{
		void setPosition(AffinePlacement position);
		AffinePlacement getPosition();
		void setShifted(boolean shifted);
		boolean getShifted();
		void setEccentricity(double eccentricity);
		double getEccentricity();
		void setSemiLatusRectum(double semiLatusRectum);
		double getSemiLatusRectum();
		void setStartConstrParam(double startConstrParam);
		double getStartConstrParam();
		void setEndConstrParam(double endConstrParam);
		double getEndConstrParam();
		};

         