/**
 * 
 */
package org.opengis.feature.schema;

import java.util.Set;

public interface AllDescriptor extends Descriptor {
	/**
	 * Returns the set of allowed content attribute descriptors.
	 * <p>
	 * At the difference of {@linkplain ChoiceDescriptor} and
	 * {@linkplain OrderedDescriptor}, the returned collection can only contain
	 * {@linkplain AttributeDescriptor}. That is, it cannot contain choice or
	 * ordered descriptors. This limitation is borrowed from the <a
	 * href="http://www.w3.org/TR/xmlschema-1/#element-all">XML Schema</a>
	 * specification, since due to the unordered nature of the content described
	 * by this descriptor, it is vital to avoid ambiguities when evaluating an
	 * Attribute's contents
	 * </p>
	 * 
	 * @return the set of allowed content attributes
	 */
	public Set<AttributeDescriptor> all();
}