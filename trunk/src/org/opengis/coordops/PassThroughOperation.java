
//  Class:  PassThroughOperation
				
package org.opengis.coordops;
public interface PassThroughOperation extends 
					SingleOperation
		{
		void setModifiedCoordinate(int[] modifiedCoordinate);
		int[] getModifiedCoordinate();
		void setUsesOperation(Operation usesOperation);
		Operation getUsesOperation();
		};

			