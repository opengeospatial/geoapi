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
package org.opengis.style.portrayal;

import org.opengis.util.InternationalString;
import org.opengis.annotation.UML;

import static org.opengis.annotation.Obligation.*;
import static org.opengis.annotation.Specification.*;

/**
 * AttributDefinition is used to define the formal parameters of external functions and the underlying
 * rendering operations of the portrayal service.
 * 
 * @version <A HREF="http://www.isotc211.org">ISO 19117 Portrayal</A>
 * @author Open Geospatial Consortium
 * @author Johann Sorel (Geomatys)
 * @since GeoAPI 2.2
 */
@UML(identifier="PF_AttributeDefinition", specification=ISO_19117)
public interface AttributeDefinition {

    /**
     * Returns the name of the attribute definition.
     * It shall be a legal and unique name for this function.
     * 
     * @return String
     */
    @UML(identifier="name", obligation=MANDATORY, specification=ISO_19117)
    String getName();
            
    /**
     * Returns a description of the usage of this attribute.
     * It is a human readable value.
     *  
     * @return InternationalString
     */
    @UML(identifier="description", obligation=OPTIONAL, specification=ISO_19117)
    InternationalString getDescription();
        
    /**
     * Returns the class type for this attribute.
     * It shall be a basic legal type.
     * 
     * @return Class
     */
    @UML(identifier="type", obligation=MANDATORY, specification=ISO_19117)
    Class getReturnType();
            
    /**
     * Returns a Default Value for this attribut.
     * This value is optional.
     * 
     * @return
     */
    @UML(identifier="defaultValue", obligation=OPTIONAL, specification=ISO_19117)
    Object getDefault();
        
}
