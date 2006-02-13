package org.opengis.feature.schema;

import java.util.List;
import java.util.Set;

import org.opengis.feature.type.AttributeType;

public interface DescriptorFactory {
	
	/**
	 * Creates an {@linkplain AttributeDescriptor} for <code>type</code>
	 * with given multiplicity
	 * 
	 * @param type type for AttributeDescriptor
	 * @param min lower multiplicity bound
	 * @param max upper multiplocity bound
	 * @return a descriptor of a given AttributeType.
	 */
	public AttributeDescriptor node(AttributeType type, int min, int max);

	/**
	 * 
	 * @param all
	 * @param min
	 * @param max
	 * @return
	 */
	public AllDescriptor all(Set<AttributeDescriptor> all, int min, int max);

	/**
	 * 
	 * @param sequence
	 * @param min
	 * @param max
	 * @return
	 */
	public OrderedDescriptor ordered(List<Descriptor> sequence, int min, int max);

	/**
	 * 
	 * @param options
	 * @param min
	 * @param max
	 * @return
	 */
	public ChoiceDescriptor choice(Set<? extends Descriptor> options, int min,
			int max);
}
