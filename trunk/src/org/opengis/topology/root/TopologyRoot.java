
//  Class:  TopologyRoot
            
package org.opengis.topology.root;
public interface TopologyRoot
		{
		int  getDimension ( );
		org.opengis.topology.primitive.Boundary  getBoundary ( );
		org.opengis.topology.primitive.DirectedTopo[]  getCoBoundarys ( );
		org.opengis.topology.primitive.Primitive[]  getInteriors ( );
		org.opengis.topology.primitive.Primitive[]  getClosures ( );
		org.opengis.topology.primitive.Primitive[]  getExteriors ( );
		org.opengis.topology.complex.Complex  getMaximalComplex ( );
		};

         