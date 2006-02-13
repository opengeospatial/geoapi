package org.opengis.feature.type;


/**
 * Describes a Feature, this is a step in the chain towards FeatureCollectionType.
 * <p>
 * This class provides no additional modeling power beyond ComplexType, it does however
 * come with additional restrictions that may be described at the Java level.
 * </p>
 * @author Jody Garnett
 *
 */
public interface FeatureType<T> extends ComplexType<T> {
	
	/** I am under the impression this should return Boolean.FALSE? */
	public Boolean isNillable();
	
	/** Must always return true */
	public boolean isIdentified();
	
	/**
	 * Indicates which AttributeType is to be considered the default
	 * geometry.
	 * @return AttributeType used to locate the default Geometry
	 */
	public GeometryType getDefaultGeometry();
	
	/**
	 * Super may be a normal ComplexType.
	 */
	public ComplexType<? super T> getSuper();
}