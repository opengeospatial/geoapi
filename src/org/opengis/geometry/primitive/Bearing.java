
//  Class:  Bearing
            
package org.opengis.geometry.primitive;
public interface Bearing		{
		void setAngles(org.opengis.measure.Angle[] angle);
		org.opengis.measure.Angle[] getAngles();
		void setDirections(double[] direction);
		double[] getDirections();
		};

         