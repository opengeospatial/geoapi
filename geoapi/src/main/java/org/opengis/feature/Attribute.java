package org.opengis.feature;

import org.opengis.feature.type.AttributeDescriptor;
import org.opengis.feature.type.AttributeType;

/**
 * An extension of Property for an attribute.
 * <p>
 * The notion of an "attribute" is similar to that of an attribute in UML.
 * </p>
 * <p>
 * This interface is capable of modeling "primitive data", things like strings,
 * numerics, dates, etc... However for "complex data" (that is non-primitive 
 * data types which are made up other primitive data types), a specific 
 * sub-interface is used, see {@link ComplexAttribute}.
 * </p>
 * <p>
 * An analogy for an attribute is a "field" in a java object. A field also
 * brings together a field name, value and type.
 * </p>
 * 
 * <p>
 * <h3>Identifiable</h3>
 * 
 * When an attribute is identifiable the {@link #getID()} method returns a
 * unique identifier for the attribute. The type of the attribute is used to
 * determine identifiability.
 * 
 * <pre>
 * Attribute attribute = ...;
 * if ( attribute.getType().isIdentified() ) {
 *   String id = attribute.getID();
 * }
 * </pre>
 * </p>
 * 
 * @see Property
 * 
 * @author Jody Garnett (Refractions Research)
 * @author Justin Deoliveira (The Open Planning Project)
 */
public interface Attribute extends Property {

    /**
     * Override of {@link Property#getDescriptor()} which type narrows to
     * {@link AttributeDescriptor}.
     * 
     * @see Property#getDescriptor()
     */
    AttributeDescriptor getDescriptor();

    /**
     * Override of {@link Property#getType()} which type narrows to
     * {@link AttributeType}.
     * 
     * @see Property#getType()
     */
    AttributeType getType();

    /**
     * Unique Identifier for the attribute.
     * <p>
     * This value is non-null in the case that
     * <code>getType().isIdentifiable()</code> is <code>true</code>.
     * </p>
     * 
     * @return A unique identifier for the attribute, or <code>null</code> if
     *         the attribute is non-identifiable.
     */
    String getID();
}