
//  Class:  Polygon
            
package org.opengis.geometry.coordinate;
public interface Polygon extends 
					org.opengis.geometry.primitive.SurfacePatch
		{
		void setBoundary(org.opengis.geometry.primitive.SurfaceBoundary boundary);
		org.opengis.geometry.primitive.SurfaceBoundary getBoundary();
		void setSpanningSurface(org.opengis.geometry.primitive.Surface spanningSurface);
		org.opengis.geometry.primitive.Surface getSpanningSurface();
		void setSurface(PolyhedralSurface surface);
		PolyhedralSurface getSurface();
		};

         