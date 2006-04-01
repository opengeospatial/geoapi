package org.opengis.feature.type;

import java.util.Map;
import java.util.Set;

/**
 * Allows for type discouverability and reuse.
 * 
 * @author Jody Garnett, Refractions Research, Inc.
 *
 */
public interface Schema extends Map<TypeName,AttributeType> {
	/**
	 * The keySet is actually a Namespace<TypeName> where you can also look
	 * up TypeNames by their local part.
	 */
	public Namespace<TypeName> keySet();
	
	/** Same as keySet() */
	public Namespace<TypeName> namespace(); // for java 1.4.x
	
	/**
	 * Here is a helper method to retrive the "uri" for this schema.
	 * 
	 * @return value from namespace().getName().toString().
	 */
	public String toURI();
}
