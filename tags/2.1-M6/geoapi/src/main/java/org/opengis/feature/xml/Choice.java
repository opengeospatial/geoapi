package org.opengis.feature.xml;

import java.util.Set;

import org.opengis.feature.Attribute;
import org.opengis.feature.ComplexAttribute;

// Java 1.4 imports
//import org.opengis.feature.type.AttributeType;

/**
 * Represents a ComplexAttribute in which only one attribute is alloewd a value at a time.
 * <p>
 * This type is equivelent to a ComplexType with the following filter(for choice of A, B or C):
 * <pre><code>
 * (NULL(A) && NOT(NULL(B)) && NOT(NULL(C)) OR
 * (NOT(NULL(A)) && NULL(B) && NOT(NULL(C)) OR
 * (NOT(NULL(A)) && NOT(NULL(B)) && NULL(C))
 * </code></pre>
 * Any implementaiton should keep this in mind if they wish to interact smoothly with
 * user interfaces designed against the pure TypeSystem.
 * </p>
 */
public interface Choice<S extends Set<Attribute>, T extends ChoiceType<S>> 
	extends ComplexAttribute<Attribute,S,T> {

    T getType();
	
    /** Limited to a List of size one */
    S get();
}
