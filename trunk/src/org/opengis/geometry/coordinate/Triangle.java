
//  Class:  Triangle
            
package org.opengis.geometry.coordinate;
public interface Triangle extends 
					Polygon
		{
		void setCornerss(Position[] corners);
		Position[] getCornerss();
		};

         