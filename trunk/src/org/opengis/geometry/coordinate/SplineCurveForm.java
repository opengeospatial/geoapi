
//  Class:  SplineCurveForm
            
package org.opengis.geometry.coordinate;
public class SplineCurveForm extends Object
 		 		{ 
 		 		private static String[] values = {
 		 		 				"polylineForm", 
				 				"circularArc", 
				 				"ellipticArc", 
				 				"parabolicArc", 
				 				"hyperbolicArc"
					 		 		}; 
 		 		public int code;
 		 		public String value() { return values[code];}
 		 		}; 

         