
//  Class:  PolynomialSpline
            
package org.opengis.geometry.coordinate;
public interface PolynomialSpline extends 
					SplineCurve
		{
		void setVectorAtStarts(double[][] vectorAtStart);
		double[][] getVectorAtStarts();
		void setVectorAtEnds(double[][] vectorAtEnd);
		double[][] getVectorAtEnds();
		};

         