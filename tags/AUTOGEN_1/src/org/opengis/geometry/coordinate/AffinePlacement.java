
//  Class:  AffinePlacement
            
package org.opengis.geometry.coordinate;
public interface AffinePlacement extends 
					Placement
		{
		void setLocation(Position location);
		Position getLocation();
		void setRefDirections(double[][] refDirection);
		double[][] getRefDirections();
		};

         