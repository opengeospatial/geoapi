package org.opengis.feature;

import org.opengis.feature.type.Name;
import org.opengis.feature.type.Namespace;

/**
 * Represents a scoped attribute name.
 * <p>
 * This interface is method compatiable with QName, to facility those
 * transitioning form XML based models. As GenericName is finalized this
 * interface will be asked to fall into line with GenericnName.
 * </p>
 * <p>
 * If you need to store the information for "getPrefix" please make use
 * of per attribute client properties.
 * </p>
 * @author Jody Garnett, Refractions Research
 */
public interface AttributeName extends Name {
	
	/**
	 * This namespace is often based on a specific domain such as "science".
	 * <p>
	 * AttributeTypes are designed for resuse, so please don't assume that there
	 * is any relationship between type containement and namespace.
	 * </p>
	 * <p>
	 * As an example:
	 * <ul>
	 * <li>namespace of "Conductor" is "cim" which is defined with a voltage
	 * <li>namespace of "voltage" is "physics"
	 * </ul>
	 */
	public Namespace getScope();
    
}