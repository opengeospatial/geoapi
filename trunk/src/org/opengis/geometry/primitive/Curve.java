
//  Class:  Curve
            
package org.opengis.geometry.primitive;
public interface Curve extends 
					OrientableCurve, 
					org.opengis.geometry.coordinate.GenericCurve
		{
		void setSegments(CurveSegment[] segment);
		CurveSegment[] getSegments();
		};

         