
//  Class:  Node
            
package org.opengis.topology.primitive;
public interface Node extends 
					Primitive, 
					DirectedNode
		{
		void setSpokes(DirectedEdge[] spoke);
		DirectedEdge[] getSpokes();
		};

         