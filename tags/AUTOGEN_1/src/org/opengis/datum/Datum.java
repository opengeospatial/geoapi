
//  Class:  Datum
				
package org.opengis.datum;
public interface Datum
		{
		void setDatumName(String datumName);
		String getDatumName();
		void setDatumID(org.opengis.referencesystem.Identifier[] datumID);
		org.opengis.referencesystem.Identifier[] getDatumID();
		void setAnchorPoint(String anchorPoint);
		String getAnchorPoint();
		void setRealizationEpoch(Date realizationEpoch);
		Date getRealizationEpoch();
		void setValidArea(org.opengis.extent.Extent validArea);
		org.opengis.extent.Extent getValidArea();
		void setScope(String scope);
		String getScope();
		void setRemarks(String remarks);
		String getRemarks();
		};

			