
//  Class:  AbsoluteExternalPositionalAccuracy
				
package org.opengis.dataquality;
public interface AbsoluteExternalPositionalAccuracy extends 
					PositionalAccuracy
		{
		void setResult(Measure[] result);
		Measure[] getResult();
		};

			