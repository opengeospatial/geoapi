
//  Class:  GenericCurve
            
package org.opengis.geometry.coordinate;
public interface GenericCurve
		{
		DirectPosition  getStartPoint ( );
		DirectPosition  getEndPoint ( );
		DirectPosition  getParam (
 					org.opengis.measure.Distance  s );
		double[]  getTangents (
 					org.opengis.measure.Distance  s );
		org.opengis.measure.Distance  getStartParam ( );
		org.opengis.measure.Distance  getEndParam ( );
		ParamForPoint  getParamForPoint (
 					DirectPosition  p );
		DirectPosition  getConstrParam (
 					double  cp );
		double  getStartConstrParam ( );
		double  getEndConstrParam ( );
		org.opengis.measure.Length  getLength1 (
 					Position  point1, 
 					Position  point2 );
		org.opengis.measure.Length  getLength2 (
 					double  cparam1, 
 					double  cparam2 );
		LineString  getAsLineString (
 					org.opengis.measure.Distance  spacing, 
 					org.opengis.measure.Distance  offset );
		};

         