
//  Class:  ConcatenatedOperation
				
package org.opengis.coordops;
public interface ConcatenatedOperation extends 
					CoordinateOperation
		{
		void setUsesOperation(SingleOperation[] usesOperation);
		SingleOperation[] getUsesOperation();
		};

			