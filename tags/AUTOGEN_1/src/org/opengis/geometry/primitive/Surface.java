
//  Class:  Surface
            
package org.opengis.geometry.primitive;
public interface Surface extends 
					OrientableSurface, 
					org.opengis.geometry.coordinate.GenericSurface
		{
		void setPatchs(SurfacePatch[] patch);
		SurfacePatch[] getPatchs();
		};

         