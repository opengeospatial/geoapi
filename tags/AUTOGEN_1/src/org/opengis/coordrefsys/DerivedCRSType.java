
//  Class:  DerivedCRSType
			
package org.opengis.coordrefsys;
public class DerivedCRSType extends Object
 		 		{ 
 		 		private static String[] values = {
 		 		 				"engineering", 
				 				"image", 
				 				"vertical", 
				 				"temporal"
					 		 		}; 
 		 		public int code;
 		 		public String value() { return values[code];}
 		 		}; 

		