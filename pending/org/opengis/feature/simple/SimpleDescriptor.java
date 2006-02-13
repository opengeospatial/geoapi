package org.opengis.feature.simple;

import java.util.List;

import org.opengis.feature.schema.AttributeDescriptor;
import org.opengis.feature.schema.OrderedDescriptor;

public interface SimpleDescriptor extends OrderedDescriptor {
	
	/**
	 * Provides a List<AttributeDescriptor> where each attribute Descriptor
	 * has multiplicity 1:1.
	 * <p>
	 * This is used to programatically indicate simple content.
	 */
	public List<AttributeDescriptor> sequence();
}
