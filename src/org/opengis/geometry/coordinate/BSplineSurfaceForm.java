
//  Class:  BSplineSurfaceForm
            
package org.opengis.geometry.coordinate;
public class BSplineSurfaceForm extends Object
 		 		{ 
 		 		private static String[] values = {
 		 		 				"planar", 
				 				"cylindrical", 
				 				"conical", 
				 				"spherical", 
				 				"toroidal", 
				 				"unspecified"
					 		 		}; 
 		 		public int code;
 		 		public String value() { return values[code];}
 		 		}; 

         