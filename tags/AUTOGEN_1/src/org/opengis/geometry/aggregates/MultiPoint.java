
//  Class:  MultiPoint
            
package org.opengis.geometry.aggregates;
public interface MultiPoint extends 
					MultiPrimitive
		{
		void setPositions(org.opengis.geometry.coordinate.DirectPosition[] position);
		org.opengis.geometry.coordinate.DirectPosition[] getPositions();
		};

         