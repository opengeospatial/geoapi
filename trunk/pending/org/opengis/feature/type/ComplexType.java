package org.opengis.feature.type;

import java.util.Collection;
import java.util.List;

import org.opengis.feature.Attribute;
import org.opengis.feature.schema.Descriptor;

/**
 * The type
 * @author Jody Garnett
 *
 * @param <T>
 */
public interface ComplexType<T/* extends List<Attribute>*/> extends AttributeType<T> {
	/**
	 * Java class bound to this complex type.
	 * <p>
	 * Note this method will return null if the complex type does not
	 * bind to a Java object.
	 * </p>
	 * @return Java binding, or null if not applicable
	 */
	Class<T> getBinding();

	/**
	 * Super is restricted to other ComplexType.
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
	ComplexType<? super T> getSuper();
	
	/**
	 * Access to multiplicity and order of allowed content.
	 * <p>
	 * Follows JavaBeans naming convention indicating this is part of
	 * our data model.
	 * </p>
	 * @see types
	 */
	Descriptor getDescriptor();

	/**
	 * Describes allowable content, indicates containment not validation .
	 * <p>
	 * This method could be removed - the information is completly
	 * available through schema();
	 * </p>
	 * This information is gathered from a breadth first search
	 * of schema(). Collection is returned so that FlatFeatureType
	 * can return an List, where as usually a Set is returned.
	 * </p>
	 * <p>
	 * Note: a AttributeType may be returned by more then one AttributeDescriptor in a Descriptor,
	 * if it is allowed in more then one ChoiceDescriptor or Sequence. While this
	 * represents a form of multiplicity it does not indicate any difference
	 * in containment.
	 * </p>
	 * <p>
	 * Follows Collections naming conventions indicating this is a derrived
	 * quality and not part of our data model.
	 * </p>
	 * @see getDescriptor
	 */
	Collection<AttributeType> types();
	
	/**
	 * Works as a search through available types().
	 * <p>
	 * This method is useful when there is no namespace collision on names,
	 * as with simple content.
	 * </p>
	 * @param name
	 * @return The "first" type that with AttributeType.getName
	 */
	AttributeType type( String name );
	
}
