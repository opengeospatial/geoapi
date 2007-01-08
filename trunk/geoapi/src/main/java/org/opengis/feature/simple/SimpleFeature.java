package org.opengis.feature.simple;

import java.util.List;

import org.opengis.feature.Attribute;
import org.opengis.feature.Feature;
import org.opengis.feature.type.AttributeType;

/**
 * Feature interface customized for Simple content.
 * <p>
 * This class has a different target audience then that
 * of the rest of the Feature Model - the target user
 * is a casual user of Shapefiles, if they can switch
 * over to database tables without noticing so much
 * the better.
 * </p>
 * <p>
 * Note this is extention of Feature, and thus it is complete
 * with respect to the needs of XPath and GML generation. This
 * represents a restriction as indicated by SimpleFeatureType,
 * and this restriction has allowed us to unambigously create
 * convience methods based in name and index.
 * </p>
 * <p>
 * We considered providing a helper method based on GenericName to
 * this class or directly to Feature. There is no significant
 * advantage over direct use of AttribtueType.
 * </p>
 * @author Jody Garnett, Refractions Research
 */
public interface SimpleFeature extends //Sequence<List<Attribute>,SimpleFeatureType>, 
	Feature<Attribute,List<Attribute>,SimpleFeatureType> {
	

	/**
	 * List of attributes is in the same order as that defined
	 * by SimpleFeatureType.
	 */
	//List<Attribute> getAttributes();

    /**
     * AttributeTypes in the order defined by SimpleFeatureType.
     * <p>
     * This method is not part of the data model and does not follow
     * Java Bean naming conventions.
     * </p>
     * @return List of AttribtueTypes in order defined by SimpleFeatureType
     */
    List<AttributeType> types();
    
    /**
	 * Value view of attribtue types, in a manner similar Map.values().
	 * <p>
	 * The content avalable through types() an values() are considered a view of
	 * attribtues(). Order is maintained, and removing content will result in a
	 * modification to all three lists. in a manner simialr to Map.keysSet() and
	 * Map.values().
	 * <p>
	 * Collections naming conventions are used to indicate this is a view into
	 * our data model.
	 */
	 List<Object> values();
    
	/**
	 * Restrictued to SimpleFeatureType
	 * <p>
	 * This restriction enabled client code to confidently
	 * assume that each attribute occurs in the perscribed order
	 * and that no super types are used.
	 * </p>
	 */
	SimpleFeatureType getType();

	/**
	 * Retrive value by attribute name.
	 * @param name
	 * @return Attribute Value associated with name
	 */
	Object get(String name);
	
	/**
	 * Access attribute by "index" indicated by SimpleFeatureType.
	 * 
	 * @param index
	 */
	Object get( int index );

	/**
	 * Modify attribute with "name" indicated by SimpleFeatureType.
	 * 
	 * @param name
	 * @param value
	 */
	void set( String name, Object value );

	/**
	 * Modify attribute at the "index" indicated by SimpleFeatureType.
	 * 
	 * @param index
	 * @param value
	 */
	void set( int index, Object value);
	
	/**
	 * Call opperation with provided parameters.
	 * 
	 * @param name Name of opperation
	 * @param parameters Should be in agreement with OperationType
	 * @return Result of operation, may be null if operation does not produce a result
	 */
	Object operation( String name, Object parameters );
	
	/**
	 * Number of attributes in SimpleFeatureType.
	 * <p>
	 * This is identical to <code>types().size()</code>
	 * </p>
	 * @return number of available attribtues
	 */
	int getNumberOfAttributes();

    /**
     * Returns the default geometry for the simple feature.
     * <p>
     * This method is convenience for getDefaultGeometry().get();
     * </p>
     * @return The default geometry, or null if none exists. 
     * 
     */
    Object defaultGeometry();
    
    /**
     * Sets the default geometry for the simple feature.
     * <p>
     * This method is convenience for getDefaultGeometry().set(geometry);
     * </p>
     * @param geometry The new defautl geometry.
     */
    void defaultGeometry(Object geometry);
}
