
//  Class:  BSplineCurve
            
package org.opengis.geometry.coordinate;
public interface BSplineCurve extends 
					SplineCurve
		{
		void setCurveForm(SplineCurveForm curveForm);
		SplineCurveForm getCurveForm();
		void setKnotSpec(KnotType knotSpec);
		KnotType getKnotSpec();
		void setIsPolynomial(boolean isPolynomial);
		boolean getIsPolynomial();
		};

         