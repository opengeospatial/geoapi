
//  Class:  Operation
				
package org.opengis.coordops;
public interface Operation extends 
					SingleOperation
		{
		void setUsesValue(GeneralParameterValue[] usesValue);
		GeneralParameterValue[] getUsesValue();
		void setUsesMethod(OperationMethod usesMethod);
		OperationMethod getUsesMethod();
		};

			