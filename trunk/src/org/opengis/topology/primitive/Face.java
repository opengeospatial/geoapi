
//  Class:  Face
            
package org.opengis.topology.primitive;
public interface Face extends 
					Primitive, 
					DirectedFace
		{
		void setBoundarys(DirectedEdge[] boundary);
		DirectedEdge[] getBoundarys();
		void setSpokes(DirectedSolid[] spoke);
		DirectedSolid[] getSpokes();
		};

         