
//  Class:  ParamForPoint
            
package org.opengis.geometry.coordinate;
public interface ParamForPoint		{
		void setDists(org.opengis.measure.Distance[] dist);
		org.opengis.measure.Distance[] getDists();
		void setPosition(DirectPosition position);
		DirectPosition getPosition();
		};

         