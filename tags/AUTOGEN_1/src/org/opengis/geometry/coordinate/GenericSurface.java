
//  Class:  GenericSurface
            
package org.opengis.geometry.coordinate;
public interface GenericSurface
		{
		double[]  getUpNormals (
 					DirectPosition  point );
		org.opengis.measure.Length  getPerimeter ( );
		org.opengis.measure.Area  getArea ( );
		};

         