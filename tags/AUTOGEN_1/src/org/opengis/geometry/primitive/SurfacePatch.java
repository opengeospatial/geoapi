
//  Class:  SurfacePatch
            
package org.opengis.geometry.primitive;
public interface SurfacePatch extends 
					org.opengis.geometry.coordinate.GenericSurface
		{
		void setInterpolation(SurfaceInterpolation interpolation);
		SurfaceInterpolation getInterpolation();
		void setNumDerivativesOnBoundary(int numDerivativesOnBoundary);
		int getNumDerivativesOnBoundary();
		SurfaceBoundary  getBoundary ( );
		};

         