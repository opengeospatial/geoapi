
//  Class:  SurfaceInterpolation
            
package org.opengis.geometry.primitive;
public class SurfaceInterpolation extends Object
 		 		{ 
 		 		private static String[] values = {
 		 		 				"none", 
				 				"planar", 
				 				"spherical", 
				 				"elliptical", 
				 				"conic", 
				 				"tin", 
				 				"parametricCurve", 
				 				"polynomialSpline", 
				 				"rationalSpline", 
				 				"triangulatedSpline"
					 		 		}; 
 		 		public int code;
 		 		public String value() { return values[code];}
 		 		}; 

         