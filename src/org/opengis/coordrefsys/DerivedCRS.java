
//  Class:  DerivedCRS
			
package org.opengis.coordrefsys;
public interface DerivedCRS extends 
					GeneralDerivedCRS
		{
		void setDerivedCRSType(DerivedCRSType derivedCRSType);
		DerivedCRSType getDerivedCRSType();
		};

		