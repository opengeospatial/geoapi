package org.opengis.feature.type;

import org.opengis.referencing.crs.CoordinateReferenceSystem;

/**
 * The type of a Feature.
 * <p>
 * Beyond a complex type, a feature defines some additional information:
 * <ul>
 *   <li>The default geometric attribute
 *   <li>The coordinate referencing system (derived from the default geometry)
 * </ul>
 * </p>
 * 
 * @author Jody Garnett, Refractions Research
 * @author Justin Deoliveira, The Open Planning Project
 */
public interface FeatureType extends ComplexType {
			
    /**
     * The default geometric attribute of the feature.
     * <p>
     * This method returns <code>null</code> in the case where no such attribute
     * exists.
     * </p>
     * @return The descriptor of the default geometry attribute, or <code>null</code>.
     */
    AttributeDescriptor getDefaultGeometryProperty();
	
    /**
     * The coordinate reference system of the feature.
     * <p>
     * This value is derived from the default geometry attribute:
     * <pre>
     *   ((GeometryType)getDefaultGeometry().getType()).getCRS();
     * </pre>
     * </p>
     * <p>
     * This method will return <code>null</code> in the case where no default
     * geometric attribute is defined.
     * </p>
     * @return The coordinate referencing system, or <code>null</code>.
     */
    CoordinateReferenceSystem getCRS();
}