
//  Class:  DirectPosition
            
package org.opengis.geometry.coordinate;
public interface DirectPosition		{
		void setCoordinates(double[] coordinate);
		double[] getCoordinates();
		void setDimension(int dimension);
		int getDimension();
		void setCoordinateReferenceSystem(String coordinateReferenceSystem);
		String getCoordinateReferenceSystem();
		};

         