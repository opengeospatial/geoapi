
//  Class:  Ellipsoid
				
package org.opengis.datum;
public interface Ellipsoid
		{
		void setEllipsoidName(String ellipsoidName);
		String getEllipsoidName();
		void setEllipsoidID(org.opengis.referencesystem.Identifier[] ellipsoidID);
		org.opengis.referencesystem.Identifier[] getEllipsoidID();
		void setSemiMajorAxis(org.opengis.measure.Length semiMajorAxis);
		org.opengis.measure.Length getSemiMajorAxis();
		void setSecondDefiningParameter(SecondDefiningParameter secondDefiningParameter);
		SecondDefiningParameter getSecondDefiningParameter();
		void setRemarks(String remarks);
		String getRemarks();
		};

			