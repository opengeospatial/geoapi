package org.opengis.feature.simple;

import java.util.Collection;
import java.util.List;
import java.util.Set;

import org.opengis.feature.type.AssociationDescriptor;
import org.opengis.feature.type.AttributeDescriptor;
import org.opengis.feature.type.AttributeType;
//import org.opengis.feature.type.FeatureType;
//import org.opengis.feature.type.FeatureCollectionType;
import org.opengis.feature.type.GeometryType;
import org.opengis.feature.type.Name;
import org.opengis.feature.type.StructuralDescriptor;
import org.opengis.feature.type.TypeFactory;
import org.opengis.feature.type.TypeName;
import org.opengis.filter.Filter;
import org.opengis.referencing.crs.CoordinateReferenceSystem;
import org.opengis.util.InternationalString;


/**
 * A TypeFactory that creates SimpleFeatureType and SimpleFeatureCollectionType.
 * <p>
 * This interface serves as a "marker" that the Factory produces Simple types.
 * While we have provided two methods here they are <b>not</b> strictkly
 * required as createFeatureType and createFeatureCollectionType will
 * also produce simple types.
 * <p>
 * @author Jody Garnett
 */
public interface SimpleTypeFactory {
    /**
     * Create a SimpleFeatureType describing a Feature containing only directly
     * bound attributes with no multiplicity.
     *
     * @param name TypeName of type to be created
     * @param types AttributeTypes of contents, in order specified
     * @param defaultGeometry Member of types to be used as the defaultGeometry
     * @param crs CoordinateReferenceSystem for the contents of this feature
     * @param restrictions Filters used to check the contents of this feature
     * @param description description of this feature
     * @return created SimpleFeatureType
     */
    SimpleFeatureType createSimpleFeatureType(Name name,
            List<AttributeType> types, AttributeDescriptor defaultGeometry,
            CoordinateReferenceSystem crs, Set<Filter> restrictions,
            InternationalString description);

    /**
     * Create a SimpleFeatureType describing a Feature containing only directly
     * bound attributes with no multiplicity.
     *
     * @param name TypeName of collection type to be created
     * @param member FeatureType of collection members
     * @param description description of this feature
     * @return created SimpleFeatureCollectionType
     */
    SimpleFeatureCollectionType createSimpleFeatureCollectionType(
            Name name, SimpleFeatureType member,
            InternationalString description);
}
