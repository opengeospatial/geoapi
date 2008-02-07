package org.opengis.feature.type;


/**
 * Describes how a ComplexType may be composed of types, associations and
 * operations.
 * <p>
 * Name and Type need to be defined in most cases.
 * <p>
 * <h2>Naming</h2>
 * <p>
 * Since a descriptor is defined with respect to a ComplexAttribute you can use
 * the provided name to lookup a descriptor at runtime. The name however belongs
 * to a namespace that may have no relationship with the containing ComplexType,
 * so please be sure
 * </p>
 *
 * <h2>Note on Use of User Data</h2>
 * </p>
 * User Data is used to allow additional "temporary" metadata to be associated
 * with attributes descriptors in order to facilitiate procesing services. These
 * services traditionally setting up "shadow" structures such as a HashMap.
 * Allowing non persisted bread crumbs is considered preferable.
 * </p>
 *
 * @author Jody Garnett, Refractions Research
 */
public interface PropertyDescriptor {
    /**
     * Used to retrieve application specific data associated with this
     * Descriptor.
     * <p>
     * Client application often are forced to keep tract of additional informal
     * metadata during processing or transformation opperations. By supporting
     * user data in a limited way offer a way to prevent the creation of
     * numerous Map<PropertyDescriptor,Object> in client code that must be kept
     * in sync with the type system.
     * </p>
     * <p>
     * There is no bridge from our Type system to the formal ISO Metadata
     * classes right now, please use this facility as a temporary measure and
     * join us on the developers list as we would request your assistence.
     * </p>
     * <p>
     * A very simple example is the association of an XML prefix with this
     * attribute descriptor.
     * </p>
     *
     * @param key
     *            key used to retrive user data
     * @return user data previously stored under the provided key
     */
    void putUserData(Object key, Object data);

    /**
     * Used to retrieve application specific data associated with this
     * PropertyType.
     * <p>
     * Client application often are forced to keep tract of additional informal
     * metadata during processing or transformation operations. By supporting
     * user data in a limited way offer a way to prevent the creation of
     * numerous Map<PropertyDescriptor,Object> in client code that must be kept
     * in sync with the feature model.
     * </p>
     * <p>
     * There is no bridge from our Type system to the formal ISO Metadata
     * classes right now, please use this facility as a temporary measure and
     * join us on the developers list as we would request your assistance.
     * </p>
     *
     * @param key
     *            key used to retrieve user data
     * @return user data previously stored under the provided key
     */
    Object getUserData(Object key);

    /**
     * Indicates Name of defined attribute in a ComplexType, this method may
     * never return a null value.
     * <p>
     * The name is actually defined by the sub type, this is derived quantity.
     * </p>
     */
    Name getName();

    /**
     * Convenience method for getting at the type of the descriptor.
     * <p>
     * This is considered "derived" because the specific type is declared by the
     * subclass.
     * </p>
     */
    PropertyType type();
}
