
//  Class:  Expression
            
package org.opengis.topology.primitive;
public interface Expression		{
		void setTerms(ExpressionTerm[] term);
		ExpressionTerm[] getTerms();
		Expression  getPlus (
 					Expression  s );
		Expression  getMinus (
 					Expression  s );
		Expression  getNegate ( );
		boolean  isZero ( );
		boolean  isCycle ( );
		Expression  getBoundary ( );
		Expression  getCoBoundary ( );
		boolean  getEquals (
 					Expression  s );
		org.opengis.topology.complex.Complex  getSupport ( );
		};

         