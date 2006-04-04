package org.opengis.feature.type;

/**
 * Describes how a ComplexType may be composed of types,
 * associations and opperations.
 * <p>
 * Name, type and multiplicity need to be defined in most cases.
 * <p>
 * <h2>Naming</h2>
 * <p>
 * Since a descriptor is defined with respect to a ComplexAttribute you can
 * use the provided name to lookup a descriptor at runtime. The name however
 * belongs to a namespace that may have no relationship with the containing
 * ComplexType, so please be sure 
 * </p>
 * 
 * <h2>Note on Use of Client Properties</h2>
 * </p>
 * Client properties are used to allow additional "tempoary" metadata to
 * be associated with attributes descriptors in order to facilitiate
 * procesing services. These services traditionally setting up "shadow"
 * structures such as a HashMap. Allowing non persisted bread crumbs is
 * considered preferable.
 * </p>
 * @author Jody Garnett, Refractions Research
 */
public interface PropertyDescriptor {

	/**
	 * Allows the association of process specific information
	 * (such as XML prefix) with an attribute descriptor.
	 * 
	 * @param key Object used to allow String and Enum keys
	 * @param value Associated with key
	 */
	void putClientProperty( Object key, Object value );
	
	/**
	 * Retrive associated process specific information
	 * (such as XML prefix).
	 * 
	 * @param key Object used to allow String and Enum keys
	 */	
	Object getClientProperty( Object key );

	/**
	 * Indicates Name of defined attribute in a ComplexType, this method may 
	 * never return a null value.
	 * <p>
	 * The name is actually defined by the sub type, this is derrived quantity.
	 * </p>
	 */
	Name name();
}