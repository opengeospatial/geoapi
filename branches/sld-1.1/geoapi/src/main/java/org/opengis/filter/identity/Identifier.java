package org.opengis.filter.identity;

/**
 * An object identifier.
 * <p>
 * This class is an abstract base for identifiers. Some known identifiers are:
 * <ul>
 * 	<li>FeatureId
 * 	<li>GMLObjectId
 * 	<li>RecordId
 * </ul>
 * </p>
 * @author Justin Deoliveira, The Open Planning Project
 *
 * @param <T> The type of the identifer itself.
 * @param <O> The type of objects to be identified.
 * 
 * @version <A HREF="http://www.opengis.org/docs/02-059.pdf">Implementation specification 1.0</A>
 * @author Jody Garnett, Refractions Research Inc.
 */
public interface Identifier<T,O> {

	/**
	 * @return The identifier itself.
	 */
	T getID();
	
	/**
	 * Determines if the id of an object matches the value of the identifier.
	 * 
	 * @param object The object to perform the test against.
	 * 
	 * @return <code>true</code> if a match, otherwise <code>false</code>
	 */
	boolean matches( O object );
	
	/**
	 * @return A string representation of the identfier.
	 */
	String toString();
}
