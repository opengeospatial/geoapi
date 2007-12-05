package org.opengis.feature;

import java.util.List;

import org.opengis.feature.type.AttributeDescriptor;
import org.opengis.feature.type.AttributeType;
import org.opengis.feature.type.Name;


/**
 * Contains information defining an attribute.
 * <p>
 * An Attribute is used to hold a value (ie aggregation), similar
 * to the way a Map.Entry holds values in a java.util.Map. Attribute
 * is responsible for holding onto the following three things:
 * <ul>
 * <li>descriptor: An AttributeDescriptor acting as the "key" for this attribute
 * <li>value: A Java Object acting as the "value" for this attribute
 * <li>type: An AttributeType acting as the "type" of this attribute
 * </ul>
 * <p>
 * If this Attribute is contained in another data structure you may
 * use the provided Descriptor. This descriptor will provided any additional
 * information (such as name and multiplicity) needed.
 * </p>
 * The use of Attribute in our feature model is similar to the use of a "field" in
 * a Java Object. A field also brings together a field name, value and type.
 * <p>
 * <b>Differences from ISO 19107</b>:
 * We do not use TypeName directly, the functionality is served by our  AttributeType
 * class which provides additional functionality. AttributeType provides a Name, the
 * java class for our value, and any additional restrictions.
 * <p>
 * Validation is provided by way of constraints implemented using Filter.
 *
 * @author Jody Garnett (Refractions Research)
 */
public interface Attribute extends Property {
    /**
     * Indicates the AttirbuteDescriptor for this content.
     * <p>
     * The attribute descriptor formally captures the name and multiplicity
     * information for this attribute. If this attribute is not contained
     * in a container, then the descriptor will be null.
     * </p>
     * @return Descriptor for this attribute, may be null.
     */
    AttributeDescriptor getDescriptor();

    /**
     * Determines if the attribute is allowed to have a <code>null</code> value.
     * <p>
     *	For those attributes which are contained within a complex type
     *	(ie. getDescriptor() != null), this method defers to the descriptor
     * </p>
     *
     * @see AttributeDescriptor#isNillable()
     */
    boolean nillable();

    /**
     * Indicate the AttributeType, if we have a descriptor it will be in agreement.
     *
     * @return AttributeType information describing allowable content
     */
    AttributeType getType();

    /**
     * Unique, immutable identification for domain object being modeled.
     *
     * @return Unique ID, may not be null if getType().isIdentifiable() is true
     */
    String getID();

    /**
     * Access to the content of this Attribute.
     * <p>
     * A subclass may define a more specific method to access this value, in order
     * to improve readability or make allowances for Java 1.4 use.
     * </p>
     * @return Value Object of the class indicated by getType().getBinding()
     */
    Object getValue();

    /**
     * Call operation on <code>this</code> Attribute.
     * <p>
     * @param name Name of operation to be called
     * @param parameters Parameters for the operation
     * @return Result of operation, may be <code>null</code>
     */
    Object operation(Name name, List<Object> parameters);

    /**
     * Set content to newValue
     * @param newValue
     *            Object of the class indicated by getType().getBinding()
     * @throws IllegalArgumentException If newValue does not match getType().getBinding()
     */
    void setValue(Object newValue);
}
