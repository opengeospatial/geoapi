
//  Class:  SurfaceBoundary
            
package org.opengis.geometry.primitive;
public interface SurfaceBoundary extends 
					PrimitiveBoundary
		{
		void setInteriors(Ring[] interior);
		Ring[] getInteriors();
		void setExterior(Ring exterior);
		Ring getExterior();
		};

         