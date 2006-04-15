package org.opengis.filter.sort;

import java.util.ArrayList;
import java.util.List;

import org.opengis.util.CodeList;

/**
 * Captures the SortBy order, ASC or DESC.
 * 
 * @see <a href="http://schemas.opengis.net/filter/1.1.0/sort.xsd">
 * @author Jody Garnett, Refractions Research.
 * @since GeoTools 2.2, Filter 1.1
 */
public class SortOrder extends CodeList<SortOrder> {
	private static final long serialVersionUID = 7840334200112859571L;
	private static final List<SortOrder> all = new ArrayList<SortOrder>(2);

	/**
	 * Represents acending order.
	 * <p>
	 * Note this has the string representation of ASC to agree
	 * with the Filter 1.1 specification.
	 * </p>
	 */
	public static final SortOrder ASCENDING  = new SortOrder("ASC");
	
	/**
	 * Represents descending order.
	 * <p>
	 * Note this has the string representation of DESC to agree
	 * with the Filter 1.1 specification.
	 * </p> 
	 */	
	public static final SortOrder DESCENDING = new SortOrder("DESC");
	
	private SortOrder( String name ){
		super(name, all );
	}
			
	public CodeList[] family() {
		return (CodeList[]) all.toArray( new CodeList[ all.size()]);
	}

}