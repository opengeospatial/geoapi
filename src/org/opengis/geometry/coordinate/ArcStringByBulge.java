
//  Class:  ArcStringByBulge
            
package org.opengis.geometry.coordinate;
public interface ArcStringByBulge extends 
					org.opengis.geometry.primitive.CurveSegment
		{
		void setBulges(double[] bulge);
		double[] getBulges();
		void setNumArc(int numArc);
		int getNumArc();
		void setNormals(double[][] normal);
		double[][] getNormals();
		ArcString  getAsArcString ( );
		};

         