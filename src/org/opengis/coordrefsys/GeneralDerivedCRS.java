
//  Class:  GeneralDerivedCRS
			
package org.opengis.coordrefsys;
public interface GeneralDerivedCRS extends 
					CoordinateReferenceSystem
		{
		void setBaseCRS(CoordinateReferenceSystem baseCRS);
		CoordinateReferenceSystem getBaseCRS();
		void setDefinedByConversion(org.opengis.coordops.Conversion definedByConversion);
		org.opengis.coordops.Conversion getDefinedByConversion();
		};

		