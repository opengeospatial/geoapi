
//  Class:  OperationParameter
				
package org.opengis.coordops;
public interface OperationParameter extends 
					GeneralOperationParameter
		{
		void setParameterName(String parameterName);
		String getParameterName();
		void setParameterID(org.opengis.referencesystem.Identifier[] parameterID);
		org.opengis.referencesystem.Identifier[] getParameterID();
		void setRemarks(String remarks);
		String getRemarks();
		};

			