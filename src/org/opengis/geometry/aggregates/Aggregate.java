
//  Class:  Aggregate
            
package org.opengis.geometry.aggregates;
public interface Aggregate extends 
					org.opengis.geometry.root.GeometryRoot
		{
		void setElements(org.opengis.geometry.root.GeometryRoot[] element);
		org.opengis.geometry.root.GeometryRoot[] getElements();
		Aggregate  getFromSet (
 					org.opengis.geometry.root.GeometryRoot[]  set );
		};

         