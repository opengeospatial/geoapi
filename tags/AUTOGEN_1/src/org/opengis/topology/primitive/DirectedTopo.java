
//  Class:  DirectedTopo
            
package org.opengis.topology.primitive;
public interface DirectedTopo extends 
					Primitive
		{
		void setOrientation(boolean orientation);
		boolean getOrientation();
		void setTopo(Primitive topo);
		Primitive getTopo();
		DirectedTopo  getNegate ( );
		Expression  getAsExpression ( );
		};

         