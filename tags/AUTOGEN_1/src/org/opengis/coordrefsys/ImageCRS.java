
//  Class:  ImageCRS
			
package org.opengis.coordrefsys;
public interface ImageCRS extends 
					CoordinateReferenceSystem
		{
		void setUsesObliqueCartesianCS(org.opengis.coordsys.ObliqueCartesianCS usesObliqueCartesianCS);
		org.opengis.coordsys.ObliqueCartesianCS getUsesObliqueCartesianCS();
		void setUsesCartesianCS(org.opengis.coordsys.CartesianCS usesCartesianCS);
		org.opengis.coordsys.CartesianCS getUsesCartesianCS();
		};

		