package org.opengis.feature.type;

import java.util.Collection;
import java.util.Set;

import org.opengis.filter.Filter;

/**
 * AttributeType information, immutable.
 */
public interface AttributeType<B> extends PropertyType {

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
	AttributeType<? super B> getSuper();

	/** Indicate that this AttributeType may not be used directly */
	boolean isAbstract();

	/**
	 * Java class bound to this content type.
	 */
	Class<B> getBinding();

	/**
	 * List of restrictions used to limit the allowable values for objects of
	 * this type.
	 * <p>
	 * These restrictions should be considered in light of those available
	 * through getSuper, in the case where Restrictions conflict these should be
	 * considered complete overrides of the restrictions available via the
	 * getSuper.
	 * </p>
	 * 
	 * @return List<Restriction> used to validate allowable values.
	 */
	Set<Filter> getRestrictions();

	/**
	 * Operations that may be invoked against this type.
	 * 
	 * @return
	 */
	Collection<OperationDescriptor> getOperations();
	
}