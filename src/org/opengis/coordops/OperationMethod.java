
//  Class:  OperationMethod
				
package org.opengis.coordops;
public interface OperationMethod
		{
		void setMethodName(String methodName);
		String getMethodName();
		void setMethodID(org.opengis.referencesystem.Identifier[] methodID);
		org.opengis.referencesystem.Identifier[] getMethodID();
		void setFormula(String formula);
		String getFormula();
		void setRemarks(String remarks);
		String getRemarks();
		void setUsesParameter(GeneralOperationParameter[] usesParameter);
		GeneralOperationParameter[] getUsesParameter();
		};

			