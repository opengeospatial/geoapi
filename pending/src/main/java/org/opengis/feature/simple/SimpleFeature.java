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
public interface SimpleFeature extends Feature {
	
	/**
	 * List of attributes is in the same order as that defined
	 * by SimpleFeatureType.
	 */
    public List<Attribute> getAttributes();
    
    /**
     * AttributeTypes in the order defined by SimpleFeatureType.
     * <p>
     * This method is not part of the data model and does not follow
     * Java Bean naming conventions.
     * </p>
     * @return List of AttribtueTypes in order defined by SimpleFeatureType
     */
    public List<AttributeType> types();
    
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
	 * Number of attributes in SimpleFeatureType.
	 * <p>
	 * This is identical to <code>types().size()</code>
	 * </p>
	 * @return number of available attribtues
	 */
	int getNumberOfAttributes();
}
