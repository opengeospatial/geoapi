
//  Class:  CoordinateReferenceSystem
			
package org.opengis.coordrefsys;
public interface CoordinateReferenceSystem extends 
					CRS
		{
		void setUsesDatum(org.opengis.datum.Datum usesDatum);
		org.opengis.datum.Datum getUsesDatum();
		void setUsesCS(org.opengis.coordsys.CoordinateSystem usesCS);
		org.opengis.coordsys.CoordinateSystem getUsesCS();
		};

		