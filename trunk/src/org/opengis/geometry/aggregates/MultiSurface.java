
//  Class:  MultiSurface
            
package org.opengis.geometry.aggregates;
public interface MultiSurface extends 
					MultiPrimitive
		{
		void setArea(org.opengis.measure.Area area);
		org.opengis.measure.Area getArea();
		void setPerimeter(org.opengis.measure.Length perimeter);
		org.opengis.measure.Length getPerimeter();
		};

         