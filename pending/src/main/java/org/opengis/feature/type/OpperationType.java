package org.opengis.feature.type;

import java.util.List;
import java.util.Set;

import org.opengis.filter.Filter;

/**
 * Opperation information, immutable.
 * <p>
 * This represents a type of opperation that can be used to update the state of
 * a AttributeType, this gets more exciting with ComplexAttribtueType.
 * <p>
 * If you come to this from a pure java background this is where we capture the
 * methods in our dynamic model. Since we do not carry implementations here you
 * would do best to think of this as an interface definition.
 * </p>
 * <p>
 * To implement a method you will need to examine the OpperationDescriptor that
 * can put you in touch with actual functionality, often implemented directly in
 * java, or using a scripting language.
 * </p>
 * <p>
 * The implementation of the opperations is against the bound AttributeType "B":
 * <ul>
 * <li>the descriptor will be able to evaulate against B and produced a change
 *     state or result
 * <li>
 * </ul>
 */
public interface OpperationType<B extends AttributeType> extends PropertyType {	

	/**
	 * Access to super type information.
	 * <p>
	 * The super type may contain an implementation based on the  additional restrictions to be considered, or a
	 * definition of isNilable.
	 * </p>
	 * 
	 * @return AttributeType of supertype
	 */
	public OpperationType<? super B> getSuper();
	
	/**
	 * Indicate that this AttributeType may not be used directly.
	 * <p>
	 * This indicates that a subclass will need to actually define the opperation
	 * meaning here. As an example a graph system could have an Edge that would
	 * have "related" opperation returning that was abstract, and a sub type would
	 * define "related" based on touches, or contains.
	 * </p>
	 */
	public boolean isAbstract();
	
	/**
	 * AttributeType this opperation type can function against.
	 */
	public Class<B> getThis();

	/**
	 * We need the following AttributeTypes as parameters.
	 * 
	 * @return
	 */
	public List<AttributeType> getParameters();
	
	/**
	 * List of restrictions used to limit the allowable returned value.
	 * 
	 * @return List<Filter> used to validate allowable values.
	 */
	public Set<Filter> getRestrictions();
	
}