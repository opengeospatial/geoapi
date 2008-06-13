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
 * <p>A portrayal operation holds the details for a particular portrayal operation.
 * It declares a set of formal parameters that are neeed when invoking the 
 * underlying rendering functions.
 * </p>
 * <p>
 * Ther should be one instance of portrayal specification class for each operation
 * defined by the portrayal service.
 * </p>
 * 
 * @version <A HREF="http://www.isotc211.org">ISO 19117 Portrayal</A>
 * @author Open Geospatial Consortium
 * @author Johann Sorel (Geomatys)
 * @since GeoAPI 2.2
 */
@UML(identifier="PF_PortrayalOperation", specification=ISO_19117)
public interface PortrayalOperation {
    
    /**
     * Returns the name of the operation.
     * 
     * @return String
     */
    @UML(identifier="name", obligation=MANDATORY, specification=ISO_19117)
    String getName();
            
    /**
     * Returns a description of the operation.
     * It is a human readable value.
     *  
     * @return InternationalString
     */
    @UML(identifier="description", obligation=OPTIONAL, specification=ISO_19117)
    InternationalString getDescription();
        
    /**
     * Returns the list of External functions.
     * 
     * @return Collection<ExternalFunction>
     */
    @UML(identifier="externalFunction", obligation=OPTIONAL, specification=ISO_19117)
    Collection<ExternalFunction> getExternalFunctions();
    
    /**
     * Returns a list of attributDefinition used by this operation. 
     * 
     * @return Collection<AttributDefinition>
     */
    @UML(identifier="formalParameter{ordered}", obligation=MANDATORY, specification=ISO_19117)
    Collection<AttributeDefinition> getFormalParameters();
    
    /**
     * Parameterset to use.
     * 
     * Those parameter are given when we invoke a 
     * portrayal operation, depending on the rendering device, this may result
     * on a return value or not.
     * <p>
     * <b>Caution</b> This method may change ! 
     * </p>
     * 
     * @param parameters 
     */
    void portray(ParameterSet parameters);
    
}
