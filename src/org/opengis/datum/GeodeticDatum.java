
//  Class:  GeodeticDatum
				
package org.opengis.datum;
public interface GeodeticDatum extends 
					Datum
		{
		void setUsesPrimeMeridian(PrimeMeridian usesPrimeMeridian);
		PrimeMeridian getUsesPrimeMeridian();
		void setUsesEllipsoid(Ellipsoid usesEllipsoid);
		Ellipsoid getUsesEllipsoid();
		};

			