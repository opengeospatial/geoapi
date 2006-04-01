package org.opengis.feature.type;

import java.util.Set;

import org.opengis.filter.Filter;

/**
 * Association information, immutable.
 * <p>
 * The model must recognize the following specific Association types:
 * <ul>
 * <li>aggregation
 * <li>spatial
 * <li>temporal
 * </ul>
 * Note that as usual multiplicity is left for the AssociationDescriptor, this class
 * is used to capture relationships between types.
 * <p>
 * Note: I am treating the domain of the association type similar to that of Attribute
 * Type, the intention is to indicate the relationship that a FeatureCollection has
 * with its contents (specifically a memberOf).
 * </p>
 * <p>
 * Care should be taken with associations, keep the aggregation, spatial, temporal goal in mind:
 * <ul>
 * <li>aggregation: a university may contain the buildings
 * <li>spatial: "touches" the current feature would be used in a graph package
 * <li>temporal: "before" the current feature would be used in a versioning system
 * </ul>
 * You should not use Associations to capture information such as "super" as this
 * represents a relationship of abstractions used to define a type, with associations we are
 * aiming to let you describe how the types in your system are related.
 * </p>
 * <p>
 * You may gather associations into a heirarchy where refine is needed, thus a lake could be
 * associated with "connected" rivers, and this refined with "up stream" and "down stream". 
 * </p>
 * @author Jody Garnett, Refractions Research, Inc.
 */
public interface AssociationType<B extends AttributeType> extends PropertyType {
	
	/**
	 * True if this type is usable as a target of a reference.
	 * 
	 * @return true if this complex type must have non null getID()
	 */
	boolean isIdentified();
	
	/**
	 * Access to super type information.
	 * <p>
	 * The super type may contain additional restrictions to be considered, or a
	 * definition of isNilable.
	 * </p>
	 * 
	 * @return AttributeType of supertype
	 */
	public AssociationType<? super B> getSuper();

	/** Indicate that this AttributeType may not be used directly */
	public boolean isAbstract();

	/**
	 * Java class bound to this content type.
	 */
	public Class<B> getAssociation();

	/**
	 * List of restrictions used to limit the allowable values for objects of
	 * this type.
	 * <p>
	 * These restrictions should be considered in light of those available through getSuper,
	 * in the case where Restrictions conflict these should be considered complete overrides
	 * of the restrictions available via the getSuper.
	 * </p>
	 * @return List<Restriction> used to validate allowable values.
	 */
	public Set<Filter> getRestrictions();
	
}