
//  Class:  Primitive
            
package org.opengis.geometry.primitive;
public interface Primitive extends 
					org.opengis.geometry.root.GeometryRoot
		{
		void setComplexs(org.opengis.geometry.complex.Complex[] complex);
		org.opengis.geometry.complex.Complex[] getComplexs();
		void setContainedPrimitives(Primitive[] containedPrimitive);
		Primitive[] getContainedPrimitives();
		void setContainingPrimitives(Primitive[] containingPrimitive);
		Primitive[] getContainingPrimitives();
		void setProxys(OrientablePrimitive[] proxy);
		OrientablePrimitive[] getProxys();
		};

         