package org.opengis.feature;

import org.opengis.feature.type.Name;
import org.opengis.feature.type.PropertyDescriptor;
import org.opengis.feature.type.PropertyType;


/**
 * Contains information as defined by an descriptor.
 * <p>
 * A Property is used to hold information in our data model. This is
 * similar to how a Map.Entry holds a key/value pair in a Map.
 * </p>
 * The value held in a PropertyDescriptor/Value pair is defined
 * according to the PropertyType:
 * <ul>
 * <li>AttributeType - the value directly containing data
 * <li>AssociationType - the value is a link to another object
 * </ul>
 * <p>
 * If this property is contained in another data structure you may
 * use the provided Descriptor. This descriptor will provide any
 * additional information (such as the name and multiplicity) needed.
 * </p>
 * @author Jody Garnett (Refractions Research)
 */
public interface Property {
    /**
     * Indicates the Descriptor for this content.
     * <p>
     * The attribute descriptor formally captures the name and multiplicity
     * information and type information.
     * </p>
     * @return Descriptor, may be null if Property is not contained in a complex attribute.
     */
    PropertyDescriptor getDescriptor();

    /**
     * Convenience method to access descriptor.
     * <p>
     * This method only exists to aid in the transform to Java 1.4 since the
     * {@link #getDescriptor()} is erased to allow for type narrowing.
     * </p>
     *
     */
    PropertyDescriptor descriptor();

    /**
     * Name (from the descriptor) of this Property.
     *
     * @return name of this property.
     */
    Name name();

    /**
     * Indicate the PropertyType, if we have a descriptor it will be in agreement.
     *
     * @return PropertyType information describing content and use
     */
    PropertyType getType();
}
