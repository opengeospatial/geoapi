
//  Class:  GeocentricCRS
			
package org.opengis.coordrefsys;
public interface GeocentricCRS extends 
					CoordinateReferenceSystem
		{
		void setUsesSphericalCS(org.opengis.coordsys.SphericalCS usesSphericalCS);
		org.opengis.coordsys.SphericalCS getUsesSphericalCS();
		void setUsesCartesianCS(org.opengis.coordsys.CartesianCS usesCartesianCS);
		org.opengis.coordsys.CartesianCS getUsesCartesianCS();
		};

		