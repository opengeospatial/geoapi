package org.opengis.feature.type;


/**
 * Indicating a named association.
 * <p>
 * This class carries the ComplexType specific information requried
 * for using an association. Name, type and multiplicity are defined.
 * <p>
 * The goal of associations is to allow for a graph of data, this is contrast
 * to attribtues which indicate containement.
 * <p>
 * Please see the description of AssociationType for more guidelines on capturing
 * your data modeling needs with association.
 * </p>
 * @author Jody Garnett, Refractions Research
 */
public interface AssociationDescriptor<A extends AssociationType> extends StructuralDescriptor {
		
	/**
	 * Indicates the type of this association.
	 * <p>
	 * This indicates the relationship represented by the association.
	 */
	A getType();

}