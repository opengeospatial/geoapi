
//  Class:  BSplineSurface
            
package org.opengis.geometry.coordinate;
public interface BSplineSurface extends 
					GriddedSurface
		{
		void setDegrees(int[] degree);
		int[] getDegrees();
		void setUknots(Knot[] uknot);
		Knot[] getUknots();
		void setVknots(Knot[] vknot);
		Knot[] getVknots();
		void setKnotSpec(KnotType knotSpec);
		KnotType getKnotSpec();
		void setSurfaceForm(BSplineSurfaceForm surfaceForm);
		BSplineSurfaceForm getSurfaceForm();
		void setIsPolynomial(boolean isPolynomial);
		boolean getIsPolynomial();
		};

         