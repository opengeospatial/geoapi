
//  Class:  CurveInterpolation
            
package org.opengis.geometry.primitive;
public class CurveInterpolation extends Object
 		 		{ 
 		 		private static String[] values = {
 		 		 				"linear", 
				 				"geodesic", 
				 				"circularArc3Points", 
				 				"circularArc2PointWithBulge", 
				 				"elliptical", 
				 				"clothoid", 
				 				"conic", 
				 				"polynomialSpline", 
				 				"cubicSpline", 
				 				"rationalSpline"
					 		 		}; 
 		 		public int code;
 		 		public String value() { return values[code];}
 		 		}; 

         