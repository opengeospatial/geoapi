
//  Class:  CovarianceMatrix
				
package org.opengis.dataquality;
public interface CovarianceMatrix extends 
					PositionalAccuracy
		{
		void setUnitOfMeasure(UnitOfMeasure[] unitOfMeasure);
		UnitOfMeasure[] getUnitOfMeasure();
		void setIncludesElement(CovarianceElement[] includesElement);
		CovarianceElement[] getIncludesElement();
		};

			