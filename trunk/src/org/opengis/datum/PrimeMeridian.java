
//  Class:  PrimeMeridian
				
package org.opengis.datum;
public interface PrimeMeridian
		{
		void setMeridianName(String meridianName);
		String getMeridianName();
		void setMeridianID(org.opengis.referencesystem.Identifier[] meridianID);
		org.opengis.referencesystem.Identifier[] getMeridianID();
		void setGreenwichLongitude(org.opengis.measure.Angle greenwichLongitude);
		org.opengis.measure.Angle getGreenwichLongitude();
		void setRemarks(String remarks);
		String getRemarks();
		};

			