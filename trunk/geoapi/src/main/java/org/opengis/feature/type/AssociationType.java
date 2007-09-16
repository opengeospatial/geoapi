package org.opengis.feature.type;

import java.util.Set;

import org.opengis.filter.Filter;

/**
 * Type of association between two properties.
 * <p>
 * The AssociationType is used to represent the way in which two properties are
 * associated. Common examples include:
 * <ul>
 * <li>aggregation: a university may contain the buildings
 * <li>spatial: "touches" the current feature would be used in a graph package
 * <li>temporal: "before" the current feature would be used in a versioning
 * system
 * <li>custom: domain specific relationships such as "business partners"
 * </ul>
 * Multiplicity information described as part of the AssociationDescriptor, here
 * we are focused on capturing the way in which values can be related.
 * <p>
 * Your applications associations may be gathered into a hierarchy offering
 * further refinement where needed. Thus a lake can be associated with
 * "connected" rivers, and thus refined with "up stream" and "down stream"
 * associations.
 * </p>
 * 
 * @author Jody Garnett, Refractions Research, Inc.
 */
public interface AssociationType extends PropertyType {

	/**
	 * Access to super type information.
	 * <p>
	 * The super type may contain additional restrictions to be considered, or a
	 * definition of isNilable.
	 * </p>
	 * 
	 * @return AttributeType of supertype
	 */
	public AssociationType getSuper();

	/**
	 * Indicate that this AttributeType may not be used directly.
	 * <p>
	 * An example abstract association would be "spatial" which would need to be
	 * sub typed to indicate "touches" or "contained".
	 * </p>
	 */
	public boolean isAbstract();

	/**
	 * AttributeType related by this association.
	 * <p>
	 * This is the AttributeType you are in effect pointing to by using an
	 * association.
	 * <p>
	 */
	public AttributeType getReferenceType();
}