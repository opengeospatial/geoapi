package org.opengis.feature;

import java.util.Collection;
import java.util.List;
import java.util.Set;

import org.opengis.feature.simple.SimpleFeature;
import org.opengis.feature.simple.SimpleFeatureCollection;
import org.opengis.feature.simple.SimpleFeatureType;
import org.opengis.feature.type.AttributeDescriptor;
import org.opengis.feature.type.AttributeType;
import org.opengis.feature.type.ComplexType;
import org.opengis.feature.type.FeatureCollectionType;
import org.opengis.feature.type.FeatureType;
import org.opengis.feature.type.TypeName;

/**
 * Provides high level convenience methods used to build attribute objects.
 * <br>
 * <p>
 * This when possible this interface should be usd to construct types as 
 * opposed to {@link org.opengis.feature.AttributeFactory}
 * </p>
 * <p>
 * This interface defines based injection for the following:
 * <ul>
 * <li>
 * </ul>
 * It is understood that constructor based injection should be supported
 * by implementations.
 * 
 * </p>
 *
 * @author Justin Deoliveira, The Open Planning Project, jdeolive@openplans.org
 *
 */
public interface AttributeBuilder {
	//
	// Injection
	//
	// Used to inject dependencies we need during construction time.
	//
	/**
	 * Returns the underlying attribute factory.
	 */
	AttributeFactory getAttributeFactory();
	
	/**
	 * Sets the underlying attribute factory.
	 */
	void setAttributeFactory(AttributeFactory attributeFactory);
	
	//
	// State
	//
	// These methods capture state used in helping produce
	// objects.
	/**
	 * This namespace will be used when constructing AttributeName
	 */
	void setNamespaceURI(String uri );
	
	/**
	 * This namespace will be used when constructing AttributeName
	 * 
	 * @return namespace will be used when constructing AttributeName
	 */
	String getNamespaceURI();
	
	/**
	 * The follwing bindings are supported "out of the box":
	 * <ul>
	 * <li>Boolean to BooleanAttribute
	 * <li>Number to NumericAttribute
	 * <li>
	 * <li>
	 * These interfaces may be found in org.opengis.feature.simple.
	 * </ul>
	 * @param binding
	 * @return
	 */
	<B> AttributeType<B> getType( Class<B> binding );
	
	/**
	 * Used to define additional class to AttributeType bindings.
	 * <p>
	 * When proper Namespaces constructs are defined we can do a
	 * bulk load here. 
	 * </p>
	 * @param <B>
	 * @param binding
	 * @param type
	 * @return
	 */
	<B> AttributeType<B> setType( Class<B> binding, AttributeType<B> type );
	
	// attribute methods
	
	/**
	 * Produce an attribute name using namespaceURI + name.
	 * 
	 * @param name
	 * @return AttributeName based on current builder state.
	 */
	AttributeName name( String name );
	
	/**
	 * Produce a type name using namespaceURI + name.
	 * 
	 * @param name
	 * @return AttributeName based on current builder state.
	 */	
	TypeName type( String name );
	
	/**
	 * Creates an attribute from an object value and a name.
     * <p>
	 * The following are actually created:
	 * <ul>
	 * <li>Attribute bound to object.getClass()
	 * <li>AttributeDescriptor baesd on name and type methods
	 * <li>AttributeType bound against object.getClass
	 * </ul>
	 * 
	 * @param name Used with name method to produce AttributeName
	 * @param object The value of the attribute.
	 */
	Attribute attribute(String name, Object object);
	
	/**
	 * Creates an attribute from an object value , name, and id.
     * <p>
	 * The following are actually created:
	 * <ul>
	 * <li>Attribute bound to object.getClass()
	 * <li>AttributeDescriptor baesd on name and type methods
	 * <li>AttributeType bound against object.getClass
	 * </ul>
	 * 
	 * @param name The name of the attribute.
	 * @param object the id of this object, must set during construction!
	 * @param id The id of the attribute.
	 */
	Attribute attribute(String name, Object object, String id);
	
	/**
	 * Creates an attribute from an object value and a name.
	 * 
	 * @param name The name of the attribute.
	 * @param object The value of the attribute.
	 */
	Attribute attribute(AttributeName name, Object object);
	
	/**
	 * Creates an attribute from an object value , name, and id.
	 * 
	 * @param name The name of the attribute.
	 * @param object The value of the attribute.
	 * @param id The id of the attribute.
	 */
	Attribute attribute(AttributeName name, Object object, String id);
	
	/**
	 * Creates an attribute froma a type, value, and id.
	 * 
	 * @param type The type of the attribute.
	 * @param object The value of the attribute.
	 * @param id The id of the attribute.
	 */
	Attribute attribute(AttributeType type, Object object, String id);
	
	/**
	 * Creates an attribute from a name, type, value, and id.
	 * 
	 * @param name THe name of the attribute.
	 * @param type The type of the attribute.
	 * @param object The value of the attribute.
	 * @param id The id of the attribute.
	 */
	Attribute attribute(AttributeName name, AttributeType type, Object object, String id);
	
	/**
	 * Creates an attribute from a descriptor, value, and id.
	 * 
	 * @param descriptor The attribute descriptor.
	 * @param object The value.
	 * @param id The id.
	 */
	Attribute attribute(AttributeDescriptor descriptor, Object object, String id);
	
	/**
	 * Creates a duplicate attribute based on another.
	 * 
	 * @param other The prototype attribute.
	 * 
	 */
	Attribute attribute(Attribute other);
	
	// complex attribute methods
	
	/**
	 * Creates a complex attribute from name, type and values.
	 */
	ComplexAttribute complexAttribute(String name, ComplexType type, Collection values);
	
	/**
	 * Creates a complex attribute from name, type and values.
	 */
	ComplexAttribute complexAttribute(AttributeName name, ComplexType type, Collection values);
	
	/**
	 * Creates a complex attribute from name, type, values, and id.
	 */
	ComplexAttribute complexAttribute(String name, ComplexType type, Collection values, String id);
	
	/**
	 * Creates a complex attribute from name, type, values, and id.
	 */
	ComplexAttribute complexAttribute(AttributeName name, ComplexType type, Collection values, String id);
	
	// feature methods
	/**
	 * Createsa feature.
	 * 
	 * @param type The type of the feature
	 * @param values. 
	 */
	Feature feature(FeatureType type, Collection values, String id);
	
	// simple feature methods
	/**
	 * Creates a simple feature.
	 * 
	 * @param type The type of the feature.
	 * @param values The values of feature.
	 */
	SimpleFeature simpleFeature(SimpleFeatureType type, Object[] values);
	
	/**
	 * Creates a simple feature.
	 * 
	 * @param type The type of the feature.
	 * @param values The values of feature.
	 */
	SimpleFeature simpleFeature(SimpleFeatureType type, List values);
	
	/**
	 * Creates a simple feature.
	 * 
	 * @param type The type of the feature.
	 * @param values The values of feature.
	 * @param id The id of the feature.
	 */
	SimpleFeature simpleFeature(SimpleFeatureType type, Object[] values, String id);
	
	/**
	 * Creates a simple feature.
	 * 
	 * @param type The type of the feature.
	 * @param values The values of feature.
	 * @param id The id of the feature.
	 */
	SimpleFeature simpleFeature(SimpleFeatureType type, List values, String id);
	
	/**
	 * Creates a duplicate simple feature based on another.
	 * 
	 * @param other The prototype feature.
	 * 
	 */
	SimpleFeature simpleFeature(SimpleFeature other);
	
	// feature collection methods
	
	/**
	 * Creaes a feature collection containing a set of allowable member types.
	 * 
	 * @param memebers The set containng types in which members of the collection
	 * may implement.
	 * 
	 */
	FeatureCollection featureCollection(Set<FeatureType> members);
	
	/**
	 * Creates a feature collection.
	 * 
	 * @param type The type of the feature collection.
	 */
	FeatureCollection featureCollection(FeatureCollectionType type);
	
	/**
	 * Creates a simple feature collection from a member type.
	 * 
	 * @param memberType The type in which members of the collection must 
	 * implement.
	 * 
	 */
	SimpleFeatureCollection simpleFeatureCollection(FeatureType memberType);
}
