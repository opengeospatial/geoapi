
//  Class:  CompoundCRS
			
package org.opengis.coordrefsys;
public interface CompoundCRS extends 
					CRS
		{
		void setIncludesCRS(CoordinateReferenceSystem[] includesCRS);
		CoordinateReferenceSystem[] getIncludesCRS();
		};

		