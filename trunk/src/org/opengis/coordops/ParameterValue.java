
//  Class:  ParameterValue
				
package org.opengis.coordops;
enum ParameterValueEnum { 
					  value,   stringValue,   integerValue,   booleanValue,   valueList,   integerValueList,   valueFile,   valueOfParameter
					}; 

union  ParameterValue  switch (ParameterValueEnum)  {
		case e_value: Measure value;
		case e_stringValue: String stringValue;
		case e_integerValue: int integerValue;
		case e_booleanValue: boolean booleanValue;
		case e_valueList: Measure[] valueList;
		case e_integerValueList: int[] integerValueList;
		case e_valueFile: String valueFile;
		case e_valueOfParameter: OperationParameter valueOfParameter;
 		};

			