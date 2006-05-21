package org.opengis.feature.xml;

import java.util.Set;

import org.opengis.feature.Attribute;
import org.opengis.feature.type.ComplexType;
import org.opengis.filter.Filter;

/**
 * Indicates a ComplexType supporting at most one attribute (at a time).
 * 
 * @author Jody Garnett, Refractions Research
 */
public interface ChoiceType<S extends Set<Attribute>> extends ComplexType<Attribute,S> {
	/**
	 * Inorder to smoothly intergrate with the feature model this list include a filter limiting the number
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
