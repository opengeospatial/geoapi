
//  Class:  VerticalDatumType
				
package org.opengis.datum;
public class VerticalDatumType extends Object
 		 		{ 
 		 		private static String[] values = {
 		 		 				"geoidal", 
				 				"depth", 
				 				"barometric", 
				 				"othersurface"
					 		 		}; 
 		 		public int code;
 		 		public String value() { return values[code];}
 		 		}; 

			