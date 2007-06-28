package org.opengis.feature.type;

import java.util.List;
import java.util.Set;

import org.opengis.filter.Filter;

/**
 * Operation information, immutable.
 * <p>
 * This represents a type of opperation that can be used to update the state of
 * an AttributeType, this gets more exciting with ComplexAttribtueType (or Feature)
 * <p>
 * If you come to this from a pure java background this is where we capture the
 * methods in our dynamic model. Since we do not carry implementations here you
 * would do best to think of this as an interface definition.
 * </p>
 * <p>
 * To implement a method you will need to examine the OperationDescriptor that
 * can put you in touch with actual functionality, often implemented directly in
 * java, or using a scripting language.
 * </p>
 * <p>
 * The implementation of the operations is against the bound AttributeType "B":
 * <ul>
 * <li>the descriptor will be able to evaulate against an instance of B
 *     and produced a change state or result
 * <li>
 * </ul>
 * <p>
 * Notes:
 * <ul>
 * <li>TODO: We will need to set up a system for advertising expected exceptions
 * <li>if the type isAbstract then the call method is not implemented
 * </ul>
 * 
 * @author Jody Garnett, Refractions Research, Inc.
 */
 public interface OperationType<B, T extends AttributeType<B>> extends PropertyType {	

	/**
	 * Access to super type information.
	 * <p>
	 * The super type may contain an implementation based on the  additional restrictions to be considered, or a
	 * definition of isNilable.
	 * </p>
	 * 
	 * @return AttributeType of supertype
	 */
	 OperationType<? super B, ? super T> getSuper();
	
	/**
	 * Indicate that this OperationType may not be used directly.
	 * <p>
	 * This indicates that a sub type will need to actually define the operation
	 * meaning here. As an example a graph system could have an Edge that would
	 * have "related" operation returning that was abstract, and a sub type road
	 * would define "related" based on touches, or "contains" or "common vertex".
	 * </p>
	 */
	 boolean isAbstract();
	
	/**
	 * AttributeType this operation type can function against.
	 */
	 T getTarget();

	 /**
	  * Inidcates the expected result type, may be <code>null</code>.
	  * 
	  * @return expected result type, may be <code>null</code>
	  */
	 AttributeType getResult();
	 
	/**
	 * We need the following AttributeTypes as parameters.
	 * <p>
	 * Note we do not need AttributeDescriptors here as parameters
	 * are ordered, so name is not needed.
	 * </p>
	 * @return inidicates paramters required for operation
	 */
	List<AttributeType> getParameters();
	
	/**
	 * List of restrictions used to limit the allowable returned value.
	 * 
	 * @return Set<Filter> used to validate allowable values.
	 */
	 Set<Filter> getRestrictions();
}