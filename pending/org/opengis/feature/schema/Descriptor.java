package org.opengis.feature.schema;



/**
 * Descriptor captures the order of types withing a ComplexType.
 * <p>
 * Order is defined by a tree structure of the following:
 * <ul>
 * <li>AttributeDescriptor - indicating a named AttributeType
 * <li>ChoiceDescriptor - indicating a choice between options
 * <lI>OrderedSchema - indicating a perscribed order is required
 * <li>AllDescriptor - indicating an unordered set
 * </ul>
 * These form the substance of aggregation in the feature model, as a convience 
 * a further subclass of OrderedDescriptor is available to denote simple content.
 * </p>
 * 
 * @author Jody Garnett
 *
 */
public interface Descriptor{
	/** Captures cadinality */
	public int getMinOccurs();
	
	/** Captures cadinality */
	public int getMaxOccurs();

}
