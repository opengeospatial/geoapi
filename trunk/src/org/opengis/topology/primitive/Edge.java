
//  Class:  Edge
            
package org.opengis.topology.primitive;
public interface Edge extends 
					Primitive, 
					DirectedEdge
		{
		void setBoundarys(DirectedNode[] boundary);
		DirectedNode[] getBoundarys();
		void setSpokes(DirectedFace[] spoke);
		DirectedFace[] getSpokes();
		};

         