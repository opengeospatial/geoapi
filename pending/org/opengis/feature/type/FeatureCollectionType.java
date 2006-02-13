package org.opengis.feature.type;

import org.opengis.feature.schema.Descriptor;


public interface FeatureCollectionType extends FeatureType {	
	/**
	 * FeatureTypes allowable as members of this collection.
	 * <p>
	 * Usually:
	 * <ul>
	 * <li>AttributeDescriptor: 0..*, of FeatureType
	 * <li>ChoiceDescriptor: 0..*, of the above
	 * </ul>
	 */
	Descriptor getMemberDescriptor();
}