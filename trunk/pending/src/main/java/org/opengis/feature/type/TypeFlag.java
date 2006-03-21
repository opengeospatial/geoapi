package org.opengis.feature.type;

import java.util.ArrayList;
import java.util.List;

import org.opengis.util.CodeList;

/**
 * Flags to be used in the constructions of types.
 * 
 * @author Justin Deoliveira, The Open Planning Project, jdeolive@openplans.org
 *
 */
public class TypeFlag extends CodeList<TypeFlag> {

	/**
	 * List of all flags. 
	 */
	private static final List<TypeFlag> all = new ArrayList<TypeFlag>(5); 
	
	/**
	 * Flag indicating all flags should be set.
	 */
	public static final TypeFlag ALL = new TypeFlag("ALL");
	
	/**
	 * Flag indicating "default" flags should be set.
	 */
	public static final TypeFlag DEFAULT = new TypeFlag("DEFAULT");
	
	/** 
	 * Flag indicating wether a type is identifiable.
	 */
	public static final TypeFlag IDENTIFIABLE = new TypeFlag("IDENTIFIABLE");
	
	/** 
	 * Flag indicating wether a type is nillable. 
	 */
	public static final TypeFlag NILLABLE = new TypeFlag("NILLABLE");
	
	/** 
	 * Flag indicating wether a type is abstract .
	 */
	public static final TypeFlag ABSTRACT = new TypeFlag("ABSTRACT");
	
	/** 
	 * Name of the flag.
	 */
	String name;
	
	private TypeFlag(String name) {
		super(name, all);
		this.name = name;
	}
	
	@Override
	public CodeList[] family() {
		return (CodeList[]) all.toArray( new CodeList[ all.size()]);
	}

}
