/**
 * 
 */
package org.opengis.feature.schema;

import java.util.Set;

public interface ChoiceDescriptor extends Descriptor {
	/**
	 * Returns the set of content descriptors that an AttributeType with this
	 * content must choice between.
	 * <p>
	 * Allowable content of the returned Set are any number of
	 * {@linkplain AttributeDescriptor}, {@linkplain ChoiceDescriptor} and
	 * {@linkplain OrderedDescriptor}. In other words, it cannot contain
	 * {@linkplain AllDescriptor}s
	 * </p>
	 * 
	 * @return
	 */
	public Set<Descriptor> options();

}