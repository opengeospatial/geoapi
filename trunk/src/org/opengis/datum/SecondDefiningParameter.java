
//  Class:  SecondDefiningParameter
				
package org.opengis.datum;
enum SecondDefiningParameterEnum { 
					  inverseFlattening,   semiMinorAxis,   isSphere
					}; 

union  SecondDefiningParameter  switch (SecondDefiningParameterEnum)  {
		case e_inverseFlattening: Scale inverseFlattening;
		case e_semiMinorAxis: Length semiMinorAxis;
		case e_isSphere: String isSphere;
 		};

			