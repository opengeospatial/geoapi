
//  Class:  CurveSegment
            
package org.opengis.geometry.primitive;
public interface CurveSegment extends 
					org.opengis.geometry.coordinate.GenericCurve
		{
		void setInterpolation(CurveInterpolation interpolation);
		CurveInterpolation getInterpolation();
		void setNumDerivativesAtStart(int numDerivativesAtStart);
		int getNumDerivativesAtStart();
		void setNumDerivativesAtEnd(int numDerivativesAtEnd);
		int getNumDerivativesAtEnd();
		void setNumDerivativeInterior(int numDerivativeInterior);
		int getNumDerivativeInterior();
		org.opengis.geometry.coordinate.PointArray  getSamplePoint ( );
		CurveBoundary  getBoundary ( );
		CurveSegment  getReverse ( );
		};

         