package org.opengis.feature.type;

import static org.opengis.annotation.Obligation.MANDATORY;
import static org.opengis.annotation.Specification.ISO_19103;

import org.opengis.annotation.UML;
import org.opengis.feature.AttributeName;
import org.opengis.util.NameSpace;

/**
 * Represents a Name, may be an entry in a Namespace.
 * <p>
 * This interface is method compatiable with QName, to facility those
 * transitioning form XML based models. As GenericName is finalized this
 * interface will be asked to fall into line with GenericName.
 * </p>
 * <p>
 * If you need to store the information for "getPrefix" please make use
 * of "client properties" facilities located on Namespace, Type and
 * Descriptor.
 * </p>
 * @author Jody Garnett, Refractions Research
 */
public interface Name {

	/**
     * Returns the scope (namespace) for this name.
     * <p>
     * The scope is set on creation and is not modifiable. The scope of a name determines where
     * a name "starts".
     * </p>
     * @since GeoAPI 2.1
     */
    @UML(identifier="scope", obligation=MANDATORY, specification=ISO_19103)
    Namespace getScope();
	
	/**
	 * String representation of namespace, usually a URI.
	 * 
	 * @return representation of namespace
	 */
    public String getNamespaceURI();
    
    /**
     * Retrieve the Local name.
     */
    public String getLocalPart();
    
    /**
     * Convert this name to a complete URI.
     * <p>
     * This URI is constructed with the getNamespaceURI and getLocalPart().
     * </p>
     * @return a uri for this name
     */
    public String getURI();
    
    /**
     * A local-independant representation of this name, see getURI().
     * 
     */
    public String toString();
}