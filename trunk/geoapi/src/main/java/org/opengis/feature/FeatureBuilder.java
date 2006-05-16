package org.opengis.feature;

import java.util.Collection;

import org.opengis.feature.type.FeatureType;
import org.opengis.referencing.crs.CoordinateReferenceSystem;

public interface FeatureBuilder<C extends Collection<Attribute>,T extends FeatureType<C>, A extends Feature<C,T>>
	extends ComplexAttributeBuilder<C,T,A> {

    /**
     * Sets the coordinate reference system of the built feature.
     */
	void setCRS(CoordinateReferenceSystem crs);
    
    /**
     * @return The coordinate reference system of the feature, or null 
     * if not set.
     */
    CoordinateReferenceSystem getCRS();
}
