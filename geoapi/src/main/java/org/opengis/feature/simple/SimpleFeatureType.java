package org.opengis.feature.simple;

import java.util.List;

import org.opengis.feature.Attribute;
import org.opengis.feature.type.AttributeType;
import org.opengis.feature.type.FeatureType;
import org.opengis.feature.type.GeometryType;
import org.opengis.feature.type.Name;

/**
 * Defines a simple feature model of attribute in a perscribed order.
 * <p>
 * This interface also defines several helper methods that only
 * make sense given the above constratins.
 * </p>
 * <p>
 * For reference these are the limitations of a SimpleFeatureType:
 * <ol>
 * <li>Attributes - properties limited to attributes only!
 * <li>Attributes - List collection - ie. order of attributes matters
 * <li>Attribute lookup by index
 * <li>Attribute lookup by name (ie String)
 * <li>getSuper() is null, required for point 3
 * </ol>
 * Name conflict is not permitted (in order to allow lookup by a simple String).
 * </p>
 * @author Jody Garnett, Refractions Research
 */
public interface SimpleFeatureType extends 
	FeatureType<Attribute,List<Attribute>> {
	
	/**
	 * Must be <code>null</code> for truely simple content.
	 * @return <code>null</code>, as no super types are allowed
	 */
///	SimpleFeatureType getSuper();
	
	
	/**
	 * Types are returned in the perscribed index order.
	 * @return Types in prescribed order
	 */
	List<AttributeType> types();
	
	/**
	 * @return the default geometry type contained by the feature type, or 
	 * null if none such exists.
	 */
	GeometryType defaultGeometry();
	
	/** List of named attributes in perscribed order */
///	List<AttributeDescriptor> getAttributes();
	
	/**
	 * Retrive attributeType by qualified name
	 */
	AttributeType get( Name name );
	
	/**
	 * Retrive attributeType by name.
	 * <p>
	 * If you do run into a conflict (the same local part used in two 
	 * namespace) please use a complete Qualified name to disambiguate.
	 * </p>
	 */
	AttributeType get( String name );
	
	/** AttribtueType indicated by index */
	AttributeType get( int index );
	
	/**
	 * The index of the AttributeType with the provided name.
	 * @param name
	 * @return index of named attribute, or -1 if not found.
	 */
	int indexOf( String name );
	
	/** Number of available attributes */
	int getNumberOfAttribtues();	
}