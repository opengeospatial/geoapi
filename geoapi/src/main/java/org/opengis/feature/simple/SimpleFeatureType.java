package org.opengis.feature.simple;

import java.util.List;

import org.opengis.feature.Attribute;
import org.opengis.feature.type.AttributeType;
import org.opengis.feature.type.FeatureType;
import org.opengis.feature.type.GeometryType;
import org.opengis.feature.type.Name;
import org.opengis.feature.xml.SequenceType;

/**
 * Defines a simple feature model of attribute in a perscribed order.
 * <p>
 * This interface also defines several helper methods that only
 * make sense given the above constratins.
 * </p>
 *  
 * @author Jody Garnett, Refractions Research
 */
public interface SimpleFeatureType extends SequenceType<List<Attribute>>, 
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
	
	/** Number of available attributes */
	int getNumberOfAttribtues();
	
}
