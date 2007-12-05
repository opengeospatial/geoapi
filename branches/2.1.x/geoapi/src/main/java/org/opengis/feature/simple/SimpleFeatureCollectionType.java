package org.opengis.feature.simple;

import java.util.List;
import java.util.Set;

import org.opengis.feature.Attribute;
import org.opengis.feature.type.AssociationDescriptor;
import org.opengis.feature.type.AssociationType;
import org.opengis.feature.type.FeatureCollectionType;

/**
 * Extension of FeatureCollectionType for a simple collection.
 * <p>
 * Maintains the following properties:
 * <ul>
 *   <li>Contains a single member feature type.</li>
 *   <li>Contains no attributes of its own.</li>
 *   <li>Has no super type.</li>
 * </ul>
 * <br>
 * With code the amounts to the following assertions being held:
 * <pre>
 * getMemberTypes().size() == 1
 * getType().getAttribute().isEmpty()
 * getSuper() == null
 * </pre>
 * </p>
 *
 * @author Justin Deoliveira (The Open Planning Project)
 */
public interface SimpleFeatureCollectionType extends FeatureCollectionType,  SimpleFeatureType
{
    /**
     * Returns the single feature of allowed members of the collection.
     */
    SimpleFeatureType getMemberType();

    /**
     * Returned set describing a single allowed SimpleFeatureType.
     * <p>
     * The SimpleFeatureType used by this association descriptor is
     * available via getMemberType();
     * @return Set of AssociationDescriptor, indicating the SimpleFeatureType
     */
    Set<AssociationDescriptor> getMembers();

    /**
     * Singleton set, use getMemberType()
     * @return Collections.singleton( getMemberType() )
     */
    Set<SimpleFeature> getMemberTypes();
}
