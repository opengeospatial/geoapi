
//  Class:  ParametricCurveSurface
            
package org.opengis.geometry.coordinate;
public interface ParametricCurveSurface extends 
					org.opengis.geometry.primitive.SurfacePatch
		{
		void setHorizontalCurveType(org.opengis.geometry.primitive.CurveInterpolation horizontalCurveType);
		org.opengis.geometry.primitive.CurveInterpolation getHorizontalCurveType();
		void setVerticalCurveType(org.opengis.geometry.primitive.CurveInterpolation verticalCurveType);
		org.opengis.geometry.primitive.CurveInterpolation getVerticalCurveType();
		org.opengis.geometry.primitive.Curve  getHorizontalCurve (
 					double  t );
		org.opengis.geometry.primitive.Curve  getVerticalCurve (
 					double  s );
		DirectPosition  getSurface (
 					double  s, 
 					double  t );
		};

         