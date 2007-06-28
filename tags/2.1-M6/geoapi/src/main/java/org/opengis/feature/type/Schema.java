package org.opengis.feature.type;

import java.util.Map;

//import java.util.Set;

/**
 * Allows for type discouverability and reuse.
 * <p>
 * We have not specified how schemas may be looked up, we recommend a JNDI
 * solution based on the namespace uri used here. If needed you may also
 * consider a Map<Name,Schema> in your application.
 * </p>
 * 
 * @author Jody Garnett, Refractions Research, Inc.
 */
public interface Schema extends Map<TypeName, AttributeType> {
	/**
	 * The keySet is actually a Namespace<TypeName> where you can also look up
	 * TypeNames by their local part.
	 */
	Namespace<TypeName> keySet();

	/** Dervived quantity from keySet() */
	Namespace<TypeName> namespace(); // for java 1.4.x

	/**
	 * Here is a helper method to retrive the "uri" for this schema.
	 * 
	 * @return value from namespace().getURI().
	 */
	String toURI();
	
	/**
	 * Used to select a profile of this schema.
	 * <p>
	 * Used to select a subset of types for a specific application. Profiles
	 * often are used to express limitiations of a source of data.
	 * </p>
	 * @param profile
	 * @return Subset of this schema
	 */
	Schema profile( Namespace<TypeName> profile );
}
