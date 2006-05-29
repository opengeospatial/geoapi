package org.opengis.feature.type;

import java.util.Set;

import org.opengis.filter.Filter;
import org.opengis.util.InternationalString;

/**
 * PropertyType information, captured as AttributeType, AssociationType and
 * OperationType.
 * <p>
 * The following information from the GFM MetaModel are represented:
 * <ul>
 * <li>memberName - actually bound to specific TypeName, these names are
 * gathered tgether into a Namespace
 * <li>description - we use an InternationalString as description may be
 * available in more then one language.
 * </ul>
 * </p>
 * <p>
 * We meet the following requirements:
 * <ul>
 * <li>Constraints on Properties: are represented with Filter
 * </ul>
 * </p>
 * 
 * @author Jody Garnett, Refractions Research, Inc.
 */
public interface PropertyType {
	/**
	 * Indicates the actual name of this PropertyType.
	 * <p>
	 * TypeName is used, and becomes important when working with supertypes and
	 * complex content for AttributeTypes.
	 * </p>
	 * <p>
	 * Follows Java beans naming conventions indicating this is part of our data
	 * model.
	 * </p>
	 * 
	 * @return Name containing both the namespace and name for this type
	 */
	TypeName getName();

	/**
	 * Access to super type information.
	 * <p>
	 * The super type may contain additional restrictions to be considered, or a
	 * definition of isNilable.
	 * </p>
	 * 
	 * @return PropertyType of supertype
	 */
	PropertyType getSuper();

	/**
	 * Indicate that this PropertyType may not be used directly and must be
	 * subtyped
	 */
	boolean isAbstract();

	/**
	 * List of restrictions used to limit the allowable values for objects of
	 * this type.
	 * <p>
	 * These restrictions should be considered in light of those available
	 * through getSuper, in the case where Restrictions conflict these should be
	 * considered complete overrides of the restrictions available via the
	 * getSuper.
	 * </p>
	 * <p>
	 * In the GFM this is known as constraints.
	 * </p>
	 * 
	 * @return List<Restriction> used to validate allowable values.
	 */
	Set<Filter> getRestrictions();

	/**
	 * PropertyType identity should be based on TypeName.
	 * <p>
	 * </p>
	 * 
	 * @return true iff this equals other
	 */
	boolean equals(Object other);

	/**
	 * Hashcode should be based on TypeName.
	 * 
	 * @return getName().hashCode()
	 */
	int hashCode();

	/**
	 * A description for the type is allowed.
	 * <p>
	 * InternationalString is used to allow for translations.
	 * </p>
	 * 
	 * @return description of this type
	 */
	InternationalString getDescription();

	/**
	 * Used to retrieve application specific data associated with this
	 * PropertyType.
	 * <p>
	 * Client application often are forced to keep tract of additional informal
	 * metadata during processing or transformation opperations. By supporting
	 * user data in a limited way offer a way to prevent the creation of
	 * numerous Map<PropertyType,Object> in client code that must be kept in
	 * sync with the feature model.
	 * </p>
	 * <p>
	 * There is no bridge from our Type system to the formal ISO Metadata
	 * classes right now, please use this facility as a temporary measure and
	 * join us on the developers list as we would request your assistence.
	 * </p>
	 * 
	 * @param key
	 *            key used to retrive user data
	 * @return user data previously stored under the provided key
	 */
	Object getUserData(Object key);

	/**
	 * Used to hold application specific data associated with this PropertyType.
	 * <p>
	 * Client application often are forced to keep tract of additional informal
	 * metadata during processing or transformation opperations. By supporting
	 * user data in a limited way offer a way to prevent the creation of
	 * numerous Map<PropertyType,Object> in client code.
	 * </p>
	 * <p>
	 * There is no bridge from our Type system to the formal ISO Metadata
	 * classes right now, please use this facility as a temporary measure and
	 * join us on the developers list as we would request your assistence.
	 * </p>
	 * <p>
	 * User's please note the following limitations on this facility:
	 * <ul>
	 * <li>Your data is not guaranteed to be persisted
	 * </ul>
	 * Implementors please consider the following suggestions:
	 * <ul>
	 * <li>You may consider offer to persist String to Serializable mappings
	 * <li>You may wish to collect user in a lazy fashion at the Schema level
	 * rather then burden your PropertyType implementations with a user data
	 * Map.
	 * </ul>
	 * 
	 * @param key
	 *            key used to retrive user data
	 * @return user data previously stored under the provided key
	 */
	void putUserData(Object key, Object data);
}