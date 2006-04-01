package org.opengis.feature.type;


/**
 * Represents a scoped type name.
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
public interface TypeName {
	/**
	 * 
	 * @return
	 */
	public Namespace getNamespace();
	/**
	 * Namespace, usually a URI.
	 * <p>
	 * With generic name this is represented as getScope().toString().
	 * </p>
	 * @return Indication of attribute namespace
	 */
    public String getNamespaceURI();
    
    /**
     * Local name of attribute();
     * <p>
     * With generic name this is represented with name().toString()
     */
    public String getLocalPart();
    
    /**
     * Fully qualified name.
     * <p>
     * With generic name this is represented as getName().toString()
     * </p>
     */
    public String toString();
}