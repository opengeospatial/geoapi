
//  Class:  Solid
            
package org.opengis.topology.primitive;
public interface Solid extends 
					Primitive, 
					DirectedSolid
		{
		void setBoundarys(DirectedFace[] boundary);
		DirectedFace[] getBoundarys();
		};

         