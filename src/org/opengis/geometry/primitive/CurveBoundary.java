
//  Class:  CurveBoundary
            
package org.opengis.geometry.primitive;
public interface CurveBoundary extends 
					PrimitiveBoundary
		{
		void setStartPoint(Point startPoint);
		Point getStartPoint();
		void setEndPoint(Point endPoint);
		Point getEndPoint();
		};

         