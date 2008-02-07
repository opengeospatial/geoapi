package org.opengis.feature.type;

import java.util.Collection;
import java.util.Set;

import org.opengis.filter.Filter;


/**
 * Declaration of attribute type.
 * <p>
 * Please note that this is a formal "dynamic type system", we have not
 * indicated at this level *which* class this attribute type is bound to.
 * As an implementation option getBinding may be restricted to a specific
 * class for compile time checks, this will only be of benefit when
 * implementing wrappers around existing staticly defined Java domain models.
 */
public interface AttributeType extends PropertyType {
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
    AttributeType getSuper();

    /** Indicate that this AttributeType may not be used directly */
    boolean isAbstract();

    /**
     * Java class bound to this content type.
     */
    Class<?> getBinding();

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
     * @return Collection<OperationDescriptor> that may be invoked on values of this type.
     */
    Collection<OperationDescriptor> getOperations();
}
