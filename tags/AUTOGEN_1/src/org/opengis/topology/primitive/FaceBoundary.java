
//  Class:  FaceBoundary
            
package org.opengis.topology.primitive;
public interface FaceBoundary extends 
PrimitiveBoundary
		{
		void setExterior(Ring exterior);
		Ring getExterior();
		void setInteriors(Ring[] interior);
		Ring[] getInteriors();
		};

         