
//  Class:  CoordinateOperation
				
package org.opengis.coordops;
public interface CoordinateOperation
		{
		void setCoordinateOperationName(String coordinateOperationName);
		String getCoordinateOperationName();
		void setCoordinateOperationID(org.opengis.referencesystem.Identifier[] coordinateOperationID);
		org.opengis.referencesystem.Identifier[] getCoordinateOperationID();
		void setOperationVersion(String operationVersion);
		String getOperationVersion();
		void setValidArea(org.opengis.extent.Extent validArea);
		org.opengis.extent.Extent getValidArea();
		void setScope(String scope);
		String getScope();
		void setPositionalAccuracy(org.opengis.dataquality.PositionalAccuracy positionalAccuracy);
		org.opengis.dataquality.PositionalAccuracy getPositionalAccuracy();
		void setRemarks(String remarks);
		String getRemarks();
		void setSourceCRS(org.opengis.coordrefsys.CRS sourceCRS);
		org.opengis.coordrefsys.CRS getSourceCRS();
		void setTargetCRS(org.opengis.coordrefsys.CRS targetCRS);
		org.opengis.coordrefsys.CRS getTargetCRS();
		};

			