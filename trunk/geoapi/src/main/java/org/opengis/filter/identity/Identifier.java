package org.opengis.filter.identity;

/**
 * An object identifier.
 * <p>
 * This class is an abstract base for identifiers. Some known identifiers are:
 * <ul>
 *   <li>FeatureId</li>
 *   <li>GMLObjectId</li>
 *   <li>RecordId</li>
 * </ul>
 * </p>
 *
 * @param <T> The type of the identifier itself.
 * @param <O> The type of objects to be identified.
 *
 * @version <A HREF="http://www.opengis.org/docs/02-059.pdf">Implementation specification 1.0</A>
 * @author Jody Garnett (Refractions Research)
 * @author Justin Deoliveira (The Open Planning Project)
 */
public interface Identifier<T,O> {
    /**
     * Returns the identifier itself.
     */
    T getID();

    /**
     * Determines if the id of an object matches the value of the identifier.
     *
     * @param object The object to perform the test against.
     * @return {@code true} if a match, otherwise {@code false}.
     */
    boolean matches(O object);

    /**
     * Returns a string representation of the identifier.
     */
    String toString();
}
