
//  Class:  EdgeBoundary
            
package org.opengis.topology.primitive;
public interface EdgeBoundary extends 
PrimitiveBoundary
		{
		void setStartNode(DirectedNode startNode);
		DirectedNode getStartNode();
		void setEndNode(DirectedNode endNode);
		DirectedNode getEndNode();
		};

         