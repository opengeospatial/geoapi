package org.opengis.feature.type;

import org.opengis.feature.AttributeName;

/**
 * Indicating a named entry for a perscribed AttributeType.
 * <p>
 * This class carries the ComplexType specific information required
 * for use a contained attribute. Name, type and multiplicity are defined.
 * If in the future the nature of the containment relationship needs further
 * definition you may expected additional information to be gathered here.
 * </p>
 * Proposal:<br>
 * It is tempting to allow additional "tempoary" metadata to be associated
 * with attributes descriptors in order to facilitiate procesing services.
 * These services traditionally setting up "shadow" structures such
 * as a HashMap. Allowing non persisted bread crumbs is considered
 * preferable.
 * <ul>
 * <li>putClientProperty(String key, Object value );
 * <li>getClientProperty(String key);
 * </ul>
 * </p>
 * @author Jody Garnett, Refractions Research
 */
public interface AttributeDescriptor<T extends AttributeType> {

	/**
	 * Allows the association of process specific information
	 * (such as XML prefix) with an attribute descriptor.
	 * 
	 * @param key Object used to allow String and Enum keys
	 * @param value Associated with key
	 */
	public void putClientProperty( Object key, Object value );
	
	/**
	 * Retrive associated process specific information
	 * (such as XML prefix).
	 * 
	 * @param key Object used to allow String and Enum keys
	 */	
	public Object getClientProperty( Object key );
	
	/** Captures cadinality */
	public int getMinOccurs();
	
	/** Captures cadinality */
	public int getMaxOccurs();
	
	/**
	 * True attribute is allowed to be null.
	 * 
	 * @return true if value may be null, false if value my be present
	 */
	public boolean isNillable();
	
	/** Indicates the type of this attribute */
	T getType();

	/**
	 * Indicates Name of defined attribute in a ComplexType, this method may 
	 * never return a null value.
	 */
	AttributeName getName();
	
}