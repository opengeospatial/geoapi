
//  Class:  CoordinateSystemAxis
				
package org.opengis.coordsys;
public interface CoordinateSystemAxis
		{
		void setAxisName(String axisName);
		String getAxisName();
		void setAxisID(org.opengis.referencesystem.Identifier[] axisID);
		org.opengis.referencesystem.Identifier[] getAxisID();
		void setAxisAbbrev(String axisAbbrev);
		String getAxisAbbrev();
		void setAxisDirection(String axisDirection);
		String getAxisDirection();
		void setAxisUnitID(UnitOfMeasure axisUnitID);
		UnitOfMeasure getAxisUnitID();
		void setRemarks(String remarks);
		String getRemarks();
		};

			