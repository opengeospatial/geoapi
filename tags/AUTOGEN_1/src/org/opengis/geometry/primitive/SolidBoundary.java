
//  Class:  SolidBoundary
            
package org.opengis.geometry.primitive;
public interface SolidBoundary extends 
					PrimitiveBoundary
		{
		void setExterior(Shell exterior);
		Shell getExterior();
		void setInteriors(Shell[] interior);
		Shell[] getInteriors();
		};

         