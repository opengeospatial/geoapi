
//  Class:  MultiSolid
            
package org.opengis.geometry.aggregates;
public interface MultiSolid extends 
					MultiPrimitive
		{
		void setVolume(org.opengis.measure.Volume volume);
		org.opengis.measure.Volume getVolume();
		void setArea(org.opengis.measure.Area area);
		org.opengis.measure.Area getArea();
		};

         