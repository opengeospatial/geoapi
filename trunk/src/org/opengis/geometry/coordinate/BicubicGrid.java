
//  Class:  BicubicGrid
            
package org.opengis.geometry.coordinate;
public interface BicubicGrid extends 
					GriddedSurface
		{
		void setHoriVectorAtStarts(double[][] horiVectorAtStart);
		double[][] getHoriVectorAtStarts();
		void setHoriVectorAtEnds(double[][] horiVectorAtEnd);
		double[][] getHoriVectorAtEnds();
		void setVertVectorAtStarts(double[][] vertVectorAtStart);
		double[][] getVertVectorAtStarts();
		void setVertVectorAtEnds(double[][] vertVectorAtEnd);
		double[][] getVertVectorAtEnds();
		};

         