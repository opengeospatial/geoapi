
//  Class:  Complex
            
package org.opengis.geometry.complex;
public interface Complex extends 
					org.opengis.geometry.root.GeometryRoot
		{
		void setElements(org.opengis.geometry.primitive.Primitive[] element);
		org.opengis.geometry.primitive.Primitive[] getElements();
		void setSuperComplexs(Complex[] superComplex);
		Complex[] getSuperComplexs();
		void setSubComplexs(Complex[] subComplex);
		Complex[] getSubComplexs();
		boolean  isMaximal ( );
		};

         