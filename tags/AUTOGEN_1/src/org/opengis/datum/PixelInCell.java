
//  Class:  PixelInCell
				
package org.opengis.datum;
public class PixelInCell extends Object
 		 		{ 
 		 		private static String[] values = {
 		 		 				"cellcenter", 
				 				"cellcorner"
					 		 		}; 
 		 		public int code;
 		 		public String value() { return values[code];}
 		 		}; 

			