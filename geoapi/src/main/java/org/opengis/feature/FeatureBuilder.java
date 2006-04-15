package org.opengis.feature;

import java.util.Collection;

import org.opengis.feature.type.FeatureType;

public interface FeatureBuilder<C extends Collection<Attribute>,T extends FeatureType<C>, A extends Feature<C,T>>
	extends ComplexAttributeBuilder<C,T,A> {

	
}
