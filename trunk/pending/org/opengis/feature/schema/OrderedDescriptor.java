/**
 * 
 */
package org.opengis.feature.schema;

import java.util.List;

public interface OrderedDescriptor extends Descriptor{
	/**
	 * TODO: make a clear statement of this method's pourpose
	 * <p>
	 * Note that the same way as with {@linkplain ChoiceDescriptor},
	 * the returned list cannot contain {@linkplain AllDescriptor}s
	 * </p>
	 * @return
	 */
	//public <T extends Descriptor>List<T> sequence();
	public List<? extends Descriptor>sequence();
}