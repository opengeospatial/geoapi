
//  Class:  Primitive
            
package org.opengis.topology.primitive;
public interface Primitive
		{
		void setComplexs(org.opengis.topology.complex.Complex[] complex);
		org.opengis.topology.complex.Complex[] getComplexs();
		void setProxys(DirectedTopo[] proxy);
		DirectedTopo[] getProxys();
		void setGeometry(org.opengis.geometry.primitive.Primitive geometry);
		org.opengis.geometry.primitive.Primitive getGeometry();
		void setContainer(Primitive container);
		Primitive getContainer();
		void setIsolateds(Primitive[] isolated);
		Primitive[] getIsolateds();
		void setMaximalComplex(org.opengis.topology.complex.Complex maximalComplex);
		org.opengis.topology.complex.Complex getMaximalComplex();
		DirectedTopo  getAsDirectedTopo (
 					boolean  orientation );
		};

         