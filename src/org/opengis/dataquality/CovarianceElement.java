
//  Class:  CovarianceElement
				
package org.opengis.dataquality;
public interface CovarianceElement		{
		void setRowIndex(int rowIndex);
		int getRowIndex();
		void setColumnIndex(int columnIndex);
		int getColumnIndex();
		void setCovariance(Double covariance);
		Double getCovariance();
		};

			