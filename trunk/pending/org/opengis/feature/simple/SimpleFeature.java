package org.opengis.feature.simple;

import org.opengis.feature.Feature;

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
 * We considered providing a helper method based on QName to
 * this class or directly to Feature. There is no significant
 * advantage over direct use of AttribtueType.
 * </p>
 * @author jgarnett
 */
public interface SimpleFeature extends Feature {

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
	 * Retrive value by attribute name.
	 * <P>
	 * Method considered and withdrawn, as it is not of
	 * interest to the casual shapefile user.
	 * </p>
	 * @param name
	 * @return Attribute Value associated with name
	 *
	Object get(QName qname);
	 */
	
	/**
	 * Access attribute by "index" indicated by SimpleFeatureType.
	 * 
	 * @param index
	 * @return
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
	 * Modify attribute with "qname" indicated by SimpleFeatureType.
	 * <p>
	 * Method considered and withdrawn, as it is not of
	 * interest to the casual shapefile user.
	 * </p>
	 * @param qname
	 * @param value
	 *
	void set( QName qname, Object value );
     */
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
	 * Repressed, for simple content SimpleFeatureType.getNumberOfAttributes() will
	 * be sufficient.
	 * </p>
	 * @return number of available attribtues
	 *
	int getNumberOfAttributes();
	 */
}
