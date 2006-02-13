package org.opengis.feature.schema;

import java.util.List;

import org.opengis.feature.Attribute;


/**
 * Descriptor captures the order of types withing a ComplexType.
 * <p>
 * Order is defined by a tree structure of the following:
 * <ul>
 * <li>AttributeDescriptor - indicating a AttributeType with multiplicity information
 * <li>ChoiceDescriptor - indicating a choice between options
 * <lI>OrderedSchema - indicating a perscribed order is required
 * <li>AllDescriptor - indicating an unordered set
 * </ul>
 * These form the substance of the GeoTools Feature Model, as a convience 
 * a further subclass of OrderedDescriptor is available for simple content.
 * </p>
 * 
 * @author Jody Garnett
 *
 */
public interface Descriptor{
	public int getMinOccurs();
	public int getMaxOccurs();

	/**
	 * Performs the validation rules of the Descriptor type to the given content
	 * list
	 * 
	 * @param content
	 * @return
	 * @throws NullPointerException
	 *             if <code>content</code> of any its members is
	 *             <code>null</code>. If a content member is meant to be
	 *             null, the Attribute content has to be null, not the Attribute
	 *             instance.
	 * @throws IllegalArgumentException
	 *             if the structure of <code>content</code> does not passes
	 *             the validation rules proper of the Descriptor instance.
	 */
	public void validate(List<Attribute> content)
			throws NullPointerException, IllegalArgumentException;
}
