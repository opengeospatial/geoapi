
//  Class:  GriddedSurface
            
package org.opengis.geometry.coordinate;
public interface GriddedSurface extends 
					ParametricCurveSurface
		{
		void setControlPoint(PointGrid controlPoint);
		PointGrid getControlPoint();
		void setRows(int rows);
		int getRows();
		void setColumns(int columns);
		int getColumns();
		};

         