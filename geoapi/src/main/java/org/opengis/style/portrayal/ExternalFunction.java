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

import java.util.Collection;
import org.opengis.util.InternationalString;
import org.opengis.annotation.UML;

import static org.opengis.annotation.Obligation.*;
import static org.opengis.annotation.Specification.*;

/**
 * <p>
 * External functions are used to perform computations that sometimes are needed to evaluate the query
 * statements and/or perform the portrayal rules.
 * </p>
 * 
 * <p>
 * There are no limitations to the operations it can perform or the return types it can have.<br>
 * External functions shall be modeled as operations, as described in ISO 19109.<br>
 * External functions shall not be used in the default portrayal specification.
 * </p>
 *  
 * @version <A HREF="http://www.isotc211.org">ISO 19117 Portrayal</A>
 * @author Open Geospatial Consortium
 * @author Johann Sorel (Geomatys)
 * @since GeoAPI 2.2
 */
@UML(identifier="PF_ExternalFunction", specification=ISO_19117)
public interface ExternalFunction {

    /**
     * Formal parameters for this function.
     * this is a immutable copy of the collection.
     * 
     * @return collection of AttributeDefinition
     */
    @UML(identifier="formalParameter", obligation=MANDATORY, specification=ISO_19117)
    Collection<AttributeDefinition> getParameters();
            
    /**
     * Returns the name of the function.
     * It shall contain no spaces and always start with a letter 
     * or and underscore character.
     * 
     * @return String
     */
    @UML(identifier="functionName", obligation=MANDATORY, specification=ISO_19117)
    String getName();
        
    /**
     * Returns the class type for this function.
     * 
     * @return Class
     */
    @UML(identifier="returnType", obligation=MANDATORY, specification=ISO_19117)
    Class getReturnType();
        
    /**
     * Returns a description of the function.
     * It is a human readable value.
     *  
     * @return InternationalString
     */
    @UML(identifier="description", obligation=OPTIONAL, specification=ISO_19117)
    InternationalString getDescription();
        
}
