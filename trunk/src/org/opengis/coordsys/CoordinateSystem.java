
//  Class:  CoordinateSystem
				
package org.opengis.coordsys;
public interface CoordinateSystem
		{
		void setCsName(String csName);
		String getCsName();
		void setCsID(org.opengis.referencesystem.Identifier[] csID);
		org.opengis.referencesystem.Identifier[] getCsID();
		void setRemarks(String remarks);
		String getRemarks();
		void setUsesAxis(CoordinateSystemAxis[] usesAxis);
		CoordinateSystemAxis[] getUsesAxis();
		};

			