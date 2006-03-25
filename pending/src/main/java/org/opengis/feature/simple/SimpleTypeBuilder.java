package org.opengis.feature.simple;

import java.util.List;

import org.opengis.feature.Attribute;
import org.opengis.feature.type.FeatureTypeBuilder;

/**
 * A builder for "simple" attribute types.
 * 
 * @author Justin Deoliveira, The Open Planning Project, jdeolive@openplans.org
 *
 * @param <B> The binding for the returned type. 
 */
public interface SimpleTypeBuilder<L extends List<Attribute>, T extends SimpleFeatureType<L>>
	extends FeatureTypeBuilder<L,T> {
	
}