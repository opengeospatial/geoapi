/*$************************************************************************************************
 **
 ** $Id$
 **
 ** $URL$
 **
 ** Copyright (C) 2008 Open GIS Consortium, Inc.
 ** All Rights Reserved. http://www.opengis.org/legal/
 **
 *************************************************************************************************/
package org.opengis.style;

import java.util.List;
import org.opengis.annotation.XmlElement;
import org.opengis.sld.Rule;

/**
 * Represents a style that applies to features or coverage.
 * There can only be two possible child for this interface :
 * <p>
 * <ul>
 *     <li>{@link FeatureTypeStyle}</li>
 *     <li>{@link CoverageStyle}</li>
 * </ul>
 * <p>
 * 
 * @version <A HREF="http://www.opengeospatial.org/standards/symbol">Symbology Encoding Implementation Specification 1.1.0</A>
 * @author Open Geospatial Consortium
 * @author Johann Sorel (Geomatys)
 * @author Chris Dillard (SYS Technologies)
 * @since GeoAPI 2.2
 */
public interface Style {

    /**
     * Possible versions
     * Can only by : {@code 1.1.0}
     * 
     */
    public static enum VERSION{
        V_1_1_0("1.1.0");
                    
        private final String number;
        
        VERSION(String num){
            number = num;
        }
        
        @Override
        public String toString(){
            return number;
        }
    };
    
    /*
     * Defaults semantic identifiers.
     * See {@link #getSemanticTypeIdentifiers} for details.
     */
    public static final String SEMANTIC_ID_LINE = "generic:line";
    public static final String SEMANTIC_ID_POLYGON = "generic:polygon";
    public static final String SEMANTIC_ID_POINT = "generic:point";
    public static final String SEMANTIC_ID_TEXT = "generic:text";
    public static final String SEMANTIC_ID_RASTER = "generic:raster";
    public static final String SEMANTIC_ID_ANY = "generic:any";
    
    
    
    /**
     * Returns a name for this style.
     * This can be any string that uniquely identifies this style within a given
     * canvas.  It is not meant to be human-friendly.  (The "title" property is
     * meant to be human friendly.)
     * @return a name for this style.
     */
    @XmlElement("Name")
    String getName();

    /**
     * Sets the name for this style.
     * See {@link #getName} for details.
     * @param name , new name for the style
     */
    @XmlElement("Name")
    void setName(String name);

    /**
     * Returns the description of this style.
     * 
     * @return Description with usual informations used
     * for user interfaces.
     */
    @XmlElement("Description")
    Description getDescription();

    /**
     * Sets the description of this style.
     * See {@link #getDescription} for details.
     * @param description : the new description
     */
    @XmlElement("Description")
    void setDescription( Description description);

   
    /**
     * Returns a string that identifies the more general "type" of geometry
     * that this style is meant to act upon.
     * In the current OGC SE specifications, this is an experimental element and
     * can take only one of the following values:
     * <p>
     * <ul>
     *   <li>{@code generic:point}</li>
     *   <li>{@code generic:line}</li>
     *   <li>{@code generic:polygon}</li>
     *   <li>{@code generic:text}</li>
     *   <li>{@code generic:raster}</li>
     *   <li>{@code generic:any}</li>
     * </ul>
     * <p>
     * The returned list is the "live" list and can be modified, both by adding and
     * removing rules. This is why there is no {@code setSemanticTypeIdentifiers} method.
     * @return the "live" list that can be modified
     */
    @XmlElement("SemanticTypeIdentifier")
    List<String> getSemanticTypeIdentifiers();

    /**
     * Returns the list of rules contained by this style. The returned list is
     * the "live" list and can be modified, both by adding and removing rules.
     * This is why there is no {@code setRules} method.
     * @return the "live" list that can be modified 
     * if this method return null than you must use the getResources method,
     * One of getRules or getResources must return a list, even an empty one.
     */
    @XmlElement("Rule")
    List<Rule> getRules(); 
    
    /**
     * Returns the list of online resources. The returned list is
     * the "live" list and can be modified, both by adding and removing resources.
     * This is why there is no {@code setResources} method.
     * @return the "live" list that can be modified
     * if this method return null than you must use the getResources method,
     * One of getRules or getResources must return a list, even an empty one.
     */
    @XmlElement("OnlineResource")
    List<OnlineResource> getResources(); 
        
    /**
     * Version of the style.
     * @return the version of the style
     */
    VERSION getVersion();
    
}
