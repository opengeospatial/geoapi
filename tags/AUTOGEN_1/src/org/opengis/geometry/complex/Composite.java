
//  Class:  Composite
            
package org.opengis.geometry.complex;
public interface Composite extends 
					Complex
		{
		void setGenerators(org.opengis.geometry.primitive.Primitive[] generator);
		org.opengis.geometry.primitive.Primitive[] getGenerators();
		};

         