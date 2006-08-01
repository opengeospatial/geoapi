package org.opengis.feature.type;

import java.util.Collection;

import org.opengis.feature.Property;

/**
 * Represents an AttirbuteType with internal strucutre composed of properties.
 * <p>
 * There are two kinds of properties that may be used to compose a complex type:
 * <ul>
 * <li>attributes - describes data contained in this complex type 
 * <li>associations - captures relationships with other types
 * </ul>
 * When choosing between attribute and associations - just answer this question. Is the
 * value removed when you delete? If it is removed the value is an attribute, if it 
 * is an attribute, if the value would still be around you should model it with an association.
 * </p>
 * @author Jody Garnett
 *
 */
public interface ComplexType<E extends Property,C extends Collection<E>> extends AttributeType<C> {
	
	/**
	 * Indicates ability of XPath to notice this attribute.
	 * <p>
	 * This facility is used to "hide" an attribute from XPath searches, while the compelx contents will still
	 * be navigated no additional nesting will be considered. It will be as if the content were "folded" inline
	 * resulting in a flatter nesting structure.
	 * </p>
	 * <p>
	 * Construct described using Java Interfaces:<pre><code>
	 * interface TestSample {
	 *     String name;
	 *     List<Measurement> measurement;
	 * }
	 * interface Measurement {
	 *     long timestamp;
	 *     Point point;
	 *     long reading;
	 * }
	 * </code></pre>
	 * The above is can hold the following information:<pre><code>
	 * [ name="survey1",
	 *   measurements=(
	 *       [timestamp=3,point=(2,3), reading=4200],
	 *       [timestamp=9,point=(2,4), reading=445600],
	 *   )
	 * ]
	 * </code></pre>
	 * Out of the box this is represented to XPath as the following tree:<pre><code>
	 * root/name: survey1
	 * root/measurement[0]/timestamp:3
	 * root/measurement[0]/point: (2,3)
	 * root/measurement[0]/reading: 4200     
	 * root/measurement[1]/timestamp:9
	 * root/measurement[2]/point: (2,4)
	 * root/measurement[3]/reading: 445600     
	 * </code></pre>
	 * 
	 * By inlining Measurement we can achive the following:<pre><code>
	 * root/name: survey1
	 * root/timestamp[0]:3
	 * root/point[0]: (2,3)
	 * root/reading[0]: 4200     
	 * root/timestamp[1]:9
	 * root/point[1]: (2,4)
	 * root/reading[1] 445600     
	 * </code></pre>
	 * 
	 * @return true if  attribute is to be considered transparent by XPath queries
	 */
	public boolean isInline();
	
	/**
	 * Java class bound to this complex type.
	 * <p>
	 * Note this method will return null if the complex type does not
	 * bind to a Java object.
	 * </p>
	 * @return Java binding, or null if not applicable
	 */
	Class<C> getBinding();

	/**
	 * Super is restricted to other ComplexTypes.
	 * <p>
	 * Note: If generics get in the way of reuse in this manner
	 * we will have to get rid of them. I cannot thing of a real
	 * world example where this is the case at the moment.
	 * </p>
	 * <p>
	 * Note: Although you may be tempted to make this
	 * ComplexType<List<Attribtue>> this would be inaccurate, a
	 * list of Attribtues is the data model, representing
	 * the representation of content to the feature modeling
	 * system. The real content may be expressed in terms a of
	 * Plain-old Java Object (POJO) as per the domain model
	 * being represented.
	 * </p>
	 * <p>
	 * A good sanity check is this: only use complex types
	 * when you need to make content accessable as attributes
	 * (perhaps you want to access the content via XPath for visialization?).
	 * </p>
	 * <p>
	 * Other ideas for making things easy:
	 * <ul>
	 * <li>An implementation of ComplexAttribute based on Java Bean
	 *     reflection.
	 * <li>Direct use of Java Beans in xPath expressions (see JXPath)
	 * </ul>
	 * In short the less work you as the end-user of this API have
	 * to put into use the better.
	 * </p>
	 */
	///ComplexType getSuper(); // TODO Restore this
	
	/**
	 * Returns the only the strucutral properties (Attributes and Associations) used to define
	 * this type.
	 * <p>
	 * We are not including the OperationDescriptors in this list as they do not
	 * vary on a instance by instance basis. The difference between a ComplexType
	 * and a AttributeType is the notion of inner strucutre represented by this
	 * method.
	 * </p>
	 * <p>
	 * You may access "views" of this information:
	 * </p>
	 * @return Collection of StructuralDescriptors describing allowable contents
	 */
	Collection<StructuralDescriptor> getProperties();
	
	/**
	 * Describes allowable content, indicating containment.
	 * <p>
	 * A collection of AttributeDescriptors (name and AttributeType) is used.
	 * We make no restrictions as to attribute order. All attributes are considered
	 * accessable by name (and order is thus insignificant).
	 * </p>
	 * <p>
	 * If you are modling a typing system where attribute order is relevant
	 * you may make use of a List. Similarly if duplicate attributes are
	 * disallowed you may make use of a Set.
	 * </p>
	 * <p>
	 * This method follows JavaBeans naming convention indicating this is part of
	 * our data model.
	 * </p>
	 */
	Collection<AttributeDescriptor> attributes();
	
	/**
	 * Allowable associations, indicating non containment relationships.
	 */
	Collection<AssociationDescriptor> associations();
	
}