package org.opengis.feature.simple;

import java.util.List;
import java.util.Set;

import org.opengis.feature.type.AssociationDescriptor;
import org.opengis.feature.type.AttributeDescriptor;
import org.opengis.feature.type.AttributeType;
import org.opengis.feature.type.GeometryType;
import org.opengis.feature.type.TypeFactory;
import org.opengis.feature.type.TypeName;
import org.opengis.filter.Filter;
import org.opengis.referencing.crs.CoordinateReferenceSystem;
import org.opengis.util.InternationalString;

/**
 * Provides two new factory methods that allow for direct creation of
 * SimpleFeatureType and SimpleFeatureCollectionType.
 * 
 * @author Jody
 */
public interface SimpleTypeFactory extends TypeFactory {
	/**
	 * Create a SimpleFeatureType describing a Feature containing only
	 * directly bound attributes with no multiplicity.
	 * <p>
	 * While no 
	 */
	SimpleFeatureType createSimpleFeatureType(
		TypeName name,List<AttributeDescriptor> schema,
		AttributeDescriptor<GeometryType> defaultGeometry, CoordinateReferenceSystem crs, 
		boolean isAbstract, Set<Filter> restrictions, AttributeType superType,
		InternationalString description
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
