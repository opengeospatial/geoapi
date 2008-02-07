package org.opengis.feature;

import org.opengis.feature.type.AssociationDescriptor;
import org.opengis.feature.type.AssociationType;
import org.opengis.feature.type.AttributeType;


/**
 * Contains information defining an association (i.e. shared value).
 * <p>
 * An Association is used to hold a relation in our data model, similar to the
 * way a Key is used to Associate a Value in a java.util.Map. Rather then use
 * Map.Entry to capture this link we are using a strongly typed AssociationType
 * providing additional information indicating the nature of the association.
 * </p>
 * <p>
 * If this Association is contained in another data structure you may use the
 * provided AssociationDescriptor for additional information. This descriptor
 * will provided any additional information (such as name and multiplicity) needed.
 * </p>
 *
 * @author Jody Garnett, Refractions Research
 */
public interface Association extends Property {
    /**
     * Indicates the AttirbuteDescriptor for this content.
     * <p>
     * The attribute descriptor formally captures the name and multiplicity
     * information for this attribute. If this attribute is not contained in a
     * container, then the descriptor will be null.
     * </p>
     *
     * @return Descriptor for this attribute, may be null.
     */
    AssociationDescriptor getDescriptor();

    /**
     * Indicate the AssociationType, if we have a descriptor it will be in
     * agreement.
     * <p>
     * This information indicates the nature of the relationship captured by this assocation.
     * <p>
     * At a minimum the following categories should be thought about:
     * <ul>
     * <li>aggregation (ie member of shared)
     * <li>temporal (before after )
     * <li>spatial (contained, touches).
     * </ul>
     * </p>
     * @return AssociationType of allowable content
     */
    AssociationType getType();

    /**
     * Indicates the AttributeType we are associated with.
     * <p>
     * Note the target attribute type is likely maintained in another part of the
     * forest, often it will be fetched via either a query or optimally by ID lookup
     * behind the scenes.
     * </p>
     * @return type of attribute we are related to.
     */
    AttributeType getRelatedType();

    /**
     * An associated Attribute.
     * <p>
     * This will be of the type indicated by the getAssociateType.
     *
     * @return associated attribute
     */
    Attribute getRelated();

    /**
     * Set the association to the provided Attribute
     * @param value
     */
    void setRelated(Attribute attribute);
}
