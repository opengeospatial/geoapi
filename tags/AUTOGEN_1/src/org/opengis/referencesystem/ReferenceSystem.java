
//  Class:  ReferenceSystem
			
package org.opengis.referencesystem;
public interface ReferenceSystem
		{
		void setSrsName(String srsName);
		String getSrsName();
		void setSrsID(Identifier[] srsID);
		Identifier[] getSrsID();
		void setValidArea(org.opengis.extent.Extent validArea);
		org.opengis.extent.Extent getValidArea();
		void setScope(String scope);
		String getScope();
		void setRemarks(String remarks);
		String getRemarks();
		};

		