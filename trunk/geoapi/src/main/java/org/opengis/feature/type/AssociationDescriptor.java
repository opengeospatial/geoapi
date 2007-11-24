package org.opengis.feature.type;


/**
 * Describes an instance of an Association.
 *
 * @author Jody Garnett, Refractions Research
 * @author Justin Deoliveira, The Open Planning Project
 */
public interface AssociationDescriptor extends PropertyDescriptor {
        
    /**
     * Override of {@link PropertyDescriptor#getType()} which type narrows to
     * {@link AssocicationType}.
     *
     *  @see PropertyDescriptor#getType()
     */
    AssociationType getType();

}
