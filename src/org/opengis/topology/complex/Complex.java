
//  Class:  Complex
            
package org.opengis.topology.complex;
public interface Complex
		{
		void setSubComplexs(Complex[] subComplex);
		Complex[] getSubComplexs();
		void setSuperComplexs(Complex[] superComplex);
		Complex[] getSuperComplexs();
		void setElements(org.opengis.topology.primitive.Primitive[] element);
		org.opengis.topology.primitive.Primitive[] getElements();
		void setGeometry(org.opengis.geometry.complex.Complex geometry);
		org.opengis.geometry.complex.Complex getGeometry();
		void setMaximalComplex(Complex maximalComplex);
		Complex getMaximalComplex();
		boolean  isMaximal ( );
		boolean  isConnected ( );
		org.opengis.topology.primitive.ComplexBoundary  getBoundary ( );
		};

         