
//  Class:  Envelope
            
package org.opengis.geometry.coordinate;
public interface Envelope		{
		void setUpperCorner(DirectPosition upperCorner);
		DirectPosition getUpperCorner();
		void setLowerCorner(DirectPosition lowerCorner);
		DirectPosition getLowerCorner();
		};

         