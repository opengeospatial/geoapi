
//  Class:  KnotType
            
package org.opengis.geometry.coordinate;
public class KnotType extends Object
 		 		{ 
 		 		private static String[] values = {
 		 		 				"uniform", 
				 				"quasiUniform", 
				 				"piecewiseBezier"
					 		 		}; 
 		 		public int code;
 		 		public String value() { return values[code];}
 		 		}; 

         