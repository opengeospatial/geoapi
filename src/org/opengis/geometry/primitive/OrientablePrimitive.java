
//  Class:  OrientablePrimitive
            
package org.opengis.geometry.primitive;
public interface OrientablePrimitive extends 
					Primitive
		{
		void setOrientation(boolean orientation);
		boolean getOrientation();
		void setPrimitive(Primitive primitive);
		Primitive getPrimitive();
		};

         