
//  Class:  ParameterValueGroup
				
package org.opengis.coordops;
public interface ParameterValueGroup extends 
					GeneralParameterValue
		{
		void setValuesOfGroup(OperationParameterGroup valuesOfGroup);
		OperationParameterGroup getValuesOfGroup();
		void setTheGeneralParameterValue(GeneralParameterValue[] theGeneralParameterValue);
		GeneralParameterValue[] getTheGeneralParameterValue();
		};

			