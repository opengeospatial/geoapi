
//  Class:  Point
            
package org.opengis.geometry.primitive;
public interface Point extends 
					Primitive
		{
		void setPosition(org.opengis.geometry.coordinate.DirectPosition position);
		org.opengis.geometry.coordinate.DirectPosition getPosition();
		Bearing  getBearing (
 					org.opengis.geometry.coordinate.Position  toPoint );
		};

         