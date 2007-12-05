package org.opengis.feature.type;


/**
 * Containment information for a complex attribute.
 * <p>
 * ComplexType specific information such as Name, type and multiplicity are defined.
 * <p>
 * If in the future the nature of the containment relationship needs further
 * definition; you may expect additional information to be gathered here.
 * </p>
 *
 * @author Jody Garnett, Refractions Research
 */
public interface StructuralDescriptor extends PropertyDescriptor {
    /** Captures cardinality */
    int getMinOccurs();

    /** Captures cardinality */
    int getMaxOccurs();
}
