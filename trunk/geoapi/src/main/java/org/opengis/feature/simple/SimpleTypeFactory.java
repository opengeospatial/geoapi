package org.opengis.feature.simple;

import java.util.List;
import java.util.Set;

import org.opengis.feature.type.AssociationDescriptor;
import org.opengis.feature.type.AttributeDescriptor;
import org.opengis.feature.type.GeometryType;
import org.opengis.feature.type.TypeFactory;
import org.opengis.feature.type.TypeName;
import org.opengis.filter.Filter;
import org.opengis.referencing.crs.CoordinateReferenceSystem;
import org.opengis.util.InternationalString;

/**
 * Provides two new factory methods that allow for direct creation of
 * SimpleFeatureType and SimpleFeatureCollectionType. These methods
 * are *not* stricktly required, they only serve as an optimization
 * to allow more direct creation.
 * <p>
 * Please note that this is an follows the Factory "GOF pattern" and we
 * are creating not one kind of content, but a collection of content designed
 * to work together.
 * </p>
 * To make using these factories more tracktable we recommend
 * the construction of a builder (an example exists in the geotools library).
 * <p>
 * @author Jody Garnett
 */
public interface SimpleTypeFactory extends TypeFactory {
	/**
	 * Create a SimpleFeatureType describing a Feature containing only
	 * directly bound attributes with no multiplicity.
	 * <p>
	 * While no 
	 */
	SimpleFeatureType createSimpleFeatureType(
		TypeName name, List<AttributeDescriptor> schema,
		AttributeDescriptor<GeometryType> defaultGeometry, CoordinateReferenceSystem crs, 
		Set<Filter> restrictions, InternationalString description
	);
	
	/**
	 * Create a SimpleFeatureType describing a Feature containing only
	 * directly bound attributes with no multiplicity.
	 */
	SimpleFeatureCollectionType createSimpleFeatureCollectionType(
		TypeName name, AssociationDescriptor member, Set<Filter> restrictions, 
		InternationalString description
	);
}
