
//  Class:  OperationParameterGroup
				
package org.opengis.coordops;
public interface OperationParameterGroup extends 
					GeneralOperationParameter
		{
		void setGroupName(String groupName);
		String getGroupName();
		void setGroupID(org.opengis.referencesystem.Identifier[] groupID);
		org.opengis.referencesystem.Identifier[] getGroupID();
		void setMaximumOccurs(int maximumOccurs);
		int getMaximumOccurs();
		void setRemarks(String remarks);
		String getRemarks();
		void setIncludesParameter(GeneralOperationParameter[] includesParameter);
		GeneralOperationParameter[] getIncludesParameter();
		};

			