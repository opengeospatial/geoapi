
//  Class:  Arc
            
package org.opengis.geometry.coordinate;
public interface Arc extends 
					ArcString
		{
		DirectPosition  getCenter ( );
		org.opengis.measure.Distance  getRadius ( );
		org.opengis.geometry.primitive.Bearing  getStartOfArc ( );
		org.opengis.geometry.primitive.Bearing  getEndOfArc ( );
		};

         