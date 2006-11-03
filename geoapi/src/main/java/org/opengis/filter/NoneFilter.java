package org.opengis.filter;

import java.io.Serializable;

/**
 * Indicating "no filtering", evaultates to <code>true</code>.
 * <p>
 * This is a placeholder filter intended to be used in data
 * structuring definition.
 * <ul>
 * <li>
 * <ul>NONE or  Filter ==> NONE
 * <ul>NONE and Filter ==> Filter
 * <ul>       not NONE ==> ALL
 * </ul>
 * The above does imply that the OR opperator can short circuit on
 * encountering NONE.
 * </p>
 * 
 * @author Jody Garnett, Refractions Research, Inc.
 */
public final class NoneFilter implements Filter, Serializable {	
	private static final long serialVersionUID = -8429407144421087160L;
	
	/** non extensible */
	NoneFilter(){}
	
	public Object accept(FilterVisitor visitor, Object extraData) {
		return visitor.visit( this, extraData );
	}
	/**
	 * Returns <code>true</code>, content is included. 
	 */
	public boolean evaluate(Object object) {
		return true;
	}
	@Override
	public boolean equals(Object obj) {
		return Filter.NONE == obj;
	}
	@Override
	public int hashCode() {
		return 0;
	}
	public String toString() {
		return "Filter.NONE";
	}
}
