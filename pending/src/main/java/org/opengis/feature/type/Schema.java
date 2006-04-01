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
	public Namespace keySet();
	
	/** Same as keySet() */
	public Namespace namespace(); // for java 1.4.x
	
	/**
	 * Here is a helper method to retrive the "uri" for this schema.
	 * 
	 * @return value from namespace().getName().toString().
	 */
	public String toURI();
}
