
//  Class:  ExpressionTerm
            
package org.opengis.topology.primitive;
public interface ExpressionTerm		{
		void setCoefficient(int coefficient);
		int getCoefficient();
		void setVariable(DirectedTopo variable);
		DirectedTopo getVariable();
		};

         