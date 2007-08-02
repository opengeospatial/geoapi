package org.opengis.feature.xml;

import java.util.Collection;
import java.util.Set;

import org.opengis.feature.Property;
import org.opengis.feature.type.ComplexType;
import org.opengis.feature.type.StructuralDescriptor;
import org.opengis.filter.Filter;

/**
 * Indicates a ComplexType supporting at most one attribute (at a time).
 * 
 * @author Jody Garnett, Refractions Research
 */
public interface ChoiceType extends ComplexType {
	
	/**
	 * A Set<Property> only one of which may be chosen.
	 */
	public Class<Collection<Property>> getBinding();
	
	/** A Set<Property> only one of which may be chosen. */
	public Set<StructuralDescriptor> getProperties();
	
	/**
	 * In order to smoothly integrate with the feature model this list include a filter limiting the number
	 * of attribute to one.
	 * <p>
	 * This exact nature of this limitation is dependent on the capabilities of your particular implemenation
	 * of the filter capabilities. You will need to ensure that your capabilities support a filter equivilent
	 * to the following:
	 * <pre><code>
	 * length(*) EQUALS 1
	 * </code></pre>
	 * Where: '*' above is the XPath expression matching all attributes. Restrictions are evaulated with
	 * the ChoiceAttribute as the root node.
	 * </p>
	 * 
	 */
	Set<Filter> getRestrictions();
}
