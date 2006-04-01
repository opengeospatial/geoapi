package org.opengis.feature.type;

import java.util.Map;
import java.util.Set;

/**
 * Allows for type discouverability and reuse.
 * 
 * @author Jody Garnett, Refractions Research, Inc.
 *
 */
public interface Schema extends Map<Name,AttributeType>{
	public Namespace keySet();	
	public Namespace getNamespace(); // for java 1.4.x
}
